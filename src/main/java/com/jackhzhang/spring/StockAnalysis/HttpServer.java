package com.jackhzhang.spring.StockAnalysis;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class HttpServer extends NanoHTTPD
{

	public HttpServer()
	{
		super(8080);
		// TODO Auto-generated constructor stub
		System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
	}

	@Override
	public Response serve(IHTTPSession session)
	{
		Method method = session.getMethod();
		String uri = session.getUri();
		// HelloServer.LOG.info(method + " '" + uri + "' ");

		String msg = "<html><body><h1>Hello server</h1>\n";
		Map<String, String> parms = session.getParms();
		if (parms.get("username") == null)
		{
			msg += "<form action='?' method='get'>\n" + "  <p>Your name: <input type='text' name='username'></p>\n"
					+ "</form>\n";
		}
		else
		{
			String input = parms.get("username");
			msg += "<p>Hello, " + parms.get("username") + "!</p>";
			try
			{
				StockRequest req = new StockRequest(input);
			}
			catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		msg += "</body></html>\n";

		return newFixedLengthResponse(msg);
	}
}
