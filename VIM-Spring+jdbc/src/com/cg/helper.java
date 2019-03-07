package com.cg;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.dao.CarDAO;
import com.cg.dto.CarDTO;

@Controller
@RequestMapping("/controller")
public class helper {

	private static final String VIEW_CAR_LIST_ACTION = "viewCarList";
	private static final String ADD_CAR_ACTION = "addCar";
	private static final String SAVE_CAR_ACTION = "saveCar";
	private static final String EDIT_CAR_ACTION = "editCar";
	private static final String DELETE_CAR_ACTION = "deleteCar";
	private static final String ERROR_KEY = "errorMessage";

	@Autowired
	CarDAO carDAORef;

	@RequestMapping(method = RequestMethod.GET)
	public String helperAction(@RequestParam("action") String action,
			@RequestParam(value = "id", required = false) String id, ModelMap map,
			@ModelAttribute("car") CarDTO car) {
		String destinationPage = "";

		if (action.equals(VIEW_CAR_LIST_ACTION))
		{
			List<CarDTO> cars = new LinkedList<CarDTO>();
               cars = carDAORef.findAll();
			map.addAttribute("carList", cars);
			destinationPage = "carList";
		}
		
		else if (action.equals(ADD_CAR_ACTION))
		{
			System.out.println("inside add");
			destinationPage = "carForm";
		}
		
		else if (action.equals(EDIT_CAR_ACTION))
		{
			car = carDAORef.findById(Integer.parseInt(id));
			map.addAttribute("car", car);
			destinationPage = "carForm";
		}
		
		return destinationPage;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveCar(@RequestParam("action") String action,@ModelAttribute("car") CarDTO car,@RequestParam("id") String ids[], ModelMap map) {
		String destinationPage = "carList";

		if(action.equals(SAVE_CAR_ACTION))
		{
		
		if(carDAORef.findById(car.getId())==null)
		carDAORef.create(car);
		else
			carDAORef.update(car);
		}
		else if(action.equals(DELETE_CAR_ACTION))
		{
			carDAORef.delete(ids);
		}
		

		List<CarDTO> cars = new LinkedList<CarDTO>();

		cars = carDAORef.findAll();
		map.addAttribute("carList", cars);
		return destinationPage;
	}

	@ModelAttribute("car") // attribute ki ID is car
	public CarDTO createCar() {
		return new CarDTO(); // object is cardto
	}

}
