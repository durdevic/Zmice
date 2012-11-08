package package1;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Tabla extends JPanel implements ActionListener {

    	
	private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 500;
    private final int RAND_POS = 29;
    private final int DELAY = 170;

    private int x[] = new int[ALL_DOTS];
    private int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;


    public Tabla() {
        addKeyListener(new TAdapter());

        setBackground(Color.black);

        ImageIcon iid = new ImageIcon(this.getClass().getResource("GreenDot1.png"));
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(this.getClass().getResource("apple-logo1.png"));
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon(this.getClass().getResource("chrome1.png"));
        head = iih.getImage();

        setFocusable(true);
        pokreniIgru();
    }


    public void pokreniIgru() {

        dots = 5;

        for (int z = 0; z < dots; z++) {
            x[z] = 60 - z*10;
            y[z] = 60;
        }

        nadjiJabuku();

        timer = new Timer(DELAY, this);
        timer.start();
    }


    public void paint(Graphics g) {
        super.paint(g);

        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0)
                    g.drawImage(head, x[z], y[z], this);
                else g.drawImage(ball, x[z], y[z], this);
            }

            Toolkit.getDefaultToolkit().sync();
            g.dispose();

        } else {
        	krajIgre(g);
        }
    }


    public void krajIgre(Graphics g) {
        String msg = "Kraj igre";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH - metr.stringWidth(msg)) / 2,
                     HEIGHT / 2);
    }


    public void proveriJabuku() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            nadjiJabuku();
        }
    }


    public void kreni() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (left) {
            x[0] -= DOT_SIZE;
        }

        if (right) {
            x[0] += DOT_SIZE;
        }

        if (up) {
            y[0] -= DOT_SIZE;
        }

        if (down) {
            y[0] += DOT_SIZE;
        }
    }


    public void proveriSudar() {

          for (int z = dots; z > 0; z--) {

              if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                  inGame = false;
              }
          }

        if (y[0] > HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] > WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
    }

    public void nadjiJabuku() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));
        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            proveriJabuku();
            proveriSudar();
            kreni();
        }

        repaint();
    }


    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
        }
    }
}
