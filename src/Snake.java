import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Snake {
    public ArrayList<PartsOfSnake> sTab = new ArrayList<>();

    public Snake(){
        int x = GamePanel.WIDTH / 2;
        int y = GamePanel.HEIGHT / 2;
        PartsOfSnake h = new PartsOfSnake(x, y);
        PartsOfSnake b = new PartsOfSnake(x -PartsOfSnake.size, y);
        PartsOfSnake t = new PartsOfSnake(x -(2*PartsOfSnake.size), y);
        sTab.add(h);
        sTab.add(b);
        sTab.add(t);

    }

    public void setTail()
    {
        InputStream imageStream = Main.class.getResourceAsStream("/IMAGES/snakeTail.png");
        if (imageStream == null) {
            System.err.println("No image: " + "/IMAGES/snakeTail.png");
        } else {
            try {
                sTab.getLast().img = ImageIO.read(imageStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream imageStream2 = Main.class.getResourceAsStream("/IMAGES/snakeBody.png");
        if (imageStream2 == null) {
            System.err.println("No image: " + "/IMAGES/snakeTail.png");
        } else {
            try {
                sTab.get(sTab.size()-2).img = ImageIO.read(imageStream2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setHead()
    {
        InputStream imageStream = Main.class.getResourceAsStream("/IMAGES/snakeHead.png");
        if (imageStream == null) {
            System.err.println("No image: " + "/IMAGES/snakeHead.png");
        } else {
            try {
                sTab.getFirst().img = ImageIO.read(imageStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream imageStream2 = Main.class.getResourceAsStream("/IMAGES/snakeBody.png");
        if (imageStream2 == null) {
            System.err.println("No image: " + "/IMAGES/snakeTail.png");
        } else {
            try {
                sTab.get(1).img = ImageIO.read(imageStream2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void draw(Graphics2D g2,int r)
    {
        AffineTransform transform = new AffineTransform();

        switch (r) {
            case (1) : {
                transform.translate(sTab.getFirst().pos_x, sTab.getFirst().pos_y + PartsOfSnake.size);
                transform.rotate(-Math.PI / 2);

                g2.drawImage(sTab.getFirst().img, transform, null);
            } break;
            case (2) : {
                transform.translate(sTab.getFirst().pos_x + PartsOfSnake.size, sTab.getFirst().pos_y);
                transform.rotate(Math.PI / 2);
                g2.drawImage(sTab.getFirst().img, transform, null);

            } break;
            case (4) : {
                transform.translate(sTab.getFirst().pos_x + PartsOfSnake.size, sTab.getFirst().pos_y + PartsOfSnake.size);
                transform.rotate(Math.PI );
                g2.drawImage(sTab.getFirst().img, transform, null);
            } break;
            default: sTab.getFirst().draw(g2); break;
    }

        for (int i = 1; i< sTab.size()-1; i++) {
            if(sTab.get(i-1).pos_y!=sTab.get(i).pos_y){
            AffineTransform transformB = new AffineTransform();
            transformB.translate(sTab.get(i).pos_x + PartsOfSnake.size, sTab.get(i).pos_y);
            transformB.rotate(Math.PI / 2);
            g2.drawImage(sTab.get(i).img, transformB, null);
            }
            else
                sTab.get(i).draw(g2);
        }
        AffineTransform transformT = new AffineTransform();
        if (sTab.get(sTab.size()-2).pos_x<sTab.getLast().pos_x)
        {
            transformT.translate(sTab.getLast().pos_x, sTab.getLast().pos_y);
            transformT.scale(-1, 1);
            transformT.translate(-30, 0);
            g2.drawImage(sTab.getLast().img, transformT, null);
        }
        else if(sTab.get(sTab.size()-2).pos_y>sTab.getLast().pos_y){
            transformT.translate(sTab.getLast().pos_x + PartsOfSnake.size, sTab.getLast().pos_y);
            transformT.rotate(Math.PI / 2);

            g2.drawImage(sTab.getLast().img, transformT, null);
        }
        else if(sTab.get(sTab.size()-2).pos_y<sTab.getLast().pos_y){
            transformT.translate(sTab.getLast().pos_x, sTab.getLast().pos_y + PartsOfSnake.size);
            transformT.rotate(Math.PI / 2);
            transformT.scale(-1, 1);
            transformT.translate(0, -30);
            g2.drawImage(sTab.getLast().img, transformT, null);
            }
        else
            sTab.getLast().draw(g2);
        transform.setToIdentity();
        transformT.setToIdentity();
    }



}
