package cn.edu.buaa.park;

import junit.framework.Assert;
import org.junit.Test;

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
        park.in(new Car());
        Assert.assertEquals(park.getNum(), num - 1);
    }

    @Test
    public void num_should_be_add_1_when_out_a_car() {
        final int num = 20;
        Park park = new Park(num);
        Car car = new Car();
        Ticket ticket = park.in(car);
        Assert.assertEquals(park.getNum(), num - 1);
        park.out(ticket);
        Assert.assertEquals(park.getNum(), num);
    }

    @Test(expected = ParkException.class)
    public void in_a_car_when_park_is_full() {
        final int num = 20;
        Park park = new Park(num);
        for(int i = 0; i <= num; i ++) {
            park.in(new Car());
        }
    }

    @Test(expected = ParkException.class)
    public void out_a_car_when_park_is_empty() {
        Park park = new Park(10);
        park.out(new Ticket());
    }

    @Test
    public void out_by_right_ticket_when_in_a_car() {
        Park park = new Park(10);
        Car car = new Car();
        Ticket ticket = park.in(car);
        Assert.assertSame(car, park.out(ticket));
    }

    @Test(expected = ParkException.class)
    public void out_by_wrong_ticket_when_in_a_car() {
        Park park = new Park(10);
        park.in(new Car());
        park.out(new Ticket());
    }

    @Test(expected = ParkException.class)
    public void out_by_right_ticket_twice_when_in_a_car() {
        Park park = new Park(10);
        Car car = new Car();
        Ticket ticket = park.in(car);
        Assert.assertSame(car, park.out(ticket));
        park.out(ticket);
    }
}