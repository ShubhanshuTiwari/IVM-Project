/*package com.cg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.cg.dao.CarDAO;
import com.cg.dto.CarDTO;


public class ControllerServlet extends HttpServlet {
	private static final String ACTION_KEY = "action";
	private static final String VIEW_CAR_LIST_ACTION = "viewCarList";
	private static final String ADD_CAR_ACTION = "addCar";
	private static final String SAVE_CAR_ACTION = "saveCar";
	private static final String EDIT_CAR_ACTION = "editCar";
	private static final String DELETE_CAR_ACTION = "deleteCar";
	private static final String ERROR_KEY = "errorMessage";
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		processRequest(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		processRequest(request, response);
	
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_KEY);
		String destinationPage = null;
		
		CarDAO carDAO;
		
		ApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		
		carDAO = appContext.getBean("repo",CarDAO.class);
		
		RequestDispatcher rd;
		// perform action
		if (VIEW_CAR_LIST_ACTION.equals(actionName)) {
			List<CarDTO> carList = new ArrayList<CarDTO>();
			carList = carDAO.findAll();
			request.setAttribute("carList", carList);
			destinationPage = "carList.jsp";
		} else if (ADD_CAR_ACTION.equals(actionName)) {

			CarDTO car = new CarDTO();
			request.setAttribute("car", car);
			destinationPage = "carForm.jsp";

		} else if (EDIT_CAR_ACTION.equals(actionName)) {
			int id = Integer.parseInt(request.getParameter("id"));
			CarDTO c = carDAO.findById(id);
			request.setAttribute("car", c);
			destinationPage = "carForm.jsp";

		} else if (SAVE_CAR_ACTION.equals(actionName)) {
			CarDTO car=new CarDTO();
			
			car.setId(Integer.parseInt(request.getParameter("id")));
			car.setMake(request.getParameter("make"));
			car.setModel(request.getParameter("model"));
			car.setModelYear(request.getParameter("modelYear"));
			//System.out.println(car.getId());
			if(carDAO.findById(car.getId())==null) {
				carDAO.create(car);
				
			}
			else
			{
				carDAO.update(car);
			}
			List<CarDTO> carList1 = new ArrayList<CarDTO>();
			carList1=carDAO.findAll();
			request.setAttribute("carList", carList1);
			destinationPage="carList.jsp";
			// TODO 7
			// Create a new CarDTO
			// set the properties of the DTO from request parameters
			// If it is a new car then invoke create api of DAO else update api
			// Get all the Cars using DAO
			// Set the found cars in request with name as 'carList'
			// Set the destination page to carList.jsp

		} else if (DELETE_CAR_ACTION.equals(actionName)) {
			
		String[] ids=request.getParameterValues("id");
			carDAO.delete(ids);
			List<CarDTO> carList1 = new ArrayList<CarDTO>();
			carList1=carDAO.findAll();
			request.setAttribute("carList", carList1);
			destinationPage="carList.jsp";
			// TODO 8
			// Get the ids of all cars to be deleted from request
			// Use appropriate api of DAO to delete all cars
			// Get all the Cars using DAO
			// Set the found cars in request with name as 'carList'
			// Set the destination page to carList.jsp

		} else {
			String errorMessage = "[" + actionName + "] is not a valid action.";
			request.setAttribute(ERROR_KEY, errorMessage);
		}

		rd = request.getRequestDispatcher(destinationPage);
		rd.forward(request, response);
		// TODO 9 Use appropriate Servlet API to forward the request to
		// appropriate destination page set in above if else blocks depending on action.

	}
}
*/