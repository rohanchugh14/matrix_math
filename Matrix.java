import java.util.Arrays;

/**
 * A class that represents a matrix of integers. This matrix uses zero-based
 * indexing, so the first element is at row 0, column 0. It is a mutable Matrix
 * representation, but to ensure safety it should only be changed through the
 * methods provided.
 * 
 */
public class Matrix {
    private int[][] matrix;
    private int rows;
    private int cols;

    /**
     * Constructs a zero matrix with the given number of rows and columns.
     * @param rows the number of rows in the zero matrix
     * @param cols the number of columns in the zero matrix
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
    }

    /**
     * Constructs a matrix with the given number of rows and columns with every
     * element initialized to the given value.
     * @param rows the number of rows in the matrix
     * @param cols the number of columns in the matrix
     * @param initialVal the initial value of every element in the matrix
     */
    public Matrix(int rows, int cols, int initialVal) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = initialVal;
            }
        }
    }

    /**
     * Constructs a matrix using the given 2D array.
     * @param matrix the 2D array to use to construct the matrix
     */
    public Matrix(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    /**
     * Returns the number of rows in this matrix.
     * @return the number of rows in this matrix
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in this matrix.
     * @return the number of columns in this matrix
     */
    public int getCols() {
        return cols;
    }

    /**
     * Returns the matrix as a 2D array of ints. It is not a copy of the
     * internal representation of the matrix, so changes to the returned
     * array will affect the matrix. This is done for speed purposes for larger
     * matrices, and you should be careful to only read the returned array and
     * not write to it without either copying it or using the getCopy method
     * instead.
     * @return the matrix as a 2D array of ints
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Returns a copy of the matrix as a 2D array of ints. This is a safe
     * method to use if you want to read the matrix without affecting it.
     * @return a copy of the matrix as a 2D array of ints
     */
    public int[][] getCopy() {
        int[][] copy = new int[rows][cols];
        for(int i = 0; i < rows; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }

    /**
     * Sets the internal matrix to the given 2D array. 
     * @param matrix the matrix to set to
     */
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    /**
     * Sets this matrix to the given matrix.
     * @param matrix the matrix to set to
     */
    public void setMatrix(Matrix matrix) {
        this.matrix = matrix.matrix;
        this.rows = matrix.rows;
        this.cols = matrix.cols;
    }

    /**
     * Sets the element in the matrix.
     * @param row the row of the element to set
     * @param col the column of the element to set
     * @param value the value to set the element to
     */
    public void setElement(int row, int col, int value) {
        checkArgs(row, col);
        matrix[row][col] = value;
    }

    /**
     * Returns the element in the matrix.
     * @param row the row of the element to get
     * @param col the column of the element to get
     * @return the element in the matrix at row, col
     */
    public int getElement(int row, int col) {
        checkArgs(row, col);
        return matrix[row][col];
    }

    /**
     * Internal method to check if the given row and column are within bounds.
     * @param row the row index to check
     * @param col the column index to check
     */
    private void checkArgs(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException("Matrix index out of bounds");
        }
    }

    /**
     * Returns a string representation of the matrix.
     * @return a string representation of the matrix
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int[] row : matrix) {
            sb.append(Arrays.toString(row));
        }
        return sb.toString();
    }

    /**
     * Checks if the matrix is upper triangular
     * @return true if the matrix is upper triangular, false otherwise
     */
    public boolean isUpperTriangular() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < i; j++) {
                if(matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the matrix is lower triangular
     * @return true if the matrix is lower triangular, false otherwise
     */
    public boolean isLowerTriangular() {
        for(int i = 0; i < rows; i++) {
            for(int j = i + 1; j < cols; j++) {
                if(matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkDimensions(Matrix other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
    }

    public void add(Matrix other) {
        checkDimensions(other);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] += other.matrix[i][j];
            }
        }
    }

    public void subtract(Matrix other) {
        checkDimensions(other);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] -= other.matrix[i][j];
            }
        }
    }

    /**
     * Multiplies every element by the given scalar.
     * @param scalar the scalar to multiply by
     */
    public void scalarMultiply(int scalar) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] *= scalar;
            }
        }
    }

    /**
     * Finds the dot product between two vectors from current matrix and
     * Matrix other. Vectors can be any combination of row or column vectors.
     * @param isRowOne true if the first vector is a row vector, false for a column vector
     * @param isRowTwo true if the second vector is a row vector, false for a column vector
     * @param indexOne the index of the first vector (row or column)
     * @param indexTwo the index of the second vector (row or column)
     * @param other the other matrix to find the dot product with, second vector refers to this matrix
     * @return
     */
    public int dot(boolean isRowOne, boolean isRowTwo, int indexOne, int indexTwo, Matrix other) {
        int sum = 0;
        if (isRowOne) {
            if (isRowTwo) {
                checkArgs(indexOne, 0);
                other.checkArgs(indexTwo, 0);
                if (cols != other.cols) {
                    throw new IllegalArgumentException("Matrices must have the same number of columns");
                }
                for (int i = 0; i < cols; i++) {
                    sum += matrix[indexOne][i] * other.matrix[indexTwo][i];
                }
            } else {
                checkArgs(indexOne, 0);
                other.checkArgs(0, indexTwo);
                if (cols != other.rows) {
                    throw new IllegalArgumentException("Matrices must have the same number of columns and rows");
                }
                for (int i = 0; i < cols; i++) {
                    sum += matrix[indexOne][i] * other.matrix[i][indexTwo];
                }    
            } 
            
        } else {
            if (isRowTwo) {
                checkArgs(0, indexOne);
                other.checkArgs(indexTwo, 0);
                if(rows != other.cols) {
                    throw new IllegalArgumentException("Matrices must have the same number of rows and columns");
                }
                for(int i = 0; i < cols; i++) {
                   sum += matrix[i][indexOne] * other.matrix[indexTwo][i];
                }
            } else {
                if (cols != other.rows) {
                    throw new IllegalArgumentException("Matrices must have the same number of columns and rows");
                }
                checkArgs(0, indexOne);
                other.checkArgs(0, indexTwo);
                for(int i = 0; i < cols; i++) {
                    sum += matrix[i][indexOne] * other.matrix[i][indexTwo];
                }
            }
        } 
        return sum;
    }

    /**
     * Multiplies the matrix by the given matrix and sets this matrix to the
     * result.
     * @param other the matrix to multiply by
     */
    public void multiply(Matrix other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("Matrices must have compatible dimensions");
        }
        int[][] result = new int[rows][other.cols];
        // for every row in left matrix, find dot product with every col in 
        // right matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                result[i][j] = dot(true, false, i, j, other);
            }
        }
        matrix = result;
        cols = other.cols;
    }

    /**
     * Multiplies two matrices and returns the result as a new Matrix. m1 and m2
     * are not modified.
     * @param m1
     * @param m2
     * @return
     */
    public static Matrix multiply(Matrix m1, Matrix m2) {
        Matrix result = new Matrix(m1.getCopy());
        result.multiply(m2);
        return result;
    }

}