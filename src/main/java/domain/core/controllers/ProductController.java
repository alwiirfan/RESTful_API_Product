package domain.core.controllers;

import domain.core.dto.ResponseData;
import domain.core.models.entity.Product;
import domain.core.models.entity.Supplier;
import domain.core.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create_product")
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        List<String> messages = responseData.getMessages();
        messages.add("add Product Successfully");
        responseData.setMessages(messages);
        responseData.setPayload(productService.save(product)); // di sini juga masih ada error internal server error
        return ResponseEntity.ok(responseData);
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
    public ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        List<String> messages = responseData.getMessages();
        messages.add("add Product Successfully");
        responseData.setMessages(messages);
        responseData.setPayload(productService.save(product)); // di sini juga masih ada error internal server error
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/delete_product/{id}")
    public void delete(@PathVariable("id") Long id){
        productService.remove(id);
    }

    @GetMapping("/find_by_name")
    public List<Product> findByName(String name){
        return productService.findByName(name);
    }

    @PostMapping("/add_supplier/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productService.addSupplier(supplier,productId);
    }
}
