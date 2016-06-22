package com.mygdx.game.Spiel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mygdx.game.NetworkConnection;
import com.mygdx.game.R;

/**
 * Created by Moers on 12.05.16.
 */



public class GameView extends SurfaceView {
    Random rnd = new Random();
    int wuerfel = rnd.nextInt((6 ) + 1);
    private List<Sprite> spriteList = new ArrayList<Sprite>();
    private List<Integer> spriteListNum = new ArrayList<Integer>();
    private int spritenumber=0;
    private int spriteposition=0;
    private SurfaceHolder surfaceHolder;

    private Bitmap background;
    private GameLoopThread theGameLoopThread;
    private final RectF rectF = new RectF();
    private GameActivity theGameActivity = new GameActivity();
    private String id="10.0.0.138";
    private long lastClick;
    private int i=0;
    private int index=0;
    private byte[]spritelist;
    byte[] clientMessage=new byte[1];
    byte[] clientId=new byte[1];
    private Bitmap bmpeins, bmpzwei, bmpdrei, bmpvier,bmpfuenf,bmpsechs;
    private List<TempSprite> temps = new ArrayList<TempSprite>();
    private List<String>clients = new ArrayList<String>();
    private String s=new String();

    @SuppressLint("WrongCall") public GameView(Context context) {
        super(context);

        theGameLoopThread = new GameLoopThread(this);
        surfaceHolder = getHolder();
        theGameActivity = (GameActivity) context;

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                theGameLoopThread.setRunning(false);
                while(retry){
                    try {
                        theGameLoopThread.join();
                        retry=false;
                    }catch(InterruptedException e){

                    }
                }

            }

