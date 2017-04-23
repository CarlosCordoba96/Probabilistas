package Probabilistas;

public class xcosx extends Funciones{
	public xcosx(double inf, double sup){
		super(inf,sup);
		maximo(lsup());//va bien si lsup<1
	}
	public double f(double x){
		if (vale(x)) return x*Math.cos(x);
		else return 0;
	}
	public double area(){
		return lsup()*Math.sin(lsup())+Math.cos(lsup())-(linf()*Math.sin(linf())+Math.cos(linf()));
	}
	public String toString(){
		return "funcion=xCosx"+super.toString();
	}
}
