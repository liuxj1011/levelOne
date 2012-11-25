package cn.edu.buaa.park;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-25
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoy_test {
    private final int FIRST_PARK_SIZE = 5;
    private final int SECOND_PARK_SIZE = 10;
    private ParkBoy parkBoy;

    @Before
    public void init() {
        parkBoy = new ParkBoy();
        parkBoy.handlePark(new Park(this.FIRST_PARK_SIZE));
        parkBoy.handlePark(new Park(this.SECOND_PARK_SIZE));
    }

    @Test
    public void in_a_car_when_first_park_is_not_full() {
        parkBoy.in(new Car());
        Assert.assertEquals(parkBoy.getParkSize(0), this.FIRST_PARK_SIZE - 1);
        Assert.assertEquals(parkBoy.getParkSize(1), this.SECOND_PARK_SIZE);
    }

    @Test
    public void in_a_car_when_first_park_is_full() {
        for(int i = 0; i <= 5; i ++) {
            parkBoy.in(new Car());
        }
        Assert.assertEquals(parkBoy.getParkSize(1), this.SECOND_PARK_SIZE - 1);
    }

    @Test
    public void out_a_car_when_it_parking_first_park() {
        Car car = new Car();
        Ticket ticket = parkBoy.in(car);
        Assert.assertSame(car, parkBoy.out(ticket));
    }

    @Test
    public void out_a_car_when_it_parking_second_park() {
        for(int i = 0; i < 5; i ++) {
            parkBoy.in(new Car());
        }
        Car car = new Car();
        Ticket ticket = parkBoy.in(car);
        Assert.assertEquals(parkBoy.getParkSize(1), this.SECOND_PARK_SIZE - 1);
        Assert.assertSame(car, parkBoy.out(ticket));
    }

    @Test(expected = ParkException.class)
    public void out_a_car_when_all_park_is_empty() {
        parkBoy.out(new Ticket());
    }

    @Test(expected = ParkException.class)
    public void in_a_car_when_all_park_is_full() {
        for(int i = 0; i <= 15; i ++) {
            parkBoy.in(new Car());
        }
    }

}
