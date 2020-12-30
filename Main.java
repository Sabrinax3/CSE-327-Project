package GUIShaap;
import java.awt.Color;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		JFrame frame=new JFrame(); //window where game runs
		Game g=new Game();
		
		frame.setBounds(10, 10, 905, 700); 
		frame.setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(g); //adding object of game to object of JFrame
		
	}

}






