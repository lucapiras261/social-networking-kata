package com.lucapiras.snk.utils.time;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author Luca Piras
 */
@Component
public class TimeAgoFormatter implements ITimeAgoFormatter {

    private static final int SECOND_MILLISECONDS = 1000;
    private static final int MINUTE_MILLISECONDS = 60 * SECOND_MILLISECONDS;
    private static final int HOUR_MILLISECONDS = 60 * MINUTE_MILLISECONDS;
    private static final int DAY_MILLISECONDS = 24 * HOUR_MILLISECONDS;

    @Override
    public String formatTimeAgo(Date now, Date timestamp) {

        String result = null;
        
        if (null != now && null != timestamp) {
            
            long difference = (now.getTime() - timestamp.getTime());

            if (difference > 0) {
                if (difference < MINUTE_MILLISECONDS) {
                    result = this.buildFormat(difference / SECOND_MILLISECONDS, "seconds");
                } else if (difference < 50 * MINUTE_MILLISECONDS) {
                    result = this.buildFormat(difference / MINUTE_MILLISECONDS, "minutes");
                } else if (difference < 24 * HOUR_MILLISECONDS) {
                    result = this.buildFormat(difference / HOUR_MILLISECONDS, "hours");
                } else {
                    result = this.buildFormat(difference / DAY_MILLISECONDS, "days");
                }
            }
        }
        
        return result;
    }
    
    protected String buildFormat(long count, String measure) {
        if (count == 1) {
            measure = measure.substring(0, measure.length() - 1);
        }
        
        StringBuilder sb = new StringBuilder("(");
        sb.append(count).append(" ").append(measure).append(" ").append("ago");
        sb.append(")");
        
        return sb.toString();
    }
}