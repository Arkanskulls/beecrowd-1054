
/*
 * Autor: Francisco Gabriel oliveira
 * Data: 18 de Novembro de 2023
 * Descrição: Este código resolve um problema de pulo de sapo em um rio com pedras de diferentes tamanhos.
 *  Sapo Fred deve pular de pedra em pedra para chegar à margem oposta de um rio poluído. Ele pode pousar em
 *   cada pedra pequena apenas uma vez. Qual é a distância máxima de um único salto que ele deve dar?
 */
package tinha_uma_pedra;
import java.util.Scanner;
import java.io.IOException;

//estrutura que representa uma pedra
class Main {
    static class Pedra {
        char tipo;
        int distMargem;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //n = numero de casos de teste
        int n = scanner.nextInt();
        for (int k = 0; k < n; k++) {
            int numPedras = scanner.nextInt();
            int d = scanner.nextInt();
            
            // vetor de pedras
            Pedra[] pedras = new Pedra[105];
            
         // Leitura das características das pedras
            for (int i = 1; i <= numPedras; i++) {
                String p = scanner.next();
                char tipo = p.charAt(0); // recebe a pedra como string 
                int distMargem = Integer.parseInt(p.substring(2));

                pedras[i] = new Pedra();
                pedras[i].tipo = tipo; // tipo da pedra  
                pedras[i].distMargem = distMargem; // atribui a distancia da pedra até a margem
            }

            int max_pulo = 0; // Tamanho máximo do pulo
            int dist_percorrida = 0; // Distância percorrida
            int countPequenas = -1; // Contador de pedras pequenas
            
            // Criação de pedras artificiais para representar a margem inicial e final
            pedras[0] = new Pedra();
            pedras[0].tipo = 'B'; // Pedra grande na margem inicial
            pedras[0].distMargem = 0;

            pedras[numPedras + 1] = new Pedra();
            pedras[numPedras + 1].tipo = 'B'; // Pedra grande na margem final
            pedras[numPedras + 1].distMargem = d;
            
            // percorre o vetor de pedras
            for (int i = 0; i <= numPedras + 1; i++) {
            	// Se a pedra for pequena, incrementa o contador
                if (pedras[i].tipo == 'S') countPequenas++;
                
                // Se a pedra for branca ou pequena na posição par, o sapo pula
                if (pedras[i].tipo == 'B' || (countPequenas % 2 == 0 && pedras[i].tipo == 'S')) {
                    max_pulo = Math.max(max_pulo, pedras[i].distMargem - dist_percorrida);
                    dist_percorrida = pedras[i].distMargem;
                }
            }

            countPequenas++; // incrementa o contador de pedras pequenas


            // percorre o vetor de pedras de trás para frente  
            for (int i = numPedras; i >= 0; i--) {
            	// se a pedra for pequena, decrementa o contador de pedras pequenas
                if (pedras[i].tipo == 'S') countPequenas--;
                // se a pedra for grande ou uma pedra pequena que está em uma posição ímpar, o sapo pula para a pedra  
                if (pedras[i].tipo == 'B' || (countPequenas % 2 == 1 && pedras[i].tipo == 'S')) {
                    max_pulo = Math.max(max_pulo, dist_percorrida - pedras[i].distMargem);// calcula a distancia percorrida
                    dist_percorrida = pedras[i].distMargem; // atualiza a distancia percorrida
                }
            }

            System.out.println("Case " + (k + 1) + ": " + max_pulo);// imprime o resultado
        }
    }
}