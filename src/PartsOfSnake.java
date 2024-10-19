import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;

public class PartsOfSnake {
    public int pos_x,pos_y;
    public static final int size = 30;
    Image img;

    public PartsOfSnake(int x,int y){
        pos_x=x;
        pos_y=y;

        InputStream imageStream = Main.class.getResourceAsStream("/IMAGES/snakeBody.png");
        if (imageStream == null) {
            System.err.println("No image: " + "/IMAGES/snakeBody.png");
        } else {
            try {
                img = ImageIO.read(imageStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(Graphics2D g2)
    {

        if (img != null) {
            g2.drawImage(img, pos_x, pos_y, null);
        }
    }

    public void setXY(int x, int y)
    {
        pos_x=x;
        pos_y=y;
    }


}
