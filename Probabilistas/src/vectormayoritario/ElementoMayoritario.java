package vectormayoritario;

import java.util.Random;

public class ElementoMayoritario {
	public static int mayoritario;

	public static int mayoritario(int dim){
		int[] v=new int[dim];
		mayoritario=carga(v);//mete datos a v y devuelve el mayoritario
		pinta(v,mayoritario);//muestra v y las veces que aparece el mayoritario
		//Distintas formas de calcular el elemento mayoritario
		mayoritario2(v,100);
		mayoritario3(v,0.001);
		mayoritario4(v);
		return mayoritario;
	}
	/*
	* ensayos=numero máximo de intentos
	* Elige un elemento del vector al azar.
	* Mira si es el mayoritario y si no lo es vuelve a lanzarse,
	* Esto lo hace como máximo n-ensayos veces.
	* Puede fallar pero la probabilidad de que falle es 1/p^ensayos con p probabilidad
	* del elemento mayoritario.
	* Esto es: con 100 ensayos, por ejemplo, y considerando el caso mas desfavorable
	* que es que la probabilidad del mayoritario es 1/2 la probabilidad de que falle es
	1/2^100
	* */
	public static int mayoritario2(int[] v,int e){
		int ensayos=e;
		int candidato=-1;
		boolean encontrado=false;
		int k=0;
		while(!encontrado && k<ensayos){
			k++;
			candidato=v[new Random().nextInt(v.length)];
			encontrado=ocurrencias(v,candidato)>=v.length/2;
		}
		if (!encontrado){
			System.out.println("mayoritatio2: No encontrado el mayoritario ");
			candidato=-1;
		}
		else{
			System.out.println("Mayoritario2: el mayoritario era "+mayoritario+
					" y yo digo el "+candidato+" con "+k+" ensayos "+" de "+ensayos);
			encontrado=true;
		}
		return candidato;
	}
	//Ahora el numero de ensayos se calculan en función del error máximo que admitimos
	public static int mayoritario3(int[] v,double error){
		int ensayos=(int) (Math.log10(1/error)/Math.log10(2));//cambio de base a base 2
		System.out.print("Mayoritario3 con un error menor de "+error+" lo que implican "+ensayos+" ensayos -->\n ");
		int m=mayoritario2(v,ensayos);
		System.out.println(m+" sale en Mayoritario3");
		return m;
	}
	/*Con contraste de hipótesis
	 * H0: p>=0.5 H1: p<0.5 tomamos p=0.5, caso peor;
	 * n=v.length/10. Solo probamos la decima parte.
	 * z=0.5-1.654*raiz(0.5^2/n) al 95% -de ahí. el 1.654-
	 */
	public static int mayoritario4(int[] v){
		int candidato=-1;
		int k=0;
		while (candidato<0){
			k++;
			candidato=v[new Random().nextInt(v.length)];
			int n=v.length/10;
			double proporcion=(double)ocurrenciasLimitadas(v,candidato,n)/n;
			double z=0.5-1.645*Math.sqrt(0.5*0.5/n);
			System.out.println(k+" vez. Mayoritario4: "+candidato+" proporcion "+proporcion+" intervalo (z=" + z+ ", infinito) con n="+n);
			String s="Como p>=z lo tomo como mayoritario";
			if (proporcion<z){
				s="Como p<z NO lo tomo como mayoritario";
				candidato=-1;
			}
			System.out.println(s);
		}
		return candidato;
	}
	public static int pinta(int[] v,int mayoritario){
		System.out.println("v");
		int cont=0;
		for(int n=0;n<v.length;n++){
			System.out.print(v[n]+" ");
			if(v[n]==mayoritario) cont++;
		}
		System.out.println("el mayoritario es="+mayoritario+" aparece "+cont+" veces de "+v.length);
		return cont;
	}
	public static void main(String aaa[]) throws Exception{
		int c=0;
		do{
			try{
				c=10;
				if(c>0){
					int s=mayoritario(c); //lanza todos los métodos
					System.out.println("el mayoritario es="+s);
				}
			}//fin del try
			catch(Exception e){ System.out.println(e.toString());}
		}while(c>0);
	}//fin del main

	private static int carga(int[] v){
		int ocurrencias=0;
		while(ocurrencias<v.length/2) ocurrencias=new Random().nextInt(v.length);
		int mayoritario=1+new Random().nextInt(v.length);
		for(int n=0;n<ocurrencias;n++){
			int pos=new Random().nextInt(v.length);
			while (v[pos]!=0) pos=new Random().nextInt(v.length);
			v[pos]=mayoritario;
		}
		for(int n=0;n<v.length;n++){
			if(v[n]==0){//si no esta ocupado por el mayor
				do{
					v[n]=1+new Random().nextInt(v.length);
				}while(v[n]==mayoritario);
			}
		}
		return mayoritario;
	}//Devuelve si m es mayoritario en v
	public static boolean esMayoritario(int[] v,int m){
		return ocurrencias(v,m)>=v.length;
	}
	//Cuenta el número de veces que aparece m en v
	public static int ocurrencias(int[] v,int m){
		int cont=0;
		for(int n=0;n<v.length;n++){
			if(v[n]==m) cont++;
		}
		return cont;
	}
	//Devuelve el numero de veces que aparece m en n-veces que preguntamos
	public static int ocurrenciasLimitadas(int[] v,int m, int veces){
		int cont=0;
		for(int n=0;n<veces;n++){
			if(v[new Random().nextInt(v.length)]==m) cont++;
		}
		return cont;
	}
}//elementoMayoritario