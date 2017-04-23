package Probabilistas;

public abstract class Funciones {
	double linf,lsup; //[linf,lsup]
	double[] intervalo; //para los intevalos de confianza para normales
	double[] intervaloP; //para los intevalos de confianza para proporciones
	double maximo; //maximo que alcanza la función en el intervalo. Nos hace
	//falta para “encerrar” la función dentro de un rectangulo
	public Funciones(double inf, double sup){
		if (inf>sup){
			double x=inf;
			inf=sup;
			sup=x;
		}
		linf=inf;
		lsup=sup;
		intervalo=new double[2]; //para los intevalos de confianza para normales
		intervaloP=new double[2]; //para los intevalos de confianza para proporciones
		maximo=f(lsup()); //es el maximo que puede llegar a tener la funcion. Lo debe
		//de cambiar cada funcion
	}
	public void maximo(double m){ //maximo que alcanza la función en el intervalo. Cuanto
		maximo=m; //mas ajustado mejor
	}
	public double maximo(){
		return maximo;
	}
	public double linf(){
		return linf;
	}
	public double lsup(){
		return lsup;
	}
	public void set_linf(double inf){
		linf=inf;
	}
	public void set_lsup(double sup){
		lsup=sup;
	}
	public double amplitud(){
		return lsup()-linf();
	}
	public boolean vale(double x){
		boolean v=x>=linf() && x<=lsup();
		if (!v) System.out.println(x+" no pertenece a "+this);
		return v;
	}
	public abstract double f(double x);
	public abstract double area();//devuelve, si podemos hacerlo, el area integrando de verdad
	//(por ejemlo si f=x^3 la primitiva es x^4/4. Si no lo sabemos integrar devolvemos 0.
			public double areaRieman(int intervalos){
		double suma=0.0;
		double inc=amplitud()/intervalos;
		double x=linf();
		while(x<lsup()){
			suma=suma+Math.abs(f(x))*inc;
			x=x+inc;
		}
		return suma;
	}
	public double areaNumericoVM(int k){ //area probabilista según el teorema del valor medio
		double [] valores=new double[k]; //para calcular medias, cuasi varianzas e intervalos de
		//confianza
		double suma=0.0;
		for(int i=0;i<k;i++){
			double x=Math.random()*amplitud()+linf(); //valor aleatorio de x entre [linf, lsup]
			double y=f(x); //valor de y para esa x
			valores[i]=amplitud()*y; //Area=base amplitud por altura y
			suma=suma+valores[i];
		}
		intervaloConfianza(valores);
		return suma/k;
	}
	public void intervaloConfianza(double[] valores){
		double media=media(valores); //media
		double S=cuasiV(valores, media); //Cuasivarianza
		intervalo[0]=media-1.96*S/Math.sqrt(valores.length);
		intervalo[1]=media+1.96*S/Math.sqrt(valores.length);
	}
	public double intervaloInf(){
		return intervalo[0];
	}
	public double intervaloSup(){
		return intervalo[1];
	}
	public double media(double valores[]){
		double media=0;
		for(int x=0;x<valores.length;x++){
			media=media+valores[x];
		}
		return media/valores.length;
	}
	public double cuasiV(double[] valores, double media){
		double S=0;
		for(int x=0;x<valores.length;x++){
			S=S+Math.pow(valores[x]-media,2);
		}
		return Math.sqrt(S/(valores.length-1));
	}
	public double areaNumericoP(int k){
		//area probabilista lanzando k puntos A=casos favorables/casos posibles
		int buenos=0;
		for(int n=0;n<k;n++){
			double x=Math.random()*amplitud()+linf();
			double y=Math.random()*maximo;
			if (y<=f(x)) buenos++;
		}
		IntervaloConfProporciones((double)buenos/k,k);
		return amplitud()*maximo*(double)buenos/k; //buenos/k proporcion;
		//amplitud()*maximo total del area
		//maximo es el maximo, o un valor mayor, que alcanza la funcion en el intervalo
	}
	public void IntervaloConfProporciones(double p,int n){
		System.out.println("p="+p);
		intervaloP[0]=p-1.96*Math.sqrt(p*(1-p)/n);
		intervaloP[1]=p+1.96*Math.sqrt(p*(1-p)/n);
	}
	public double intervaloInfP0(){ //linf del intervalo de confianza para las proporciones
		return intervaloP[0];
	}
	public double intervaloSupP1(){ //lsup del intervalo de confianza para las proporciones
		return intervaloP[1];
	}
	public double intervaloInfP(){ //recalcula el intervaloInfP0 en funcion del area en que
		//hemos encerrado la función
		return amplitud()*maximo()*intervaloInfP0();
	}
	public double intervaloSupP(){
		return amplitud()*maximo()*intervaloSupP1();
	}
}