package model;

import java.util.ArrayList;
import java.util.Random;

public class FlashcardList {

    private String name;
    private int numberOfFlashcards;
    private ArrayList<Flashcard> flashcards;
    private ArrayList<Flashcard> untestedFlashcards; //idk if this is the play tbh
    private ArrayList<Flashcard> testedFlashcards;
    Random rand;

    public FlashcardList(String name) {
        this.name = name;
        numberOfFlashcards = 0;
        rand = new Random();
        flashcards = new ArrayList<Flashcard>();
        untestedFlashcards = new ArrayList<Flashcard>();
        testedFlashcards = new ArrayList<Flashcard>();
    }

    public void addFlashcard(Flashcard f) {
        flashcards.add(f);
    }

    public Flashcard getRandomFlashcard() {
        int randInt = rand.nextInt(untestedFlashcards.size());

        Flashcard f = untestedFlashcards.remove(randInt);

        testedFlashcards.add(f);

        return f;
    }
    /*
    public ArrayList<Flashcard> getFlashcards() {
        return flashcards
    }
    */

    public void reset() {

    }


    public int getNumberOfFlashcards() {
        return numberOfFlashcards;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    //?
    public void test() {
       //return score?
        //reset();
        //untestedFlashcards = flashcards.copy();
    }
}
