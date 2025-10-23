public class FrontCell {
    private int x;
    private int y;
    private String token;

    public FrontCell(int x, int y, String token) {
        this.x = x;
        this.y = y;
        this.token = token;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getToken() {
        return token;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "FrontCell{" +
                "x=" + x +
                ", y=" + y +
                ", token='" + token + '\'' +
                '}';
    }

    /**
     * Check if the cell is empty (walkable space)
     */
    public boolean isEmpty() {
        return " ".equals(token);
    }

    /**
     * Check if the cell is a wall
     */
    public boolean isWall() {
        return "█".equals(token);
    }

    /**
     * Check if the cell contains furniture
     */
    public boolean isFurniture() {
        return token != null && (token.equals("T") || // Table
                token.equals("C") || // Chilled Counter
                token.equals("R") || // Refrigerator
                token.equals("H") // Shelf
        );
    }

    /**
     * Get the type of furniture in this cell
     * 
     * @return "Table", "ChilledCounter", "Refrigerator", "Shelf", or null if not
     *         furniture
     */
    public String getFurnitureType() {
        if (token == null)
            return null;

        switch (token) {
            case "T":
                return "Table";
            case "C":
                return "ChilledCounter";
            case "R":
                return "Refrigerator";
            case "H":
                return "Shelf";
            default:
                return null;
        }
    }
}