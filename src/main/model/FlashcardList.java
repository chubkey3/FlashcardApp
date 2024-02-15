package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FlashcardList implements java.io.Serializable {

    private String name;
    private ArrayList<Flashcard> flashcards;
    private ArrayList<Flashcard> untestedFlashcards;
    Random rand;
    transient Scanner input;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public FlashcardList(String name) {
        this.name = name;
        rand = new Random();
        flashcards = new ArrayList<Flashcard>();
        untestedFlashcards = new ArrayList<Flashcard>();
        input = new Scanner(System.in);
    }

    public void addFlashcard(Flashcard f) {
        flashcards.add(f);
    }

    public Flashcard getRandomFlashcard() {
        int randInt = rand.nextInt(untestedFlashcards.size());

        Flashcard f = untestedFlashcards.remove(randInt);

        return f;
    }

    /*
    public ArrayList<Flashcard> getFlashcards() {
        return flashcards
    }
    */

    public void reset() {
        untestedFlashcards = new ArrayList<>(flashcards);
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

    //?
    public void test() {
       //return score?
        Flashcard temp;
        int i = 1;
        reset();

        System.out.println("TEST FOR " + name.toUpperCase() + "\n");

        while (untestedFlashcards.size() > 0) {
            temp = getRandomFlashcard();

            System.out.println(ANSI_CYAN + "Q" + i + ". " + temp.getFront() + ANSI_RESET);

            System.out.print("Press ENTER to view answer");

            input.nextLine();

            System.out.println(ANSI_RED + "A" + i + ". " + temp.getBack() + "\n" + ANSI_RESET);

            i++;
        }

        System.out.println("Press ENTER to finish test");

        input.nextLine();
    }
}
