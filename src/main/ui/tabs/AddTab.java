package ui.tabs;

import ui.FlashcardApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Add Tab for JTabbedPane
public class AddTab extends Tab {

    private JTextField frontText;
    private JTextField backText;
    private JButton addButton;

    private String front;
    private String back;

    // EFFECTS: initializes tab for adding flashcards
    public AddTab(FlashcardApp controller) {
        super(controller);

        frontText = new JTextField();
        backText = new JTextField();
        addButton = new JButton("Add");

        frontText.setColumns(16);
        backText.setColumns(16);

        initTextFields();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addFlashcard(front, back);
                frontText.setText("");
                backText.setText("");
            }
        });

        JPanel p = new JPanel();

        p.add(frontText);
        p.add(backText);
        p.add(addButton);

        p.setSize(300, 300);

        this.add(p);
    }

    // EFFECTS: initializes text field components
    // MODIFIES: this
    public void initTextFields() {
        frontText.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                front = frontText.getText();
            }
        });

        backText.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {


            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {
                back = backText.getText();
            }
        });
    }
}
