package ui.tabs;

import ui.FlashcardApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Save and Load Tab for JTabbedPane
public class SaveAndLoadTab extends Tab {

    private JButton saveButton;
    private JButton loadButton;

    // EFFECTS: initializes tab for saving and loading flashcards
    public SaveAndLoadTab(FlashcardApp controller) {
        super(controller);

        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.saveFlashcards();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadFlashcards();
            }
        });

        JPanel p = new JPanel();

        p.add(saveButton);
        p.add(loadButton);

        p.setSize(300, 300);

        this.add(p);
    }
}
