package com.group17.hifiprototype.dataclasses;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Reads all JSONs from a given directory (assumes they are races).
 */
public class RaceLoader {
    private final String dirPath;
    private List<File> JSONs;
    private HashMap<Race, File> raceFile;
    private HashMap<String, List<Race>> groupRaces;

    public RaceLoader(String dirPath) {
        this.dirPath = dirPath;
    }

    /**
     * Reads JSON files and converts them to races.
     * Once this is called, you can ask for groups.
     * @throws IOException
     */
    public void init() throws IOException {
        getJSONs();
        loadRaces();
    }

    /**
     * Gets list of JSON files from directory.
     * @throws IOException
     */
    public void getJSONs() throws IOException {
        File dir = new File(dirPath);
        JSONs = List.of(dir.listFiles(f -> f.getName().endsWith(".json")));
    }

    /**
     * @return File names of all read JSONs.
     */
    public List<String> getFileNames() {
        return JSONs.stream().map(File::getName).collect(Collectors.toList());
    }

    /**
     * Converts all JSONs to races.
     * @throws IOException
     */
    public void loadRaces() throws IOException {

        raceFile = new HashMap<>();
        groupRaces = new HashMap<>();

        for (File file : JSONs) {
            String JSONText = new String(Files.readAllBytes(file.toPath()));
            JSONObject jobj = new JSONObject(JSONText);
            Race race = Race.loadRaceFromJSON(jobj);
            raceFile.put(race, file);
            String group = jobj.getString("Group");
            groupRaces.getOrDefault(group, new ArrayList<>()).add(race);
        }
    }

    /**
     * @return Set of all races.
     */
    public Set<Race> getAllRaces() {
        return new HashSet<>(raceFile.keySet());
    }

    /**
     *
     * @param groupName A group name. If this group does not exist, this throws a NoSuchElementException.
     * @return A group with all races that had groupName as their group.
     * @throws NoSuchElementException
     */
    public Group getGroup(String groupName) throws NoSuchElementException{
        if (!groupRaces.containsKey(groupName)) throw new NoSuchElementException("This group does not exist!");
        else {
            Group gr = new Group(groupName);
            gr.addRaces(groupRaces.get(groupName));
            gr.flushOldRaces();
            return gr;
        }
    }


}
