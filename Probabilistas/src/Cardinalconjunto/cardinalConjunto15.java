package Cardinalconjunto;

import java.util.LinkedList;
import java.util.Random;

/*
 * //un conjunto tiene unos elementos distintos que desconocemos cuales son.
 * Puede ser U1=R U2=R,A (los ponemos U=1, U2=1,3..
 *pero como maximo maxUrnas (que será el máximo numero de elementos distintos).
 *Contamos los elementos que se repiten. Funciona bien para un maxUrnas<50
 */
public class cardinalConjunto15 {
	static double umbral=0.9;//cuando se alcanza esta probabilidad se termina
	static int maxUrnas=100;
	public static int conjuntos(int hay){
		//hay es numero de bolas. Sera la urna y es lo que hay que calcular
		int sol=0;
		double solucion=0.0;
		LinkedList<Integer> valen =new LinkedList<Integer> ();
		for(int n=1;n<=maxUrnas;n++) valen.add(n);
		//Originalmente tiene todas las Urnas. Se van a ir quitando los que han salido alguna
		//vez con
		//probabilidad 0
		System.out.println("hay "+maxUrnas+" urnas y de bolas distintas de verdad hay "+hay+" pero no lo se");
				int bolas=0;//bolas que saco
		while(solucion<umbral && !valen.isEmpty()){//saco 1, 2, 3, .....bolas
			bolas++;
			int [] saco=new int[bolas];
			//saco bolas de ntre las que hay
			for(int i=0;i<saco.length;i++) saco[i]=1+new Random().nextInt(hay);
			//bolas distintas sacadas RRBRN devolvería 3 (RBN)
			int distintos=distintas(saco);
			//si hay mas bolas que urnas, porque se han eliminado, las añadimos
			if(distintos>valen.getLast()){
				while(valen.getLast()<=distintos) valen.add(valen.getLast()+1);
			}
			System.out.println("Hay distintas: "+distintos);
			//denominador de Bayes
			double denominador=denominador(distintos,bolas,valen);
			while(denominador==0){
				//sale un NaN, en vez de incrementar las bolas las quitamos
				bolas--;
				denominador=denominador(distintos,bolas,valen);
			}
			double p=0.0;
			double suma=0.0; //para asegurarnos de que la suma de las probabilidades es 1
			for(int urna=1;urna<=maxUrnas;urna++){
				//calculo la probabilidad de cada conjunto para esa sacada
				p=(probabilidad(distintos,urna,bolas,valen)/maxUrnas)/denominador;//Bayes
				//Se hacen muy pequeñas las probabilidades y disminuimos el número de bolas que sacamos en
				//vez de incrementarlo
				if(p==Double.NaN) bolas--;
				if (p==0.0){
					valen.removeFirstOccurrence(urna);//quitamos la urna
				}
				if(p>solucion){
					solucion=p;
					sol=urna;
				}
				suma=suma+p;
				if(valen.contains(urna))
					System.out.println("Indistinguibles urna="+urna+" probabilidad= "+p+" bolas="+bolas+" suma:"+suma);
			}
		}// while(solucion<umbral && !valen.isEmpty())
		String s="Indistinguibles Urna solucion ="+sol+" probabilidad="+solucion+" el bueno era el"+hay;
				System.out.println(s);
		return sol;
	} //conjuntos(int hay)
	private static int distintas(int[] saco){
		//si saco tiene los valores 2 2 5 1 2 lo que vemos son las repeticiones. devolveria 3
		LinkedList<Integer> aux=new LinkedList<Integer>();
		for(int n=0;n<saco.length;n++){
		int pos=aux.indexOf(saco[n]);
		if (pos==-1) aux.add(saco[n]);
		}
		return aux.size();
		}
	public static double probabilidad(int distintos,int urna,int bolas,LinkedList<Integer> valen){
		// P(xx|Ui) P(d distintos en n bolas|urna k)=Vk,d/VRk,n
		double p=0.0;
		if(valen.contains(urna)) p=Var(urna,distintos)/VarR(urna,bolas);
		return p;
		}
	public static double Var(int m,int n){//variaciones sin repes de m elementos tomados de n en n
		double v=0.0;
		int aux=m-n+1;
		if (aux>0 && aux<=m){
		v=1.0;
		for(int i=m;i>=aux;i--) v=v*i;
		}
		return v;
		}
	public static double VarR(int m,int n){//variaciones con Repes de m etos tomados de n en n
		return Math.pow(m,n);
		}
	public static double denominador(int distintas,int bolas,LinkedList<Integer> valen){
		// denominador del th de bayes P(Ui|x)=P(x|Ui).P(Ui)/Suma(P(x|Uj).P(Uj))
		double p=0.0;
		for(int urna=1;urna<=maxUrnas;urna++)
		p=p+probabilidad(distintas,urna,bolas,valen);
		return p/maxUrnas;
		}
	
}