package powroznik.christopher;

import java.util.Scanner;

class InteractionUtils {

    private int h;
    private int w;
    private int[][] field;
    private String[][] visField;
    private int progress;
    private int mines;

    InteractionUtils(int mHeight, int mWidth, int mProgress, int[][] mField, String[][] mVisField) {
        h = mHeight - 1;
        w = mWidth - 1;
        progress = mProgress;
        mines = mProgress;
        field = mField;
        visField = mVisField;
    }

    private String checkLocation(int x, int y) {
        return Integer.toString(field[x][y]);
    }

    void manualSelection() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the x coord:");
        int x = scanner.nextInt() - 1;

        System.out.print("Enter the y coord:");
        int y = scanner.nextInt() - 1;

        System.out.print("Is this a mine? (true/false):");
        boolean mine = scanner.nextBoolean();

        // put marker
        if (mine && !visField[x][y].equals("X")) {
            visField[x][y] = "X";
            progress -= 1;
        }

        //remove marker & check
        else if (visField[x][y].equals("X")) {
            zeroLogic(x, y);
            progress += 1;
        }

        //no mine & check
        else {
            zeroLogic(x, y);
        }

        //wrong selection
        if (visField[x][y].equals("9")) {
            Main.gameOver();
        }

        System.out.print("\n" + progress + " mines left");
    }

    void printVisField() {
        System.out.print("\n");
        for (int i = 0; i < h + 1; i++) {
            for (int j = 0; j < w + 1; j++) {
                System.out.print(visField[j][i] + "  ");
            }
            System.out.print("\n");
        }
    }

    boolean checkComplete() {
        int correct = 0;

        for (int i = 0; i < h + 1; i++) {
            for (int j = 0; j < w + 1; j++) {
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

    private void zeroLogic(int x, int y) {

        if (field[x][y] == 0 && (visField[x][y].equals("O") || visField[x][y].equals("X"))) {

            visField[x][y] = "0";

            //check topLeft
            if (x != 0 && y != 0) {
                zeroLogic(x - 1, y - 1);
            }

            //check left
            if (x != 0) {
                zeroLogic(x - 1, y);
            }

            //check botLeft
            if (x != 0 && y != h) {
                zeroLogic(x - 1, y + 1);
            }

            //check bot
            if (y != h) {
                zeroLogic(x, y + 1);
            }

            //check botRight
            if (x != w && y != h) {
                zeroLogic(x + 1, y + 1);
            }

            //check right
            if (x != w) {
                zeroLogic(x + 1, y);
            }

            //check topRight
            if (x != w && y != 0) {
                zeroLogic(x + 1, y - 1);
            }

            //check top
            if (y != 0) {
                zeroLogic(x, y - 1);
            }

        } else {
            visField[x][y] = checkLocation(x, y);
        }
    }
}
