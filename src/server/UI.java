/*
 * Created by JFormDesigner on Fri Apr 09 14:13:13 CST 2021
 */

package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Shizhan Xu, 771900
 * University of Melbourne
 * All rights reserved
 */
public class UI extends JFrame {
    // The name of the card of the main monitor page
    public static final String MONITOR_NAME = "monitor";

    // A port number -> detail panel mapping.
    private final Map<Integer, DetailPanel> detailPanels;
    private final MonitorPanel monitor;
    private final CardLayout cardLayout;

    public UI() {
        detailPanels = new HashMap<>();
        monitor = new MonitorPanel(this);
        initComponents();
        cardLayout = (CardLayout) getContentPane().getLayout();
    }

    /**
     * Discard the initial page and navigate to monitor page.
     * Resize the window since monitor and detail pages have the same size.
     */
    private void startButtonActionPerformed(ActionEvent event) {
        remove(initialPanel);
        add(monitor, MONITOR_NAME);
        cardLayout.show(getContentPane(), MONITOR_NAME);
        pack();
    }

    /**
     * Put the page with the specific name to the front.
     */
    protected void navigateTo(String name) {
        cardLayout.show(getContentPane(), name);
    }

    /**
     * Add a client according to his specific socket.
     * A detail page is created for him and added into the cardLayout.
     * The client is added to monitor page as well.
     * @param res the response socket for this client
     */
    protected void addClient(Socket res) {
        DetailPanel detail = new DetailPanel(res, this);
        // A new line char is included for clearer appearance on the list.
        add(detail, Integer.toString(res.getPort()) + '\n');
        detailPanels.put(res.getPort(), detail);
        monitor.addClient(res.getPort());
    }

    /**
     * Remove a client from this UI.
     * First remove it from the monitor list. Then remove the corresponding
     * detail page. Finally remove the map entry.
     * @param port the port number of the response socket of this client
     */
    protected void removeClient(int port) {
        monitor.removeClient(port);
        remove(detailPanels.get(port));
        detailPanels.remove(port);
    }

    /**
     * Record each request received by this server.
     * @param s the request detail string
     * @param port the port number of the client
     */
    protected void receiveRequest(String s, int port) {
        detailPanels.get(port).addAction(s);
        monitor.renewAction(s, port);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        initialPanel = new JPanel();
        textField3 = new JTextField();
        textField4 = new JTextField();
        initialTitle = new JTextField();
        startButton = new JButton();
        mySignature = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Dictionary Server");
        Container contentPane = getContentPane();
        contentPane.setLayout(new CardLayout());

        //======== initialPanel ========
        {
            initialPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)initialPanel.getLayout()).columnWidths = new int[] {600, 0};
            ((GridBagLayout)initialPanel.getLayout()).rowHeights = new int[] {35, 35, 105, 55, 0, 0};
            ((GridBagLayout)initialPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)initialPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 1.0, 1.0, 1.0E-4};

            //---- textField3 ----
            textField3.setEditable(false);
            textField3.setText("Welcome to the server side GUI of");
            textField3.setHorizontalAlignment(SwingConstants.CENTER);
            textField3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            initialPanel.add(textField3, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- textField4 ----
            textField4.setEditable(false);
            textField4.setText("the ONE and the ONLY");
            textField4.setHorizontalAlignment(SwingConstants.CENTER);
            textField4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
            initialPanel.add(textField4, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- initialTitle ----
            initialTitle.setEditable(false);
            initialTitle.setFont(new Font("Footlight MT Light", Font.BOLD, 40));
            initialTitle.setText("ONLINE DICTIONARY");
            initialTitle.setHorizontalAlignment(SwingConstants.CENTER);
            initialPanel.add(initialTitle, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- startButton ----
            startButton.setText("Start monitor");
            startButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            startButton.addActionListener(e -> startButtonActionPerformed(e));
            initialPanel.add(startButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- mySignature ----
            mySignature.setEditable(false);
            mySignature.setText("Shizhan Xu, 771900, All rights reserved");
            mySignature.setHorizontalAlignment(SwingConstants.TRAILING);
            initialPanel.add(mySignature, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(initialPanel, "card1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel initialPanel;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField initialTitle;
    private JButton startButton;
    private JTextField mySignature;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
