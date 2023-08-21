package domain.core.controllers;

import domain.core.dto.CategoryDTO;
import domain.core.dto.ResponseData;
import domain.core.models.entity.Category;
import domain.core.services.CategoryService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create_category")
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryDTO categoryDTO, Errors errors){

        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = modelMapper.map(categoryDTO, Category.class);

        responseData.setStatus(true);
        List<String> messages = new ArrayList<>();
        messages.add("create category successfully");
        responseData.setMessages(messages);
        responseData.setPayload(categoryService.save(category));
        return ResponseEntity.ok(responseData);

    }

}
