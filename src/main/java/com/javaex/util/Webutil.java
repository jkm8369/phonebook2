package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Webutil {

	
	//필드
	
	
	//생성자
	public Webutil() {}
	
	//메소드gs
	
	//forward(request, response,"/list.jsp");
	//redirect(request, response, "http://");
	
	//메소드 일반
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException{
		response.sendRedirect(url);
	}
	
	
}
