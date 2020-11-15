package gui.graphics;

import gui.graphics.coordinatesystem.CartesianScreenPlane;
import gui.graphics.coordinatesystem.Converter;
import gui.graphics.MouseClick;

import Polynoms.*;

import java.util.HashMap;
import java.awt.*;
import java.util.LinkedHashMap;

public class CartesianPainter extends Painter{
    CartesianScreenPlane cartesianScreenPlane ;
    HashMap<Double,Double> dots = new HashMap<Double, Double>();
    Newton nw = null;
    Newton2 p = null;
    static final int EPS = 5;
    boolean rigth ;

    public CartesianPainter(CartesianScreenPlane csPlane){
        cartesianScreenPlane = csPlane;
    }

    public void ValueMaus(int x, int y, boolean l){
        rigth = l;
        if(rigth){
            for (double X:
                 dots.keySet()) {
                if(Math.abs(Converter.xCrt2Scr(X,cartesianScreenPlane)-x)<EPS){
                    if(Math.abs(Converter.yCrt2Scr(dots.get(X),cartesianScreenPlane)-y)<EPS){
                        dots.remove(X);
                        if( dots.size()>0){
                            nw = new Newton(dots);
                           // p = new Newton2(dots);
                            break;
                        }

                    }
                }
            }
        }
        else {
            dots.put(Converter.xScr2Crt(x, cartesianScreenPlane), Converter.yScr2Crt(y, cartesianScreenPlane));
            if (nw == null) {
                nw = new Newton(dots);
            } else {
                //nw = new Newton(dots);

                    nw.AddUzel(Converter.xScr2Crt(x, cartesianScreenPlane), Converter.yScr2Crt(y, cartesianScreenPlane));

            }

            /*if (p == null) {
                p = new Newton2(new LinkedHashMap<Double, Double>() {{
                    put(Converter.xScr2Crt(x, cartesianScreenPlane),
                            Converter.yScr2Crt(y, cartesianScreenPlane));
                }}
                );
            } else {
                p.add(Converter.xScr2Crt(x, cartesianScreenPlane),
                        Converter.yScr2Crt(y, cartesianScreenPlane));
            }
            dots.put(Converter.xScr2Crt(x, cartesianScreenPlane), Converter.yScr2Crt(y, cartesianScreenPlane));

             */
        }

    }

    public void addDot(int x, int y){
        if (p == null){
            p = new Newton2(new LinkedHashMap<Double, Double>(){{put(Converter.xScr2Crt(x,cartesianScreenPlane),
                    Converter.yScr2Crt(y,cartesianScreenPlane));}});
        }
        else {
            p.add(Converter.xScr2Crt(x,cartesianScreenPlane),
                    Converter.yScr2Crt(y,cartesianScreenPlane));
        }
        dots.put(Converter.xScr2Crt(x,cartesianScreenPlane),Converter.yScr2Crt(y,cartesianScreenPlane));
    }

    @Override
    public void paint(Graphics g) {
        drawSetka(g);
        drawAxis(g);
        if (dots.size()>0) {
            if (nw == null) return;
            else {
                drawPolynom(g, nw);
            }
        }
        //drawPolynom2(g,p);
    }

    private void drawAxis(Graphics g){
        g.setColor(Color.BLUE);

        if (cartesianScreenPlane.getXMin()>0){
            g.drawLine(2,
                    0,
                    2,
                    cartesianScreenPlane.getHeight());
            for (int i = (int)cartesianScreenPlane.getYMin();i<cartesianScreenPlane.getYMax();i++){
                g.drawString(""+i,
                        15,
                        Converter.yCrt2Scr(i,cartesianScreenPlane));
            }
        }
        else{
            g.drawLine(Converter.xCrt2Scr(0,cartesianScreenPlane),
                    0,
                    Converter.xCrt2Scr(0,cartesianScreenPlane),
                    cartesianScreenPlane.getHeight());
            for (int i = (int)cartesianScreenPlane.getYMin(); i<cartesianScreenPlane.getYMax();i++){
                g.drawString(""+i,
                        Converter.xCrt2Scr(0, cartesianScreenPlane)+10,
                        Converter.yCrt2Scr(i,cartesianScreenPlane));
            }
        }
        if (cartesianScreenPlane.getXMax()<0){
            g.drawLine(cartesianScreenPlane.getWidth()-2,
                    0,
                    cartesianScreenPlane.getWidth()-2,
                    cartesianScreenPlane.getHeight()
            );
        }

        if (cartesianScreenPlane.getYMin()>0){
            g.drawLine(0,cartesianScreenPlane.getHeight()-2,
                    cartesianScreenPlane.getWidth(),
                    cartesianScreenPlane.getHeight()-2 );
            for (int i = (int)cartesianScreenPlane.getXMin(); i<cartesianScreenPlane.getXMax();i++){
                g.drawString(""+i,
                        Converter.xCrt2Scr(i, cartesianScreenPlane),
                        cartesianScreenPlane.getHeight()-7);
            }
            if ((int)cartesianScreenPlane.getXMax()==cartesianScreenPlane.getXMax()){
                g.drawString(""+(int)cartesianScreenPlane.getXMax(),
                        cartesianScreenPlane.getWidth()-3,
                        cartesianScreenPlane.getHeight()-7);
            }
            else{
                g.drawString(""+(int)cartesianScreenPlane.getXMax(),
                        Converter.xCrt2Scr((int)cartesianScreenPlane.getXMax(), cartesianScreenPlane),
                        cartesianScreenPlane.getHeight()-7);
            }
        }
        else{
            g.drawLine(0,Converter.yCrt2Scr(0,
                    cartesianScreenPlane),
                    cartesianScreenPlane.getWidth(),
                    Converter.yCrt2Scr(0,cartesianScreenPlane) );
            for (int i = (int)cartesianScreenPlane.getXMin(); i<cartesianScreenPlane.getXMax();i++){
                g.drawString(""+i,
                        Converter.xCrt2Scr(i, cartesianScreenPlane),
                        Converter.yCrt2Scr(0,cartesianScreenPlane)-5);
            }
            if ((int)cartesianScreenPlane.getXMax()==cartesianScreenPlane.getXMax()){
             g.drawString(""+(int)cartesianScreenPlane.getXMax(),
                        cartesianScreenPlane.getWidth()-3,
                        Converter.yCrt2Scr(0,cartesianScreenPlane)-5);
            }
            else{
                g.drawString(""+(int)cartesianScreenPlane.getXMax(),
                        Converter.xCrt2Scr((int)cartesianScreenPlane.getXMax(), cartesianScreenPlane),
                        Converter.yCrt2Scr(0,cartesianScreenPlane)-5);
            }
        }
        if (cartesianScreenPlane.getYMax()<0){
            g.drawLine(0,
                    2,
                    cartesianScreenPlane.getWidth(),
                    2);

            for (int i = (int)cartesianScreenPlane.getXMin(); i<cartesianScreenPlane.getXMax();i++){
                g.drawString(""+i,
                        Converter.xCrt2Scr(i, cartesianScreenPlane),
                        10);
            }
            if ((int)cartesianScreenPlane.getXMax()==cartesianScreenPlane.getXMax()){
                g.drawString(""+(int)cartesianScreenPlane.getXMax(),
                        cartesianScreenPlane.getWidth()-3,
                        10);
            }
            else{
                g.drawString(""+(int)cartesianScreenPlane.getXMax(),
                        Converter.xCrt2Scr((int)cartesianScreenPlane.getXMax(), cartesianScreenPlane),
                        10);
            }
        }

    }

