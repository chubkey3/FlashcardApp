package ui;

import model.Flashcard;
import model.FlashcardList;

import java.util.ArrayList;
import java.util.Scanner;

/*
inheritance for console printing
 */

public class FlashcardApp {

    private Scanner input;
    private ArrayList<FlashcardList> flashcardLists;

    public FlashcardApp() {
        init();
        run();
    }

    public void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        flashcardLists = new ArrayList<FlashcardList>();

        // test
        flashcardLists.add(new FlashcardList("hello"));
        flashcardLists.get(0).addFlashcard(new Flashcard("test1_front", "test1_back"));
        flashcardLists.get(0).addFlashcard(new Flashcard("test2_front", "test2_back"));
        flashcardLists.get(0).addFlashcard(new Flashcard("test3_front", "test3_back"));

        // implemntation on loading flashcardlists from data directory
    }

    public void run() {
        String command;
        boolean running = true;

        System.out.println("Welcome to the Flashcard Study Tool!\n");

        while (running) {
            displayOptions();

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                saveFlashcards();
                running = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Thanks for using the Flashcard Study Tool!");

    }

    public void saveFlashcards() {
        // System.out.println(flashcardLists);
    }

    public void displayOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add flashcard list");
        System.out.println("\tf -> add flashcard");
        System.out.println("\tv -> view flashcard list");
        System.out.println("\tt -> test flashcard list");
        System.out.println("\tq -> quit");

    }

    public void processCommand(String command) {
        /*

        t - test (then 1, 2, 3... depending on which flashcard collection to use)
        a - add flashcard collection (enter subject)
        f - add flashcard (then 1, 2, 3... depending on which flashcard collection to add to; then front/back)
        v - view flashcards in flashcard list (then 1, 2, 3... depending on which flashcard collection to be viewed)

        */
        switch (command) {
            case "t":
                test();
                break;
            case "a":
                addFlashcardList();
                break;
            case "f":
                addFlashcard();
                break;
            case "v":
                viewFlashcardLists();
                break;
            default:
                System.out.println(command + "is not a valid command.");
        }

    }

    public void addFlashcardList() {
        System.out.println("Enter name of flashcard list");

        FlashcardList f = new FlashcardList(input.next());

        flashcardLists.add(f);
    }

    public void addFlashcard() {
        for (int i = 0; i < flashcardLists.size(); i++) {
            System.out.println((i + 1) + ". " + flashcardLists.get(i).getName());
        }

        System.out.println("Select a list to add to");

        int listNum = input.nextInt() - 1;

        String front;
        String back;

        System.out.println("Enter front text on flashcard");
        front = input.next();

        System.out.println("Enter back text on flashcard");
        back = input.next();

        flashcardLists.get(listNum).addFlashcard(new Flashcard(front, back));
    }

    public void test() {
        for (int i = 0; i < flashcardLists.size(); i++) {
            System.out.println((i + 1) + ". " + flashcardLists.get(i).getName());
        }

        System.out.println("Select a list to test");

        int listNum = input.nextInt() - 1;

        flashcardLists.get(listNum).test();
    }

    public void viewFlashcardLists() {
        for (int i = 0; i < flashcardLists.size(); i++) {
            System.out.println((i + 1) + ". " + flashcardLists.get(i).getName());
        }

        System.out.println("Select a list to view");

        int listNum = input.nextInt() - 1;

        for (Flashcard f : flashcardLists.get(listNum).getFlashcards()) {
            System.out.println(f.getFront() + " | " + f.getBack());
        }

    }
}