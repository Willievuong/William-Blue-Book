package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.SelectCarOption;
import model.Automobile;
import model.Option;
import util.FileIO;

@WebServlet("/ChoiceBuilder")
public class ChoiceBuilder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SelectCarOption clientAccess = new SelectCarOption();
		Automobile car = clientAccess.findCar(request.getParameter("car"));
		FileIO priceCheck = new FileIO();
		String model = car.getName();
		String paint = request.getParameter("paint");
		String transmission = request.getParameter("transmission");
		String traction = request.getParameter("traction");
		String airBag = request.getParameter("airBag");
		String moonRoof = request.getParameter("moonRoof");

		car.addNewChoice(paint, priceCheck.priceCheckIndividual(paint, 0));
		car.addNewChoice(transmission, priceCheck.priceCheckIndividual(transmission, 1));
		car.addNewChoice(traction, priceCheck.priceCheckIndividual(traction, 2));
		car.addNewChoice(airBag, priceCheck.priceCheckIndividual(airBag, 3));
		car.addNewChoice(moonRoof, priceCheck.priceCheckIndividual(moonRoof, 4));
		
		request.setAttribute("theCar", car);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/sumPage.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
