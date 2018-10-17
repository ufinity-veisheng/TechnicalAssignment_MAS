package com.ufinity.main;

import java.util.Scanner;

import com.ufinity.config.Config;
import com.ufinity.requirements.Function;

/**
 * @author Vei Sheng Ong
 * MainFunction.class is the main function class that read the user input of toDate and fromDate
 */
public class MainFunction {

	public static void main(String[] args) {
		Config cfg = new Config();
		Scanner sc = new Scanner(System.in);

		String fromDate;
		String toDate;
        //Loop until the fromDate and toDate enter by the user is valid date format
		do {
			System.out.println(cfg.getProperty("fromDate"));
			fromDate = sc.nextLine();
			System.out.println(cfg.getProperty("toDate"));
			toDate = sc.nextLine();
		} while ((boolean) Function.fromtoDateInput(fromDate, toDate));
		sc.close();
	}
}
