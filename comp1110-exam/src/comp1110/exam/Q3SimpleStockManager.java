package comp1110.exam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * COMP1110 Final Exam, Question 3i
 */
public class Q3SimpleStockManager {
    private HashMap<String, Item> itemMap = new HashMap<>();
    private ArrayList<String> skuList = new ArrayList<>();

    class Item {
        String sku;
        String name;
        double price;
        int stock;
        int targetStock;

        Item (String sku, String name, double price) {
            this.sku = sku;
            this.name = name;
            this.price = price;
        }
    }

    /**
     * A new product has been introduced.
     *
     * @param sku The product’s sku
     * @param name The product’s name
     * @param price The product’s price (per unit)
     */
    public void newItem(String sku, String name, double price) {
        // FIXME Question 3ia: complete this function
        Item item;
        if (itemMap.containsKey(sku)) {
            item = itemMap.get(sku);
            item.stock++;
        } else {
            item = new Item(sku, name, price);
            itemMap.put(sku, item);
            skuList.add(sku);
        }
    }

    /**
     * Return the name of a product.
     *
     * @param sku The product’s sku
     * @return the name of the product
     */
    public String getItemName(String sku) {
        Item item = itemMap.get(sku);
        if (item != null) {
            return item.name; // FIXME Question 3ib: complete this function
        } else {
            return null;
        }
    }

    /**
     * Return the price of a product.
     *
     * @param sku The product’s sku
     * @return the price of the product
     */
    public double getItemPrice(String sku) {
        Item item = itemMap.get(sku);
        if (item != null) {
            return item.price; // FIXME Question 3ic: complete this function
        } else {
            return 0;
        }
    }

    /**
     * Return the amount of stock for a product.
     *
     * @param sku The product’s sku
     * @return the number of items in stock
     */
    public int getStock(String sku) {
        Item item = itemMap.get(sku);
        if (item != null) {
            return item.stock; // FIXME Question 3id: complete this function
        } else {
            return 0;
        }
    }

    /**
     * An product has been sold; reduce current stock accordingly.
     *
     * @param sku The product’s sku
     * @param sold The quantity sold
     * @return The number of items of stock remaining after the sale
     */
    public int sale(String sku, int sold) {
        Item item = itemMap.get(sku);
        if (item != null) {
            item.stock -= sold;
            return item.stock; // FIXME Question 3ie: complete this function
        } else {
            return 0;
        }
    }

    /**
     * New stock has arrived; increase current stock accordingly.
     *
     * @param sku The product’s sku
     * @param added The quantity newly arrived
     */
    public void addStock(String sku, int added) {
        // FIXME Question 3if: complete this function
        Item item;
        if (itemMap.containsKey(sku)) {
            item = itemMap.get(sku);
            item.stock += added;
        }
    }

    /**
     * Set the target amount of stock for a product.
     *
     * @param sku The item’s sku
     * @param target The target quantity desired to be held in stock
     */
    public void setTargetStock(String sku, int target) {
        // FIXME Question 3ig: complete this function
        Item item = itemMap.get(sku);
        if (item != null) {
            item.targetStock = target;
        }
    }

    /**
     * Stock has been carefully counted. Set current stock correctly.
     *
     * @param sku The product’s sku
     * @param actual The quantity actually in the store
     * @return The stock loss or gain (new current – old current)
     */
    public int setActualStock(String sku, int actual) {
        Item item = itemMap.get(sku);
        if (item != null) {
            int tmpStock = actual - item.stock;
            item.stock = actual;
            return tmpStock;  // FIXME Question 3ih: complete this function
        } else {
            return 0;
        }
    }

    /**
     * Return the number of items required for a given product
     * in order to reach the target stock for that item (target - stock)
     *
     * @param sku The SKU of the item to be queried
     * @return The difference between target and actual stock for that item
     */
    public int getStockRequired(String sku) {
        Item item = itemMap.get(sku);
        if (item != null) {
            return item.targetStock - item.stock; // FIXME Question 3ii: complete this function
        } else {
            return 0;
        }
    }

    /**
     * @return the value of the currently held stock (the price of the
     * product multiplied by the number of items in stock, for all items).
     */
    public double totalStockValue() {
        double value = 0.0;

        for (String sku: skuList) {
            Item item = itemMap.get(sku);
            if (item != null) {
                value += item.price * item.stock;
            }
        }
        return value; // FIXME Question 3ij: complete this function
    }
}
