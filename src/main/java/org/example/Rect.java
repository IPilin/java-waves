package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rect {
    protected float x;
    protected float y;
    protected int width;
    protected int height;

    public int getIntX() {
        return (int)x;
    }

    public int getIntY() {
        return (int)y;
    }
}
