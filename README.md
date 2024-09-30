# Saltos: Soluções Recursivas e Iterativas para Cruzar o Rio

Este projeto implementa um algoritmo que calcula quantas maneiras diferentes existem para cruzar um rio, dado um caminho de pedras representado por uma string. O problema envolve decidir como se mover entre as pedras e considera que algumas pedras não podem ser pisadas.

O código contém três implementações principais:
1. **Versão Recursiva Simples**
2. **Versão Recursiva com Memoization**
3. **Versão Iterativa**

## Estrutura do Projeto

### Arquivo principal:
- `Saltos.java`: Contém as três abordagens para resolver o problema de quantas maneiras existem para cruzar o rio.

### Requisitos
- Java JDK 8+ (ou qualquer versão mais recente).
- Uma string de entrada que representa o caminho de pedras.

## Executando o Programa

Para executar o programa, compile e rode o arquivo `Saltos.java`. Ao rodar o programa, é necessário passar uma string como argumento de linha de comando. Essa string deve seguir o formato:

- O primeiro e o último caracteres são irrelevantes.
- Os caracteres entre o primeiro e o último representam as pedras. Um `1` indica que a pedra é válida para pisar, e um `0` indica que a pedra não pode ser usada.

### Exemplo de Execução

```bash
java Saltos "0101010"
