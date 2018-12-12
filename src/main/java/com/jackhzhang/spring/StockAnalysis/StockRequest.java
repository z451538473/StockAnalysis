package com.jackhzhang.spring.StockAnalysis;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class StockRequest
{
	private static final String QUERY_ROOT = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY";

	private String key = "";

	public StockRequest(String symbol)
	{
		// this.key = key;
		Request(symbol);
	}

	public void Request(String symbol)
	{
		RestTemplate req = new RestTemplate();
		String url = String.format(QUERY_ROOT + "&symbol=%s&apikey=%s", symbol, key);
		String rsp = req.getForObject(url, String.class);
		System.out.println("http req " + rsp);
		JSONObject json = JSON.parseObject(rsp);
		JSONObject data = json.getJSONObject("Time Series (Daily)");
		System.out.println(data.size());
		ArrayList<StockData> dataList = new ArrayList<StockData>();
		for (String date : data.keySet())
		{
			JSONObject obj = data.getJSONObject(date);
			StockData stock = new StockData();
			stock.symbol = symbol;
			stock.date = date;
			stock.openPrice = obj.getFloatValue("1. open");
			stock.highPrice = obj.getFloatValue("2. high");
			stock.lowPrice = obj.getFloatValue("3. low");
			stock.closePrice = obj.getFloatValue("4. close");
			stock.tradeVolume = obj.getLongValue("5. volume");
			dataList.add(stock);
		}

		Collections.sort(dataList, new Comparator<StockData>()
		{
			public int compare(StockData a, StockData b)
			{
				// TODO Auto-generated method stub
				return -a.date.compareTo(b.date);
			}
		});

		for (int i = 0; i < dataList.size(); i++)
		{
			if (i > 30)
				break;

			System.out.println(JSONObject.toJSON(dataList.get(i)));
		}
	}
}
