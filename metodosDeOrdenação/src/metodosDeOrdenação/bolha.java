package metodosDeOrdenação;

import java.util.Arrays;

public class bolha {

	public static void main(String[] args) {
		
		int [] v= {55,35,45,70,22,10,2,14,28,89};
		bolha(v);
		System.out.println(Arrays.toString(v));
	}

	private static void bolha(int[] v) {
		for (int ultimo =v.length-1; ultimo >0; ultimo--) {
			for (int i=0; i< ultimo; i++) 
				if(v[i] > v[i+1]) 
				trocar(v,i,i+1);
				
		}
	}

	private static void trocar(int[] v, int i, int j) {
		int aux = v[i];
		v[i]=v[j];
		v[j]=aux;
		
	}
	
	/* EXEMPLO PROF
	 void sort(int[] array, int n) {
	for (int i = (n - 1); i > 0; i--) {
		for (int j = 0; j < i; j++) {
			if (array[j] > array[j + 1]) {
               			
				int temp = array[j];
      				array[j] = array[j+1];
      				array[j+1] = temp;
			}
		}
	}
}*/

}
