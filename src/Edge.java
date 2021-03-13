
import java.util.ArrayList;
import java.util.Collections;


class Edge {
    private String nameFrom;
    private String nameTo;
    private int id;
    private int fromNodeIndex=0;
    private int toNodeIndex;
    private int length;
    ArrayList<Vertex> v;
    private boolean nameFromUsed;
    private String prev = "";
    private int prevDist;
    public Edge(int fromNodeIndex,int toNodeIndex,int length){
        this.fromNodeIndex = fromNodeIndex;
        this.toNodeIndex = toNodeIndex;
        this.length = length;
    }
    public Edge(int fromNodeIndex){
        this(fromNodeIndex,-1,-1);
    }
    public Edge(){
        this(-1,-1,-1);
    }
    public Edge(int fromNodeIndex,int toNodeIndex,int length,String nameFrom,String nameTo){
        this.fromNodeIndex = fromNodeIndex;
        this.toNodeIndex = toNodeIndex;
        this.length = length;
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
        this.v = v;
    }
    public int getFromNodeIndex(){
        return fromNodeIndex;
    }
    
    public int getToNodeIndex(){
        return toNodeIndex;
    }
    
    public int getLength(){
        return length;
    }
    
    // determines the neighbouring node of a supplied node, based an the two nodes connected by this edge   
    public int getNeighbourIndex(int nodeIndex){
        if(this.fromNodeIndex == nodeIndex){
            return this.toNodeIndex;
        }
        else{
            return this.fromNodeIndex;
        }
    }
    
    public void addFromNodeIndex(int fromNodeIndex){
        this.fromNodeIndex = fromNodeIndex;
    }
    
    public void addToNodeIndex(int toNodeIndex){
        this.toNodeIndex = toNodeIndex;
    }
    
    public void addLength(int length){
        this.length = length;
    }
    public void addName(String name){
        this.nameFrom = name;
    }
    public void addID(int id){
        this.id = id;
    }
    public String getNameFrom(){
        return this.nameFrom;
    }
    public String getNameTo(){
        return this.nameTo;
    }
    public boolean getNameFromUsed(){
        return nameFromUsed;
    }
    public void setNameFromUsed(){
        this.nameFromUsed = true;
    }
    public void setVertexInfo(ArrayList<Vertex> v){
        this.v = v;
    }
    public String getPrev(){
        return prev;
    }
    public void setPrev(String prev){
        this.prev = prev;
    }
    public int getPrevDist(){
        return this.prevDist;
    }
    public void setPrevDist(int prevDist){
        this.prevDist = prevDist;
    }
        
    
}
