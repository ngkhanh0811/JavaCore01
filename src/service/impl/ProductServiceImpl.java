package service.impl;/*Welcome to my show !

@author: NgKhanh
Date: 6/30/2023
Time: 2:13 PM

ProjectName: CRUDTextFile*/

import entity.Product;
import service.ProductService;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductServiceImpl<T> implements ProductService<T> {


    public List<Product> getData(){
        List<Product> productList = new ArrayList<>();
        String line = null;
        try {
            File myObj = new File("D:\\demo\\CRUDTextFile\\src\\data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                line = myReader.nextLine();
                String[] arr = line.split(",");
                String idProduct = arr[0];
                String name = arr[1];
                String manufac = arr[2];
                String category = arr[3];
                BigDecimal price = new BigDecimal(arr[4]);
                Product product = new Product(idProduct, name, manufac, category, price);
                productList.add(product);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return productList;
    }
    public void getAll(){
        try {
            File myObj = new File("D:\\demo\\CRUDTextFile\\src\\data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void getById(String id){
        List<Product> productList = new ArrayList<>();
        getData().forEach(element-> {
            productList.add(element);
        });
        for (Product product: productList){
            if (product.getId().equals(id)){
                System.out.println(product.toString());
            }
        }
    }

    public void addNewItem(String id, String name, String manufacturer, String category, BigDecimal price){
        List<Product> productList = new ArrayList<>();
        getData();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\demo\\CRUDTextFile\\src\\data.txt", true) );

            Product item = new Product(id, name, manufacturer, category, price);
            getData().forEach(element -> {
                productList.add(element);
            });
            productList.add(item);
            writer.write(item.toString());
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id){
        List<Product> productList = new ArrayList<>();
        try {
            File myObj = new File("D:\\demo\\CRUDTextFile\\src\\data.txt");
            Scanner myReader = new Scanner(myObj);
            getData().forEach(element -> {
                productList.add(element);
            });
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\demo\\CRUDTextFile\\src\\data.txt"));


                productList.remove(id-1);
                System.out.println("Success delete!");

                for (Product products: productList){
                    bufferedWriter.write(products.toString());
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Product searchBy(String x){
        List<Product> productList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\demo\\CRUDTextFile\\src\\data.txt"));

            getData().forEach(element -> {
                productList.add(element);
            });

           String line;
           while((line = reader.readLine()) != null){
                if (line.contains(x)){
                    System.out.println(line);
                    break;
                }
           }
           reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void update(String id, String name, String manu, String category, BigDecimal price){
        List<Product> productList = new ArrayList<>();
        try {
            getData().forEach(element -> {
                productList.add(element);
            });

            Product item = productList.get(Integer.parseInt(id)-1);
            item.setId(id);
            item.setName(name);
            item.setManufacturer(manu);
            item.setCategory(category);
            item.setPrice(price);

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\demo\\CRUDTextFile\\src\\data.txt"));

                for (Product products: productList){
                    bufferedWriter.write(products.toString());
                    bufferedWriter.newLine();
                }
            bufferedWriter.close();


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}