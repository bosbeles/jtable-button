package com.bsbls.deneme.table;

import com.bsbls.home.gui.test.GuiTester;
import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FontLabel extends JLabel {


    private transient IconCode iconCode;
    private int size = 12;

    public FontLabel(IconCode iconCode) {
        super();
        setIconCode(iconCode);
    }

    public static void main(String[] args) {
        GuiTester.test("Light", f -> {
            JComboBox<IconCode> combo = new JComboBox<>(FontAwesome.values());
            JSlider slider = new JSlider(0, 150, 20);


            IconFontSwing.register(FontAwesome.getIconFont());
            FontLabel comp = new FontLabel(FontAwesome.COG);
            comp.setBackground(Color.BLACK);
            comp.setForeground(Color.RED);
            comp.setSize(slider.getValue());


            combo.addItemListener(e -> {
                IconCode item = (IconCode) e.getItem();
                comp.setIconCode(item);
            });
            slider.addChangeListener(e -> {
                int value = slider.getValue();
                comp.setSize(value);
                f.pack();
            });

            JPanel north = new JPanel();
            north.add(combo);
            north.add(slider);
            JPanel center = new JPanel();
            center.add(comp);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(north, BorderLayout.NORTH);
            panel.add(center, BorderLayout.CENTER);

            Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> EventQueue.invokeLater(() -> {
                int selectedIndex = combo.getSelectedIndex();
                combo.setSelectedIndex((selectedIndex + 1) % combo.getItemCount());
            }), 1000, 100, TimeUnit.MILLISECONDS);

            return panel;
        });
    }

    public void setIconCode(IconCode iconCode) {
        this.iconCode = iconCode;
        Icon icon = IconFontSwing.buildIcon(iconCode, size, this.getForeground());
        setIcon(icon);
    }

    public void setSize(int size) {
        this.size = size;
        setIconCode(iconCode);
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (iconCode != null) {
            setIconCode(iconCode);
        }
    }
}
