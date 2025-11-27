/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Represents a cell in front of a shopper for interaction purposes.
 * Stores position and token information for front-facing cells.
 * 
 * @author Gabriel
 * @version 1.0.6
 */
public class FrontCell {
    private int x, y;
    private String token;

    /**
     * Constructs a FrontCell with specified coordinates and token.
     *
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @param token the token representing the cell type
     */
    public FrontCell(int x, int y, String token) {
        this.x = x;
        this.y = y;
        this.token = token;
    }

    /**
     * Gets the x-coordinate of the cell.
     *
     * @return the x-coordinate
     */
    public int getX() { 
        return x; 
    }
    
    /**
     * Gets the y-coordinate of the cell.
     *
     * @return the y-coordinate
     */
    public int getY() { 
        return y; 
    }
    
    /**
     * Gets the token representing the cell type.
     *
     * @return the cell token
     */
    public String getToken() { 
        return token; 
    }
    
    /**
     * Gets the cell type (alias for getToken()).
     *
     * @return the cell type token
     */
    public String getCellType() { 
        return token; 
    }
}