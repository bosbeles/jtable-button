package com.bsbls.deneme.table;

import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class PanelRenderer implements TableCellRenderer {

    private JPanel panel;
    private JButton send;
    private JButton settings;
    private JButton delete;

    public PanelRenderer() {
        send = createButton(FontAwesome.PLAY);
        settings = createButton(FontAwesome.COG);
        delete = createButton(FontAwesome.TIMES);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 0, 3, 5);

        Insets margin = new Insets(0, 0, 0, 0);
        send.setMargin(margin);
        settings.setMargin(margin);
        delete.setMargin(margin);

        gc.gridx++;
        panel.add(send, gc);
        gc.gridx++;
        panel.add(settings, gc);
        gc.gridx++;
        panel.add(delete, gc);

    }


    private JButton createButton(IconCode iconCode) {
        JButton button = new JButton();
        button.setIcon(IconFontSwing.buildIcon(iconCode, 20, Color.BLACK));
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setRequestFocusEnabled(false);
        return button;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            panel.setBackground(table.getSelectionBackground());
        } else {
            panel.setBackground(table.getBackground());
        }
        return panel;
    }
}
