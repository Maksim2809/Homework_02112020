package gui.graphics.components;

import javax.swing.*;
import java.awt.*;
import gui.graphics.Painter;
import gui.graphics.coordinatesystem.Converter;
import gui.graphics.MouseClick;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;


public class GraphicsPanel extends JPanel {
    Painter p = null;
    MouseClick m;

    boolean mouse = true;

    public void addPainter(Painter p){
        this.p = p;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(p!=null) p.paint(g);
        if(mouse){
            mouse = false;
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    m.getDots(e.getX(), e.getY(), true  ? e.getButton()==MouseEvent.BUTTON3 : false);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

    }
    public void addMausEvent(MouseClick m){
        this.m = m;
    }
}