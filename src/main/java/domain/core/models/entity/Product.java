package domain.core.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    @Column(name = "product_name", length = 100)
    private String name;

    @NotEmpty(message = "Description is required")
    @Column(name = "product_description", length = 500)
    private String description;

    private double price;

    @ManyToOne // untuk relasi antar table
    private Category category;

    @ManyToMany
    @JoinTable(name = "product_of_supplier", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    private Set<Supplier> suppliers;

    public Product() {
    }

    public Product(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
