/*
recusao simples
recursao memorizada
sem recursao

podem dar pulos de 1 a 3m
depois de dar pulo de 3m, nao pode dar pulo de 3m
bit 0 representa a falta de uma pedra e 1 a presenca de uma pedra
 */

 public class PathOfStones {

    public static void main(String[] args) {
        String caminho = args[0];

        // Remover os 'm' das extremidades
        caminho = caminho.substring(1, caminho.length() - 1);
        int n = caminho.length();

        // Recursão Simples
        int formasSimples = contarSaltosSimples(caminho, 0, n, false);
        System.out.println("Recursão simples: existem " + formasSimples + " maneiras");

        // Recursão com Memorização
        int[] memo = new int[n];
        for (int i = 0; i < n; i++) {
            memo[i] = -1;
        }
        int formasMemorizadas = contarSaltosMemorizados(caminho, 0, n, memo, false);
        System.out.println("Recursão memorizada: existem " + formasMemorizadas + " maneiras");

        // Sem Recursão (Iterativa)
        int formasIterativas = contarSaltosIterativo(caminho, n);
        System.out.println("Sem recursão: existem " + formasIterativas + " maneiras");
    }

    // Método recursivo simples com verificação para evitar dois saltos consecutivos de 3 metros
    public static int contarSaltosSimples(String caminho, int posicao, int n, boolean pulouTres) {
        if (posicao >= n) return 1;  // Chegou à outra margem
        if (caminho.charAt(posicao) == '0') return 0;  // Pedra ausente

        // Tenta saltar 1 metro
        int formas = contarSaltosSimples(caminho, posicao + 1, n, false);

        // Tenta saltar 2 metros (se não ultrapassar o limite)
        if (posicao + 2 < n) {
            formas += contarSaltosSimples(caminho, posicao + 2, n, false);
        }

        // Tenta saltar 3 metros, mas não pode fazer dois saltos consecutivos de 3 metros
        if (!pulouTres && posicao + 3 < n) {
            formas += contarSaltosSimples(caminho, posicao + 3, n, true);
        }

        return formas;
    }

    // Método recursivo com memorização e controle de saltos de 3 metros consecutivos
    public static int contarSaltosMemorizados(String caminho, int posicao, int n, int[] memo, boolean pulouTres) {
        if (posicao >= n) return 1;  // Chegou à outra margem
        if (caminho.charAt(posicao) == '0') return 0;  // Pedra ausente
        if (memo[posicao] != -1) return memo[posicao];  // Se já foi calculado

        // Tenta saltar 1 metro
        int formas = contarSaltosMemorizados(caminho, posicao + 1, n, memo, false);

        // Tenta saltar 2 metros
        if (posicao + 2 < n) {
            formas += contarSaltosMemorizados(caminho, posicao + 2, n, memo, false);
        }

        // Tenta saltar 3 metros (sem consecutividade de 3 metros)
        if (!pulouTres && posicao + 3 < n) {
            formas += contarSaltosMemorizados(caminho, posicao + 3, n, memo, true);
        }

        memo[posicao] = formas;
        return memo[posicao];
    }

    // Método iterativo (sem recursão)
    public static int contarSaltosIterativo(String caminho, int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;  // Ponto de partida (margem inicial)

        for (int i = 0; i < n; i++) {
            if (caminho.charAt(i) == '1') {  // Só podemos pular para pedras disponíveis
                if (i + 1 <= n) dp[i + 1] += dp[i];
                if (i + 2 <= n) dp[i + 2] += dp[i];
                if (i + 3 <= n) dp[i + 3] += dp[i];
            }
        }
        return dp[n];  // Retorna o número de maneiras de alcançar a última pedra (margem final)
    }
}
