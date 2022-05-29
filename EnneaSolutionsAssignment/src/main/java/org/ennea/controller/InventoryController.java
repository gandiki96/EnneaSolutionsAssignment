package org.ennea.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ennea.DataObjects.FetchStockBySupplierRequestDto;
import org.ennea.DataObjects.ProductsListDto;
import org.ennea.DataObjects.ProductsWIthStockDto;
import org.ennea.model.InventoryModel;
import org.ennea.service.InventoryService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	InventoryService inventoryService;

	@PostMapping("/uploadCsv")
	public ResponseEntity<String> uploadInventoryDataToDB(@RequestParam("file") MultipartFile file) throws Exception {

		try {

			log.info("InventoryController - uploadInventoryDataToDB - Start");

			if (inventoryService.uploadInventoryDataToDB(file)) {
				log.info("InventoryController - uploadInventoryDataToDB - End");
				return new ResponseEntity<>("Data Uploaded to DB Successfully", HttpStatus.OK);
			}

			else {
				log.info("InventoryController - uploadInventoryDataToDB - End");
				return new ResponseEntity<>("Failed to Upload data to DB", HttpStatus.BAD_REQUEST);

			}

		} catch (Exception e) {
			log.error("Exception in InventoryController - uploadInventoryDataToDB " + e.getLocalizedMessage());
			throw (e);

		}

	}

	@SuppressWarnings("null")
	@PostMapping("/fetchStock")
	public ResponseEntity<List<ProductsWIthStockDto>> fetchStockBySupplier(
			@RequestParam(required = false)String product,
			@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer size,
			@RequestBody FetchStockBySupplierRequestDto request) throws Exception {

		try {

			log.info("InventoryController - fetchStockBySupplier - Start");
			pageNo = pageNo != null ? pageNo : 1;
			size = size != null ? size : 10;
			List<ProductsWIthStockDto> productList = inventoryService.fetchStockBySupplier(request,product,pageNo,size);
			log.info("InventoryController - fetchStockBySupplier - End");
			return new ResponseEntity<>(productList, HttpStatus.OK);

		} catch (Exception e) {
			log.error("Exception in InventoryController - uploadInventoryDataToDB " + e.getLocalizedMessage());
			throw (e);

		}

	}
	
	@GetMapping("/products")
	
	public ResponseEntity<List<InventoryModel>> getProducts(@RequestParam(required = false) Integer pageNo,
			@RequestParam(required = false) Integer size
			) throws Exception {

		try {
			log.info("InventoryController - getProducts - Start");
			pageNo = pageNo != null ? pageNo : 1;
			size = size != null ? size : 10;
			List<InventoryModel> productsList = inventoryService.getProducts(pageNo, size);
			log.info("InventoryController - getProducts - End ");			
			return new ResponseEntity<>(productsList, HttpStatus.OK);

		} catch (Exception e) {
			log.error("Exception in InventoryController - getProducts " + e.getLocalizedMessage());
			throw (e);

		}

	}

}
