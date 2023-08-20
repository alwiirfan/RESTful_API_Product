package domain.core.services;

import domain.core.models.entity.Category;
import domain.core.models.repositorry.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Category findByID(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void remove(Long id){
        categoryRepository.deleteById(id);
    }

}
