package main.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListener_ implements MouseListener {
	public static boolean mDown = false;
	public static int mX;
	public static int mY;
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mDown = true;
		mX = e.getX();
		mY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mDown = false;

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
