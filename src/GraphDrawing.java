
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
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vento
 */
public class GraphDrawing extends JFrame implements MouseListener, MouseMotionListener, KeyListener {
    
    Degrees degreeA = new Degrees();
    Degrees degreeB = new Degrees();
    Vector v = new Vector();
    TableDijkstra td;
    int numIdBackup;
    boolean checkSaved = false;
    String pathGraph = "";
    ArrayList <String> T = new ArrayList<String>();
    public static void main(String[] args) {
        try {
            GraphDrawing gui = new GraphDrawing();
        } catch (Exception ex) {

        }
    }
    //Data of graph
    ArrayList<Vertex> Vertexs = new ArrayList<>();
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

    JFrame frameHelp = new JFrame("Help");
    JFrame frameDijkstra = new JFrame("Dijkstra");

    JPanel boxSave = new JPanel();
    JPanel boxOpen = new JPanel();
    JPanel boxHelp = new JPanel();
    JPanel boxDijkstra = new JPanel();

    JButton saveButt = new JButton();
    JButton openButt = new JButton();
    JButton helpButt = new JButton();
    JButton DijkstraButt = new JButton();

    JFileChooser pathSave = new JFileChooser();
    JFileChooser pathOpen = new JFileChooser();

    JLabel helpString = new JLabel();

    //-----###e
    JPanel menubar = new JPanel();
    int shift = 50;

    //////////////////////////////// Backup ////////////////////////////////
    class Backup {

        ArrayList<Vertex> VertexsBackup;
        ArrayList<Edge_> Edge_sBackup;

        public Backup() {
            this.VertexsBackup = Vertexs;
            this.Edge_sBackup = Edge_s;
        }

    }

