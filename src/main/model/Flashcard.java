package model;

import org.json.JSONObject;
import persistence.Writable;

// Flashcard represents a single flashcard with a prompt or question on the front
// and an answer or definition on the back
public class Flashcard implements Writable {

    private final String front;
    private final String back;

    // EFFECTS: initializes new flashcard with a string representing the front
    // and a string representing the back
    public Flashcard(String front, String back) {
        this.front = front;
        this.back = back;
    }

    // EFFECTS: checks if provided answer matches the actual answer on the Flashcard
    // check removes whitespace, converts strings to lowercase and checks if they equal
    public boolean checkAnswer(String answer) {
        if (answer.replaceAll("\\s+","").equalsIgnoreCase(back.replaceAll("\\s+",""))) {
            EventLog.getInstance().logEvent(new Event("Correctly answered '" + answer + "' to " + "'" + front + "'"));
            return true;
        }

        EventLog.getInstance().logEvent(new Event("Incorrectly answered '" + answer + "' to " + "'" + front + "'"));

        return false;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("front", front);
        json.put("back", back);
        return json;
    }
}
