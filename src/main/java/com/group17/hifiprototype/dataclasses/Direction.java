package com.group17.hifiprototype.dataclasses;

/**
 * Cardinal directions.
 */
public enum Direction {
    // World directions in clockwise order starting from North
    N("↑"),
    NE("↗"),
    E("→"),
    SE("↘"),
    S("↓"),
    SW("↙"),
    W("←"),
    NW("↖");

    private final String arrowSymbol;

    Direction(String arrowSymbol) {
        this.arrowSymbol = arrowSymbol;
    }

    @Override
    public String toString() {
        return this.arrowSymbol;
    }
}
