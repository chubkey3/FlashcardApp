package ui.tabs;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import model.Flashcard;
import ui.FlashcardApp;

// View Tab for JTabbedPane
public class ViewTab extends Tab {

    private JList list;

    // EFFECTS: initializes tab for viewing flashcards
    public ViewTab(FlashcardApp controller) {
        super(controller);

        list = new JList();

        this.add(list);

    }

    // EFFECTS: updates flashcards
    // MODIFIES: this
    public void updateList() {
        List<String> temp = new ArrayList<>();

        for (Flashcard f : controller.getFlashcards()) {
            temp.add(f.getFront());
        }

        list.setListData(temp.toArray());
    }
}
