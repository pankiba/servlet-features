package com.pankiba.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/with-push-builder")
public class PushBuilderServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(PushBuilderServlet.class);

	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		
		logger.info("PushBuilderServlet::doGet with pushBuilder ");
		
		PushBuilder pushBuilder = httpServletRequest.newPushBuilder();
		
		if (pushBuilder != null) {
			logger.info(" pushbuilder activated ");
			pushBuilder.path("images/apachetomcat.jpeg").addHeader("content-type", "image/png").push();
		}

	}
}
