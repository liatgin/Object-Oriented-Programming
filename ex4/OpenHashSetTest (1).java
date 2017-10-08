import static org.junit.Assert.*;

import org.junit.Test;


public class OpenHashSetTest {

	@Test
	public void testDefaultConstructor1() {
		OpenHashSet testOpenHashSet = new OpenHashSet();
		
		assertEquals(0, testOpenHashSet.size());
		assertEquals(.75,testOpenHashSet.upperLoadFactor,0);
		assertEquals(.25,testOpenHashSet.lowerLoadFactor,0);
	}
	
	@Test 
	public void testArrayConstructor2(){
		String[] stringArray = {"1","5","9","7"	};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertTrue(testSet.contains("1"));
		assertTrue(testSet.contains("5"));
		assertTrue(testSet.contains("9"));
		assertTrue(testSet.contains("7"));
		assertEquals(4,testSet.size());
		assertEquals(16, testSet.capacity());
		assertEquals(.75,testSet.upperLoadFactor,0);
	}
	
	@Test
	public void testLoadsConstructor3(){
		OpenHashSet testSet = new OpenHashSet(.6f,.1f);
		assertEquals(16, testSet.capacity());
		assertEquals(0, testSet.size());
		assertEquals(.6f,testSet.upperLoadFactor,0);
		assertEquals(.1f,testSet.lowerLoadFactor,0);
	}
	
	@Test
	public void testAddWord4(){
		OpenHashSet testSet = new OpenHashSet();
		assertTrue(testSet.add("Hi"));
	}
	
	@Test
	public void testAddExistingWord5(){
		OpenHashSet testSet = new OpenHashSet();
		assertTrue(testSet.add("Hi"));
		assertFalse(testSet.add("Hi"));
	}
	
	@Test
	public void testContainsNegative6(){
		OpenHashSet testSet = new OpenHashSet();
		assertFalse(testSet.contains("Hi"));
	}

	@Test
	public void testContainsPositive7(){
		OpenHashSet testSet = new OpenHashSet();
		assertTrue(testSet.add("Hi"));
		assertTrue(testSet.contains("Hi"));
	}
	
	@Test
	public void testDeleteExisting8(){
		OpenHashSet testSet = new OpenHashSet();
		assertTrue(testSet.add("Hi"));
		assertTrue(testSet.delete("Hi"));
	}
	
	@Test
	public void testDeleteNonExisting9(){
		OpenHashSet testSet = new OpenHashSet();
		assertFalse(testSet.delete("Hi"));
	}
	
	@Test
	public void testSizeEmpty10(){
		OpenHashSet testSet = new OpenHashSet();
		assertEquals(0, testSet.size());
	}
	
	@Test
	public void testSizeOneElement11(){
		OpenHashSet testSet = new OpenHashSet();
		assertTrue(testSet.add("Hi"));
		assertEquals(1, testSet.size());
	}
	
	@Test
	public void testAddDelete12(){
		OpenHashSet testSet = new OpenHashSet();
		assertTrue(testSet.add("Hi"));
		assertTrue(testSet.delete("Hi"));
		assertEquals(0, testSet.size());
	}
	
	@Test
	public void testDeleteNonExistingPlusSize13(){
		OpenHashSet testSet = new OpenHashSet();
		assertTrue(testSet.add("Hi"));
		assertFalse(testSet.delete("Bye"));
		assertEquals(1, testSet.size());
	}
	
	@Test
	public void testCapacity14(){
		OpenHashSet testSet = new OpenHashSet();
		assertEquals(16, testSet.capacity());
	}
	
	@Test
	public void testCapacityAfterSimpleAddition15(){
		OpenHashSet testSet = new OpenHashSet();
		assertTrue(testSet.add("Hi"));
		assertEquals(16, testSet.capacity());
	}
	
	@Test
	public void testCapacityDifferent16(){
		OpenHashSet testSet = new OpenHashSet();
		assertTrue(testSet.add("Hi"));
		assertFalse(testSet.add("Hi"));
		assertFalse(testSet.add("Hi"));
		assertEquals(16, testSet.capacity());
	}
	
