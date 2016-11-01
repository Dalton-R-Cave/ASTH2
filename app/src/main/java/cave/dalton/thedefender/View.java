package cave.dalton.thedefender;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import cave.dalton.thedefender.Shapes.Player;
import cave.dalton.thedefender.Shapes.Square;

/**
 * Created by dalton on 10/22/16.
 */

public class View extends SurfaceView implements SurfaceHolder.Callback{

    private static final String TAG = View.class.getSimpleName();

    private static final int ENEMY_FREQUENCY = 50;
    private static long time;

    private MainThread mainThread;
    Player player = new Player(100, 100, 0, Color.YELLOW, 100, 100);

    ArrayList<Player> enemies = new ArrayList<>();
//    Player enemy = new Player(500, 500, 15, Color.RED, 100, 100);

    public View(Context context){
        super(context);

        getHolder().addCallback(this);
        mainThread = new MainThread(getHolder(), this);
//        enemy.moveTowards(player);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mainThread.setRunning(true);
        mainThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //do nothing
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface is being destroyed");

        boolean retry = true;
        while(retry){
            try{
                mainThread.join();
                retry = false;
            }
            catch(InterruptedException e){
                //retry shutting down the thread
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float eX = event.getX();
        float eY =  event.getY();
//        defender.handleTouch(eX, eY);
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            player.handleTouch(eX, eY);
        }
        return true;
    }

    public void update(long change){
        time += change;
        if(time > ENEMY_FREQUENCY){
            time = 0;
            //spawn enemy
            Player enemy = new Player(500, 500, 15, Color.RED, 100, 100);
            enemy.moveTowards(player);
            enemies.add(enemy);
        }


        player.update(enemies);

        for(Player enemy: enemies){
            enemy.update();
            if(enemy.isHit()){
                enemies.remove(enemy);
            }
        }

    }

    public void render(Canvas canvas){

        //Draw the background
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        if(paint != null){
            canvas.drawPaint(paint);
        }
        player.render(canvas);

        for(Player enemy: enemies){
            enemy.render(canvas);
        }
//        defender.render(canvas);
//        if(enemy != null) {
//            enemy.render(canvas);
//        }
    }
}
