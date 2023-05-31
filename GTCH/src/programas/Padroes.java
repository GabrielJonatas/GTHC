package programas;

public class Padroes {

	public static void main(String[] args) {
		Padroes.Padrao2(40);

	}

	public static void Padrao1(int n) {

		for(int i=1; i<=n; i++) {
			int j = i;
			while(j>0) {
				System.out.print("* ");
				j--;
			}
			System.out.print("\n");
		}
	}
	
	public static void Padrao2(int n) {
		for(int i=1; i<=n; i++) {
			for(int j=i;j<=n;j++) {
				System.out.print(j + " ");
			}
			System.out.print("\n");
			
			
			// o if abaixo é pra garantir o formato triangular do padrão da saída
			if(i<10) {
				for(int k = 0; k<i;k++) {
					System.out.print("  ");
				}
			} else {
				for(int k = 3; k<i;k++) {
					System.out.print("   ");
				}
			}			
		}
	}
	
	public static void Padrao3(int n) {
		int k = 0;
		
		for(int i=1; i<=n; i++) {
			
			for(int j =0; j<i;j++) {
				k++;
				System.out.print(k + " ");
			}
			System.out.print("\n");
		}
		
	}

}
