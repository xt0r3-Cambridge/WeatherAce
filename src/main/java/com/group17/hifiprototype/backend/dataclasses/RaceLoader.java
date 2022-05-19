package com.group17.hifiprototype.backend.dataclasses;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages loading race JSONs from a specified directory. Supports loading the JSONs,
 * creating races from them, and grouping them by category.
 */
public class RaceLoader {

    private static RaceLoaderObject loader;

    /**
     * Retrieves JSONs, creates races, creates groups.
     *
     * @param directoryPath Directory with race JSONs
     * @throws UnsupportedOperationException
     * @throws IOException
     */
    public static void initialise(String directoryPath) throws UnsupportedOperationException, IOException {
        if (loader == null) {
            loader = new RaceLoaderObject(directoryPath);
            loader.init();
        } else throw new UnsupportedOperationException("Loader already initialised!");
    }

    /**
     * @param group A race group.
     * @return All races from that group.
     */
    public static Group getGroup(RaceGroups group) {
        return loader.getGroup(group.toString());
    }

    /**
     * @return All loaded races.
     */
    public static Set<Race> getAllRaces() {
        return loader.getAllRaces();
    }

    private static class RaceLoaderObject {
        private final String dirPath;
        private List<File> JSONs;
        private HashMap<Race, File> raceFile;
        private HashMap<String, List<Race>> groupRaces;

        public RaceLoaderObject(String dirPath) {
            this.dirPath = dirPath;
        }

        /**
         * Reads JSON files and converts them to races.
         * Once this is called, you can ask for groups.
         *
         * @throws IOException
         */
        public void init() throws IOException {
            getJSONs();
            loadRaces();
        }

        /**
         * Traverses directory structure to get all JSON files. Assumes these are races.
         *
         * @throws IOException
         */
        private void getJSONs() throws IOException {
            File mainDir = new File(dirPath);
            JSONs = new ArrayList<>();
            Queue<File> dirs = new LinkedList<>();
            dirs.offer(mainDir);
            while (!dirs.isEmpty()) {
                File dir = dirs.poll();
                JSONs.addAll(List.of(dir.listFiles(f -> f.getName().endsWith(".json"))));
                List<File> otherDirs = List.of(dir.listFiles(f -> f.isDirectory()));
                for (File file : otherDirs) {
                    dirs.offer(file);
                }
            }
        }


        /**
         * This exists for debugging.
         *
         * @return File names of all read JSONs.
         */
        public List<String> getFileNames() {
            return JSONs.stream().map(File::getName).collect(Collectors.toList());
        }

        /**
         * Converts all JSONs to races.
         *
         * @throws IOException
         */
        private void loadRaces() throws IOException {

            raceFile = new HashMap<>();
            groupRaces = new HashMap<>();

            for (File file : JSONs) {
                String JSONText = new String(Files.readAllBytes(file.toPath()));
                JSONObject jobj = new JSONObject(JSONText);
                Race race = Race.loadRaceFromJSON(jobj);
                raceFile.put(race, file);
                String group = jobj.getString("Group");
                if (!groupRaces.containsKey(group)) groupRaces.put(group, new ArrayList<>());
                groupRaces.get(group).add(race);
            }
        }

        /**
         * @return Set of all races.
         */
        public Set<Race> getAllRaces() {
            return new HashSet<>(raceFile.keySet());
        }

        /**
         * @param groupName A group name. If this group does not exist, this throws a NoSuchElementException.
         * @return A group with all races that had groupName as their group.
         * @throws NoSuchElementException
         */
        public Group getGroup(String groupName) throws NoSuchElementException {
            if (!groupRaces.containsKey(groupName)) throw new NoSuchElementException("This group does not exist!");
            else {
                Group gr = new Group(groupName);
                gr.addRaces(groupRaces.get(groupName));
                gr.flushOldRaces();
                return gr;
            }
        }

    }


}
