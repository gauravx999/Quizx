import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExamSystem extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton nextButton;
    private ButtonGroup bg;
    private int current = 0, score = 0;
    private String rollNo;

    private String[][] questions = {
            { "What is Java?", "A programming language", "A coffee", "An island", "All of the above", "0" },
            { "Which keyword is used to inherit a class in Java?", "extends", "implements", "super", "inherit", "0" },
            { "Which company developed Java?", "Microsoft", "Apple", "Sun Microsystems", "Google", "2" },
            { "Which data type is used to store a single character?", "char", "String", "Character", "word", "0" },
            { "Which of these is not a Java feature?", "Object-Oriented", "Portable", "Interpreted", "Low-level", "3" }
    };

    public ExamSystem() {
        rollNo = JOptionPane.showInputDialog(this, "Enter your Roll Number:");
        if (rollNo == null || rollNo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Roll Number is required to start the exam.");
            System.exit(0);
        }

        setTitle("Examination System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setForeground(Color.BLUE);
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        options = new JRadioButton[4];
        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 14));
            options[i].setForeground(Color.RED);
            bg.add(options[i]);
            optionsPanel.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(Color.BLUE);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        loadQuestion();
        setVisible(true);
    }

    public void loadQuestion() {
        if (current < questions.length) {
            questionLabel.setText("Q" + (current + 1) + ": " + questions[current][0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(questions[current][i + 1]);
            }
            bg.clearSelection();
        } else {
            JOptionPane.showMessageDialog(this, "Exam Over! Your Score: " + score);
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (options[Integer.parseInt(questions[current][5])].isSelected()) {
                score++;
            }
            current++;
            loadQuestion();
        }
    }

    public static void main(String[] args) {
        new ExamSystem();
    }
}