	@Test
	public void testCapacityAgain17(){
		OpenHashSet testSet = new OpenHashSet(.7f,.1f);
		assertTrue(testSet.add("Hi"));
		assertFalse(testSet.add("Hi"));
		assertTrue(testSet.add("Bye"));
		assertEquals(2, testSet.size());
		assertEquals(16, testSet.capacity());
		assertFalse(testSet.add("Hi"));
		assertEquals(2, testSet.size());
		assertEquals(16, testSet.capacity());
	}
	
	@Test
	public void testCapacityChanged18(){
		OpenHashSet testSet = new OpenHashSet(.7f,.1f);
		assertTrue(testSet.add("Hi"));
		assertTrue(testSet.add("Hello"));
		assertTrue(testSet.add("Bye"));
		assertEquals(3, testSet.size());
		assertEquals(16, testSet.capacity());
		assertTrue(testSet.add("Ciao"));
		assertEquals(4, testSet.size());
		assertTrue(testSet.add("1"));
		assertTrue(testSet.add("2"));
		assertTrue(testSet.add("3"));
		assertTrue(testSet.add("4"));
		assertTrue(testSet.add("5"));
		assertTrue(testSet.add("6"));
		assertTrue(testSet.add("7"));
		assertEquals(16, testSet.capacity());
		assertTrue(testSet.add("8"));
		assertEquals(32, testSet.capacity());
		assertFalse(testSet.add("8"));
		assertTrue(testSet.add("9"));
		assertTrue(testSet.add("10"));
		assertTrue(testSet.add("11"));
		assertTrue(testSet.add("12"));
		assertTrue(testSet.add("13"));
		assertTrue(testSet.add("14"));
		assertEquals(18, testSet.size());
		assertEquals(32, testSet.capacity());
		
	}
	
	@Test
	public void testCapacityChangedAgain19(){
		OpenHashSet testSet = new OpenHashSet(.7f,.1f);
		assertTrue(testSet.add("Hi"));
		assertTrue(testSet.add("Hello"));
		assertFalse(testSet.add("Hello"));
		assertTrue(testSet.add("Bye"));
		assertEquals(3, testSet.size());
		assertEquals(16, testSet.capacity());
		assertTrue(testSet.add("Ciao"));
		assertFalse(testSet.add("Hi"));
		assertEquals(4, testSet.size());
		assertTrue(testSet.add("5"));
		assertTrue(testSet.add("6"));
		assertTrue(testSet.add("7"));
		assertTrue(testSet.add("8"));
		assertTrue(testSet.add("9"));
		assertTrue(testSet.add("10"));
		assertTrue(testSet.add("11"));
		assertEquals(16, testSet.capacity());
		assertFalse(testSet.add("11"));
		assertEquals(16, testSet.capacity());
		assertTrue(testSet.add("12"));
		assertEquals(32, testSet.capacity());
		assertEquals(12, testSet.size());
		
	}
	
	@Test
	public void testCapacityChangedTwice20(){
		OpenHashSet testSet = new OpenHashSet(.5f,.1f);
		assertTrue(testSet.add("Hi"));
		assertTrue(testSet.add("Hello"));
		assertTrue(testSet.add("Bye"));
		assertTrue(testSet.add("4"));
		assertTrue(testSet.add("5"));
		assertTrue(testSet.add("6"));
		assertTrue(testSet.add("7"));
		assertTrue(testSet.add("8"));
		assertEquals(16, testSet.capacity());
		assertTrue(testSet.add("9"));
		assertEquals(32, testSet.capacity());
		assertTrue(testSet.add("10"));
		assertTrue(testSet.add("11"));
		assertEquals(32, testSet.capacity());
		assertTrue(testSet.add("12"));
		assertTrue(testSet.add("13"));
		assertTrue(testSet.add("14"));
		assertTrue(testSet.add("15"));
		assertTrue(testSet.add("16"));
		assertEquals(32, testSet.capacity());
		assertTrue(testSet.add("17"));
		assertEquals(64, testSet.capacity());
		assertTrue(testSet.add("18"));
		assertTrue(testSet.add("19"));
		assertTrue(testSet.add("20"));
		assertEquals(64, testSet.capacity());
		assertEquals(20, testSet.size());
	}
	
