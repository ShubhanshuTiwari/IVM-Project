package com.cg.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.cg.dao.CarDAO;
import com.cg.dto.CarDTO;

public class InMemoryCarDAO implements CarDAO{
	
	Map<Integer,CarDTO> map=null;
	
	public InMemoryCarDAO()
	{
	map=new HashMap<Integer,CarDTO>();
	
	
	}

	@Override
	public List<CarDTO> findAll() {
		// TODO Auto-generated method stub
		List<CarDTO> carList=new ArrayList<CarDTO>(map.values());
		return carList;
	}

	@Override
	public CarDTO findById(int id) {
		// TODO Auto-generated method stub
		CarDTO car=null;
		car=map.get(id);
		return car;
	}

	@Override
	public void create(CarDTO car) {
		// TODO Auto-generated method stub
		CarDTO c=map.putIfAbsent(car.getId(),car);
		
	}

	@Override
	public void update(CarDTO car) {
		// TODO Auto-generated method stub
		
		CarDTO c=map.put(car.getId(),car);
		
	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub
		for(String s:ids)
		{
			CarDTO c=map.remove(Integer.parseInt(s));
		}
	}

}
