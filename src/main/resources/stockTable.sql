CREATE DATABASE IF NOT EXISTS `stock_database`;
USE `stock_database`;
CREATE TABLE IF NOT EXISTS `stock_data_table`
(
	`stock_symbol` 		VARCHAR(10),
	`stock_info_date` 	DATE,
	`open_price` 		FLOAT,
	`close_price` 		FLOAT,
	`high_price` 		FLOAT,
	`low_price` 		FLOAT,
	`open_price` 		BIGINT(20),
    PRIMARY KEY  (`stock_symbol`, `stock_info_date`)
);