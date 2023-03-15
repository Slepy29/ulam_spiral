import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Spiral extends Frame {
    private int width;
    private int height;
    private ArrayList<Integer> aList;
    public Spiral(int width, int height){
        super();
        HashMap<Integer,ArrayList<Integer>> hashMap = new PrimeNumbGen(width,height).gethMap();
        aList = new ArrayList<>();
        for (int i = 1; i <= hashMap.size(); i++) {
            aList.addAll(hashMap.get(i));
        }
        this.width = width;
        this.height = height;
        this.setVisible(true);
        this.setSize(width,height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = width/2;
        int y = height/2;
        int steps = 0;
        int direction = 0;
        int val =1;

        while(val<=(height<=width?height*height*10:width*width*10)) {
            switch (direction){
                case 0 -> { //right
                    //g.setColor(Color.BLACK);
                    steps++;
                    for (int i = 0; i < steps; i++) {
                        if (aList.contains(val)){
                            g.drawLine(x,y,x,y);
                            aList.remove(val);
                        }
                        x++;
                        val++;
                    }
                    direction++;
                }
                case 1 -> { //up
                    //g.setColor(Color.BLUE);
                    for (int i = 0; i < steps; i++) {
                        if (aList.contains(val)){
                            g.drawLine(x,y,x,y);
                            aList.remove(val);
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
                        if (aList.contains(val)){
                            g.drawLine(x,y,x,y);
                            aList.remove(val);
                        }
                        x--;
                        val++;
                    }
                    direction++;
                }
                case 3 -> { //down
                    //g.setColor(Color.YELLOW);
                    for (int i = 0; i < steps; i++) {
                        if (aList.contains(val)){
                            g.drawLine(x,y,x,y);
                            aList.remove(val);
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