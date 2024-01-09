package ihm;

import impl.ProductDao;
import model.Product;
import service.ProductService;

import java.time.LocalDate;
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
            System.out.println("5. Quitter");
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
        LocalDate date = LocalDate.now();
        System.out.println("Saisir le stock initial : ");
        Integer stock = sc.nextInt();

        if (productService.addProduct(brand, ref, date, price, stock)){
            System.out.println("Produit " + ref + "ajouté avec succès");
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
            //TODO Demander une date STRING convertir en LOCALDATE
            LocalDate date = LocalDate.now();
            System.out.println("Saisir le stock initial : ");
            Integer stock = sc.nextInt();

            product.setReference(ref);
            product.setBrand(brand);
            product.setPrice(price);
            product.setStock(stock);
            product.setPurchaseDate(date);

            if (productService.updateProduct(product)){
                System.out.println("Produit modifié avec succès");
            } else {
                System.out.println("Echec lors de la modification du produit");
            };
        } else {
            System.out.println("Echec lors de la recherche du produit");
        }

    }

}
