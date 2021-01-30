/*
 * Created by JFormDesigner on Thu Jan 28 12:13:20 EST 2021
 */

package Summoners.Bots.RSCRevolution.UI;

import java.awt.*;
import java.awt.event.*;
import Summoners.Bots.RSCRevolution.MainApplet;
import Summoners.Bots.RSCRevolution.util.logger.ILogger;
import Summoners.Bots.RSCRevolution.util.logger.Logger;

import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Dae COokies
 */
public class Summoners_RSCR_GUI extends JFrame implements ILogger {

    public void processCommand(String s) {
        writeln("New Command: '" + s + "'");
        String[] arguments = s.split(" ");
        if (arguments.length > 0) {
            String command = arguments[0];
            switch (command.toLowerCase()) {
                case "run":
                    if (arguments.length > 1) {
                        String parameter = arguments[1];
                        String[] parameters = null;
                        if (arguments.length > 2) {
                            parameters = new String[arguments.length - 2];
                            for (int i = 0; i < parameters.length; ++i) {
                                parameters[i] = arguments[i + 2];
                            }
                        }
                        mainApplet.mc.InitializeScript(parameter, parameters);
                    }
                    break;
                case "stop":
                    mainApplet.mc.setIsScriptRunning(false);
                    break;
                case "status":
                    mainApplet.mc.script.PrintStatus();
                    break;
                case "command":
                    if (arguments.length > 1) {
                        String parameter = arguments[1];
                        String[] parameters = null;
                        if (arguments.length > 2) {
                            parameters = new String[arguments.length - 2];
                            for (int i = 0; i < parameters.length; ++i) {
                                parameters[i] = arguments[i + 2];
                            }
                        }
                        else { parameters = new String[]{ "" }; }
                        mainApplet.mc.script.OnCommand(parameter, parameters);
                    }
                default:
                    writeln("Error: no command '" + command + "'");
                    break;
            }
        }
    }

    public static void main(String[] var0) {
        Summoners_RSCR_GUI var2 = new Summoners_RSCR_GUI();

    }

    Logger logger;
    public Summoners_RSCR_GUI() {
        super("Summoners: RSCRevolution");
        logger = new Logger(this);
        initComponents(logger);
        mainApplet.init();
        mainApplet.start();
    }

    public void write(String s) {
        outputTextArea.append(s);
        if (!mousePressed) this.outputTextArea.setCaretPosition(this.outputTextArea.getText().length());
    }

    public void writeln(String s) {
        write(s + "\n");
    }

    boolean mousePressed;

    private void outputTextAreaMousePressed(MouseEvent e) {
        mousePressed = true;
    }

    private void outputTextAreaMouseReleased(MouseEvent e) {
        mousePressed = false;
    }
    private void initComponents(Logger logger) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Dae COokies
        scrollPane1 = new JScrollPane();
        outputTextArea = new JTextArea();
        inputTextField = new JTextField();
        mainApplet = new MainApplet(logger);
        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();

        //======== scrollPane1 ========
        {

            //---- outputTextArea ----
            outputTextArea.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    outputTextAreaMousePressed(e);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    outputTextAreaMouseReleased(e);
                }
            });
            scrollPane1.setViewportView(outputTextArea);
        }

        {
            inputTextField.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    processCommand(e.getActionCommand());
                    inputTextField.setText("");
                }
            });
        }
        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                    .addComponent(mainApplet, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addComponent(inputTextField, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(mainApplet, GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(inputTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        setDefaultCloseOperation(3);
        setResizable(true);
        setVisible(true);
        setBackground(Color.black);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Dae COokies

    private MainApplet mainApplet;
    private JScrollPane scrollPane1;
    private JTextArea outputTextArea;
    private JTextField inputTextField;


    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
