/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.shape.Shape;
import javafx.event.Event;
import javafx.scene.Node;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.util.Random;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import java.io.File;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 *
 * @author cyberknight
 */
public class JavaFXApplication2 extends Application {

    public ArrayList<Rectangle> asteroidz = new ArrayList();
    public ArrayList<Rectangle> coinz = new ArrayList();
    public ArrayList<String> input = new ArrayList<String>();
    public Rectangle asteroid1;
    public Rectangle asteroid2;
    public Rectangle asteroid3;
    public Rectangle asteroid4;
    public Rectangle rocketship;
    public Rectangle coin;
    ImageView pic;
    ImageView pic2;
    ImageView pic3;
    ImageView pic4;
    ImageView pic5;
    ImageView pic6;
    ImageView pic7;
    ImageView pic8;
    Media media;
    Media media2;
    Media media3;
    MediaPlayer beat;
    int counter;
    int counter2 = 15;
    Label score = new Label("Score: ");
    Label death = new Label("You Died!");
    Label finalscore = new Label("Final Score: ");
    Label highscore = new Label("High Score: ");
    Label easteregg = new Label("You're a rocket man!");
    Label restart = new Label("Press R to restart");
    Label shoot = new Label("Press SPACE to destroy an asteroid!");

    @Override

    public void start(Stage primaryStage) throws FileNotFoundException {

        Pane root = new Pane();

        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(300, 600);
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        media = new Media(new File("src/Rocketman.wav").toURI().toString());
        beat = new MediaPlayer(media);
        beat.setVolume(30);
        beat.play();
        beat.setOnEndOfMedia(new Runnable() {
            public void run() {
                beat.seek(Duration.ZERO);
                beat.play();

            }
        });

        pic = new ImageView(new Image("file:src/rocketship.png"));
        pic2 = new ImageView(new Image("file:src/asteroid.png"));
        pic3 = new ImageView(new Image("file:src/asteroid.png"));
        pic4 = new ImageView(new Image("file:src/asteroid.png"));
        pic5 = new ImageView(new Image("file:src/asteroid.png"));
        pic6 = new ImageView(new Image("file:src/coin.png"));
        pic7 = new ImageView(new Image("file:src/background.png"));
        pic8 = new ImageView(new Image("file:src/explosion.gif"));

        holder.setStyle("-fx-background-image: background.png");
        Scene scene = new Scene(root, 300, 600, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Notice gc is not being used yet
        GraphicsContext gc = canvas.getGraphicsContext2D();
        score.relocate(0, 0);
        death.relocate(700, 700);
        finalscore.relocate(700, 700);
        easteregg.relocate(700, 700);
        highscore.relocate(0, 20);
        restart.relocate(700, 700);
        shoot.relocate(700, 700);

        //notice we are creating shape objects
        rocketship = new Rectangle(150, 575, 23, 23);
        rocketship.setFill(Color.TRANSPARENT);
        pic.relocate(150, 560);
        pic.setFitHeight(40);
        pic.setFitWidth(40);
        pic.setRotate(180);
        rocketship.setFill(Color.PLUM);

        asteroid1 = new Rectangle(0, 0, 25, 25);
        asteroid1.setFill(Color.TRANSPARENT);
        pic2.relocate(0, 0);
        pic2.setFitHeight(40);
        pic2.setFitWidth(40);

        asteroid2 = new Rectangle(75, 0, 25, 25);
        asteroid2.setFill(Color.BLUE);
        pic3.setX(asteroid2.getX());
        pic3.relocate(75, 0);
        pic3.setFitHeight(40);
        pic3.setFitWidth(40);

        asteroid3 = new Rectangle(150, 0, 25, 25);
        asteroid3.setFill(Color.BLUE);
        pic4.setX(asteroid3.getX());
        pic4.relocate(150, 0);
        pic4.setFitHeight(40);
        pic4.setFitWidth(40);

        asteroid4 = new Rectangle(225, 0, 25, 25);
        asteroid4.setFill(Color.BLUE);
        pic5.setX(asteroid4.getX());
        pic5.relocate(225, 0);
        pic5.setFitHeight(40);
        pic5.setFitWidth(40);

        coin = new Rectangle(900, 0, 25, 25);
        coin.setFill(Color.YELLOW);
        pic6.setX(coin.getX());
        pic6.relocate(900, 0);
        pic6.setFitHeight(40);
        pic6.setFitWidth(40);

        pic7.setFitHeight(600);
        pic7.setFitWidth(300);

        pic8.relocate(700, 700);
        pic8.setFitHeight(100);
        pic8.setFitHeight(100);
        pic8.setPreserveRatio(true);

        score.setTextFill(Color.web("#ffffff"));
        death.setTextFill(Color.web("#ffffff"));
        finalscore.setTextFill(Color.web("#ffffff"));
        easteregg.setTextFill(Color.web("#ffffff"));
        highscore.setTextFill(Color.web("#ffffff"));
        restart.setTextFill(Color.web("#ffffff"));
        shoot.setTextFill(Color.web("#ffffff"));

        // notice the difference in how an ArrayList adds items
        asteroidz.add(asteroid1);
        asteroidz.add(asteroid2);
        asteroidz.add(asteroid3);
        asteroidz.add(asteroid4);
        coinz.add(coin);

        //we have created an animation timer --- the class MUST be overwritten - look below
        AnimationTimer timer = new MyTimer();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                String code = event.getCode().toString();
                //    input.remove( code );
                if (event.getCode() == KeyCode.RIGHT) {
                    rocketship.setX(rocketship.getX() + 15);
                    pic.setX(pic.getX() + 15);
                    checkBounds(rocketship);
                } else if (event.getCode() == KeyCode.LEFT) {
                    rocketship.setX(rocketship.getX() - 15);
                    pic.setX(pic.getX() - 15);
                    checkBounds(rocketship);
                } else if (event.getCode() == KeyCode.E) {
                    easteregg.relocate(0, 60);
                } else if (event.getCode() == KeyCode.R) {
                    death.relocate(700, 700);
                    score.relocate(0, 0);
                    finalscore.relocate(700, 700);
                    highscore.relocate(0, 20);
                    restart.relocate(700, 700);
                    counter = 0;
                    rocketship.relocate(150, 575);
                    pic.relocate(150, 560);
                    shoot.relocate(700, 700);
                    pic8.relocate(700, 700);
                } else if (event.getCode() == KeyCode.SPACE) {
                    media2 = new Media(new File("src/explosionsound.wav").toURI().toString());
                    beat = new MediaPlayer(media2);
                    beat.setVolume(90);
                    beat.play();
                    //pic8.relocate(200, 200);
                    shoot.relocate(700, 700);
                    coin.relocate(750, 750);
                    pic6.relocate(750, 750);
                    pic5.relocate(800, 800);
                    asteroid4.relocate(800, 800);
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
                    // rectangleVelocity.set(0);
                }
            }
        });

        //try disabling canvas --- notice the difference
        root.getChildren().add(canvas);
        //notice we are manually adding the shape objects to the "root" window
        root.getChildren().add(pic7);
        root.getChildren().add(asteroid1);
        root.getChildren().add(asteroid2);
        root.getChildren().add(asteroid3);
        root.getChildren().add(asteroid4);
        root.getChildren().add(rocketship);
        root.getChildren().add(pic);
        root.getChildren().add(pic2);
        root.getChildren().add(pic3);
        root.getChildren().add(pic4);
        root.getChildren().add(pic5);
        root.getChildren().add(pic6);
        root.getChildren().add(coin);
        root.getChildren().add(score);
        root.getChildren().add(death);
        root.getChildren().add(finalscore);
        root.getChildren().add(easteregg);
        root.getChildren().add(highscore);
        root.getChildren().add(restart);
        root.getChildren().add(shoot);
        root.getChildren().add(pic8);

        timer.start();
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     *
     * The same as before main just calls the args described above
     */
    ///  vvvvvvvvvvvv   MAIN vvvvvvvvvvv
    public static void main(String[] args) {
        launch(args);
        Application.launch();
    }

    //// ^^^^^^^^^^^  MAIN ^^^^^^^^^^^^^
    // we create our time here --- to animate
    private class MyTimer extends AnimationTimer {

        boolean movedown = true;

        /// handle is defined by the abstract parent class -- it must be redined
        /// this is what happens again and again until stop()
        @Override
        public void handle(long now) {
            // You can look at the key presses here as well -- this is one of many. Try others
            if (input.contains("LEFT")) {
                rocketship.setX(rocketship.getX() - 5);
            }

            doHandle();
            /// notice doHandle()  is what happens again and again it's defined below

            highscore.setText("Highscore: " + Integer.toString(counter2));
            if (counter2 <= counter) {
                highscore.setText("Highscore: " + Integer.toString(counter));
            }
        }

        private void doHandle() {
            checkBounds(rocketship);
            if (movedown && asteroid1.getY() < 700) {
                asteroid1.setY(asteroid1.getY() + .5);
                pic2.setY(pic2.getY() + .5);
            }
            if (!movedown && asteroid1.getY() > 1) {
                asteroid1.setY(asteroid1.getY() - .5);
                pic2.setY(pic2.getY() - .5);
            }
            if (asteroid1.getY() > 1100) {
                movedown = false;
            }
            if (asteroid1.getY() < 1) {
                movedown = true;
            }
            if (asteroid1.getY() > 650) {
                asteroid1.setY(0);
                pic2.setY(0);
                counter += 1;
                score.setText("Score: " + Integer.toString(counter));
                int newpos = (int) (Math.random() * 275);
                asteroid1.setX(newpos);
                pic2.setX(newpos);
                asteroid1.setFill(Color.TRANSPARENT);
            }

            if (movedown && asteroid2.getY() < 700) {
                asteroid2.setY(asteroid2.getY() + .6);
                pic3.setY(pic3.getY() + .6);
            }
            if (!movedown && asteroid2.getY() > 1) {
                asteroid2.setY(asteroid2.getY() - .6);
                pic3.setY(pic3.getY() + .6);
            }
            if (asteroid2.getY() > 1100) {
                movedown = false;
            }
            if (asteroid2.getY() < 1) {
                movedown = true;
            }
            if (asteroid2.getY() > 650) {
                asteroid2.setY(0);
                pic3.setY(0);
                for (int i = 0; i < 5; i++) {
                    asteroid2.setX((i + 1) + (int) (Math.random() * 275));
                }
            }
            pic3.setX(asteroid2.getX());

            if (movedown && asteroid3.getY() < 700) {
                asteroid3.setY(asteroid3.getY() + .7);
                pic4.setY(pic4.getY() + .7);
            }
            if (!movedown && asteroid3.getY() > 1) {
                asteroid3.setY(asteroid3.getY() - .7);
                pic4.setY(pic4.getY() + .7);
            }
            if (asteroid3.getY() > 700) {
                movedown = false;
            }
            if (asteroid3.getY() < 1) {
                movedown = true;
            }
            if (asteroid3.getY() > 650) {
                asteroid3.setY(0);
                pic4.setY(0);
                for (int i = 0; i < 5; i++) {
                    asteroid3.setX((i + 1) + (int) (Math.random() * 275));
                }
            }
            pic4.setX(asteroid3.getX());

            if (movedown && asteroid4.getY() < 700) {
                asteroid4.setY(asteroid4.getY() + .8);
                pic5.setY(pic5.getY() + .8);
            }
            if (!movedown && asteroid4.getY() > 1) {
                asteroid4.setY(asteroid4.getY() - .8);
                pic5.setY(pic5.getY() + .8);
            }
            if (asteroid4.getY() > 1100) {
                movedown = false;
            }
            if (asteroid4.getY() < 1) {
                movedown = true;
            }
            if (asteroid4.getY() > 650) {
                asteroid4.setY(0);
                pic5.setY(0);
                for (int i = 0; i < 5; i++) {
                    asteroid4.setX((i + 1) + (int) (Math.random() * 275));
                }
            }
            pic5.setX(asteroid4.getX());

            if (movedown && coin.getY() < 700) {
                coin.setY(coin.getY() + .7);
                pic6.setY(pic6.getY() + .7);
            }
            if (!movedown && coin.getY() > 1) {
                coin.setY(coin.getY() - .7);
                pic6.setY(pic6.getY() + .7);
            }
            if (coin.getY() > 1100) {
                movedown = false;
            }
            if (coin.getY() < 1) {
                movedown = true;
            }
            if (coin.getY() > 650) {
                coin.setY(0);
                pic6.setY(0);
                for (int i = 0; i < 5; i++) {
                    coin.setX((i + 1) + (int) (Math.random() * 275));
                }
            }
            pic6.setX(coin.getX());
        }
    }

    private void checkBounds(Rectangle rocketship) {
        // checkBounds is called in two different locations --- it's really only necessary in the animation dohandle
        // experiment - check the differences

        boolean collisionDetected = false;

        for (Rectangle spacestuff : asteroidz) {
            if (rocketship.getBoundsInParent().intersects(spacestuff.getBoundsInParent())) {
                collisionDetected = true;
                death.relocate(150, 300);
                score.relocate(700, 700);
                finalscore.relocate(150, 320);
                finalscore.setText("Final Score: " + Integer.toString(counter));
                rocketship.relocate(700, 700);
                highscore.relocate(150, 340);
                restart.relocate(150, 360);
                pic.relocate(700, 700);
                shoot.relocate(700, 700);
                coin.relocate(750, 750);
                pic6.relocate(750, 750);
            } else {
                spacestuff.setFill(Color.TRANSPARENT);
            }
        }

        for (Rectangle spacestuff : coinz) {
            if (rocketship.getBoundsInParent().intersects(coin.getBoundsInParent())) {
                collisionDetected = true;
                coin.relocate(725, 725);
                shoot.relocate(0, 40);
                pic6.relocate(750, 750);
                media3 = new Media(new File("src/coinsound.wav").toURI().toString());
                beat = new MediaPlayer(media3);
                beat.setVolume(30);
                beat.play();
            } else {
                coin.setFill(Color.TRANSPARENT);
            }
        }

        if (collisionDetected) {
            rocketship.setFill(Color.BLUE);
        } else {
            rocketship.setFill(Color.TRANSPARENT);

        }
    }
}

class Rocketship extends Rectangle {

    boolean isgoup, isgoleft, isgoright;

    public Rocketship(double x, double y) {

    }
}

class Asteroids {

    Image asteroid = new Image("");
    public ImageView picture = new ImageView(asteroid);

    public Asteroids(double x, double y) {
    }
}
