package com.karcompany.iggroup;


import java.util.HashSet;
import java.util.Set;

public class RoboCleaner {
    public static int findCleanedCellsLength(String[] rows) {
        if (rows == null || rows.length == 0)
            return 0;

        int[][] grid = new int[rows.length][rows[0].length()];

        for(int i=0;i<rows.length;i++) {
            grid[i] = new int[rows[i].length()];
            for(int j=0;j<rows[i].length();j++) {
                if (rows[i].charAt(j) == '.') {
                    grid[i][j] = 0;
                } else if (rows[i].charAt(j) == 'X') {
                    grid[i][j] = 1;
                }
            }
        }

        int cMax = rows[0].length();
        int rMax = rows.length;
        int r = 0, c = 0;
        int cleaned = 0;
        Set<String> visited = new HashSet<>();
        Set<String> visitedWithDirection = new HashSet<>();
        char direction = 'r';
        boolean changed;
        while(true) {
            changed = false;
            if (grid[r][c] == 0) {
//                grid[r][c] = 2;
                String candidate = r+","+c;
                if (!visited.contains(candidate)) {
                    visited.add(candidate);
                    cleaned++;
                } else if (visitedWithDirection.contains(candidate+","+direction)) {
                    break;
                }
                visitedWithDirection.add(candidate+","+direction);
            }

            if (direction == 'r') {
                if ((c+1 < cMax) && grid[r][c+1] == 0) {
                    c++;
                } else if ((c+1 < cMax))
                    direction = 'b';
                changed = true;
            }
            else if (direction == 'b') {
                if ((r+1 < rMax) && grid[r+1][c] == 0) {
                    r++;
                } else if ((r+1 < rMax))
                    direction = 'l';
                else
                    direction = 'l';
                changed = true;
            }
            else if (direction == 'l') {
                if ((c-1 >= 0) && grid[r][c-1] == 0) {
                    c--;
                } else if ((c-1) >= 0)
                    direction = 't';
                else
                    direction = 't';
                changed = true;
            }
            else if (direction == 't') {
                if ((r-1 >= 0) && grid[r-1][c] == 0) {
                    r--;
                } else if ((r-1) >= 0)
                    direction = 'r';
                else
                    direction = 'r';
                changed = true;
            }

            if (!changed) {
                break;
            }
        }

        return cleaned;
    }

    public static void main(String[] args) {
        String[] rows = new String[] {
//                "...X..", "....XX", "..X..."
//                "....X..", "X......", ".....X.", "......."
                "...X.", ".X..X", "X...X", "..X.."
        };
        System.out.println(findCleanedCellsLength(rows));
    }
}
