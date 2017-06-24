package servlet;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import client.*;

@WebServlet("/ListModelsServlet")
public class ListModelsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String title = "List of Available Models";
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		CarModelOptionsIO clientAccess = new CarModelOptionsIO();
		ArrayList<String> modelList = clientAccess.getAutoList();
	
		out.write(docType + "<HTML><HEAD><TITLE>" + title + "</TITLE></HEAD>\n");
		out.write("<H1 ALIGN=CENTER>"+ title + "</H1>\n");
		out.write("<form action=\"ListOptionSetsServlet\" method=\"GET\">");
		out.write("<center><select name=\"model\" id=\"model\">");
		for(int i = 0; i < modelList.size(); i++){
		out.write("<Option border=\"1\" value=\"" + modelList.get(i) + "\">" + modelList.get(i) + "</Option>");
		}
		out.write("</select></Center>");
		out.write("<center><input type=submit name=chosenModel></center>");
		out.write("</form>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
