package com.lucapiras.snk.utils.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca Piras
 */
public class TimeAgoFormatterTest {
    
    /**
     * Test of formatTimeAgo method, of class TimeAgoFormatter.
     */
    @Test
    public void testFormatTimeAgo() throws ParseException {
        
        TimeAgoFormatter instance = new TimeAgoFormatter();
        
        Date now = null;
        Date timestamp = null;
        String result = instance.formatTimeAgo(now, timestamp);
        assertEquals(null, result);
        
        now = new Date();
        timestamp = null;
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals(null, result);
        
        now = null;
        timestamp = new Date();        
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals(null, result);
        
        now = new Date();
        timestamp = now;        
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals(null, result);
        
        now = new Date();
        timestamp = new Date();
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals(null, result);
        
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String day = "04/06/2018 ";
        now = formatter.parse(day + "19:00:00");
        
        timestamp = formatter.parse(day + "18:59:59");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(1 second ago)", result);
        
        timestamp = formatter.parse(day + "18:59:37");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(23 seconds ago)", result);
        
        timestamp = formatter.parse(day + "18:59:01");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(59 seconds ago)", result);
        
        timestamp = formatter.parse(day + "18:59:00");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(1 minute ago)", result);
        
        timestamp = formatter.parse(day + "18:58:59");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(1 minute ago)", result);
        
        timestamp = formatter.parse(day + "18:58:01");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(1 minute ago)", result);
        
        timestamp = formatter.parse(day + "18:58:00");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(2 minutes ago)", result);
        
        timestamp = formatter.parse(day + "18:00:00");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(1 hour ago)", result);
        
        timestamp = formatter.parse(day + "16:00:00");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(3 hours ago)", result);
        
        day = "03/06/2018 ";
        
        timestamp = formatter.parse(day + "19:00:01");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(23 hours ago)", result);
        
        timestamp = formatter.parse(day + "19:00:00");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(1 day ago)", result);
        
        day = "02/06/2018 ";
        
        timestamp = formatter.parse(day + "19:00:01");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(1 day ago)", result);
        
        timestamp = formatter.parse(day + "19:00:00");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(2 days ago)", result);
        
        timestamp = formatter.parse(day + "16:00:00");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(2 days ago)", result);
        
        day = "01/06/2018 ";
        
        timestamp = formatter.parse(day + "16:00:00");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(3 days ago)", result);
        
        day = "25/05/2018 ";
        
        timestamp = formatter.parse(day + "16:00:00");
        result = instance.formatTimeAgo(now, timestamp);
        assertEquals("(10 days ago)", result);
    }
    
}
