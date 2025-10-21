import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class Map {
    private String[][] map;
    private int width;
    private int height;

    // StorageUnits
    private ArrayList<Table> tables;
    private ArrayList<ChilledCounters> ChilledCounters;
    private ArrayList<Shelf> Shelf;
    private ArrayList<Refrigerator> Refrigerators;
    private ArrayList<Service> Service;

    
    private ArrayList<int[]> wallPositions;

    private HashMap<String, ArrayList<int[]>> furniturePosition;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new String[height][width];

        this.tables = new ArrayList<>();
        this.ChilledCounters = new ArrayList<>();
        this.Shelf = new ArrayList<>();
        this.Refrigerators = new ArrayList<>();
        this.Service = new ArrayList<>();

        initializeEmptyMap();
    }  

    public void initializeEmptyMap() {
        for (String[] row : map) {
            Arrays.fill(row," ");    
        }
    }
    // Super simple wall methods - just modify the map
    public void addWall(int x, int y) {
        if (isValidPosition(x, y)) {
            map[y][x] = "█";
        }
    }

    public void addPerimeterWalls() {
        for (int x = 0; x < width; x++) {
            addWall(x, 0);
            addWall(x, height - 1);
        }
        for (int y = 0; y < height; y++) {
            addWall(0, y);
            addWall(width - 1, y);
        }
    }

    // Even simpler furniture methods
    public void addTable(Table table, int x, int y) {
        if (isValidPosition(x, y) && map[y][x].equals(" ")) {
            tables.add(table);
            map[y][x] = "T";
        }
    }

    public void addChilledCounter(ChilledCounters cc, int x, int y) {
        if (isValidPosition(x, y) && map[y][x].equals(" ")) {
            ChilledCounters.add(cc);
            map[y][x] = "C";
        }
    }

    public void addRefrigerator(Refrigerator ref, int x, int y) {
        if (isValidPosition(x, y) && map[y][x].equals(" ")) {
            Refrigerators.add(ref);
            map[y][x] = "R";
        }
    }

    public void addShelf(Shelf shelf, int x, int y) {
        if (isValidPosition(x, y) && map[y][x].equals(" ")) {
            Shelf.add(shelf);
            map[y][x] = "H";
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void printMap() {
        System.out.print("   ");
        for (int j = 0; j < width; j++) {
            System.out.printf("%2d ", j);
        }
        System.out.println();

        for (int i = 0; i < height; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public void printStats() {
        System.out.println("\n=== RESTAURANT LAYOUT ===");
        System.out.println("Tables: " + tables.size());
        System.out.println("Chilled Counters: " + ChilledCounters.size());
        System.out.println("Refrigerators: " + Refrigerators.size());
        System.out.println("Shelves: " + Shelf.size());
    }



}
