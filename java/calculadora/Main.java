package eu.mat;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Stack;

public class Main {

	private static String operadores = "+-*/^";

	public static void main(String... args) {
	Analisar("2 + ( 2 * 3 + 6 + 10 ) / 5 ^ 9 + ( 2 * 9 + 6 / 8 ) ^ 8"); //ERROR 1.5276018532130398E10
	}

	private static void Analisar(String expressao) {

		System.out.println("Resolvendo: " + expressao);

		Stack<Integer> pilha = new Stack<>();

		StringBuilder string = new StringBuilder();

		// Quebrando a string no espaço
		for (String valores : expressao.split("\\s")) {

			int posicao = operadores.indexOf(valores.charAt(0));

			// Verificar se é um operador
			if (posicao > -1) {
				// Se a pilha estiver vazia adicionar a posição do operador confome a lista
				// OPERADORES
				if (pilha.isEmpty())
					pilha.push(posicao);
				else {

					while (!pilha.isEmpty()) {
						int primeiroItemLista = pilha.peek();

						if (primeiroItemLista > posicao || primeiroItemLista == posicao && !valores.equals("^"))
							string.append(operadores.charAt(pilha.pop())).append(' '); // se caso o primeiro operador da
																						// pilha for maior que o atual,
																						// remove-lo e mover para a
																						// String
						else
							break;
					}

					pilha.push(posicao);

				}
			} 
			else
			if (valores.charAt(0) == '(')
				pilha.push(-999); // Adicionar abertura
			else
			 if (valores.charAt(0) == ')') {
				while (pilha.peek() != -999) {
					// System.out.println(pilha.peek());
					string.append(operadores.charAt(pilha.pop())).append(" "); // mover operadores para String até
																				// chegar no )
				}
				pilha.pop(); // remover -999 da pilha para não dar erro
			} 
			 else
		  if (!valores.equals("(") && !valores.equals(")"))
				string.append(valores).append(" ");
		  
			;
		}

		while (!pilha.isEmpty())
			string.append(operadores.charAt(pilha.pop())).append(" ");

		//System.out.println("String: [" + string.toString() + "]");
		resolver(string.toString());

	}

	private static void resolver(String _string) {

		Stack<Double> numeros = new Stack<>();

		StringBuilder resultado = new StringBuilder();

		for (String valores : _string.split("\\s")) {

			int checkOperador = operadores.indexOf(valores.charAt(0));

			if (checkOperador > -1) {

				Double x1 = numeros.pop();
				Double x2 = numeros.pop();

				if (valores.equals("+"))
					numeros.push(x2 + x1);

				if (valores.equals("-"))
					numeros.push(x2 - x1);

				if (valores.equals("*"))
					numeros.push(x2 * x1);

				if (valores.equals("/"))
					numeros.push(x2 / x1);

				if (valores.equals("^"))
					numeros.push(Math.pow(x2, x1));

			} else {
				numeros.push(Double.parseDouble(valores));
			}

		}
		resultado.append(new DecimalFormat("#.######").format(new BigDecimal(numeros.pop(), new MathContext(0)))); //Arrumar (ERRO 1.5276018532130398E10) operação  "2 + ( 2 * 3 + 6 + 10 ) / 5 ^ 9 + ( 2 * 9 + 6 / 8 ) ^ 8"

		System.out.println("Resultado: [" + resultado.toString() + "]");
	}

}
