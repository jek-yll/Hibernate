package model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long id;

    private String brand;
    private String reference;
    private LocalDate purchaseDate;
    private Double price;
    private Integer stock;

    public Product() {
    }

    public Product(String brand, String reference, LocalDate purchaseDate, Double price, Integer stock) {
        this.brand = brand;
        this.reference = reference;
        this.purchaseDate = purchaseDate;
        this.price = price;
        this.stock = stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Produit => " +
                "identifiant : " + id +
                ", marque : '" + brand + '\'' +
                ", référence : '" + reference + '\'' +
                ", date d'achat : " + purchaseDate +
                ", prix : " + price +
                ", stock : " + stock ;
    }
}
