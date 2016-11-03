package powroznik.christopher;

import java.util.Random;
import java.util.Scanner;

class FieldUtils {

    int getHeight() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Height:");
        return scanner.nextInt();
    }

    int getWidth() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the length:");
        return scanner.nextInt();
    }

    int getMines() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of mines:");
        return scanner.nextInt();
    }

    long getSeed() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the seed:");
        return scanner.nextLong();
    }

    int[][] createField(int height, int width, int mines, long seed) {
        int[][] field = new int[height][width];
        Random rNG = new Random(seed);

        int placed = 0;

        while (placed < mines) {
            int x = rNG.nextInt(width);
            int y = rNG.nextInt(height);

            if (field[y][x] != 9) {
                field[y][x] = 9;
                placed += 1;
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (field[i][j] != 9) {
                    field[i][j] = inflate(j, i, height, width, field);
                }
            }
        }

        return field;
    }

    private int inflate(int y, int x, int h, int w, int[][] field) {
        int num = 0;
        h = h - 1;
        w = w - 1;

        //check topLeft
        if (x != 0 && y != 0) {
            if (field[x - 1][y - 1] == 9) {
                num += 1;
            }
        }

        //check left
        if (x != 0) {
            if (field[x - 1][y] == 9) {
                num += 1;
            }
        }

        //check botLeft
        if (x != 0 && y != h) {
            if (field[x - 1][y + 1] == 9) {
                num += 1;
            }
        }

        //check bot
        if (y != h) {
            if (field[x][y + 1] == 9) {
                num += 1;
            }
        }

        //check botRight
        if (x != w && y != h) {
            if (field[x + 1][y + 1] == 9) {
                num += 1;
            }
        }

        //check right
        if (x != w) {
            if (field[x + 1][y] == 9) {
                num += 1;
            }
        }

        //check topRight
        if (x != w && y != 0) {
            if (field[x + 1][y - 1] == 9) {
                num += 1;
            }
        }

        //check top
        if (y != 0) {
            if (field[x][y - 1] == 9) {
                num += 1;
            }
        }

        return num;
    }

    String[][] createVisField(int height, int width) {
        String[][] visField = new String[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                visField[j][i] = "O";
            }
        }
        return visField;
    }

    void printField(int height, int width, int[][] field) {
        System.out.print("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(field[j][i] + "  ");
            }
            System.out.print("\n");
        }
    }

}
