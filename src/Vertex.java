
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;
/**
 *
 * @author vento
 */
public class Vertex {
    int x;
    int y;
    String name = "A";
    int r;
    int shift ;
    boolean isSelect;
    int degree;
    int id;
    static int idGen = 0;
    char namePrint;
    Vertex(int x, int y) {
        this.id = idGen;
        idGen++;
        this.r = 36;
        this.shift = 30;
        this.x = x;
        this.y = y;
        namePrint= (char) ((int)name.charAt(0)+id);
        this.name = Character.toString(namePrint);
        this.isSelect = false;
    }

    boolean inCircle(int x0, int y0) {
        return ((x0 - x) * (x0 - x) + (y0 - y) * (y0 - y)) <= r * r;
    }

    void draw(Graphics2D g) {
        g.setColor(isSelect ? Color.orange : Color.BLACK);
        g.setStroke(new BasicStroke(2));

        g.fillOval(x - r, y - r, r * 2, r * 2);

        g.setColor(Color.WHITE);
        g.fillOval(x - r + (r - shift) / 2, y - r + (r - shift) / 2, r * 2 - (r - shift), r * 2 - (r - shift));

        g.setColor(isSelect ? Color.orange : Color.BLACK);
        g.drawString(name, x - 10, y + 10);
        g.drawString("Degree:"+Integer.toString(degree),x - 35,y - 45);
        //g.drawString(Integer.toString(id),x,y);
    }
    

}

