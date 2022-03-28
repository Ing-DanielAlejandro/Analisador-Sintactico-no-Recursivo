package Jflextest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class TestClass {

	static String str = null;
	static int cont = 0;
	static NewLexer lexer;
	static Token token;

	public static void main(String args[]) throws IOException {

		InputStream is;
		str = JOptionPane.showInputDialog("Ingresa datos");

		is = new ByteArrayInputStream(str.getBytes());
		lexer = new NewLexer(is);

		token = lexer.yylex();
		if (E() && (token = lexer.yylex()) == null) {
			System.out.println("Aceptada");
		} else
			System.out.println("No aceptada");
	}

	static boolean compara(Token tokenb) {

		if (tokenb == token)
			return true;

		return false;

	}

	static boolean siguienteToken(Token tokenb) {

		if (tokenb != null) {
			try {
				if ((token = lexer.yylex()) == tokenb)
					return true;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	static boolean E() {
		if (compara(token.entero) || compara(token.flotante) || compara(token.exponente) || compara(token.PI)) {
			return T() && EP();
		}
		return false;
	}

	static boolean T() {
		if (compara(token.entero) || compara(token.flotante) || compara(token.exponente) || compara(token.PI)) {
			return F() && TP();
		}
		return false;
	}

	static boolean EP() {
		if ( compara(token.suma)) {
			try {
				token = lexer.yylex();
				return T() && EP();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if ( compara(token)) {
			return true;
		}
		return false;
	}

	static boolean TP() {

		if (compara(token.multi)) {
			try {
				token = lexer.yylex();
				return F() && TP();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (compara(token)) {
			return true;
		}
		return false;
	}

	static boolean F() {
		
		if (compara(token.PI)) {
			try {
				token = lexer.yylex();
				 E();compara(token.PD);
				return true;

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	
		if (compara(token.entero) || compara(token.flotante) || compara(token.exponente)) {
			try {
				token = lexer.yylex();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	

		return false;
	}

}
