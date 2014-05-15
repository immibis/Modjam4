package immibis.modjam4;

import org.junit.Test;
import static org.junit.Assert.*;

public class MathTests {
	@Test
	public void testShaftUtilsAngDiff() {
		assertEquals(ShaftUtils.angdiff(0x80000000, 0x30000000), 0x50000000);
		assertEquals(ShaftUtils.angdiff(0x80000000, 0x90000000), 0xF0000000);
		assertEquals(ShaftUtils.angdiff(0x80000000, 0x80000000), 0x00000000);
		assertEquals(ShaftUtils.angdiff(0x10000000, 0x00000000), 0x10000000);
		assertEquals(ShaftUtils.angdiff(0xE0000000, 0x00000000), 0xE0000000);
		assertEquals(ShaftUtils.angdiff(0x90000000, 0x40000000), 0x50000000);
		assertEquals(ShaftUtils.angdiff(0xE0000000, 0x40000000), 0xA0000000);
	}
}
