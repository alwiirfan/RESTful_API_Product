package domain.core.services;

import domain.core.models.entity.Product;
import domain.core.models.entity.Supplier;
import domain.core.models.repositorry.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    // save = bisa untuk create dan untuk update
    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product findByID(Long id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public void remove(Long id){
        productRepository.deleteById(id);
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findByID(productId);
        if (product == null){
            throw new RuntimeException("Product with ID : " + productId + " not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

    public Product findProductByName(String name){
        return productRepository.findProductByName(name);
    }

    public List<Product> findProductByNameLike(String name){
        return productRepository.findProductByNameLike("%" + name + "%");
    }

    public List<Product> findByCategory(Long categoryId){
        return productRepository.findProductByCategory(categoryId);
    }

    public List<Product> findBySupplier(Long supplierId){
        Supplier supplier = supplierService.findByID(supplierId);
        if (supplier == null){
            return new ArrayList<>();
        }
        return productRepository.findProductBySupplier(supplier);
    }
}
