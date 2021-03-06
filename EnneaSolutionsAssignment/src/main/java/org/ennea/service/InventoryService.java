package org.ennea.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.ennea.DataObjects.FetchStockBySupplierRequestDto;
import org.ennea.DataObjects.ProductsWIthStockDto;
import org.ennea.mapper.CsvBeanMapper;
import org.ennea.model.InventoryModel;
import org.ennea.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class InventoryService {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	InventoryRepository inventoryRepository;

	public boolean uploadInventoryDataToDB(MultipartFile files) throws Exception {
		try {
			log.info("InventoryService - uploadInventoryDataToDB - Start");
			if (mapToCsvNamedBean(files, CsvBeanMapper.class)) {
				return true;
			}

			log.info("InventoryService - uploadInventoryDataToDB - End");
		} catch (Exception e) {
			log.error("Exception in  InventoryService - uploadInventoryDataToDB " + e.getLocalizedMessage());
			throw (e);
		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean mapToCsvNamedBean(MultipartFile file, Class<CsvBeanMapper> csvBeanMapperClass) throws Exception {

		try {
			log.info("InventoryService - mapToCsvNamedBean - Start");
			Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
			CsvToBean<CsvBeanMapper> csvToBean = new CsvToBeanBuilder(reader).withType(csvBeanMapperClass)
					.withIgnoreLeadingWhiteSpace(true).withIgnoreEmptyLine(true).build();
			List<CsvBeanMapper> parsedInventoryListFromCsv = (List<CsvBeanMapper>) csvToBean.parse();

			String jsonStr = mapper.writeValueAsString(parsedInventoryListFromCsv);

			InventoryModel[] modelArray = mapper.readValue(jsonStr, InventoryModel[].class);

			List<InventoryModel> resultList = Arrays.asList(modelArray);

			if (resultList != null && !resultList.isEmpty()) {
				inventoryRepository.saveAll(resultList);
				return true;
			}
			log.info("InventoryService - mapToCsvNamedBean - End");
		} catch (Exception e) {
			log.error("Exception in  InventoryService - mapToCsvNamedBean " + e.getLocalizedMessage());
			throw (e);
		}
		return false;
	}

	public List<ProductsWIthStockDto> fetchStockBySupplier(FetchStockBySupplierRequestDto request, String product,
			Integer page, Integer size) {
		List<ProductsWIthStockDto> result = null;
		Page<InventoryModel> inventoryResultSet = null;

		try {
			log.info("InventoryService - fetchStockBySupplier - Start");
			inventoryResultSet = inventoryRepository.fetchWithSupplierData(request.getSuppliers(),
					PageRequest.of(page, size));			
			result = inventoryResultSet.stream().map(x -> {
				ProductsWIthStockDto dto = new ProductsWIthStockDto();
				dto.setProduct(x.getProductName() != null ? x.getProductName() : "Not Specified");
				dto.setStock(x.getStock());
				dto.setSupplier(x.getSupplier());
				return dto;
			}).collect(Collectors.toList());// collect(Collectors.groupingBy(ProductsWIthStockDto::getSupplier));
			if (product != null && !product.isEmpty()) {

				result = result.stream().filter(p -> p.getProduct().equalsIgnoreCase(product))
						.collect(Collectors.toList());

			}
			result.sort(Comparator.comparing(ProductsWIthStockDto::getSupplier));
			log.info("InventoryService - fetchStockBySupplier - End");

		} catch (Exception e) {
			log.error("Exception in  InventoryService - fetchStockBySupplier " + e.getLocalizedMessage());
			throw (e);
		}
		return result;
	}

	public List<InventoryModel> getProducts(Integer page, Integer size) {
		// TODO Auto-generated method stub

		Page<InventoryModel> productsModel = null;
		List<InventoryModel> productsModelList = null;
		try {
			log.info("InventoryService - getProducts - Start");
			productsModel = inventoryRepository.getProducts(PageRequest.of(page, size));
			if (productsModel.getContent() != null)
				productsModelList = productsModel.stream().collect(Collectors.toList());

			log.info("InventoryService - getProducts - End");

		} catch (Exception e) {
			log.error("Exception in  InventoryService - getProducts " + e.getLocalizedMessage());
		}
		return productsModelList;
	}

}
