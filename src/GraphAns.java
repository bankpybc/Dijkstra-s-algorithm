
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class GraphAns extends JFrame implements MouseListener, MouseMotionListener, KeyListener {
    Degrees degreeA = new Degrees();
    Degrees degreeB = new Degrees();
    Vector v = new Vector();
    TableDijkstra td;
    static int numIdBackup;
    static private String pathGraph = "";
    static String pathAns = "";
    public static void main(String[] args) throws FileNotFoundException {
        try {
                GraphAns gui = new GraphAns();
        } catch (Exception ex) {

        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathGraph));

        Backup backup = gson.fromJson(bufferedReader, Backup.class);
        
        Vertexs = backup.VertexsBackup;
        Edge_s = backup.Edge_sBackup;
         numIdBackup = (Vertexs.get(Vertexs.size()-1).id)+1;
       //  System.out.println("numback "+numIdBackup);
         Vertex.idGen = numIdBackup;
        //bind object reference
        for (Edge_ e : Edge_s) {
            if (e.vertexA != null) {
                int id = e.vertexA.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexA = v;
                        break;
                    }
                }
            }
            if (e.vertexB != null) {
                int id = e.vertexB.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexB = v;
                        break;
                    }
                }
            } 
        }
      
        
    }
    //Data of graph
    static ArrayList<Vertex> Vertexs = new ArrayList<>();
   static ArrayList<Edge_> Edge_s = new ArrayList<>();
    
    Object selected = null;
    TempEdge TempEdge = null; //TempEdge edge
     
    //UI 
    Canvas c;
    String mode = "Vertex"; //Vertex,Edge_
    
    //set font 
    Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 24);
    
    //find size monitor
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    
    JPanel boxOpen = new JPanel();
    
    JButton openButt = new JButton();
    JFileChooser pathSave = new JFileChooser();
    JFileChooser pathOpen = new JFileChooser();


    //-----###e
    JPanel menubar = new JPanel();
    int shift = 50;

    @Override
    public void mouseClicked(MouseEvent arg0) {
        try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
          try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
          try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
         try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
          try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
         try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
          try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
          try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
          try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
          try {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (RuntimeException e) {

        }
    }


    
    //////////////////////////////// Backup ////////////////////////////////
    class Backup {

        ArrayList<Vertex> VertexsBackup;
        ArrayList<Edge_> Edge_sBackup;

        public Backup() {
            this.VertexsBackup = Vertexs;
            this.Edge_sBackup = Edge_s;
        }

    }
    
    GraphAns() throws FileNotFoundException {
        super("GraphAns");
        // create a empty canvas 
        c = new Canvas() {
            @Override
            public void paint(Graphics g) {
            }
        };
        c.setBackground(Color.gray);

        // add mouse listener 
        c.addMouseListener(this);
        c.addMouseMotionListener(this);

        // add keyboard listener 
        c.addKeyListener(this);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        });

       
        openButt.setFont(sanSerifFont);
        
 
        
        boxOpen.setBackground(Color.white);
   
        //button
       
        openButt.setText("Click to show Graph");
        openButt.setBounds((screenSize.width - getWidth()) - 1900+ shift, 750, 300, 33);
        getContentPane().add(openButt);
        openButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    openButtAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphAns.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

      
        //-----###e     
        
        c.setBounds(0, 0, (screenSize.width - getWidth()) - 400, (screenSize.height - getHeight()));
        c.setBounds(0, 0, (screenSize.width - getWidth()) - 400, (screenSize.height - getHeight()));
        setBounds(0, 0, (screenSize.width - getWidth()), (screenSize.height - getHeight()));
        //setUndecorated(true);
        //setVisible(true);
        add(c);

        setSize(1200, 850);
        show();
    }

    //
  
    void openButtAction(ActionEvent e) throws IOException {
        
  

            draw();
        //open(pathGraph);
    }
    public void save(String path) throws IOException {
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        FileWriter writer = new FileWriter(path);
        
        Backup backup = new Backup();
        writer.write(gson.toJson(backup));
        writer.close();
    }
    public void open(String path) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        Backup backup = gson.fromJson(bufferedReader, Backup.class);
        
        Vertexs = backup.VertexsBackup;
        Edge_s = backup.Edge_sBackup;
         numIdBackup = (Vertexs.get(Vertexs.size()-1).id)+1;
       //  System.out.println("numback "+numIdBackup);
         Vertex.idGen = numIdBackup;
        //bind object reference
        for (Edge_ e : Edge_s) {
            if (e.vertexA != null) {
                int id = e.vertexA.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexA = v;
                        break;
                    }
                }
            }
            if (e.vertexB != null) {
                int id = e.vertexB.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexB = v;
                        break;
                    }
                }
            } 
        }
    }
    //set canvas is gray
    public void clear() {
        Graphics2D g = (Graphics2D)c.getGraphics();
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    public void selected(int x, int y) {
        Object obj = null;
        for (Vertex s : Vertexs) {
            if (s.inCircle(x, y)) {
                s.isSelect = true;
                obj = s;
                break;
            }
        }
        if (obj == null) {
            for (Edge_ t : Edge_s) {
                if (t.inLine(x, y)) {
                    t.isSelect = true;
                    obj = t;
                    break;
                }
            }
        }
        if (obj == null) {
            if (selected == null) {
                return;
            } else {
                if (selected instanceof Vertex) {
                    Vertex s = (Vertex) selected;
                    s.isSelect = false;
                } else {
                    Edge_ t = (Edge_) selected;
                    t.isSelect = false;
                }
                selected = null;
            }
        } else {
            if (selected == null) {
                selected = obj;
            } else {
                if (obj == selected) {
                    return;
                } else {
                    if (selected instanceof Vertex) {
                        Vertex s = (Vertex) selected;
                        s.isSelect = false;
                    } else {
                        Edge_ t = (Edge_) selected;
                        t.isSelect = false;
                    }
                    selected = obj;
                }
            }
        }
    }
    public void draw() {
        clear();
        
        Graphics2D g = (Graphics2D) c.getGraphics();
        g.setFont(sanSerifFont);
        for (Edge_ t : Edge_s) {
            t.draw(g);
          
        }
        if (TempEdge != null) {
            TempEdge.line(g);
        }
        for (Vertex s : Vertexs) {
            s.draw(g);
        }
    }
    public void setVertex(ArrayList <Vertex> Vertexs){
        for (int i = 0; i < Vertexs.size(); i++) {
            this.Vertexs.add(Vertexs.get(i));
        }
    }
    public void setEdge_(ArrayList <Edge_> Edge_s){
        for (int i = 0; i < Vertexs.size(); i++) {
            this.Edge_s.add(Edge_s.get(i));
        }
       
    }
    public void setPathGraph(String pathGraph){
        this.pathGraph = pathGraph;
        
    }
    public void setLineAns(String pathAns) throws FileNotFoundException{
        this.pathAns = pathAns;
        for (int i = 0; i < Edge_s.size(); i++) {
            Edge_s.get(i).isSelect=false;
        }
        for (int i = 0; i < Vertexs.size(); i++) {
             Vertexs.get(i).isSelect=false;
        }
        for (int i = 0; i < Edge_s.size(); i++) {
            for (int j = 0; j <pathAns.length()-2; j++) {
                if(Edge_s.get(i).vertexA.name.equals(valueOf(pathAns.charAt(j)))&&Edge_s.get(i).vertexB.name.equals(valueOf(pathAns.charAt(j+2)))){        
                   Edge_s.get(i).isSelect=true;
                }
                
                else if(Edge_s.get(i).vertexB.name.equals(valueOf(pathAns.charAt(j)))&&Edge_s.get(i).vertexA.name.equals(valueOf(pathAns.charAt(j+2)))){        
                   Edge_s.get(i).isSelect=true;
                }
                
            }
            
         //   System.out.println(":"+Edge_s.get(i).vertexA.name);
           
        }
        draw();
    }
}

