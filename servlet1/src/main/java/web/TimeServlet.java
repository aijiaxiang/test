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
//		//��ȡ�������
//		String uname = req.getParameter("uname");
//		/*
//		 * ��������������������ص����ݵ�����
//		 */
//		res.setContentType("text/html");
//		//ͨ��response��������
//		PrintWriter out = res.getWriter();
//		//���
//		out.println("<div style='font-size:60px;'>" +
//				"Hello " + uname + "</div>");
//		out.close();
		System.out.println("����ʽ��"+req.getMethod());
		System.out.println("����·����"+req.getServletPath());
		System.out.println("Э�����ͣ�"+req.getProtocol());
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
