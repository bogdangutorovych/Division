package com.foxminded.division;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntegerDivisionTest {
	
	IntegerDivision integerDivision = new IntegerDivision(1, 1);

	@Test
	public void shouldCalculateLocalDividendNext() {
		assertEquals("Next local divident isn't correct", 10, integerDivision.calculateLocalDividend(10, 10));
		assertEquals("Next local divident isn't correct", 123, integerDivision.calculateLocalDividend(1234, 13));
		assertEquals("Next local divident isn't correct", 5678, integerDivision.calculateLocalDividend(567896321, 4000));
	}
	
	@Test
	public void shouldAddSymbols() {

		StringBuilder input = new StringBuilder("");
		StringBuilder output = new StringBuilder("     ");
		assertEquals("Modified string isn't correct", output.toString(),integerDivision.addChars(" ", 5, input).toString());
		
		StringBuilder input2 = new StringBuilder("AsDf");
		StringBuilder output2 = new StringBuilder("AsDf");
		assertEquals("Modified string isn't correct", output2.toString(),integerDivision.addChars("c", 0, input2).toString());
		
		StringBuilder input3 = new StringBuilder("1!2@3#4$");
		StringBuilder output3 = new StringBuilder("1!2@3#4$NNNNNNNNNN");
		assertEquals("Modified string isn't correct", output3.toString(),integerDivision.addChars("N", 10, input3).toString());
		
		StringBuilder input4 = new StringBuilder("1!2@3#4$");
		StringBuilder output4 = new StringBuilder("1!2@3#4$");
		assertEquals("Modified string isn't correct", output4.toString(),integerDivision.addChars("j", -2, input4).toString());
	}
	
	@Test
	public void shoulCalculateDivisionResult() {
		assertEquals("Result with period is wrong", "0", integerDivision.calculateDivisionResult(0, 123465));
		assertEquals("Result with period is wrong", "19614.75", integerDivision.calculateDivisionResult(78459, 4));
		assertEquals("Result with period is wrong", "333.(3)", integerDivision.calculateDivisionResult(1000, 3));
		assertEquals("Result with period is wrong", "0.58(3)", integerDivision.calculateDivisionResult(7, 12));
		assertEquals("Result with period is wrong", "0.(641025)", integerDivision.calculateDivisionResult(25, 39));
		assertEquals("Result with period is wrong", "1001001", integerDivision.calculateDivisionResult(123123123, 123));
		assertEquals("Result with period is wrong", "0.6(857142)", integerDivision.calculateDivisionResult(24, 35));
		assertEquals("Result with period is wrong", "0.(03)", integerDivision.calculateDivisionResult(1, 33));
		assertEquals("Result with period is wrong", "0.(003)", integerDivision.calculateDivisionResult(1, 333));
		assertEquals("Result with period is wrong", "1.4401197604", integerDivision.calculateDivisionResult(962, 668));
		
	}
	
	@Test
	public void shoulCalculatePeriod() {
		assertEquals("Calculated period is wrong", "", integerDivision.calculatePeriod(0, 123465));
		assertEquals("Calculated period is wrong", "", integerDivision.calculatePeriod(78459, 4));
		assertEquals("Calculated period is wrong", "3", integerDivision.calculatePeriod(1000, 3));
		assertEquals("Calculated period is wrong", "3", integerDivision.calculatePeriod(7, 12));
		assertEquals("Calculated period is wrong", "641025", integerDivision.calculatePeriod(25, 39));
		assertEquals("Calculated period is wrong", "", integerDivision.calculatePeriod(123123123, 123));
		assertEquals("Calculated period is wrong", "857142", integerDivision.calculatePeriod(24, 35));
		assertEquals("Calculated period is wrong", "03", integerDivision.calculatePeriod(1, 33));
		assertEquals("Calculated period is wrong", "003", integerDivision.calculatePeriod(1, 333));
		
	}
	
	@Test
	public void shoulCalculatePeriodLenth() {
		assertEquals("Calculated period lenth is wrong", 0, integerDivision.calculatePeriodLenth(0, 123465));
		assertEquals("Calculated period lenth is wrong", 0, integerDivision.calculatePeriodLenth(100, 5));
		assertEquals("Calculated period lenth is wrong", 0, integerDivision.calculatePeriodLenth(78459, 5));
		assertEquals("Calculated period lenth is wrong", 2, integerDivision.calculatePeriodLenth(1, 33));
		assertEquals("Calculated period lenth is wrong", 3, integerDivision.calculatePeriodLenth(1, 333));
		assertEquals("Calculated period lenth is wrong", 4, integerDivision.calculatePeriodLenth(1, 3333));
		assertEquals("Calculated period lenth is wrong", 1, integerDivision.calculatePeriodLenth(7, 12));
		assertEquals("Calculated period lenth is wrong", 6, integerDivision.calculatePeriodLenth(25, 39));
		assertEquals("Calculated period lenth is wrong", 6, integerDivision.calculatePeriodLenth(24, 35));
		assertEquals("Calculated period lenth is wrong", 166, integerDivision.calculatePeriodLenth(962, 668));
	}
	
	@Test
	public void shoulCalculateFraction() {
		assertEquals("Calculated fraction is wrong", "", integerDivision.calculateFraction(0, 123465));
		assertEquals("Calculated fraction is wrong", "75", integerDivision.calculateFraction(78459, 4));
		assertEquals("Calculated fraction is wrong", "3333333333", integerDivision.calculateFraction(1000, 3));
		assertEquals("Calculated fraction is wrong", "5833333333", integerDivision.calculateFraction(7, 12));
		assertEquals("Calculated fraction is wrong", "6410256410", integerDivision.calculateFraction(25, 39));
		assertEquals("Calculated fraction is wrong", "", integerDivision.calculateFraction(123123123, 123));
		assertEquals("Calculated fraction is wrong", "6857142857", integerDivision.calculateFraction(24, 35));
		assertEquals("Calculated fraction is wrong", "4401197604", integerDivision.calculateFraction(962, 668));
		assertEquals("Calculated fraction is wrong", "0303030303", integerDivision.calculateFraction(1, 33));
		
	}
	
	@Test
	public void shoulCalculateFractionWithPeriod() {
		assertEquals("Calculated fraction with period is wrong", "", integerDivision.calculateFractionWithPeriod("", "123465"));
		assertEquals("Calculated fraction with period is wrong", "", integerDivision.calculateFractionWithPeriod("75", ""));
		assertEquals("Calculated fraction with period is wrong", "(3)", integerDivision.calculateFractionWithPeriod("3333333333", "3"));
		assertEquals("Calculated fraction with period is wrong", "58(3)", integerDivision.calculateFractionWithPeriod("5833333333", "3"));
		assertEquals("Calculated fraction with period is wrong", "(641025)", integerDivision.calculateFractionWithPeriod("6410256410", "641025"));
		assertEquals("Calculated fraction with period is wrong", "", integerDivision.calculateFractionWithPeriod("123123123", ""));
		assertEquals("Calculated fraction with period is wrong", "", integerDivision.calculateFractionWithPeriod("6857142857", ""));
		assertEquals("Calculated fraction with period is wrong", "(03)", integerDivision.calculateFractionWithPeriod("0303030303", "03"));
		assertEquals("Calculated fraction with period is wrong", "(003)", integerDivision.calculateFractionWithPeriod("0030030030", "003"));
		assertEquals("Calculated fraction with period is wrong", "4401197604", integerDivision.calculateFractionWithPeriod("4401197604", "401197604790419161676646"));
	}
	
	@Test
	public void shoulCalculateNumberOfFractionDivision() {
		assertEquals("Calculated number of fraction division is wrong", 10, integerDivision.calculateNumberOfFractionDivision(962, 668));
		assertEquals("Calculated number of fraction division is wrong", 2, integerDivision.calculateNumberOfFractionDivision(1, 33));
		assertEquals("Calculated number of fraction division is wrong", 3, integerDivision.calculateNumberOfFractionDivision(1, 333));
		assertEquals("Calculated number of fraction division is wrong", 3, integerDivision.calculateNumberOfFractionDivision(7, 12));
		assertEquals("Calculated number of fraction division is wrong", 6, integerDivision.calculateNumberOfFractionDivision(25, 39));
		assertEquals("Calculated number of fraction division is wrong", 0, integerDivision.calculateNumberOfFractionDivision(123123123, 123));
		assertEquals("Calculated number of fraction division is wrong", 0, integerDivision.calculateNumberOfFractionDivision(0, 5));
	}
	
	@Test
	public void shoulCalculateShiftFirstSecondLines() {
		assertEquals("Count of spaces for frist and second lines", 0, integerDivision.calculateShiftFirstSecondLines(0, 123456));
		assertEquals("Count of spaces for frist and second lines", 3, integerDivision.calculateShiftFirstSecondLines(1, 101));
		assertEquals("Count of spaces for frist and second lines", 1, integerDivision.calculateShiftFirstSecondLines(25, 39));
		assertEquals("Count of spaces for frist and second lines", 0, integerDivision.calculateShiftFirstSecondLines(123123123, 123));

	}
	
	@Test
	public void shoulSplitNumber() {
		assertEquals("Incorrect splitting of number", "123", integerDivision.splitNumber(1235489, 3));
		assertEquals("Incorrect splitting of number", "", integerDivision.splitNumber(0, 0));
		assertEquals("Incorrect splitting of number", "", integerDivision.splitNumber(568, 0));
		assertEquals("Incorrect splitting of number", "2536", integerDivision.splitNumber(2536, 4));
		assertEquals("Incorrect splitting of number", "123123", integerDivision.splitNumber(123123, 15));
	}
	
}
