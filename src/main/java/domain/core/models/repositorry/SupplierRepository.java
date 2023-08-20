package domain.core.models.repositorry;

import domain.core.models.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
