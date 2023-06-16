package org.defolt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rect {
    protected Vector2D position;
    protected int width;
    protected int height;
}
