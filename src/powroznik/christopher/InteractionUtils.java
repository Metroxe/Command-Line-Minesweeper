package powroznik.christopher;

import java.util.Scanner;

class InteractionUtils {

    private int height;
    private int width;
    private int[][] field;
    private String[][] visField;
    private int progress;
    private int mines;

    InteractionUtils(int mHeight, int mWidth, int mProgress, int[][] mField, String[][] mVisField) {
        height = mHeight;
        width = mWidth;
        progress = mProgress;
        mines = mProgress;
        field = mField;
        visField = mVisField;
    }

    void manualSelection() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the x coord:");
        int x = scanner.nextInt() - 1;

        System.out.print("Enter the y coord:");
        int y = scanner.nextInt() - 1;

        System.out.print("Is this a mine? (true/false):");
        boolean mine = scanner.nextBoolean();

        if (mine && !visField[x][y].equals("X")) {
            visField[x][y] = "X";
            progress -= 1;

        } else if (visField[x][y].equals("X")) {
            visField[x][y] = checkLocation(x, y);
            progress += 1;

        } else {
            visField[x][y] = checkLocation(x, y);
        }

        if (visField[x][y].equals("9")) {
            Main.gameOver();
        }

        System.out.print("\n" + progress + " mines left");
    }

    private String checkLocation(int x, int y) {
        return Integer.toString(field[x][y]);
    }

    void printVisField() {
        System.out.print("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(visField[j][i] + "  ");
            }
            System.out.print("\n");
        }
    }

    boolean checkComplete() {
        int correct = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String vis = visField[j][i];
                String real = checkLocation(j, i);

                if (vis.equals("X") && real.equals("9")) {
                    correct += 1;

                    if (correct == mines) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
