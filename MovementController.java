import java.util.Scanner;

public class MovementController {

    public static boolean move(Shopper shopper, Map map, int dx, int dy) {
        int currentx = shopper.getX();
        int currenty = shopper.getY();
        int nx = currentx + dx;
        int ny = currenty + dy;

        // Boundary and Walkability Check
        if (!map.isValidPosition(nx, ny))
            return false;
        if (!map.isWalkable(nx, ny))
            return false;

        // Update Map and Shopper Position
        if (map.isValidPosition(currentx, currenty) && "S".equals(map.getCell(currentx, currenty))) {
            map.setCell(currentx, currenty, " ");
        }
        shopper.setPostion(nx, ny);
        map.setCell(nx, ny, "S");
        return true;
    }

    // Update Facing of Shopper
    public static boolean moveUp(Shopper shopper, Map map) {
        shopper.setFacing(Shopper.Direction.UP);
        return move(shopper, map, 0, -1);
    }

    public static boolean moveDown(Shopper shopper, Map map) {
        shopper.setFacing(Shopper.Direction.DOWN);
        return move(shopper, map, 0, 1);
    }

    public static boolean moveLeft(Shopper shopper, Map map) {
        shopper.setFacing(Shopper.Direction.LEFT);
        return move(shopper, map, -1, 0);
    }

    public static boolean moveRight(Shopper shopper, Map map) {
        shopper.setFacing(Shopper.Direction.RIGHT);
        return move(shopper, map, 1, 0);
    }

    public static FrontCell getCellInFront(Shopper shopper, Map map) {
        int x = shopper.getX();
        int y = shopper.getY();
        switch (shopper.getFacing()) {
            case UP -> y -= 1;
            case DOWN -> y += 1;
            case LEFT -> x -= 1;
            case RIGHT -> x += 1;
        }
        if (!map.isValidPosition(x, y))
            return null;
        String token = map.getCell(x, y);
        return new FrontCell(x, y, token);
    }

    /**
     * Handle keyboard input for movement
     * 
     * @param input   The input string from user (w/a/s/d or arrow keys)
     * @param shopper The shopper to move
     * @param map     The map to move on
     * @return true if movement was successful, false otherwise
     */
    public static boolean handleInput(String input, Shopper shopper, Map map) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        input = input.toLowerCase().trim();

        switch (input) {
            case "w":
            case "up":
                return moveUp(shopper, map);
            case "s":
            case "down":
                return moveDown(shopper, map);
            case "a":
            case "left":
                return moveLeft(shopper, map);
            case "d":
            case "right":
                return moveRight(shopper, map);
            default:
                return false;
        }
    }

    /**
     * Interactive input loop for continuous movement
     * 
     * @param shopper The shopper to control
     * @param map     The map to move on
     * @param scanner Scanner object for reading input
     * @return the last command entered (useful for detecting 'quit' or other
     *         commands)
     */
    public static String interactiveMovement(Shopper shopper, Map map, Scanner scanner) {
        System.out.println("\n=== Movement Controls ===");
        System.out.println("W/Up    - Move Up");
        System.out.println("S/Down  - Move Down");
        System.out.println("A/Left  - Move Left");
        System.out.println("D/Right - Move Right");
        System.out.println("Q       - Quit movement mode");
        System.out.println("========================\n");

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("q") || input.equals("quit")) {
                System.out.println("Exiting movement mode.");
                return input;
            }

            boolean moved = handleInput(input, shopper, map);

            if (moved) {
                System.out.println("Moved successfully to (" + shopper.getX() + ", " + shopper.getY() + ")");
                System.out.println("Facing: " + shopper.getFacing());

                // Show what's in front
                FrontCell front = getCellInFront(shopper, map);
                if (front != null) {
                    System.out.println(
                            "Cell in front: " + front.getToken() + " at (" + front.getX() + ", " + front.getY() + ")");
                }

                // Optionally display map
                map.printMap();
            } else {
                System.out.println("Cannot move in that direction!");
            }
        }
    }

    /**
     * Process a single character input (useful for real-time input systems)
     * 
     * @param keyCode character code or key identifier
     * @param shopper The shopper to move
     * @param map     The map to move on
     * @return true if movement was successful
     */
    public static boolean handleKeyPress(char keyCode, Shopper shopper, Map map) {
        switch (Character.toLowerCase(keyCode)) {
            case 'w':
                return moveUp(shopper, map);
            case 's':
                return moveDown(shopper, map);
            case 'a':
                return moveLeft(shopper, map);
            case 'd':
                return moveRight(shopper, map);
            default:
                return false;
        }
    }
}