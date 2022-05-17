package com.group17.hifiprototype.dataclasses;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Group {

    private final String name;
    private ArrayList<Race> races;

    public Group(String name) {
        this.name = name;
        races = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Race> getRaces() {
        return new ArrayList<>(races);
    }

    public boolean isIn(Race r) {
        return races.contains(r);
    }

    public void addRace(Race race) {
        races.add(race);
        races.sort(Race::compareTo);
    }

    public void flushOldRaces() {
        ArrayList<Race> goodRaces = new ArrayList<>();
        for (Race race : races) {
            if (race.getEndTime().isAfter(ZonedDateTime.now())) {
                goodRaces.add(race);
            }
        }
        goodRaces.sort(Race::compareTo);
        races = goodRaces;
    }

    public <T extends Collection<Race>> void addRaces(T rs) {
        for (Race race : rs) {
            races.add(race);
        }
        races.sort(Race::compareTo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Group: ").append(name);
        sb.append(", Races: ");
        sb.append(races.stream().map(Race::getName).collect(Collectors.toList()));
        return sb.toString();
    }

    /**
     * Loads weather data for all races in the group
     */
    public void loadWeatherData() {
        for (Race race : races) {
            System.out.println("Loading data for race: "+race.getName());
            race.loadWeatherData();
            System.out.println("Loading complete!");
        }
    }
}
