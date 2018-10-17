package com.ufinity.model;

/**
 * @author Vei Sheng Ong
 * This InterestRates.class is the common class abstract the data model with the standard setters and getters method to process the application
 */
public class InterestRates {

	private String endOfMonth;
	private double bankFDeposit3m;
	private double bankFDeposit6m;
	private double bankFDeposit12m;
	private double bankSDeposit;
	private double fcFDeposit3m;
	private double fcFDeposit6m;
	private double fcFDeposit12m;
	private double fcSDeposit;

	public InterestRates() {

	}

	public InterestRates(String endOfMonth, double bankFDeposit3m, double bankFDeposit6m, double bankFDeposit12m,
			double bankSDeposit, double fcFDeposit3m, double fcFDeposit6m, double fcFDeposit12m, double fcSDeposit) {
		super();
		this.endOfMonth = endOfMonth;
		this.bankFDeposit3m = bankFDeposit3m;
		this.bankFDeposit6m = bankFDeposit6m;
		this.bankFDeposit12m = bankFDeposit12m;
		this.bankSDeposit = bankSDeposit;
		this.fcFDeposit3m = fcFDeposit3m;
		this.fcFDeposit6m = fcFDeposit6m;
		this.fcFDeposit12m = fcFDeposit12m;
		this.fcSDeposit = fcSDeposit;
	}

	public String getEndOfMonth() {
		return endOfMonth;
	}

	public void setEndOfMonth(String endOfMonth) {
		this.endOfMonth = endOfMonth;
	}

	public double getBankFDeposit3m() {
		return bankFDeposit3m;
	}

	public void setBankFDeposit3m(double bankFDeposit3m) {
		this.bankFDeposit3m = bankFDeposit3m;
	}

	public double getBankFDeposit6m() {
		return bankFDeposit6m;
	}

	public void setBankFDeposit6m(double bankFDeposit6m) {
		this.bankFDeposit6m = bankFDeposit6m;
	}

	public double getBankFDeposit12m() {
		return bankFDeposit12m;
	}

	public void setBankFDeposit12m(double bankFDeposit12m) {
		this.bankFDeposit12m = bankFDeposit12m;
	}

	public double getBankSDeposit() {
		return bankSDeposit;
	}

	public void setBankSDeposit(double bankSDeposit) {
		this.bankSDeposit = bankSDeposit;
	}

	public double getFcFDeposit3m() {
		return fcFDeposit3m;
	}

	public void setFcFDeposit3m(double fcFDeposit3m) {
		this.fcFDeposit3m = fcFDeposit3m;
	}

	public double getFcFDeposit6m() {
		return fcFDeposit6m;
	}

	public void setFcFDeposit6m(double fcFDeposit6m) {
		this.fcFDeposit6m = fcFDeposit6m;
	}

	public double getFcFDeposit12m() {
		return fcFDeposit12m;
	}

	public void setFcFDeposit12m(double fcFDeposit12m) {
		this.fcFDeposit12m = fcFDeposit12m;
	}

	public double getFcSDeposit() {
		return fcSDeposit;
	}

	public void setFcSDeposit(double fcSDeposit) {
		this.fcSDeposit = fcSDeposit;
	}

	@Override
	public String toString() {
		return "InterestRates [endOfMonth=" + endOfMonth + ", bankFDeposit3m=" + bankFDeposit3m + ", bankFDeposit6m="
				+ bankFDeposit6m + ", bankFDeposit12m=" + bankFDeposit12m + ", bankSDeposit=" + bankSDeposit
				+ ", fcFDeposit3m=" + fcFDeposit3m + ", fcFDeposit6m=" + fcFDeposit6m + ", fcFDeposit12m="
				+ fcFDeposit12m + ", fcSDeposit=" + fcSDeposit + "]";
	}

}
