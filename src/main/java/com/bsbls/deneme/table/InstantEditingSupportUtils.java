package com.bsbls.deneme.table;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InstantEditingSupportUtils {


    public interface InstantEditingSupport {

    }

    private InstantEditingSupportUtils() {
    }


    public static void install(JTable table) {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                if (isOutOfBounds(table, row, col)) return;
                TableCellEditor cellEditor = table.getCellEditor(row, col);
                if (cellEditor instanceof InstantEditingSupport) {
                    table.editCellAt(row, col);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                TableCellEditor cellEditor = table.getCellEditor();
                if (cellEditor instanceof InstantEditingSupport && isOutOfBounds(table, row, col)) {
                    cellEditor.stopCellEditing();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseEntered(e);
            }
        };

        table.addMouseListener(mouseAdapter);
        table.addMouseMotionListener(mouseAdapter);
    }

    private static boolean isOutOfBounds(JTable table, int row, int col) {
        return row < 0 || col < 0 || table.getRowCount() <= row;
    }
}
