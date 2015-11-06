package src.test.java.unit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import src.main.java.NumberGenerator;

public class NumberGeneratorTest {
    
    private NumberGenerator numberGenerator;
    
    @Before 
    public void initialize() {
	 numberGenerator = new NumberGenerator();
    }
    
	    

    @Test
    public void testRandBetween() {
	int random = numberGenerator.rand(20);
	int high = 20;
	int low = 1;
	assertTrue("Error, random is too high", high >= random);
	assertTrue("Error, random is too low",  low  <= random);
	
    }

}
