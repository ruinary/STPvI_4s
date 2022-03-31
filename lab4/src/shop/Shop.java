package shop;

import assortment.Assortment;

import java.util.ArrayList;

public class Shop {

    public String shop_name;

    public Shop(String shop_name) {
        this.shop_name = shop_name;
    }

    ArrayList<Assortment> assort = new ArrayList<>();

    public ArrayList<Assortment> getAssortment() {
        return assort;
    }

    public void setAssortment(ArrayList<Assortment> assort) {
        if(assort == null)
            throw new NullPointerException("Null pointer");
        this.assort = assort;
    }

    public void addAssortment(Assortment assort) {
        if(assort == null)
            throw new NullPointerException("Null pointer");
        System.out.println("В книжный магазин '" + this.shop_name + "' добавлен: " + assort.toString());
        this.assort.add(assort);
    }

    public Shop() {
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    @Override
    public String toString() {
        return "\n---------------------------------------------" +
                "\n\t\tКнижный магазин: " + shop_name +
                "\nАссортимент: " + assort +
                "\n---------------------------------------------\n";
    }
}
