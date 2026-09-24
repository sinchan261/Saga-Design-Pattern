package com.example.productsservice.products.Web.Controller;

import com.example.CoreService.CoreApplication.Dao.Product;
import com.example.productsservice.products.Dto.ProductCreationRequest;
import com.example.productsservice.products.Dto.ProductCreationResponse;
import com.example.productsservice.products.Services.ProductServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Getter
@Setter
public class ProductsController {
    @Autowired
    private ProductServiceImpl productService;

    public ProductsController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreationResponse save(@RequestBody  ProductCreationRequest request) {
        var product = new Product();
        BeanUtils.copyProperties(request, product);
        Product result = productService.save(product);

        var productCreationResponse = new ProductCreationResponse();
        BeanUtils.copyProperties(result, productCreationResponse);
        return productCreationResponse;
    }
}
