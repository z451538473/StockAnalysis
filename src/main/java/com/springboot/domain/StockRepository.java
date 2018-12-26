package com.springboot.domain;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<StockData, StockDataId>
{
	List<StockData> findBySymbolAndDateGreaterThanOrderByDateDesc(String symbol, String date);
}
