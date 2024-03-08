package persistence;

import model.FlashcardList;
import model.Flashcard;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// code taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads a flashcard list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads flashcard list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FlashcardList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFlashcardList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses flashcard list from JSON object and returns it
    private FlashcardList parseFlashcardList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        FlashcardList fl = new FlashcardList(name);
        addThingies(fl, jsonObject);
        return fl;
    }

    // MODIFIES: fl
    // EFFECTS: parses flashcards from JSON object and adds them to flashcard list
    private void addThingies(FlashcardList fl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("flashcards");
        for (Object json : jsonArray) {
            JSONObject nextFlashcard = (JSONObject) json;
            addThingy(fl, nextFlashcard);
        }
    }

    // MODIFIES: fl
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(FlashcardList fl, JSONObject jsonObject) {
        String front = jsonObject.getString("front");
        String back = jsonObject.getString("back");
        Flashcard f = new Flashcard(front, back);
        fl.addFlashcard(f);
    }
}
