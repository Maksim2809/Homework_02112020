package gui.graphics.coordinatesystem;

public class CartesianScreenPlane{

    private int realWidth;
    public int getWidth(){
        return realWidth-1;
    }
    private int realHeight;
    public int getHeight(){
        return realHeight-1;
    }

    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    public double getXMin() {
        return xMin;
    }

    public double getXMax() {
        return xMax;
    }

    public double getYMin() {
        return yMin;
    }

    public double getYMax() {
        return yMax;
    }

    public double getXDen() {
        return getWidth() / (xMax - xMin);
    }

    public double getYDen() {
        return getHeight() / (yMax - yMin);
    }

    public CartesianScreenPlane(
        int realWidth,
        int realHeight,
        double xMin,
        double xMax,
        double yMin,
        double yMax) {
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }
    public void setSize(int w, int h){
        realHeight = h;
        realWidth = w;
    }
    public void valueSpiner(double xMin, double xMax, double yMin, double yMax){
        this.xMin = xMin;this.xMax = xMax;this.yMin = yMin;this.yMax = yMax;
    }
}