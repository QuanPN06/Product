package quanpnph29471.example.demo1.Model;

public class Product {
    private int id,id_cat,price;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Product() {
    }

    public Product(int id, String name, int price, int id_cat) {
        this.id = id;
        this.id_cat = id_cat;
        this.price = price;
        this.name = name;

    }

    public Product( String name, int price, int id_cat) {
        this.id_cat = id_cat;
        this.price = price;
        this.name = name;
    }
}
