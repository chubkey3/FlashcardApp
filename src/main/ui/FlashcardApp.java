package ui;

import model.Event;
import model.EventLog;
import model.Flashcard;
import model.FlashcardList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


// FlashcardApp class represents the flashcard application
public class FlashcardApp extends JFrame {

    private static final String JSON_STORE = "./data/flashcards.json";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static final int VIEW_TAB_INDEX = 0;
    public static final int ADD_TAB_INDEX = 1;
    public static final int REMOVE_TAB_INDEX = 2;
    public static final int TEST_TAB_INDEX = 3;
    public static final int SAVE_AND_LOAD_TAB_INDEX = 4;

    private Scanner input;
    private FlashcardList flashcardList;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private ViewTab viewTab;
    private AddTab addTab;
    private TestTab testTab;
    private SaveAndLoadTab saveAndLoadTab;
    private RemoveTab removeTab;

    private JTabbedPane sidebar;

    // EFFECTS: runs flashcard app
    public FlashcardApp() {
        super("Flashcard App");

        flashcardList = new FlashcardList("Sample Flashcard List");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                for (Event ev : EventLog.getInstance()) {
                    System.out.println(ev);
                }
            }
        });
    }

    // EFFECTS: returns flashcards in flashcardList
    public ArrayList<Flashcard> getFlashcards() {
        if (flashcardList == null) {
            return new ArrayList<Flashcard>();
        }

        return flashcardList.getFlashcards();
    }

    // EFFECTS: resets test flashcards
    public void resetFlashcards() {
        flashcardList.reset();
    }

    // EFFECTS: initializes tabs in GUI
    // MODIFIES: this
    public void loadTabs() {
        viewTab = new ViewTab(this);
        addTab = new AddTab(this);
        testTab = new TestTab(this);
        saveAndLoadTab = new SaveAndLoadTab(this);
        removeTab = new RemoveTab(this);

        sidebar.add(viewTab, VIEW_TAB_INDEX);
        sidebar.setTitleAt(VIEW_TAB_INDEX, "View");
        sidebar.add(addTab, ADD_TAB_INDEX);
        sidebar.setTitleAt(ADD_TAB_INDEX, "Add");
        sidebar.add(removeTab, REMOVE_TAB_INDEX);
        sidebar.setTitleAt(REMOVE_TAB_INDEX, "Remove");
        sidebar.add(testTab, TEST_TAB_INDEX);
        sidebar.setTitleAt(TEST_TAB_INDEX, "Test");
        sidebar.add(saveAndLoadTab, SAVE_AND_LOAD_TAB_INDEX);
        sidebar.setTitleAt(SAVE_AND_LOAD_TAB_INDEX, "Save/Load");
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
            update();
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
                //addFlashcard();
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
    public void addFlashcard(String front, String back) {

        flashcardList.addFlashcard(new Flashcard(front, back));

        update();
    }

    public void removeFlashcard(String flashcard) {
        flashcardList.removeFlashcards(flashcard);

        update();
    }

    public void update() {
        viewTab.updateList();
        removeTab.updateList();
    }

    // EFFECTS: returns random flashcard from flashcardList
    public Flashcard getRandomFlashcard() {
        return flashcardList.getRandomFlashcard();
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
