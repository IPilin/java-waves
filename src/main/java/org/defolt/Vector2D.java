package org.defolt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vector2D {
    private float x;
    private float y;
    private float length;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getIntX() {
        return (int)x;
    }

    public int getIntY() {
        return (int)y;
    }

    public Vector2D sub(Vector2D a) {
        return new Vector2D(x - a.getX(), y - a.getY());
    }

    public float mag() {
//        return (float)Math.sqrt(x * x + y * y);
        return (float)y;
    }

    public void normalize() {
//        x = x / mag();
//        y = y / mag();
        y = 1;
    }

    public void mult(float a) {
        //x *= a;
        y *= a;
    }

    public void add(Vector2D a) {
        //x += a.getX();
        y += a.getY();
    }
}
