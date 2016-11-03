package powroznik.christopher;

public class Main {

    public static void main(String[] args) {
        FieldUtils fU = new FieldUtils();
        int height = fU.getHeight();
        int width = fU.getWidth();
        int mines = fU.getMines();
        boolean complete = false;

        while (mines >= height * width) {
            System.out.print("Error: Too many mines");
            mines = fU.getMines();
        }

        long seed = fU.getSeed();

        //create field
        int[][] field = fU.createField(height, width, mines, seed);
        fU.printField(height, width, field);

        //create visField
        String[][] visField = fU.createVisField(height, width);
        InteractionUtils iU = new InteractionUtils(height, width, mines, field, visField);

        //main loop
        while (!complete) {
            iU.printVisField();
            iU.manualSelection();
            complete = iU.checkComplete();
        }

        System.out.print("You Win!");
        fU.printField(height,width,field);
        System.exit(0);
    }

    static void gameOver() {
        System.out.print("Game Over!");
        System.exit(0);
    }
}
