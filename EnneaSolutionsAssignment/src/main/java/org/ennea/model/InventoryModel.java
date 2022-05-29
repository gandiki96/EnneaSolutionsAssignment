package org.ennea.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "inventory",schema = "enneadev")
public class InventoryModel {
	
	@Id
	@Column(name = "id")	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "product_code")	
	private String productCode;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "stock")
	private Integer stock;
	
	@Column(name = "deal")
	private Integer deal;
	
	@Column(name = "free_count")
	private Integer free;
	
	@Column(name = "mrp")
	private Double mrp;
	
	@Column(name = "rate")
	private Double rate;
	
	@Column(name = "expiry_date")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private String date;
	
	@Column(name = "batch")
	private String batch;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "supplier")
	private String supplier;
	
	
	
	

}
