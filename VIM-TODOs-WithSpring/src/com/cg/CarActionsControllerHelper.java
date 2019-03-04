package com.cg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.beans.CarDTO;
import com.cg.dao.CarDAO;
import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

@Controller
@RequestMapping("/controller")
public class CarActionsControllerHelper {
	private static final String ACTION_KEY = "action";
	private static final String VIEW_CAR_LIST_ACTION = "viewCarList";
	private static final String ADD_CAR_ACTION = "addCar";
	private static final String SAVE_CAR_ACTION = "saveCar";
	private static final String EDIT_CAR_ACTION = "editCar";
	private static final String DELETE_CAR_ACTION = "deleteCar";
	private static final String ERROR_KEY = "errorMessage";

	@Autowired
	private CarDAO carDAORef;
	
	@RequestMapping(method=RequestMethod.GET)
	public String handleActions(ModelMap map, 
			@RequestParam(value="action") String action){
		String destinationPage = "";
		
		if(action.equals(VIEW_CAR_LIST_ACTION)){
		
			List<CarDTO> cars = carDAORef.findAll();
			map.addAttribute("carList", cars);
			destinationPage = "carList";
		} else if(action.equals(ADD_CAR_ACTION)){
			System.out.println("inside Add Car");
			destinationPage = "carForm";
		}
		
		return destinationPage;
	}

	@RequestMapping(method=RequestMethod.POST)
	public String saveCar(@ModelAttribute("car") CarDTO car, ModelMap map){
		String destinationPage = "carList"; 
		
		carDAORef.create(car);
		List<CarDTO> cars = carDAORef.findAll();
		map.addAttribute("carList", cars);
		
		return destinationPage;
	}

	@ModelAttribute("car")
	public CarDTO createCar(){
		return new CarDTO();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
