package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];   //Buttons for all the 10 numbers
    JButton[] functionButtons = new JButton[9];
    JButton addButton,subButton,mulButton,divButton,decButton,equButton,delButton,clrButton,negButton;
    JPanel panel;
    Font myFont = new Font("Chiller", Font.BOLD | Font.ITALIC,30);
    double num1=0,num2=0,result=0;
    char operator;
    Calculator()
    {
       frame = new JFrame("Mr.Calculator");
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\rhydi\\Desktop\\Projects\\Java\\AppIcon.jpg");
        frame.setIconImage(icon);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(420,550);
       frame.setLayout(null);
       textField = new JTextField();
       textField.setBounds(55,25,300,50);
       textField.setFont(myFont);
       textField.setEditable(false);
       panel = new JPanel();
       panel.setBounds(50,100,300,300);
       panel.setLayout(new GridLayout(4,4,10,10));
        {   // Initialising fxn buttons in the array
            addButton = new JButton("+");
            functionButtons[0] = addButton;
            subButton = new JButton("-");
            functionButtons[1] = subButton;
            mulButton = new JButton("x");
            functionButtons[2] = mulButton;
            divButton = new JButton("/");
            functionButtons[3] = divButton;
            decButton = new JButton(".");
            functionButtons[4] = decButton;
            equButton = new JButton("=");
            functionButtons[5] = equButton;
            delButton = new JButton("Del");
            functionButtons[6] = delButton;
            clrButton = new JButton("Clear");
            functionButtons[7] = clrButton;
            negButton=new JButton("Neg");
            functionButtons[8] = negButton;
            for (int i=0;i<9;i++)
            {
                functionButtons[i].addActionListener(this);
                functionButtons[i].setFont(myFont);
                functionButtons[i].setFocusable(false);
            }
            negButton.setBounds(50,430,90,50);
            delButton.setBounds(150,430,90,50);
            clrButton.setBounds(250,430,90,50);
            frame.add(negButton);
            frame.add(delButton);
            frame.add(clrButton);

        }
        {//Initializing number buttons
            for (int i=0;i<10;i++)
            {
                numberButtons[i]=new JButton(String.valueOf(i));
                numberButtons[i].addActionListener(this);
                numberButtons[i].setFont(myFont);
                numberButtons[i].setFocusable(false);
            }
        }
        for(int i=1;i<=3;i++) panel.add(numberButtons[i]);
        panel.add(addButton);
        for(int i=4;i<=6;i++) panel.add(numberButtons[i]);
        panel.add(subButton);
        for(int i=7;i<=9;i++) panel.add(numberButtons[i]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(textField);
        frame.add(panel);
       frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       for(int i=0;i<10;i++)
       {
           if(e.getSource()==numberButtons[i])
           {
               textField.setText(textField.getText().concat(String.valueOf(i)));
           }
       }
       if(e.getSource()==decButton)
       {
           textField.setText(textField.getText().concat(String.valueOf(".")));
       }
        if(e.getSource()==addButton)
        {
            num1=Double.parseDouble(textField.getText());
            operator='+';
            textField.setText("");
        }
        if(e.getSource()==subButton)
        {
            num1=Double.parseDouble(textField.getText());
            operator='-';
            textField.setText("");
        }
        if(e.getSource()==mulButton)
        {
            num1=Double.parseDouble(textField.getText());
            operator='*';
            textField.setText("");
        }
        if(e.getSource()==divButton)
        {
            num1=Double.parseDouble(textField.getText());
            operator='/';
            textField.setText("");
        }
        if(e.getSource()==equButton)
        {
            num2=Double.parseDouble(textField.getText());
            switch (operator)
            {
                case '+':
                    result = num1+num2;
                    break;
                case '-':
                    result = num1-num2;
                    break;
                case '*':
                    result = num1*num2;
                    break;
                case '/':
                    result = num1/num2;
                    break;
            }
            textField.setText(String.valueOf(result));
            num1=result;
        }
        if(e.getSource()==clrButton)
        {
            textField.setText("");
        }
        if(e.getSource()==delButton)
        {
            String string=textField.getText();
            StringBuffer sb = new StringBuffer(string);
            sb.deleteCharAt(sb.length()-1);
            textField.setText(String.valueOf(sb));
        }
        if(e.getSource()==negButton)
        {
            num1=Double.parseDouble(textField.getText());
            result=num1*-1;
            textField.setText(String.valueOf(result));
            num1=result;
        }
    }
}
