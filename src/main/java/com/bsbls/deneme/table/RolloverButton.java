package com.bsbls.deneme.table;

import jiconfont.IconCode;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RolloverButton extends JButton {

    private Color primary = Color.BLACK;
    private Color secondary = Color.WHITE;

    public RolloverButton(IconCode iconCode, int size) {
        Icon primaryIcon = IconFontSwing.buildIcon(iconCode, size, primary);
        Icon secondaryIcon = IconFontSwing.buildIcon(iconCode, size, secondary);

        setIcon(primaryIcon);
        setRolloverIcon(secondaryIcon);
        setRolloverEnabled(true);
        setFocusable(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFocusable(false);
        setRequestFocusEnabled(false);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                setIcon(secondaryIcon);
            }
        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                setIcon(primaryIcon);
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                if (RolloverButton.this.contains(e.getPoint())) {
                    setIcon(secondaryIcon);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(secondaryIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(primaryIcon);
            }
        });
    }
}
