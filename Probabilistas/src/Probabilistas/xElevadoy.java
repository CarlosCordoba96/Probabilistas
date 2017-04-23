package Probabilistas;
public class xElevadoy extends Funciones{
	int potencia;
	public xElevadoy(int p,double inf, double sup){
		super(inf,sup);
		if (p<0) p=0;
		potencia=p;
		maximo(f(lsup())); //es creciente
	}
	public double f(double x){
		if (vale(x)) return Math.pow(x,potencia);
		else return 0;
	}
	public double area(){
		return (Math.pow(lsup(),potencia+1)-Math.pow(linf(),potencia+1))/(potencia+1);
	}
	public String toString(){
		return "funcion=x^"+potencia+super.toString();
	}
}