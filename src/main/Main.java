package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.listeners.MouseListener_;
import main.listeners.MouseMotionListener_;

public class Main {
	public static JFrame j = new JFrame("Engine Base");
	public static JPanel c = new Canvas();
	public static void main(String[] args) {
		j.setLayout(new BorderLayout());
		j.add(c);
		c.addMouseListener(new MouseListener_());
		c.addMouseMotionListener(new MouseMotionListener_());
		j.setSize(600, 600);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Engine e = new Engine();
		e.init();
		e.startloop();
	}
}