package model;

public class Flashcard {
    private String front;
    private String back;
    private boolean correct;

    public Flashcard(String front, String back) {
        this.front = front;
        this.back = back;
        this.correct = false;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }
}
