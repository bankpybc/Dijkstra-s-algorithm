
import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.Collections;

// now we must create graph object and implement dijksta algorithm
public class Graph {

    private ArrayList<Node> nodes;
    private int noOfNodes;
    private ArrayList<Edge> edges;
    private int noOfEdges;
    String[] ans;
    int numStart = 0;
    String startVertex = "";
    StringBuilder pathAns;
    String dist = "";
    ArrayList<String> T;
    int count = 1;
    String graphUpdated = "";
    public Graph(ArrayList<Edge> edges) {
        T = new ArrayList<String>();
        pathAns = new StringBuilder();
        ans = new String[edges.size()];
        this.edges = edges;
        //create all nodes ready to be updatd with the edges
        this.noOfNodes = calculateNoOfNode(edges);
        this.nodes = new ArrayList<Node>();

        for (int n = 0; n < this.noOfNodes; n++) {
            this.nodes.add(new Node());
        }
        // add all the edges to the nodes, each edge added to two nodes(to and from)
        this.noOfEdges = edges.size();

        for (int edgeToAdd = 0; edgeToAdd < this.noOfEdges; edgeToAdd++) {
            this.nodes.get(edges.get(edgeToAdd).getFromNodeIndex()).getEdges().add(edges.get(edgeToAdd));
            this.nodes.get(edges.get(edgeToAdd).getToNodeIndex()).getEdges().add(edges.get(edgeToAdd));
        }

    }

    private int calculateNoOfNode(ArrayList<Edge> edges) {
        int noOfNodes = 0;
        for (Edge e : edges) {
            if (e.getToNodeIndex() > noOfNodes) {
                noOfNodes = e.getToNodeIndex();
            }
            if (e.getFromNodeIndex() > noOfNodes) {
                noOfNodes = e.getFromNodeIndex();
            }
        }
        noOfNodes++;
        return noOfNodes;
    }

