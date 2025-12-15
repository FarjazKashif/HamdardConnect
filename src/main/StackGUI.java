package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Example: Stack DSA with GUI
public class StackGUI extends JFrame {
    private Stack<Integer> stack;
    private JTextArea displayArea;
    private JTextField inputField;
    private JLabel statusLabel;
    
    public StackGUI() {
        stack = new Stack<>();
        setupUI();
    }
    
    private void setupUI() {
        setTitle("Stack Data Structure Visualization");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Display area for stack
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter value:"));
        inputField = new JTextField(10);
        inputPanel.add(inputField);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        
        JButton pushBtn = new JButton("Push");
        JButton popBtn = new JButton("Pop");
        JButton peekBtn = new JButton("Peek");
        JButton sizeBtn = new JButton("Size");
        JButton clearBtn = new JButton("Clear");
        JButton isEmptyBtn = new JButton("Is Empty?");
        
        pushBtn.addActionListener(e -> push());
        popBtn.addActionListener(e -> pop());
        peekBtn.addActionListener(e -> peek());
        sizeBtn.addActionListener(e -> showSize());
        clearBtn.addActionListener(e -> clear());
        isEmptyBtn.addActionListener(e -> checkEmpty());
        
        buttonPanel.add(pushBtn);
        buttonPanel.add(popBtn);
        buttonPanel.add(peekBtn);
        buttonPanel.add(sizeBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(isEmptyBtn);
        
        // Status label
        statusLabel = new JLabel("Ready");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // Add panels
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(topPanel, BorderLayout.NORTH);
        add(statusLabel, BorderLayout.SOUTH);
        
        updateDisplay();
    }
    
    private void push() {
        try {
            int value = Integer.parseInt(inputField.getText());
            stack.push(value);
            inputField.setText("");
            updateDisplay();
            statusLabel.setText("Pushed: " + value);
        } catch (NumberFormatException ex) {
            statusLabel.setText("Error: Please enter a valid integer");
        }
    }
    
    private void pop() {
        if (!stack.isEmpty()) {
            int value = stack.pop();
            updateDisplay();
            statusLabel.setText("Popped: " + value);
        } else {
            statusLabel.setText("Error: Stack is empty");
        }
    }
    
    private void peek() {
        if (!stack.isEmpty()) {
            statusLabel.setText("Top element: " + stack.peek());
        } else {
            statusLabel.setText("Error: Stack is empty");
        }
    }
    
    private void showSize() {
        statusLabel.setText("Stack size: " + stack.size());
    }
    
    private void clear() {
        stack.clear();
        updateDisplay();
        statusLabel.setText("Stack cleared");
    }
    
    private void checkEmpty() {
        statusLabel.setText("Is empty? " + stack.isEmpty());
    }
    
    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack Contents (Top to Bottom):\n");
        sb.append("================================\n\n");
        
        if (stack.isEmpty()) {
            sb.append("[ Empty ]\n");
        } else {
            for (int i = stack.size() - 1; i >= 0; i--) {
                sb.append("  | ").append(stack.get(i)).append(" |\n");
                sb.append("  +-----+\n");
            }
        }
        
        displayArea.setText(sb.toString());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StackGUI gui = new StackGUI();
            gui.setVisible(true);
        });
    }
}