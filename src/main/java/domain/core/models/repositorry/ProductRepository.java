package domain.core.models.repositorry;

import domain.core.models.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByNameContains(String name); // unutuk mencari product berdasarkan nama product
}
