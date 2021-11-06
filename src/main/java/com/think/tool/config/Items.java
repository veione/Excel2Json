package com.think.tool.config;

import java.util.List;

public class Items {
    private Integer id;
    private List<Integer> specialDrop;
    private Integer itemType;
    private List<Float> floats;
    private List<String> strings;
    private Boolean bool;
    private List<Double> doubles;
    private Float price;
    private String name;
    private String sellDate;
    private Custom value;

    public List<Integer> getSpecialDrop() {
        return specialDrop;
    }

    public void setSpecialDrop(List<Integer>specialDrop) {
        this.specialDrop = specialDrop;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public List<Float> getFloats() {
        return floats;
    }

    public void setFloats(List<Float> floats) {
        this.floats = floats;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public List<Double> getDoubles() {
        return doubles;
    }

    public void setDoubles(List<Double> doubles) {
        this.doubles = doubles;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Custom getValue() {
        return value;
    }

    public void setValue(Custom value) {
        this.value = value;
    }

    public static class Custom {
        private int key;
        private int value;
        private String name;
        private String role;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
