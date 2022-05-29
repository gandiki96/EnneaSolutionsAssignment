package org.ennea.repository;

import java.util.List;

import org.ennea.model.InventoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, Long>{

	List<InventoryModel> findBySupplierIn(List<String> suppliers);

	
	@Query(value = "select * from enneadev.inventory where enneadev.inventory.supplier in :suppliers and enneadev.inventory.stock > 0", nativeQuery = true)
	Page<InventoryModel> findBySupplier(@Param("suppliers")List<String> suppliers, Pageable page);

	@Query(value ="select * from enneadev.inventory et where et.expiry_date != '/  /' AND cast(et.expiry_date as date) > now()",nativeQuery = true)
	Page<InventoryModel> getProducts(Pageable page);

	

}
