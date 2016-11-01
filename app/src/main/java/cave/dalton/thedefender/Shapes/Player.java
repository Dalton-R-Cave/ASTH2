package cave.dalton.thedefender.Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by dalton on 10/31/16.
 */

public class Player extends Square{
    private static final String TAG = Player.class.getSimpleName();

    ArrayList<Circle> projectiles = new ArrayList();
    ArrayList<Circle> toRemove = new ArrayList();

    public Player(float x, float y, int velocity, int color, float height, float width){
        super(x, y, velocity, color, height, width);
    }

    public ArrayList getProjectiles(){
        return projectiles;
    }

    public void handleTouch(float eX, float eY){
        Circle projectile = new Circle(x, y, 30, Color.GREEN, 30);
        projectiles.add(projectile);
        projectile.createLine(eX, eY);
    }

    public void update(ArrayList<Player> others){
        super.update();
        if(!projectiles.isEmpty()){
            for(Circle projectile: projectiles){
                projectile.update();
                if(projectile.offScreen()){
                    toRemove.add(projectile);
                }
                for(Player player: others){
                    if(projectile.collides(player)){
                        toRemove.add(projectile);
                        player.setHit(true);
                    }
                }

            }
            projectiles.removeAll(toRemove);
            toRemove.clear();
        }
        for(Player player: others) {
            if (collides(player)) {
                this.setHit(true);
                player.setHit(true);
            }
        }
    }

    public void render(Canvas canvas){
        super.render(canvas);
        for(Circle projectile: projectiles){
            projectile.render(canvas);
        }
    }
}
