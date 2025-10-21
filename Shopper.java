import java.util.ArrayList;


// Represents a shopper in the supermarket simulation
public class Shopper {
    private String name;
    private int age;
    private Equipment equipment;
    private ArrayList<Products> handCarried;
    private boolean checkedOut = false;
    private boolean exited= false;

    //Construct
    public Shopper(String name, int age) {
        this.name = name;
        this.age = age;
        this.handCarried = new ArrayList<>();
    }

    public 

    public String getName() {return name;}
    public int getAge() {return age;}
    public Equipment getEquipment() {return equipment;}
    public ArrayList<Products> getHandCarried() {return handCarried;}
    public boolean hasCheckedOut() {return checkedOut;}


    public boolean setEquipment(Equipment equipment) {
        if (this.equipment == null) {
            this.equipment = equipment;
            return true;
        }
        return false;
    }
    public void setCheckedOut(boolean status) {
        this.checkedOut = checkedOut;
    }

    public Equipment removeEquipment() {
        Equipment temp = this.equipment;
        this.equipment = null;
        return temp;
    }

    public boolean AddToHandCarried(Products product) {
        if(handCarried.size() >= 2) return false;
        handCarried.add(product);
        return true;
    }

    public boolean RemoveFromHandCarried(Products product) {
        return handCarried.remove(product);
    }
   
    public Products removeFromHandCarriedByIndex(int index) {
        return handCarried.remove(index);
    }
    
    public boolean addProduct(Products product) {
        if (this.equipment != null && equipment.addProduct(product)) {
            return true;
        } else if (handCarried.size() < 2) {
            return AddToHandCarried(product);
        }
        return false;
    } 

    public boolean removeProduct(Products product) {
        if(equipment != null && equipment.removeProduct(product)) {
            return true;
        }
        return RemoveFromHandCarried(product);
    }

    public ArrayList<Products> getAllProducts() {
        ArrayList<Products> allProducts = new ArrayList<>(handCarried);
        if (equipment != null) {
            allProducts.addAll(equipment.getProducts());
        }
        return allProducts;
    }

    public int getTotalProductCount() {
        int count = handCarried.size();
        if (equipment != null) {
            count += equipment.getProducts().size();
        }
        return count;
    }

    public boolean canPurchase(Products product) {
        if (age < 18) {
            String productType = product.getProductType();
            if (productType.equals("ALCOHOL") || productType.equals("CLEANING")) {
                return false;
            }
        }
        return true;
    }

    public double applyDiscount(double totalAmount) {
        double originalPrice = product.getPrice();
        if(product.isConsumble() && !product.getProductType().equals("ALCOHOL")){
            if(product.isBeverage()) {
                return originalPrice * 0.9;
            } else {
                return originalPrice * 0.80;
            }
        }
    }

}
