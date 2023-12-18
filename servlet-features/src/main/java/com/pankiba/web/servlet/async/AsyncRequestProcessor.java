package com.pankiba.web.servlet.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncRequestProcessor implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(AsyncRequestProcessor.class);

	private AsyncContext asyncContext;
	
	private int secs;

	public AsyncRequestProcessor() {
	}

	public AsyncRequestProcessor(AsyncContext asyncContext, int secs) {
		this.asyncContext = asyncContext;
		this.secs = secs;
	}

	@Override
	public void run() {
		
		logger.info("Async Supported : " + asyncContext.getRequest().isAsyncSupported());
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		longProcessing(secs);
		
		try {
			PrintWriter out = asyncContext.getResponse().getWriter();
			stopWatch.stop();
			out.write("Processing done successfully in (HH:mm:ss.SSS) "+stopWatch);
			logger.info("Processing done successfully in (HH:mm:ss.SSS) "+stopWatch);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		asyncContext.complete();
	}

	private void longProcessing(int secs) {
		try {
			Thread.sleep(secs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
