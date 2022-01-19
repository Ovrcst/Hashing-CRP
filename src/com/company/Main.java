package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

public class Main extends JFrame implements ActionListener {
    int value, position,size;
    JFrame frame = new JFrame("Calculator");
    JTextField inputSize = new JTextField();
    JTextArea answers = new JTextArea();
    JTextField textfield = new JTextField();
    JButton submit = new JButton("Submit");
    JLabel forTextField = new JLabel("Input Numbers Here:");
    JLabel forAnswers = new JLabel("Answer:");
    JLabel forSize = new JLabel("Size of Array:");

    Main(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLayout(null);

        answers.setBounds(110, 95, 720, 100);
        answers.setEditable(false);
        forAnswers.setBounds(50,95,80,40);

        textfield.setBounds(180, 245,650,100);
        forTextField.setBounds(50,245,150,40);

        submit.setBounds(420, 395, 80, 20);
        submit.addActionListener(this);

        inputSize.setBounds(420, 360,80,20);
        inputSize.setToolTipText("Size of The Array");
        forSize.setBounds(335,360,150,20);

        frame.add(forSize);
        frame.add(forTextField);
        frame.add(forAnswers);
        frame.add(inputSize);
        frame.add(answers);
        frame.add(textfield);
        frame.add(submit);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
	// write your code here
        Main main = new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            hashed();
        }
    }
    void hashed(){
        if(inputSize.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Input Size for the Array");
        }
        size = Integer.parseInt(inputSize.getText());
        Hashtable<Integer, Integer> table = new Hashtable<>();
        //while(true){
        String str = textfield.getText();
        String[] splitted = str.split("\\s+");
        for(String a:splitted) {
            value = Integer.parseInt(a);
            position = value % size;
            //if(value == -1){
            //break;
            //}
            //System.out.printf("%d %s %d = %d %n",value,"%", size, position);
            if (table.get(position) == null) { //(table.get(position) != null && table.size() == size)
                table.put(position, value);
            }
            else if(table.size()==size){
                continue;
            }
            else {
                for (int i = position + 1; i < size; i++) {
                    if (table.get(i) == null) {
                        table.put(i, value);
                        break;
                    }
                    if (i == size - 1) {
                        for (int j = 0; j < position; j++) {
                            if (table.get(j) == null) {
                                table.put(j, value);
                                break;
                            }
                        }
                    }
                }
            }
        }
            //System.out.println(table);
            //System.out.println("\nCurrent Size of the Table is "+table.size());
        //}
        //System.out.println("FINAL TABLE IS:" + table);
        String theAnswer ="";
        for(int i= 0;i<table.size();i++){
            theAnswer = theAnswer + table.get(i) + " ";
        }
        answers.setText(theAnswer);
        System.out.println(table);
    }

}
