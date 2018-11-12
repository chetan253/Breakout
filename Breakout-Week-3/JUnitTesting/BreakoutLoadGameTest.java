import static org.junit.Assert.assertEquals;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Test;
import org.mockito.Mockito;

import com.UI.ButtonPanel;
import com.action.Breakout;
import com.action.Time;

import org.junit.Assert;

public class BreakoutLoadGameTest {
	JFrame frame = new JFrame();
	Time time = new Time();
	Breakout breakout = new Breakout(frame, time);
	
	
	@Test
	public void LoadGametest() {
		
		Assert.assertEquals(breakout.loadGame("./Save_2018-09-16_18-26-11.ser"), 0);		
		
	}

}

