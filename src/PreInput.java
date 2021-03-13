
import java.util.ArrayList;
import java.util.Collections;


public class PreInput {
    //int fromNodeIndex,int toNodeIndex,int length
    int fromNodeIndex[];
    int toNodeIndex[];
    int length[];
    String[] nameFrom;
    String[] nameTo;
    ArrayList <Edge_> Edge_s;
    PreInput(ArrayList <Edge_> Edge_s){
       fromNodeIndex= new int[Edge_s.size()];
       toNodeIndex = new int [Edge_s.size()];
       length = new int [Edge_s.size()];
       nameFrom = new String [Edge_s.size()];
       nameTo = new String [Edge_s.size()];
       for(int i=0;i<Edge_s.size();i++){
        this.fromNodeIndex[i]= Edge_s.get(i).vertexA.id;
        this.toNodeIndex[i] = Edge_s.get(i).vertexB.id;
        this.length[i] = Integer.valueOf(Edge_s.get(i).weight);
        this.nameFrom[i] = Edge_s.get(i).vertexA.name;
        this.nameTo[i] = Edge_s.get(i).vertexB.name;
          // System.out.println("From:"+fromNodeIndex[i]+"To"+toNodeIndex[i]+"length:"+length[i]);
       } 
    }
    public void setPreInput(ArrayList <Edge_> Edge_s){
        this.Edge_s = Edge_s;
    }
    public void runPreInput(){
        fromNodeIndex= new int[Edge_s.size()];
         toNodeIndex = new int [Edge_s.size()];
          length = new int [Edge_s.size()];
       for(int i=0;i<Edge_s.size();i++){
        this.fromNodeIndex[i]= Edge_s.get(i).vertexA.id;
        this.toNodeIndex[i] = Edge_s.get(i).vertexB.id;
        this.fromNodeIndex[i] = Integer.valueOf(Edge_s.get(i).weight);
       } 
    }

}
