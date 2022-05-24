import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

/**
 * OrbitingPlanets class
 * helps setup the frame for which the planets will be displayed
 * Planet mercury, venus, etc : the planets that can be added
 * int clicks : keeps track of the amount of time the user clicks
 * ExecutorService executorService : handles the threads
 */
public class OrbitingPlanets extends JPanel {
    Planet mercury, venus, earth, mars, jupiter, saturn, uranus, neptune ;
    int clicks = 0;
    // create ExecutorService to manage threads
    ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * OrbitingPlanets constructor
     * sets the windows size, creates the planets, adds the mouse listener
     */
    OrbitingPlanets(){
        //set window size
        setSize(750,750);

        //create new planets
        mercury = new Planet(100,10);
        venus = new Planet(133,15);
        earth = new Planet(166,15);
        mars = new Planet(200,10);
        jupiter = new Planet(233,50);
        saturn = new Planet(266,45);
        uranus = new Planet(300,35);
        neptune = new Planet(333,35);

        //create and add mouselistener
        mouseListener mm = new mouseListener();
        addMouseListener(mm);

    }

    /**
     * Planet Class implements Runnable : creates planets as threads
     * int xCoord : holds the xcoordinate of the given planet
     * int ycoord : holds the ycoordinate of the given planet
     * int radius : holds the planet radius relative to the sun
     * int size : holds the planet size
     */
    public class Planet implements Runnable{

        int xCoord;
        int yCoord;
        final int radius;
        final int size;

        //constructor sets up the planet
        Planet(int radius, int size){
            xCoord = 600;
            yCoord = 412;
            this.radius = radius;
            this.size = size;
        }

        /**
         * void run() : runs the planets method to allow the coordinates to update
         */
        @Override
        public void run() {

            //use a loop that will run for a long time
            for(int i = 0; i < 1000000; i++) {
                try {
                    //the sleep amount controls the planet speed, i want the speed to be related to size
                    Thread.sleep(size);
                    //convert to radians for easier circular movement
                    double radians = i*Math.PI/180;

                    //equations for circular motion
                    xCoord = (375 - size/2) + (int)Math.round (radius * Math.cos(radians));
                    yCoord = (375 - size) + (int)Math.round (radius * Math.sin(radians));

                    //call  repaint method to update GUI
                    repaint();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                    Thread.currentThread().interrupt(); // re-interrupt the thread
                }
            }

        }

        /**
         * getx
         * @return : returns xcoord
         */
        public int getX(){return xCoord;}

        /**
         * gety
         * @return : returns ycoord
         */
        public int getY(){return yCoord;}

        /**
         * getSize
         * @return : returns the planets size
         */
        public int getSize(){return size;}
    }

    /**
     * mouseListener
     * override the mouselistener class
     */
    class mouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            //keep track of how many clicks the user has created
            clicks++;

            if(clicks == 1){
                //execute based on amount of clicks
                executorService.execute(mercury);
            }
            else if(clicks == 2){
                executorService.execute(venus);
            }
            else if(clicks == 3){
                executorService.execute(earth);

            }
            else if(clicks == 4){
                executorService.execute(mars);

            }
            else if(clicks == 5){
                executorService.execute(jupiter);

            }
            else if(clicks == 6){
                executorService.execute(saturn);

            }
            else if(clicks == 7){
                executorService.execute(uranus);

            }
            else if(clicks == 8){
                executorService.execute(neptune);

            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

    /**
     * override the paincomponent method
     * @param g : Graphics
     */
    @Override
    public void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D)g;  // Switch to graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g2);
        setBackground(Color.BLACK);

        //create sun
        g2.setColor(Color.YELLOW);
        g2.fillOval(325,325, 100,100);

        //check which planets need to be rendered and render them
        if(clicks >= 1){
            g2.setColor(Color.gray);
            g2.fillOval(mercury.getX(),mercury.getY(),mercury.getSize(),mercury.getSize());
        }
        if(clicks >= 2){
            g2.setColor(Color.orange);
            g2.fillOval(venus.getX(), venus.getY(),venus.getSize(),venus.getSize());
        }
        if(clicks >= 3){
            g2.setColor(Color.green);
            g2.fillOval(earth.getX(), earth.getY(),earth.getSize(),earth.getSize());
        }
        if(clicks >= 4){
            g2.setColor(new Color(175,0,0));
            g2.fillOval(mars.getX(),mars.getY(),mars.getSize(),mars.getSize());
        }
        if(clicks >= 5){
            g2.setColor(new Color(115,103,64));
            g2.fillOval(jupiter.getX(),jupiter.getY(),jupiter.getSize(),jupiter.getSize());
        }
        if(clicks >= 6){
            g2.setColor(new Color(255,237,186));
            g2.fillOval(saturn.getX(), saturn.getY(),saturn.getSize(),saturn.getSize());
        }
        if(clicks >= 7){
            g2.setColor(Color.cyan);
            g2.fillOval(uranus.getX(),uranus.getY(),uranus.getSize(),uranus.getSize());
        }
        if(clicks >= 8){
            g2.setColor(Color.blue);
            g2.fillOval(neptune.getX(), neptune.getY(),neptune.getSize(),neptune.getSize());
        }

    }

}
