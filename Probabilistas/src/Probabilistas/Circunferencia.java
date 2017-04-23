package Probabilistas;

public class Circunferencia extends Funciones{
	double r;//radio
	public Circunferencia(double radio){
		super(-radio,radio);
		r=radio;
		maximo(r);
	}
	public double radio(){return r;}
	public Circunferencia(){////en [0,1] r=1 y centro (0,0)
		this(1.0);
	}

	public double f(double x){
		if (vale(x))
			return Math.sqrt(r*r-x*x);
		else 
			return 0;
	}
	public double area(){
		return Math.PI*r*r;
	}
	public String toString(){
		return "Circunferencia de radio="+r;
	}
	public double areaRieman(int intervalos){
		return 2*super.areaRieman(intervalos);
	}
	//area probabilista según el teoreama del valor medio. k el numero de medidas
	public double areaNumericoVM(int k){
		return 2*super.areaNumericoVM(k);//*2 es para la parte de abajo
	}
	public double intervaloInf(){
		return 2*super.intervaloInf();//*2 es para la parte de abajo
	}
	public double intervaloSup(){
		return 2*super.intervaloSup();
	}
	//area probabilista lanzando k puntos
	// A=casos favorables/casos posibles
	public double areaNumericoP(int k){
		return 2*super.areaNumericoP(k);//*2 es para la parte de abajo
	}
	public double intervaloInfP(){
		return super.intervaloInfP()*2;//el ultimo *2 es para la parte de abajo
	}
	public double intervaloSupP(){
		return super.intervaloSupP()*2;//el ultimo *2 es para la parte de abajo
	}





}