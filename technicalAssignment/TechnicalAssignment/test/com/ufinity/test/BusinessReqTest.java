package com.ufinity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.ufinity.requirements.Function;

/**
 * @author Vei Sheng Ong
 * BusinessReqTest.class is the JUnit test class which use to test the dateFormat validation
 */
class BusinessReqTest {

	// Valid format(MMM-yyyy) of fromDate and toDate
	@Test
	void validDate() {
		String fromDate = "Jan-1983";
		String toDate = "Dec-1983";
		assertEquals(false, Function.fromtoDateInput(fromDate, toDate));
	}

	// Invalid fromDate format
	@Test
	void invalidFromDate() {
		String fromDate = "January-1983";
		String toDate = "Dec-1983";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// Invalid toDate format
	@Test
	void invalidToDate() {
		String fromDate = "Jan-1983";
		String toDate = "December-1983";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// Empty fromDate
	@Test
	void emptyFromDate() {
		String fromDate = "";
		String toDate = "Dec-1983";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// Empty toDate
	@Test
	void emptyToDate() {
		String fromDate = "Jan-1983";
		String toDate = "";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// Empty Date
	@Test
	void emptyDate() {
		String fromDate = "";
		String toDate = "";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// fromDate is greater than toDate
	@Test
	void greatFromDate() {
		String fromDate = "Jan-2018";
		String toDate = "Jan-2017";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// toDate is greater than systemDate
	@Test
	void greatToSysDate() {
		String fromDate = "Jan-2018";
		String toDate = "Nov-2018";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// toDate is greater than systemDate
	@Test
	void greatFromSysDate() {
		String fromDate = "Dec-2018";
		String toDate = "Jan-2018";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// fromDate & toDate is greater than systemDate
	@Test
	void greatSysDate() {
		String fromDate = "Jan-2019";
		String toDate = "Dec-2019";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// toDate is equal to systemDate
	@Test
	void equalToSysDate() {
		String fromDate = "Jan-2018";
		String toDate = "Oct-2018";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

	// fromDate & toDate is equal to systemDate
	@Test
	void equalToFromSysDate() {
		String fromDate = "Oct-2018";
		String toDate = "Oct-2018";
		assertEquals(true, Function.fromtoDateInput(fromDate, toDate));
	}

}