	@Test
	public void testDataConstructorAgain21(){
		String[] stringArray = {"Hi","Bye","Hello","Ciao","Shalom"	};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertEquals(5, testSet.size());
	}
	
	@Test
	public void testDataConstructorCapacity22(){
		String[] stringArray = {"Hi","Bye","Hello","Ciao","Shalom"	};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertEquals(16, testSet.capacity());
	}
	
	@Test
	public void testDataConstructorDuplicates23(){
		String[] stringArray = {"Hi","Bye","Hello","Ciao","Shalom","Hi","Hello","Bye"	};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertEquals(5, testSet.size());
		assertEquals(16, testSet.capacity());
	}
	
	@Test
	public void testSameBucket24(){
		String[] stringArray = {"DAST","CLIDS","Infi","Hello","Bye",
				"Test","Algebra","History","Java","Math"	};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertEquals(10, testSet.size());
		assertEquals(16, testSet.capacity());
	}
	
	@Test
	public void testSimpleDelete25(){
		String[] stringArray = {"DAST","CLIDS","Infi","Hello","Bye",
				"Test","Algebra","History","Java","Math"	};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertTrue(testSet.delete("History"));
		assertTrue(testSet.delete("CLIDS"));
		assertEquals(8, testSet.size());
		assertEquals(16, testSet.capacity());
	}
	
	@Test
	public void testComplexDelete26(){
		String[] stringArray = {"DAST","CLIDS","Infi","Hello","Bye",
				"Test","Algebra","History","Java","Math"	};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertTrue(testSet.delete("Hello"));
		assertTrue(testSet.delete("Algebra"));
		assertEquals(8, testSet.size());
		assertEquals(16, testSet.capacity());
	}
	
	@Test
	public void testDeleteCapacity27(){
		String[] stringArray = {"DAST","CLIDS","Infi","Hello","Bye",
				"Test","Algebra"};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertEquals(16, testSet.capacity());
		assertEquals(7, testSet.size());
		assertTrue(testSet.delete("Test"));
		assertTrue(testSet.delete("Algebra"));
		assertTrue(testSet.delete("DAST"));
		assertTrue(testSet.delete("Infi"));
		assertEquals(8, testSet.capacity());
		assertEquals(3, testSet.size());
		assertFalse(testSet.delete("Clids"));
		assertEquals(8, testSet.capacity());
		assertEquals(3, testSet.size());

	}
	
	@Test
	public void testDeleteCapacityTwice28(){
		String[] stringArray = {"DAST","CLIDS","Infi","Hello","Bye",
				"Test","Algebra"};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertEquals(16, testSet.capacity());
		assertEquals(7, testSet.size());
		assertTrue(testSet.delete("Test"));
		assertTrue(testSet.delete("Hello"));
		assertTrue(testSet.delete("DAST"));
		assertTrue(testSet.delete("Infi"));
		assertEquals(8, testSet.capacity());
		assertEquals(3, testSet.size());
		assertTrue(testSet.delete("CLIDS"));
		assertTrue(testSet.delete("Bye"));
		assertEquals(4, testSet.capacity());
		assertEquals(1, testSet.size());
	}
	
	@Test
	public void testComplex29(){
		String[] stringArray = {"hash", "Hash", "guitar", "piano", "violin", "DAST", "CLIDS", "Infi", "Hello", "Bye", "Test", "Algebra", "Boo", "DAST", "CLIDS", "Shalom", "Kushkush", "hello", "hi", "naal", "DAST", "CLIDS", "oink"};
		OpenHashSet testSet = new OpenHashSet(stringArray);
		assertEquals(32, testSet.capacity());
		assertEquals(19, testSet.size());
		assertTrue(testSet.delete("Boo"));
		assertTrue(testSet.delete("Infi"));
		assertTrue(testSet.delete("Hello"));
		assertTrue(testSet.delete("hello"));
		assertTrue(testSet.delete("Hash"));
		assertTrue(testSet.add("Hash"));
		assertTrue(testSet.delete("Hash"));
		assertTrue(testSet.delete("Bye"));
		assertEquals(13, testSet.size());
		assertEquals(32, testSet.capacity());
	}
}
