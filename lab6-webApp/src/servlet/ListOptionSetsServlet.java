package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import adapter.BuildAuto;
import adapter.CreateAuto;
import client.CarModelOptionsIO;
import client.SelectCarOption;
import model.Automobile;

@WebServlet("/ListOptionSetsServlet")
public class ListOptionSetsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String title = "List of Available Options For Model";
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n";
		String makerModel = request.getParameter("model");
		String[] splitter = makerModel.split(" ");
		String model = splitter[1];
		SelectCarOption clientAccess = new SelectCarOption();
		Automobile userConfig = new Automobile();
		userConfig = clientAccess.findCar(model);
		model = userConfig.getModel();
		request.setAttribute("car", model);
		int x = 0;
		
		/// lab6-app/WEB-INF/lib/sumPage.jsp\
	

		out.write(docType + "<HTML><HEAD><TITLE>" + title + "</TITLE></HEAD>\n <H1 ALIGN=CENTER>" + title
				+ "</H1><Center>");
		out.write("<form action=\"ChoiceBuilder\" method=\"GET\">");
		out.write("<table border=\"1\"><tr><th>Make/Models</th><th>" + makerModel + "</th></tr>");

		out.write("<tr><th>Paint</th><th><select id=\"paint\" name=\"paint\">");
		for (int i = 0; i < userConfig.getOpSetSize(); i++) {
			out.write("<Option value=\"" + userConfig.getOpSetList().get(i).getOpt().get(x).getName() + "\">"
					+ userConfig.getOpSetList().get(i).getOpt().get(x).getName() + "</Option>\n");
		}
		x++;
		out.write("</select></th></tr>");

		out.write("<tr><th>Transmission</th><th><select id=\"transmission\" name=\"transmission\">");
		out.write("<Option value=\"" + "automatic" + "\">" + "automatic" + "</Option>\n");
		out.write("<Option value=\"" + "manual" + "\">" + "manual" + "</Option>\n");
		x++;
		out.write("</select></th></tr>");

		out.write("<tr><th>ABS/Traction Control</th><th><select id=\"traction\" name=\"traction\">");
		out.write("<Option value=\"" + "standard" + "\">" + "standard" + "</Option>\n");
		out.write("<Option value=\"" + "ABS" + "\">" + "ABS" + "</Option>\n");
		out.write("<Option value=\"" + "ABSAT" + "\">" + "ABSAT" + "</Option>\n");
		x++;
		out.write("</select></th></tr>");
 
		out.write("<tr><th>Side Impact Airbag</th><th><select id=\"airBag\" name=\"airBag\">");
		out.write("<Option value=\"" + "present" + "\">" + "Yes" + "</Option>\n");
		out.write("<Option value=\"" + "notpresent" + "\">" + "No" + "</Option>\n");
		x++;
		out.write("</select></th></tr>");

		out.write("<tr><th>Power MoonRoof</th><th><select id=\"moonRoof\" name=\"moonRoof\">");
		out.write("<Option value=\"" + "present" + "\">" + "Yes" + "</Option>\n");
		out.write("<Option value=\"" + "notpresent" + "\">" + "No" + "</Option>\n");
		x++;
		out.write("</select></th></tr>");
		out.write("</table>");
		out.write("</center>");
		out.write("<center><input type=submit name=chosenChoice></center>");
		out.write("</form>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
