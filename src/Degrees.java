
/**
 *
 * @author Blank
 */
    public class Degrees{
      private Vertex v = null;
      public Degrees(){
          this.v = null;
      }
      public Degrees(Vertex v){
          this.v = v;
      }
      public void setVertex(Vertex v){
          this.v = v;
      }
      public void addDegree(Vertex v){
        v.degree++;
      }
      public void addDegree(){
          this.v.degree++;
      }
      public void delDegree(Vertex v){
        if(v.degree>0){
            v.degree--;
        }
      }
      public void delDegree(){
          if(this.v.degree>0){
            this.v.degree--;
        }
      }
      public String getVertexName(){
          return this.v.name;
      }
    }