            public void surfaceCreated(SurfaceHolder holder) {
                theGameLoopThread.setRunning(true);
                theGameLoopThread.start();

            }

            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                       int height) {
                // TODO Auto-generated method stub

            }
        });
        background = BitmapFactory.decodeResource(getResources(),
                R.drawable.brett);

        bmpeins = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        bmpzwei = BitmapFactory.decodeResource(getResources(), R.drawable.two);
        bmpdrei = BitmapFactory.decodeResource(getResources(), R.drawable.three);
        bmpvier = BitmapFactory.decodeResource(getResources(), R.drawable.four);
        bmpfuenf = BitmapFactory.decodeResource(getResources(), R.drawable.five);
        bmpsechs = BitmapFactory.decodeResource(getResources(), R.drawable.six);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.DKGRAY);
        drawBackground(canvas);

        //Erklärung: GameView= Host     GameView2=Client
        if(spritenumber<1 && NetworkConnection.getInstance().isHost()){
            createSprite(i, id); //Für sich selber sprite erstellen
             spritenumber = spriteList.size();

            clients=NetworkConnection.getInstance().getlEndpointIDs();
                for(int k=0;k<clients.size();k++) {
                    createSprite(i, clients.get(k));
                    clientId[0] = (byte) i;
                    NetworkConnection.getInstance().sendMessageToClient(clientId, clients.get(k));
                }
            spritelist=new byte[100000];
            spritelist=spriteList.toString().getBytes();
            NetworkConnection.getInstance().sendMessageToAllClients(spritelist);

                    spritenumber = spriteList.size();

        }



        for (int i = temps.size() - 1; i >= 0; i--) {
            temps.get(i).draw(canvas);
        }
        //Sprites zeichnen
        for (Sprite sprite : spriteList) {
            sprite.onDraw(canvas);
        }


    }
    //Sprites hinzufügen und in Sprite liste adden
    private void createSprite(int index,String id) {
        Bitmap bmp = null;
        switch (index) {
            case 0:
                bmp = BitmapFactory.decodeResource(getResources(),
                        R.drawable.kegel_blau);
                break;
            case 1:
                bmp = BitmapFactory.decodeResource(getResources(),
                        R.drawable.kegel_lila);
                break;
            case 2:
                bmp = BitmapFactory.decodeResource(getResources(),
                        R.drawable.kegel_schwarz);
                break;
            case 3:
                bmp = BitmapFactory.decodeResource(getResources(),
                        R.drawable.kegel_rot);
                break;
        }
        Sprite sprite = new Sprite(this, bmp,id);


        spriteList.add(sprite);
        spriteListNum.add(index);
        i++;
    }


    @Override
    //Wenn Sprite gedrückt wird
    public boolean onTouchEvent(MotionEvent event) {
        if(NetworkConnection.getInstance().isHost()){
        if (System.currentTimeMillis() - lastClick > 300) {
            lastClick = System.currentTimeMillis();
            synchronized (getHolder()) {
                Sprite sprite = spriteList.get(0);
                if (sprite.isTouched(event.getX(), event.getY())) {

                    System.out.println("Drücke");
                    wuerfel = rnd.nextInt(5) + 1;
                    checkwuerfeln(wuerfel, event.getX(), event.getY());
                    System.out.println(wuerfel);

                    kalkuliere("10.0.0.138", wuerfel);
                }
            }
        }
            else{
            if (System.currentTimeMillis() - lastClick > 300) {
                lastClick = System.currentTimeMillis();
                synchronized (getHolder()) {
                    Sprite sprite = spriteList.get(index);
                    if (sprite.isTouched(event.getX(), event.getY())) {

                        int wuerfel = rnd.nextInt((6 ) + 1);
                        clientMessage[0]=(byte)wuerfel;
                        NetworkConnection.getInstance().sendMessageToHost(clientMessage);


                    }




                }
            }
            }




        }


        return true;
    }

    //warte auf Pakete
    //Für Paket mit einem x wert und einem y wert warten:

    public void kalkuliere(String id,int x){
        System.out.println("Kalkuliere");
        if( spriteList.get(spriteposition).getId()==id){



            spriteList.get(spriteposition).setxSpeed((getWidth()/10)*wuerfel);
            //sprite.setxSpeed(getWidth()/10*1);
            spriteList.get(spriteposition).setySpeed(getHeight()/10);
            if ( (spriteList.get(spriteposition).getx() <70 && spriteList.get(spriteposition).gety() < -30)) {

                //Ende evtl noch sieger id
                NetworkConnection.getInstance().sendMessageToAllClients("Ende".getBytes());

                theGameActivity.onGameOver();
            }
            else{
                System.out.println(spriteposition+ " "+spritenumber);
                //Sende Spritelist
                spritelist=spriteList.toString().getBytes();
                NetworkConnection.getInstance().sendMessageToAllClients(spritelist);

                if(spriteposition>=spritenumber-1)
                    spriteposition=0;
                else
                    spriteposition++;
            }
            System.out.println(x);

        }

    }

    /*CLIENT SEITIG

            WARTE AUF "ENDE", dann beende
            WARTE AUF SPRITELISTE
            WARTE AUF "ZAHL"
            WARTE AUF "DU BIST DRAN"

    SERVER SEITIG
            WARTE AUF WUERFELZAHL
*/

    //Hintergrund
    public void drawBackground(Canvas canvas){
        rectF.set(0, 0, getMeasuredWidth(), getMeasuredHeight());

        canvas.drawBitmap(background, null, rectF, null);



    }

    public void checkwuerfeln(int i, float x, float y){
        if (wuerfel == 1)
            temps.add(new TempSprite(temps, this,
                    x, y, bmpeins));
        else if (wuerfel == 2)
            temps.add(new TempSprite(temps, this,
                    x, y, bmpzwei));
        else if (wuerfel == 3)
            temps.add(new TempSprite(temps, this,
                    x, y, bmpdrei));
        else if (wuerfel == 4)
            temps.add(new TempSprite(temps, this,
                    x, y, bmpvier));
        else if (wuerfel == 5)
            temps.add(new TempSprite(temps, this,
                    x, y, bmpfuenf));
        else if (wuerfel == 6)
            temps.add(new TempSprite(temps, this,
                    x, y, bmpsechs));
    }

}



