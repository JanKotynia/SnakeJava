import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Food {
    public int pos_x,pos_y;
    public static final int size = 30;
    public Image img;

    public Food()
    {
        InputStream imageStream = Main.class.getResourceAsStream("/IMAGES/Rat.png");
        if (imageStream == null) {
            System.err.println("No image: " + "/IMAGES/Rat.png");
        } else {
            try {
                img = ImageIO.read(imageStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int rand1 = new Random().nextInt(25) +1;
        int rand2 = new Random().nextInt(25) +1;
        int alignedLeftX = (PlayManager.left_x / 30) * 30;
        int alignedTopY = (PlayManager.top_y / 30) * 30;
        pos_x = alignedLeftX + rand1 * 30;
        pos_y = alignedTopY + rand2 * 30;
        System.out.println(pos_x + " " + pos_y);
    }

    public void newPosition()
    {
        int rand1 = new Random().nextInt(24) +1;
        int rand2 = new Random().nextInt(24) +1;
        int alignedLeftX = (PlayManager.left_x / 30) * 30;
        int alignedTopY = (PlayManager.top_y / 30) * 30;
        pos_x = alignedLeftX + rand1 * 30;
        pos_y = alignedTopY + rand2 * 30;
        System.out.println(pos_x + " " + pos_y);

    }
    public void draw(Graphics2D g2)
    {
        if (img != null) {
            g2.drawImage(img, pos_x, pos_y, null);
        }
    }

}
