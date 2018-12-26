package com.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.StockApplication;

@RestController
public class StockLogController
{
	private Logger logger = LoggerFactory.getLogger(StockApplication.class);
	
	public void Log(String log)
	{
		logger.info(log);
	}
}
