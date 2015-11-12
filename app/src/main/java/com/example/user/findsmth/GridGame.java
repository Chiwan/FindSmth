package com.example.user.findsmth;

/**
 * Created by Jean-Philippe Kha on 12/11/2015.
 */
public class GridGame {
    // x et y sont la tailles de la grille
    private int x;
    private int y;
    private int[][] grid;
    public GridGame(int x, int y){
        this.x=x;
        this.y=y;
        grid=new int[x][y];
    }
    public GridGame(){
        x=16;
        y=16;
        grid=new int[16][16];
        //0 noir, 1 blanc 2 entré 3 sortie
        for (int i =0;i<16;i++){
            for(int j =0; j<16;j++) {
                if(j==15){
                    grid[i][j] =0;
                }else if(i==15){
                    grid[i][j] =0;
                }else {
                    grid[i][j] = 1;
                }
            }
        }
        grid[0][0]=2;
        grid[5][10]=3;
        grid[3][5]=4;
        grid[10][14]=5;
        grid[14][14]=6;
        grid[4][14]=7;
        grid[10][5]=8;

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

}