    public void calculateShortesDistances() {
        //node 0 as source
        this.nodes.get(numStart).setDistanceFromSource(0);
        int nextNode = numStart;

        //visit every node
        for (int i = 0; i < this.nodes.size(); i++) {
            // loop around rhe edges of current node
            ArrayList<Edge> currentNodeEdges = this.nodes.get(nextNode).getEdges();
            //     System.out.println(this.nodes[nextNode]);
                    graphUpdated+="--------------------------------------\n";
                     graphUpdated+="SelectVertex : " + edges.get(nextNode).v.get(nextNode).name+"\n";
            T.remove(edges.get(nextNode).v.get(nextNode).name);
            for (int joinedEdge = 0; joinedEdge < currentNodeEdges.size(); joinedEdge++) {
                int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode);

//                System.out.println("neigh : "+edges.get(neighbourIndex).v.get(neighbourIndex).name);// print neighbour Node
                //only if not visited
                if (!this.nodes.get(neighbourIndex).isVisited()) {
                    int tentative = this.nodes.get(nextNode).getDistanceFromSource() + currentNodeEdges.get(joinedEdge).getLength();
                    //        System.out.println("Tentative : "+tentative+" nodeGetdist : "+this.nodes[nextNode].getDistanceFromSource() +"currentNodeEd : "+ currentNodeEdges.get(joinedEdge).getLength()+"VertexFrom : "+currentNodeEdges.get(joinedEdge).getNameFrom()+"VertexTo : "+currentNodeEdges.get(joinedEdge).getNameTo());
                    if (tentative < nodes.get(neighbourIndex).getDistanceFromSource()) {
                        //             System.out.println("Ten : "+tentative+" neiDistance : "+nodes.get(neighbourIndex).getDistanceFromSource());
                        nodes.get(neighbourIndex).setDistanceFromSource(tentative);
                        edges.get(neighbourIndex).setPrevDist(tentative);
                        edges.get(neighbourIndex).setPrev(edges.get(nextNode).v.get(nextNode).name);

                    }
                }
                edges.get(numStart).setPrev("null");
            }
            // all neighbours checked so node visited
            nodes.get(nextNode).setVisited(true);
            //next node must be with shortest distance
            nextNode = getNodeShortestDistanced();
            //        System.out.println(edges[nextNode].v.get(nextNode).name);
        }
    }

    private int getNodeShortestDistanced() {
        int storedNodeIndex = 0;
        int storedDist = Integer.MAX_VALUE;
        graphUpdated+="T : ";
        for (int j = 0; j < T.size(); j++) {

            graphUpdated+=T.get(j) + " ";
        }
        graphUpdated+="\n";
        graphUpdated+="Round : " + count+"\n";
        graphUpdated+="--------------------------------------\n";
        for (int i = 0; i < this.nodes.size(); i++) {
            int currentDist = this.nodes.get(i).getDistanceFromSource();
            if (!this.nodes.get(i).isVisited() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
            //  System.out.println("Out : "+edges.get(i).getPrev());
            for (int j = 0; j < T.size(); j++) {
                if (edges.get(i).getPrev().equals(T)) {
                    graphUpdated+="--------------------------------------\n";
                    T.remove(i);
                }
            }
            if(currentDist==Integer.MAX_VALUE){
                graphUpdated+="Vertex : " + edges.get(i).v.get(i).name + " Dist : Infinite" + "  Prev :" + edges.get(i).getPrev()+"\n";
            }
            else{
                graphUpdated+="Vertex : " + edges.get(i).v.get(i).name + " Dist : " + currentDist + "  Prev :" + edges.get(i).getPrev()+"\n";
            }
            
        }
        count++;
        graphUpdated+="--------------------------------------\n";
        return storedNodeIndex;
    }

    //display result
    public void printResult() {
        String output = "Number of nodes = " + this.noOfNodes;
        output += "\nNumber of edges = " + this.noOfEdges + "\n";
        for (int i = 0; i < this.nodes.size(); i++) {
            output += (edges.get(i).v.get(i).name + "  " + nodes.get(i).getDistanceFromSource() + " " + edges.get(i).getPrev() + "\n");
            ans[i] = "";
            ans[i] += edges.get(i).v.get(i).name + "," + nodes.get(i).getDistanceFromSource() + "," + edges.get(i).getPrev();
        }
    //    System.out.println(output);
    }

    public ArrayList<Node> getNode() {
        return nodes;
    }

    public int getNoOfNodes() {
        return noOfNodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public int getNoOfEdges() {
        return noOfEdges;
    }

    public void setStartVertex(String startVertex) {
        this.startVertex = startVertex;
        for (int i = 0; i < edges.get(0).v.size(); i++) {
            if (edges.get(i).v.get(i).name.equals(startVertex)) {
                this.numStart = i;
            //    System.out.println("numStart: " + numStart);
            }
        }
    }

    public void findEndLength(String endVertex) {
        int idEnd = 0;
        String next;
        String temp = "";
        temp += endVertex + ",";
        for (int i = 0; i < edges.get(0).v.size(); i++) {
            if (edges.get(i).v.get(i).name.equals(endVertex)) {
                idEnd = i;
            }
        }
        StringBuilder dist = new StringBuilder();
        dist.append(" = " + edges.get(idEnd).getPrevDist());
        for (int i = 0; i < edges.get(0).v.size(); i++) {
            next = edges.get(idEnd).getPrev();
            //   System.out.println("loop : "+i+"  next :"+ next);
            temp += next;
            if (startVertex.equals(next)) {
                break;
            }
            temp += ",";
            for (int j = 0; j < edges.get(0).v.size(); j++) {
                if (edges.get(j).v.get(j).name.equals(next)) {
                    idEnd = j;
                }
            }
        }
      //  System.out.println(temp);
        pathAns.append(temp);
        pathAns.reverse();
        this.dist = valueOf(dist);
        // pathAns.append(dist);
     //   System.out.println(pathAns);
    }

    public void setT(ArrayList<String> T) {
        this.T = T;
    }
    public void printGraphUpdate(){
        System.out.print(graphUpdated);
    }
}
