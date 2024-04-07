package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardListTest {

    private FlashcardList testList;
    private Flashcard flashcardTest;
    private Flashcard flashcardTest2;

    @BeforeEach
    void beforeEach() {
        testList = new FlashcardList("test flashcard list");
        flashcardTest = new Flashcard("test_front", "test_back");
        flashcardTest2 = new Flashcard("test_front2", "test_back2");
    }

    @Test
    void testConstructor() {
        assertEquals("test flashcard list", testList.getName());
        assertEquals(0, testList.getNumberOfFlashcards());
        assertEquals(new ArrayList<>(), testList.getFlashcards());
    }

    @Test
    void testAddFlashcard() {
        testList.addFlashcard(flashcardTest);

        assertEquals(1, testList.getNumberOfFlashcards());
        assertEquals(flashcardTest, testList.getFlashcards().get(0));
    }

    @Test
    void testAddMultipleFlashcards() {
        testList.addFlashcard(flashcardTest);

        assertEquals(1, testList.getNumberOfFlashcards());
        assertEquals(flashcardTest, testList.getFlashcards().get(0));

        testList.addFlashcard(flashcardTest2);

        assertEquals(2, testList.getNumberOfFlashcards());
        assertEquals(flashcardTest, testList.getFlashcards().get(0));
        assertEquals(flashcardTest2, testList.getFlashcards().get(1));
    }


    @Test
    void getRandomFlashcardTest() {
        testList.addFlashcard(flashcardTest);

        assertEquals(flashcardTest, testList.getRandomFlashcard());
        assertEquals(0, testList.getUntestedFlashcards().size());
    }

    @Test
    void resetTest() {
        testList.addFlashcard(flashcardTest);

        assertEquals(1, testList.getUntestedFlashcards().size());
        assertEquals(testList.getFlashcards(), testList.getUntestedFlashcards());

        testList.getRandomFlashcard();
        assertEquals(0, testList.getUntestedFlashcards().size());
        assertEquals(new ArrayList<Flashcard>(), testList.getUntestedFlashcards());

        testList.reset();
        assertEquals(1, testList.getUntestedFlashcards().size());
        assertEquals(testList.getFlashcards(), testList.getUntestedFlashcards());

    }

    @Test
    void removeTest() {
        testList.addFlashcard(flashcardTest);
        testList.addFlashcard(flashcardTest2);

        assertEquals(2, testList.getFlashcards().size());
        assertEquals(flashcardTest, testList.getFlashcards().get(0));
        assertEquals(flashcardTest2, testList.getFlashcards().get(1));

        testList.removeFlashcards(flashcardTest.getFront());

        assertEquals(1, testList.getFlashcards().size());
        assertEquals(flashcardTest2, testList.getFlashcards().get(0));

        testList.removeFlashcards(flashcardTest.getFront());

        assertEquals(1, testList.getFlashcards().size());
        assertEquals(flashcardTest2, testList.getFlashcards().get(0));

        testList.removeFlashcards(flashcardTest2.getFront());

        assertEquals(0, testList.getFlashcards().size());
    }
}