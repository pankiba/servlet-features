package com.pankiba.web.listener.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(ServletContextListenerImpl.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		logger.info(" ServletContext Initialized : " + servletContextEvent.getServletContext().getServerInfo());

		// create the thread pool
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(100));
		servletContextEvent.getServletContext().setAttribute("threadPoolExecutor", threadPoolExecutor);
		
		logger.info(" ThreadPoolExecutor details ");
		logger.info("{}", threadPoolExecutor);

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		logger.info(" ServletContext Destroyed ");

		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) servletContextEvent.getServletContext()
				.getAttribute("threadPoolExecutor");
		threadPoolExecutor.shutdown();
	}

}
