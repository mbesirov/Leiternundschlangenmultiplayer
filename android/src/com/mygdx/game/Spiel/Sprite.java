package com.mygdx.game.Spiel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Moers on 13.05.16.
 */
public class Sprite {
    private int x = 0;
    private int y = 0;
    private int sprung;
    private int a;
    private int b;
    public boolean vorbei;
    private int movex;
    private int diffx;
    private int diffy;
    private boolean links= false;
    private boolean rechts=true;
    private boolean rauf = false;
    public boolean runter = false;


    private int xSpeed;
    private int anzahl=0;
    private int ySpeed;
    private int width;
    private int height;
    private Bitmap bmp;
    private GameView theGameView;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public Sprite(GameView theGameView, Bitmap bmp,String id) {
        this.theGameView = theGameView;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
        this.id=id;
        //Random rnd = new Random();
        //x = rnd.nextInt(theGameView.getWidth() - width);
        //y = rnd.nextInt(theGameView.getHeight() - height);


x=-30+theGameView.getWidth()/10*0;
      // y = theGameView.getHeight() - height - 50;
    y = theGameView.getHeight() - height - 50-theGameView.getHeight()/10*0;

        //ySpeed = rnd.nextInt(10) - 4;
        //(xSpeed = rnd.nextInt(10) - 4;
        ySpeed = 0;
        xSpeed = 0;
    }

    public void setxSpeed(int Speed) {
        xSpeed = Speed;
    }

    public void setySpeed(int Speed) {
        ySpeed = Speed;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setx(int koordinatex) {
        x = koordinatex;
    }

    public void sety(int koordinatey) {
        y = koordinatey;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }




    private void bounceOff() {


/*----------------------------------------------------------------------------------------------------------------------*/
/*--------------------------------------------------------------Leitern-------------------------------------------------*/
/*----------------------------------------------------------------------------------------------------------------------*/
//leiter 8 auf 26
   if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*0)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*0)+30 ) && (x== (theGameView.getWidth()/10)*7 -30)) {
            y = y - ySpeed * 2 ;
            x = (theGameView.getWidth()/10)*5 -40;

        }

//leiter 21 auf 82
   if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*2)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*2)+30 ) && (x>-50 && x<-5)) {
            y = y - (theGameView.getHeight()/10)* 6 ;
            x = (theGameView.getWidth()/10)*1 -40;

        }

//leiter 43 auf 77




       if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)+30 )&&  (x>(((theGameView.getWidth()/10)*2) -30)-20) && (x<(((theGameView.getWidth()/10)*2 )-30)+20) )  {

            y = y - (theGameView.getHeight()/10)*3 ;
            x = (theGameView.getWidth()/10)*3 -40;
            links=true;
            rechts=false;

        }

//leiter 50 auf 91
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)+30 )&&  (x>(((theGameView.getWidth()/10)*9) -30)-20) && (x<(((theGameView.getWidth()/10)*9 )-30)+20) ) {
            y = y - (theGameView.getHeight() / 10) * 5;
            links = true;
            rechts = false;
        }


//leiter 54 auf 93
       if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*5)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*5)+30 ) && (x>(theGameView.getWidth()/10*6 -30)-20 && x<(theGameView.getWidth()/10*6 -30)+20) ) {
            y = y - (theGameView.getHeight() / 10) * 4;
             x = (theGameView.getWidth()/10)*7 -40;
            links = true;
            rechts = false;
        }


//leiter 66 auf 87
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*6)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*6)+30 )&&  (x>(((theGameView.getWidth()/10)*5) -30)-20) && (x<(((theGameView.getWidth()/10)*5 )-30)+20) )  {
            y = y - (theGameView.getHeight() / 10) * 2;
            x = (theGameView.getWidth()/10)*6 -40;
           rechts=true;
            links =false;
        }

        // leiter 62 auf 96
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*6)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*6)+30 )&& (x>(((theGameView.getWidth()/10)*1) -30)-20) && (x<(((theGameView.getWidth()/10)*1 )-30)+20) ) {
            y = y - (theGameView.getHeight() / 10) * 3;
            x = (theGameView.getWidth()/10)*4 -40;
            links=true;
            rechts =false;
        }

//leiter 80 auf 100
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*7)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*7)+30 ) &&  (x>-50 && x<-5)){
            y = y - (theGameView.getHeight() / 10) * 2;
            x = (theGameView.getWidth() / 10) * 0 - 40;
            links = true;
            rechts = false;
        }

/*----------------------------------------------------------------------------------------------------------------------*/
/*--------------------------------------------------------------Schlangen-----------------------------------------------*/
/*----------------------------------------------------------------------------------------------------------------------*/

//Schlange 98-28
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*9)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*9)+30 ) && (x>(((theGameView.getWidth()/10)*2) -30)-20) && (x<(((theGameView.getWidth()/10)*2 )-30)+20) ) {
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 2;
            x = (theGameView.getWidth() / 10) * 7 - 40;
            links=false;
            rechts =true;
        }

//Schlange 95-24
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*9)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*9)+30 ) && (x>(((theGameView.getWidth()/10)*5) -30)-20) && (x<(((theGameView.getWidth()/10)*5 )-30)+20) ) {
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 2;
            x = (theGameView.getWidth() / 10) * 3 - 40;
            links=false;
            rechts =true;
        }

