package Probabilistas;

public class xdivsenx extends Funciones{
	public xdivsenx(double inf, double sup){
		super(inf,sup);
		maximo(f(lsup()));
	}
	public double f(double x){
		if (vale(x))
			if (x==0)
				return 1;
			else
				return x/Math.sin(x);
		else
			return 0;
	}
	public double area(){
		return 0;//no lo se
	}
	public String toString(){
		return "funcion=x/Senx "+super.toString();
	}
}