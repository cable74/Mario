package unit17;
//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import static java.lang.Character.*;

import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
	//private Alien alienOne;
	//private Alien alienTwo;
private ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	
	private ArrayList<Ammo> shots = new ArrayList<Ammo>();
	

	private boolean[] keys;
	private BufferedImage back;
	private Image bg;
	
	
	
	public OuterSpace()
	{
	//	JOptionPane.showMessageDialog(this, "Mario game by Caleb Lee, Press Left/Right to start, Space to shoot");
		
		setBackground(Color.black);

		keys = new boolean[5];
		ship = new Ship(500, 300, 5);

		
		//instantiate other stuff

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
		try {
			bg = ImageIO.read(new File("src/unit17/mariobackground.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.drawImage(bg, 0, 0, null);
		ship.draw(graphToBack);
		
		if (aliens.size() == 0) {
			aliens.add(new Alien(0,0,2));
			aliens.add(new Alien(700,0,2));
			
		}
		
		if(keys[0] == true && ship.getX()>0)
		{
			ship.changedirection(true);
			ship.move("LEFT");
		}

		if(keys[1] == true&&ship.getX()<700)
		{
			ship.changedirection(false);
			ship.move("RIGHT");
		}
		if(keys[2] == true&&ship.getY()>0)
		{
			ship.move("DOWN");
		}
		if(keys[3] == true&& ship.getY()<500)
		{
			ship.move("UP");
		}
		if(keys[4] == true)
		{
			
			shots.add(new Ammo(ship.getX()+37,ship.getY(),5));	
			keys[4] = false;

		}
		
		for(Alien i : aliens)
		{
			if(i.getY() > 700)
				i.setY(0);
			i.move("UP");
			i.draw(graphToBack);
			
			
			
			 for(Ammo s: shots)
             {

                 if(i.getX()>=s.getX()-100 && i.getX()<=s.getX() && i.getY()>=s.getY() && i.getY() <= s.getY()+80)
                 {
                     aliens.remove(i);
                     break;
                 }
                 
		}
		
		//add code to move stuff
		for(Ammo J : shots)
		{
			if (ship.direction == false) {
				if (J.getNew()) {
					J.setDir(false);
				}
			}
			else
			{	
				{
				if (J.getNew())
					J.setDir(true);
				}
			}
			if(!J.getDir())
				J.move("LEFT");
			else
				J.move("RIGHT");
			J.draw(graphToBack);
			
		}
		
		
		//add in collision detection
		

		twoDGraph.drawImage(back, null, 0, 0);
	}
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = true;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{

	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(5);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}

