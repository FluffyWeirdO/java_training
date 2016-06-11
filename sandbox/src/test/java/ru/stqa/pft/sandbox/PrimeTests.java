package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

    @Test
    public void testPrime() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test
    public void testNotPrime() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }

    //    @Test
    public void testPrimeLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }


    //    @Test
    public void testPrimesWhile() {
        Assert.assertFalse(Primes.isPrimeWhile(Integer.MAX_VALUE));
    }

    //    @Test
    public void testNotPrimesWhile() {
        Assert.assertFalse(Primes.isPrimeWhile(Integer.MAX_VALUE - 2));
    }
}
