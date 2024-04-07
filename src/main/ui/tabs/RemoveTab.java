package ui.tabs;

import model.Flashcard;
import ui.FlashcardApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RemoveTab extends Tab {

    private JComboBox select;
    private JButton removeButton;

    public RemoveTab(FlashcardApp controller) {
        super(controller);

        select = new JComboBox();

        removeButton = new JButton("Remove");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.removeFlashcard(String.valueOf(select.getSelectedItem()));
            }
        });

        JPanel p = new JPanel();

        p.add(select);
        p.add(removeButton);

        p.setSize(300, 300);

        this.add(p);
    }

    public void updateList() {
        select.removeAllItems();

        for (Flashcard f : controller.getFlashcards()) {
            select.addItem(f.getFront());
        }
    }
}
