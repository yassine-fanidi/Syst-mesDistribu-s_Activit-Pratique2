package org.fanidiyassine.billingservice.web;

import org.fanidiyassine.billingservice.entities.Bill;
import org.fanidiyassine.billingservice.feign.CustomerServiceRestClient;
import org.fanidiyassine.billingservice.feign.InventoryServiceRestClient;
import org.fanidiyassine.billingservice.repositories.BillRepository;
import org.fanidiyassine.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillRestController {
    private BillRepository billRepository;
    private ProductItemRepository  productItemRepository;
    private CustomerServiceRestClient customerServiceRestClient;
    private InventoryServiceRestClient  inventoryServiceRestClient;

    public BillRestController(BillRepository billRepository,
                              ProductItemRepository productItemRepository,
                              CustomerServiceRestClient customerServiceRestClient,
                              InventoryServiceRestClient inventoryServiceRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerServiceRestClient = customerServiceRestClient;
        this.inventoryServiceRestClient = inventoryServiceRestClient;
    }

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerServiceRestClient.findByCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryServiceRestClient.getProduct(productItem.getProductId()));
        });
        return bill;
    }
}
