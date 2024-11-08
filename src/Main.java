import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] matriz = {
                {0, 1, 0, 0, 1, 0, 1, 0, 0, 0},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0, 1, 1, 0, 0},
                {1, 0, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1, 1, 0}
        };

        Map<Integer, List<Integer>> listaAdyacencia = matrizAListaAdyacencia(matriz);

        List<Integer> recorridoBFS = bfs(listaAdyacencia, 0);
        List<Integer> recorridoDFS = dfs(listaAdyacencia, 0);

        System.out.println("Recorrido BFS: " + recorridoBFS);
        System.out.println("Recorrido DFS: " + recorridoDFS);
    }

    public static Map<Integer, List<Integer>> matrizAListaAdyacencia(int[][] matriz) {
        Map<Integer, List<Integer>> listaAdyacencia = new HashMap<>();

        for (int i = 0; i < matriz.length; i++) {
            listaAdyacencia.put(i, new ArrayList<>());

            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    listaAdyacencia.get(i).add(j);
                }
            }
        }

        return listaAdyacencia;
    }

    public static List<Integer> bfs(Map<Integer, List<Integer>> grafo, int inicio) {
        List<Integer> recorrido = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.add(inicio);
        visitados.add(inicio);

        while (!queue.isEmpty()) {
            int nodoActual = queue.poll();
            recorrido.add(nodoActual);

            for (int vecino : grafo.getOrDefault(nodoActual, new ArrayList<>())) {
                if (!visitados.contains(vecino)) {
                    queue.add(vecino);
                    visitados.add(vecino);
                }
            }
        }

        return recorrido;
    }

    public static List<Integer> dfs(Map<Integer, List<Integer>> grafo, int inicio) {
        List<Integer> recorrido = new ArrayList<>();
        Set<Integer> visitados = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(inicio);

        while (!stack.isEmpty()) {
            int nodoActual = stack.pop();

            if (!visitados.contains(nodoActual)) {
                visitados.add(nodoActual);
                recorrido.add(nodoActual);

                List<Integer> vecinos = grafo.getOrDefault(nodoActual, new ArrayList<>());
                for (int i = vecinos.size() - 1; i >= 0; i--) {
                    int vecino = vecinos.get(i);
                    if (!visitados.contains(vecino)) {
                        stack.push(vecino);
                    }
                }
            }
        }

        return recorrido;
    }
}