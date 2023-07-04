package service;/*Welcome to my show !

@author: NgKhanh
Date: 6/30/2023
Time: 2:13 PM

ProjectName: CRUDTextFile*/

import entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService<T> {
    List<Product> getData();
    void getById(String id);
    void getAll();
    void addNewItem(String id, String name, String manufacturer, String category, BigDecimal price);
    void deleteById(int id);
    Product searchBy(String x);
    void update(String id, String name, String manu, String category, BigDecimal price);
}