package com.lucapiras.snk.utils.string;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca Piras
 */
public class AdvancedStringTokenizerTest {

    /**
     * Test of extractRemainingText method, of class AdvancedStringTokenizer.
     */
    @Test
    public void testExtractRemainingText() {
        System.out.println("extractRemainingText");
        
        String delim = " ";
                
        AdvancedStringTokenizer instance = new AdvancedStringTokenizer("", delim);
        assertEquals(instance.extractRemainingText(delim), "");
        
        String test = "This is a trial.";
        instance = new AdvancedStringTokenizer(test, delim);
        assertEquals(instance.extractRemainingText(delim), test);
        
        instance = new AdvancedStringTokenizer(test, delim);
        instance.nextToken();
        assertEquals(instance.extractRemainingText(delim), "is a trial.");
        
        instance = new AdvancedStringTokenizer(test, delim);
        instance.nextToken();
        instance.nextToken();
        assertEquals(instance.extractRemainingText(delim), "a trial.");
        
        instance = new AdvancedStringTokenizer(test, delim);
        instance.nextToken();
        instance.nextToken();
        instance.nextToken();
        assertEquals(instance.extractRemainingText(delim), "trial.");
        
        instance = new AdvancedStringTokenizer(test, delim);
        instance.nextToken();
        instance.nextToken();
        instance.nextToken();
        instance.nextToken();
        assertEquals(instance.extractRemainingText(delim), "");
        
        String testPlusDelim = test + delim;
        instance = new AdvancedStringTokenizer(test, delim);
        instance.nextToken();
        instance.nextToken();
        instance.nextToken();
        instance.nextToken();
        assertEquals(instance.extractRemainingText(delim), "");
        
        instance = new AdvancedStringTokenizer(testPlusDelim, delim);
        assertEquals(instance.extractRemainingText(delim), test);
    }
    
}
