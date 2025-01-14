import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame {
    private JFrame f;
    private JButton encrypt;
    private JButton decrypt;
    private JTextArea input;
    private JTextArea output;
    private JComboBox<Integer> rotor1;
    private JComboBox<Integer> rotor2;
    private JComboBox<Integer> rotor3;
    private JTextField startChar;
    
    private final Integer[] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
    11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26};

    public EnigmaFrame() {
        super();
        f = new JFrame();
        f.setTitle("Engima GUI");
        f.setSize(500,500);

        encrypt = new JButton("Encrypt");
        decrypt = new JButton("Decrypt");
        input = new JTextArea(20, 50);
        output = new JTextArea(20, 50);
        output.setEditable(false);
        rotor1 = new JComboBox<Integer>(num);
        rotor2 = new JComboBox<Integer>(num);
        rotor3 = new JComboBox<Integer>(num);
        startChar = new JTextField(3);


        JPanel panel = new JPanel(new FlowLayout());
        f.add(panel, BorderLayout.NORTH);
        panel.add(new JLabel("Inner"));
        panel.add(rotor1);
        panel.add(new JLabel("Middle"));
        panel.add(rotor2);
        panel.add(new JLabel("Out"));
        panel.add(rotor3);
        panel.add(new JLabel("Initial Positions"));
        panel.add(startChar);
        panel.add(encrypt);
        panel.add(decrypt);

        JPanel panel2 = new JPanel(new FlowLayout());
        f.add(panel2, BorderLayout.CENTER);
        panel2.add(new JLabel("Input"));
        panel2.add(input);
        

        JPanel panel3 = new JPanel(new FlowLayout());
        f.add(panel3, BorderLayout.SOUTH);
        panel3.add(new JLabel("Output"));
        panel3.add(output);

        ConvertActionListener e = new ConvertActionListener();

        encrypt.setActionCommand("encrypt");
        decrypt.setActionCommand("decrypt");
        encrypt.addActionListener(e);
        decrypt.addActionListener(e);
        
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);  
    }

    private class ConvertActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ee) {
            try{
                String text = input.getText();

                int inner = (int) rotor1.getSelectedItem();
                int middle = (int) rotor2.getSelectedItem();
                int outer = (int) rotor3.getSelectedItem();
                String result = null;
                String start = startChar.getText();

                if(start.length() != 3){
                    throw new Exception();
                }

                start = start.toUpperCase();
                text = text.toUpperCase();

                Enigma en = new Enigma(inner, middle, outer, start);
                
                String e2 = ee.getActionCommand();
                if("decrypt".equals(e2)) {
                    result = en.decrypt(text);
                }
                else if ("encrypt".equals(e2)) {
                    result = en.encrypt(text);
                }
            
                output.setText(result);
                }catch(Exception e){
                output.setText("Error occured. Check initial positions has 3 characters.");
            }
        }
    }
}
