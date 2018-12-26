package com.springboot.controller;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.domain.StockData;
import com.springboot.domain.StockRepository;

@RestController
public class StockQueryController
{
	@Autowired
	StockRepository stockResp;

	private static final String QUERY_ROOT = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY";

	@Value("${stock.queryApiKey}")
	private String key;

	@RequestMapping("/stockQuery")
	public String HandleQuery(@RequestParam("symbol") String symbol, @RequestParam(value = "fullData", required = false, defaultValue = "false") boolean fullData)
	{
		try
		{
			RestTemplate req = new RestTemplate();
			String url = String.format(QUERY_ROOT + "&symbol=%s&apikey=%s", symbol, key);
			if(fullData)
			{
				url += "&outputsize=full";
			}
			String rsp = req.getForObject(url, String.class);
			JSONObject json = JSON.parseObject(rsp);
			JSONObject data = json.getJSONObject("Time Series (Daily)");
			//System.out.println(data.size());
			// ArrayList<StockData> dataList = new ArrayList<StockData>();
			for (String date : data.keySet())
			{
				JSONObject obj = data.getJSONObject(date);
				StockData stock = new StockData();
				stock.setSymbol(symbol);
				stock.setDate(date);
				stock.setOpenPrice(obj.getFloatValue("1. open"));
				stock.setHighPrice(obj.getFloatValue("2. high"));
				stock.setLowPrice(obj.getFloatValue("3. low"));
				stock.setClosePrice(obj.getFloatValue("4. close"));
				stock.setTradeVolume(obj.getLongValue("5. volume"));
				stockResp.save(stock);
			}
			return "Successful add " + data.size() + " data";
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			return e.toString();
		}
	}
}
