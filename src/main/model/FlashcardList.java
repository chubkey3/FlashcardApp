package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


// FlashcardList represents a list of Flashcards able to be tested
public class FlashcardList implements java.io.Serializable {

    private String name;
    private ArrayList<Flashcard> flashcards;
    private ArrayList<Flashcard> untestedFlashcards;
    Random rand;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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
    }

    // REQUIRES: untestedFlashcards.size() > 0
    // EFFECTS: returns random flashcard in list
    public Flashcard getRandomFlashcard() {
        int randInt = rand.nextInt(untestedFlashcards.size());

        return untestedFlashcards.remove(randInt);
    }

    // EFFECTS: sets untestedFlashcards to original list of flashcards for future testing
    public void reset() {
        untestedFlashcards = new ArrayList<>(flashcards);
    }

    // MODIFIES: this
    // EFFECTS: prints random flashcards to console until flashcards is empty;
    // flashcards are printed with front (question) first then after user input, back (answer)
    // is printed to screen
    public void test() {
        Flashcard temp;
        int i = 1;
        reset();
        Scanner input = new Scanner(System.in);

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
}
