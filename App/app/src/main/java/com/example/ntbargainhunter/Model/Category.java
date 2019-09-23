package com.example.ntbargainhunter.Model;

public class Category {

    public int categoryIcon;
    public String categoryName;

    public Category(String productName, int productImage) {
        this.categoryIcon = productImage;
        this.categoryName = productName;
    }

    public int getCategoryImage() {
        return categoryIcon;
    }

    public void setCategoryImage(int productImage) {
        this.categoryIcon = productImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String productName) {
        this.categoryName = productName;
    }
}
