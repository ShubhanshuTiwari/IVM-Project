package com.cg.dao.impl;

import com.cg.dao.CarDAO;
import com.cg.dto.CarDTO;
import com.cg.util.ServiceLocator;
import com.cg.util.ServiceLocatorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

//TODO 1 Import appropriate classes based on following TODOs
//Follow TODOs (if available)
/**
 * 
 * This is a JDBCCarDAO class
 * @see java.lang.Object
 * @author Abhishek
 * 
 *
 */
 
 //TODO 2 Implement appropriate Interface
public class JDBCCarDAO  implements CarDAO{
	//TODO 3 Declare a local variable datasource of type DataSource follow encapsulation principle
	

	DataSource dataSource;
	public JDBCCarDAO()  {
		try {
			dataSource=ServiceLocator.getDataSource("jdbc/VIMDataSource");
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("inside jdbc constructor::"+dataSource);
		//TODO 4 Initialize the dataSource in TODO 3 using ServiceLocator API
		//TODO 5 If any error occur in getting this service then throw ServiceLocatorException
		//with error message as 'Container Service not available'
	
		
	}

	
	@Override
	/**
	 * This method is mapped to ADD_CAR_ACTION
	 * @param car a CarDTO 
	 */
	public void create(CarDTO car) {
		// TODO Auto-generated method stub
		Connection connection = null;

		String insertQuery = "insert into Car (MAKE,MODEL,MODEL_YEAR) values(?,?,?)";
		
		try{
			try {
				System.out.println("Inside jdbc create::"+dataSource);
				connection=dataSource.getConnection();
				System.out.println("Connection established::"+connection);
				PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

				insertStatement.setString(1, car.getMake());
				insertStatement.setString(2, car.getModel());
				insertStatement.setString(3, car.getModelYear());
				insertStatement.executeUpdate();
				connection.commit();

				
				//TODO 6 
				//Get a connection using datasource
				//Start the JDBC transaction
				//Create a PreparedStatement using insertQuery
				//Set the parameters of the PreparedStatement
				//Invoke appropriate API of JDBC to update and commit the record
				
			}
			 catch (SQLException e) {
					// e.printStackTrace();

					if (connection != null)
						connection.rollback();
					throw e;
				}
			finally {
				if (connection != null)
					connection.close();			
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	@Override
	/**
	 * This method is mapped to DELETE_CAR_ACTION
	 * @param ids collection of CAR ids. 
	 */
	public void delete(String[] ids) {
		Connection connection = null;
		String deleteQuery = "delete from Car where id=?";

		try{
			try {
				connection=dataSource.getConnection();
				for(String id:ids)
				{
				PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
				deleteStatement.setInt(1,Integer.parseInt(id));
				deleteStatement.executeUpdate();
				}
				

				//TODO 7 
				//Get a connection using datasource
				//Start the JDBC transaction
				//Create a PreparedStatement using deleteQuery
				//Set the parameters of the PreparedStatement
				//Invoke appropriate API of JDBC to update and commit the record
				
			} 
			finally {
				if (connection != null)
					connection.close();				
			}
		}
		catch(SQLException e){
			try {
				throw new JDBCDaoException("SQL error while excecuting query: "+ deleteQuery,e);
			} catch (JDBCDaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
	}

	@Override
	/**
	 * This method is mapped to EDIT_CAR_ACTION
	 * @param car a CarDTO 
	 */
	public void update(CarDTO car) {
		// TODO Auto-generated method stub
		String updateQuery = "update Car set make=?,model=?,model_year=? where id=?";
		Connection connection = null;
		
		try{
			try {
				//TODO 8 
				//Get a connection using datasource
				//Start the JDBC transaction
				//Create a PreparedStatement using updateQuery
				//Set the parameters of the PreparedStatement
				//Invoke appropriate API of JDBC to update and commit the record
				connection=dataSource.getConnection();
				PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
				updateStatement.setString(1, car.getMake());
				updateStatement.setString(2, car.getModel());
				updateStatement.setString(3, car.getModelYear());
				updateStatement.executeUpdate();
				connection.commit();

				
				
			}
			finally {
				if (connection != null)
					connection.close();			
			}
		}
		catch(SQLException e){
			try {
				throw new JDBCDaoException("SQL error while excecuting query: "+ updateQuery,e);
			} catch (JDBCDaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	@Override
	/**
	 * This method is mapped to VIEW_CAR_LIST_ACTION
	 * @return List list of cars 
	 */
	public List<CarDTO> findAll() {		List<CarDTO> carList = new ArrayList<CarDTO>();

	Connection connection = null;

	String selectQuery = "select * from Car";

	try {
		try {
			// TODO 9
			// Get a connection using datasource
			connection = dataSource.getConnection();
			System.out.println("connection established find");
			// Don't start the JDBC transaction

			// Create a Statement using selectQuery
			Statement selectStatement = connection.createStatement();
			System.out.println("connection established find1");
			
			// Invoke appropriate API of JDBC to fire the query
			ResultSet result;
			System.out.println("connection established find2");
			
			result = selectStatement.executeQuery(selectQuery);
			System.out.println("connection established find3");
			
			// For iteration over the ResultSet populate carList with CarDTO

			while (result.next()) {
				System.out.println("connection established find4");
				
				CarDTO carDTO = new CarDTO();
				carDTO.setId(result.getInt(1));
				carDTO.setMake(result.getString(2));
				carDTO.setModel(result.getString(3));
				carDTO.setModelYear(result.getString(4));

				carList.add(carDTO);
			}
			System.out.println("connection established find5");
			
		} finally {
			if (connection != null)
				connection.close();
		}
	} catch (SQLException e) {
		try {
			throw new JDBCDaoException("SQL error while excecuting query: " + selectQuery, e);
		} catch (JDBCDaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	return carList;
	}

	@Override
	/**
	 * This method is utility method for finding car by id.
	 * @param id id of the car to be searched
	 * @return  CarDTO A CarDTO
	 */
	public CarDTO findById(int id) {
		// TODO Auto-generated method stub
		String selectQuery = "select * from Car where id=?";
		CarDTO car = new CarDTO();
		Connection connection = null;
		
		try{
			try {
				connection = dataSource.getConnection();
				connection.setAutoCommit(true);
				PreparedStatement selectStatement = connection
				.prepareStatement(selectQuery);
				selectStatement.setInt(1, id);
				ResultSet result = selectStatement.executeQuery();
				result.next();

				car.setId(result.getInt("id"));
				car.setMake(result.getString("make"));
				car.setModel(result.getString("model"));
				car.setModelYear(result.getString("MODEL_YEAR"));
			} 
			finally {
				if (connection != null)
					connection.close();			
			}
		}
		catch(SQLException e){
			try {
				throw new JDBCDaoException("SQL error while excecuting query: "+ selectQuery,e);
			} catch (JDBCDaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		

		return car;
	}

	

}