//Schlange 92-51
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*9)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*9)+30 ) && (x>(((theGameView.getWidth()/10)*8) -30)-20) && (x<(((theGameView.getWidth()/10)*8 )-30)+20) ) {
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 5;
            x = (theGameView.getWidth() / 10) * 9 - 40;
            links=true;
            rechts =false;
        }

// Schlange 83-19
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*8)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*8)+30 ) && (x>(((theGameView.getWidth()/10)*2) -30)-20) && (x<(((theGameView.getWidth()/10)*2 )-30)+20) ) {
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 1;
            x = (theGameView.getWidth() / 10) * 1 - 40;
            links=true;
            rechts =false;
        }

//Schlange 73-1
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*7)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*7)+30 ) && (x>(((theGameView.getWidth()/10)*7) -30)-20) && (x<(((theGameView.getWidth()/10)*7 )-30)+20) ){
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10)*0;
            x = (theGameView.getWidth() / 10) *0 - 40;
            links=false;
            rechts =true;
        }

//Schlange 69-33
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*6)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*6)+30 ) && (x>(((theGameView.getWidth()/10)*8) -30)-20) && (x<(((theGameView.getWidth()/10)*8 )-30)+20) ) {
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 3;
            x = (theGameView.getWidth() / 10) * 7 - 40;
            links=true;
            rechts =false;
        }

//Schlange 64-36
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*6)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*6)+30 ) && (x>(((theGameView.getWidth()/10)*3) -30)-20) && (x<(((theGameView.getWidth()/10)*3 )-30)+20) ){
            //y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 3;
            //x = (theGameView.getWidth() / 10) * 4 - 40;
            links=true;
            rechts =false;
        }

//Schlange 59-17
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*5)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*5)+30 ) &&(x>(((theGameView.getWidth()/10)*1) -30)-20) && (x<(((theGameView.getWidth()/10)*1 )-30)+20) ){
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 1;
            x = (theGameView.getWidth() / 10) * 3 - 40;
            links=true;
            rechts =false;
        }

//Schlange 55-7
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*5)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*5)+30 ) && (x>(((theGameView.getWidth()/10)*5) -30)-20) && (x<(((theGameView.getWidth()/10)*5 )-30)+20) ) {
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10)*0;
            x = (theGameView.getWidth() / 10) * 6 - 40;
            links=false;
            rechts =true;
        }

//Schlange 52-11
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*5)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*5)+30 ) && (x>(((theGameView.getWidth()/10)*8) -30)-20) && (x<(((theGameView.getWidth()/10)*8 )-30)+20) ) {
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 1;
            x = (theGameView.getWidth() / 10) * 9 - 40;
            links=true;
            rechts =false;
        }

//Schlange 48-9
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)+30 ) && (x>(((theGameView.getWidth()/10)*7) -30)-20) && (x<(((theGameView.getWidth()/10)*7 )-30)+20) ){
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10)*0;
            x = (theGameView.getWidth() / 10) * 8 - 40;
            links=false;
            rechts =true;
        }


//Schlange 44-22
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)+30 ) && (x>(((theGameView.getWidth()/10)*3) -30)-20) && (x<(((theGameView.getWidth()/10)*3 )-30)+20) ) {
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 2;
            x = (theGameView.getWidth() / 10) * 1 - 40;
            links=false;
            rechts =true;
        }

//Schlange 46-5
        if((y > (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)-30 )&& (y < (theGameView.getHeight()-height-50-(theGameView.getHeight()/10)*4)+30 ) && (x>(((theGameView.getWidth()/10)*5) -30)-20) && (x<(((theGameView.getWidth()/10)*5 )-30)+20) ) {
            y = theGameView.getHeight()-height-50-(theGameView.getHeight() / 10) * 0;
            x = (theGameView.getWidth() / 10) * 4 - 40;
            links=false;
            rechts =true;
        }


/*----------------------------------------------------------------------------------------------------------------------*/
/*--------------------------------------------------------------Game Over-----------------------------------------------*/
/*----------------------------------------------------------------------------------------------------------------------*/




        if (x < -40) {
            links = false;
            rechts = true;
            a = 0;

        }



// links rauf
        if ((x > theGameView.getWidth() - width - xSpeed+theGameView.getWidth()/10) && (xSpeed >= theGameView.getWidth() / 10) && links == false) {
            links = true;
            rechts = false;
            diffx = (theGameView.getWidth() - width - xSpeed) - x;
            a = 0;
            x=theGameView.getWidth()-width;

            if (diffx < -30) {
                y = y - ySpeed;
                ySpeed = 0;
                x = x-30+ diffx + theGameView.getWidth() / 5;
                xSpeed = 0;
            }
        }

        if (links == true && a == 0)
        {
            x = x - xSpeed ;
            xSpeed = 0;
        }

        if (a == 1)
        {
            a = 0;
        } else if (links == false)
        {
            x = x + xSpeed;
            xSpeed = 0;
        }

       if (x - xSpeed < -40 && rechts == false) {
            rechts = true;
            links = false;
            diffx = (-30 - width - xSpeed) - x;
            x=-30;
            xSpeed = 0;

           if (diffx >-200) {
                y = y - ySpeed;
                ySpeed = 0;
                x = x + diffx -width+20+ theGameView.getWidth() / 5;
                xSpeed = 0;
            }

        }
    }
    public void onDraw(Canvas canvas) {
        bounceOff();
        canvas.drawBitmap(bmp, x, y, null);
        if ( x<70 && y < -30) {
            TokenColor.won=true;
        }
    }

    public boolean isTouched(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }


}

