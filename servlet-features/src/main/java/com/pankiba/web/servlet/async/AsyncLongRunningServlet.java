package com.pankiba.web.servlet.async;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pankiba.web.listener.impl.CustomAsyncListener;

@WebServlet(urlPatterns = "/async-long-running-servlet", asyncSupported = true)
public class AsyncLongRunningServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(AsyncLongRunningServlet.class);

	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		logger.info("AsyncLongRunningServlet::doGet processing start");

		httpServletRequest.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);

		String time = httpServletRequest.getParameter("time");
		int milliSeconds = Integer.valueOf(time);

		AsyncContext asyncCtx = httpServletRequest.startAsync();
		asyncCtx.addListener(new CustomAsyncListener());
		asyncCtx.setTimeout(9000);

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) httpServletRequest.getServletContext()
				.getAttribute("threadPoolExecutor");
		threadPoolExecutor.execute(new AsyncRequestProcessor(asyncCtx, milliSeconds));

		stopWatch.stop();
		logger.info("AsyncLongRunningServlet::doGet processing ends in (HH:mm:ss.SSS) " + stopWatch);

	}

}
