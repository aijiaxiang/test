package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		//读取请求参数
//		String uname = req.getParameter("uname");
//		/*
//		 * 告诉浏览器，服务器返回的数据的类型
//		 */
//		res.setContentType("text/html");
//		//通过response获得输出流
//		PrintWriter out = res.getWriter();
//		//输出
//		out.println("<div style='font-size:60px;'>" +
//				"Hello " + uname + "</div>");
//		out.close();
		System.out.println("请求方式："+req.getMethod());
		System.out.println("访问路径："+req.getServletPath());
		System.out.println("协议类型："+req.getProtocol());
		Enumeration<String> e = req.getHeaderNames();
		while(e.hasMoreElements()){
			String key = e.nextElement();
			String value = req.getHeader(key);
			System.out.println(key+":"+value);
		}
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String now = sdf.format(date);
		out.println("<p>"+now+"</p>");
		out.close();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
