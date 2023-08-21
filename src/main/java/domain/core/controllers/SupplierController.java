package domain.core.controllers;

import domain.core.dto.ResponseData;
import domain.core.dto.SupplierDTO;
import domain.core.models.entity.Supplier;
import domain.core.services.SupplierService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create_supplier")
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors){

        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class); // with dependency ModelMapper

        responseData.setStatus(true);
        List<String> messages = responseData.getMessages();
        messages.add("success to create supplier");
        responseData.setMessages(messages);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);

    }

    @GetMapping("/find_all_suppliers")
    public Iterable<Supplier> findAll(){
        return supplierService.findAll();
    }

    @GetMapping("/find_supplier_by_id/{id}")
    public Supplier findByID(@PathVariable("id") Long id){
        return supplierService.findByID(id);
    }

    @PutMapping("/update_supplier")
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors){

        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class); // with dependency ModelMapper

        responseData.setStatus(true);
        List<String> messages = responseData.getMessages();
        messages.add("success to create supplier");
        responseData.setMessages(messages);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);

    }

    @DeleteMapping("/delete_supplier/{id}")
    public void delete(@PathVariable("id") Long id){
        supplierService.remove(id);
    }
}
