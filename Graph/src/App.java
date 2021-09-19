import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        GraphHandler graphHandler = new GraphHandler();
        File file = FileHandler.getFile("DoThi.txt");
        int graph[][] = graphHandler.initGraph(file);

        graphHandler.printGraph(graph);
        graphHandler.printColorVertices(graph);
        graphHandler.browseBFS(graph, 2, 8);

    }

}
