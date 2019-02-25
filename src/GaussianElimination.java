
import java.util.Arrays;


public class GaussianElimination
{
    private final double[][] matrix;
    private int x, y, z;
    private final int row, col;
    
    public static void main(String[] args)
    {
        int[][] matrix = {{2, -4, 1, 6},
                          {3, -1, 1, 11},
                          {1, 1, -1, -3}};
        
        GaussianElimination object = new GaussianElimination(matrix);
        
        System.out.println("X: " + object.getX());
        System.out.println("Y: " + object.getY());
        System.out.println("Z: " + object.getZ());
        
        int[] temp;
        
        for (int i = 0; i < object.row; i++)
        {
            System.out.println(Arrays.toString(object.getMatrix()[i]));
        }
    }
    
    public GaussianElimination(int[][] matrix)
    {
        row = matrix.length;
        col = matrix[0].length;
        
        this.matrix = new double[row][col];
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        
        arrange();
        compute();
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getZ()
    {
        return z;
    }
    
    public int[][] getMatrix()
    {
        int[][] matrix = new int[row][col];
        
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                matrix[i][j] = (int) this.matrix[i][j];
            }
        }
        
        return matrix;
    }
    
    private void compute()
    {
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < i; j++)
            {
                if (matrix[i][j] != 0)
                {
                    reduce(j, i);
                }
            }
        }
        
        this.z = (int)(matrix[2][3] / matrix[2][2]);
        this.y = (int)((matrix[1][3] - (matrix[1][2]) * this.z) / matrix[1][1]);
        this.x = (int)((matrix[0][3] - (matrix[0][2] * this.z) - (matrix[0][1] * this.y)) / matrix[0][0]);
    }
    
    private void reduce(int reference, int modified)
    {
        double multiplicand = (-matrix[modified][reference]) / matrix[reference][reference];
        
        for (int i = 0; i < matrix[0].length; i++)
        {
            matrix[modified][i] += matrix[reference][i] * multiplicand;
        }
    }
    
    private void arrange()
    {
        // Set first encountered row with first col value of 1 as first row
        for (int i = 0; i < row; i++)
        {
            if (matrix[i][0] == 1)
            {
                swap(0, i);
                break;
            }
        }
        
        // Sort rows based on number of leading zeroes
        for (int i = 1; i < row; i++)
        {
            int iCtr = 0;
            
            for (int j = 0; j < col; j++)
            {
                if (matrix[i][j] != 0)
                {
                    break;
                }
                else
                {
                    ++iCtr;
                }
            }
            
            for (int j = i; j < row; j++)
            {
                int jCtr = 0;
                
                for (int k = 0; k < col; k++)
                {
                    if (matrix[j][k] != 0)
                    {
                        break;
                    }
                    else
                    {
                        ++jCtr;
                    }
                }
                
                if (jCtr < iCtr)
                {
                    swap(i, j);
                }
            }
        }
        
        // If first row first col value is not 1, divide entire row by its value
        if (matrix[0][0] != 1)
        {
            for (int i = 0; i < col; i++)
            {
                matrix[0][i] /= matrix[0][0];
            }
        }
    }
    
    private void swap(int arg0, int arg1)
    {
        double[] temp = matrix[arg0];
        matrix[arg0] = matrix[arg1];
        matrix[arg1] = temp;
    }
}
