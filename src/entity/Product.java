package entity;/*Welcome to my show !

@author: NgKhanh
Date: 6/30/2023
Time: 2:37 PM

ProjectName: CRUDTextFile*/

import java.math.BigDecimal;

public class Product {
    private String id;
    private String name;
    private String manufacturer;

    @Override
    public String toString() {
        return id + ',' + name + ',' +  manufacturer + ',' +  category + ',' +  price;
    }

    public Product(String id, String name, String manufacturer, String category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
        this.price = price;
    }

    private String category;
    BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
