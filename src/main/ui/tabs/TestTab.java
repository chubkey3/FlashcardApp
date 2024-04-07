package ui.tabs;

import model.Flashcard;
import ui.FlashcardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TestTab extends Tab {

    private JLabel question;
    private JTextField answer;

    private JButton check;
    private JButton next;

    private Flashcard current;

    private JButton newTest;

    ImageIcon correctImg;
    ImageIcon incorrectImg;

    JPanel imgTest;
    private JLabel imageAsLabel;

    public TestTab(FlashcardApp controller) {
        super(controller);

        correctImg = new ImageIcon("./data/right.jpg");
        incorrectImg = new ImageIcon("./data/wrong.jpg");

        initComponents();

        JPanel p = new JPanel();

        p.add(newTest);
        p.add(question);
        p.add(answer);
        p.add(check);
        p.add(next);

        this.add(p);
        this.add(imgTest);
    }

    public void initComponents() {

        question = new JLabel();
        answer = new JTextField();
        answer.setColumns(16);

        initButtons();

        imgTest = new JPanel();
        imgTest.setPreferredSize(new Dimension(200, 200));
    }

    public void initButtons() {
        newTest = new JButton("New Test");
        newTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTest();
            }
        });

        check = new JButton("Check");
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(answer.getText());
            }
        });

        next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextFlashcard();
            }
        });

        check.setEnabled(false);
        next.setEnabled(false);
    }

    // EFFECTS: starts new flashcard test instance
    // MODIFIES: this
    public void startTest() {
        if (controller.getFlashcards().size() == 0) {
            JOptionPane.showMessageDialog(null, "Please Add a Flashcard First!");
        } else {
            controller.resetFlashcards();
            nextFlashcard();
            setEnabled(true);
        }
    }

    // EFFECTS: sets check and next buttons to enabled
    public void setEnabled(boolean enabled) {
        check.setEnabled(enabled);
        next.setEnabled(enabled);
    }

    // EFFECTS: checks current answer with a
    // sets correct image if answers match and incorrect image otherwise
    // MODIFIES: this
    public void checkAnswer(String a) {
        if (imageAsLabel != null) {
            imgTest.removeAll();
        }

        if (current.checkAnswer(a)) {
            imageAsLabel = new JLabel(correctImg);
            imgTest.add(imageAsLabel);

        } else {
            imageAsLabel = new JLabel(incorrectImg);
            imgTest.add(imageAsLabel);

        }

        imgTest.updateUI();
    }

    // EFFECTS: sets next flashcard for testing
    // MODIFIES: this

    public void nextFlashcard() {
        try {
            current = controller.getRandomFlashcard();
            System.out.println(current.getFront());
            question.setText(current.getFront());

            answer.setText("");

            if (imageAsLabel != null) {
                imgTest.remove(imageAsLabel);
                imgTest.repaint();
            }


        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Test Finished!");
        }

    }
}
