import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class submitButtonListener implements ActionListener {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;

    public submitButtonListener(JTextArea inputTextArea, JTextArea outputTextArea){
        this.inputTextArea = inputTextArea;
        this.outputTextArea = outputTextArea;
    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e){
        MarkovChainController controller;
        try {
            controller = new MarkovChainController(this.inputTextArea.getText(), false);
        } catch(FileNotFoundException e1) {
            return; // should never happen lol
        }

        this.outputTextArea.setText(controller.generateText(1000));
    }
}
