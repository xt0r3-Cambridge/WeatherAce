package com.group17.hifiprototype.dataclasses;

/**
 * API request granularity
 */
public enum Granularity {
    //15 Minutes
    Minutes,
    //1 Hour
    Hours,
    //1 Day
    Days;

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
