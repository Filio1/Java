package sudoku.sudoku;

import java.lang.*;

public class Sudoku {
    private static final int GRID_SIZE = 9;
    @SuppressWarnings("FieldMayBeFinal")
    private int[][] mat;
    static private final int N = 9; // number of columns/rows.
    static private final int SRN = 3; // square root of N
    @SuppressWarnings("FieldMayBeFinal")
    private int K; // No. Of missing digits


    // Constructor
    Sudoku()
    {
        this.K = (int)((Math.floor(Math.random() * (40 - 20)))) + 20;
        this.mat = new int[N][N];
    }

    public int[][] getMat() {
        return this.mat;
    }

    // Sudoku Generator
    public void fillValues()
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.mat[i][j] = 0;
            }
        }
        // Fill the diagonal with SRN x SRN matrices
        this.fillDiagonal();

        // Fill remaining blocks
        this.fillRemaining(0, SRN);

        // Remove Randomly K digits to make game
        this.removeKDigits();
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    void fillDiagonal()
    {

        for (int i = 0; i < N; i = i + SRN)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i < SRN; i++)
            for (int j = 0; j < SRN; j++)
                if (this.mat[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    void fillBox(int row,int col)
    {
        int num;
        for (int i = 0; i < SRN; i++)
        {
            for (int j = 0; j < SRN; j++)
            {
                do
                {
                    num = randomGenerator(N);
                }
                while (!unUsedInBox(row, col, num));

                this.mat[row + i][col + j] = num;
            }
        }
    }

    // Random generator
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Check if safe to put in cell
    boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - (i % SRN), j - (j%SRN), num));
    }

    // check in the row for existence
    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j < N; j++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i < N; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    boolean fillRemaining(int i, int j)
    {
        //  System.out.println(i+" "+j);
        if (j >= N && i < N - 1)
        {
            i = i + 1;
            j = 0;
        }
        if (i >= N && j >= N)
            return true;

        if (i < SRN)
        {
            if (j < SRN)
                j = SRN;
        }
        else if (i < N - SRN)
        {
            if (j == (i / SRN) * SRN)
                j =  j + SRN;
        }
        else
        {
            if (j == (N - SRN))
            {
                i = i + 1;
                j = 0;
                if (i >= N)
                    return true;
            }
        }

        for (int num = 1; num <= N; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                mat[i][j] = num;
                if (fillRemaining(i, j + 1)) {
                    return true;
                }

                mat[i][j] = 0;
            }
        }
        return false;
    }

    // Remove the K no. of digits to
    // complete game
    public void removeKDigits()
    {
        int count = this.K;
        while (count != 0)
        {
            int cellId = randomGenerator(N * N) - 1;

            int i = (cellId / N);
            int j = cellId % 9;
            if (j != 0)
                j = j - 1;

            // System.out.println(i+" "+j);
            if (this.mat[i][j] != 0)
            {
                count--;
                this.mat[i][j] = 0;
            }
        }
    }

    private boolean isNumberInRow(int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (this.mat[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInColumn(int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (this.mat[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInBox(int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (this.mat[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidPlacement(int number, int row, int column) {
        return (!isNumberInRow(number, row)) &&
                (!isNumberInColumn(number, column)) &&
                (!isNumberInBox(number, row, column));
    }
    public boolean solveBoard() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (this.mat[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(numberToTry, row, column)) {
                            this.mat[row][column] = numberToTry;

                            if (solveBoard()) {
                                return true;
                            }
                            else {
                                this.mat[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }
}