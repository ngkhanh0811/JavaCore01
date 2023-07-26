import entity.Product;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductServiceImpl<T> {
    Scanner obj1 = new Scanner(System.in);

    public void displayHeader() {
        System.out.printf("%-25s", "Id ");
        System.out.printf("%-25s", "Product name");
        System.out.printf("%-25s", "Manufacturer");
        System.out.printf("%-25s", "Category");
        System.out.printf("%-25s", "Price");
        System.out.println();
    }

    public void displayProduct(Product p) {
        System.out.printf("%-25s", p.getId());
        System.out.printf("%-25s", p.getName());
        System.out.printf("%-25s", p.getManufacturer());
        System.out.printf("%-25s", p.getCategory());
        System.out.printf("%-25s", p.getPrice());
        System.out.println();
    }

    public BufferedReader readFile(){
        BufferedReader reader = null;
        try {
            File myObj = new File("D:\\Training-TASC\\CRUDTextFile\\src\\data.txt");

            if (!myObj.exists()) {
                myObj.createNewFile();
                System.out.println("File created successfully.");
            }
            reader = new BufferedReader(new FileReader("D:\\Training-TASC\\CRUDTextFile\\src\\data.txt"));
        } catch (Exception e){
            System.err.println(e);
        }
        return reader;
    }

    public void getAll() throws IOException {
        Product product;
        String line;
        BufferedReader myReader = readFile();
        displayHeader();
        while ((line = myReader.readLine()) != null) {
            try {
                String[] arr = line.split(",");
                String idProduct = arr[0];
                String name = arr[1];
                String manufac = arr[2];
                String category = arr[3];
                BigDecimal price = new BigDecimal(arr[4]);
                product = new Product(idProduct, name, manufac, category, price);
                displayProduct(product);
            } catch (Exception e) {
                System.err.println();
            }
        }
            myReader.close();
    }

    public String getId(){
        do {
            System.out.println("Enter id:");
            String id = obj1.nextLine().trim();
            if (id.matches("[1-9]") && !id.isEmpty()) {
                return id;
            } else{
                System.err.println("Please enter number for id!");
            }
        } while (true);
    }

    public static void main(String[] args) {
        ProductServiceImpl service = new ProductServiceImpl();
        System.out.println(service.getId());
    }

    public void getById() throws IOException {
        boolean found = false;
        do {
            System.out.println("Enter id:");
            String id = obj1.nextLine().trim();
            Product product;

            String line;
            BufferedReader myReader = readFile();
                while ((line = myReader.readLine()) != null) {
                    try {
                        String[] arr = line.split(",");
                        String idProduct = arr[0];
                        String name = arr[1];
                        String manu = arr[2];
                        String category = arr[3];
                        BigDecimal price = new BigDecimal(arr[4]);
                        product = new Product(idProduct, name, manu, category, price);
                        if (id.matches("[1-9]")) {
                            if (Integer.parseInt(product.getId()) == Integer.parseInt(id)) {
                                found = true;
                                displayHeader();
                                displayProduct(product);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            if (found == true){
                break;
            } else{
                System.err.println("Cannot found id!");
            }
        } while (true);
    }

    public void searchByName() throws IOException {
        boolean found = false;
        do {
            System.out.println("Enter search string:");
            String searchString = obj1.nextLine().trim();
            Product product;
            String line;
            BufferedReader myReader = readFile();
            displayHeader();
            while ((line = myReader.readLine()) != null) {
                try {
                    String[] arr = line.split(",");
                    String idProduct = arr[0];
                    String name = arr[1];
                    String manu = arr[2];
                    String category = arr[3];
                    BigDecimal price = new BigDecimal(arr[4]);
                    product = new Product(idProduct, name, manu, category, price);
                        if ((product.getName()).contains(searchString) == true) {
                            found = true;
                            displayProduct(product);
                        }
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            if (found == true){
                break;
            } else{
                System.err.println("Cannot found item!");
            }
        } while (true);
    }
    public void addNewItem() {
        String priceCreate;
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        String nameCreate;
        String manuCreate;
        String cateCreate;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Training-TASC\\CRUDTextFile\\src\\data.txt", true));
            Scanner scan = new Scanner(new File("D:\\Training-TASC\\CRUDTextFile\\src\\data.txt"));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] arr = line.split(",");
                list.add(arr[0]);
            }

            // Create new array list to store list id of product
            list1.add(0, 0);
            list.forEach(item -> {
                list1.add(Integer.parseInt(item));
            });

            int max = Integer.MIN_VALUE;
            for (int value : list1) {
                if (value > max) {
                    max = value;
                }
            }
            // Generate id auto increment with value = max of id + 1
            String idCreate = String.valueOf(max + 1);
            do {
                System.out.println("Enter product name:");
                nameCreate = obj1.nextLine().trim();
                if (!nameCreate.contains(",") && !nameCreate.isEmpty()){
                    break;
                }
                System.out.println("Product name is invalid");
            } while (true);
            do {
                System.out.println("Enter manufacturer name:");
                manuCreate = obj1.nextLine().trim();
                if (!manuCreate.contains(",") && !manuCreate.isEmpty()){
                    break;
                }
                System.out.println("Manufacturer name is invalid");
            } while (true);
            do {
                System.out.println("Enter category name:");
                cateCreate = obj1.nextLine().trim();
                if (!cateCreate.contains(",") && !cateCreate.isEmpty()){
                    break;
                }
                System.out.println("Category name is invalid");
            } while (true);
            do {
                System.out.println("Enter price:");
                priceCreate = obj1.nextLine().trim();

                if (priceCreate.matches("[0-9]+(\\.[0-9]+)?")) {
                    BigDecimal newPrice = new BigDecimal(priceCreate);
                    Double priceToDouble = Double.parseDouble(priceCreate);
                    if (priceToDouble > 0) {
                        Product item = new Product(idCreate, nameCreate, manuCreate, cateCreate, newPrice);
                        writer.write(item.toString());
                        writer.newLine();
                        writer.close();
                        break;
                    }
                }
                System.out.println("Please enter number for price!");
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update() throws IOException {
        String idUpdate = getId();
        String nameUpdate;
        String manuUpdate;
        String cateUpdate;
        StringBuilder newData = new StringBuilder();
        String line;
        Product product;
        do {
            System.out.println("Enter new product name:");
            nameUpdate = obj1.nextLine().trim();
        } while (nameUpdate.isEmpty());
        do {
            System.out.println("Enter new manufacturer" +
                    " name:");
            manuUpdate = obj1.nextLine().trim();
        } while (manuUpdate.isEmpty());
        do {
            System.out.println("Enter new category name:");
            cateUpdate = obj1.nextLine().trim();
        } while (cateUpdate.isEmpty());
        String priceUpdate;
        do {
            System.out.println("Enter price:");
            priceUpdate = obj1.nextLine().trim();

            if (priceUpdate.matches("[0-9]+(\\.[0-9]+)?")) {
                BigDecimal newPrice = new BigDecimal(priceUpdate);
                Double priceToDouble = Double.parseDouble(priceUpdate);
                if (priceToDouble > 0) {
                    // Lấy ra product với giá trị sau update
                    //todo: set giá trị mới vào đúng vị trí trong file
                    BufferedReader myReader = readFile();
                    displayHeader();
                    while ((line = myReader.readLine()) != null) {
                        try {
                            String[] arr = line.split(",");
                            String idProduct = arr[0];
                            String name = arr[1];
                            String manufac = arr[2];
                            String category = arr[3];
                            BigDecimal price = new BigDecimal(arr[4]);
                            product = new Product(idProduct, name, manufac, category, price);
                            // If idUpdate is exists, set product with new value
                            if (Integer.parseInt(product.getId()) == Integer.parseInt(idUpdate)){
                                product.setName(nameUpdate);
                                product.setManufacturer(manuUpdate);
                                product.setCategory(cateUpdate);
                                product.setPrice(newPrice);
                                newData.append(product.toString());
                                newData.append("\n");
                            } else{
                                // If idUpdate is not exists, append current value
                                newData.append(product.toString());
                                newData.append("\n");
                            }
                        } catch (Exception e) {
                            System.err.println();
                        }
                    }
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\Training-TASC\\CRUDTextFile\\src\\data.txt"));
                    bufferedWriter.write(newData.toString());
                    System.out.println("Updated successfully");
                    bufferedWriter.close();
                    myReader.close();
                    break;
                }
            }
            System.out.println("Please enter number for price!");
        } while (true);
    }

    //todo: xử lí hàm delete
//    public void deleteItem() {
//        List<Product> productList = getData();
//        List<Product> products = new ArrayList<>();
//        String deleteId;
//        do{
//            System.out.println("Enter id: ");
//            deleteId = obj1.nextLine().trim();
//            if (deleteId.matches("[0-9]+(\\.[0-9]+)?") && Integer.parseInt(deleteId) > 0){
//                String finalDeleteId = deleteId;
//                productList.forEach(item -> {
//                    // concurency modification exception -> bug nên phải dùng cách add những item không chọn vào list mới
//                    // xóa list cũ -> add lại
//                    if (!item.getId().equals(finalDeleteId)) {
//                        products.add(item);
//                        System.err.println("Cannot found id");
//                    }
//                });
//                break;
//            }
//        } while (true);
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Training-TASC\\CRUDTextFile\\src\\data.txt"))) {
//            for (Product product : products) {
//                writer.write(product.toString());
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred while writing to the file.");
//            e.printStackTrace();
//        }
//    }
}