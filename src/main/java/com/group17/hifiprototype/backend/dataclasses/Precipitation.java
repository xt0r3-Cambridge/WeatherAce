package com.group17.hifiprototype.backend.dataclasses;

/**
 * The different kinds of precipitation the weather API gives us.
 */
public enum Precipitation {
    None("☀"),
    Rain("\uD83C\uDF27"),
    Snow("❄"),
    FreezingRain("\uD83C\uDF28"),
    Sleet("\uD83C\uDF28");

    private final String symbol;

    Precipitation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return this.symbol;
    }
}
