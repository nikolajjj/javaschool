package unit;

import com.tsystems.Util.DateUtil;
import com.tsystems.dto.DriverShiftDTO;
import com.tsystems.entity.DriverShift;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class WorkingHoursTest {
    @Test
    public void testWorkingHours() {
        Date end = new Date();
        Date begin = new Date(System.currentTimeMillis() - 3600 * 1000);
        double worked = DateUtil.diffInHours(begin, end);
        double expected = 1d;

        Assert.assertEquals(expected, worked, 0.1);
    }
}
