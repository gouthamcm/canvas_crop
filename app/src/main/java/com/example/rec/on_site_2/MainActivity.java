package com.example.rec.on_site_2;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity {
Paint mPaint;
private boolean touched;
private int xUp;
private int xDown;
private int yUp;
private int width;
private  int height;
private int yDown;
private int count=0;
    Bitmap bitmap;
 static String TAG="saaji";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView view=new MyView(this);
        setContentView(view);



    }
    public class MyView extends View{

        public MyView(Context context) {
            super(context);


        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
           if(count==0){  bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.rain);
            canvas.drawBitmap(bitmap,0,0,new Paint());
            mPaint = new Paint();
            mPaint.setColor(Color.BLACK);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawColor(Color.TRANSPARENT);}
            if(touched) {
                canvas.drawRect(xDown, yDown, xUp, yUp, mPaint);

 width=(xUp-xDown)>0?(xUp-xDown):1;
 height =(yUp-yDown)>0?(yUp-yDown):1;
 Log.d(TAG,String.valueOf(height));
 Log.d(TAG,String.valueOf(width));
Log.d(TAG,"xup =" +String.valueOf(xUp));
Log.d(TAG,"yup= "+ String.valueOf(yUp));
                Log.d(TAG,"xdo" +
                        "wm =" +String.valueOf(xDown));
                Log.d(TAG,"ydown= "+ String.valueOf(yDown));

                Bitmap newBitmap=Bitmap.createBitmap(bitmap,xDown,yDown,width,height);
                canvas.drawBitmap(newBitmap,xDown,yDown,new Paint());
            }




        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    xDown = (int)event.getX();
                    yDown = (int)event.getY();

                    xUp = 0;
                    yUp = 0;
                    break;

                case MotionEvent.ACTION_UP:
                    xUp = (int) event.getX();
                    yUp =(int) event.getY();
                    touched = true;
                    count++;
                    this.invalidate();


                    break;
            }

            return true;
        }
    }



}
