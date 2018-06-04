package com.lucapiras.snk.utils.string;

import java.util.StringTokenizer;

/**
 *
 * @author Luca Piras
 */
public class AdvancedStringTokenizer extends StringTokenizer {

    public AdvancedStringTokenizer(String str) {
        super(str);
    }
    
    public AdvancedStringTokenizer(String str, String delim, boolean returnDelims) {
        super(str, delim, returnDelims);
    }
    
    public AdvancedStringTokenizer(String str, String delim) {
        super(str, delim);
    }
    
    public String extractRemainingText(String delim) {
        StringBuilder sb = new StringBuilder("");
        
        while (this.hasMoreElements()) {
            sb.append(this.nextToken());
            
            if (this.hasMoreElements()) {
                sb.append(delim);
            }
        }
        
        return sb.toString();
    }
}