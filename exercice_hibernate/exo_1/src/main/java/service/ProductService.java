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

    public List<Product> productsWithPriceMin (Double price){
        return productDao.productsWithPriceMin(price);
    }

    public List<Product> productsBuyBetween (LocalDate date1, LocalDate date2){

        List <Product> products = productDao.productsFilterByDate(date1, date2);

        if (date2.isBefore(date1)){
            if (products.isEmpty()){
                System.out.println("Aucun produit a afficher");
            }
        } else {
            System.out.println("Saisi des dates incorrect");
        }
        return products;
    }

    public List<Product> filterByStock(Integer stock) {

        List<Product>products = productDao.filterByStockMin(stock);

        if (products.isEmpty()){
            System.out.println("Aucun produit a afficher");
        }

        return products;
    }

    public List<Product> filterByBrand(String brand) {

        List<Product>products = productDao.filterByBrand(brand);

        if (products.isEmpty()){
            System.out.println("Aucun produit a afficher");
        }

        return products;
    }
}
