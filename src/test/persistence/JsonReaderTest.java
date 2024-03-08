package persistence;

import model.FlashcardList;
import model.Flashcard;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FlashcardList fl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFlashcardList.json");
        try {
            FlashcardList fl = reader.read();
            assertEquals("My work room", fl.getName());
            assertEquals(0, fl.getNumberOfFlashcards());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFlashcardList.json");
        try {
            FlashcardList fl = reader.read();
            assertEquals("My work room", fl.getName());
            List<Flashcard> flashcards = fl.getFlashcards();
            assertEquals(2, flashcards.size());

            assertEquals("hi1", flashcards.get(0).getFront());
            assertEquals("bye1", flashcards.get(0).getBack());
            assertEquals("hi2", flashcards.get(1).getFront());
            assertEquals("bye2", flashcards.get(1).getBack());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}