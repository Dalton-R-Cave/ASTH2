package cave.dalton.thedefender.Shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by dalton on 10/31/16.
 */

public class Circle extends Shape {

    float radius;

    public Circle(float x, float y, int velocity, int color, float radius){
        super(x, y, velocity, color);
        this.radius = radius;
    }

    public void render(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);
    }

    public boolean collides(Shape other){
        return (Math.abs(other.x - x) < radius);
    }
}
