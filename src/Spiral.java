import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Spiral extends Frame {

    public Spiral(int width, int height){
        super();
        this.addComponentListener(
                new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        super.componentResized(e);
                        repaint();
                    }
                }
        );
        this.setVisible(true);
        this.setSize(width,height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = getWidth();
        int height = getHeight();
        int x = width/2;
        int y = height/2;
        int steps = 0;
        int direction = 0;
        int val =1;
        boolean[] arr = new PrimeNumbGen(width,height).getPrimesArr();
        while(val<=(height<=width?height*height:width*width)) {
            switch (direction){
                case 0 -> { //right
                    //g.setColor(Color.BLACK);
                    steps++;
                    for (int i = 0; i < steps; i++) {
                        if (arr[val]){
                            g.drawLine(x,y,x,y);
                        }
                        x++;
                        val++;
                    }
                    direction++;
                }
                case 1 -> { //up
                    //g.setColor(Color.BLUE);
                    for (int i = 0; i < steps; i++) {
                        if (arr[val]){
                            g.drawLine(x,y,x,y);
                        }
                        y--;
                        val++;
                    }
                    direction++;
                }
                case 2 -> { //left
                    //g.setColor(Color.RED);
                    steps++;
                    for (int i = 0; i < steps; i++) {
                        if (arr[val]){
                            g.drawLine(x,y,x,y);
                        }
                        x--;
                        val++;
                    }
                    direction++;
                }
                case 3 -> { //down
                    //g.setColor(Color.YELLOW);
                    for (int i = 0; i < steps; i++) {
                        if (arr[val]){
                            g.drawLine(x,y,x,y);
                        }
                        y++;
                        val++;
                    }
                    direction=0;
                }
            }
        }
        g.setColor(Color.GREEN);
        g.fillRect(width/2,height/2,1,1);
    }
}