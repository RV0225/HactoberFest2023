package student_grade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculatorGUI extends JFrame {
    private JTextField nameTextField;
    private JComboBox<String> subjectComboBox;
    private JTextField scoreTextField;
    private JButton addButton;
    private JButton calculateButton;
    private JTextArea resultTextArea;

    private String studentName;
    private int totalMarks;
    private int numSubjects;
    private double averagePercentage;
    private char grade;

    public StudentGradeCalculatorGUI() {
        setTitle("Student Grade Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Enter the student's name:");
        panel.add(nameLabel, constraints);

        constraints.gridy++;
        nameTextField = new JTextField(20);
        panel.add(nameTextField, constraints);

        constraints.gridy++;
        JLabel subjectLabel = new JLabel("Select subject:");
        panel.add(subjectLabel, constraints);

        constraints.gridy++;
        subjectComboBox = new JComboBox<>(new String[]{"Science", "English", "Social Science", "Hindi", "Maths","Physics","Chemistry","Accounts","Business"});
        panel.add(subjectComboBox, constraints);

        constraints.gridy++;
        JLabel scoreLabel = new JLabel("Enter score (out of 100):");
        panel.add(scoreLabel, constraints);

        constraints.gridy++;
        scoreTextField = new JTextField(5);
        panel.add(scoreTextField, constraints);

        constraints.gridy++;
        addButton = new JButton("Add");
        panel.add(addButton, constraints);

        constraints.gridy++;
        calculateButton = new JButton("Calculate");
        panel.add(calculateButton, constraints);

        constraints.gridy++;
        resultTextArea = new JTextArea(8, 30);
        resultTextArea.setEditable(false);
        panel.add(resultTextArea, constraints);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get score and update total marks
                int score = Integer.parseInt(scoreTextField.getText());
                totalMarks += score;

                // Reset input fields
                scoreTextField.setText("");
                subjectComboBox.setSelectedIndex(0);
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear previous results
                resultTextArea.setText("");

                // Get student name
                studentName = nameTextField.getText();

                // Calculate average percentage
                numSubjects = 5; // Assuming 5 subjects
                averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;

                // Calculate grade
                grade = calculateGrade(averagePercentage);

                // Display results
                resultTextArea.append("Student Name: " + studentName + "\n");
                resultTextArea.append("Total Marks for all subjects: " + totalMarks + "\n");
                resultTextArea.append("Average Percentage: " + averagePercentage + "%\n");
                resultTextArea.append("Grade: " + grade + "\n");
                totalMarks = 0;
            }
        });

        add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StudentGradeCalculatorGUI().setVisible(true);
            }
        });
    }

    // Function to calculate the grade based on the average percentage
    public char calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return 'A';
        } else if (averagePercentage >= 80) {
            return 'B';
        } else if (averagePercentage >= 70) {
            return 'C';
        } else if (averagePercentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}


