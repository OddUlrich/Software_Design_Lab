package comp1110.homework.J14;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    ArrayList<Item> items = new ArrayList<>();

    class Item {
        String name;
        int stock;
        int price;
        int rate;
        int reorderdays;

        Item(String name, int stock, int price) {
            this.name = name;
            this.stock = stock;
            this.price = price;
        }
        Item(String name, int stock, int price, int rate, int reorderdays) {
            this.name = name;
            this.stock = stock;
            this.price = price;
            this.rate = rate;
            this.reorderdays = reorderdays;
        }
    }

    void addItem(String name, int stock, int price) {
        Item item = new Item(name, stock, price);
        items.add(item);
    }

    void addItem(String name, int stock, int price,
                 int rate, int reorderdays) {
        Item item = new Item(name, stock, price, rate, reorderdays);
        items.add(item);
    }

    int totalStockValue() {
        int sum = 0;
        for (Item item: items) {
            sum += item.price * item.stock;
        }
        return sum;
    }

    HashMap<String, Integer> reorder() {
        HashMap<String, Integer> reorders = new HashMap<>();

        for (Item item: items) {
            if (item.stock < item.rate * item.reorderdays + 1) {
                reorders.put(item.name, item.rate * 7 - item.stock);
            }
        }

        return reorders;
    }
}
