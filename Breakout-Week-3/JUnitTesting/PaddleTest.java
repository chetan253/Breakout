import org.junit.Test;

import com.constants.Constants;
import com.entity.Paddle;
import com.entity.Component;

import org.junit.Assert;

public class PaddleTest {
	
	Paddle p;
	int x=10;
	int y=10;
	Component c;
	public PaddleTest()
	{
		p=new Paddle(x, y);
	}

	@Test
	public void getWidthTest()
	{
		
		int width=p.getWidth();
		Assert.assertEquals(width, Constants.paddleWidth);
	}
	@Test
	public void getHeightTest()
	{
		
		int height=p.getHeight();
		Assert.assertEquals(height, Constants.paddleHeight);
	}
	

}