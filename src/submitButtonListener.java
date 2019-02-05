import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * An implementation of {@link ActionListener} that is used to generate text for ChatterBot.
 */

class submitButtonListener implements ActionListener {

    private JTextComponent inputTextArea;
    private JTextComponent outputTextArea;

    /**
     * Creates a {@link submitButtonListener} with the given input and
     */
    submitButtonListener(JTextComponent inputTextArea, JTextComponent outputTextArea){
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
