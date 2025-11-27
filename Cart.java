/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Represents a shopping cart equipment that can hold products.
 * Extends the Equipment class with a capacity of 30 items.
 * 
 * @author Gabriel
 * @version 1.0.6
 */
public class Cart extends Equipment {
    /**
     * Constructs a Cart with default capacity of 30 items.
     */
    public Cart() {
        super(30); // Cart holds 30 items
    }
}