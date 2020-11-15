package gui;

import gui.graphics.CartesianPainter;
import gui.Spinner;
import gui.graphics.MouseClick;
import gui.graphics.components.ControlPanel;
import gui.graphics.components.GraphicsPanel;
import gui.graphics.coordinatesystem.CartesianScreenPlane;

import Polynoms.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


public class MainWindow extends JFrame implements Spinner, MouseClick{
    GraphicsPanel mainPanel;
    ControlPanel controlPanel;
    CartesianScreenPlane csPlane ;
    CartesianPainter p ;

    int xx = -1;
    int yy = -1;

    Newton nw = null;

    static final Dimension MIN_SIZE = new Dimension(450, 350);
    static final Dimension MIN_FRAME_SIZE = new Dimension(600, 500);

    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(MIN_FRAME_SIZE);
        setTitle("Полиномы");

        mainPanel = new GraphicsPanel();
        controlPanel = new ControlPanel();

        mainPanel.setBackground(Color.WHITE);

        controlPanel.setBorder(new EtchedBorder());

        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addComponent(mainPanel, (int)(MIN_SIZE.height*0.8), MIN_SIZE.height, GroupLayout.DEFAULT_SIZE)
                .addGap(4)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(4)
        );
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                            .addComponent(mainPanel, MIN_SIZE.width, MIN_SIZE.width, GroupLayout.DEFAULT_SIZE)
                            .addComponent(controlPanel, MIN_SIZE.width, MIN_SIZE.width, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(4)
        );
        pack();
                csPlane = new CartesianScreenPlane(
                mainPanel.getWidth(),
                mainPanel.getHeight(),
                -5,5,
                -5,5
        );
        mainPanel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                csPlane.setSize(mainPanel.getWidth(),mainPanel.getHeight());
                mainPanel.repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        p = new CartesianPainter(csPlane);
        controlPanel.addIsc(this);
        mainPanel.addMausEvent(this);
        mainPanel.addPainter(p);
    }
    @Override
    public void spinerValue(double xmin, double xmax, double ymin, double ymax){
        csPlane.valueSpiner(xmin,xmax,ymin,ymax);
        mainPanel.repaint();
    }

    @Override
    public void getDots(int x, int y, boolean left) {
        p.ValueMaus(x,y,left);
        mainPanel.repaint();

    }
}
