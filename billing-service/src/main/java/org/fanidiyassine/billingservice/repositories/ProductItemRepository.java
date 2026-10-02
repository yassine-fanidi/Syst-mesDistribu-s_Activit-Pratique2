package org.fanidiyassine.billingservice.repositories;

import org.fanidiyassine.billingservice.entities.ProductItem;
import org.fanidiyassine.billingservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
    List<ProductItem> findByBillId(Long billId);
}
