import java.util.*;

public class Sudoku
{
    public static boolean isSafe(int board[][], int row, int col, int n, int num)
    {
        //Checking if there is a row clash
        for(int c=0;c<n;c++)
        {
            if(board[row][c]==num)
            {
                return false;
            }
        }

        //Checking if there is a column clash
        for(int r=0;r<n;r++)
        {
            if(board[r][col]==num)
            {
                return false;
            }
        }

        //Checking if there is a box-clash
        int sqrt=(int)Math.sqrt(n);
        int boxRowStart=row-row%sqrt;
        int boxColStart=col-col%sqrt;
        for(int r=boxRowStart;r<boxRowStart+sqrt;r++)
        {
            for(int c=boxColStart;c<boxColStart+sqrt;c++)
            {
                if(board[row][col]==num)
                {
                    return false;
                }
            }
        }

        return true;

    }

    public static boolean solveSudoku(int board[][], int n)
    {
        int row=-1;
        int col=-1;
        boolean isEmpty=true;

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(board[i][j]==0)
                {
                    row=i;
                    col=j;
                    isEmpty=false;
                    break;
                }
            }
            if(!isEmpty)
            {
                break;
            }
        }

        if(isEmpty)
        {
            return true;                        //If there are no more 0s left in sudoku...that means sudoku is solved...hence solveSoduu() returns true
        }

        //One by one trying all values from 1 to 9 in that particular  small box where the value is 0
        for(int num=1;num<=n;num++)
        {
            if(isSafe(board,row,col,n,num))     //If the number perfectly matches row fit, column fit and box fit
            {
                board[row][col]=num;            //Then put the number there
                if(solveSudoku(board,n))        //Then do recursion to solve rest of the remaining spaces
                {
                    return true;                //If there are no empty spaces left in sudoku, then solveSudoku() returns true...that means the sudoku is solved....thus we can terminate this function and go back to main function....where we can now print the solved sudoku
                }
                else
                {
                    board[row][col]=0;
                }
            }
        }

        return false;                           //If no solution to a particular sudoku is possible then it returns false
    }

    public static void print(int board[][], int n)
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("***********************************************************************************");
        System.out.print("Enter the value of n for which you want to make a n*n sudoku: ");
        int n=sc.nextInt();
        int board[][]=new int[n][n];
        System.out.println("Start entering the values for the unsolved sudoku. Please fill 0 in place of blank positions");
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                board[i][j]=sc.nextInt();
            }
        }
        System.out.println("***********************************************************************************");
        System.out.println("The unsolved sudooku is: ");
        print(board,n);
        System.out.println("***********************************************************************************");
        if(solveSudoku(board,n))
        {
            System.out.println("The solved sudoku is:");
            print(board,n);
        }
        else
        {
            System.out.println("No solution is possible to the above sudoku");
        }
        System.out.println("***********************************************************************************");
    }


}
