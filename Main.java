import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    public static List<Integer> SEARCH_GOOD_SOLUTION_AUX(List<Integer> barras, int comprimento) {
        // Ordenamos as barras em ordem decrescente de comprimento
        barras.sort((a, b) -> b - a);

        List<Integer> cortes = new ArrayList<>();
        int comprimentoRestante = comprimento;

        // Percorremos as barras
        for (int barra : barras) {
            // Verificamos se o comprimento da barra é menor ou igual ao comprimento restante
            if (barra <= comprimentoRestante) {
                // Adicionamos a barra cortada à lista de cortes
                cortes.add(barra);
                // Atualizamos o comprimento restante
                comprimentoRestante -= barra;

                // Se o comprimento restante for zero, terminamos o loop
                if (comprimentoRestante == 0) {
                    break;
                }
            }
        }
        return cortes;
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

            List<Integer> barras = new ArrayList<Integer>();
            int prices[] = new int[n];

            for(int j = 0; j < n; j++){
                int value = generator.nextInt(100);

                barras.add(value);
                prices[j] = value;
            }

            long startGreedy = System.nanoTime();
            int res1 = ROD_CUT_GREEDY(n, prices);
            List<Integer> solution = SEARCH_GOOD_SOLUTION_AUX(barras, n);
            long elapsedGreedy = System.nanoTime() - startGreedy;

            System.out.println("\n");

            System.out.println("o metodo guloso executou em " + elapsedGreedy + " ns");
            System.out.println("(GULOSO): Valor maximo obtido eh " + res1);
            System.out.println("(GULOSO): Solução eh: " + solution);

            System.out.println("\n");

            long startIterative = System.nanoTime();
            int res2 = BOTTOM_UP_CUT_ROD(prices, n);
            long elapsedIterative = System.nanoTime() - startIterative;
            System.out.println("o metodo iterativo executou em " + elapsedIterative + " ns");
            System.out.println("(ITERATIVO): Valor maximo obtido eh: " + res2);

            System.out.println("#################################################################");
            System.out.println("\n");
        }
    }
}
