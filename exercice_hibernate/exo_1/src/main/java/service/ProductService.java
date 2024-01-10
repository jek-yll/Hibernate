package service;

import impl.ProductDao;
import model.Image;
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

    public List<Product> productsBuyBetween (LocalDate dateMin, LocalDate dateMax){

        List <Product> products = productDao.productsFilterByDate(dateMin, dateMax);

        if (dateMin.isBefore(dateMax)){
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

    public boolean deleteByBrand(String brand){
        return productDao.deleteByBrand(brand);
    }

    public Long getSockByBrand(String brand){
        return productDao.getTotalStockByBrand(brand);
    }

    public Double getAvgPrice(){
        return productDao.getAvgPrice();
    }

    public Boolean addImageToProduct(Product product, String url) {

        Image image = new Image();
        image.setUrl(url);

        return productDao.addImageToProduct(image, product);
    }
}
