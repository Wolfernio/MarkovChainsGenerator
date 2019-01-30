import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args){

        JFrame jFrame = new JFrame("ChatterBot");
        jFrame.setLayout(new GridLayout(3, 1));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(600, 500);
        jFrame.setResizable(false);

        JTextArea inputArea = new JTextArea();

        JButton activateButton = new JButton("Go!");

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        jFrame.add(inputArea);
        jFrame.add(activateButton);
        jFrame.add(outputArea);

        activateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                TextLoaderFormatter.splitText(inputArea.getText());
            }
        });

        jFrame.setVisible(true);


    }
}
