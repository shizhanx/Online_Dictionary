/*
 * Created by JFormDesigner on Wed Apr 07 22:26:42 CST 2021
 */

package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

/**
 * Shizhan Xu, 771900
 * University of Melbourne
 * All rights reserved
 */
public class UI extends JFrame {
    protected DictionaryClient client;

    public UI(DictionaryClient client) {
        this.client = client;
        initComponents();
        setVisible(true);
    }

    /**
     * Perform connection with the server, catch any possible errors
     * create popup dialogs responsively
     */
    private void connectButtonActionPerformed(ActionEvent event) {
        try {
            client.connect();
        } catch (UnknownHostException e) {
            ClientError.connectionError(initialPanel,
                    "server address cannot be reached");
        } catch (IllegalArgumentException e) {
            ClientError.connectionError(initialPanel,
                    "port number must be between 0 and 65535");
        } catch (ConnectException e) {
            ClientError.connectionError(initialPanel,
                    "please try another port number");
        } catch (IOException e) {
            ClientError.connectionError(initialPanel,
                    "The server is down, closing now");
        }

        JPanel searchPanel = new SearchPanel(client);
        add(searchPanel);
        remove(initialPanel);
        pack();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        initialPanel = new JPanel();
        initialTitle = new JTextField();
        connectButton = new JButton();
        mySignature = new JTextField();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dictionary Client");
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== initialPanel ========
        {
            initialPanel.setLayout(new GridBagLayout());
            ((GridBagLayout)initialPanel.getLayout()).columnWidths = new int[] {600, 0};
            ((GridBagLayout)initialPanel.getLayout()).rowHeights = new int[] {205, 105, 0, 0};
            ((GridBagLayout)initialPanel.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
            ((GridBagLayout)initialPanel.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0E-4};

            //---- initialTitle ----
            initialTitle.setEditable(false);
            initialTitle.setFont(new Font("Footlight MT Light", Font.BOLD, 32));
            initialTitle.setText("Welcome to the online dictionary");
            initialTitle.setHorizontalAlignment(SwingConstants.CENTER);
            initialPanel.add(initialTitle, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- connectButton ----
            connectButton.setText("Connect");
            connectButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
            connectButton.addActionListener(e -> connectButtonActionPerformed(e));
            initialPanel.add(connectButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 5, 0), 0, 0));

            //---- mySignature ----
            mySignature.setEditable(false);
            mySignature.setText("Shizhan Xu, 771900, All rights reserved");
            mySignature.setHorizontalAlignment(SwingConstants.TRAILING);
            initialPanel.add(mySignature, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        }
        contentPane.add(initialPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel initialPanel;
    private JTextField initialTitle;
    private JButton connectButton;
    private JTextField mySignature;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
