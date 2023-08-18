package domain.core.models.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tbl_product")
public class Product implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    private String product_name;

    private String description;

    private String price;

    public Product() {
    }

    public Product(Long id_product, String product_name, String description, String price) {
        this.id_product = id_product;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
