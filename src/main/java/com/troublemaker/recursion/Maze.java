package com.troublemaker.recursion;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @BelongsProject: data_structure
 * @BelongsPackage: com.troublemaker.recursion
 * @Author: troublemaker
 * @CreateTime: 2022-05-07  00:04
 * @Description: TODO
 * @Version: 1.0
 */
public class Maze {

    private final int[][] mazeMap = new int[10][10];

    private void initMazeMap() {
        for (int i = 0; i < mazeMap.length; i++) {
            mazeMap[i][0] = 1;
            mazeMap[i][mazeMap[0].length - 1] = 1;
        }
        for (int i = 0; i < mazeMap[0].length; i++) {
            mazeMap[0][i] = 1;
            mazeMap[mazeMap.length - 1][i] = 1;
        }
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            mazeMap[(random.nextInt(6) + 2)][(random.nextInt(6) + 2)] = 1;
        }
    }

    public void showMazeMap() {
        for (int[] ints : mazeMap) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
    }

    public boolean outMazeMap(int i, int j) {
        if (i == 8 && j == 9) {
            return true;
        } else {
            //0表示未走过
            if (mazeMap[i][j] == 0) {
                mazeMap[i][j] = 2;
                //四个方向进行判断，只要不是0，直接false
                if (outMazeMap(i + 1, j)) {
                    return true;
                } else if (outMazeMap(i, j + 1)) {
                    return true;
                } else if (outMazeMap(i - 1, j)) {
                    return true;
                } else if (outMazeMap(i, j - 1)) {
                    return true;
                } else {
                    //四个方向false 置为3
                    mazeMap[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    @Test
    public void test() {
        Maze maze = new Maze();
        maze.initMazeMap();
        maze.showMazeMap();
        maze.outMazeMap(1, 1);
        System.out.println("==========================================");
        maze.showMazeMap();

    }

}

