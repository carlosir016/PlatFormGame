package Core;

import Entity.Player;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {


    final int pixelOriginal = 32; //32x32 pixels
    final int scale = 2;


    public int getPixel() {
        return pixel;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    final int pixel =  pixelOriginal * scale; //64*64 pixels
    final int maxColumns = 16;
    final int maxRows = 12;
    final int screenWidth = pixel * maxColumns; //768 pixels
    final int screenHeight = pixel * maxRows; //576 pixels

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWith = pixel * maxWorldCol;
    public final int worldHeight = pixel * maxWorldRow;


    final int FPS = 60;

   //TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    //public CollisionChecker collisionChecker = new CollisionChecker(this);
    private Player player = new Player(this,keyHandler);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }


    public void starGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000.00/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >=1) {
                update();
                repaint();
                delta--;
                drawCount++;

            }
            if (timer >= 1000000000.00){
                System.out.println("FPS"+ drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

       // tileManager.draw((Graphics2D) graphics);
        player.draw((Graphics2D) graphics);

        graphics.dispose();
    }
}