    private void drawSetka(Graphics g){
        g.setColor(Color.BLACK);
        if (cartesianScreenPlane.getXMin()<0){
            for (int i =0; i>cartesianScreenPlane.getXMin();i-=1){
                g.drawLine(Converter.xCrt2Scr(i,cartesianScreenPlane),
                        0,
                        Converter.xCrt2Scr(i,cartesianScreenPlane),
                        cartesianScreenPlane.getHeight());
            }
            for (int i =0; i<=cartesianScreenPlane.getXMax();i+=1){
                g.drawLine(Converter.xCrt2Scr(i,cartesianScreenPlane),
                        0,
                        Converter.xCrt2Scr(i,cartesianScreenPlane),
                        cartesianScreenPlane.getHeight());
            }
        }
        else{
            for (int i =(int)cartesianScreenPlane.getXMin(); i<=cartesianScreenPlane.getXMax();i++){
                g.drawLine(Converter.xCrt2Scr(i,cartesianScreenPlane),
                        0,
                        Converter.xCrt2Scr(i,cartesianScreenPlane),
                        cartesianScreenPlane.getHeight());
            }
        }

        if(cartesianScreenPlane.getYMin()<0){
            for (int i =0;i>cartesianScreenPlane.getYMin();i=i-1){
                g.drawLine(0,
                        Converter.yCrt2Scr(i,cartesianScreenPlane),
                        cartesianScreenPlane.getWidth(),
                        Converter.yCrt2Scr(i,cartesianScreenPlane));
            }
            for (int i = 0; i<cartesianScreenPlane.getYMax();i++){
                g.drawLine(0,
                        Converter.yCrt2Scr(i,cartesianScreenPlane),
                        cartesianScreenPlane.getWidth(),
                        Converter.yCrt2Scr(i,cartesianScreenPlane));
            }
        }
        else{
            for(int i =(int)cartesianScreenPlane.getYMin();i< cartesianScreenPlane.getYMax();i++){
                g.drawLine(0,
                        Converter.yCrt2Scr(i,cartesianScreenPlane),
                        cartesianScreenPlane.getWidth(),
                        Converter.yCrt2Scr(i,cartesianScreenPlane));
            }
        }
    }

    private void drawPolynom(Graphics g, Newton newton){
        g.setColor(Color.RED);
        if(newton == null) return;;
        for (int i = cartesianScreenPlane.getWidth()-1;i>1;i=i-1) {
            int y1 = Converter.yCrt2Scr(newton.invoke(Converter.xScr2Crt(i,cartesianScreenPlane)),cartesianScreenPlane);
            int y2 = Converter.yCrt2Scr(newton.invoke(Converter.xScr2Crt(i-1,cartesianScreenPlane)),cartesianScreenPlane);
            g.drawLine(i,
                    y1,
                    i-1,
                    y2);
        }
        g.setColor(Color.BLACK);
        for (double id
                : dots.keySet()){
            g.fillOval(Converter.xCrt2Scr(id,cartesianScreenPlane)-3,
                    Converter.yCrt2Scr(dots.get(id),cartesianScreenPlane)-3,
                    6
                    ,6
            );
        }
    }

    private void drawPolynom2(Graphics g, Newton2 p){
        if (p == null) return;
        g.setColor(Color.BLUE);
        for (var i = 0; i<cartesianScreenPlane.getWidth()-1; i++) {
            g.drawLine(i,
                    Converter.yCrt2Scr(p.invoke(Converter.xScr2Crt(i, cartesianScreenPlane)), cartesianScreenPlane),
                    i + 1
                    , Converter.yCrt2Scr(p.invoke(Converter.xScr2Crt(i + 1, cartesianScreenPlane)), cartesianScreenPlane));
        }
        g.setColor(Color.RED);
    }

}
