package org.ennea.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
	private String productCode = "N/A";
	
	@Column(name = "product_name")
	private String productName = "N/A";
	
	@Column(name = "stock")
	private Integer stock = 0;
	
	@Column(name = "deal")
	private Integer deal = 0;
	
	@Column(name = "free_count")
	private Integer free = 0;
	
	@Column(name = "mrp")
	private Double mrp = 0.0;
	
	@Column(name = "rate")
	private Double rate = 0.0;
	
	@Column(name = "expiry_date")	
	private String date = "/ /";
	
	@Column(name = "batch")
	private String batch = "N/A";
	
	@Column(name = "company")
	private String company = "N/A";
	
	@Column(name = "supplier")
	private String supplier = "N/A";	

}
