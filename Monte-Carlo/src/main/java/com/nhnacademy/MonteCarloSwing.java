package com.nhnacademy;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MonteCarloSwing extends JPanel {
    private int iterations;
    private int insideCircle;
    private Random random;

    public MonteCarloSwing(int iterations) {
        this.iterations = iterations;
        this.insideCircle = 0;
        this.random = new Random();
        setPreferredSize(new Dimension(800, 800));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < iterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (Math.pow(x, 2) + Math.pow(y, 2) <= 1) {
                insideCircle++;
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }

            int drawX = (int)(x * getWidth());
            int drawY = (int)(y * getHeight());

            g.drawOval(drawX, drawY, 1, 1);
        }

        System.out.println("Estimated Pi value: " + ((double) insideCircle / iterations * 4));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Monte Carlo Simulation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new MonteCarloSwing(10000000));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
