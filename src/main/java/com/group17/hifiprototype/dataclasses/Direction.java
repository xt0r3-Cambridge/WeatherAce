package com.group17.hifiprototype.dataclasses;

public enum Direction {
    // World directions in clockwise order starting from North
    N,NE,E,SE,S,SW,W,NW;

    @Override
    public String toString() {
        return this.name();
    }
}
