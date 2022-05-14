package com.group17.hifiprototype.dataclasses;

public enum Direction {
    N,S,E,W,NE,NW,SE,SW;

    @Override
    public String toString() {
        return this.name();
    }
}
