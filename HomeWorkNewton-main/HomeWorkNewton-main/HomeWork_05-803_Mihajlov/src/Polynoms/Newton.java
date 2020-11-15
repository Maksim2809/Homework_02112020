package Polynoms;

import java.util.HashMap;

public class Newton extends Polynom{
    private Polynom[] p = null;
    private HashMap<Double, Double> v;
    private Double[] key = new Double[0];

    public Polynom polin;
    private Polynom pp =new Polynom(new double[]{1.0});

    private int a = 0;
    public Newton(HashMap<Double, Double> v){
        key = v.keySet().toArray(key);
        this.v = v;
        a = v.size();
        p = new Polynom[a];

        ListPolynom(a);
        polin = Sum(p);
        this.coef = polin.coef;
    }

    private void ListPolynom(int a)
    {
        p[0] = new Polynom(new double[]{v.get(key[0])});

        for (int i=1;i<a;i++)
        {
            pp = pp.times(new Polynom(new double[]{-key[i-1], 1.0}));
            p[i] = pp.times(Coef(i));
        }
    }

    private double Coef(int index)
    {
        double aa = 0;
        double sm = 0;
         for(int i=0;i<index+1;i++)
        {
            aa=v.get(key[i]);
            for (int j = 0;j<index+1;j++)
            {
                if (i!=j) aa=aa/(key[i] - key[j]);
            }
            sm+=aa;
        }
        return sm;
    }

    private Polynom Sum(Polynom[] pol)
    {
        Polynom ppp = new Polynom(new double[]{0.0});
        for (Polynom id:
             p) {
            ppp=ppp.plus(id);
        }
        return ppp;
    }

    public void AddUzel(double x, double y)
    {
        //добавляю точку
        v.put(x,y);

        //увеличиваю значение, характеризующее показатель, степени на 1
        a+=1;

        //пустой массив для новыйх коэффицентов
        Double[] arr = new Double[a];

        for (int i =0;i<a;i++ )
        {
            if(i<a-1) {
            arr[i] = key[i];
            }
            else {
                arr[i]=x;
            }

        }
        //изменяю массив значений иксов из поля класса
        key = arr;

        // умножаю (X-X0)...(X-X_[a-1]) на (X - x), где x первый аргумент функции
        pp = pp.times(new Polynom(new double[]{-key[a-2], 1.0}));

        //это будет значение функции
        double f_x = 0;

        //коэффицент перtд новой старшей степенью
        double sm = 0;

        //вспомогательный коэффицент для подсчёта "sm"
        double w = 1;

        //считаю sm
        for(int i=0;i<a;i++)
        {
            f_x=v.get(key[i]);
            for (int j = 0;j<a;j++)
            {
                if (i!=j) w = w*(key[i] - key[j]);
            }
            sm+=f_x/w;
            w=1;
        }
        polin = polin.plus(pp.times(sm));
        this.coef = polin.coef;
    }
    public boolean proverka(double x){
        if(v.containsKey(x)) return true;
        else return false;
    }
}
