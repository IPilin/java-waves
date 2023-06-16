package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Block extends Rect {
    private float speed;
    private float acceleration;
    private int mass = 1;
    private Block prev;
    private Block next;

    private float k = 0.001f;
    private float maxSpeed = 10f;
    private float restLength = 0;

    public Block(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    public void update() {
        if (mass == -1) {
            return;
        }
        if (speed > 0) {
            if (prev != null && y - prev.y > maxSpeed) {
                return;
            }
            if (next != null && y - next.y > maxSpeed) {
                return;
            }
        }
        if (speed < 0) {
            if (prev != null && prev.y - y > maxSpeed) {
                return;
            }
            if (next != null && next.y - y > maxSpeed) {
                return;
            }
        }
        y += speed;
    }

    public void generateSpeed() {
        float aForce = 0;
        float bForce = 0;
        if (prev != null) {
            float x = y - prev.y;
            aForce = -k * x;
        }
        if (next != null) {
            float x = y - next.y;
            bForce = -k * x;
        }

        float g = 0.000f;
        float gravity = y > 200 ? -g * (y - 200) : g * (200 - y);

        speed += (aForce + bForce + gravity) / mass;
    }
}
