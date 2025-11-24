/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */
public class FrontCell {
    private int x, y;
    private String token;

    public FrontCell(int x, int y, String token) {
        this.x = x;
        this.y = y;
        this.token = token;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getToken() { return token; }
}
