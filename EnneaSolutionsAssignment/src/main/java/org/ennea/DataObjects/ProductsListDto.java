package org.ennea.DataObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties
@Data
public class ProductsListDto {

	@JsonProperty("code")
	private String code;

	@JsonProperty("name")
	private String productName;
	
	@JsonProperty("stock")
	private Integer stock;
	
	@JsonProperty("mrp")
	private Integer mrp;
	
	@JsonProperty("deal")
	private Integer deal;
	
	@JsonProperty("free")
	private Integer free;
	
	@JsonProperty("rate")
	private Integer rate;
	
	@JsonProperty("batch")
	private String batch;
	
	@JsonProperty("company")
	private String company;
	
	@JsonProperty("supplier")
	private String supplier;
	
	@JsonProperty("expiry")
	private String expiryDate;
	

}