    GraphDrawing() {
        super("GraphDrawing");
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
                System.exit(0);
            }
        });

        saveButt.setFont(sanSerifFont);
        openButt.setFont(sanSerifFont);
        helpButt.setFont(sanSerifFont);
        DijkstraButt.setFont(sanSerifFont);

        helpString.setFont(sanSerifFont);

        boxSave.setBackground(Color.white);
        boxOpen.setBackground(Color.white);

        boxHelp.setBackground(Color.white);
        boxDijkstra.setBackground(Color.white);

        frameHelp.add(boxHelp);

        //button
        saveButt.setText("Save");
        saveButt.setBounds((screenSize.width - getWidth()) - 400 + shift, 150, 300, 33);
        getContentPane().add(saveButt);
        saveButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    saveButtAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        openButt.setText("Open");
        openButt.setBounds((screenSize.width - getWidth()) - 400 + shift, 100, 300, 33);
        getContentPane().add(openButt);
        openButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    T.clear();
                    openButtAction(e);
                    for (int i = 0; i < Vertexs.size(); i++) {
                        T.add(Vertexs.get(i).name);
                     //   System.out.println(T.get(i)+" name add after open");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        helpButt.setText("Help");
        helpButt.setBounds((screenSize.width - getWidth()) - 400 + shift, 50, 300, 33);
        getContentPane().add(helpButt);
        helpButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                helpButtAction(e);
            }
        });

        DijkstraButt.setText("Dijkstra's Algorithm");
        DijkstraButt.setBounds((screenSize.width - getWidth()) - 400 + shift, 200, 300, 33);
        getContentPane().add(DijkstraButt);
        DijkstraButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
          //    System.out.println(checkSaved);
                if (!checkSaved) {
                    try {
                        saveButtAction(e);
                    } catch (IOException ex) {
                        Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                if(checkSaved){
                td = new TableDijkstra();
                td.setEdge(Edge_s);
                td.setVertexInfo(Vertexs);
                td.setPathGraph(pathGraph);
                td.setT(T);
                }
            }
        });
        //-----###e     
        menubar.setBackground(Color.darkGray);
        menubar.setBounds((screenSize.width - getWidth()) - 400, 0, 400, (screenSize.height - getHeight()));
        c.setBounds(0, 0, (screenSize.width - getWidth()) - 400, (screenSize.height - getHeight()));
        c.setBounds(0, 0, (screenSize.width - getWidth()) - 400, (screenSize.height - getHeight()));
        setBounds(0, 0, (screenSize.width - getWidth()), (screenSize.height - getHeight()));
        //setUndecorated(true);
        //setVisible(true);
        add(c);
        add(menubar);
        // setSize(1500, 1000);
        show();
    }

    //
    void helpButtAction(ActionEvent e) {
        String help = "<html>";
        help += "Double click for create Vertex<br>";
        help += "Click on Vertex then type for rename<br>";
        help += "Click on Vertex or center of edge then it is orange you can edit etc move , rename , delete <br>";
        help += "Click on Vertex then press delete for remove Vertex<br>";
        help += "Press and hold spacebar with drag mouse for create edge<br>";
        help += "Click on character on edge then type for rename<br>";
        help += "Click on character on edge then drag mouse for move edge<br>";
        help += "Click on character on edge then press delete for remove edge<br>";
        help += "Press Button save for save Graph on canvas to json file<br>";
        help += "Press Button open for open Graph json file to canvas<br>";
        help += "Dijkstra's algorithm have to save before working or edit the graph every time<br>";
        help += "Press Spacebar to show Graphanswer<br>";
        helpString.setText(help);
        boxHelp.add(helpString);
        boxHelp.setAutoscrolls(true);

        frameHelp.setBounds(screenSize.width / 2 - 500, screenSize.height / 2 - 200, 1000, 450);
        frameHelp.setVisible(true);
    }

    void saveButtAction(ActionEvent e) throws IOException {
        pathSave.setBounds(60, 120, 750, 450);
        boxSave.add(pathSave);

        int ret = pathSave.showDialog(null, "save");
        String path ;
        
        if (ret == JFileChooser.APPROVE_OPTION) {
            File filePath = pathSave.getSelectedFile();
            path = filePath.getPath();
            if(path.length()<=0){
                saveButtAction(e);
            }
            else{
                checkSaved = true;
            }
            pathGraph = path;
         
            save(path);
        }
    }

    void openButtAction(ActionEvent e) throws IOException {
        checkSaved = false;
        pathOpen.setBounds(60, 120, 750, 450);
        boxOpen.add(pathOpen);

        int ret = pathOpen.showDialog(null, "open");
        String path = "";

        if (ret == JFileChooser.APPROVE_OPTION) {
            File filePath = pathOpen.getSelectedFile();
            path = filePath.getPath();
            pathGraph = path;
            open(path);
            draw();
        }
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
        numIdBackup = (Vertexs.get(Vertexs.size() - 1).id) + 1;
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
        Graphics2D g = (Graphics2D) c.getGraphics();
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
        checkSaved = false;
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

////////////////////////////////  UI EVENT  ////////////////////////////////
    // 3.1 mouse listener and mouse motion listener mehods 
    // keyboard listener and keyboard motion listener mehods 
    @Override
    public void keyTyped(KeyEvent ke) {
        // System.out.println("key " + ke.getKeyChar() + " = " + (int) ke.getKeyChar());
        if ((int) ke.getKeyChar() == 19) {
            try {
                //ctrl + S
                save("backup.json");
            } catch (IOException ex) {

            }
        } else if ((int) ke.getKeyChar() == 15) {
            try {
                //ctrl + O 
                open("backup.json");
            } catch (IOException ex) {

            }
        } else if ((int) ke.getKeyChar() == 14) {
            //ctrl + N 

        } else if ((int) ke.getKeyChar() == 12) {
            //ctrl + L

        } else if ((int) ke.getKeyChar() == 18) {
            //ctrl + R 

        } else if ((int) ke.getKeyChar() == 9) {

        } else if ((int) ke.getKeyChar() == 1) {
            //ctrl + A 

        }

        if (selected instanceof Vertex) {
            Vertex s = (Vertex) selected;
     //       System.out.println("Vertex:" + s.id);
            int status = (int) ke.getKeyChar();
            if (status == 8) { //delete
                if (s.name.length() > 1) {
                    s.name = s.name.substring(0, s.name.length() - 1).trim();
                } else {
                    s.name = "".trim();
                }
                T.set(s.id,s.name);
         //       System.out.println(T.get(s.id));
            } else if (status == 127) { // space
                ArrayList<Edge_> TempEdge = new ArrayList<>();
                for (Edge_ t : Edge_s) {
                    if (t.vertexA == selected || t.vertexB == selected) {
                        TempEdge.add(t);
                    }
                 
                }
                
                for (Edge_ t : TempEdge) {
                     degreeA.setVertex(t.vertexA);
                     degreeB.setVertex(t.vertexB);
                    degreeA.delDegree();
                    degreeB.delDegree();
                    Edge_s.remove(t);
                }
                for (int i = 0; i < Vertexs.size(); i++) {
                    if(Vertexs.get(i).id>s.id){
                        Vertexs.get(i).id--;
          //              System.out.println(Vertexs.get(i).name);
          //              System.out.println(Vertexs.get(i).id);
        //                System.out.println("idGen "+Vertex.idGen);
                    }
                }
                Vertex.idGen=Vertexs.size()-1;
                Vertexs.remove(selected);
                selected = null;

            } else {
                s.name += ke.getKeyChar();
                s.name = s.name.trim();
            }

        } else if (selected instanceof Edge_) {
            Edge_ t = (Edge_) selected;

            /* for(int i=0;i<Edge_s.size();i++){
                System.out.print("Vertex:"+Edge_s.get(i).vertexA.name+" to Vertex:"+Edge_s.get(i).vertexB.name+" length:");
                System.out.println(Edge_s.get(i).weight);
            }*/
     //       System.out.println("Edge:" + t.id);
            int status = (int) ke.getKeyChar();
            int c = 0;
            if (status == 8) { //backspace
                if (t.weight.length() > 1) {
                    t.weight = t.weight.substring(0, t.weight.length() - 1).trim();
                } else {
                    t.weight = "".trim();
                }
                c++;
            } else if (status == 127) {
                Edge_ a = (Edge_) selected;
                degreeA.setVertex(a.vertexA);
                degreeB.setVertex(a.vertexB);
                degreeA.delDegree();
                degreeB.delDegree();
                 
                Edge_s.remove(selected);
                selected = null;

            } else {
                if (ke.getKeyChar() == ' ') {
                    return;
                }
                t.weight += ke.getKeyChar();
                t.weight = t.weight.trim();
            }

        }

        draw();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if ((int) ke.getKeyChar() == 32) {
            //press space bar
            mode = "Edge_";
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //release space bar
        mode = "Vertex";
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        selected(x, y);
        if (e.getClickCount() == 2 && !e.isConsumed()) {
            e.consume();
            if (!Vertexs.contains(selected)) {
                Vertex TempVertex = new Vertex(x, y);
                Vertexs.add(TempVertex);
                T.add(TempVertex.name);
                System.out.println(T.get(TempVertex.id));
            }
        }
        draw();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mode.equals("Vertex")) {
            if (selected != null) {
                if (selected instanceof Vertex) {
                    Vertex s = (Vertex) selected;
                    for (Edge_ t : Edge_s) {
                        if (t.vertexA == s || t.vertexB == s) {
                            int difx = x - s.x;
                            int dify = y - s.y;
                            if (t.vertexA != t.vertexB) {
                                if (t.vertexA != null) {
                                    t.x_center = (t.vertexA.x + t.vertexB.x) / 2;
                                    t.y_center = (t.vertexA.y + t.vertexB.y) / 2;
                                }
                            } else {
                                t.x_center += difx;
                                t.y_center += dify;
                            }

                        }
                    }
                    s.x = x;
                    s.y = y;
                } else {
                    Edge_ t = (Edge_) selected;
                    t.x_center = x;
                    t.y_center = y;
                }
            }

        } else if (mode.equals("Edge_")) {
            try {
                Vertex Vertex = null;
                for (Vertex s : Vertexs) {
                    if (s.inCircle(x, y)) {
                        Vertex = s;
                    }
                }
                if (Vertex != null) {
                    if (Vertex != TempEdge.vertexA) {
                        double angle = Math.atan2(TempEdge.vertexA.y - Vertex.y, TempEdge.vertexA.x - Vertex.x);
                        double dx = Math.cos(angle);
                        double dy = Math.sin(angle);
                        TempEdge.x1 = Vertex.x + (int) (Vertex.r * dx);
                        TempEdge.y1 = Vertex.y + (int) (Vertex.r * dy);
                    } else {
                        TempEdge.x1 = x;
                        TempEdge.y1 = y;
                    }
                } else {
                    TempEdge.x1 = x;
                    TempEdge.y1 = y;
                }
            } catch (Exception ex) {

            }
        }
        draw();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mode.equals("Vertex")) {
            TempEdge = null;
        } else if (mode.equals("Edge_")) {
            try {
                TempEdge.x1 = x;
                TempEdge.y1 = y;
                Vertex vertexB = null;
                for (Vertex s : Vertexs) {
                    if (s.inCircle(x, y)) {
                        TempEdge.x1 = s.x;
                        TempEdge.y1 = s.y;
                        vertexB = s;
                        Edge_ edge = new Edge_(TempEdge.vertexA, vertexB);
                        degreeA.setVertex(TempEdge.vertexA);
                        degreeB.setVertex(vertexB);
                        if (s != TempEdge.vertexA) {
                            edge.x_center = (TempEdge.vertexA.x + s.x) / 2;
                            edge.y_center = (TempEdge.vertexA.y + s.y) / 2;
                        } else {
                            double angle = Math.atan2(y - TempEdge.vertexA.y, x - TempEdge.vertexA.x);
                            double dx = Math.cos(angle);
                            double dy = Math.sin(angle);

                            int rc = 3 * TempEdge.vertexA.r;

                            edge.x_center = TempEdge.vertexA.x + (int) (dx * rc);
                            edge.y_center = TempEdge.vertexA.y + (int) (dy * rc);

                        }
                        degreeA.addDegree();
                        degreeB.addDegree();
                        v.add(degreeA.getVertexName());
                        v.add(degreeB.getVertexName());
                        Edge_s.add(edge);
                        break;
                    }
                }
                TempEdge = null;
            } catch (Exception ex) {
            }
        }
        draw();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mode.equals("Edge_")) {
            TempEdge = new TempEdge(x, y);
            for (Vertex s : Vertexs) {
                if (s.inCircle(x, y)) {
                    TempEdge.setA(s);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

}
