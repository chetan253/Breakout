import org.junit.Test;

import com.constants.Constants;
import com.entity.Brick;
import com.entity.Component;

import org.junit.Assert;

public class BrickTest {
	
	Brick br;
	int x=10;
	int y=10;
	Component c;
	public BrickTest()
	{
		br=new Brick(x, y);
	}

	@Test
	public void getWidthTest()
	{
		
		int width=br.getWidth();
		Assert.assertEquals(width, Constants.brickWidth);
	}
	@Test
	public void getHeightTest()
	{
		
		int height=br.getHeight();
		Assert.assertEquals(height, Constants.brickWidth);
	}
	

}
