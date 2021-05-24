package server;

import javax.swing.*;
import java.awt.*;

/**
 * Shizhan Xu, 771900
 * University of Melbourne
 * All rights reserved
 */
public class ServerError {
    /**
     * Incorrect arguments leading to system exit.
     */
    public static void argError(String msg) {
        System.err.println("Argument error: " + msg);
        System.exit(1);
    }

    /**
     * Connection failed, pop up a notice.
     */
    public static void connectionError(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent,
                "Connection error: " + msg,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * An IOException happened when trying to manipulate a socket.
     */
    public static void IOError(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent,
                "IO error: " + msg,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}
