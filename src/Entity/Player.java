package Entity;

import Core.GamePanel;
import Core.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
   private final GamePanel gamePanel;
     private final KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;


    public Player (GamePanel gamePanel,KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage(); //posible hacer private
        screenX = gamePanel.getScreenWidth()/2 - (gamePanel.getPixel()/2);
        screenY = gamePanel.getScreenHeight()/2- (gamePanel.getPixel()/2);
        hitbox = new Rectangle(8,16,20,20);
    }

    private void setDefaultValues(){
        worldX = gamePanel.getPixel()*23;
        worldY = gamePanel.getPixel()*21;
        speed = 3;
        direction = "up";
    }


    private void getPlayerImage(){
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Player/huevo_up1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.isUpPressed()) {
            direction = "up";
        } else if (keyHandler.isDownPressed()) {
            direction = "down";
        } else if (keyHandler.isLeftPressed()) {
            direction = "left";
        } else if (keyHandler.isRightPressed()) {
            direction = "right";
        }

        // collisionOn = false;
        // gamePanel.collisionChecker.checkTile(this);

        //IF COLLISION IS FALSE PLAYER CAN MOVE

        if (keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isRightPressed() || keyHandler.isLeftPressed()) {

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D){
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        graphics2D.drawImage(image, screenX, screenY,gamePanel.getPixel(), gamePanel.getPixel(), null);

    }

}