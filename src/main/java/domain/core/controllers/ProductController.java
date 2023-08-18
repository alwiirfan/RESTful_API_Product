package domain.core.controllers;

import domain.core.models.entity.Product;
import domain.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create_product")
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/find_all_products")
    public Iterable<Product> findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/find_product_by_id/{id}")
    public Product findByIDProduct(@PathVariable("id") Long id){
        return productService.findByID(id);
    }

    @PutMapping("/update_product")
    public Product update(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete_product/{id}")
    public void delete(@PathVariable("id") Long id){
        productService.remove(id);
    }

    @GetMapping("/")
    public List<Product> findByName(String name){
        return productService.findByName(name);
    }
}
