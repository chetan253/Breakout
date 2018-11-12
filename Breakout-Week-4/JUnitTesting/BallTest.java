import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.constants.Constants;
import com.entity.Ball;
import com.entity.Component;

public class BallTest {

		Ball b;
		int x=10;
		int y=10;
		Component c;
		
		public BallTest()
		{
			b=new Ball(x, y);
		}

		@Test
		public void getWidthTest()
		{
			
			int width=b.getWidth();
			Assert.assertEquals(width, Constants.ballRadius);
		}
		@Test
		public void getHeightTest()
		{
			
			int height=b.getHeight();
			Assert.assertEquals(height, Constants.ballRadius);
		}
}


