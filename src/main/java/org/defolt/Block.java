package org.defolt;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Block extends Rect {
    private Vector2D velocity = new Vector2D(0, 0);
    private int mass = 1;

    private Block prev;
    private Block next;

    private float value = 0;
    private float sum = 0;
    private float k = 0.1f;
    private float restLength = 1;
    private float zeroY = 400;
    private float g = 0.01f;

    public Block(Vector2D position, int width, int height) {
        super(position, width, height);
    }

    public void update() {
        if (mass == -1) {
            return;
        }

//        if (value < 5 && Math.abs(velocity.getY()) < 0.05f) {
//            value = zeroY;
//        } else {
//            value += velocity.getY();
//        }

        value += velocity.getY();

//        if (value < 0f) {
//            value = 0;
//            velocity = new Vector2D(0,0);
//        }

        value *= 0.999;
    }

    public void applyForce(float force) {
        velocity.setY(velocity.getY() + force);
    }

    public void show(Graphics g) {
        value = dist(value);
        g.setColor(Color.getHSBColor(0, 0, dist(value)));
        g.fillRect(position.getIntX(), position.getIntY(), width, height);
    }

    private float dist(float a) {
        //a = Math.abs(a);
        a = Math.max(a, 0f);
        a = Math.min(a, 1f);
        return a;
    }
}
