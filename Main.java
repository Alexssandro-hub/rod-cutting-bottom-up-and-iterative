import java.io.*;
import java.util.Random;

class GFG {
    public static int cutRod(int prices[], int n)
    {
        int mat[][] = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    mat[i][j] = 0;
                }
                else {
                    if (i == 1) {
                        mat[i][j] = j * prices[i - 1];
                    }
                    else {
                        if (i > j)
                            mat[i][j] = mat[i - 1][j];
                        else
                            mat[i][j] = Math.max(prices[i - 1] + mat[i][j - i], mat[i - 1][j]);
                    }
                }
            }
        }

        return mat[n][n];
    }

    public static int BOTTOM_UP_CUT_ROD(int p[],int n){
        int r[] = new int[n+1];
        int q;
        r[0] = 0;
        for (int j = 1; j <= n; j++)
        {
            q = Integer.MIN_VALUE;
            for (int i = 0; i < j; i++)
            {
                q = Math.max(q, p[i]+r[j-i-1]);
            }
            r[j] = q;
        }
        return r[n];
    }
    public static void main(String[] args)
    {
        int brk = 10;
        Random generator = new Random();

        for(int i = 0; i < brk; i++){
            int n = generator.nextInt((20-10) + 1) + 10;

            System.out.println("Tamanho da Entrada eh: " + n);

            int prices[] = new int[n];

            for(int j = 0; j < n; j++) prices[j] = generator.nextInt(100);

            long startIterative = System.nanoTime();
            int res1 = cutRod(prices, n);
            long elapsedIterative = System.nanoTime() - startIterative;
            System.out.println("o metodo iterativo executou em " + elapsedIterative);
            System.out.println("(ITERATIVO): Valor maximo obtido eh " + res1);

            System.out.println("#################################################################");

            long startGreedy = System.nanoTime();
            int res2 = BOTTOM_UP_CUT_ROD(prices, n);
            long elapsedGreedy = System.nanoTime() - startGreedy;
            System.out.println("o metodo guloso executou em " + elapsedGreedy);
            System.out.println("(GULOSO): Valor maximo obtido eh: " + res2);

            System.out.println("#################################################################");
        }
    }
}