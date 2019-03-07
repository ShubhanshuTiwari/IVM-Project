package com.vim.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dto.CarDTO;
import com.vim.dao.CarDAO;

@Repository
@Transactional
public class JPACarDAO implements CarDAO{
	
    @PersistenceContext
	private EntityManager  entityManager;
   
    public JPACarDAO() {
    	System.out.println("JPACarDAO instantiated...");
    }
    
	@Override
	@Transactional
	public void create(CarDTO car) {
	
		try {
	     System.out.println("saving car");
			System.out.println(car.getModel());
					entityManager.persist(car);
						entityManager.close();
					
				System.out.println("After commit .................");
			
		} catch (RuntimeException e) {
			//tx.rollback();
			throw e;
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public void delete(String[] ids) {
		
	
		try {
				for(String sID : ids){
				CarDTO car = entityManager.find(CarDTO.class, Integer.parseInt(sID));
				entityManager.remove(car);
			}
		} catch (RuntimeException e) {
		
		throw e;
		} finally {
		entityManager.close();
		}		
	}
	
	@Override
	public List<CarDTO> findAll() {
		
		try {
			Query query = entityManager.createQuery(
			"select car from CarDTO car");
	
			List<CarDTO> ls=(List<CarDTO>)query.getResultList();
			return ls;
		} finally {
		entityManager.close();
		}
	}
	
	@Override
	public CarDTO findById(int id) {
	
		CarDTO car = entityManager.find(CarDTO.class, id);
		return car;
	}
	
	@Override
	public void update(CarDTO car) {
		
	
		try {
			
			entityManager.merge(car);
			
			
			System.out.println("After commit .................");
			
		} catch (RuntimeException e) {
			
			throw e;
		} finally {
			entityManager.close();
		}
	}
}
