package com.pankiba.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pankiba.web.servlet.async.AsyncLongRunningServlet;

@WebServlet("/long-running-servlet")
public class LongRunningServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(AsyncLongRunningServlet.class);

	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		logger.info("LongRunningServlet::doGet processing start");

		String time = httpServletRequest.getParameter("time");

		int milliSeconds = Integer.valueOf(time);
		longProcessing(milliSeconds);
		
		PrintWriter out = httpServletResponse.getWriter();
		stopWatch.stop();

		out.write("Processing done successfully in (HH:mm:ss.SSS) "+stopWatch);

		logger.info("LongRunningServlet::doGet processing ends in (HH:mm:ss.SSS) "+stopWatch);

	}

	private void longProcessing(int secs) {
		try {
			Thread.sleep(secs);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

}
