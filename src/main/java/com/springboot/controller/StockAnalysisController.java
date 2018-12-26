package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.domain.StockAnalysisData;
import com.springboot.domain.StockData;
import com.springboot.service.StockDataService;

@RestController
public class StockAnalysisController
{
	@Autowired
	StockDataService stockDataService;

	@Autowired
	StockLogController stockLogController;

	@RequestMapping("/stockData")
	public StockAnalysisData GetStockData(@RequestParam("symbol") String symbol)
	{
		List<StockData> list = stockDataService.GetLastYearStockData(symbol);
		StockAnalysisData analysisData = new StockAnalysisData();
		float highPrice = 0;
		float lowPrice = 0;
		for (int i = 0; i < list.size(); i++)
		{
			StockData data = list.get(i);
			highPrice = (i == 0) ? data.getHighPrice() : Math.max(data.getHighPrice(), highPrice);
			lowPrice = (i == 0) ? data.getLowPrice() : Math.min(data.getLowPrice(), lowPrice);
		}
		analysisData.highPriceLastYear = highPrice;
		analysisData.lowPriceLastYear = lowPrice;
		return analysisData;
	}
}
