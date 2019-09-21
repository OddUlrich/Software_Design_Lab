package comp1110.exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * COMP1110 Final Exam, Question 3ii
 */
public class Q3StockManager {
    private HashMap<String, Vendor> vendorMap = new HashMap<>();
    private HashMap<String, Items> itemsMap = new HashMap<>();
    private ArrayList<String> skusList = new ArrayList<>();

    class Vendor {
        String vid;
        String name;

        Vendor(String vid, String name) {
            this.vid = vid;
            this.name = name;
        }

    }

    class Items {
        String vid;
        String sku;
        String name;
        double price;
        int stock;
        int targetStock;

        Items (String sku, String name, double price) {
            this.sku = sku;
            this.name = name;
            this.price = price;
        }
    }


    /**
     * A new vendor has been introduced.
     *
     * @param vid The vid of the new vendor vendor
     * @param name The vendor’s name
     */
    public void newVendor(String vid, String name) {
        // FIXME Question 3iia: complete this function
        Vendor vendor = new Vendor(vid, name);
        vendorMap.put(vid, vendor);
    }

    /**
     * A new product has been introduced.
     *
     * @param sku The product’s sku
     * @param vid The vid of the product's vendor
     * @param name The product’s name
     * @param price The product’s price (per unit)
     */
    public void newItem(String sku, String vid, String name, double price) {
        // FIXME Question 3iib: complete this function
        Vendor vendor = vendorMap.get(vid);
        Items item = new Items(sku, name, price);
        item.vid = vid;
        itemsMap.put(sku, item);
        skusList.add(sku);
    }

    /**
     * Return the name of a product.
     *
     * @param sku The product’s sku
     * @return the name of the product
     */
    public String getItemName(String sku) {
        Items item = itemsMap.get(sku);
        return item.name; // FIXME Question 3iic: complete this function
    }

    /**
     * Return the name of a product's vendor.
     *
     * @param sku The product’s sku
     * @return the name of the product's vendor
     */
    public String getItemVendorName(String sku) {
        Items item = itemsMap.get(sku);
        Vendor vendor = vendorMap.get(item.vid);
        return vendor.name; // FIXME Question 3iid: complete this function
    };

    /**
     * Return the price of a product.
     *
     * @param sku The product’s sku
     * @return the price of the product
     */
    public double getItemPrice(String sku) {
        Items item = itemsMap.get(sku);
        return item.price; // FIXME Question 3iie: complete this function
    };

    /**
     * Return the amount of stock for a product.
     *
     * @param sku The product’s sku
     * @return the number of items in stock
     */
    public int getStock(String sku) {
        Items item = itemsMap.get(sku);
        return item.stock; // FIXME Question 3iif: complete this function
    };

    /**
     * An product has been sold; reduce current stock accordingly.
     *
     * @param sku The product’s sku
     * @param sold The quantity sold
     * @return The number of items of stock remaining after the sale
     */
    public int sale(String sku, int sold) {
        Items item = itemsMap.get(sku);
        item.stock -= sold;
        return item.stock; // FIXME Question 3iig: complete this function
    }

    /**
     * New stock has arrived; increase current stock accordingly.
     *
     * @param sku The product’s sku
     * @param added The quantity newly arrived
     */
    public void addStock(String sku, int added) {
        // FIXME Question 3iih: complete this function
        Items item = itemsMap.get(sku);
        item.stock += added;
    }

    /**
     * Set the target amount of stock for a product.
     *
     * @param sku The item’s sku
     * @param target The target quantity desired to be held in stock
     */
    public void setTargetStock(String sku, int target) {
        // FIXME Question 3iij: complete this function
        Items item = itemsMap.get(sku);
        item.targetStock = target;
    }

    /**
     * Stock has been carefully counted. Set current stock correctly.
     *
     * @param sku The product’s sku
     * @param actual The quantity actually in the store
     * @return The stock loss or gain (new current – old current)
     */
    public int setActualStock(String sku, int actual) {
        Items item = itemsMap.get(sku);
        int tmpStock = actual - item.stock;
        item.stock = actual;
        return tmpStock;    // FIXME Question 3iik: complete this function
    }

    /**
     * For a given vendor, return a map indicating for each product provided by
     * that vendor, the number of items required in order to reach the target
     * stock for that item (target - stock).   A positive number indicates that
     * stock is needed, a negative number indicates that there is extra stock.
     *
     * @param vid The VID of the vendor to be queried
     * @return A map of item SKUs and the difference between target and actual stock for that item
     */
    public Map<String, Integer> getStockRequired(String vid) {
        HashMap<String, Integer> skuDiffMap = new HashMap<String, Integer>();

        for (String sku: skusList) {
            Items item = itemsMap.get(sku);
            if (item.vid.equals(vid)) {
                skuDiffMap.put(sku, item.targetStock- item.stock);
            }
        }
        return skuDiffMap; // FIXME Question 3iil: complete this function
    }
}
