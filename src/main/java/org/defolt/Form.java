package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Form extends JPanel implements ActionListener, MouseListener {
    JFrame window;
    WavesApi api;
    Timer timer;
    int blockSize = 5;

    public static void main(String[] args) {
        new Form();
    }

    public Form() {
        addMouseListener(this);
        window = new JFrame();
        window.setContentPane(this);
        window.setTitle("Waves");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setPreferredSize(new Dimension(700, 600));

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        api = new WavesApi(700, 600, blockSize);

        for (int i = 0; i < 20; i++) {
            api.addBlock((blockSize + 1) * i, 200, 2);
        }
        for (int i = 20; i < 100; i++) {
            api.addBlock((blockSize + 1) * i, 200, 1);
        }
        api.getBlocks().get(0).setMass(-1);

        timer = new Timer(10, this);

        new Timer(10, (e) -> repaint()).start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (var block : api.getBlocks()) {
            if (block.getMass() == 1) {
                g.setColor(Color.GRAY);
            } else {
                g.setColor(Color.BLUE);
            }
            g.fillRect(block.getIntX(), block.getIntY(), block.width, block.height);
            if (block.getNext() != null) {
                g.drawLine(block.getIntX() + blockSize, block.getIntY(),
                        block.getNext().getIntX(), block.getNext().getIntY());
            }
        }
    }

    public void mouseMapper(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            var b = api.getBlocks().get(0);
            b.setY(e.getY());
        }
        if (SwingUtilities.isRightMouseButton(e)) {
            if (timer.isRunning()) {
                timer.stop();
            } else {
                timer.start();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        api.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
}
