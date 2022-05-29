package org.ennea.DataObjects;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class FetchStockBySupplierRequestDto {
	
	@JsonProperty("suppliers")
	List<String> suppliers;

}
