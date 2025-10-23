import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Handles receipt generation for supermarket checkout
 */
public class Receipt {
    private String shopperName;
    private int shopperAge;
    private LocalDateTime dateTime;
    private ArrayList<Products> products;
    private double subtotal;
    private double totalDiscount;
    private double finalTotal;
    private ArrayList<ProductWithDiscount> productsWithDiscounts;

    public Receipt(Shopper shopper) {
        this.shopperName = shopper.getName();
        this.shopperAge = shopper.getAge();
        this.dateTime = LocalDateTime.now();
        this.products = shopper.getAllProducts();
        calculateTotals(shopper);
    }

    /**
     * Calculates all totals and discounts
     */
    private void calculateTotals(Shopper shopper) {
        subtotal = 0;
        totalDiscount = 0;
        finalTotal = 0;
        productsWithDiscounts = new ArrayList<>();

        for (Products product : products) {
            double originalPrice = product.getPrice();
            double discountedPrice = shopper.applyDiscount(product);
            double itemDiscount = originalPrice - discountedPrice;

            subtotal = subtotal + originalPrice;
            totalDiscount = totalDiscount + itemDiscount;
            finalTotal = finalTotal + discountedPrice;

            // Store product with its calculated discount
            productsWithDiscounts.add(new ProductWithDiscount(product, discountedPrice));
        }
    }

    /**
     * Generates the receipt as a formatted string
     */
    public String generateReceipt() {
        String receipt = "";
        receipt = receipt + "=== SUPERMARKET RECEIPT ===\n";
        receipt = receipt + "Shopper: " + shopperName + "\n";
        receipt = receipt + "Age: " + shopperAge + "\n";
        receipt = receipt + "Date: " + dateTime + "\n\n";

        receipt = receipt + "ITEMS PURCHASED:\n";
        receipt = receipt + "----------------------------------------\n";

        // Group products by type for better display
        ArrayList<ProductGroup> productGroups = groupProducts();

        for (ProductGroup group : productGroups) {
            Products product = group.product;
            int quantity = group.quantity;
            double originalPrice = product.getPrice() * quantity;
            double discountedPrice = group.discountedPricePerUnit * quantity;
            double itemDiscount = originalPrice - discountedPrice;

            receipt = receipt + product.getName() + " x" + quantity + "\n";
            receipt = receipt + String.format("  Price: $%.2f x %d = $%.2f\n",
                    product.getPrice(), quantity, originalPrice);

            if (itemDiscount > 0) {
                receipt = receipt + String.format("  Discount: -$%.2f\n", itemDiscount);
                receipt = receipt + String.format("  Final: $%.2f\n", discountedPrice);
            }

            receipt = receipt + "  SN: " + product.getSerialNumber() + "\n\n";
        }

        receipt = receipt + "----------------------------------------\n";
        receipt = receipt + String.format("Subtotal: $%.2f\n", subtotal);
        receipt = receipt + String.format("Total Discount: $%.2f\n", totalDiscount);
        receipt = receipt + String.format("FINAL TOTAL: $%.2f\n", finalTotal);

        // Add senior citizen discount note if applicable
        if (shopperAge >= 60 && totalDiscount > 0) {
            receipt = receipt + "Senior Citizen Discount Applied\n";
        }

        receipt = receipt + "========================================\n";
        receipt = receipt + "Thank you for shopping with us!";

        return receipt;
    }

    /**
     * Groups identical products together
     */
    private ArrayList<ProductGroup> groupProducts() {
        ArrayList<ProductGroup> groups = new ArrayList<>();

        for (ProductWithDiscount pwd : productsWithDiscounts) {
            boolean found = false;
            for (ProductGroup group : groups) {
                if (group.product.getSerialNumber().equals(pwd.product.getSerialNumber())) {
                    group.quantity++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                groups.add(new ProductGroup(pwd.product, 1, pwd.discountedPrice));
            }
        }

        return groups;
    }

    /**
     * Helper class to store product with its discounted price
     */
    private class ProductWithDiscount {
        Products product;
        double discountedPrice;

        ProductWithDiscount(Products product, double discountedPrice) {
            this.product = product;
            this.discountedPrice = discountedPrice;
        }
    }

    /**
     * Helper class to group products
     */
    private class ProductGroup {
        Products product;
        int quantity;
        double discountedPricePerUnit;

        ProductGroup(Products product, int quantity, double discountedPricePerUnit) {
            this.product = product;
            this.quantity = quantity;
            this.discountedPricePerUnit = discountedPricePerUnit;
        }
    }

    /**
     * Displays receipt to console
     */
    public void displayReceipt() {
        System.out.println(generateReceipt());
    }

    // Getters for receipt data
    public String getShopperName() {
        return shopperName;
    }

    public int getShopperAge() {
        return shopperAge;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public int getItemCount() {
        return products.size();
    }
}