package com.springboot.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Table(name="stock_data_table")
@IdClass(StockDataId.class)
public class StockData
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "stock_symbol")
	private String symbol;
	@Id
	@Column(name = "stock_info_date")
	private String date;
	@Column(name = "open_price")
	private float openPrice;
	@Column(name = "close_price")
	private float closePrice;
	@Column(name = "high_price")
	private float highPrice;
	@Column(name = "low_price")
	private float lowPrice;
	@Column(name = "trade_volume")
	private long tradeVolume;
	
	public String getSymbol()
	{
		return symbol;
	}
	public void setSymbol(String symbol)
	{
		this.symbol = symbol;
	}
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public float getOpenPrice()
	{
		return openPrice;
	}
	public void setOpenPrice(float openPrice)
	{
		this.openPrice = openPrice;
	}
	public float getClosePrice()
	{
		return closePrice;
	}
	public void setClosePrice(float closePrice)
	{
		this.closePrice = closePrice;
	}
	public float getHighPrice()
	{
		return highPrice;
	}
	public void setHighPrice(float highPrice)
	{
		this.highPrice = highPrice;
	}
	public float getLowPrice()
	{
		return lowPrice;
	}
	public void setLowPrice(float lowPrice)
	{
		this.lowPrice = lowPrice;
	}
	public long getTradeVolume()
	{
		return tradeVolume;
	}
	public void setTradeVolume(long tradeVolume)
	{
		this.tradeVolume = tradeVolume;
	}
}