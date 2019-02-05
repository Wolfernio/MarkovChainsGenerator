import javax.swing.*;

/**
 * Launcher code for ChatterBot. Can be executed or instantiated.
 */
public class Main {

    /**
     * Creates a visible ChatterBot GUI
     */
    public Main(){
        main(null);
    }

    /**
     * Creates a visible ChatterBot GUI
     */
    public static void main(String[] args){
        JFrame frame = new ChatterBotWindow();

        frame.setVisible(true);
    }
}
