import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.UI.ButtonPanel;
import com.action.*;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ButtonPanelTest {
	
	ButtonPanel bPanel;
	JFrame frame;
	Breakout breakoutGame;
	Time timer;
	JPanel controlPanel;
	
	public  ButtonPanelTest() 
	{
		frame=Mockito.mock(JFrame.class);
		breakoutGame=Mockito.mock(Breakout.class);
		timer=Mockito.mock(Time.class);
		controlPanel=Mockito.mock(JPanel.class);
		bPanel=new ButtonPanel(frame, breakoutGame, timer);
	}
	
	
	
	
	
	@Test
	public void createUIPanelTest1() {
		String type="GridBag";
		bPanel.createUIPanel(type);
		ButtonPanel panel=Mockito.mock(ButtonPanel.class);
		Mockito.doCallRealMethod().when(panel).add(Mockito.any(JPanel.class), Mockito.any(BorderLayout.class));
		panel.add(controlPanel, BorderLayout.PAGE_END);
		Mockito.verify(panel, Mockito.times(1)).add(controlPanel, BorderLayout.PAGE_END);
	}
	
	@Test
	public void createUIPanelTest2() {
		String type="abc";
		bPanel.createUIPanel(type);
		ButtonPanel panel=Mockito.mock(ButtonPanel.class);
		Mockito.doCallRealMethod().when(panel).add(Mockito.any(JPanel.class), Mockito.any(BorderLayout.class));
		panel.add(controlPanel, BorderLayout.PAGE_END);
		Mockito.verify(panel, Mockito.times(1)).add(controlPanel, BorderLayout.PAGE_END);
	}
	
	public void initializeGameTest() {
		breakoutGame.initializeGame();
	}

}
