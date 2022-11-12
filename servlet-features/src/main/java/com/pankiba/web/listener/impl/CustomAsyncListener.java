package com.pankiba.web.listener.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebListener
public class CustomAsyncListener implements AsyncListener {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomAsyncListener.class);

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		logger.info("AppAsyncListener onComplete");
	}

	@Override
	public void onTimeout(AsyncEvent asyncEvent) throws IOException {

		logger.info("AppAsyncListener onTimeout");

		// we can send appropriate response to client
		ServletResponse response = asyncEvent.getAsyncContext().getResponse();
		PrintWriter out = response.getWriter();
		out.write("TimeOut Error in Processing");
	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
		logger.info("AppAsyncListener onError");
	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		logger.info("AppAsyncListener onStartAsync");
	}

}
