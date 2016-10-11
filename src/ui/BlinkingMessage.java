/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import org.pushingpixels.trident.Timeline;
import org.pushingpixels.trident.Timeline.RepeatBehavior;

/**
 *
 * @author jeff
 */
public class BlinkingMessage {
    
    public static void createAndShowGUI(String message) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setLayout(new GridLayout(30, 30));
//
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
//        frame.setSize(xSize, ySize);

//        for (int i = 0; i < 900; i++) {
//            JPanel panel = new JPanel();
//            frame.add(panel);
//
//            Timeline timeline = new Timeline(panel);
//            timeline.addPropertyToInterpolate("background", Utils.randomColor(), Utils.randomColor());
//            timeline.setDuration(100);
//            timeline.playLoop(RepeatBehavior.REVERSE);
//        }
        final JFrame frame2 = new JFrame();
        
        frame2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
                frame2.setVisible(false);
            }
        });
        
        frame2.setSize(xSize, ySize);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        JTextArea txtArea = new JTextArea(message);
        txtArea.setSize(frame2.getSize());
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setBackground(frame2.getBackground());

        panel.add(txtArea);
        frame2.add(panel);

        Timeline timeline = new Timeline(panel);
        timeline.addPropertyToInterpolate("background", frame2.getBackground(), Color.red);
        timeline.setDuration(100);
        timeline.playLoop(RepeatBehavior.REVERSE);
        Timeline timeline2 = new Timeline(txtArea);
        timeline2.addPropertyToInterpolate("background", frame2.getBackground(), Color.red);
        timeline2.setDuration(100);
        timeline2.playLoop(RepeatBehavior.REVERSE);

//        frame.setVisible(true);
        frame2.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI("some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text some text ");
            }
        });
    }
}
