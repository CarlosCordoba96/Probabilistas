package lasvegas;

public class reinas {
	public static void main(String [] args){
		reinas(8);
	}
	public static void reinas(int dim){
		if(dim>=4){//con menos no hay solucion
			int[] r=new int[dim];
			int[] sol=new int[dim];
			long Ti=System.nanoTime();
			lasVegas(r,sol);
			long Tf=System.nanoTime()-Ti;
			pinta ("solLasvegas "+Tf,sol);
			r=new int[dim];
			sol=new int[dim];
			Ti=System.nanoTime();
			rBacktracking(r,0,sol);
			Tf=System.nanoTime()-Ti;
			pinta ("solBack "+Tf,sol);
		}
	}
	private static void pinta(String string, int[] sol) {
		System.out.println(string+"\n");
		System.out.print("[");
		for(int i=0;i<sol.length;i++){
			System.out.print(sol[i]+" ");
		}
		System.out.print("]\n");
	}
	private static void lasVegas(int r[],int[] sol){
		boolean vale=false;
		int d=r.length/2;
		while(!vale){//se relanza
			while(!vale){//genera una posible solucion aleatoria
				r=new int[sol.length];
				vale=true;
				for(int f=0;f<d && vale;f++){
					r[f]=(int)(Math.random()*(r.length-1));
					vale=vale(r,f);
				}
			}
			rBacktracking(r,d,sol);//solucionamos el problema para las filas que quedan
			vale=vale(sol,sol.length-1);//si no se puede solucionar se relanza
		}
	}
	private static void rBacktracking(int[] r,int etapa,int[] sol){
		if(etapa==r.length)
			System.arraycopy(r,0, sol, 0, sol.length);
		else{
			for(int fil=0;fil<r.length && !vale(sol,sol.length-1);fil++){
				if(vale(r,etapa))
					rBacktracking(r,etapa+1,sol);
					r[etapa]=r[etapa]+1;
			}
			r[etapa]=0;
		}
	}
	private static boolean vale(int[] estado,int pos){
		boolean fracaso=false;
		for (int i = 0; i < pos; i++) {
			if (estado[i] == estado[pos])
				fracaso=true; // misma columna
			else if (Math.abs(estado[i] - estado[pos]) == (pos - i))
				fracaso=true; // misma diagonal
		}
		return !fracaso;
	}

}
