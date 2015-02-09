import static org.junit.Assert.*;

import org.junit.Test;


public class Testset {

	@Test
	public void test() {
		CustomSet<Integer> set = new CustomSet<Integer>();
		for (int i = 0; i < 10; i++)
			set.add(i);
		assertEquals(set.size(), 10);
		set.add(2);
		assertEquals(set.size(), 10);
		set.remove(2);
		assertEquals(set.size(), 9);
		assertEquals(set.get(2).intValue(), 3);
	}

}
