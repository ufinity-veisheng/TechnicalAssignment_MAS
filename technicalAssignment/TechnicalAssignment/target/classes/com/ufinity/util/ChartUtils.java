package com.ufinity.util;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries.CategorySeriesRenderStyle;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler.LegendPosition;

import com.ufinity.model.InterestRates;


/**
 * @author Vei Sheng Ong
 * This ChartUtils.class is the class where the data is generated into the x chart format
 */
public class ChartUtils {

	public static void barChart(List <InterestRates> interestRatesList) {

		// Create Chart
		CategoryChart chart = new CategoryChartBuilder().width(1600).height(1200).title("Financial Companies Rates Against Bank Rates")
				.xAxisTitle("Month").yAxisTitle("Value").build();

		// Customize Chart
		chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
		chart.getStyler().setHasAnnotations(true);

		List<Double> bankFDeposit3mList = new ArrayList<Double>();
		List<Double> bankFDeposit6mList = new ArrayList<Double>();
		List<Double> bankFDeposit12mList = new ArrayList<Double>();
		List<Double> bankSDepositList = new ArrayList<Double>();
		List<Double> fcFDeposit3mList = new ArrayList<Double>();
		List<Double> fcFDeposit6mList = new ArrayList<Double>();
		List<Double> fcFDeposit12mList = new ArrayList<Double>();
		List<Double> fcSDepositList = new ArrayList<Double>();
		List<String> endOfMonthList = new ArrayList<String>();
		
		for(InterestRates interestRates: interestRatesList) {
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
		
		chart.addSeries("Bank Fixed Deposits 3 Month", endOfMonthList, bankFDeposit3mList);
		chart.addSeries("Financial Company Fixed Deposits 3 Month", endOfMonthList, fcFDeposit3mList);
		chart.addSeries("Bank Fixed Deposits 6 Month", endOfMonthList, bankFDeposit6mList);
		chart.addSeries("Financial Company Fixed Deposits 6 Month", endOfMonthList, fcFDeposit6mList);
		chart.addSeries("Bank Fixed Deposits 12 Month", endOfMonthList, bankFDeposit12mList);
		chart.addSeries("Financial Company Fixed Deposits 12 Month", endOfMonthList, fcFDeposit12mList);
		chart.addSeries("Bank Saving Deposits", endOfMonthList, bankSDepositList);
		chart.addSeries("Financial Company Saving Deposits", endOfMonthList, fcSDepositList);
		
		// Show it
		new SwingWrapper(chart).displayChart();

	}

	public static void lineChart(List <InterestRates> interestRatesList) {

		// generates Log data
		List<Double> fcFDeposit3mList = new ArrayList<Double>();
		List<Double> fcFDeposit6mList = new ArrayList<Double>();
		List<Double> fcFDeposit12mList = new ArrayList<Double>();
		List<Double> fcSDepositList = new ArrayList<Double>();
		List<String> endOfMonthList = new ArrayList<String>();

		for(InterestRates interestRates: interestRatesList) {
			fcFDeposit3mList.add(interestRates.getFcFDeposit3m());
			fcFDeposit6mList.add(interestRates.getFcFDeposit6m());
			fcFDeposit12mList.add(interestRates.getFcFDeposit12m());
			fcSDepositList.add(interestRates.getFcSDeposit());
			endOfMonthList.add(interestRates.getEndOfMonth());
		}
		
	    // Create Chart
		CategoryChart chart = new CategoryChartBuilder().width(1600).height(1200).title("Financial Company Rates")
				.xAxisTitle("Month").yAxisTitle("Value").build();

	    // Customize Chart
	    chart.getStyler().setDefaultSeriesRenderStyle(CategorySeriesRenderStyle.Line);
	    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);

	    // Series
	    chart.addSeries("Financial Company Fixed Deposits 3 Month", endOfMonthList, fcFDeposit3mList);
	    chart.addSeries("Financial Company Fixed Deposits 6 Month", endOfMonthList, fcFDeposit6mList);
	    chart.addSeries("Financial Company Fixed Deposits 12 Month", endOfMonthList, fcFDeposit12mList);
	    chart.addSeries("Financial Company Saving Deposits", endOfMonthList, fcSDepositList);
	    
		// Show it
		new SwingWrapper(chart).displayChart();
	}

}
