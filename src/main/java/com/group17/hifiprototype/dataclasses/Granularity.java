package com.group17.hifiprototype.dataclasses;

public enum Granularity {
    Minutes,Hours,Days;

    /**
     @return Time step, in minutes.
     **/
    public int getStep() {
        int output;
        switch(this) {
            case Minutes -> output = 15;
            case Hours -> output = 60;
            case Days -> output = 60*24;
            default -> output = 60*24;
        }
        return output;
    }
}
