package org.quantumwall.cleaner.gui;

import javax.swing.*;
import java.awt.*;

public class CleanerFrame extends JFrame {
    private static final int SCEEN_WIDTH;
    private static final int SCREEN_HEIGHT;
    private static final int FRAME_WIDTH;
    private static final int FRAME_HEIGHT;
    
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        SCEEN_WIDTH = screenSize.width;
        SCREEN_HEIGHT = screenSize.height;
        FRAME_WIDTH = SCEEN_WIDTH > 500 ? 500 : SCEEN_WIDTH;
        FRAME_HEIGHT = SCREEN_HEIGHT > 500 ? 500 : SCREEN_HEIGHT;
    }
    
    public CleanerFrame() {
        super("CCleaner");
        setBounds((SCEEN_WIDTH - FRAME_WIDTH) / 2,
                (SCREEN_HEIGHT - FRAME_HEIGHT) / 2,
                FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
        }
    }
}
