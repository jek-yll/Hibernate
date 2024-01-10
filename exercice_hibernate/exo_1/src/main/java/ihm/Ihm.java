package ihm;

import impl.ProductDao;
import model.Product;
import service.ProductService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ihm {

    private static ProductService productService = new ProductService();
    private static Scanner sc = new Scanner(System.in);

    public static void start(){
        boolean running = true;
        while (running) {
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Modifier un produit");
            System.out.println("3. Supprimer un produit");
            System.out.println("4. Afficher un produit");
            System.out.println("5. Afficher tous les produits");
            System.out.println("6. Filtre : prix minimum");
            System.out.println("7. Filtre : date");
            System.out.println("8. Filtre : stock");
            System.out.println("9. Filtre : marque");
            System.out.println("10. Stock total des produits d'une marque");
            System.out.println("11. Supprimer les produits d'une marque");
            System.out.println("12. Calcul du prix moyen des produits");
            System.out.println("13. Ajouter une image");
            System.out.println("0. Quitter");

            System.out.print("Choix : ");

            int choice = sc.nextInt();
            sc.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    findProduct();
                    break;
                case 5:
                    getAllProduct();
                    break;
                case 6:
                    filterByPriceMin();
                    break;
                case 7:
                    filterByDate();
                    break;
                case 8:
                    filterByStock();
                    break;
                case 9:
                    filterByBrand();
                    break;
                case 10:
                    stockByBrand();
                    break;
                case  11:
                    deleteByBrand();
                    break;
                case 12:
                    getAvgPrice();
                    break;
                case 13:
                    addImage();
                    break;
                case 0:
                    running = false;
                    ProductDao.close();
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
        }
        System.out.println("Au revoir !");
    }

    private static void addProduct(){
        System.out.println("#### AJOUTER UN PRODUIT ####");
        System.out.println("Saisir la reference : ");
        String ref = sc.nextLine();
        System.out.println("Saisir la marque : ");
        String brand = sc.nextLine();
        System.out.println("Saisir le prix : ");
        Double price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Saisir la date d'achat (jj-mm-aaa): ");
        String date = sc.nextLine();
        LocalDate dateFormat = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("Saisir le stock initial : ");
        Integer stock = sc.nextInt();

        if (productService.addProduct(brand, ref, dateFormat, price, stock)){
            System.out.println("Produit " + ref + " ajouté avec succès");
        } else {
            System.out.println("Echec lors de l'ajout du produit");
        };
    }

    private static void deleteProduct(){
        System.out.println("#### SUPPRIMER UN PRODUIT ####");
        System.out.println("Saisir l'identifiant du produit à supprimer : ");
        Long id = sc.nextLong();
        sc.nextLine();

        if (productService.removeProduct(id)){
            System.out.println("Produit supprimé avec succès");
        } else {
            System.out.println("Echec lors de la suppression du produit");
        }
    }

    private static void findProduct(){
        System.out.println("#### RECHERCHER UN PRODUIT (id)####");
        System.out.println("Saisir l'identifiant du produit recherché : ");

        Long id = sc.nextLong();
        sc.nextLine();

        Product product = productService.findProductById(id);

        if (product != null){
            System.out.println(product);
        } else {
            System.out.println("Echec lors de la recherche du produit");
        }
    }

    private static void updateProduct(){
        System.out.println("#### MODIFICATION  D'UN PRODUIT ####");
        System.out.println("Saisir l'identifiant du produit à modifier : ");
        Long id = sc.nextLong();
        sc.nextLine();

        Product product = productService.findProductById(id);

        if (product != null){
            System.out.println("Produit à modifier : " + product);

            System.out.println("Saisir la reference : ");
            String ref = sc.nextLine();
            System.out.println("Saisir la marque : ");
            String brand = sc.nextLine();
            System.out.println("Saisir le prix : ");
            Double price = sc.nextDouble();
            sc.nextLine();
            System.out.println("Saisir la date d'achat (jj-mm-aaa): ");
            String date = sc.nextLine();
            LocalDate dateFormat = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            System.out.println("Saisir le stock initial : ");
            Integer stock = sc.nextInt();

            product.setReference(ref);
            product.setBrand(brand);
            product.setPrice(price);
            product.setStock(stock);
            product.setPurchaseDate(dateFormat);

            if (productService.updateProduct(product)){
                System.out.println("Produit modifié avec succès");
            } else {
                System.out.println("Echec lors de la modification du produit");
            };
        } else {
            System.out.println("Echec lors de la recherche du produit");
        }

    }

    private static void getAllProduct(){
        System.out.println("#### LISTES DES PRODUITS ####");
        List<Product> products = productService.getAllProduct();

        if (products.isEmpty()){
            System.out.println("Aucun produit à afficher");
        } else {
            for (Product p: products) {
                System.out.println(p);
            }
        }
    }

    private static void filterByPriceMin(){
        System.out.println("#### FILTRE : PRIX MIN ####");
        List<Product> productsFiltered = new ArrayList<>();
        System.out.println("Saisir un prix : ");
        Double price = sc.nextDouble();
        sc.nextLine();

        productsFiltered = productService.productsWithPriceMin(price);

        if (productsFiltered.isEmpty()){
            System.out.println("Aucun produit à plus de "+ price);
        } else {
            for (Product p : productsFiltered){
                System.out.println(p);
            }
        }
    }

    private static void filterByDate(){
        System.out.println("#### FILTRE : DATE ####");
        System.out.println("Recherche des produits acheté entre le (jj-mm-aaa) :");
        String dateMin = sc.nextLine();
        System.out.println("Et le (jj-mm-aaa): ");
        String dateMax = sc.nextLine();

        LocalDate dateMinFormat = LocalDate.parse(dateMin, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate dateMaxFormat = LocalDate.parse(dateMax, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        List<Product> products = productService.productsBuyBetween(dateMinFormat, dateMaxFormat);
        for (Product p : products) {
            System.out.println(p);
        }
    }

    private static void filterByStock(){
        System.out.println("#### FILTRE : STOCK ####");
        System.out.println("Recherche des produits ayant un stock inférieur à : ");
        Integer stock = sc.nextInt();
        sc.nextLine();

        List<Product> products = productService.filterByStock(stock);

        if (products.isEmpty()){
            System.out.println("Aucun produit à afficher");
        } else {
            for (Product p : products){
                System.out.println("Produit : " + p.getBrand() + " " + p.getReference());
            }
        }
    }

    private static void filterByBrand(){
        System.out.println("#### FILTRE : MARQUE ####");
        System.out.println("Recherche des produits de la marque : ");
        String brand = sc.nextLine();

        List<Product> products = productService.filterByBrand(brand);

        if (products.isEmpty()){
            System.out.println("Aucun produit à afficher");
        } else {
            for (Product p : products){
                System.out.println("Produit : " + p);
            }
        }
    }

    private static void stockByBrand(){
        System.out.println("#### STOCK PAR MARQUE ####");
        System.out.println("Recherche du stock total de la marque : ");
        String brand = sc.nextLine();

        System.out.println("Stock total des produits de la marque " + brand + " : " + productService.getSockByBrand(brand));
    }

    private static void deleteByBrand(){
        System.out.println("#### SUPPRESSION PAR MARQUE ####");
        System.out.println("Quelle marque souhaitez vous retirer : ");
        String brand = sc.nextLine();

        if (productService.deleteByBrand(brand)) {
            System.out.println("Suppression des produits de la marque " + brand + " effectué avec succes ");
        } else {
            System.out.println("Echec lors de la suppression des produits");
        }
    }

    private static void getAvgPrice(){
        System.out.println("#### MOYENNE DE PRIX DES PRODUITS ####");
        System.out.println(productService.getAvgPrice());
    }

    private static void addImage(){
        System.out.println("#### AJOUTER UNE IMAGE ####");
        System.out.println("Saisir l'URL de l'image : ");
        String url = sc.nextLine();
        System.out.println("Saisir l'identifiant du produit : ");
        Long id = sc.nextLong();
        sc.nextLine();

        Product product = productService.findProductById(id);

        if (product == null){
            System.out.println("Le produit recherché n'existe pas");
        } else {
            if (productService.addImageToProduct(product, url)){
                System.out.println("Image ajouté au produit " + id);
            } else {
                System.out.println("Erreur lors de l'ajout d'une image");
            };
        }
    }

}
