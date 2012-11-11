package cn.edu.buaa.park;

import junit.framework.Assert;
import org.junit.Test;

import javax.smartcardio.Card;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-11
 * Time: 下午3:06
 * To change this template use File | Settings | File Templates.
 */
public class Park_test {
    @Test
    public void num_should_be_sub_1_when_in_a_car() {
        final int num = 20;
        Park park = new Park(num);
        Car car = new Car();
        park.in(car);
        Assert.assertEquals(park.getNum(), num - 1);
    }

    @Test
    public void num_should_be_add_1_when_out_a_car() {
        final int num = 20;
        Park park = new Park(num);
        Car car = new Car();
        park.in(car);
        park.out(car);
        Assert.assertEquals(park.getNum(), num);
    }

    @Test(expected = ParkException.class)
    public void in_a_car_when_park_is_full() {
        final int num = 20;
        Park park = new Park(num);
        for(int i = 0; i <= num; i ++) {
            Car car = new Car();
            park.in(car);
        }
    }

    @Test(expected = ParkException.class)
    public void out_a_car_when_park_is_empty() {
        final int num = 20;
        Park park = new Park(num);
        Car car = new Car();
        park.out(car);
    }
}
