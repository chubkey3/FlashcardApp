package model;

import persistence.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

// FlashcardList represents a list of Flashcards able to be tested
public class FlashcardList implements Writable {

    private String name;
    private ArrayList<Flashcard> flashcards;
    private ArrayList<Flashcard> untestedFlashcards;
    Random rand;

    // REQUIRES: name to be non-zero length
    // EFFECTS: name for flashcard list is set to name
    // flashcards and untestedFlashcards are initialized to an empty array
    public FlashcardList(String name) {
        this.name = name;
        rand = new Random();
        flashcards = new ArrayList<>();
        untestedFlashcards = new ArrayList<>();
    }

    // REQUIRES: f to be well-formed (not null)
    // MODIFIES: this
    // EFFECTS: adds new Flashcard to FlashcardList
    public void addFlashcard(Flashcard f) {
        flashcards.add(f);
        untestedFlashcards.add(f);

        EventLog.getInstance().logEvent(new Event("Added flashcard to flashcard list"));
    }

    // REQUIRES: untestedFlashcards.size() > 0
    // EFFECTS: returns random flashcard in list
    public Flashcard getRandomFlashcard() {
        int randInt = rand.nextInt(untestedFlashcards.size());

        EventLog.getInstance().logEvent(new Event("Random flashcard chosen"));

        return untestedFlashcards.remove(randInt);
    }

    // EFFECTS: sets untestedFlashcards to original list of flashcards for future testing
    public void reset() {
        untestedFlashcards = new ArrayList<>(flashcards);

        EventLog.getInstance().logEvent(new Event("New flashcard test instance started"));
    }

    public void removeFlashcards(String flashcard) {

        for (int i = 0; i < flashcards.size(); i++) {
            if (flashcards.get(i).getFront().equals(flashcard)) {
                flashcards.remove(i);
            }
        }

        EventLog.getInstance().logEvent(new Event("Removed flashcard from flashcard list"));
    }

    public int getNumberOfFlashcards() {
        return flashcards.size();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    public ArrayList<Flashcard> getUntestedFlashcards() {
        return untestedFlashcards;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("flashcards", thingiesToJson());

        EventLog.getInstance().logEvent(new Event("Flashcard parsed into JSON"));

        return json;
    }

    // EFFECTS: returns flashcards in this flashcard list as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flashcard f : flashcards) {
            jsonArray.put(f.toJson());
        }

        EventLog.getInstance().logEvent(new Event("Flashcards parsed into JSON"));

        return jsonArray;
    }
}
