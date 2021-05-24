package client;

import javax.swing.*;
import java.awt.*;

public class ClientError {
    /**
     * Incorrect arguments leading to system exit.
     */
    public static void argError(String msg) {
        System.err.println("Argument error: " + msg);
        System.exit(1);
    }

    /**
     * Connection failed, pop up a notice, exit afterwards.
     */
    public static void connectionError(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent,
                "Connection error: " + msg,
                "Error",
                JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    /**
     * The request format is incorrect, pop up a notice.
     */
    public static void formatWarning(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent,
                "Format warning: " + msg,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * The server side content doesn't support the request,
     * pop up a notice.
     */
    public static void contentWarning(Component parent, String msg) {
        JOptionPane.showMessageDialog(parent,
                "Content warning: " + msg,
                "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * The server side has successfully completed a request
     */
    public static void successMessage(Component parent) {
        JOptionPane.showMessageDialog(parent,
                "Another triumph of our DICTIONARY",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
