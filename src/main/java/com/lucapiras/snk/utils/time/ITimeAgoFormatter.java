package com.lucapiras.snk.utils.time;

import java.util.Date;

/**
 *
 * @author Luca Piras
 */
public interface ITimeAgoFormatter {
    
    public String formatTimeAgo(Date now, Date timestamp);
    
}