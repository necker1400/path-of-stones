/*
recusao simples
recursao memorizada
sem recursao

podem dar pulos de 1 a 3m
depois de dar pulo de 3m, nao pode dar pulo de 3m
bit 0 representa a falta de uma pedra e 1 a presenca de uma pedra
 */

import java.util.Scanner;

public class PathOfStones {

    public static void main(String[] args) {
        String caminho = args[0];
        int n = caminho.length() - 2;  // Ignora os 'm' das margens

        // Recursão Simples
        int formasSimples = contarSaltosSimples(caminho, 1, n);
        System.out.println("Recursão simples: existem " + formasSimples + " maneiras");

        // Recursão com Memorização
        int[] memo = new int[n + 1];  // Memo para recursão com memorização
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        int formasMemorizadas = contarSaltosMemorizados(caminho, 1, n, memo);
        System.out.println("Recursão memorizada: existem " + formasMemorizadas + " maneiras");

        // Sem Recursão (Iterativa)
        int formasIterativas = contarSaltosIterativo(caminho, n);
        System.out.println("Sem recursão: existem " + formasIterativas + " maneiras");
    }

    // Método recursivo simples
    public static int contarSaltosSimples(String caminho, int posicao, int n) {
        if (posicao > n) return 1;  // Chegou à outra margem
        if (caminho.charAt(posicao) == '0') return 0;  // Pedra ausente

        // Tenta saltar 1, 2 ou 3 metros
        int formas = contarSaltosSimples(caminho, posicao + 1, n)
                   + contarSaltosSimples(caminho, posicao + 2, n)
                   + contarSaltosSimples(caminho, posicao + 3, n);
        return formas;
    }

    // Método recursivo com memorização
    public static int contarSaltosMemorizados(String caminho, int posicao, int n, int[] memo) {
        if (posicao > n) return 1;  // Chegou à outra margem
        if (caminho.charAt(posicao) == '0') return 0;  // Pedra ausente
        if (memo[posicao] != -1) return memo[posicao];  // Se já foi calculado

        // Tenta saltar 1, 2 ou 3 metros
        memo[posicao] = contarSaltosMemorizados(caminho, posicao + 1, n, memo)
                      + contarSaltosMemorizados(caminho, posicao + 2, n, memo)
                      + contarSaltosMemorizados(caminho, posicao + 3, n, memo);
        return memo[posicao];
    }

    // Método iterativo (sem recursão)
    public static int contarSaltosIterativo(String caminho, int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;  // Ponto de partida (margem inicial)

        for (int i = 1; i <= n; i++) {
            if (caminho.charAt(i) == '1') {  // Só podemos pular para pedras disponíveis
                if (i - 1 >= 0) dp[i] += dp[i - 1];
                if (i - 2 >= 0) dp[i] += dp[i - 2];
                if (i - 3 >= 0) dp[i] += dp[i - 3];
            }
        }
        return dp[n];  // Retorna o número de maneiras de alcançar a última pedra (margem final)
    }
}
