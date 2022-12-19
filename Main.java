import java.io.*;
import java.util.Random;

class GFG {
    public static int ROD_CUT_GREEDY(int n, int prices[])
    {
        // Array para armazenar o valor máximo de
        // p(i)/i para cada i
        int[] dp = new int[n + 1];

        // Iterar em todos os comprimentos possíveis da barra de ferro
        for (int i = 1; i <= n; i++) {
            // Procura o valor máximo p(j)/j para todo  j de tal forma que j <= i
            int maxValue = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                maxValue = Math.max(maxValue, prices[j-1] / j);
            }

            // Armazena o valor máximo de p(j)/j no array dp
            dp[i] = maxValue;
        }

        // Retorna o valor máximo de p(i)/i para i = n
        return dp[n];
    }

    public static int BOTTOM_UP_CUT_ROD(int p[],int n){
        int r[] = new int[n+1];

        r[0] = 0;
        for (int j = 1; j <= n; j++)
        {
            r[j] = Integer.MIN_VALUE;
            for (int i = 0; i < j; i++)
            {
                r[j] = Math.max(r[j], p[i]+r[j-i-1]);
            }
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

            for(int j = 0; j < n; j++)
                prices[j] = generator.nextInt(100);

            long startGreedy = System.nanoTime();
            int res1 = ROD_CUT_GREEDY(n, prices);
            long elapsedGreedy = System.nanoTime() - startGreedy;

            System.out.println("\n");

            System.out.println("o metodo guloso executou em " + elapsedGreedy + " ns");
            System.out.println("(GULOSO): Valor maximo obtido eh " + res1);

            System.out.println("\n");

            long startIterative = System.nanoTime();
            int res2 = BOTTOM_UP_CUT_ROD(prices, n);
            long elapsedIterative = System.nanoTime() - startIterative;
            System.out.println("o metodo iterativo executou em " + elapsedIterative + " ns");
            System.out.println("(ITERATIVO): Valor maximo obtido eh: " + res2);

        }
    }
}
