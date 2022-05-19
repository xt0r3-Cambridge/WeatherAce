package com.group17.hifiprototype.backend.dataclasses;


/**
 * Supported race groups.
 */
public enum RaceGroups {
    F1, NASCAR, WEC, Local, Favourites;

    @Override
    public String toString() {
        return this.name();
    }
}
