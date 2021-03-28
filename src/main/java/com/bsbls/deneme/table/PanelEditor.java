package com.bsbls.deneme.table;

import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;

public class PanelEditor extends AbstractCellEditor
        implements TableCellEditor {

    private JPanel panel;
    private JButton send;
    private JButton settings;
    private JButton delete;
    private int currentRow;
    private JTable table;


    public PanelEditor() {

        send = createButton(FontAwesome.PLAY);
        settings = createButton(FontAwesome.COG);
        delete = createButton(FontAwesome.TIMES);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        int space = 5;
        if (UIManager.getLookAndFeel().getName().contains("Flat")) {
            space = 6;
        }
        gc.insets = new Insets(4, 0, 3, space);

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

        send.addActionListener(e -> {
            selectRow();
            System.out.println("Action " + Instant.now());

        });

        send.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                send.setIcon(IconFontSwing.buildIcon(FontAwesome.PLAY, 20, Color.WHITE));
            }

        });

        send.setRolloverEnabled(false);
        send.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                send.setIcon(IconFontSwing.buildIcon(FontAwesome.PLAY, 20, Color.BLACK));
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                if (send.contains(e.getPoint())) {
                    send.setIcon(IconFontSwing.buildIcon(FontAwesome.PLAY, 20, Color.WHITE));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                send.setIcon(IconFontSwing.buildIcon(FontAwesome.PLAY, 20, Color.WHITE));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                send.setIcon(IconFontSwing.buildIcon(FontAwesome.PLAY, 20, Color.BLACK));
            }
        });

        settings.addActionListener(e -> {
            selectRow();
            Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
            Window windowAncestor = SwingUtilities.getWindowAncestor(settings);
            JDialog dialog = new JDialog(windowAncestor, Dialog.ModalityType.DOCUMENT_MODAL);
            dialog.getContentPane().add(new JButton("Deneme"));
            dialog.pack();
            dialog.setLocationRelativeTo(windowAncestor);
            dialog.setVisible(true);
            dialog.dispose();
            focusOwner.requestFocusInWindow();
        });

        delete.addActionListener(e -> {
            selectRow();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            this.stopCellEditing();
            model.removeRow(table.convertRowIndexToModel(currentRow));
            selectRow();
        });


        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                TableCellEditor cellEditor = table.getCellEditor();
                if (cellEditor != null && !panel.contains(e.getPoint())) {
                    cellEditor.stopCellEditing();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                selectRow();
            }
        });
    }

    private void selectRow() {
        if (currentRow < table.getRowCount()) {
            table.getSelectionModel().setSelectionInterval(currentRow, currentRow);
        }
    }

    private JButton createButton(IconCode iconCode) {
        JButton button = new JButton();
        button.setIcon(IconFontSwing.buildIcon(iconCode, 20, Color.BLACK));
        button.setRolloverIcon(IconFontSwing.buildIcon(iconCode, 20, Color.WHITE));
        button.setRolloverEnabled(true);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setRequestFocusEnabled(false);
        return button;
    }


    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        panel.setBackground(table.getSelectionBackground());

        this.currentRow = row;
        this.table = table;

        return panel;
    }


    @Override
    public Object getCellEditorValue() {
        return "";
    }
}