package com.group17.hifiprototype.backend.dataclasses;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import com.group17.hifiprototype.backend.dataclasses.Race;

/**
 * Group of races. These correspond to the different motorsports.
 */
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

    /**
     * @return List of races, sorted by start time.
     */
    public ArrayList<Race> getRaces() {
        return new ArrayList<>(races);
    }

    /**
     * @param index
     * @return The ith race in time sorted order.
     */
    public Race getRace(int index) {
        return races.get(index);
    }

    /**
     * @param name
     * @return A race with the provided name.
     * @throws NoSuchElementException
     */
    public Race getRace(String name) throws NoSuchElementException {
        for (Race race : races) {
            if (race.getName().equals(name)) {
                return race;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Checks if a provided race is in this group.
     *
     * @param r
     * @return
     */
    public boolean isIn(Race r) {
        return races.contains(r);
    }

    /**
     * Manually add a race to this group. You probably shouldn't do this unless you know what you're doing.
     *
     * @param race
     */
    public void addRace(Race race) {
        races.add(race);
        races.sort(Race::compareTo);
    }

    /**
     * Removes all races whose end time is before the current time.
     */
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


    /**
     * Adds all provided races to this group. You probably shouldn't do this unless you know what you're doing.
     *
     * @param rs  A collection of races.
     * @param <T>
     */
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
    public void loadWeatherData() throws IOException {
        for (Race race : races) {
            race.loadWeatherData();
        }
    }
}
