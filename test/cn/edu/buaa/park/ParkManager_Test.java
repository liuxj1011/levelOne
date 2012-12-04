package cn.edu.buaa.park;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * 停车场经理测试类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-25
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
public class ParkManager_Test {
	private ParkManager manager;
	
	@Before
	public void init() {
		this.manager = new ParkManager();
		this.manager.handlePark(new Park(8));
		ParkBoy boyA = new ParkBoy();
		boyA.handlePark(new Park(5));
		boyA.handlePark(new Park(9));
		this.manager.handleBoy(boyA);
		ParkBoy boyB = new ParkBoy();
		boyB.handlePark(new Park(4));
		boyB.handlePark(new Park(7));
		this.manager.handleBoy(boyB);
	}
	
	@Test
	public void test_empty_num_when_in_thirty_three_car() {
        for(int i = 0; i < 33; i ++) {
            manager.in(new Car());
            Assert.assertEquals(manager.getEmptyNum(), 33 - i - 1);
        }
    }
	
	@Test(expected = ParkException.class)
	public void in_a_car_when_all_park_is_full() {
        for(int i = 0; i < 34; i ++) {
            manager.in(new Car());
        }
    }
	
	@Test
	public void out_a_car_when_in_a_car() {
		Car car = new Car();
		Ticket ticket = manager.in(car);
		Assert.assertSame(manager.out(ticket), car);
	}
}