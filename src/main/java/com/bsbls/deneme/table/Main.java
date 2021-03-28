package com.bsbls.deneme.table;

import com.bsbls.home.gui.test.GuiTester;
import com.formdev.flatlaf.extras.FlatInspector;
import com.formdev.flatlaf.extras.FlatUIDefaultsInspector;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {


    public static void main(String[] args) {
        // Nimbus, Light, Dark, Metal
        GuiTester.test("Light", f -> {
            FlatInspector.install("ctrl shift alt X");
            FlatUIDefaultsInspector.install("ctrl shift alt Y");
            IconFontSwing.register(FontAwesome.getIconFont());

            JPanel panel = new JPanel(new BorderLayout());
            JTable table = new JTable();
            table.setRowHeight(32);
            table.setFillsViewportHeight(true);
            table.setModel(new DefaultTableModel(new Object[][]{
                    {"A2", "B1", "Send"},
                    {"A11", "B2", "Send"},
                    {"A3", "B3", "Send"},
                    {"A1", "B3", "Send"},
                    {"A4", "B3", "Send"},
                    {"1.json", "B3", "Send"},
                    {"2.json", "B3", "Send"},
                    {"3.json", "B3", "Send"},
                    {"4.json", "B3", "Send"},
                    {"32.json", "B3", "Send"},
                    {"111.json", "B3", "Send"},
            }, new Object[]{"A", "B", "C"}) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 2;
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    if (columnIndex == 2) {
                        return JPanel.class;
                    }
                    return super.getColumnClass(columnIndex);
                }
            });


            Action action = new AbstractAction("Send") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.convertRowIndexToModel(table.getEditingRow());
                    System.out.println(row);
                }
            };
            JButton button = new JButton(action);
            table.setDefaultEditor(JPanel.class, new PanelEditor());
            table.setDefaultRenderer(JPanel.class, new PanelRenderer());
            panel.add(new JScrollPane(table), BorderLayout.CENTER);
            InstantEditingSupportUtils.install(table);
            return panel;
        });
    }
}
