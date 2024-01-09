package service;

import impl.ProductDao;
import model.Product;

import java.time.LocalDate;
import java.util.List;

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
        return productDao.getById(id);
    }
    public boolean updateProduct(Product p){
        return productDao.update(p);
    }

    public List<Product> getAllProduct(){
        return productDao.getALL();
    }
}
