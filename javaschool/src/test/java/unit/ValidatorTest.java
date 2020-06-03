package unit;

import com.tsystems.controller.validation.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorTest {
    @Test
    public void isValidPlate1() {
        Assert.assertTrue(Validator.isPlateValid("AA00001"));
    }

    @Test
    public void isValidPlate2() {
        Assert.assertTrue(Validator.isPlateValid("Aa00001"));
    }

    @Test
    public void isValidPlate3() {
        Assert.assertTrue(Validator.isPlateValid("AA00001"));
    }

    @Test
    public void isValidPlate4() {
        Assert.assertTrue(Validator.isPlateValid("pp00001"));
    }

    @Test
    public void isValidPlate5() {
        Assert.assertTrue(Validator.isPlateValid("aa00000"));
    }

    @Test
    public void isValidPlate6() {
        Assert.assertTrue(Validator.isPlateValid("sD75015"));
    }

    @Test
    public void isValidPlate7() {
        Assert.assertTrue(Validator.isPlateValid("dd55514"));
    }

    @Test
    public void isValidPlate8() {
        Assert.assertTrue(Validator.isPlateValid("as77777"));
    }
}
