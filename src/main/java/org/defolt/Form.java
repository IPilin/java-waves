package org.defolt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Form extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    JFrame window;
    WavesApi api;
    Timer timer;
    int blockSize = 10;
    float k = 0.01f;
    float restLength = 0;
    Block[][] blocks = new Block[100][100];
    List<Spring> springs = new ArrayList<>();

    public static void main(String[] args) {
        new Form();
    }

    public Form() {
        addMouseListener(this);
        addMouseMotionListener(this);
        window = new JFrame();
        window.setContentPane(this);
        window.setTitle("Waves");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setPreferredSize(new Dimension(700, 600));



        api = new WavesApi(700, 600, blockSize);

        for (int j = 0; j < blocks.length; j++) {
            for (int i = 0; i < blocks[j].length; i++) {
                blocks[j][i] = new Block(new Vector2D(i * (blockSize), j * (blockSize)), blockSize, blockSize);
                if (i == 0 || i == blocks[j].length - 1) {
                    blocks[j][i].setMass(-1);
                }
                if (j == 0 || j == blocks.length - 1) {
                    blocks[j][i].setMass(-1);
                }
                if (i != 0) {
                    springs.add(new Spring(k, restLength, blocks[j][i - 1], blocks[j][i]));
                }
                if (j != 0) {
                    springs.add(new Spring(k, restLength, blocks[j - 1][i], blocks[j][i]));
                }
            }
        }


        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        timer = new Timer(10, this);

        new Timer(10, (e) -> repaint()).start();
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (var spring : springs) {
            spring.show(g);
        }

        for (var block : blocks) {
            for (var b : block) {
                b.show(g);
            }
        }
    }

    public void mouseMapper(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            int x = e.getX() / blockSize;
            int y = e.getY() / blockSize;
            x = Math.max(x, 0);
            x = Math.min(x, blocks[0].length - 1);
            y = Math.max(y, 0);
            y = Math.min(y, blocks.length - 1);
            blocks[y][x].setValue(10);
            blocks[y][x].setMass(1);
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            int x = e.getX() / blockSize;
            int y = e.getY() / blockSize;
            x = Math.max(x, 0);
            x = Math.min(x, blocks[0].length - 1);
            y = Math.max(y, 0);
            y = Math.min(y, blocks.length - 1);
            blocks[y][x].setMass(-1);
            blocks[y][x].setValue(0);
        }
    }

    int mult = 10;
    int counter = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        for (var spring : springs) {
            spring.update();
        }
        for (var block : blocks) {
            for (var b : block) {
                b.update();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseMapper(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseMapper(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
