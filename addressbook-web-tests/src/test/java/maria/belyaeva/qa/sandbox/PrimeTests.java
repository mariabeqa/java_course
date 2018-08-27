package maria.belyaeva.qa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
    @Test
    public void isPrime() {
        Assert.assertTrue(Prime.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void isNonPrime() {
        Assert.assertFalse(Prime.isPrime(Integer.MAX_VALUE-2));
    }

    @Test
    public void isPrimeLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Prime.isPrime(n));
    }

    @Test
    public void isPrimeFast() {
        Assert.assertTrue(Prime.isPrimeFast(Integer.MAX_VALUE));
    }
}
