package com.pankiba.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/without-push-builder")
public class ImageLoaderServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ImageLoaderServlet.class);

	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws ServletException, IOException {
		
		logger.info("ImageLoaderServlet::doGet : without PushBuilder");
		
		try (PrintWriter respWriter = httpServletResponse.getWriter();) {
			respWriter.write("<html>" + "<img src='images/apachetomcat.jpeg'>" + "</html>");
		}

	}
}
