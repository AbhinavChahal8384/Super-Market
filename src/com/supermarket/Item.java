package com.supermarket;



class Item {
    String name;
    int quantity;
    String date;

    public Item(String name, int quantity, String date) {
        this.name = name;
        this.quantity = quantity;
        this.date = date;
    }

    @Override
    public String toString() {
        return name + "," + quantity + "," + date;
    }

    public static Item fromString(String line) {
        String[] parts = line.split(",");
        return new Item(parts[0], Integer.parseInt(parts[1]), parts[2]);
    }
}

