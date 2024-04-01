package ui.tabs;

import ui.FlashcardApp;

import javax.swing.*;

// Tab Class for JTabbedPane
public class Tab extends JPanel {

    protected FlashcardApp controller;

    // EFFECTS: initializes tab with reference to flashcard app
    public Tab(FlashcardApp controller) {
        super();

        this.controller = controller;

    }

}
