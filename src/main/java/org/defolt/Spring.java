package org.defolt;

import java.awt.*;

public class Spring {
    private float k;
    private float restLength;
    private Block a;
    private Block b;

    public Spring(float k, float restLength, Block a, Block b) {
        this.k = k;
        this.restLength = restLength;
        this.a = a;
        this.b = b;
    }

    public void update() {
        if (a.getMass() == -1 || b.getMass() == -1) {
            return;
        }
        var force = a.getValue() - b.getValue();
        float x = force - restLength;

        force = -k * x;
        a.applyForce(force);
        force *= -1;
        b.applyForce(force);
//        var force = a.getPosition().sub(b.getPosition());
//        float x = -force.mag() / 10 - restLength;
//        force.normalize();
//        force.mult(x);
//        a.applyForce(force);
//        force.mult(-1);
//        b.applyForce(force);
    }

    public void show(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(a.getPosition().getIntX() + a.getWidth() / 2,
                a.getPosition().getIntY() + a.getHeight() / 2,
                b.getPosition().getIntX() + b.getWidth() / 2,
                b.getPosition().getIntY() + b.getHeight() / 2);
    }
}
