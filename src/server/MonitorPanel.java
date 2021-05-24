/*
 * Created by JFormDesigner on Fri Apr 09 15:28:00 CST 2021
 */

package server;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Shizhan Xu, 771900
 * University of Melbourne
 * All rights reserved
 */
public class MonitorPanel extends JPanel {
    // Assign a default listModel to the list of clients
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final UI frame;

    public MonitorPanel(UI ui) {
        initComponents();
        frame = ui;
        clientList.setModel(listModel);
    }

    /**
     * Initially the details button is disabled. Enable it when a client is selected
     */
    private void clientListValueChanged(ListSelectionEvent e) {
        detailsButton.setEnabled(true);
    }

    /**
     * Navigate to the selected client's details page
     */
    private void detailsButtonActionPerformed(ActionEvent e) {
        frame.navigateTo((String) clientList.getSelectedValue());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        title = new JTextField();
        latestActionText = new JTextField();
        scrollPane1 = new JScrollPane();
        clientList = new JList();
        detailsButton = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
        . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax
        . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,
        12 ), java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans
        . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .
        getPropertyName () )) throw new RuntimeException( ); }} );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {200, 200, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {100, 50, 300, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

        //---- title ----
        title.setEditable(false);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("Monitor connections");
        title.setFont(new Font("Nirmala UI", Font.PLAIN, 36));
        add(title, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- latestActionText ----
        latestActionText.setHorizontalAlignment(SwingConstants.CENTER);
        latestActionText.setEditable(false);
        latestActionText.setText("Latest actions will appear here");
        latestActionText.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        add(latestActionText, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //---- clientList ----
            clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            clientList.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
            clientList.setPrototypeCellValue("12345");
            clientList.addListSelectionListener(e -> clientListValueChanged(e));
            scrollPane1.setViewportView(clientList);
        }
        add(scrollPane1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- detailsButton ----
        detailsButton.setText("Details");
        detailsButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
        detailsButton.setEnabled(false);
        detailsButton.addActionListener(e -> detailsButtonActionPerformed(e));
        add(detailsButton, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    /**
     * Add a client to the list. Record this action.
     * @param port port number of the response socket of this client
     */
    protected void addClient(int port) {
        // A new line char is included for clearer appearance on the list.
        listModel.addElement(Integer.toString(port) + '\n');
        latestActionText.setText("Client connected at port: " + port);
    }

    /**
     * Remove a client from the list. Record this action. Disable details button
     * because the selected client might be disconnected.
     * @param port port number of the response socket of this client
     */
    protected void removeClient(int port) {
        // A new line char is included for clearer appearance on the list.
        listModel.removeElement(Integer.toString(port) + '\n');
        latestActionText.setText("The client at port: " + port + " is no longer connected");
        detailsButton.setEnabled(false);
    }

    /**
     * Record a request from a client.
     * @param s the request detail string
     * @param port port number of the response socket of this client
     */
    protected void renewAction(String s, int port) {
        latestActionText.setText("Client "+ port + " just did: " + s);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTextField title;
    private JTextField latestActionText;
    private JScrollPane scrollPane1;
    private JList clientList;
    private JButton detailsButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
