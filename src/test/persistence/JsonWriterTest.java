package persistence;

import model.FlashcardList;
import model.Flashcard;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test taken from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            FlashcardList fl = new FlashcardList("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            FlashcardList fl = new FlashcardList("My work room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFlashcardList.json");
            writer.open();
            writer.write(fl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFlashcardList.json");
            fl = reader.read();
            assertEquals("My work room", fl.getName());
            assertEquals(0, fl.getNumberOfFlashcards());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            FlashcardList fl = new FlashcardList("My work room");
            fl.addFlashcard(new Flashcard("hi1", "bye1"));
            fl.addFlashcard(new Flashcard("hi2", "bye2"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFlashcardList.json");
            writer.open();
            writer.write(fl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFlashcardList.json");
            fl = reader.read();
            assertEquals("My work room", fl.getName());
            List<Flashcard> flashcards = fl.getFlashcards();
            assertEquals(2, flashcards.size());

            assertEquals("hi1", flashcards.get(0).getFront());
            assertEquals("bye1", flashcards.get(0).getBack());
            assertEquals("hi2", flashcards.get(1).getFront());
            assertEquals("bye2", flashcards.get(1).getBack());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}