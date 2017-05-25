package com.foxminded.division;

public class IntegerDivision {
	
	public final int PRECISION = 10;
	public final int COUNT_BRACKETS = 2;

	private int dividend;
	private int divider;
	private int resultEachDigit;
	private int shiftSpace;
	private int numberOfFractionDivision;
	private String divisionResult;
	private StringBuilder dividendSB;
	private StringBuilder divisionResultString;
	private StringBuilder firstSplitedString;
	private StringBuilder secondSplitedString;
	private StringBuilder fullResult = new StringBuilder("");
	
	public StringBuilder getFullResult() {
		return fullResult;
	}

	public IntegerDivision(int dividend, int divider) {
		this.numberOfFractionDivision = calculateNumberOfFractionDivision(dividend, divider);
		this.dividend = dividend;
		this.divider = divider;
		this.divisionResultString = new StringBuilder("");
		this.dividendSB = new StringBuilder(Integer.toString(this.dividend));
		this.divisionResult = calculateDivisionResult(this.dividend, this.divider);

	}

	public String calculateDivisionResult (int dividend, int divider) {
		if (divider == 0) {
			System.out.println("Can not divide by zero!");
			System.exit(0);
		}
		if (dividend % divider == 0 ) {	
			return Integer.toString(dividend / divider);
		} else if (calculateFraction(dividend, divider).length() < PRECISION) {	
			return Double.toString((double)dividend / divider);
		} else {	
			return Integer.toString(dividend / divider) + "." + calculateFractionWithPeriod(calculateFraction(dividend, divider), calculatePeriod(dividend, divider));
		}
	}

	public String calculatePeriod (int dividend, int divider) {
		String fractionPart = new String("");
		int periodLenth = calculatePeriodLenth(dividend, divider);
		if (periodLenth > 0) {
			int i = 0;
			dividend = dividend % divider;
			while (i != divider) {
				fractionPart = fractionPart + Integer.toString((10 * dividend) / divider);
				dividend = (10 * dividend) % divider;
				i += 1;
			}
		}
		String period = "";
		for (int i = 0; i < divider ; i++) {
			if (fractionPart.substring(i, periodLenth + i).equals(fractionPart.substring(periodLenth + i, periodLenth + periodLenth + i))) {
				period = fractionPart.substring(i, periodLenth + i);
				return period;
			} 
		}
		return period;
	}
	
	public int calculatePeriodLenth (int dividend, int divider) {
		if (dividend % divider == 0) {
			return 0;
		}
		int i = 0;
		while (i != divider) {
			dividend = (10 * dividend) % divider;
			i += 1;
			if (dividend == 0) {
				return 0;
			}
		}
		int c = dividend;
		dividend = (10 * dividend) % divider;
		int periodLenth = 1;
		while (dividend != c) {
			dividend = (10 * dividend) % divider;
			periodLenth += 1;
		}
		return periodLenth;
	}

	public String calculateFraction (int dividend, int divider) {
		String doubleResult = Double.toString((double)dividend / divider);
		String integerResult = Integer.toString(dividend / divider);
		String fraction = new String();
		if (dividend % divider == 0) {
			return fraction;
		} else if ((doubleResult.length() - integerResult.length() + 1) < PRECISION) {
			fraction = doubleResult.substring(integerResult.length() + 1, doubleResult.length());
			return fraction;
		} else {
			fraction = doubleResult.substring(integerResult.length() + 1, integerResult.length() + 1 + PRECISION);
		}
		return fraction;
	} 

	public String calculateFractionWithPeriod (String fraction, String period) {
		if (period.length() > fraction.length()) {
			return fraction;
		}
		int i = fraction.indexOf(period);
		if ((i != -1) && (period.length() != 0)) {
			return fraction.substring(0, i) + "(" + period + ")";	
		}
		return "";
	}

	public int calculateNumberOfFractionDivision (int dividend, int divider) {
		int i = calculateFraction(dividend, divider).length();
		if (i > 0 && i < PRECISION) {
			return i;
		} else if (calculatePeriodLenth(dividend, divider) >= PRECISION) {
			return PRECISION;
		} else if ((calculatePeriodLenth(dividend, divider) < PRECISION) && (calculatePeriodLenth(dividend, divider) > 0)) {
			return calculateFractionWithPeriod(calculateFraction(dividend, divider), calculatePeriod(dividend, divider)).length() - COUNT_BRACKETS;
		} else if (calculateFraction(dividend, divider).length() == 0) {
			return 0;
		}
		return PRECISION;
	}
	
	public StringBuilder addChars(String s, int n, StringBuilder dest) {
		for (int i = 0; i < n ; i++) {
			dest.append(s);
		}
		return dest;
	}
	
