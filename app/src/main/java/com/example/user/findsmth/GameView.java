package com.example.user.findsmth;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


import android.os.SystemClock;
import android.util.AttributeSet;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;


public class GameView extends SurfaceView {
    Paint paint;
    GridGame gridGame;
    private int w;
    private int h;
    private GameLoopThread gameLoopThread;
    private SurfaceHolder holder;
    public boolean mapView;
    private int x;
    private int y;
    private boolean init= true;
    private long lastUpdate = 0;
    private boolean activeOnTouch=false;
    private boolean activeLaunch=false;
    public int launchFromX;
    public int launchFromY;
    private boolean launching=false;
    private boolean moving=false;
    private int currentFruits;
    private ArrayList<Fruits> listFruit;
    private Game gameActivity;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mapView=true;
        currentFruits=0;
        paint=new Paint();
        this.setBackgroundColor(Color.WHITE);
        gridGame = new GridGame();
        gameActivity = (Game)getContext();
        gameLoopThread = new GameLoopThread(this,context);
        holder = getHolder();

        listFruit = new ArrayList<>();
        listFruit.add(new Fruits(3,"Banane"));
        listFruit.add(new Fruits(4, "Brocoli"));
        listFruit.add(new Fruits(5, "Carotte"));
        listFruit.add(new Fruits(6,"Chou"));
        listFruit.add(new Fruits(7,"Citron"));
        listFruit.add(new Fruits(8,"Raisin"));
        Random rdm= new Random();
        int nbChoisie= rdm.nextInt(listFruit.size());
        //int nbChoisie=3;
        currentFruits = nbChoisie;





        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

    }
    @Override
    protected void onMeasure(int w, int h){
        int size =Math.min(w, h);
        setMeasuredDimension(size, size);
        this.w=getMeasuredWidth();
        this.h=getMeasuredHeight();

    }

    @Override
    public void onDraw(Canvas canvas){


        Drawable blanc = getResources().getDrawable(R.drawable.blank);
        Drawable blank = getResources().getDrawable(R.drawable.blanc);
        Drawable cible = getResources().getDrawable(R.drawable.cible);
        Drawable cercleBlanc = getResources().getDrawable(R.drawable.cercleblanc);
        Drawable banane = getResources().getDrawable(R.drawable.banane);
        Drawable brocoli = getResources().getDrawable(R.drawable.brocoli);
        Drawable carotte = getResources().getDrawable(R.drawable.carotte);
        Drawable chou = getResources().getDrawable(R.drawable.chou);
        Drawable citron = getResources().getDrawable(R.drawable.citron);
        Drawable raisin = getResources().getDrawable(R.drawable.grappe_de_raisin);



        int largeurCase =Math.min( w / 16,h/16);
        int largeurCaseInNewFrame =Math.min( w/4,h/4);
        int caseX=x/largeurCase;
        int caseY=y/largeurCase;
        int decalageCaseX=x%largeurCase*largeurCaseInNewFrame/largeurCase;
        int decalageCaseY=y%largeurCase*largeurCaseInNewFrame/largeurCase;

        paint.setColor(Color.BLACK);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int x1 =largeurCaseInNewFrame * i -decalageCaseX;
                int x2= largeurCaseInNewFrame * (i + 1)-decalageCaseX;
                int y1= largeurCaseInNewFrame * j -decalageCaseY ;
                int y2= largeurCaseInNewFrame * (j + 1)-decalageCaseY;

                if (!(caseX - 2 + i < 0 || caseX - 2 + i >= gridGame.getX()) && !(caseY - 2 + j < 0 || caseY - 2 + j >= gridGame.getY())) {


                    if ( gridGame.getGrid()[caseX - 2 + i][caseY - 2 + j] == 0){
                        blank.setBounds(y1, x1, y2, x2);
                        blank.draw(canvas);
                    }
                    else if(gridGame.getGrid()[caseX - 2 + i][caseY - 2 + j] == 1) {
                        blanc.setBounds(y1, x1, y2, x2);
                        blanc.draw(canvas);
                    }
                    else if(gridGame.getGrid()[caseX - 2 + i][caseY - 2 + j] == 2) {
                        cercleBlanc.setBounds(y1, x1, y2, x2);
                        cercleBlanc.draw(canvas);
                    }
                    else if(gridGame.getGrid()[caseX - 2 + i][caseY - 2 + j] == 3) {
                        banane.setBounds(y1, x1, y2, x2);
                        banane.draw(canvas);

                    }
                    else if(gridGame.getGrid()[caseX - 2 + i][caseY - 2 + j] == 4) {
                        brocoli.setBounds(y1, x1, y2, x2);
                        brocoli.draw(canvas);

                    }
                    else if(gridGame.getGrid()[caseX - 2 + i][caseY - 2 + j] == 5) {
                        carotte.setBounds(y1, x1, y2, x2);
                        carotte.draw(canvas);

                    }
                    else if(gridGame.getGrid()[caseX - 2 + i][caseY - 2 + j] == 6) {
                        chou.setBounds(y1, x1, y2, x2);
                        chou.draw(canvas);

                    }
                    else if(gridGame.getGrid()[caseX - 2 + i][caseY - 2 + j] == 7) {
                        citron.setBounds(y1, x1, y2, x2);
                        citron.draw(canvas);

                    }
                    else if(gridGame.getGrid()[caseX - 2 + i][caseY - 2 + j] == 8) {
                        raisin.setBounds(y1, x1, y2, x2);
                        raisin.draw(canvas);
                    }
                }
                else{
                    blank.setBounds(y1, x1, y2, x2);
                    blank.draw(canvas);
                }
            }

            cible.setBounds(w / 2 - largeurCase, h / 2 - largeurCase, (w / 2) + largeurCase, (h / 2) + largeurCase);
            cible.draw(canvas);
            if (launching) {
                paint.setStrokeWidth(16);
                canvas.drawLine(h / 2, w / 2, launchFromX, launchFromY, paint);
            }
        }
        gameActivity.wordToFind.setText(listFruit.get(currentFruits).getName());
    }


    public void changeXY(float y , float x){

        y=y*2;
        x=x*2;
        int largeurCase =Math.min( w / 16,h/16);
        int newX=this.x;
        int newY=this.y;
        if (x>2||x<-2){
            newX+=x;
        }

        if (y>2|| y<-2){
            newY+=-y;
        }
        int caseX=newX/largeurCase;
        int caseY=newY/largeurCase;
        int caseX0=this.x/largeurCase;
        int caseY0=this.y/largeurCase;

        if (newX>=0 && newY>=0&&!(caseX < 0 || caseX >= gridGame.getX()) && !(caseY  < 0 || caseY >= gridGame.getY()) && !(gridGame.getGrid()[caseX][caseY] == 0)) {
            this.x=newX;
            this.y=newY;
        }
        else if (newY>=0 &&!(caseX0 < 0 || caseX0 >= gridGame.getX()) && !(caseY  < 0 || caseY >= gridGame.getY()) && !(gridGame.getGrid()[caseX0][caseY] == 0)) {
            this.y=newY;
        }
        else if (newX>=0 &&!(caseX < 0 || caseX >= gridGame.getX()) && !(caseY0  < 0 || caseY0 >= gridGame.getY()) &&!( gridGame.getGrid()[caseX][caseY0] == 0)) {
            this.x=newX;
        }

        //System.out.println(listFruit.get(currentFruits).getName());
        if(gridGame.getGrid()[caseX][caseY]== listFruit.get(currentFruits).getValue()){
            this.x=30;
            this.y=30;
            Random rdm= new Random();
            int nbChoisie= rdm.nextInt(listFruit.size());
            currentFruits = nbChoisie;
            gameActivity.wordToFind.setText(listFruit.get(currentFruits).getName());
            gameActivity.chronometerSave.setText(gameActivity.chronometer.getText());
            gameActivity.chronometer.setBase(SystemClock.elapsedRealtime());
        }

    }

    public void changeXYOnClick(double y,double x){
        int newX=(int) ((x - w/2)/16)+this.x;
        int newY=(int) ((y - h/2)/16)+this.y;
        int largeurCase =Math.min( w / 16,h/16);

        int caseX=newX/largeurCase;
        int caseY=newY/largeurCase;
        int caseX0=this.x/largeurCase;
        int caseY0=this.y/largeurCase;
        if (newX>=0 && newY>=0&&!(caseX < 0 || caseX >= gridGame.getX()) && !(caseY  < 0 || caseY >= gridGame.getY()) && !(gridGame.getGrid()[caseX][caseY] == 0)) {
            this.x=newX;
            this.y=newY;
        }
        else if (newY>=0 &&!(caseX0 < 0 || caseX0 >= gridGame.getX()) && !(caseY  < 0 || caseY >= gridGame.getY()) && !(gridGame.getGrid()[caseX0][caseY] == 0)) {
            this.y=newY;
        }
        else if (newX>=0 &&!(caseX < 0 || caseX >= gridGame.getX()) && !(caseY0  < 0 || caseY0 >= gridGame.getY()) &&!( gridGame.getGrid()[caseX][caseY0] == 0)) {
            this.x=newX;
        }
        //System.out.println(listFruit.get(currentFruits).getName());
        if(gridGame.getGrid()[caseX][caseY]== listFruit.get(currentFruits).getValue()){
            Random rdm= new Random();
            int nbChoisie= rdm.nextInt(listFruit.size());
            currentFruits = nbChoisie;
            gameActivity.wordToFind.setText(listFruit.get(currentFruits).getName());
            this.x = 30;
            this.y = 30;
            gameActivity.chronometerSave.setText(gameActivity.chronometer.getText());
            gameActivity.chronometer.setBase(SystemClock.elapsedRealtime());
        }

    }

    public void setMapView(){
        mapView=!mapView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (activeOnTouch && ! mapView) {

                    float x = event.getX();
                    float y = event.getY();
                    changeXYOnClick(x, y);
                }
                else if (activeLaunch && ! mapView && !moving) {
                    launching=true;
                    launchFromX=(int) event.getX();
                    launchFromY=(int) event.getY();
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                float x = event.getX();
                float y = event.getY();
                if (activeOnTouch && ! mapView) {
                    changeXYOnClick(x, y);
                }
            }
            case MotionEvent.ACTION_MOVE: {
                if (activeOnTouch && ! mapView) {

                    long curTime = System.currentTimeMillis();

                    if ((curTime - lastUpdate) > 50) {
                        long diffTime = (curTime - lastUpdate);
                        lastUpdate = curTime;
                        float x = event.getX();
                        float y = event.getY();
                        changeXYOnClick(x, y);
                    }
                }
                else if(activeLaunch&& !mapView&& !moving){
                    launchFromX=(int) event.getX();
                    launchFromY=(int) event.getY();
                }
            }
        }

        return true;
    }

    public void activeOnTouche() {
        activeOnTouch=true;
    }

}
