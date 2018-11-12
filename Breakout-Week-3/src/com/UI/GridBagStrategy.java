package com.UI;

import javax.swing.*;

import com.constants.Constants;

import java.awt.*;

public class GridBagStrategy implements Strategy {
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

    public GridBagStrategy(JPanel frame, JPanel panel, JButton b1, JButton b2, JButton b3,
                        JButton b4, JButton b5, JButton b6, JButton b7, JButton b8){
        this.panel = panel;
        this.frame = frame;
        this.layout = new GridBagLayout();
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

        GridBagConstraints cst = new GridBagConstraints();

        // add button 1 to the panel
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 0;
        panel.add(b1,cst);

        // add button 2 to the panel
        cst.fill = GridBagConstraints.HORIZONTAL;

        cst.gridx = 1;
        cst.gridy = 0;
        panel.add(b2);
        // add button 3 to the panel

        cst.gridx = 0;
        cst.gridy = 1;
        panel.add(b3, cst);
        // add button 4 to the panel

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridwidth = 1;
        cst.gridx = 1;
        cst.gridy = 1;
        panel.add(b4,cst);

        // add button 5 to the panel
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridwidth = 1;
        cst.gridy = 2;       //third row

        panel.add(b5,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridwidth = 1;
        cst.gridy = 2;       //2 row

        panel.add(b6,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridwidth = 2;
        cst.gridy = 3;       //third row

        panel.add(b7,cst);

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridwidth = 2;
        cst.gridy = 4;       //third row

        panel.add(b8,cst);


        panel.setMaximumSize(new Dimension(Constants.GRID_WIDTH,Constants.GRID_HEIGHT));
        //frame.add(panel);
        //frame.setVisible(true);
    }
}
