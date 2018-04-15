package cp120.class2;
import edu.uweo.javaintro.tools.Turtle;

import java.awt.*;

public class DemoIDE {

    private Color color;
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public static void main(String[] args) {
        Turtle toots = new Turtle();
        toots.fillBox( 100,2000000);
        String str= "how now";

    }
}
