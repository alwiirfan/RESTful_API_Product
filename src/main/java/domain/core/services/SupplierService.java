package domain.core.services;

import domain.core.models.entity.Supplier;
import domain.core.models.repositorry.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public Supplier findByID(Long id){
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.orElse(null);
    }

    public Iterable<Supplier> findAll(){
        return supplierRepository.findAll();
    }

    public void remove(Long id){
        supplierRepository.deleteById(id);
    }

}
