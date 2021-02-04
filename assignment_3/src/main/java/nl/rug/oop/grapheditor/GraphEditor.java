package nl.rug.oop.grapheditor;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.SaveAndLoad;
import nl.rug.oop.grapheditor.view.GraphFrame;

public class GraphEditor {

    public static void main(String[] args) {
        GraphModel graphModel = new GraphModel();
        if (args.length > 0) {
            /* Used for loading the file from a path given in terminal*/
            SaveAndLoad.loadGraph(graphModel, args[0]);
        }
        new GraphFrame(graphModel);
    }
}

