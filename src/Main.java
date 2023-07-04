import entity.Product;
import org.w3c.dom.Text;
import service.impl.ProductServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.getData();
        Scanner obj = new Scanner(System.in);
        Scanner obj1 = new Scanner(System.in);
        Scanner obj2 = new Scanner(System.in);
        int select;
        do {
            System.out.println("1. Get list products");
            System.out.println("2. Get product by id");
            System.out.println("3. Search product");
            System.out.println("4. Delete product by id");
            System.out.println("5. Update product");
            System.out.println("6. Create new product");
            System.out.println("Choose your option: ");
            select = obj.nextInt();
            switch (select){
                case 1:
                    productService.getAll();
                    break;
                case 2:
                    System.out.println("Enter id:");
                    String id = obj1.nextLine();
                    productService.getById(id);
                    break;
                case 3:
                    System.out.println("Enter search string: ");
                    String param = obj1.nextLine();
                    productService.searchBy(param);
                    break;
                case 4:
                    System.out.println("Enter id: ");
                    int deleteId = Integer.parseInt(obj1.nextLine());
                    productService.deleteById(deleteId);
                    break;
                case 5:
                    System.out.println("Enter id:");
                    String idUpdate = obj1.nextLine();
                    System.out.println("Enter new product name:");
                    String nameUpdate = obj1.nextLine();
                    System.out.println("Enter new manufacturer:");
                    String manuUpdate = obj1.nextLine();
                    System.out.println("Enter new category:");
                    String cateUpdate = obj1.nextLine();
                    System.out.println("Enter new price:");
                    BigDecimal priceUpdate = obj2.nextBigDecimal();
                    productService.update(idUpdate, nameUpdate, manuUpdate, cateUpdate, priceUpdate);
                    break;
                case 6:
                    System.out.println("Enter id:");
                    String idCreate = obj1.nextLine();
                    System.out.println("Enter product name:");
                    String nameCreate = obj1.nextLine();
                    System.out.println("Enter manufacturer:");
                    String manuCreate = obj1.nextLine();
                    System.out.println("Enter category:");
                    String cateCreate = obj1.nextLine();
                    System.out.println("Enter price:");
                    BigDecimal priceCreate = obj2.nextBigDecimal();
                    productService.addNewItem(idCreate, nameCreate, manuCreate, cateCreate, priceCreate);
                    break;
                default:
                    break;
            }
        } while (select <= 6);{
            System.out.println("Cannot found!");
        }
    }
}