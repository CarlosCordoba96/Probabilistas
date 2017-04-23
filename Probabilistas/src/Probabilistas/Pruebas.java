package Probabilistas;
import java.util.*;
public class Pruebas {
	public static void main(String aaa[]) throws Exception{
		String texto="1.Circunferencia 2. x^y 3 xcosx 4 Pi 5 x/senx 6 Ln x 0. Fin";
		Scanner sc=new Scanner(System.in);
		boolean fin=false;
		do{
			Funciones f=null;
			try{
				switch (1){
				case 1:
					f=new Circunferencia(3.0);
							break;
					case 2: f=new xElevadoy(2,0,1);
					break;
					case 3: f=new xcosx(0,1);
					break;
					case 4:f=new Circunferencia(1.0);
					break;
					case 5: f=new xdivsenx(0,1);
					break;
					case 0: 
						fin=true;
						default:;
				}//fin del switch
				if(!fin){
					int k=100;
					System.out.println
					(f+"\nareaRieman: "+f.areaRieman(k)+
							"\nareaNumericoVM: "+f.areaNumericoVM(k)+" ["+f.intervaloInf()+" "+f.intervaloSup()+"]"+
							"\nareaNumericoP: "+f.areaNumericoP(k)+" ["+f.intervaloInfP()+" , "+f.intervaloSupP()+"]"+
							"\narea: "+ f.area());
				}
			}//fin del try
			catch(Exception e){ System.out.println(e.getMessage()); }
		}while(!(fin));
	}//fin del main
}