import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class GraphHandler {

    public int[][] initGraph(File file) {
        int graph[][] = null;
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            int length = sc.nextInt();
            graph = new int[length][length];

            while (sc.hasNext())
                for (int i = 0; i < length; i++)
                    for (int j = 0; j < length; j++)
                        graph[i][j] = sc.nextInt();

        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        } finally {
            if (sc != null)
                sc.close();
        }

        return graph;
    }

    public void printGraph(int[][] graph) {
        int length = graph.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(graph[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int verticesLevel(int[][] graph, int vertices) {
        int count = 0;
        int length = graph.length;

        for (int i = 0; i < length; i++) {
            if (graph[vertices][i] != 0)
                count++;
        }

        return count;
    }

    private void sortVertices(int[][] graph, int vertices[]) {
        int length = graph.length;
        for (int i = 0; i < length; i++) {
            boolean swapped = false;
            for (int j = length - 1; j > i; j--) {
                if (verticesLevel(graph, vertices[j]) >= verticesLevel(graph, vertices[j - 1])) {
                    swapped = true;
                    int tmp = vertices[j];
                    vertices[j] = vertices[j - 1];
                    vertices[j - 1] = tmp;
                }
            }
            if (!swapped)
                break;
        }
    }

    public void printColorVertices(int[][] graph) {
        int length = graph.length;
        int count = 0;
        int[] vertices = coloringGraph(graph);

        for (int i = 1; i <= length; i++) {
            System.out.print("Color " + i + ": ");
            for (int j = 0; j < length; j++) {
                if (vertices[j] == i) {
                    count++;
                    System.out.print((j + 1) + " ");
                }
            }
            System.out.println();
            if (count == length)
                break;
        }
    }

    private int[] coloringGraph(int[][] graph) {
        int length = graph.length;
        int count = 0;
        int color = 0;
        int colorVertices[] = new int[length];
        int colorVerticeTmp[] = new int[length];

        for (int i = 0; i < length; i++) {
            colorVerticeTmp[i] = i;
        }
        sortVertices(graph, colorVerticeTmp);

        while (count < length) {
            count += coloringVertices(graph, ++color, colorVerticeTmp, colorVertices);
        }
        return colorVertices;
    }

    private int coloringVertices(int[][] graph, int color, int[] colorVerticeTmp, int[] coloringVertices) {
        int count = 0;
        int length = graph.length;

        for (int i = 0; i < length; i++) {
            boolean allowColoring = true;
            int index = colorVerticeTmp[i];
            for (int j = 0; j < length; j++) {
                if (coloringVertices[index] != 0 || (graph[index][j] != 0 && coloringVertices[j] == color)) {
                    allowColoring = false;
                    break;
                }
            }
            if (allowColoring) {
                count++;
                coloringVertices[index] = color;
            }
        }

        return count;
    }

    public void printSavePath(int[] savePath, int start, int finish) {
        Stack<Integer> stack = new Stack<>();
        while (finish != start) {
            stack.add(finish);
            finish = savePath[finish];
        }
        System.out.print(start + " ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public void browseBFS(int[][] graph, int start, int finish) {
        int lenght = graph.length;
        int[] checked = new int[lenght];
        int[] savePath = new int[lenght];
        for (int i = 0; i < lenght; i++) {
            checked[i] = 0;
            savePath[i] = -1;
        }

        BFS(graph, start, checked, savePath);
        if (checked[finish] == 1) {
            System.out.println("Co duong di tu " + start + " den " + finish);
            printSavePath(savePath, start, finish);
        } else {
            System.out.println("Khong tim thay duong di tu " + start + " den " + finish);
        }
    }

    public void BFS(int[][] graph, int start, int[] checked, int[] savePath) {
        int length = graph.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        checked[start] = 1;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int i = 0; i < length; i++) {
                if (graph[v][i] != 0 && checked[i] == 0 && savePath[i] == -1) {
                    queue.add(i);
                    checked[i] = 1;
                    savePath[i] = v;
                }
            }
        }
    }
}
