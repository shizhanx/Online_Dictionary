/*
 * Created by JFormDesigner on Fri Apr 09 15:46:03 CST 2021
 */

package server;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;

/**
 * Shizhan Xu, 771900
 * University of Melbourne
 * All rights reserved
 */
public class DetailPanel extends JPanel {
    // The response socket of the associated client of this detail page
    private final Socket socket;
    private final UI ui;

    public DetailPanel(Socket res, UI ui) {
        socket = res;
        this.ui = ui;
        int port = socket.getPort();
        initComponents();
        title.setText("Client at port: " + port);
    }

    /**
     * Try to force close the socket. Upon succeed, the connection thread will
     * detect an IOException and terminate. This page will no longer be available
     * and the UI will navigate back to the monitor page.
     */
    private void disconnectButtonActionPerformed(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(this,
                "Are you sure to force disconnect this client???",
                "Warning",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            try {
                if (!socket.isClosed()) socket.close();
                ui.navigateTo(UI.MONITOR_NAME);
            } catch (IOException ioException) {
                ServerError.IOError(this,
                        "Cannot close socket due to an I/O operation");
            }
        }
    }

    /**
     * Navigate back to the monitor page. Since there can only be one detail
     * page showing in front of the monitor page, setting this one to
     * invisible will automatically set the monitor page to visible.
     */
    private void backButtonActionPerformed(ActionEvent e) {
        ui.navigateTo(UI.MONITOR_NAME);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        title = new JTextField();
        scrollPane1 = new JScrollPane();
        actionsText = new JTextArea();
        backButton = new JButton();
        disconnectButton = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
        swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e" , javax. swing .border
        . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069al\u006fg"
        , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) , getBorder
        () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
        . beans. PropertyChangeEvent e) { if( "\u0062or\u0064er" .equals ( e. getPropertyName () ) )throw new RuntimeException
        ( ) ;} } );
        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {200, 200, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {100, 300, 50, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};

        //---- title ----
        title.setEditable(false);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        add(title, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //======== scrollPane1 ========
        {
            scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            //---- actionsText ----
            actionsText.setText("Actions performed:\n");
            actionsText.setFont(new Font("Source Code Pro", Font.PLAIN, 20));
            actionsText.setEditable(false);
            scrollPane1.setViewportView(actionsText);
        }
        add(scrollPane1, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- backButton ----
        backButton.setText("Back ");
        backButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        backButton.addActionListener(e -> backButtonActionPerformed(e));
        add(backButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- disconnectButton ----
        disconnectButton.setText("Force disconnect");
        disconnectButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 18));
        disconnectButton.setMnemonic('D');
        disconnectButton.setToolTipText("Click to force disconnect this client, other clients are not affected");
        disconnectButton.addActionListener(e -> disconnectButtonActionPerformed(e));
        add(disconnectButton, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    /**
     * Record a request from the associated client.
     * @param action the request detail string
     */
    protected void addAction(String action) {
        actionsText.append(action + '\n');
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTextField title;
    private JScrollPane scrollPane1;
    private JTextArea actionsText;
    private JButton backButton;
    private JButton disconnectButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
