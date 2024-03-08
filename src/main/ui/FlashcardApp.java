package ui;

import model.Flashcard;
import model.FlashcardList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.*;
import java.util.Scanner;


// FlashcardApp class represents the flashcard application
public class FlashcardApp {

    private static final String JSON_STORE = "./data/flashcards.json";

    private Scanner input;
    private FlashcardList flashcardList;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    // EFFECTS: runs flashcard app
    public FlashcardApp() {
        init();
        run();
    }

    // MODIFIES: this
    // EFFECTS: initializes app
    public void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        flashcardList = new FlashcardList("Sample Flashcard List");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
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

    // EFFECTS: saves flashcard list to file
    public void saveFlashcards() {
        try {
            jsonWriter.open();
            jsonWriter.write(flashcardList);
            jsonWriter.close();
            System.out.println("Saved " + flashcardList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads flashcard list to file
    public void loadFlashcards() {
        try {
            flashcardList = jsonReader.read();
            System.out.println("Loaded " + flashcardList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: displays command line options
    public void displayOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add flashcard");
        System.out.println("\tv -> view flashcard list");
        System.out.println("\tt -> test flashcard list");
        System.out.println("\ts -> save flashcard list");
        System.out.println("\tl -> load flashcard list");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {

        switch (command) {
            case "t":
                test();
                break;
            case "a":
                addFlashcard();
                break;
            case "v":
                viewFlashcardLists();
                break;
            case "s":
                saveFlashcards();
                break;
            case "l":
                loadFlashcards();
                break;
            default:
                System.out.println(command + "is not a valid command.");
        }

    }

    // MODIFIES: this
    // EFFECTS: adds flashcard to specific flashcard list
    public void addFlashcard() {

        String front;
        String back;

        System.out.println("Enter front text on flashcard");
        front = input.next();

        System.out.println("Enter back text on flashcard");
        back = input.next();

        flashcardList.addFlashcard(new Flashcard(front, back));
    }

    // EFFECTS: prints random flashcards to console until flashcards is empty;
    // flashcards are printed with front (question) first then after user input, back (answer)
    // is printed to screen
    public void test() {

        Flashcard temp;
        int questionNum = 1;
        Scanner input = new Scanner(System.in);

        flashcardList.reset();

        System.out.println("TEST FOR " + flashcardList.getName().toUpperCase() + "\n");

        while (flashcardList.getUntestedFlashcards().size() > 0) {
            temp = flashcardList.getRandomFlashcard();

            System.out.println(ANSI_CYAN + "Q" + questionNum + ". " + temp.getFront() + ANSI_RESET);

            System.out.print("Press ENTER to view answer");

            input.nextLine();

            System.out.println(ANSI_RED + "A" + questionNum + ". " + temp.getBack() + "\n" + ANSI_RESET);

            questionNum++;
        }

        System.out.println("Press ENTER to finish test");

        input.nextLine();
    }

    // EFFECTS: prints flashcards of specific flashcard list to console
    public void viewFlashcardLists() {

        for (Flashcard f : flashcardList.getFlashcards()) {
            System.out.println(f.getFront() + " | " + f.getBack());
        }

    }
}
