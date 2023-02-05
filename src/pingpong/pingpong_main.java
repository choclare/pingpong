package pingpong;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pingpong_main extends JFrame implements KeyListener, ActionListener{
	
	Timer timer=new Timer(10, this);
	pingpong_panel pp = new pingpong_panel(1000, 1000, 50, 200, 10);

	public pingpong_main ()
	{
		addKeyListener(this);		
	    timer.start();
	    
		setTitle("awagga");
		setSize(pp.frame_width, pp.frame_height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(pp);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ev){
		if(ev.getSource()==timer){
			pp.update_ballpos();
		    pp.repaint();
		}
	}

	public void keyPressed(KeyEvent e) {
	    if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			pp.board_xpos+=20;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
			pp.board_xpos-=20;
		} else if (e.getKeyCode() == KeyEvent.VK_UP ) {
			pp.start_ballpos();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN ) {
		}
    }

    public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {
		new pingpong_main();		
	}
}

