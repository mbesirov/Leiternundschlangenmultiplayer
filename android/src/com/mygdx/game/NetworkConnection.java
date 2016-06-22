package com.mygdx.game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AppIdentifier;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;

import android.net.ConnectivityManager;

import java.util.ArrayList;
import java.util.List;

public class NetworkConnection implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener,
        Connections.ConnectionRequestListener,
        Connections.MessageListener,
        Connections.EndpointDiscoveryListener {

    public List<String> getlEndpointIDs() {
        return lEndpointIDs;
    }

    public void setlEndpointIDs(List<String> lEndpointIDs) {
        this.lEndpointIDs = lEndpointIDs;
    }

    private List<String>lEndpointIDs = new ArrayList<String>();

    private static final long TIMEOUT_ADVERTISE = 1000L * 30L;
    private static final long TIMEOUT_DISCOVER = 1000L * 30L;
    private Context context;

    public void setHost(boolean host) {
        this.isHost = host;
    }

    public boolean isHost() {
        return isHost;
    }

    /**
     * Possible states for this application:
     *      IDLE - GoogleApiClient not yet connected, can't do anything.
     *      READY - GoogleApiClient connected, ready to use Nearby Connections API.
     *      ADVERTISING - advertising for peers to connect.
     *      DISCOVERING - looking for a peer that is advertising.
     *      CONNECTED - found a peer.
     */
    public @interface NearbyConnectionState {}
    private static final int STATE_IDLE = 1023;
    private static final int STATE_READY = 1024;
    private static final int STATE_ADVERTISING = 1025;
    private static final int STATE_DISCOVERING = 1026;
    private static final int STATE_CONNECTED = 1027;
    private static final String TAG = "Leitern";

    /** GoogleApiClient for connecting to the Nearby Connections API **/
    private GoogleApiClient mGoogleApiClient;

    /** Views and Dialogs **/
    private TextView mDebugInfo;
    private EditText mMessageText;
    private AlertDialog mConnectionRequestDialog;
    private MyListDialog mMyListDialog;

    /** The current state of the application **/
    @NearbyConnectionState
    private int mState = STATE_IDLE;

    /** The endpoint ID of the connected peer, used for messaging **/
    private String mOtherEndpointId;
    private String[] EndpointList=new String [4];
    private boolean isHost=false;
    private static NetworkConnection instance;
    private NetworkConnection(){}
    private int i=0;
    public static NetworkConnection getInstance(){
        if(instance == null)
            instance = new NetworkConnection();

        return instance;

    }
    private void startDiscovery() {

        if (!isConnectedToNetwork()) {
            debugLog("startDiscovery: not connected to WiFi network.");
            return;
        }

        // Discover nearby apps that are advertising with the required service ID.
        String serviceId = context.getString(R.string.service_id);
        Nearby.Connections.startDiscovery(mGoogleApiClient, serviceId, 0, this)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            debugLog("startDiscovery:onResult: SUCCESS");
                        } else {
                            debugLog("startDiscovery:onResult: FAILURE");

                            // If the user hits 'Discover' multiple times in the timeout window,
                            // the error will be STATUS_ALREADY_DISCOVERING
                            int statusCode = status.getStatusCode();
                            if (statusCode == ConnectionsStatusCodes.STATUS_ALREADY_DISCOVERING) {
                                debugLog("STATUS_ALREADY_DISCOVERING");
                            }
                        }
                    }
                });
    }

    private void startAdvertising() {
        if (!isConnectedToNetwork()) {
            debugLog("startAdvertising: not connected to WiFi network.");
            return;
        }
        isHost=true;

        // Advertising with an AppIdentifer lets other devices on the network discover
        // this application and prompt the user to install the application.

        final String name = context.getString(R.string.service_id);
        List<AppIdentifier> appIdentifierList = new ArrayList<>();
        appIdentifierList.add(new AppIdentifier(name));
        AppMetadata appMetadata = new AppMetadata(appIdentifierList);

        // Advertise for Nearby Connections. This will broadcast the service id defined in
        // AndroidManifest.xml. By passing 'null' for the name, the Nearby Connections API
        // will construct a default name based on device model such as 'LGE Nexus 5'.

        Nearby.Connections.startAdvertising(mGoogleApiClient, name, appMetadata, 0,
                this).setResultCallback(new ResultCallback<Connections.StartAdvertisingResult>() {
            @Override
            public void onResult(Connections.StartAdvertisingResult result) {
                if (result.getStatus().isSuccess()) {
                    debugLog("startAdvertising:onResult: SUCCESS");
                    Toast.makeText(context, "Succesadvertising", Toast.LENGTH_LONG).show();
                } else {
                    debugLog("startAdvertising:onResult: FAILURE ");

                    // If the user hits 'Advertise' multiple times in the timeout window,
                    // the error will be STATUS_ALREADY_ADVERTISING
                    int statusCode = result.getStatus().getStatusCode();
                    if (statusCode == ConnectionsStatusCodes.STATUS_ALREADY_ADVERTISING) {
                        debugLog("STATUS_ALREADY_ADVERTISING");
                    }
                }
            }
        });
    }

    public void connect(Context ctx){
        context = ctx;
        mGoogleApiClient = new GoogleApiClient.Builder(context).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Nearby.CONNECTIONS_API).build();
        mGoogleApiClient.connect();
    }

    public void disconnect(){
        if(mGoogleApiClient!=null&&mGoogleApiClient.isConnected())
            mGoogleApiClient.disconnect();
    }
    @Override
    public void onConnected(Bundle bundle) {
        if(isHost){
            startAdvertising();
        }else{
            startDiscovery();
        }
     }

    @Override
    public void onConnectionSuspended(int i) {

    }
    private void sendMessage(byte[] msg) {
        // Sends a reliable message, which is guaranteed to be delivered eventually and to respect
        // message ordering from sender to receiver. Nearby.Connections.sendUnreliableMessage
        // should be used for high-frequency messages where guaranteed delivery is not required, such
        // as showing one player's cursor location to another. Unreliable messages are often
        // delivered faster than reliable messages.;
        Nearby.Connections.sendReliableMessage(mGoogleApiClient, mOtherEndpointId, msg);
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void sendMessageToClient(String clientName,byte[] message){
        if(!isHost){
            Nearby.Connections.sendReliableMessage(mGoogleApiClient, clientName, message);
        }
    }
    @Override
    public void onConnectionRequest(final String endpointId, String deviceId, final String endpointName, byte[] payload) {


        // This device is advertising and has received a connection request. Show a dialog asking
        // the user if they would like to connect and accept or reject the request accordingly.
        mConnectionRequestDialog = new AlertDialog.Builder(context)
                .setTitle("Connection Request")
                .setMessage("Do you want to connect to " + endpointName + "?")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        byte[] payload = null;
                        Nearby.Connections.acceptConnectionRequest(mGoogleApiClient, endpointId,
                                payload, NetworkConnection.this)
                                .setResultCallback(new ResultCallback<Status>() {
                                    @Override
                                    public void onResult(Status status) {
                                        if (status.isSuccess()) {
                                            debugLog("acceptConnectionRequest: SUCCESS");
                                            Toast.makeText(context, endpointName + " joined game", Toast.LENGTH_LONG).show();
                                            //sendMessage("Joined the game".getBytes());
                                            if (!lEndpointIDs.contains(endpointId)) {
                                                sendMessageToAllClients(("Toast;"+endpointName+" has joined the game").getBytes());
                                                lEndpointIDs.add(endpointId);
                                            }

                                        } else {
                                            debugLog("acceptConnectionRequest: FAILURE");
                                        }
                                    }
                                });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Nearby.Connections.rejectConnectionRequest(mGoogleApiClient, endpointId);
                    }
                }).create();

        mConnectionRequestDialog.show();
    }
    private void connectTo(String endpointId, final String endpointName) {
        debugLog("connectTo:" + endpointId + ":" + endpointName);

        // Send a connection request to a remote endpoint. By passing 'null' for the name,
        // the Nearby Connections API will construct a default name based on device model
        // such as 'LGE Nexus 5'.
        String myName = null;
        byte[] myPayload = null;
        Nearby.Connections.sendConnectionRequest(mGoogleApiClient, myName, endpointId, myPayload,
                new Connections.ConnectionResponseCallback() {
                    @Override
                    public void onConnectionResponse(String endpointId, Status status,
                                                     byte[] bytes) {
                        Log.d(TAG, "onConnectionResponse:" + endpointId + ":" + status);
                        if (status.isSuccess()) {
                            debugLog("onConnectionResponse: " + endpointName + " SUCCESS");
                            Toast.makeText(context, "Connected to " + endpointName,
                                    Toast.LENGTH_SHORT).show();

                            mOtherEndpointId = endpointId;
                        } else {
                            debugLog("onConnectionResponse: " + endpointName + " FAILURE");
                        }
                    }
                }, this);
    }
    private void debugLog(String msg){
        Log.d(TAG, msg);
    }


    @Override
    public void onEndpointFound(String endpointId, String deviceId, String serviceId, String endpointName) {
        Log.d(TAG, "onEndpointFound:" + endpointId + ":" + endpointName);

        // This device is discovering endpoints and has located an advertiser. Display a dialog to
        // the user asking if they want to connect, and send a connection request if they do.
        if (mMyListDialog == null) {
            // Configure the AlertDialog that the MyListDialog wraps
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle("Endpoint(s) Found")
                    .setCancelable(true)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mMyListDialog.dismiss();
                        }
                    });

            // Create the MyListDialog with a listener
            mMyListDialog = new MyListDialog(context, builder, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String selectedEndpointName = mMyListDialog.getItemKey(which);
                    String selectedEndpointId = mMyListDialog.getItemValue(which);

                    NetworkConnection.this.connectTo(selectedEndpointId, selectedEndpointName);
                    mMyListDialog.dismiss();
                }
            });
        }

        mMyListDialog.addItem(endpointName, endpointId);
        mMyListDialog.show();
    }

    @Override
    public void onEndpointLost(String endpointId) {
        debugLog("onEndpointLost:" + endpointId);

        // An endpoint that was previously available for connection is no longer. It may have
        // stopped advertising, gone out of range, or lost connectivity. Dismiss any dialog that
        // was offering a connection.
        if (mMyListDialog != null) {
            mMyListDialog.removeItemByValue(endpointId);
        }
    }

    @Override
    public void onMessageReceived(String s, byte[] bytes, boolean b) {
        String[] msg=bytes.toString().split(";");
        if(msg.length>0) {
            String type = msg[0];
            if(type == "Toast")
                Toast.makeText(context, msg[1], Toast.LENGTH_LONG).show();
            else if(type == "MyTurn"){

            }
        }
    }

    @Override
    public void onDisconnected(String s) {

    }

    @Override
    public void onClick(View v) {

    }

    private boolean isConnectedToNetwork() {
        ConnectivityManager connManager = (ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return (info != null && info.isConnectedOrConnecting());
    }

    public void sendMessageToHost(byte[] message){
        if(!isHost){
            Nearby.Connections.sendReliableMessage( mGoogleApiClient, mOtherEndpointId, message);
        }
    }

    public void sendMessageToAllClients(byte[] message){
        if(isHost && lEndpointIDs.size()>0){
            Nearby.Connections.sendReliableMessage( mGoogleApiClient,lEndpointIDs, message);
        }
    }

    public void sendMessageToOtherClients(byte[] message, String sendingNotTo){
        ArrayList help = new ArrayList(lEndpointIDs);
        help.remove(sendingNotTo);
        if(isHost && help.size()!=0){
            Nearby.Connections.sendReliableMessage( mGoogleApiClient,help, message);
        }
    }

    public void sendMessageToClient(byte[] message,String toClient){
        if(isHost){
            Nearby.Connections.sendReliableMessage( mGoogleApiClient,toClient, message);
        }
    }
}
