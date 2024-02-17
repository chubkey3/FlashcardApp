package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FlashcardTest {

    private Flashcard flashcardTest;

    @BeforeEach
    void beforeEach() {
        flashcardTest = new Flashcard("test_front", "test_back");
    }

    @Test
    void getFrontTest() {
        assertEquals("test_front", flashcardTest.getFront());
    }

    @Test
    void getBackTest() {
        assertEquals("test_back", flashcardTest.getBack());
    }
}