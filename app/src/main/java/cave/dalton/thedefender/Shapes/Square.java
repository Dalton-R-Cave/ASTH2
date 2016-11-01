package cave.dalton.thedefender.Shapes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by dalton on 10/31/16.
 */

public class Square extends Shape {

    private float height, width;

    public Square(float x, float y, int velocity, int color, float height, float width){
        super(x, y, velocity, color);
        this.height = height;
        this.width = width;
    }

    public void render(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public boolean collides(Shape other){
        if((other.x > x) && (other.x < x + width)){
            if((other.y > y) && (other.y < y + height)){
                return true;
            }
        }
        return false;
    }
}
