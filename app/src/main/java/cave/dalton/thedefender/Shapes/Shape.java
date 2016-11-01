package cave.dalton.thedefender.Shapes;

import android.graphics.Canvas;

/**
 * Created by dalton on 10/31/16.
 */

public abstract class Shape {

    float x, y;
    int color;
    int velocity;
    Float destX, destY;
    float slope, yInt;
    boolean hit;

    public Shape(float x, float y, int velocity, int color){
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.color = color;
        hit = false;
    }

    public boolean isHit(){
        return hit;
    }

    public void setHit(boolean hit){
        this.hit = hit;
    }

    public int getVelocity(){
        return velocity;
    }

    public void setVelocity(int velocity){
        this.velocity = velocity;
    }

    public float getSlope(){
        return slope;
    }

    public float getYInt(){
        return yInt;
    }

    public void setSlope(float slope){
        this.slope = slope;
    }

    public void setyInt(float yInt){
        this.yInt = yInt;
    }

    public float getDestX(){
        return destX;
    }

    public float getDestY(){
        return destY;
    }

    public void setDestX(float destX){
        this.destX = destX;
    }

    public void setDestY(float destY){
        this.destY = destY;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public int getColor(){
        return color;
    }

    public void setColor(int color){
        this.color = color;
    }

    abstract void render(Canvas canvas);

    public void createLine(float destX, float destY){
        this.destX = destX;
        this.destY = destY;
        slope = (y - destY)/ (x - destX);
        yInt = (-1 * slope * x) + y;
    }

    public void update(){
        if(destX != null && !isHit()){
            x = x + velocity;
            y = (slope * x) + yInt;
        }
    }

    public void moveTowards(Shape other){
        createLine(other.x, other.y);
        if(toLeft(other)){
            velocity *= -1;
        }
    }

    abstract boolean collides(Shape other);

    public boolean toLeft(Shape other){
        return (x - other.x > 0);
    }

    public boolean offScreen(){
        return (x > 2000 || y > 2000);
    }
}
