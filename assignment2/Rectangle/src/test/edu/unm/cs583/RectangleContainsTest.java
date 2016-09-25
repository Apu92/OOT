package test.edu.unm.cs583;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import edu.unm.cs583.Rectangle;

@RunWith(Parameterized.class)
public class RectangleContainsTest {

	private Rectangle r;

	private int x;
	private int y;
	private int width;
	private int height;

	private static int testCounter = 0;

	/**
	 * Ctor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public RectangleContainsTest(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		r = new Rectangle(x, y, width, height);
	}

	@Parameterized.Parameters
	public static Collection<?> primeNumbers() {
		return Arrays.asList(new Object[][] {
			{ 1, 2, 3, 4 },      // Basic test
			{ 0, 0, 4, 4 },      // Origin test
			{ 0, 0, 0, 0 },      // Zero test
			{ 1, 1, 0, 0 },      // Zero width height test
			{ -3, -2, 0, 0},     // Negative origin zero width height test
			{ -1, -2, 3, 6},     // Negative origin test
			{ -4, -1, -2, -4},   // Negative width height tests
			{ 5, -8, -1, -2},    
			{ -5, 8, -1, -2},
			{ -5, -8, 1, -2},
			{ -5, -8, -1, 2},
			{ 5, 8, -1, -2}
		});
	}

	@Before
	public void initialize() {
		System.out.println("Parameterized count is : " + ++testCounter + " - " + r);
	}

	@Test
	public void testCorners() {
		// Define base point left lower corner
		int baseX = x;
		int tmpX = x + width;
		if (width < 0) {
			baseX = x + width;
			tmpX = x;
		}
		int baseY = y;
		int tmpY = y + height;
		if (height < 0) {
			baseY = y + height;
			tmpY = y;
		}

		boolean absWidthNotZero = Math.abs(width) != 0;
		boolean absHeightNotZero = Math.abs(height) != 0;

		// Test left lower corner
		assertEquals(r.contains(baseX, baseY), true);
		assertEquals(r.contains(baseX - 1, baseY), false);
		assertEquals(r.contains(baseX, baseY - 1), false);
		assertEquals(r.contains(baseX - 1, baseY - 1), false);
		assertEquals(r.contains(baseX - 1, baseY + 1), false);
		assertEquals(r.contains(baseX + 1, baseY - 1), false);
		if (absWidthNotZero) {
			assertEquals(r.contains(baseX + 1, baseY), true);
		}
		if (absHeightNotZero) {
			assertEquals(r.contains(baseX, baseY + 1), true);
		}
		if (absWidthNotZero && absHeightNotZero) {
			assertEquals(r.contains(baseX + 1, baseY + 1), true);
		}

		// Test right lower corner
		assertEquals(r.contains(tmpX, baseY), true);
		assertEquals(r.contains(tmpX + 1, baseY), false);
		assertEquals(r.contains(tmpX + 1, baseY + 1), false);
		assertEquals(r.contains(tmpX + 1, baseY - 1), false);
		assertEquals(r.contains(tmpX, baseY - 1), false);
		assertEquals(r.contains(tmpX - 1, baseY - 1), false);
		if (absWidthNotZero) {
			assertEquals(r.contains(tmpX - 1, baseY), true);
		}
		if (absHeightNotZero) {
			assertEquals(r.contains(tmpX, baseY + 1), true);
		}
		if (absWidthNotZero && absHeightNotZero) {
			assertEquals(r.contains(tmpX - 1, baseY + 1), true);
		}

		// Test left upper corner
		assertEquals(r.contains(baseX, tmpY), true);
		assertEquals(r.contains(baseX - 1, tmpY), false);
		assertEquals(r.contains(baseX - 1, tmpY + 1), false);
		assertEquals(r.contains(baseX - 1, tmpY - 1), false);
		assertEquals(r.contains(baseX, tmpY + 1), false);
		assertEquals(r.contains(baseX + 1, tmpY + 1), false);
		if (absWidthNotZero) {
			assertEquals(r.contains(baseX + 1, tmpY), true);
		}
		if (absHeightNotZero) {
			assertEquals(r.contains(baseX, tmpY - 1), true);
		}
		if (absWidthNotZero && absHeightNotZero) {
			assertEquals(r.contains(baseX + 1, tmpY - 1), true);
		}

		// Test right upper corner
		assertEquals(r.contains(tmpX, tmpY), true);
		assertEquals(r.contains(tmpX + 1, tmpY), false);
		assertEquals(r.contains(tmpX + 1, tmpY - 1), false);
		assertEquals(r.contains(tmpX, tmpY + 1), false);
		assertEquals(r.contains(tmpX - 1, tmpY + 1), false);
		assertEquals(r.contains(tmpX + 1, tmpY + 1), false);
		if (absWidthNotZero) {
			assertEquals(r.contains(tmpX - 1, tmpY), true);
		}
		if (absHeightNotZero) {
			assertEquals(r.contains(tmpX, tmpY - 1), true);
		}
		if (absWidthNotZero && absHeightNotZero) {
			assertEquals(r.contains(tmpX - 1, tmpY - 1), true);
		}
	}

	@Test
	public void testCenter() {

		int centerX = x + width / 2;
		int centerY = y + height / 2;

		int testDistanceX = width == 0 ? 1 : width;
		int testDistanceY = height == 0 ? 1 : height;

		// Test center point
		assertEquals(r.contains(centerX, centerY), true);

		// Test center point on first x line
		assertEquals(r.contains(centerX, y), true);
		assertEquals(r.contains(centerX, y - testDistanceY), false);

		// Test center point on second x line
		assertEquals(r.contains(centerX, y + height), true);
		assertEquals(r.contains(centerX, y + 2 * testDistanceY), false);

		// Test center point on first y line
		assertEquals(r.contains(x, centerY), true);
		assertEquals(r.contains(x - testDistanceX, centerY), false);

		// Test center point on second y line
		assertEquals(r.contains(x + width, centerY), true);
		assertEquals(r.contains(x + 2 * testDistanceX, centerY), false);
	}

}
