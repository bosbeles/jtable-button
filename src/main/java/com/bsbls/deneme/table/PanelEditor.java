package com.bsbls.deneme.table;

import jiconfont.icons.font_awesome.FontAwesome;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;

public class PanelEditor extends AbstractCellEditor
        implements TableCellEditor, InstantEditingSupportUtils.InstantEditingSupport {

    private JPanel panel;
    private JButton send;
    private JButton settings;
    private JButton delete;
    private int currentRow;
    private JTable table;
    private Color primary = Color.BLACK;
    private Color secondary = Color.WHITE;


    public PanelEditor() {


        send = new RolloverButton(FontAwesome.PLAY, 20);
        settings = new RolloverButton(FontAwesome.COG, 20);
        delete = new RolloverButton(FontAwesome.TIMES, 20);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();


        int space = 5;
        // Flat LAF'ta renderer ile editor arasında kayma oluyor.
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