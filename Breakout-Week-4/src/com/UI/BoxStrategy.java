package com.UI;

import javax.swing.*;

import com.constants.Constants;

import java.awt.*;

public class BoxStrategy implements Strategy {
    private JPanel panel;
    private JPanel frame;
    private LayoutManager layout;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton b5;
    JButton b6;
    JButton b7;
    JButton b8;

    public BoxStrategy(JPanel frame, JPanel panel, JButton b1, JButton b2, JButton b3,
                       JButton b4, JButton b5, JButton b6, JButton b7, JButton b8){
        this.panel = panel;
        this.frame = frame;
        this.layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
        this.b6 = b6;
        this.b7 = b7;
        this.b8 = b8;
        this.panel.setLayout(this.layout);
        this.setObjects();
    }

    @Override
    public void setObjects() {

        b1.setMaximumSize(new Dimension(Constants.GRID_B_WIDTH, Constants.GRID_BUTTON_HEIGHT));
        this.panel.add(b1);


        b2.setMaximumSize(new Dimension(Constants.GRID_B_WIDTH, Constants.GRID_BUTTON_HEIGHT));
        this.panel.add(b2);


        b3.setMaximumSize(new Dimension(Constants.GRID_B_WIDTH, Constants.GRID_BUTTON_HEIGHT));
        this.panel.add(b3);


        b4.setMaximumSize(new Dimension(Constants.GRID_B_WIDTH, Constants.GRID_BUTTON_HEIGHT));
        this.panel.add(b4);


        b5.setMaximumSize(new Dimension(Constants.GRID_B_WIDTH, Constants.GRID_BUTTON_HEIGHT));
        this.panel.add(b5);


        b6.setMaximumSize(new Dimension(Constants.GRID_B_WIDTH, Constants.GRID_BUTTON_HEIGHT));
        this.panel.add(b6);


        b7.setMaximumSize(new Dimension(Constants.GRID_B_WIDTH, Constants.GRID_BUTTON_HEIGHT));
        this.panel.add(b7);

        b8.setMaximumSize(new Dimension(Constants.GRID_B_WIDTH, Constants.GRID_BUTTON_HEIGHT));
        this.panel.add(b8);


        panel.setMaximumSize(new Dimension(Constants.GRID_B_WIDTH,Constants.GRID_B_HEIGHT));
    }
}
