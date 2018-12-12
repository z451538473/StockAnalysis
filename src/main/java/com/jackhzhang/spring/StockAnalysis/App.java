package com.jackhzhang.spring.StockAnalysis;

import fi.iki.elonen.util.ServerRunner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ServerRunner.run(HttpServer.class);
    }
}
