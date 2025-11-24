/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */
public class MovementController {

    // Processes input for both Movement and Vision
    public static boolean handleInput(String input, Shopper shopper, Map map) {
        if (input == null || input.isEmpty()) return false;
        input = input.toLowerCase().trim();

        switch (input) {
            // --- MOVEMENT (WASD) --- Updates Position (X, Y)
            case "w": return move(shopper, map, 0, -1); // North
            case "s": return move(shopper, map, 0, 1);  // South
            case "a": return move(shopper, map, -1, 0); // West
            case "d": return move(shopper, map, 1, 0);  // East
            
            // --- VISION (IJKL) --- Updates Facing Direction Only
            case "i": shopper.setFacingDirection(Shopper.Direction.NORTH); return true;
            case "k": shopper.setFacingDirection(Shopper.Direction.SOUTH); return true;
            case "j": shopper.setFacingDirection(Shopper.Direction.WEST); return true;
            case "l": shopper.setFacingDirection(Shopper.Direction.EAST); return true;
        }
        return false;
    }
    
    // Helper to check collision before moving
    private static boolean move(Shopper shopper, Map map, int dx, int dy) {
        int nx = shopper.getX() + dx;
        int ny = shopper.getY() + dy;
        
        if (map.isWalkable(nx, ny)) {
            // Clear old visual 'S'
            map.setCell(shopper.getX(), shopper.getY(), " ");
            
            // Update Shopper
            shopper.setPosition(nx, ny);
            
            // Set new visual 'S'
            map.setCell(nx, ny, "S");
            return true;
        }
        return false;
    }
    
    // Identifies what block is directly in front of the shopper
    public static FrontCell getCellInFront(Shopper shopper, Map map) {
        int x = shopper.getX();
        int y = shopper.getY();
        
        switch (shopper.getFacingDirection()) {
            case NORTH: y -= 1; break;
            case SOUTH: y += 1; break;
            case WEST:  x -= 1; break;
            case EAST:  x += 1; break;
        }
        
        if (!map.isValid(x, y)) return null;
        // Wrap the token string in our FrontCell object
        return new FrontCell(x, y, map.getCell(x, y));
    }
}