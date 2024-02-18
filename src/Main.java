import java.io.*;
public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String ERROR = "\u001B[31m"; // RED
    public static final String INPUT = "\u001B[32m"; // GREEN
    public static final String COMMENT = "\u001B[33m"; // YELLOW
    public static final String CHOOSE = "\u001B[34m"; // BLUE
    public static final String OUTPUT = "\u001B[35m"; // PURPLE
    public static final String HEADER_OUTPUT = "\u001B[36m"; // CYAN
    public static void main(String[] args) throws FileNotFoundException {
        String pathToMatrix = "src/inputMatrix.txt";
        Matrix matrix = new Matrix(pathToMatrix);

        matrix.printMatrix();
        System.out.println("Наибольшее по модулю собственное значение заданной матрицы:");
        System.out.println(matrix.powMethod(null));
    }

    /* EXAMPLES

    #1
        1	0	-5	    l1 = 1
        2	-1	-3	    l2 = -2
        0	0	-2	    l3 = -1

    #2
        1	0	0	    l1 = 1
        3	-1	10	    l2 = -2
        -10	0	-2	    l3 = -1

    #3
        1	0	0	    l1 = 1
        3	5	10	    l2 = -2
        -10	0	-2	    l3 = 5

    #4
        4	0	1	    l1 = -18,15
        -11	-5	18	    l2 = 4,46
        6	12	-1	    l3 = 11,69

    #5
        -3  4   -2      l1 = 1
        1   0   1       l2 = 2
        6   -6  5       l3 = -1

    #6
        2   -1  -1      l1 = l2 = 1
        3   -2  -3      l3 = 0
        -1  1   2

    */

}