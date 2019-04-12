public class calculo {

	public static void main(String[] teste) {
		
		if (teste.length == 0 || teste[0] == null) {
			System.out.println("Digite um numero");
			return;
		}
		
		int elevar = Integer.parseInt(teste[0]);
		int operaca= 1;
		
		String resultado = "1";
		
		long tempoInicio = System.currentTimeMillis();
		
		while (elevar > 0) {
			resultado = calcular_esta_porra(resultado);
			
			if (elevar == 1) {
				long tempoFinal = System.currentTimeMillis() - tempoInicio;
				System.out.println("Resultado (2^" +operaca + "): " + resultado + " (" + String.format("%02d segundos  e %02d milisegundos", tempoFinal/1000, tempoFinal%60) + ")");		
			}

			
			operaca++;
			elevar--;
		}

	}
	
	
	//Retorno -9223372036854775808
	private static long calcular(long resultado) {
		return resultado * (long) 2;
	}
	
	private static String calcular_esta_porra(String numero) {
		
		
		numero = new StringBuilder(numero).reverse().toString();
		int somar = 0;
		String _resultado = "";
		
		for (int i=0; i < numero.length(); i++) {
			
			int x = Integer.parseInt(Character.toString(numero.charAt(i)));
			int resultado  = (x*2) + somar;
			somar = 0;
			
			if (resultado > 9) {
				somar = Integer.parseInt(Character.toString(String.valueOf(resultado).charAt(0)));
				resultado =  Integer.parseInt(Character.toString(String.valueOf(resultado).charAt(1)));
			}
			
			_resultado += String.valueOf(resultado);
			
		}
		if (somar > 0)
		_resultado += String.valueOf(somar);
		
		return _resultado = new StringBuilder(_resultado).reverse().toString();
	}
	

	
	
	
}
