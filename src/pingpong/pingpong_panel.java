package pingpong;

import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Random;

public class pingpong_panel extends JPanel{
	int frame_width;
	int frame_height;
	int ball_diemeter;
	int board_ypos;
	int board_xwidth;
	int board_ywidth;
	int board_xpos;
	int ball_xpos;
	int ball_ypos;
	boolean ball_start;
	int ball_velocity;
	int ball_vel_x;
	int ball_vel_y;
	
	public pingpong_panel (int width, int height, int radius, int b_w, int b_h)
	{
		frame_width = width;
		frame_height = height;
		ball_diemeter = radius;
		board_ypos = frame_height*6/7;
		board_xwidth = b_w;
		board_ywidth = b_h;
		board_xpos = frame_width/2 - board_xwidth/2;
		ball_xpos = frame_width/2 - ball_diemeter/2;
		ball_ypos = 0;
		
		ball_velocity = 10;

		ball_start = false;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);   // to remove all previous paint
		Graphics2D g2d=(Graphics2D)g;
		Shape circleShape=new Ellipse2D.Double(ball_xpos, ball_ypos, ball_diemeter, ball_diemeter);
		g2d.setColor(Color.BLUE);
		g2d.fill(circleShape);
		
		g2d.setColor(Color.GRAY);
		g2d.fillRect(board_xpos, board_ypos, board_xwidth, board_ywidth);
	}
	
	private int generateRandomNumber() {
		Random rand = new Random();
		int upperbound = 179;
		int int_random;
		
		while(true)
		{
			int_random = rand.nextInt(upperbound);
			if ((int_random >= 30 && int_random <= 80) || (int_random >= 100 && int_random <= 150))
			{
				break;
			}
		}
	     
		System.out.println(int_random);
		return int_random;
	}
	
	public void start_ballpos () {
		int angle = generateRandomNumber();
		
        // convert degrees to radians
        double radians = Math.toRadians(angle);
  
		//reinit ball and board poisition
		board_ypos = frame_height*6/7;
		board_xpos = frame_width/2 - board_xwidth/2;
		ball_xpos = frame_width/2 - ball_diemeter/2;
		ball_ypos = 0;
		
		ball_vel_x = (int)((double)ball_velocity * Math.cos(radians));
		ball_vel_y = (int)((double)ball_velocity * Math.sin(radians));
		System.out.println(ball_vel_x);
		System.out.println(ball_vel_y);
		
		ball_start = true;
	}
	
	public void update_ballpos( ) {
		
		if (ball_start)
		{
			ball_xpos += ball_vel_x;
			ball_ypos += ball_vel_y;
			
			// if ball hits either walls, change x direction
			if (ball_xpos <= 0 || ball_xpos + ball_diemeter >= frame_width)
			{
				ball_vel_x *= -1;  
			}
			
			// if ball hits the board, change y direction
			if (ball_ypos + ball_diemeter >= board_ypos)
			{
				if (ball_xpos + ball_diemeter >= board_xpos && ball_xpos <= board_xpos + board_xwidth)
				{	
					ball_vel_y *= -1;
				}
				else
				{
					System.out.println("Game end!!!!");
					ball_start = false;
				}
			}
			
			// if ball reaches to ceiling, change y direction
			if (ball_ypos < 0)
			{
				ball_vel_y *= -1;
			}
		}
	}
}
