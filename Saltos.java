/*
    Autor: @Ruan Necker
    Link do Repositório: https://github.com/necker1400/path-of-stones
    Como Usar

    Compilar o Programa:
        Compile o arquivo Java com o seguinte comando:

        javac Saltos.java

    Executar o Programa:
        (execute o programa passando dois números binários como argumentos pela linha de comando)

        java Saltos m1111m


    O programa retornará o número de maneiras de cruzar o rio, seguindo as regras do problema.

        Resultado esperado: Existem 13 maneiras de cruzar o rio.
    */
    
import java.util.HashMap;
import java.util.Map;

public class Saltos {

    // Mapa para memorização na versão com memorização
    static Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        // Verifica se o usuário passou um argumento pela linha de comando
        if (args.length == 0) {
            System.out.println("Por favor, forneça uma string representando o caminho de pedras.");
            return;
        }

        // Recebe a string de caminho como argumento do terminal
        String caminho = args[0];

        // Converte a string para um array de pedras, onde 1 representa uma pedra e 0 um buraco
        // Removemos as margens (primeiro e último caracteres 'm')
        int[] pedras = new int[caminho.length() - 2];

        for (int i = 1; i < caminho.length() - 1; i++) {
            pedras[i - 1] = caminho.charAt(i) - '0'; // Converte '1' ou '0' para inteiro
        }

        // Versão recursiva simples
        int maneirasRecursivaSimples = contarManeirasRecursivaSimples(0, pedras, false);
        System.out.println("Versão Recursiva Simples: Existem " + maneirasRecursivaSimples + " maneiras de cruzar o rio.");

        // Versão recursiva com memorização
        memo.clear(); // Limpa a memorização para garantir resultado correto
        int maneirasMemorizada = contarManeiras(0, pedras, false);
        System.out.println("Versão Recursiva com Memorização: Existem " + maneirasMemorizada + " maneiras de cruzar o rio.");

        // Versão não-recursiva (iterativa)
        int maneirasIterativa = contarManeirasIterativa(pedras);
        System.out.println("Versão Iterativa: Existem " + maneirasIterativa + " maneiras de cruzar o rio.");
    }

    // ============================
    // 1. Versão Recursiva Simples
    // ============================

    public static int contarManeirasRecursivaSimples(int posicao, int[] pedras, boolean ultimoFoi3m) {
        // Caso base: Se chegamos ao final das pedras (última pedra), há apenas 1 maneira de chegar à margem
        if (posicao >= pedras.length) {
            return 1;
        }

        // Se a posição atual não tem uma pedra (é um buraco), não há maneiras de continuar
        if (pedras[posicao] == 0) {
            return 0;
        }

        // Caso recursivo: Tentar pular 1, 2 ou 3 metros, se as pedras existirem
        int totalManeiras = 0;

        // Pulo de 1 metro
        totalManeiras += contarManeirasRecursivaSimples(posicao + 1, pedras, false);

        // Pulo de 2 metros
        if (posicao + 2 <= pedras.length) {
            totalManeiras += contarManeirasRecursivaSimples(posicao + 2, pedras, false);
        }

        // Pulo de 3 metros, apenas se o último pulo não foi de 3 metros
        if (!ultimoFoi3m && posicao + 3 <= pedras.length) {
            totalManeiras += contarManeirasRecursivaSimples(posicao + 3, pedras, true);
        }

        return totalManeiras;
    }

    // ============================
    // 2. Versão Recursiva com Memorização (já implementada)
    // ============================

    public static int contarManeiras(int posicao, int[] pedras, boolean ultimoFoi3m) {
        // Caso base: Se chegamos ao final das pedras (última pedra), há apenas 1 maneira de chegar à margem
        if (posicao >= pedras.length) {
            return 1;
        }

        // Se a posição atual não tem uma pedra (é um buraco), não há maneiras de continuar
        if (pedras[posicao] == 0) {
            return 0;
        }

        // Chave de memorização incluindo a posição e o estado `ultimoFoi3m`
        String chaveMemo = posicao + "," + ultimoFoi3m;

        // Verifica se o resultado já foi calculado e está memorizado
        if (memo.containsKey(chaveMemo)) {
            return memo.get(chaveMemo);
        }

        // Caso recursivo: Tentar pular 1, 2 ou 3 metros, se as pedras existirem
        int totalManeiras = 0;

        // Pulo de 1 metro
        totalManeiras += contarManeiras(posicao + 1, pedras, false);

        // Pulo de 2 metros
        if (posicao + 2 <= pedras.length) {
            totalManeiras += contarManeiras(posicao + 2, pedras, false);
        }

        // Pulo de 3 metros, apenas se o último pulo não foi de 3 metros
        if (!ultimoFoi3m && posicao + 3 <= pedras.length) {
            totalManeiras += contarManeiras(posicao + 3, pedras, true);
        }

        // Memoriza o resultado para evitar recomputação
        memo.put(chaveMemo, totalManeiras);

        return totalManeiras;
    }

    // ============================
    // 3. Versão Não-Recursiva (Iterativa)
    // ============================

    public static int contarManeirasIterativa(int[] pedras) {
        // Tamanho do array de pedras
        int n = pedras.length;
    
        // Caso base: Se não houver pedras, há 1 maneira de cruzar
        if (n == 0) {
            return 1;
        }
    
        // Array para armazenar o número de maneiras de chegar a cada posição
        int[] maneiras = new int[n + 1];
    
        // No início, há 1 maneira de estar na posição 0 (antes da primeira pedra)
        maneiras[0] = 1;
    
        // Variável que rastreia se o último salto foi de 3 metros
        boolean ultimoFoi3m = false;
    
        // Iteramos por todas as pedras
        for (int posicao = 0; posicao < n; posicao++) {
            // Se a posição atual for um buraco (0), continue
            if (pedras[posicao] == 0) {
                System.out.println("Posição " + posicao + " é um buraco. Continuando.");
                continue;
            }
    
            // Pulo de 1 metro
            if (posicao + 1 <= n) {
                maneiras[posicao + 1] += maneiras[posicao];
                System.out.println("Pulando 1 metro da posição " + posicao + " para " + (posicao + 1));
            }
    
            // Pulo de 2 metros
            if (posicao + 2 <= n) {
                maneiras[posicao + 2] += maneiras[posicao];
                System.out.println("Pulando 2 metros da posição " + posicao + " para " + (posicao + 2));
            }
    
            // Pulo de 3 metros, apenas se o último salto não foi de 3 metros
            if (!ultimoFoi3m && posicao + 3 <= n) {
                maneiras[posicao + 3] += maneiras[posicao];
                System.out.println("Pulando 3 metros da posição " + posicao + " para " + (posicao + 3));
                ultimoFoi3m = true;  // Atualizamos para indicar que o último salto foi de 3 metros
            } else {
                ultimoFoi3m = false; // Resetamos caso o salto não seja de 3 metros
            }
        }
    
        // O número de maneiras de cruzar o rio é armazenado na última posição
        System.out.println("Número total de maneiras de cruzar o rio: " + maneiras[n]);
        return maneiras[n];
    }    
}
