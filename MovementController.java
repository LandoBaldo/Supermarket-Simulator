public class MovementController {

    public static boolean move(Shopper shopper, Map map, int dx, int dy) {
        int currentx = shopper.getX();
        int currenty = shopper.getY();
        int nx = currentx + dx;
        int ny = currenty + dy;

        //Boundary and Walkability Check
        if(!map.isValidPosition(nx, ny)) return false;
        if(!map.isWalkable(nx, ny)) return false;

        //Update Map and Shopper Position
        if(map.isValidPosition(currentx, currenty) && "S".equals(map.getCell(currentx,currenty))) {
            map.setCell(currentx,currenty, " ");
        }
        shopper.setPostion(nx, ny);
        map.setCell(nx, ny, "S");
        return true;
    }

    //Update Facing of Shopper
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
        if (!map.isValidPosition(x, y)) return null;
        String token = map.getCell(x,y);
        return new FrontCell(x, y, token);
    }
    
    // Needs Handle Input
}
