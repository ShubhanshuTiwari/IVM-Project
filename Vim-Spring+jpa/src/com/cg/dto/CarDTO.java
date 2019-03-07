package com.cg.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Car")
public class CarDTO
{
//	TODO:1 Oracle db is using strategy = GenerationType.SEQUENCE
//	TODO:2 MySQL db is using strategy = GenerationType.AUTO

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private int id;
	
	@Column(name = "MAKE")
    private String make;
	
	@Column(name = "MODEL")
    private String model;
	
	@Column(name = "MODEL_YEAR")
    private String modelYear;

    public CarDTO()
    {
        this.id = -1;
        this.make = "";
        this.model = "";
        this.modelYear = "";        
    }
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }
    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }
    public String getModelYear()
    {
        return modelYear;
    }

    public void setModelYear(String modelYear)
    {
        this.modelYear = modelYear;
    }
}
