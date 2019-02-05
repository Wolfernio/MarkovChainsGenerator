import javax.swing.*;
import java.awt.*;

public class ChatterBotWindow extends JFrame {

    private JPanel mainPanel;
    private JTextArea inputArea;
    private JButton activateButton;
    private JTextArea outputArea;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JScrollPane inputScrollPane;
    private JPanel buttonPanel;
    private JPanel subButtonPanel;
    private JScrollPane outputScrollPane;

    public ChatterBotWindow(){

        this.setTitle("ChatterBot");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 500);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridLayout(3, 1));

        this.inputPanel = new JPanel(new BorderLayout());
        this.buttonPanel = new JPanel(new BorderLayout());
        this.subButtonPanel = new JPanel(new BorderLayout());
        this.outputPanel = new JPanel(new BorderLayout());

        this.inputArea = new JTextArea();
        this.activateButton = new JButton("Go!");
        this.outputArea = new JTextArea();
        this.outputArea.setEditable(false);

        this.inputArea.setLineWrap(true);
        this.outputArea.setLineWrap(true);

        this.inputArea.setWrapStyleWord(true);
        this.outputArea.setWrapStyleWord(true);

        this.inputScrollPane = new JScrollPane(this.inputArea);
        this.outputScrollPane = new JScrollPane(this.outputArea);

        this.inputPanel.add(this.inputScrollPane, BorderLayout.CENTER);
        this.outputPanel.add(this.outputScrollPane, BorderLayout.CENTER);

        this.inputPanel.add(new JLabel("Input:"), BorderLayout.NORTH);
        this.inputPanel.add(new JPanel(), BorderLayout.EAST);
        this.inputPanel.add(new JPanel(), BorderLayout.SOUTH);
        this.inputPanel.add(new JPanel(), BorderLayout.WEST);
        this.outputPanel.add(new JLabel("Output:"), BorderLayout.NORTH);
        this.outputPanel.add(new JPanel(), BorderLayout.EAST);
        this.outputPanel.add(new JPanel(), BorderLayout.SOUTH);
        this.outputPanel.add(new JPanel(), BorderLayout.WEST);

        this.subButtonPanel.add(this.activateButton, BorderLayout.PAGE_START);
        this.buttonPanel.add(this.subButtonPanel, BorderLayout.LINE_END);

        mainPanel.add(this.inputPanel);
        mainPanel.add(this.buttonPanel);
        mainPanel.add(this.outputPanel);

        this.activateButton.addActionListener(new submitButtonListener(inputArea, outputArea));

        this.add(new JPanel(), BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.EAST);
        this.add(new JPanel(), BorderLayout.SOUTH);
        this.add(new JPanel(), BorderLayout.WEST);

        this.add(mainPanel, BorderLayout.CENTER);
    }
}
