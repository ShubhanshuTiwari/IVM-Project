package com.cg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.dto.CarDTO;
import com.vim.dao.CarDAO;

@Controller
@RequestMapping("/controller")
public class CarActionsControllerHelper {

	private static final String VIEW_CAR_LIST_ACTION = "viewCarList";
	private static final String ADD_CAR_ACTION = "addCar";
	private static final String SAVE_CAR_ACTION = "saveCar";
	private static final String EDIT_CAR_ACTION = "editCar";
	private static final String DELETE_CAR_ACTION = "deleteCar";
	// private static final String ERROR_KEY = "errorMessage";

	@Autowired
	private CarDAO carDAORef;

	@RequestMapping(method = RequestMethod.GET)
	public String handleActions(@ModelAttribute("car") CarDTO car, ModelMap map,
			@RequestParam(value = "action") String action, @RequestParam(value = "id", required = false) String id) {
		String destinationPage = "";
		if (action.equals(VIEW_CAR_LIST_ACTION)) {
			System.out.println("inside view CAr");
			List<CarDTO> carList = new ArrayList<CarDTO>();
			carList = carDAORef.findAll();
			map.addAttribute("carList", carList);
			destinationPage = "carList";
		} else if (action.equals(ADD_CAR_ACTION)) {
			System.out.println("inside Add Car");
			destinationPage = "carForm";
		} else if (action.equals(EDIT_CAR_ACTION)) {
			int x = Integer.parseInt(id);
			car = carDAORef.findById(x);
			map.addAttribute("car", car);
			System.out.println("inside editCar");
			destinationPage = "carForm";
		}
		return destinationPage;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String AddCar(@ModelAttribute("car") CarDTO car, ModelMap map, @RequestParam(value = "action") String action,
			@RequestParam(value = "id") String[] ids) {
		String destinationPage = "carList";
		if (action.equals(SAVE_CAR_ACTION)) {
			if (carDAORef.findById(car.getId()) == null) {
				System.out.println("inside save car");
				carDAORef.create(car);
			} else {
				carDAORef.update(car);
			}
		} else if (action.equals(DELETE_CAR_ACTION)) {
			System.out.println("inside delete");
			carDAORef.delete(ids);
		}
		List<CarDTO> cars = carDAORef.findAll();
		map.addAttribute("carList", cars);

		return destinationPage;
	}

	@ModelAttribute("car")
	public CarDTO createCar() {
		return new CarDTO();
	}

}
