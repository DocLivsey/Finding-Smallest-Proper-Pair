import java.io.*;
import java.util.*;
import java.text.*;
public class Matrix {
    protected int rowsCount;
    protected int columnsCount;
    protected double[][] matrix;
    Matrix(String pathToFile) throws FileNotFoundException
    {
        File input = new File(pathToFile);
        Scanner whileScan = new Scanner(input);
        String line = whileScan.nextLine();
        String[] strArr = line.trim().split("\\s+");
        double[] row;

        this.rowsCount = 1;
        this.columnsCount = strArr.length;
        this.matrix = new double[this.rowsCount][this.columnsCount];


        while (whileScan.hasNextLine())
        {
            line = whileScan.nextLine();
            strArr = line.trim().split("\\s+");
            row = new double[strArr.length];
            for (int i = 0; i < strArr.length; i++)
                row[i] = Double.parseDouble(strArr[i]);
            this.addRow(row);
        }
        Scanner finalScan = new Scanner(input);
        line = finalScan.nextLine();
        strArr = line.trim().split("\\s+");
        row = new double[strArr.length];
        for (int i = 0; i < strArr.length; i++)
            row[i] = Double.parseDouble(strArr[i]);
        this.setRow(row, 0);
    }
    Matrix()
    {
        System.out.println(Main.INPUT + "Введите количество строк и столбцов в матрице:" + Main.RESET);
        Scanner scan = new Scanner(System.in);
        int rowsCount = scan.nextInt();
        int columnsCount = scan.nextInt();
        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
        this.matrix = new double[rowsCount][columnsCount];
    }
    Matrix(int rowsCount, int columnsCount)
    {
        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
        this.matrix = new double[rowsCount][columnsCount];
        for (int i = 0; i < this.rowsCount; i++)
            for (int j = 0; j < this.columnsCount; j++)
                this.setItem(i, j, Double.NaN);
    }
    Matrix(double[][] matrix, int rowsCount, int columnsCount)
    {
        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
        this.matrix = matrix;
    }
    int getRowsCount()
    { return this.rowsCount; }
    int getColumnsCount()
    { return this.columnsCount; }
    double[][] getMatrix()
    { return this.matrix; }
    double getItem (int indexRow, int indexColumn)
    { return this.matrix[indexRow][indexColumn]; }
    void setMatrix(double[][] matrix)
    { this.matrix = matrix; }
    void setItem(int rowIndex, int colIndex, double replaceItem)
    { this.matrix[rowIndex][colIndex] = replaceItem; }
    void setRow(double[] row, int index)
    {
        if (this.columnsCount != row.length)
        { System.out.println(Main.ERROR + "Невозможно заменить строку в исходной матрице из-за несоответсвия количества столбцов" + Main.RESET); }
        else
            this.matrix[index] = row;
    }
    void setColumn(double[] column, int index)
    {
        if (this.rowsCount != column.length)
        { System.out.println(Main.ERROR + "Невозможно заменить столбец в исходной матрице из-за несоответсвия количества столбцов" + Main.RESET); }
        else
            for (int i = 0; i < this.rowsCount; i++)
                this.setItem(i, index, column[i]);
    }
    public boolean equals(Object obj)
    { return super.equals(obj); }
    Matrix cloneMatrix()
    {
        Matrix cloneMatrix = new Matrix(this.rowsCount, this.columnsCount);
        for (int i = 0; i < this.rowsCount; i++)
            for (int j = 0; j < this.columnsCount; j++)
                cloneMatrix.setItem(i, j, this.getItem(i, j));
        return cloneMatrix;
    }
    void createMatrix()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println(Main.INPUT + "Введите элементы матрицы " + rowsCount + " на " + columnsCount + ":" + Main.RESET);
        for(int i = 0; i < this.rowsCount; i++)
            for (int j = 0; j < this.columnsCount; j++)
            {
                double a = scan.nextDouble();
                this.setItem(i, j, a);
            }
    }
    void createRandomMatrix(double from, double to)
    {
        Random random = new Random();
        for (int i = 0; i < this.rowsCount; i++)
            for (int j = 0; j < this.columnsCount; j++)
                this.setItem(i, j, random.nextDouble(from, to));
    }
    void createRandomIntMatrix(int from, int to)
    {
        Random random = new Random();
        for (int i = 0; i < this.rowsCount; i++)
            for (int j = 0; j < this.columnsCount; j++)
                this.setItem(i, j, random.nextInt(from, to));
    }
    public void createSingleMatrix()
    {
        for (int i = 0; i < this.rowsCount; i++)
            for (int j = 0; j < this.columnsCount; j++)
            {
                if (i == j) this.setItem(i, j, 1);
                else this.setItem(i, j, 0);
            }
    }
    void printMatrix()
    {
        System.out.println(Main.HEADER_OUTPUT + "Матрица " + rowsCount + " на " + columnsCount + ":" + Main.OUTPUT);
        for(int i = 0; i < this.rowsCount; i++)
        {
            for (int j = 0; j < this.columnsCount; j++)
            {
                System.out.print(this.getItem(i, j) + " ");
            }
            System.out.println();
        }
        System.out.print(Main.RESET);
    }
    void printFormattedMatrix()
    {
        System.out.println(Main.HEADER_OUTPUT + "Матрица " + rowsCount + " на " + columnsCount + ":" + Main.OUTPUT);
        for(int i = 0; i < this.rowsCount; i++)
        {
            for (int j = 0; j < this.columnsCount; j++)
            {
                DecimalFormat formattedOut = new DecimalFormat("#.##");
                String result = formattedOut.format(this.getItem(i, j));
                System.out.print(result + " ");
            }
            System.out.println();
        }
        System.out.print(Main.RESET);
    }
    void writeInFile(String pathToFile) throws IOException
    {
        FileWriter fileWriter = new FileWriter(pathToFile);
        for (int i = 0; i < this.rowsCount; i++)
        {
            for (int j = 0; j < this.columnsCount; j++)
            {
                DecimalFormat formattedOut = new DecimalFormat("#.##");
                String result = formattedOut.format(this.getItem(i, j));
                fileWriter.write(result + " ");
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
    void writeFormattedInFile(String pathToFile) throws IOException
    {
        FileWriter fileWriter = new FileWriter(pathToFile);
        for (int i = 0; i < this.rowsCount; i++)
        {
            for (int j = 0; j < this.columnsCount; j++)
            {
                fileWriter.write(this.getItem(i, j) + " ");
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
    }
    void addRow(double[] row)
    {
        if (row.length != this.columnsCount)
        { System.out.println(Main.ERROR + "Невозможно добавить строку в исходную матрицу из-за несоответсвия количества столбцов" + Main.RESET); }
        else
        {
            this.rowsCount ++;
            double[][] newMatrix = new double[this.rowsCount][this.columnsCount];
            for (int i = 0; i < this.rowsCount; i++)
            {
                if (i != this.rowsCount - 1)
                    System.arraycopy(this.matrix[i], 0, newMatrix[i], 0, this.columnsCount);
                else newMatrix[i] = row;
            }
            this.matrix = newMatrix;
        }
    }
    void addRowAfter (double[] row, int index)
    {
        if (row.length != this.columnsCount)
        { System.out.println(Main.ERROR + "Невозможно добавить строку в исходную матрицу из-за несоответсвия количества столбцов" + Main.RESET); }
        else
        {
            this.rowsCount ++;
            double[][] newMatrix = new double[this.rowsCount][this.columnsCount];
            newMatrix[index + 1] = row;
            for (int i = 0; i < this.rowsCount; i++)
            {
                if (i < index + 1)
                    newMatrix[i] = this.matrix[i];
                else if (i > index + 1)
                    newMatrix[i] = this.matrix[i - 1];
            }
            this.matrix = newMatrix;
        }
    }
    void deleteRow(int index)
    {
        if (index > this.rowsCount || index < 0)
        { System.out.println(Main.ERROR + "Номер строки указан неверно" + Main.RESET); }
        else
        {
            Matrix newMatrix = new Matrix(this.rowsCount - 1, this.columnsCount);
            int tmpIndex = 0;
            for (int i = 0; i < this.rowsCount; i++)
                if (i != index)
                {
                    newMatrix.setRow(this.matrix[i], tmpIndex);
                    tmpIndex++;
                }
            this.rowsCount --;
            this.matrix = newMatrix.getMatrix();
        }
    }
    void addColumn(double[] column)
    {
        if (column.length != this.rowsCount)
        { System.out.println(Main.ERROR + "Невозможно добавить столбец в исходную матрицу из-за несоответсвия количества строк" + Main.RESET); }
        else
        {
            this.columnsCount ++;
            double[][] newMatrix = new double[this.rowsCount][this.columnsCount];
            for (int i = 0; i < this.rowsCount; i++)
                for (int j = 0; j < this.columnsCount; j++)
                {
                    if (j != this.columnsCount - 1)
                        newMatrix[i][j] = this.getItem(i, j);
                    else newMatrix[i][j] = column[i];
                }
            this.matrix = newMatrix;
        }
    }
    void addColumnAfter (double[] column, int index)
    {
        if (column.length != this.rowsCount)
        { System.out.println(Main.ERROR + "Невозможно добавить столбец в исходную матрицу из-за несоответсвия количества строк" + Main.RESET); }
        else
        {
            this.columnsCount ++;
            double[][] newMatrix = new double[this.rowsCount][this.columnsCount];
            for (int i = 0; i < this.rowsCount; i++)
            {
                newMatrix[i][index + 1] = column[i];
                for (int j = 0; j < this.columnsCount; j++)
                {
                    if (j < index + 1)
                        newMatrix[i][j] = this.getItem(i, j);
                    else if (j > index + 1)
                        newMatrix[i][j] = this.getItem(i, j - 1);
                }
            }
            this.matrix = newMatrix;
        }
    }
    void deleteColumn(int index)
    {
        if (index > this.columnsCount || index < 0)
        { System.out.println(Main.ERROR + "Номер столбца указан неверно" + Main.RESET); }
        else
        {
            Matrix newMatrix = new Matrix(this.rowsCount, this.columnsCount - 1);
            int tmpIndex = 0;
            for (int i = 0; i < this.columnsCount; i++)
                if (i != index)
                {
                    double[] tmpCol = new double[this.rowsCount];
                    for (int j = 0; j < this.rowsCount; j++)
                        tmpCol[j] = this.getItem(j, i);
                    newMatrix.setColumn(tmpCol, tmpIndex);
                    tmpIndex++;
                }
            this.columnsCount --;
            this.matrix = newMatrix.getMatrix();
        }
    }
    Matrix partOfMatrix(int leftBorder, int rightBorder, int upBorder, int downBorder)
    {
        int newRowsCount = downBorder - upBorder + 1;
        int newColumnsCount = rightBorder - leftBorder + 1;
        double[][] matrixPart = new double[newRowsCount][newColumnsCount];
        for (int oldRow = upBorder, newRow = 0; oldRow < downBorder + 1; oldRow++, newRow++)
            for (int oldCol = leftBorder, newCol = 0; oldCol < rightBorder + 1; oldCol++, newCol++)
                matrixPart[newRow][newCol] = this.getItem(oldRow, oldCol);
        return new Matrix(matrixPart, newRowsCount, newColumnsCount);
    }
    Matrix matrixAddition(Matrix addMatrix)
    {
        if (this.rowsCount != addMatrix.rowsCount || this.columnsCount != addMatrix.columnsCount)
        {
            System.out.println(Main.ERROR + "Размеры матриц разные \n" + Main.COMMENT + "Пожалуйста, введите матрицы одного размера" + Main.RESET);
            return null;
        }
        else
        {
            Matrix resultMatrix = new Matrix(this.rowsCount, addMatrix.columnsCount);
            for(int i = 0; i < resultMatrix.getRowsCount(); i++)
                for(int j = 0; j < resultMatrix.getColumnsCount(); j++)
                { resultMatrix.setItem(i, j, this.getItem(i, j) + addMatrix.getItem(i, j)); }
            return resultMatrix;
        }
    }
    Matrix constantMultiplication(double constant)
    {
        double[][] newMatrix = this.matrix;
        for (int i = 0; i < this.rowsCount; i++)
            for (int j = 0; j < this.columnsCount; j++)
            { newMatrix[i][j] *= constant; }
        return new Matrix(newMatrix, this.rowsCount, this.columnsCount);
    }
    Matrix matrixMultiplication(Matrix addMatrix)
    {
        if (this.columnsCount != addMatrix.getRowsCount())
        {
            System.out.println(Main.ERROR + "Размеры матриц разные \n" + Main.COMMENT +
                    "Пожалуйста, убедитесь, что количество столбцов первой матрицы равно количеству строк второй матрицы" + Main.RESET);
            return null;
        }
        else
        {
            Matrix resultMatrix = new Matrix(this.rowsCount, addMatrix.getColumnsCount());
            for (int i = 0; i < this.rowsCount; i++)
            {
                resultMatrix.getMatrix()[i] = new double[addMatrix.getColumnsCount()];
                for (int j = 0; j < addMatrix.getColumnsCount(); j++)
                {
                    resultMatrix.setItem(i, j, 0);

                    for (int k = 0; k < addMatrix.getRowsCount(); k++)
                    { resultMatrix.getMatrix()[i][j] += this.getItem(i, k) * addMatrix.getItem(k, j); }
                }
            }
            return resultMatrix;
        }
    }
    public Matrix matrixPow(int pow)
    {
        Matrix matrixPow = this.cloneMatrix();
        for (int i = 0; i < pow - 1; i++)
            matrixPow = matrixPow.matrixMultiplication(this).cloneMatrix();
        return matrixPow;
    }
    Vector matrixAndVectorMultiplication(Vector addVector)
    {
        Matrix vectorMatrix = addVector.vectorToMatrix();
        Vector resultVector;
        resultVector = this.matrixMultiplication(vectorMatrix).matrixToVector();
        return resultVector;
    }
    Vector matrixToVector()
    {
        if (this.columnsCount != 1)
        {
            System.out.println(Main.ERROR + "Преобразование из матрицы в вектор невозможно." + Main.RESET);
            return null;
        }
        else
        {
            double[] convertVector = new double[this.rowsCount];
            for (int i = 0; i < this.rowsCount; i++)
                convertVector[i] = this.getItem(i, 0);
            return new Vector(convertVector, this.rowsCount);
        }
    }
    Matrix transposition()
    {
        double[][] transpositionMatrix = new double[this.columnsCount][this.rowsCount];
        for(int i = 0; i < this.columnsCount; i++)
            for (int j = 0; j < this.columnsCount; j++)
            { transpositionMatrix[i][j] = this.getItem(j, i); }
        return new Matrix(transpositionMatrix, this.columnsCount, this.rowsCount);
    }

    boolean isMatrixEqual(Matrix compareMatrix)
    {
        if (this.rowsCount != compareMatrix.getRowsCount() || this.columnsCount != compareMatrix.getColumnsCount())
        {
            System.out.println(Main.ERROR + "Размеры матриц разные" + Main.RESET);
            return false;
        }
        else
        {
            for (int i = 0; i < this.rowsCount; i++)
                for (int j = 0; j < this.columnsCount; j++)
                    if (this.getItem(i, j) != compareMatrix.getItem(i, j))
                        return false;
            return true;
        }
    }
    void swapRow(int indexChange, int indexChangeWith)
    {
        double[] tmpRow = this.matrix[indexChange];
        this.matrix[indexChange] = this.matrix[indexChangeWith];
        this.matrix[indexChangeWith] = tmpRow;
    }
    void swapColumn(int indexChange, int indexChangeWith)
    {
        for (int i = 0; i < this.rowsCount; i++)
        {
            double tmpCol = this.getItem(i, indexChange);
            this.setItem(i, indexChange, this.getItem(i, indexChangeWith));
            this.setItem(i, indexChangeWith, tmpCol);
        }
    }
    double gaussianDeterminant()
    {
        double[][] copyMatrix = new double[this.rowsCount][this.columnsCount];
        for (int i = 0; i < this.rowsCount; i++)
            System.arraycopy(this.matrix[i], 0, copyMatrix[i], 0, this.columnsCount);
        Matrix tempMatrix = new Matrix(copyMatrix, this.rowsCount, this.columnsCount);
        double determinant = 1;
        for (int k = 0; k < rowsCount - 1; k++)
            for (int i = k + 1; i < rowsCount; i++)
            {
                if (tempMatrix.getItem(k, k) == 0)
                {
                    for (int l = i; l < this.rowsCount; l++)
                    {
                        if (tempMatrix.getItem(l, l) != 0) {tempMatrix.swapRow(k, l); determinant *= -1;}
                        else if (tempMatrix.getItem(l, l) == 0 && l == this.rowsCount - 1)
                            System.out.println(Main.ERROR + "Ошибка! Деление на ноль, невозможно посчитать определитель" + Main.RESET);
                    }
                }
                double tmp = tempMatrix.getItem(i, k) / tempMatrix.getItem(k, k);
                for (int j = k; j < columnsCount; j++)
                { tempMatrix.getMatrix()[i][j] -= tmp * tempMatrix.getItem(k, j); }
            }
        for (int i = 0; i < this.rowsCount; i++)
        { determinant *= tempMatrix.getItem(i, i); }
        return Math.round(determinant);
    }
    double matrix2By2Determinant()
    {
        if (this.rowsCount != 2 || this.columnsCount != 2)
        {
            System.out.println(Main.ERROR + "Ошибка! Данный метод вычисления определителя работает только для матриц 2 на 2");
            return  0;
        }
        else
        {
            double firstTerm = this.getItem(0, 0) * this.getItem(1, 1);
            double secondTerm = this.getItem(0, 1) * this.getItem(1, 0);

            return firstTerm - secondTerm;
        }
    }
    double matrix3By3Determinant()
    {
        if (this.rowsCount != 3 || this.columnsCount != 3)
        {
            System.out.println(Main.ERROR + "Ошибка! Данный метод вычисления определителя работает только для матриц 3 на 3");
            return  0;
        }
        else
        {
            double firstTerm = 1;
            for (int i = 0; i < this.rowsCount; i++)
                firstTerm *= this.getItem(i, i);
            double secondTerm = this.getItem(0, 1) * this.getItem(1, 2) * this.getItem(2, 0);
            double thirdTerm = this.getItem(0, 2) * this.getItem(1, 0) * this.getItem(2, 1);
            double fourthTerm = this.getItem(0, 0) * this.getItem(1, 2) * this.getItem(2, 1);
            double fifthTerm = this.getItem(0, 1) * this.getItem(1, 0) * this.getItem(2, 2);
            double sixthTerm = this.getItem(0, 2) * this.getItem(1,1) * this.getItem(2, 0);

            return firstTerm + secondTerm + thirdTerm - fourthTerm - fifthTerm - sixthTerm;
        }
    }
    double calculateDeterminant()
    {
        double determinant;
        if (this.rowsCount == 2 && this.columnsCount == 2)
            determinant = this.matrix2By2Determinant();
        else if (this.rowsCount == 3 && this.columnsCount == 3)
            determinant = this.matrix3By3Determinant();
        else
            determinant = this.gaussianDeterminant();
        return determinant;
    }
    Matrix setMinor(int rowIndex, int colIndex)
    {
        Matrix minor = this.cloneMatrix();
        minor.deleteRow(rowIndex);
        minor.deleteColumn(colIndex);
        return minor.cloneMatrix();
    }
    Matrix inversion()
    {
        Matrix inversiveMatrix = new Matrix(this.rowsCount, this.columnsCount);
        if (this.rowsCount != this.columnsCount)
            System.out.println(Main.ERROR + "Ошибка! Матрица не квадратичная. Обратную невозможно посчитать" + Main.RESET);
        else
        {
            double determinant = this.calculateDeterminant();
            if (determinant == 0)
                System.out.println(Main.ERROR + "Заданная матрица Вырождена. Невозможно найти обратную" + Main.RESET);
            else
            {
                for (int i = 0; i < this.rowsCount; i++)
                {
                    for (int j = 0; j < this.columnsCount; j++)
                    {
                        Matrix minor = this.setMinor(i, j);
                        double minorDet = minor.calculateDeterminant() * Math.pow(-1, (i + j));
                        inversiveMatrix.setItem(i, j, minorDet);
                    }
                }
                inversiveMatrix = inversiveMatrix.transposition();
                inversiveMatrix = inversiveMatrix.constantMultiplication(1 / determinant);
            }
        }
        return inversiveMatrix;
    }
    public boolean isNanMatrix()
    {
        for (double[] row : this.matrix)
            for (double item : row)
                if (Double.isNaN(item))
                    return true;
        return false;
    }
    public boolean isMatrixSingular()
    { return this.calculateDeterminant() == 0; }
    public static class EigenPair {
        protected double eigenValue;
        protected Vector eigenVector;
        public EigenPair(double eigenValue, Vector eigenVector)
        { this.eigenValue = eigenValue; this.eigenVector = eigenVector; }
        public double getEigenValue() {
            return eigenValue;
        }
        public Vector getEigenVector() {
            return eigenVector;
        }
        public void setEigenValue(double eigenValue) {
            this.eigenValue = eigenValue;
        }
        public void setEigenVector(Vector eigenVector) {
            this.eigenVector = eigenVector;
        }
        @Override
        public String toString()
        { return "eigenValue = " + this.eigenValue + " eigenVector = " + this.eigenVector.toString(); }
        public static boolean isTheVectorSignChanged(Vector eigenVectorBefore, Vector eigenVectorAfter)
        {
            boolean flagIsChanged = true;
            for (int i = 0; i < eigenVectorBefore.getVectorSize(); i++)
                if (Math.round(eigenVectorAfter.getItem(i) * eigenVectorBefore.getItem(i)) > 0)
                    flagIsChanged = false;
            return flagIsChanged;
        }
    }
    public double PM_AlgorithmFindBiggestEigenPair(Vector y_0)
    {
        System.out.println(Main.ERROR + "Внимание, степенной метод работает только для матриц простой структуры" + Main.RESET);
        double maxAbsLambda;
        if (y_0 == null)
        {
            y_0 = new Vector(this.rowsCount);
            y_0.createRandomVector(0, 10);
        }
        double normaY = y_0.ChebyshevNorm();
        Vector x_0 = y_0.cloneVector().constantMultiplication(1 / normaY);
        double lambda_K = 0;
        MathBase eps = new MathBase();
        Vector xPrev = x_0.cloneVector();
        do {
            maxAbsLambda = lambda_K;
            Vector yNew = this.matrixAndVectorMultiplication(xPrev).cloneVector();
            normaY = yNew.ChebyshevNorm();
            for (int i = 0; i < xPrev.getVectorSize(); i++)
                if (Math.abs(xPrev.getItem(i)) > eps.getEpsilon())
                    lambda_K += (yNew.getItem(i) / xPrev.getItem(i));
            Vector xNew = yNew.cloneVector().constantMultiplication(1 / normaY);
            xPrev = xNew.cloneVector();
        } while(Math.abs(lambda_K - maxAbsLambda) >= eps.getEpsilon());
        maxAbsLambda = lambda_K;
        return maxAbsLambda;
    }
    public EigenPair powMethod(int pow, Vector y_0)
    {
        double maxAbsLambda;
        System.out.println(Main.ERROR + "Внимание, степенной метод работает только для матриц простой структуры" + Main.RESET);
        if (y_0 == null)
        {
            y_0 = new Vector(this.rowsCount);
            y_0.createRandomVector(0, 10);
        }
        double normaY = y_0.ChebyshevNorm();
        Vector x_0 = y_0.cloneVector().constantMultiplication(1 / normaY);
        double lambda_K = 0;
        MathBase eps = new MathBase();
        Vector xPrev = x_0.cloneVector();
        do {
            maxAbsLambda = lambda_K;
            Vector yNew = this.matrixPow(pow).cloneMatrix().matrixAndVectorMultiplication(xPrev).cloneVector();
            normaY = yNew.ChebyshevNorm();
            for (int i = 0; i < xPrev.getVectorSize(); i++)
                if (Math.abs(xPrev.getItem(i)) > eps.getEpsilon())
                    lambda_K = (yNew.getItem(i) / xPrev.getItem(i));
            lambda_K = Math.pow(Math.abs(lambda_K), (double) 1 / pow);
            Vector xNew = yNew.cloneVector().constantMultiplication(1 / normaY);
            xPrev = xNew.cloneVector();
        } while(Math.abs(lambda_K - maxAbsLambda) >= eps.getEpsilon());
        maxAbsLambda = lambda_K;
        if (EigenPair.isTheVectorSignChanged(xPrev, this.matrixAndVectorMultiplication(xPrev)))
            maxAbsLambda = -maxAbsLambda;
        return new EigenPair(maxAbsLambda, xPrev);
    }
    /* СТЕПЕННОЙ МЕТОД
    АЛГОРИТМ ВЫЧИСЛЕНИЯ НАИБОЛЬШЕГО ПО МОДУЛЮ СОБСТВЕННОГО ЗНАЧЕНИЯ МАТРИЦЫ */
    public EigenPair advancedPowMethod(int pow, Vector y_0)
    {
        switch (ParseMessage.parseChoiceOfTwo("Max", "Min"))
        {
            case 1:
                return this.powMethod(pow, y_0);
            case 2:
                double biggestLambda = this.powMethod(pow, y_0).getEigenValue();
                Matrix singleMatrix = new Matrix(this.rowsCount, this.columnsCount);
                singleMatrix.createSingleMatrix();
                singleMatrix = singleMatrix.constantMultiplication(-biggestLambda);
                Matrix newMatrix = this.matrixAddition(singleMatrix).cloneMatrix();
                EigenPair newPair = newMatrix.powMethod(pow, y_0);
                return new EigenPair(biggestLambda + newPair.getEigenValue(),
                        this.powMethod(pow, y_0).getEigenVector().vectorAddition(newPair.getEigenVector()));
            default:
                System.out.println(Main.ERROR + "ERROR" + Main.RESET);
        }
        return new EigenPair(Double.NaN, null);
    }
    /* ПРОДВИНУТЫЙ СТЕПЕННОЙ МЕТОД
    АЛГОРИТМ ВЫЧИСЛЕНИЯ НАИБОЛЬШЕГО ИЛИ НАИМЕНЬШЕГО ПО МОДУЛЮ СОБСТВЕННОГО ЗНАЧЕНИЯ МАТРИЦЫ */
}