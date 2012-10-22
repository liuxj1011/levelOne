package cn.edu.buaa.guessnumber;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-10-22
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 */
public class GuessNumber_Test {

    @Test
    public void should_be_return_1a0b() {
        Assert.assertEquals("1a0b", new GuessNumber("1234").validate("1567"));
        Assert.assertEquals("1a0b", new GuessNumber("1234").validate("5267"));
        Assert.assertEquals("1a0b", new GuessNumber("1234").validate("5637"));
        Assert.assertEquals("1a0b", new GuessNumber("1234").validate("5674"));
    }

    @Test
    public void should_be_return_2a0b() {
        Assert.assertEquals("2a0b", new GuessNumber("1234").validate("1256"));
        Assert.assertEquals("2a0b", new GuessNumber("1234").validate("5634"));
        Assert.assertEquals("2a0b", new GuessNumber("1234").validate("1536"));
        Assert.assertEquals("2a0b", new GuessNumber("1234").validate("5264"));
    }

    @Test
    public void should_be_return_3a0b() {
        Assert.assertEquals("3a0b", new GuessNumber("1234").validate("1235"));
        Assert.assertEquals("3a0b", new GuessNumber("1234").validate("5234"));
        Assert.assertEquals("3a0b", new GuessNumber("1234").validate("1534"));
        Assert.assertEquals("3a0b", new GuessNumber("1234").validate("1254"));
    }

    @Test
    public void should_be_return_4a0b() {
        Assert.assertEquals("4a0b", new GuessNumber("1234").validate("1234"));
    }

    @Test
    public void should_be_return_0a1b() {
        Assert.assertEquals("0a1b", new GuessNumber("1234").validate("4567"));
        Assert.assertEquals("0a1b", new GuessNumber("1234").validate("5367"));
        Assert.assertEquals("0a1b", new GuessNumber("1234").validate("5627"));
        Assert.assertEquals("0a1b", new GuessNumber("1234").validate("5671"));
    }

    @Test
    public void should_be_return_0a2b() {
        Assert.assertEquals("0a2b", new GuessNumber("1234").validate("4167"));
        Assert.assertEquals("0a2b", new GuessNumber("1234").validate("5362"));
        Assert.assertEquals("0a2b", new GuessNumber("1234").validate("4627"));
        Assert.assertEquals("0a2b", new GuessNumber("1234").validate("5621"));
    }

    @Test
    public void should_be_return_0a3b() {
        Assert.assertEquals("0a3b", new GuessNumber("1234").validate("4127"));
        Assert.assertEquals("0a3b", new GuessNumber("1234").validate("5312"));
        Assert.assertEquals("0a3b", new GuessNumber("1234").validate("4127"));
        Assert.assertEquals("0a3b", new GuessNumber("1234").validate("3621"));
    }

    @Test
    public void should_be_return_0a4b() {
        Assert.assertEquals("0a4b", new GuessNumber("1234").validate("4321"));
        Assert.assertEquals("0a4b", new GuessNumber("1234").validate("4123"));
        Assert.assertEquals("0a4b", new GuessNumber("1234").validate("2341"));
        Assert.assertEquals("0a4b", new GuessNumber("1234").validate("2413"));
    }

    @Test
    public void should_be_return_1a1b() {
        Assert.assertEquals("1a1b", new GuessNumber("1234").validate("1358"));
        Assert.assertEquals("1a1b", new GuessNumber("1234").validate("5137"));
        Assert.assertEquals("1a1b", new GuessNumber("1234").validate("3258"));
        Assert.assertEquals("1a1b", new GuessNumber("1234").validate("5724"));
    }

}