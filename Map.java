/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    private String[][] grid;
    private int width;
    private int height;
    private int currentFloor;  // ADDED: Track which floor this map represents

    private ArrayList<Table> tables;
    private ArrayList<Shelf> shelves;
    private ArrayList<Refrigerator> refrigerators;
    private ArrayList<Service> services;
    private ArrayList<ChilledCounter> chilledCounters;

    public Map(int width, int height, int floor) {
        this.width = width;
        this.height = height;
        this.currentFloor = floor;  // ADDED: Store the floor number
        this.grid = new String[height][width];

        this.tables = new ArrayList<>();
        this.shelves = new ArrayList<>();
        this.refrigerators = new ArrayList<>();
        this.services = new ArrayList<>();
        this.chilledCounters = new ArrayList<>();

        initializeEmptyMap();
        if (floor == 1) {
            initializeGroundFloor();
        } else if (floor == 2) {
            initializeSecondFloor();
        }
    }   

    private void initializeEmptyMap() {
        for (String[] row : grid)
            Arrays.fill(row, " ");
    }

    private void initializeGroundFloor() {
        // 1. OUTER BORDER (#) - Rows 0 and 21, Columns 0 and 21
        for (int x = 0; x < width; x++) {
            grid[0][x] = "#";
            grid[height - 1][x] = "#";
        }
        for (int y = 0; y < height; y++) {
            grid[y][0] = "#";
            grid[y][width - 1] = "#";
        }

        // 2. ROW 1: CHILLED COUNTERS (Light blue sections)
        addBlockOfChilledCounters(1, 6, 1, 1);
        addBlockOfChilledCounters(8, 13, 1, 1);
        addBlockOfChilledCounters(15, 20, 1, 1);

        // 3. ROWS 4-7: TOP AISLE SECTION
        addBlockOfShelves(2, 3, 4, 7);
        addBlockOfShelves(6, 7, 4, 7);
        addBlockOfTables(10, 11, 4, 7);
        addBlockOfShelves(14, 15, 4, 7);
        addBlockOfShelves(18, 19, 4, 7);

        // 4. ROWS 10-13: BOTTOM AISLE SECTION
        addBlockOfShelves(2, 3, 10, 13);
        addBlockOfShelves(6, 7, 10, 13);
        addBlockOfTables(10, 11, 10, 13);
        addBlockOfShelves(14, 15, 10, 13);
        addBlockOfShelves(18, 19, 10, 13);

        // 5. ROW 15: SERVICE STATIONS & ATMs
        addService(new Service(Service.ServiceType.STAIRS, 1, 15));
        addService(new Service(Service.ServiceType.ATM, 3, 15));  // ATM
        addService(new Service(Service.ServiceType.PRODUCT_SEARCH, 8, 15));
        addService(new Service(Service.ServiceType.PRODUCT_SEARCH, 13, 15));
        addService(new Service(Service.ServiceType.ATM, 18, 15));  // ATM
        addService(new Service(Service.ServiceType.STAIRS, 20, 15));

        // 6. ROW 17-18: REMOVED - Wall was blocking exit access

        // 7. ROW 18: CHECKOUT COUNTERS with walls between them
        int[] wallCols18 = {1, 3, 5, 7, 14, 16, 18, 20};
        for (int x : wallCols18) {
            if (x != 10 && x != 11) {
                addBlockOfWalls(18, 18, x, x);
            }
        }

        int[] checkoutCols = {2, 4, 6, 8, 13, 15, 17, 19};
        for (int x : checkoutCols) {
            addService(new Service(Service.ServiceType.CHECKOUT_COUNTER, x, 18));
        }
        
        addBlockOfWalls(18, 18, 10, 11);
        addBlockOfWalls(17, 17, 10, 11);
        
        // 8. ROW 20: BASKET/CART STATIONS
        addService(new Service(Service.ServiceType.BASKET_STATION, 1, 20));
        addService(new Service(Service.ServiceType.CART_STATION, 20, 20));

        // 9. ROW 21: ENTRANCE
        addService(new Service(Service.ServiceType.ENTRANCE, 10, 21));
        addService(new Service(Service.ServiceType.ENTRANCE, 11, 21));
    }

    private void initializeSecondFloor() {
        // 1. OUTER BORDER (#)
        for (int x = 0; x < width; x++) {
            grid[0][x] = "#";
            grid[height - 1][x] = "#";
        }
        for (int y = 0; y < height; y++) {
            grid[y][0] = "#";
            grid[y][width - 1] = "#";
        }

        // 2. ROW 1: REFRIGERATORS, STATIONS & ATM
        addService(new Service(Service.ServiceType.BASKET_STATION, 1, 1));
        addBlockOfRefrigerators(3, 6, 1, 1);
        addService(new Service(Service.ServiceType.ATM, 8, 1));  // ATM
        addBlockOfRefrigerators(9, 12, 1, 1);
        addBlockOfRefrigerators(15, 18, 1, 1);
        addService(new Service(Service.ServiceType.CART_STATION, 20, 1));

        // 3. ROWS 4-7: TOP AISLES
        addBlockOfShelves(2, 3, 4, 7);
        addBlockOfShelves(6, 7, 4, 7);
        addBlockOfTables(10, 11, 4, 7);
        addBlockOfShelves(14, 15, 4, 7);
        addBlockOfShelves(18, 19, 4, 7);

        // 4. ROWS 10-13: BOTTOM AISLES
        addBlockOfShelves(2, 3, 10, 13);
        addBlockOfShelves(6, 7, 10, 13);
        addBlockOfTables(10, 11, 10, 13);
        addBlockOfShelves(14, 15, 10, 13);
        addBlockOfShelves(18, 19, 10, 13);

        // 5. ROW 15: STAIRS, PRODUCT SEARCH & ATMs
        addService(new Service(Service.ServiceType.STAIRS, 1, 15));
        addService(new Service(Service.ServiceType.ATM, 3, 15));  // ATM
        addService(new Service(Service.ServiceType.PRODUCT_SEARCH, 8, 15));
        addService(new Service(Service.ServiceType.PRODUCT_SEARCH, 13, 15));
        addService(new Service(Service.ServiceType.ATM, 18, 15));  // ATM
        addService(new Service(Service.ServiceType.STAIRS, 20, 15));

        // 6. ROWS 16-17: WALL BLOCKS
        addBlockOfWalls(16, 17, 4, 5);
        addBlockOfWalls(16, 17, 10, 11);
        addBlockOfWalls(16, 17, 16, 17);

        // 7. ROW 20: TABLES & WALLS (NO STAIRS - only row 15 stairs connect floors)
        addBlockOfTables(3, 7, 20, 20);
        addBlockOfWalls(20, 20, 8, 8);
        addBlockOfTables(9, 12, 20, 20);
        addBlockOfWalls(20, 20, 13, 13);
        addBlockOfTables(14, 18, 20, 20);
    }

    private void addBlockOfChilledCounters(int xStart, int xEnd, int yStart, int yEnd) {
        for (int y = yStart; y <= yEnd; y++)
            for (int x = xStart; x <= xEnd; x++)
                addAmenity(new ChilledCounter(), x, y, "C", chilledCounters);
    }

    private void addBlockOfWalls(int yStart, int yEnd, int xStart, int xEnd) {
        for (int y = yStart; y <= yEnd; y++)
            for (int x = xStart; x <= xEnd; x++)
                if (isValid(x, y))
                    grid[y][x] = "=";
    }

    private void addBlockOfTables(int xStart, int xEnd, int yStart, int yEnd) {
        for (int y = yStart; y <= yEnd; y++)
            for (int x = xStart; x <= xEnd; x++)
                addAmenity(new Table(), x, y, "T", tables);
    }

    private void addBlockOfShelves(int xStart, int xEnd, int yStart, int yEnd) {
        for (int y = yStart; y <= yEnd; y++)
            for (int x = xStart; x <= xEnd; x++)
                addAmenity(new Shelf(), x, y, "H", shelves);
    }

    private void addBlockOfRefrigerators(int xStart, int xEnd, int yStart, int yEnd) {
        for (int y = yStart; y <= yEnd; y++)
            for (int x = xStart; x <= xEnd; x++)
                addAmenity(new Refrigerator(), x, y, "R", refrigerators);
    }

    private <T extends StorageUnit> void addAmenity(T obj, int x, int y, String symbol, ArrayList<T> list) {
        if (isValid(x, y)) {
            obj.setX(x);
            obj.setY(y);
            
            // Assign address before adding to list
            String location = getLocationName(x, y);
            String unitType = getUnitTypeName(obj);
            int unitNumber = list.size() + 1;
            
            String address = location + ", " + unitType + " " + unitNumber;
            obj.setAddress(address);
            
            list.add(obj);
            grid[y][x] = symbol;
        }
    }

    public void addService(Service s) {
        if (isValid(s.getX(), s.getY())) {
            services.add(s);
            String symbol = "S";
            switch (s.getType()) {
                case CART_STATION:
                    symbol = "K";
                    break;
                case BASKET_STATION:
                    symbol = "B";
                    break;
                case CHECKOUT_COUNTER:
                    symbol = "$";
                    break;
                case STAIRS:
                    symbol = "^";
                    break;
                case ENTRANCE:
                    symbol = "E";
                    break;
                case PRODUCT_SEARCH:
                    symbol = "?";
                    break;
                case ATM:  // NEW: ATM Machine
                    symbol = "M";
                    break;
            }
            grid[s.getY()][s.getX()] = symbol;
        }
    }

    public boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isWalkable(int x, int y) {
        if (!isValid(x, y))
            return false;
        String cell = grid[y][x];
        String nonWalkable = "#=HTR$^CKBE?M";  // FIXED: Added M for ATM
        return !nonWalkable.contains(cell);
    }

    public String getCell(int x, int y) {
        return isValid(x, y) ? grid[y][x] : null;
    }

    public void setCell(int x, int y, String val) {
        if (isValid(x, y))
            grid[y][x] = val;
    }

    public void printMap() {
        System.out.print("   ");
        for (int j = 0; j < width; j++)
            System.out.printf("%2d ", j);
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < width; j++)
                System.out.print(grid[i][j] + "  ");
            System.out.println();
        }
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public ArrayList<Refrigerator> getRefrigerators() {
        return refrigerators;
    }

    public ArrayList<Service> getServices() {
        return services;
    }
    
    public ArrayList<ChilledCounter> getChilledCounters() {
        return chilledCounters;
    }

    public Service getServiceAt(int x, int y) {
        for (Service s : services)
            if (s.getX() == x && s.getY() == y)
                return s;
        return null;
    }
    
    /**
     * Helper method to get location name (floor + aisle/wall) based on position
     */
    private String getLocationName(int x, int y) {
        String floorName = (currentFloor == 1) ? "GF" : "2F";
        
        // Row 1: Walls
        if (y == 1) {
            if (x >= 1 && x <= 6) return floorName + ", Wall 6";
            if (x >= 8 && x <= 13) return floorName + ", Wall 5";
            if (x >= 15 && x <= 20) return floorName + ", Wall 4";
        }
        
        // Rows 4-7: Top Aisles (13-18)
        if (y >= 4 && y <= 7) {
            if (x >= 2 && x <= 3) return floorName + ", Aisle 18";
            if (x >= 6 && x <= 7) return floorName + ", Aisle 17";
            if (x >= 10 && x <= 11) return floorName + ", Aisle 16";
            if (x >= 14 && x <= 15) return floorName + ", Aisle 15";
            if (x >= 18 && x <= 19) return floorName + ", Aisle 14";
        }
        
        // Rows 10-13: Bottom Aisles (19-23)
        if (y >= 10 && y <= 13) {
            if (x >= 2 && x <= 3) return floorName + ", Aisle 19";
            if (x >= 6 && x <= 7) return floorName + ", Aisle 20";
            if (x >= 10 && x <= 11) return floorName + ", Aisle 21";
            if (x >= 14 && x <= 15) return floorName + ", Aisle 22";
            if (x >= 18 && x <= 19) return floorName + ", Aisle 23";
        }
        
        // Row 20: Bottom walls/tables
        if (y == 20) {
            if (x >= 3 && x <= 7) return floorName + ", Wall 7";
            if (x >= 9 && x <= 12) return floorName + ", Wall 8";
            if (x >= 14 && x <= 18) return floorName + ", Wall 9";
        }
        
        return floorName + ", Unknown Location";
    }
    
    /**
     * Helper method to get storage unit type name
     */
    private String getUnitTypeName(StorageUnit unit) {
        if (unit instanceof Table) return "Table";
        if (unit instanceof Shelf) return "Shelf";
        if (unit instanceof Refrigerator) return "Refrigerator";
        if (unit instanceof ChilledCounter) return "ChilledCounter";
        return "Unit";
    }
}
