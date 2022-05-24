import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        //create new window
        OrbitingPlanets orbit = new OrbitingPlanets();
        JFrame frame = new JFrame();

        frame.setTitle("Orbiting Planets");
        frame.setSize(750, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(orbit);
        frame.setVisible(true);

    }
}
