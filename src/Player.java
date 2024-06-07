import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    private final double MOVE_AMT = 0.2;
    private BufferedImage right;
    private BufferedImage left;
    private BufferedImage down;
    private BufferedImage norm;
    private boolean facingUp;
    private boolean facingRight;

    private boolean facingleft;
    private boolean facingdown;
    private double xCoord;
    private double yCoord;
    private int score;



    public Player(String Img, String rightImg, String leftImg, String downImg) {
        facingUp = true;
        xCoord = 50; // starting position is (50, 435), right on top of ground
        yCoord = 435;
        score = 0;
        try {
            norm = ImageIO.read(new File(Img));
            right = ImageIO.read(new File(rightImg));
            left = ImageIO.read(new File(leftImg));
            down = ImageIO.read(new File(downImg));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getxCoord() {
        return (int) xCoord;
    }

    public int getyCoord() {
        return (int) yCoord;
    }

    public int getScore() {
        return score;
    }
    public void faceUp() {
        facingUp = true;
    }

    public void faceRight() {facingRight = true;}
    public void faceLeft() {
        facingRight = true;
    }
    public void facedown() {
        facingdown = true;
    }

    // Diagonal movement variables
    private double speed = .2; // Adjust this for desired speed
    private double angle;  // Angle of movement (degrees)

    // ... (rest of the class)

    public void moveDiagonally(double angleDegrees) {
        this.angle = angleDegrees; // Update angle for continuous movement
    }

    public void moveDiagonally() {
        double angleRadians = Math.toRadians(angle);
        double xSpeed = speed * Math.cos(angleRadians);
        double ySpeed = speed * Math.sin(angleRadians);

        if (xCoord + xSpeed >= 0 && xCoord + xSpeed <= 1280) {
            xCoord += xSpeed;
        }
        if (yCoord + ySpeed >= 0 && yCoord + ySpeed <= 910) {
            yCoord += ySpeed;
        }
    }

    public void moveRight() {
        if (xCoord + MOVE_AMT <= 1280) {
            xCoord += MOVE_AMT;
        }
    }

    public void moveLeft() {
        if (xCoord - MOVE_AMT >= 0) {
            xCoord -= MOVE_AMT;
        }
    }

    public void moveUp() {
        if (yCoord - MOVE_AMT >= 0) {
            yCoord -= MOVE_AMT;
        }
    }

    public void moveDown() {
        if (yCoord + MOVE_AMT <= 910) {
            yCoord += MOVE_AMT;
        }
    }

    public void moveNE(){
        if(xCoord + MOVE_AMT <= 1280 && yCoord - MOVE_AMT >= 0){
            xCoord += MOVE_AMT;
            yCoord -= MOVE_AMT;
        }
    }

    public void moveNW(){
        if(xCoord - MOVE_AMT >= 0 && yCoord - MOVE_AMT >= 0){
            xCoord -= MOVE_AMT;
            yCoord -= MOVE_AMT;
        }
    }

    public void moveSE(){
        if(xCoord + MOVE_AMT <= 1280 && yCoord + MOVE_AMT <= 910){
            xCoord += MOVE_AMT;
            yCoord += MOVE_AMT;
        }
    }

    public void moveSW(){
        if(xCoord - MOVE_AMT >= 0 && yCoord + MOVE_AMT <= 910){
            xCoord -= MOVE_AMT;
            yCoord += MOVE_AMT;
        }
    }

    public void collectCoin() {
        score++;
    }

    public BufferedImage getPlayerImage() {
        if (facingUp) {
            facingleft = false;
            facingRight = false;
            facingdown = false;
            return norm;
        } else if (facingRight){
            facingleft = false;
            facingUp = false;
            facingdown = false;
            return right;
        }
        else if(facingleft){
            facingUp = false;
            facingRight = false;
            facingdown = false;
            return left;
        }
        else{
            facingleft = false;
            facingRight = false;
            facingUp = false;
            return down;
        }
    }

    // we use a "bounding Rectangle" for detecting collision
    public Rectangle playerRect() {
        int imageHeight = getPlayerImage().getHeight();
        int imageWidth = getPlayerImage().getWidth();
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, imageWidth, imageHeight);
        return rect;
    }
}
