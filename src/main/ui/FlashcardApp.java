package ui;

import model.Flashcard;
import model.FlashcardList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


// FlashcardApp class represents the flashcard application
public class FlashcardApp {

    private Scanner input;
    private ArrayList<FlashcardList> flashcardLists;
    private FileOutputStream fileOutput;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // EFFECTS: runs flashcard app
    public FlashcardApp() {
        init();
        run();
    }

    // MODIFIES: this
    // EFFECTS: initializes console scanner and reads flashcards from serialized file
    public void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        flashcardLists = new ArrayList<>();

        readFlashcards();
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

    // EFFECTS: saves serialized flashcards field to file
    public void saveFlashcards() {
        try {
            fileOutput = new FileOutputStream("data/test.ser");
            ObjectOutputStream  objectOutputStream = new ObjectOutputStream(fileOutput);

            objectOutputStream.writeObject(flashcardLists);

            objectOutputStream.close();

            fileOutput.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO ERROR!");
        }
    }

    // MODIFIES: this
    // EFFECTS: reads serialized flashcard field from file
    public void readFlashcards() {
        ArrayList<FlashcardList> temp;

        try {
            FileInputStream file = new FileInputStream("data/test.ser");
            ObjectInputStream in = new ObjectInputStream(file);

            temp = (ArrayList<FlashcardList>)in.readObject();

            in.close();
            file.close();

            flashcardLists = temp;

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    // EFFECTS: displays command line options
    public void displayOptions() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add flashcard list");
        System.out.println("\tf -> add flashcard");
        System.out.println("\tv -> view flashcard list");
        System.out.println("\tt -> test flashcard list");
        System.out.println("\tq -> quit");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
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

    // MODIFIES: this
    // EFFECTS: adds flashcard list
    public void addFlashcardList() {
        System.out.println("Enter name of flashcard list");

        FlashcardList f = new FlashcardList(input.next());

        flashcardLists.add(f);
    }

    // MODIFIES: this
    // EFFECTS: adds flashcard to specific flashcard list
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

    // EFFECTS: prints random flashcards to console until flashcards is empty;
    // flashcards are printed with front (question) first then after user input, back (answer)
    // is printed to screen
    public void test() {
        for (int i = 0; i < flashcardLists.size(); i++) {
            System.out.println((i + 1) + ". " + flashcardLists.get(i).getName());
        }

        System.out.println("Select a list to test");

        int listNum = input.nextInt() - 1;

        FlashcardList selectedList = flashcardLists.get(listNum);

        Flashcard temp;
        int questionNum = 1;
        Scanner input = new Scanner(System.in);

        selectedList.reset();

        System.out.println("TEST FOR " + selectedList.getName().toUpperCase() + "\n");

        while (selectedList.getUntestedFlashcards().size() > 0) {
            temp = selectedList.getRandomFlashcard();

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
