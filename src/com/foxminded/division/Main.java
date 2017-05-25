package com.foxminded.division;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {

		int divident = readInt("Input divident: ");
		int divider = readInt("Input divider: ");
		IntegerDivision integerDivision = new IntegerDivision(divident, divider);
		integerDivision.printDivisionResult();
		System.out.println(integerDivision.getFullResult().toString());
	}

	public static int readInt(String message){
		while (true) {
			try {
				System.out.println(message);
				Scanner scanner = new Scanner(System.in);
				return scanner.nextInt();
			} catch (Exception E) {
				System.out.println("Wrong number!");
			}
		}
	}

}
