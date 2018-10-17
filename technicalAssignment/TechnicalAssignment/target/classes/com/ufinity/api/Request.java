package com.ufinity.api;

import com.ufinity.config.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.*;
import com.ufinity.model.InterestRates;

/**
 * @author Vei Sheng Ong This Request.class is the API request class which pass
 *         in the required parameters into order to query the data provided by
 *         the Monetary Authority of Singapore (MAS)
 */
public class Request {
	
	/*
	 * requestURL will obtain the MAS url to query the API
	 * params will be the resource id
	 * value is the resource id value
	 * params1 is the &filters[end_of_month] 
	 */
	public static List<InterestRates> sendGET(String dateParams) throws Exception {
		Config cfg = new Config();
		List<InterestRates> interestRatesList = new ArrayList<InterestRates>();
		URL obj = new URL(cfg.getProperty("requestURL") + cfg.getProperty("params") + cfg.getProperty("value") + cfg.getProperty("params1") + dateParams);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", cfg.getProperty("userAgent"));

		//validate that the API connection is establish
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			//loop through each line of the API request result and save into response
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//Set the data result into interestRates Array list which used the model class of variable InterestRates.class
			try {
				JSONObject myResponse = new JSONObject(response.toString());
				JSONObject result1 = myResponse.getJSONObject("result");
				JSONArray arr = result1.getJSONArray("records");
				for (int i = 0; i < arr.length(); i++) {
					InterestRates interestRates = new InterestRates();
					JSONObject record = arr.getJSONObject(i);
					interestRates.setEndOfMonth(record.getString(cfg.getProperty("endOfMonth").toString()));
					interestRates
							.setBankFDeposit3m(Double.parseDouble(record.getString(cfg.getProperty("bankFDeposit3m"))));
					interestRates
							.setBankFDeposit6m(Double.parseDouble(record.getString(cfg.getProperty("bankFDeposit6m"))));
					interestRates.setBankFDeposit12m(
							Double.parseDouble(record.getString(cfg.getProperty("bankFDeposit12m"))));
					interestRates
							.setBankSDeposit(Double.parseDouble(record.getString(cfg.getProperty("bankSDeposit"))));
					interestRates
							.setFcFDeposit3m(Double.parseDouble(record.getString(cfg.getProperty("fcFDeposit3m"))));
					interestRates
							.setFcFDeposit6m(Double.parseDouble(record.getString(cfg.getProperty("fcFDeposit6m"))));
					interestRates
							.setFcFDeposit12m(Double.parseDouble(record.getString(cfg.getProperty("fcFDeposit12m"))));
					interestRates.setFcSDeposit(Double.parseDouble(record.getString(cfg.getProperty("fcSDeposit"))));
					interestRatesList.add(interestRates);
				}
			} catch (Exception e) {
				System.err.println(cfg.getProperty("queryError"));
			}
		} else {
			System.err.println(cfg.getProperty("pageNotFound"));
		}
		return interestRatesList;
	}
}
