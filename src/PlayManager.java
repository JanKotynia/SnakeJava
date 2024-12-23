import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class PlayManager {
    final int Widht = 750;
    final int Height = 750;
    public static int left_x= (GamePanel.WIDTH/2) - 400;
    public static int top_y = (GamePanel.HEIGHT/2) - 400;
    Food f = new Food();
    private final Snake snake = new Snake();
    public ArrayList<PartsOfSnake> sTmp = new ArrayList<>();
    public static boolean GameOver;
    public int moveD = 0;


    public PlayManager() {
        for (PartsOfSnake part : snake.sTab) {
            sTmp.add(new PartsOfSnake(part.pos_x, part.pos_y));
        }
        snake.setTail();
        snake.setHead();
    }

    public void draw2(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(15f));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawRect(left_x, top_y , Widht +15 , Height + 15 );
        Font font = new Font("Courier New", Font.BOLD, 50);
        g2.setFont(font);
        g2.drawString("SNAKE",GamePanel.WIDTH/2-600,top_y+50);
        font = new Font("Arial", Font.BOLD, 80);
        g2.setFont(font);
        g2.setColor(Color.CYAN);
        FontMetrics metrics = g2.getFontMetrics(font);
        if(GameOver) {
            int gamePositionX = (GamePanel.WIDTH - metrics.stringWidth("GAME OVER")) / 2;
            g2.drawString("GAME OVER", gamePositionX, GamePanel.HEIGHT / 2);
        }
        f.draw(g2);
        snake.draw(g2,moveD);
    }

    public void updateG()
    {
            if (KeyHandler.up) {
                moveD = 1;

                KeyHandler.up = false;
            }
            if (KeyHandler.down) {
                moveD = 2;
                KeyHandler.down = false;
            }
            if (KeyHandler.right) {
                moveD = 3;
                KeyHandler.right = false;
            }
            if (KeyHandler.left) {
                moveD = 4;
                KeyHandler.left = false;
            }

            if (moveD != 0)
                move(moveD);

            if (snake.sTab.getFirst().pos_x == f.pos_x && snake.sTab.getFirst().pos_y == f.pos_y) {
                int temp_x = snake.sTab.getLast().pos_x;
                int temp_y = snake.sTab.getLast().pos_y;

                do{
                    f.newPosition();
                }while (!checkPosition(f.pos_x,f.pos_y));

                PartsOfSnake newbody = new PartsOfSnake(temp_x,temp_y);
                snake.sTab.add(newbody);
                sTmp.add(new PartsOfSnake(temp_x, temp_y));
                snake.setTail();
            }
    }

    private boolean checkPosition(int x, int y)
    {
        for(int i=0;i<snake.sTab.size();i++)
        {
            if(snake.sTab.get(i).pos_x == x && snake.sTab.get(i).pos_y == y)
                return false;
        }
        return true;
    }

    private void move(int direction)
    {
        for (int i = 0; i < snake.sTab.size(); i++) {
            sTmp.get(i).pos_x = snake.sTab.get(i).pos_x;
            sTmp.get(i).pos_y = snake.sTab.get(i).pos_y;
        }

        switch (direction) {

            case 1 : {
            //up
                    if (snake.sTab.getFirst().pos_y - PartsOfSnake.size > top_y && checkCollision(1))
                        snake.sTab.getFirst().pos_y = snake.sTab.getFirst().pos_y - PartsOfSnake.size;
                    else
                        GameOver = true;
            } break;
            case 2 : {
            //down
                if (snake.sTab.getFirst().pos_y + PartsOfSnake.size < top_y + Height && checkCollision(2))
                    snake.sTab.getFirst().pos_y = snake.sTab.getFirst().pos_y + PartsOfSnake.size;
                else
                    GameOver=true;
            } break;
            case 3 : {
            //right
                if (snake.sTab.getFirst().pos_x + PartsOfSnake.size < left_x + Widht && checkCollision(3))
                    snake.sTab.getFirst().pos_x = snake.sTab.getFirst().pos_x + PartsOfSnake.size;
                else
                    GameOver=true;
            } break;
            case 4 : {
            //left
                if (snake.sTab.getFirst().pos_x - PartsOfSnake.size > left_x && checkCollision(4))
                    snake.sTab.getFirst().pos_x = snake.sTab.getFirst().pos_x - PartsOfSnake.size;
                else
                    GameOver=true;
            } break;

            }
            if(!GameOver) {
                for (int i = 1; i < snake.sTab.size(); i++) {
                    snake.sTab.get(i).pos_x = sTmp.get(i - 1).pos_x;
                    snake.sTab.get(i).pos_y = sTmp.get(i - 1).pos_y;
                }
            }



        }

    private boolean checkCollision(int direction) {
        for (int i = 1; i < snake.sTab.size(); i++) {
            if (snake.sTab.get(i).pos_x == snake.sTab.getFirst().pos_x && snake.sTab.get(i).pos_y == snake.sTab.getFirst().pos_y)
                return false;
        }
        return true;
    }
}
