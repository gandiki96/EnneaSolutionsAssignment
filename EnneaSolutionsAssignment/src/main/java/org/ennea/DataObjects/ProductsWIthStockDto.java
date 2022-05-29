package org.ennea.DataObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductsWIthStockDto {
	
	@JsonProperty("Product")
	private String product;
	
	@JsonProperty("stock")
	private Integer stock;
	
	@JsonProperty("supplier")
	private String supplier;
	
	
	

}
