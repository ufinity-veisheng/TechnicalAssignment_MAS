package com.ufinity.requirements;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import com.ufinity.api.*;
import com.ufinity.config.Config;
import com.ufinity.model.InterestRates;
import com.ufinity.util.ChartUtils;

/**
 * @author Vei Sheng Ong
 * This Function.class contains the functions for the business requirements of this technical assignments.
 */
public class Function {

	public static boolean fromtoDateInput(String fromDate, String toDate) {
		boolean invalidInput = true;

		Config cfg = new Config();
		List<InterestRates> interestRatesList = new ArrayList<InterestRates>();

		String dateList = "";
		Date sysDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
		String sysFormatDate = dateFormat.format(sysDate);

		//dateFormatter is use to format the date which input by the user
		//dateFormatter1 is use to format the date input by the user to the required format by the API request
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM-yyyy", Locale.ROOT);
		DateTimeFormatter dateFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM", Locale.ROOT);
		try {
			YearMonth toMonth = YearMonth.parse(toDate, dateFormatter);
			YearMonth fromMonth = YearMonth.parse(fromDate, dateFormatter);
			YearMonth sysFDate = YearMonth.parse(sysFormatDate, dateFormatter);
			
			//toDate is input by the user and parse into the YearMonth object as toMonth
			//fromDate is input by the user and parse into the YearMonth object as fromMonth
			//Validation for toMonth cannot be bigger/higher YearMonth than fromMonth
			//Validation for toMonth and fromMonth cannot have the bigger/higher value than the system current time
			if (toMonth.compareTo(fromMonth) < 0) {
				System.err.println(cfg.getProperty("invalidDatePeriod"));
				invalidInput = true;
			} else if (toMonth.compareTo(sysFDate) > 0 || toMonth.equals(sysFDate) || fromMonth.compareTo(sysFDate) > 0
					|| fromMonth.equals(sysFDate)) {
				System.err.println(cfg.getProperty("invalidDatePeriod1"));
				invalidInput = true;
			} else {
				for (YearMonth month = YearMonth.parse(fromDate, dateFormatter); !month.isAfter(toMonth); month = month
						.plusMonths(1)) {
					dateList += month.format(dateFormatter1) + ",";
				}
				if (dateList.substring(dateList.length() - 1).equals(",")) {
					dateList = dateList.substring(0, dateList.length() - 1);
				}
				try {
					interestRatesList = Request.sendGET(dateList);
					compareResult(interestRatesList);
					calAverage(interestRatesList);
					calSlope(interestRatesList);
					ChartUtils.barChart(interestRatesList);
					ChartUtils.lineChart(interestRatesList);
					invalidInput = false;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.err.println(cfg.getProperty("invalidDateFormat"));
		}
		return invalidInput;
	}

	
	public static void compareResult(List<InterestRates> interestRatesList) {
		Config cfg = new Config();

		List<Double> bankFDeposit3mList = new ArrayList<Double>();
		List<Double> bankFDeposit6mList = new ArrayList<Double>();
		List<Double> bankFDeposit12mList = new ArrayList<Double>();
		List<Double> bankSDepositList = new ArrayList<Double>();
		List<Double> fcFDeposit3mList = new ArrayList<Double>();
		List<Double> fcFDeposit6mList = new ArrayList<Double>();
		List<Double> fcFDeposit12mList = new ArrayList<Double>();
		List<Double> fcSDepositList = new ArrayList<Double>();
		List<String> endOfMonthList = new ArrayList<String>();

		for (InterestRates interestRates : interestRatesList) {
			bankFDeposit3mList.add(interestRates.getBankFDeposit3m());
			bankFDeposit6mList.add(interestRates.getBankFDeposit6m());
			bankFDeposit12mList.add(interestRates.getBankFDeposit12m());
			bankSDepositList.add(interestRates.getBankSDeposit());
			fcFDeposit3mList.add(interestRates.getFcFDeposit3m());
			fcFDeposit6mList.add(interestRates.getFcFDeposit6m());
			fcFDeposit12mList.add(interestRates.getFcFDeposit12m());
			fcSDepositList.add(interestRates.getFcSDeposit());
			endOfMonthList.add(interestRates.getEndOfMonth());
		}
		
		//Print out the data for the period enter
		for (InterestRates interestRates : interestRatesList) {
			System.out.println();
			System.out.println(cfg.getProperty("line1"));
			System.out.println(cfg.getProperty("title1") + interestRates.getEndOfMonth());
			System.out.println(cfg.getProperty("line1"));
			System.out.println(cfg.getProperty("content1") + interestRates.getBankFDeposit3m());
			System.out.println(cfg.getProperty("content2") + interestRates.getBankFDeposit6m());
			System.out.println(cfg.getProperty("content3") + interestRates.getBankFDeposit12m());
			System.out.println(cfg.getProperty("content4") + interestRates.getBankSDeposit());
			System.out.println(cfg.getProperty("content5") + interestRates.getFcFDeposit3m());
			System.out.println(cfg.getProperty("content6") + interestRates.getFcFDeposit6m());
			System.out.println(cfg.getProperty("content7") + interestRates.getFcFDeposit12m());
			System.out.println(cfg.getProperty("content8") + interestRates.getFcSDeposit());
			System.out.println(cfg.getProperty("line1"));
			System.out.println();
		}

		
		//Print out the compare of Financial Companies Rates Against Bank Rates
		System.out.println(cfg.getProperty("line2"));
		System.out.println(cfg.getProperty("title2"));
		System.out.println(cfg.getProperty("line2"));
		System.out.printf("\t\t\t\t" + "     | " + "  Fixed Deposit 3 Months " + " | " + "   Fixed Deposit 6 Months    "
				+ " | " + "  Fixed Deposit 12 Months    " + " | " + "       Savings Deposits      " + " | ");
		System.out.println();
		System.out.println(cfg.getProperty("line2"));
		for (int i = 0; i < interestRatesList.size(); i++) {
			System.out.println();
			System.out.printf("Bank" + "\t\t" + "   | Month : " + endOfMonthList.get(i) + " | " + "\t\t"
					+ bankFDeposit3mList.get(i) + "\t\t" + " | " + "\t\t" + bankFDeposit6mList.get(i) + "\t\t" + " | "
					+ "\t\t" + bankFDeposit12mList.get(i) + "\t\t" + " | " + "\t\t" + bankSDepositList.get(i) + "\t\t"
					+ " | ");
			System.out.println();
			System.out.printf("Financial Companies" + "| Month : " + endOfMonthList.get(i) + " | " + "\t\t"
					+ fcFDeposit3mList.get(i) + "\t\t" + " | " + "\t\t" + fcFDeposit6mList.get(i) + "\t\t" + " | "
					+ "\t\t" + fcFDeposit12mList.get(i) + "\t\t" + " | " + "\t\t" + fcSDepositList.get(i) + "\t\t"
					+ " | ");
			System.out.println();
			// Using the Financial Companies Savings Deposit compare against Bank Savings Deposit to get the higher rate of the month is either Financial Companies or Bank
			if (fcSDepositList.get(i) > bankSDepositList.get(i)) {
				System.out.println("This month " + endOfMonthList.get(i)
						+ ", the Financial Companies have a higher rate than Bank");
			}
		}

		System.out.println(cfg.getProperty("line2"));

	}

	public static void calAverage(List<InterestRates> interestRatesList) {
		Config cfg = new Config();

		//Sum up Financial Companies Fixed Deposit 3 Months and divided by the numbers of months. 
		//Sum up Financial Companies Fixed Deposit 6 Months and divided by the numbers of months. 
		//Sum up Financial Companies Fixed Deposit 12 Months and divided by the numbers of months. 
		//Sum up Financial Companies Savings Deposits and divided by the numbers of months. 
		//Sum up Bank Fixed Deposit 3 Months and divided by the numbers of months. 
		//Sum up Bank Fixed Deposit 6 Months and divided by the numbers of months. 
		//Sum up Bank Fixed Deposit 12 Months and divided by the numbers of months. 
		//Sum up Bank Savings Deposits and divided by the numbers of months.
		DecimalFormat df = new DecimalFormat("####0.00");
		Double aveFcFDeposit3m = (double) 0;
		Double aveFcFDeposit6m = (double) 0;
		Double aveFcFDeposit12m = (double) 0;
		Double aveFcSDeposit = (double) 0;
		Double aveBankFDeposit3m = (double) 0;
		Double aveBankFDeposit6m = (double) 0;
		Double aveBankFDeposit12m = (double) 0;
		Double aveBankSDeposit = (double) 0;
		for (InterestRates interestRates : interestRatesList) {
			aveFcFDeposit3m += interestRates.getFcFDeposit3m() / interestRatesList.size();
			aveFcFDeposit6m += interestRates.getFcFDeposit6m() / interestRatesList.size();
			aveFcFDeposit12m += interestRates.getFcFDeposit12m() / interestRatesList.size();
			aveFcSDeposit += interestRates.getFcSDeposit() / interestRatesList.size();
			aveBankFDeposit3m += interestRates.getBankFDeposit3m() / interestRatesList.size();
			aveBankFDeposit6m += interestRates.getBankFDeposit6m() / interestRatesList.size();
			aveBankFDeposit12m += interestRates.getBankFDeposit12m() / interestRatesList.size();
			aveBankSDeposit += interestRates.getBankSDeposit() / interestRatesList.size();
		}
		//print out the result
		System.out.println();
		System.out.println(cfg.getProperty("line3"));
		System.out.println(cfg.getProperty("title3"));
		System.out.println(cfg.getProperty("line3"));
		System.out.println(cfg.getProperty("content9") + df.format(aveFcFDeposit3m));
		System.out.println(cfg.getProperty("content10") + df.format(aveFcFDeposit6m));
		System.out.println(cfg.getProperty("content11") + df.format(aveFcFDeposit12m));
		System.out.println(cfg.getProperty("content12") + df.format(aveFcSDeposit));
		System.out.println(cfg.getProperty("content25") + df.format(aveBankFDeposit3m));
		System.out.println(cfg.getProperty("content26") + df.format(aveBankFDeposit6m));
		System.out.println(cfg.getProperty("content27") + df.format(aveBankFDeposit12m));
		System.out.println(cfg.getProperty("content28") + df.format(aveBankSDeposit));
	}

	public static void calSlope(List<InterestRates> interestRatesList) {
		Config cfg = new Config();

		List<Double> fcFDeposit3mList = new ArrayList<Double>();
		List<Double> fcFDeposit6mList = new ArrayList<Double>();
		List<Double> fcFDeposit12mList = new ArrayList<Double>();
		List<Double> fcSDepositList = new ArrayList<Double>();
		List<String> endOfMonthList = new ArrayList<String>();

		for (InterestRates interestRates : interestRatesList) {
			fcFDeposit3mList.add(interestRates.getFcFDeposit3m());
			fcFDeposit6mList.add(interestRates.getFcFDeposit6m());
			fcFDeposit12mList.add(interestRates.getFcFDeposit12m());
			fcSDepositList.add(interestRates.getFcSDeposit());
			endOfMonthList.add(interestRates.getEndOfMonth());
		}
		
		
		//Get the input of toDate and fromDate result of Financial Companies to compare and obtain the trend of the rates slope
		Double firstFDeposit3M = fcFDeposit3mList.get(0);
		Double lastFDeposit3M = fcFDeposit3mList.get(fcFDeposit3mList.size() - 1);
		Double firstFDeposit6M = fcFDeposit6mList.get(0);
		Double lastFDeposit6M = fcFDeposit6mList.get(fcFDeposit6mList.size() - 1);
		Double firstFDeposit12M = fcFDeposit12mList.get(0);
		Double lastFDeposit12M = fcFDeposit12mList.get(fcFDeposit12mList.size() - 1);
		Double firstSDeposit = fcSDepositList.get(0);
		Double lastSDeposit = fcSDepositList.get(fcSDepositList.size() - 1);

		//Print out the result of the Financial Companies Interest Rates Slope
		System.out.println();
		System.out.println(cfg.getProperty("line4"));
		System.out.println(cfg.getProperty("title4"));
		System.out.println(cfg.getProperty("line4"));
		if (lastFDeposit3M > firstFDeposit3M) {
			System.out.println(cfg.getProperty("content13"));
		} else if (lastFDeposit3M < firstFDeposit3M) {
			System.out.println(cfg.getProperty("content14"));
		} else {
			System.out.println(cfg.getProperty("content15"));
		}
		if (lastFDeposit6M > firstFDeposit6M) {
			System.out.println(cfg.getProperty("content16"));
		} else if (lastFDeposit6M < firstFDeposit6M) {
			System.out.println(cfg.getProperty("content17"));
		} else {
			System.out.println(cfg.getProperty("content18"));
		}
		if (lastFDeposit12M > firstFDeposit12M) {
			System.out.println(cfg.getProperty("content19"));
		} else if (lastFDeposit12M < firstFDeposit12M) {
			System.out.println(cfg.getProperty("content20"));
		} else {
			System.out.println(cfg.getProperty("content21"));
		}
		if (lastSDeposit > firstSDeposit) {
			System.out.println(cfg.getProperty("content22"));
		} else if (lastSDeposit < firstSDeposit) {
			System.out.println(cfg.getProperty("content23"));
		} else {
			System.out.println(cfg.getProperty("content24"));
		}

	}
}
