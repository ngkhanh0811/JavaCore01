import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ProductServiceImpl productService = new ProductServiceImpl();
        Scanner obj = new Scanner(System.in);
        int select = 10;
        do {
            try {
                System.out.println("1. Get list products");
                System.out.println("2. Get product by id");
                System.out.println("3. Search product");
                System.out.println("4. Delete product by id");
                System.out.println("5. Update product");
                System.out.println("6. Create new product");
                System.out.println("7. End program");
                System.out.println("Choose your option: ");
                select = Integer.parseInt(obj.nextLine().trim());
                switch (select) {
                    case 1:
                        productService.getAll();
                        break;
                    case 2:
                        productService.getById();
                        break;
                    case 3:
                        productService.searchByName();
                        break;
//                    case 4:
//                        productService.deleteItem();
//                        break;
                    case 5:
                        productService.update();
                        break;
                    case 6:
                        productService.addNewItem();
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Cannot found, the program will rollback to menu!");
            }
        } while (select != 7);
        {
            System.out.println("Program ended!");
        }
    }
}