/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gabriel
 */
import Base.*; 
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- SUPERMARKET SIMULATOR ---");
        
        System.out.print("Name: ");
        String name = sc.nextLine();
        
        // FIXED: Added Age Input
        System.out.print("Age: ");
        int age = 20; // Default
        try {
            age = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid age. Defaulting to 20.");
        }
        
        // Setup
        Supermarket sm = new Supermarket("DLSU Mart", 22, 22);
        Shopper player = new Shopper(name, age);
        
        sm.addShopper(player, 11, 21);
        
        boolean running = true;
        while(running) {
            sm.displayMap();
            System.out.println("Pos: " + player.getX() + "," + player.getY() + " Facing: " + player.getFacingDirection());
            System.out.print("[WASD] Move | [IJKL] Look | [E/Space] Interact | [Q] Quit: ");
            
            String input = sc.nextLine().trim();
            if(input.equalsIgnoreCase("q")) break;
            
            if(input.equalsIgnoreCase("e") || input.equals(" ")) {
                sm.handleInteraction(player);
            } else {
                // FIXED: Passing sm.getMap() instead of null
                MovementController.handleInput(input, player, sm.getMap()); 
            }
        }
        sc.close();
    }
}
