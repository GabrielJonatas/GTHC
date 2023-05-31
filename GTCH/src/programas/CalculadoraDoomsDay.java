package programas;

public class CalculadoraDoomsDay {

	public static void main(String[] args) {
			CalculadoraDoomsDay.RetornaDiaDaData(3,1,0);
	}
	
	// Programa principal que calcula o dia da semana referente a data
	private static void RetornaDiaDaData(int dia,int mes, int ano) {
		String[] daysOfTheWeek = {"domingo","segunda-feira","terça-feira","quarta-feira","quinta-feira","sexta-feira","sábado"};
		int[] doomsDayPorMes = {3,28,14,4,9,6,11,8,5,10,7,12};
		
		// Para verificar se o usuário colocou uma data válida
		if(CalculadoraDoomsDay.verificacaoDeData(dia, mes, ano)) {
			System.out.print("Data inválida");
			return;
		};
		
		int doomsDayMes = doomsDayPorMes[mes-1];
		int diaProximo = doomsDayMes; // Variável para manter o doomsDayMes inalterado, pois ele é uma referência
		
		// Verificações para determinar se o dia de entrada está atrás, a frente ou está no mesmo dia que o Doomsday
		// Após verificado, faz os ajustes para determinar o dia da semana referente ao dia de entrada
		if(dia>doomsDayMes) {
			while(dia>=diaProximo + 7) {
				diaProximo += 7;
			}
			
			if(dia == diaProximo) {
				System.out.print(daysOfTheWeek[CalculadoraDoomsDay.DoomsdayOfTheYear(ano)]);
			} else if(dia-diaProximo + CalculadoraDoomsDay.DoomsdayOfTheYear(ano) > 6) {
				System.out.print(daysOfTheWeek[dia - diaProximo - 7 + CalculadoraDoomsDay.DoomsdayOfTheYear(ano)]);
			} else {
			System.out.print(daysOfTheWeek[dia - diaProximo + CalculadoraDoomsDay.DoomsdayOfTheYear(ano)]);
			}
			
		} else if (dia<doomsDayMes) {
			while(diaProximo - 7 >= dia) {
				diaProximo -= 7;
			}
			
			if(dia == diaProximo) {
				System.out.print(daysOfTheWeek[CalculadoraDoomsDay.DoomsdayOfTheYear(ano)]);
			}else if(CalculadoraDoomsDay.DoomsdayOfTheYear(ano) - diaProximo + dia < 0) {
				System.out.print(daysOfTheWeek[CalculadoraDoomsDay.DoomsdayOfTheYear(ano) - diaProximo + dia + 7 ]);
			} else {
				System.out.print(daysOfTheWeek[CalculadoraDoomsDay.DoomsdayOfTheYear(ano) - diaProximo + dia]);
			}
			
		} else {
			System.out.print(daysOfTheWeek[CalculadoraDoomsDay.DoomsdayOfTheYear(ano)]);
		}
		
	}
	
	// Método para calcular o dia da semana do Doomsday referente ao ano de entrada
	private static int DoomsdayOfTheYear(int year) {
		
		int[] yearsDoomsday = {2,0,5,3};
		
		int numeroDias;
		int j = 0;
		int yearProximo = 100;

		//o yearProximo desempenhara o papel de situar a referência de seculo para o programa
		while(year>=yearProximo + 100) {
			yearProximo += 100;
		}
		
		// doomsDay é a variável que contém a referência de dia da semana do doomsday para o século mais próximo do ano de entrada
		int doomsDay = CalculadoraDoomsDay.CalculaDoomsdayYearPorSeculo(yearProximo);

		// As verificações abaixo são para ajustar o doomsday se o ano não for um século redondo
		if(year < 100) {
			numeroDias = year;
		} else {
			numeroDias = (year - yearProximo) + (int)((year - yearProximo)/4);
		}
		
		if(numeroDias>0) {
			while(numeroDias>7*(j+1)) {
				j++;
			}

			numeroDias -= 7*j;
		}

		if(doomsDay + numeroDias > 6) {
			return doomsDay + numeroDias - 7;
		} else {
			return doomsDay + numeroDias;
		}
			
	}

	// Esse método eu diria que é o coração do programa, pois ele calcula o Doomsday de qualquer século (pelos menos em teoria, testei para uma amostra mais próxima do nosso ano atual)
	// Para anos até 10000, a lógica pelo menos para mim, faz sentido
	private static int CalculaDoomsdayYearPorSeculo(int year) {
		
		String[] daysOfTheWeek = {"domingo","segunda-feira","terça-feira","quarta-feira","quinta-feira","sexta-feira","sábado"};
		int[] yearsDoomsday = {2,0,5,3};
		
		int seculo = 100;
		int n = 0;
		int redutor=year;
		
		// Achei uma relação de resto de divisão por 4 para divisões consecutivas dependendo do milenio estabelecido
		// Aparentemente esse resto de divisão coincide muito bem com o indice correto a ser escolhido no array yearsDoomsDay
		if(year == 100) {
			return yearsDoomsday[1];
		} else {
			while(year>seculo) {
				seculo = seculo*(int)Math.pow(100,n);
				n++;
			}
			
			for(int x = n; x>1; x--) {
				redutor /= 4;
			}
			
			return yearsDoomsday[redutor%4];
		}	
	}
	
	// Método simples de verificação dos dados colocados pelo usuário
	private static boolean verificacaoDeData(int dia, int mes, int ano) {
		int[] meses = {31,32,31,30,31,30,31,31,30,31,30,31};
		
		if(ano<0) return true;
		
		if(mes>12||mes<0) return true;
		
		if(meses[mes-1] > 31) {
			int bissexto;
			
			if(ano%4 == 0) {
				bissexto = 29;
			} else {
				bissexto = 28;
			}
			
			if(dia>bissexto) return true;
			
			return false;
		};
		
		if(dia>meses[mes-1])return true;
		
		return false;
	}
	
	
	
}
