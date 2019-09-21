package comp1110.homework.J14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Cost {
    HashMap<String, Item> itemHashMap = new HashMap<>();
    List<Item> itemList = new ArrayList<>();

    void addItem(String name, int cost) {
        Item newItem = new Item(name, cost);
        itemHashMap.put(name, newItem);
        itemList.add(newItem);
    }

    void printCost() {
        Collections.sort(itemList,(i1, i2) -> i1.compareTo(i2));
        for (Item item: itemList) {
            System.out.println(item.name + " " + item.cost);
        }
    }

    int voucherWaste(int value) {
        int[] costVec = new int[value + 1];
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);
            for (int j = item.cost; j <= value; j++) {
                if (costVec[j] > costVec[j - item.cost] + item.cost) {
                    costVec[j] = costVec[j];
                } else {
                    costVec[j] = costVec[j - item.cost] + item.cost;
                }
            }
        }
        return value - costVec[value];
    }
//    int voucherWaste(int value) {
//        if (itemList.size() == 0 || value <= 0) {
//            return value;
//        }
//
//        int[][] valueMatrix = new int[itemList.size()][value];
//        Item tmpItem;
//        int tmpMax;
//
//        // Build a matrix about all cases.
//        for (int tmpValue = 0; tmpValue < value; tmpValue++) {
//            for (int idx = 0; idx < itemList.size(); idx++) {
//                tmpItem = itemList.get(idx);
//                if (tmpItem.cost > tmpValue) {
//                    if (idx == 0) {  // The first iteration of items.
//                        valueMatrix[idx][tmpValue] = 0;
//                    } else {  // Copy the values from the previous items.
//                        valueMatrix[idx][tmpValue] = valueMatrix[idx - 1][tmpValue];
//                    }
//                } else {  // This item can be included.
//                    if (idx == 0) {
//                        valueMatrix[idx][tmpValue] = tmpItem.cost;
//                    } else {
//                        tmpMax = valueMatrix[idx - 1][tmpValue - tmpItem.cost];
//
//                        // Keep the greater one.
//                        if (tmpMax > valueMatrix[idx - 1][tmpValue]) {
//                            valueMatrix[idx][tmpValue] = tmpMax;
//                        } else {
//                            valueMatrix[idx][tmpValue] = valueMatrix[idx - 1][tmpValue];
//                        }
//                    }
//                }
//            }
//        }
//
//        // Get the best situation.
//        int maxVal = valueMatrix[itemList.size() - 1][value - 1];
//        return (value - maxVal);
//    }

    public class Item implements Comparable<Item> {
        String name;
        int cost;   // in cents
        public Item(String n, int c) {
            name = n;
            cost = c;
        }
        public String toString() {
            return name + " " + cost;
        }

        @Override
        public int compareTo(Item o) {
            if (this.cost != o.cost) {
                return this.cost - o.cost;
            } else {
                if (this.name.equals(o.name)) {
                    return this.name.charAt(0) - o.name.charAt(0);
                } else {
                    return 0;
                }
            }
        }
    }

}
