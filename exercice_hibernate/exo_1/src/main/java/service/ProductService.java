package service;

import impl.ProductDao;
import model.Product;

import java.time.LocalDate;

public class ProductService {

    private static ProductDao productDao;

    public ProductService(){
        productDao = new ProductDao();
    }

    public boolean addProduct(String brand, String reference, LocalDate purchaseDate, Double price, Integer stock){

        Product product = new Product( brand, reference, purchaseDate, price, stock );
        return productDao.create(product);
    }

    public boolean removeProduct(Long id){
        return productDao.delete(id);
    }

    public Product findProductById(Long id){
        Product p = productDao.getById(id);
        return p;
    }

    public boolean updateProduct(Product p){
        return productDao.update(p);
    }

}
