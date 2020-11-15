package gui.graphics.components;

import gui.Spinner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel {
    private JLabel lXMin;
    private JLabel lXMax;
    private JLabel lYMin;
    private JLabel lYMax;
    private JSpinner sXMin;
    private JSpinner sXMax;
    private JSpinner sYMin;
    private JSpinner sYMax;
    private SpinnerNumberModel nmXMin;
    private SpinnerNumberModel nmXMax;
    private SpinnerNumberModel nmYMin;
    private SpinnerNumberModel nmYMax;
    private static int PREF = GroupLayout.PREFERRED_SIZE;
    private static int DEF  = GroupLayout.DEFAULT_SIZE;
    private Spinner spiner;


    public ControlPanel(){
        lXMin = new JLabel();
        lXMax = new JLabel();
        lYMin = new JLabel();
        lYMax = new JLabel();
        lXMin.setText("Xmin = ");
        lXMax.setText("Xmax = ");
        lYMin.setText("Ymin = ");
        lYMax.setText("Ymax = ");
        nmXMin = new SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1);
        nmXMax = new SpinnerNumberModel(5.0, -4.9, 100.0, 0.1);
        nmYMin = new SpinnerNumberModel(-5.0, -100.0, 4.9, 0.1);
        nmYMax = new SpinnerNumberModel(5.0, -4.9, 100.0, 0.1);
        sXMin = new JSpinner(nmXMin);
        sXMax = new JSpinner(nmXMax);
        sYMin = new JSpinner(nmYMin);
        sYMax = new JSpinner(nmYMax);

        sXMin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                nmXMax.setMinimum((Double)sXMin.getValue()+0.1);
                spiner.spinerValue((double)sXMin.getValue(),(double)sXMax.getValue(),
                        (double)sYMin.getValue(),(double)sYMax.getValue());
            }
        });

        sXMax.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                nmXMin.setMaximum((Double)sXMax.getValue()-0.1);
                spiner.spinerValue((double)sXMin.getValue(),(double)sXMax.getValue(),
                        (double)sYMin.getValue(),(double)sYMax.getValue());
            }
        });


        sYMin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                nmYMax.setMinimum((Double)sYMin.getValue()+0.1);
                spiner.spinerValue((double)sXMin.getValue(),(double)sXMax.getValue(),
                        (double)sYMin.getValue(),(double)sYMax.getValue());
            }
        });

        sYMax.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                nmYMin.setMaximum((Double)sYMax.getValue()-0.1);
                spiner.spinerValue((double)sXMin.getValue(),(double)sXMax.getValue(),
                        (double)sYMin.getValue(),(double)sYMax.getValue());
            }
        });


        GroupLayout gl = new GroupLayout(this);
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                        .addComponent(lXMin, PREF, PREF, PREF)
                        .addComponent(sXMin, PREF, PREF, PREF)
                        .addComponent(lXMax, PREF, PREF, PREF)
                        .addComponent(sXMax, PREF, PREF, PREF)
                )
                .addGap(8)
                .addGroup(
                        gl.createParallelGroup()
                        .addComponent(lYMin, PREF, PREF, PREF)
                        .addComponent(sYMin, PREF, PREF, PREF)
                        .addComponent(lYMax, PREF, PREF, PREF)
                        .addComponent(sYMax, PREF, PREF, PREF)
                )
                .addGap(4)
        );

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                        .addComponent(lXMin, PREF, PREF, PREF)
                        .addComponent(lYMin, PREF, PREF, PREF)
                )
                .addGap(2)
                .addGroup(
                        gl.createParallelGroup()
                        .addComponent(sXMin, DEF, DEF, DEF)
                        .addComponent(sYMin, DEF, DEF, DEF)
                )
                .addGap(8, 8, Integer.MAX_VALUE)
                .addGroup(
                        gl.createParallelGroup()
                        .addComponent(lXMax, PREF, PREF, PREF)
                        .addComponent(lYMax, PREF, PREF, PREF)
                )
                .addGap(2)
                .addGroup(
                        gl.createParallelGroup()
                        .addComponent(sXMax, DEF, DEF, DEF)
                        .addComponent(sYMax, DEF, DEF, DEF)
                )
                .addGap(4)
        );
        setLayout(gl);
    }

    public void addIsc(Spinner e){
         spiner = e;
    }
}
