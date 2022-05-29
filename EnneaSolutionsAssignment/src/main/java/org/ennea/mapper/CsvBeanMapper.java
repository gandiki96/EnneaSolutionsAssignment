package org.ennea.mapper;



import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
public class CsvBeanMapper{

    @CsvBindByName(column = "code")
    private String code;
    
    @CsvBindByName(column = "name")
    private String name;
    
    @CsvBindByName(column = "batch")
    private String batch;
    
    @CsvBindByName(column = "stock")
    private String stock;
    
    @CsvBindByName(column = "deal")
    private String deal;
	@CsvBindByName(column = "free")
    private String free;
    
    @CsvBindByName(column = "mrp")
    private String mrp;
    
    @CsvBindByName(column = "rate")
    private String rate;
    
    @CsvBindByName(column = "exp")
    private String exp;
    
    @CsvBindByName(column = "company")
    private String company;
    
    @CsvBindByName(column = "supplier")
    private String supplier;

}
