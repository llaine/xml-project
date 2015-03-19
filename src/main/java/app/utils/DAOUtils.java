package app.utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * projetXML
 *
 * @author llaine
 * @package app.utils
 */
public class DAOUtils {

    private AtomicInteger count = new AtomicInteger(0);

    private void incrementCount() {
        count.incrementAndGet();
    }

    public Long getCount() {
        this.incrementCount();
        return count.longValue();
    }

    /**
     * Get a random long between 0 and 100000
     * @return
     */
    public Long getRandomLong(){
        long LOWER_RANGE = 0;
        long UPPER_RANGE = 1000000;
        Random random = new Random();
        return LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
    }
}