	public int calculateShiftFirstSecondLines (int dividend, int divider) {
		int i = 0;
		if (dividend == 0) {
			return 0;
		} else if (dividend >= divider) {
			return i;
		} else {
			while (dividend < divider) {
				dividend *= 10;
				i++;
			}
			return i;
		}
	}

	public void printDivisionResult() {
		StringBuilder spaces = new StringBuilder(" ");

		fullResult.append(spaces.toString() + dividendSB);
		addChars(" ", calculateShiftFirstSecondLines(this.dividend, this.divider), fullResult);
		fullResult.append("|" + divider + "\n");

		fullResult.append("-");
		addChars(" ", Integer.toString(dividend).length(), fullResult); 
		addChars(" ", calculateShiftFirstSecondLines(this.dividend, this.divider), fullResult);
		fullResult.append("|");
		addChars("-", divisionResult.length(), fullResult);
		
		for(int i = 0; (this.dividend >= this.divider) || (this.numberOfFractionDivision > 0 && this.dividend != 0); i++) {
			while (this.dividend < this.divider) {
				dividend = Integer.parseInt(dividendSB.append("0").toString());
				this.numberOfFractionDivision--;
			}
			if (i == 0) {
				updateDividend();
				fullResult.append("\n" + spaces.toString());
				addChars(" ", (this.firstSplitedString.length() - Integer.toString(resultEachDigit * divider).length()), fullResult);
				fullResult.append(resultEachDigit * divider);
				addChars(" ", this.secondSplitedString.length(), fullResult);
				fullResult.append("|" + divisionResult);
				fullResult.append("\n" + spaces.toString());
				addChars(" ", this.firstSplitedString.length() - Integer.toString(resultEachDigit * divider).length(), fullResult);
				addChars("-", Integer.toString(resultEachDigit * divider).length(), fullResult);
				addChars(" ",this.shiftSpace, spaces);
				if (this.shiftSpace >= 0) {
					spaces.append(" ");
				}
				continue;
			}

			fullResult.append("\n" + spaces.toString() + calculateLocalDividend(this.dividend, this.divider));
			updateDividend();
			fullResult.append("\n" + spaces.toString().substring(0, spaces.toString().length() - 1) + "-"); 
			fullResult.append("\n" + spaces.toString());
			addChars(" ", (this.firstSplitedString.length() - Integer.toString(resultEachDigit * divider).length()), fullResult);
			fullResult.append(resultEachDigit * divider);
			fullResult.append("\n" + spaces.toString());
			addChars(" ", this.firstSplitedString.length() - Integer.toString(resultEachDigit * divider).length(), fullResult);
			addChars("-", Integer.toString(resultEachDigit * divider).length(), fullResult);
			addChars(" ",this.shiftSpace, spaces);
			if (this.shiftSpace >= 0) {
				spaces.append(" ");
			}
		}
		fullResult.append("\n" + spaces.toString() + Integer.parseInt(this.dividendSB.toString()));
	}

	public int calculateLocalDividend(int dividend, int divider) {
		int i = 1;
		while (Integer.parseInt(splitNumber(dividend, i)) < divider) {
			i++;
		}
		this.firstSplitedString = new StringBuilder(splitNumber(dividend, i));
		this.secondSplitedString = new StringBuilder(Integer.toString(dividend).substring(i, Integer.toString(dividend).length()));
		return Integer.parseInt(this.firstSplitedString.toString());
	}

	public void updateDividend() {
		this.resultEachDigit = calculateLocalDividend(this.dividend, this.divider) / divider;
		divisionResultString.append(resultEachDigit);
		this.dividendSB = new StringBuilder(Integer.toString(calculateLocalDividend(this.dividend, this.divider) % divider));

		if (calculateLocalDividend(this.dividend, this.divider) % divider == 0) {
			this.shiftSpace = Integer.toString(resultEachDigit * divider).length() - Integer.toString(calculateLocalDividend(this.dividend, this.divider) % divider).length();
		} else {
			this.shiftSpace = Integer.toString(resultEachDigit * divider).length() - Integer.toString(calculateLocalDividend(this.dividend, this.divider) % divider).length() - 1;
			if (this.shiftSpace < 0) {
				this.shiftSpace = 0;
			}
		}
		this.dividendSB.append(this.secondSplitedString);
		dividend = Integer.parseInt(this.dividendSB.toString());
	}

	public String splitNumber (int number, int i) {
		if (Integer.toString(number).length() < i) {
			return Integer.toString(number);
		}
		return Integer.toString(number).substring(0, i);
	}
	
}
