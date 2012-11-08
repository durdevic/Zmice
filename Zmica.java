package package1;



import javax.swing.JFrame;


public class Zmica extends JFrame {

   

	public Zmica() {

        add(new Tabla());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 340);
        setLocationRelativeTo(null);
        setTitle("Zmica");

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Zmica();
    }
}
