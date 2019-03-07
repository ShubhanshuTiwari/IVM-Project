package com.cg.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.dao.CarDAO;
import com.cg.dto.CarDTO;


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

@Repository("repo")
public class JDBCCarDAO implements CarDAO {
	//TODO 3 Declare a local variable datasource of type DataSource follow encapsulation principle
	
	DataSource datasource=null;
	@Autowired
	public void setDataSource(DataSource ds)
	{
		System.out.println("setting dataSource");
		datasource=ds;
		/*try {
			System.out.println(datasource.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public JDBCCarDAO()  {
	
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
				connection=datasource.getConnection();
				PreparedStatement selectStatement = connection.prepareStatement(insertQuery);
				selectStatement.setString(1, car.getMake());
				selectStatement.setString(2, car.getModel());
				selectStatement.setString(3, car.getModelYear());
				selectStatement.executeUpdate();
				//TODO 6 
				//Get a connection using datasource
				//Start the JDBC transaction
				//Create a PreparedStatement using insertQuery
				//Set the parameters of the PreparedStatement
				//Invoke appropriate API of JDBC to update and commit the record
				
			} 
			catch (SQLException e) {
//				e.printStackTrace();
				
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
			try {
				throw new JDBCDaoException("SQL error while excecuting this query: "+ insertQuery,e);
			} catch (JDBCDaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	/**
	 * This method is mapped to DELETE_CAR_ACTION
	 * @param ids collection of CAR ids. 
	 */
	public void delete(String[] ids) {
		Connection connection = null;
		String deleteQuery = "delete from car where id=?";

		try{
			try {
				 PreparedStatement deleteStatement;
				 connection=datasource.getConnection();
				 connection.setAutoCommit(true);
				 for(String i: ids)
				 {
					deleteStatement=connection.prepareStatement(deleteQuery);
					deleteStatement.setInt(1,Integer.parseInt(i));
					deleteStatement.executeUpdate();
				 }
					//TODO 7 
				//Get a connection using datasource
				//Start the JDBC transaction
				//Create a PreparedStatement using deleteQuery
				//Set the parameters of the PreparedStatement
				//Invoke appropriate API of JDBC to update and commit the record
				
			} 
			catch (SQLException e) {
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
		String updateQuery = "update car set make=?,model=?,model_year=? where id=?";
		Connection connection = null;
		
		try{
			try {
				connection=datasource.getConnection();
				PreparedStatement selectStatement = connection.prepareStatement(updateQuery);
				selectStatement.setString(1, car.getMake());
				selectStatement.setString(2, car.getModel());
				selectStatement.setString(3, car.getModelYear());
				selectStatement.setInt(4, car.getId());
				selectStatement.executeUpdate();
				//TODO 8 
				//Get a connection using datasource
				//Start the JDBC transaction
				//Create a PreparedStatement using updateQuery
				//Set the parameters of the PreparedStatement
				//Invoke appropriate API of JDBC to update and commit the record
				
			} 
			catch (SQLException e) {
				if(connection != null)
					connection.rollback();
				
				throw e;
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
	public List<CarDTO> findAll() {
		List<CarDTO> carList = new ArrayList<CarDTO>();
        Statement st;
        ResultSet result;
		Connection connection = null;
		String selectQuery = "select * from CAR";
		
		try{
			try {
				connection=datasource.getConnection();
				st=connection.createStatement();
				result = st.executeQuery(selectQuery);
				
				while(result.next()) {
				CarDTO car = new CarDTO();
				car.setId(result.getInt("id"));
				car.setMake(result.getString("make"));
				car.setModel(result.getString("model"));
				car.setModelYear(result.getString("MODEL_YEAR"));
				carList.add(car);
				}
				
				//TODO 9 
				//Get a connection using datasource
				//Don't start the JDBC transaction
				//Create a Statement using selectQuery
				//Invoke appropriate API of JDBC to fire the query
				//For iteration over the ResultSet populate carList with CarDTO 

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
		String selectQuery = "select * from CAR where id=?";
		CarDTO car = null;
		Connection connection = null;
		try{
			try {
				connection = datasource.getConnection();
				connection.setAutoCommit(true);
				PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
				selectStatement.setInt(1, id);
				ResultSet result = selectStatement.executeQuery();
				
				System.out.println(id);
				if(result.next())
				{
				car=	new CarDTO();
				car.setId(result.getInt("id"));
				car.setMake(result.getString("make"));
				car.setModel(result.getString("model"));
				car.setModelYear(result.getString("MODEL_YEAR"));
				}
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
