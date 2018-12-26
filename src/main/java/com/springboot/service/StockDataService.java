package com.springboot.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.controller.StockLogController;
import com.springboot.domain.StockData;
import com.springboot.domain.StockRepository;

@Service
public class StockDataService
{
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	StockLogController stockLogController;

	public List<StockData> GetLastYearStockData(String symbol)
	{
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = sdf.format(cal.getTime());
		stockLogController.Log("GetAllStockData " + startTime);
		return stockRepository.findBySymbolAndDateGreaterThanOrderByDateDesc(symbol, startTime);
	}
}
