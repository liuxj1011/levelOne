package cn.edu.buaa.park;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-25
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoy_Test {
    private ParkBoy parkBoy;

    /**
     * 初始化数据
     */
    @Before
    public void init() {
        parkBoy = new ParkBoy();
        parkBoy.handlePark(new Park(5));
        parkBoy.handlePark(new Park(10));
    }

    /**
     * 使用默认策略停15辆车，测试每停一辆车时每个停车场的空车位情况
     */
    @Test
    public void in_fifteen_car_when_default_strategy() {
        for(int i = 0; i < 15; i ++) {
            parkBoy.in(new Car(String.valueOf(i)));
            switch(i) {
                case 0:
                    Assert.assertEquals(parkBoy.getParkSize(0), 4);
                    Assert.assertEquals(parkBoy.getParkSize(1), 10);
                    break;
                case 1:
                    Assert.assertEquals(parkBoy.getParkSize(0), 3);
                    Assert.assertEquals(parkBoy.getParkSize(1), 10);
                    break;
                case 2:
                    Assert.assertEquals(parkBoy.getParkSize(0), 2);
                    Assert.assertEquals(parkBoy.getParkSize(1), 10);
                    break;
                case 3:
                    Assert.assertEquals(parkBoy.getParkSize(0), 1);
                    Assert.assertEquals(parkBoy.getParkSize(1), 10);
                    break;
                case 4:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 10);
                    break;
                case 5:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 9);
                    break;
                case 6:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 8);
                    break;
                case 7:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 7);
                    break;
                case 8:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 6);
                    break;
                case 9:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 5);
                    break;
                case 10:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 4);
                    break;
                case 11:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 3);
                    break;
                case 12:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 2);
                    break;
                case 13:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 1);
                    break;
                case 14:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 0);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 使用默认策略停15辆车，测试每停一辆车时停车BOY的空车位情况
     */
    @Test
    public void test_empty_num_when_in_fifteen_car_and_default_strategy() {
        for(int i = 0; i < 15; i ++) {
            parkBoy.in(new Car(String.valueOf(i)));
            Assert.assertEquals(parkBoy.getEmptyNum(), 15 - i - 1);
        }
    }

    /**
     * 取一辆车当它停在第一个停车场
     */
    @Test
    public void out_a_car_when_it_parking_first_park() {
        Car car = new Car("1");
        Ticket ticket = parkBoy.in(car);
        Assert.assertSame(car, parkBoy.out(ticket));
    }

    /**
     * 取一辆车当它停在第二个停车场
     */
    @Test
    public void out_a_car_when_it_parking_second_park() {
        for(int i = 0; i < 5; i ++) {
            parkBoy.in(new Car(String.valueOf(i)));
        }
        Car car = new Car("5");
        Ticket ticket = parkBoy.in(car);
        Assert.assertEquals(parkBoy.getParkSize(1), 9);
        Assert.assertSame(car, parkBoy.out(ticket));
    }

    /**
     * 当停车BOY所有停车场为空时，取车失败
     */
    @Test(expected = ParkException.class)
    public void out_a_car_when_all_park_is_empty() {
        parkBoy.out(new Ticket());
    }

    /**
     * 当停车BOY所有停车场已满时，停车失败
     */
    @Test(expected = ParkException.class)
    public void in_a_car_when_all_park_is_full() {
        for(int i = 0; i <= 15; i ++) {
            parkBoy.in(new Car(String.valueOf(i)));
        }
    }

    /**
     * 使用平均策略停15辆车，测试每停一辆车时每个停车场的空车位情况
     */
    @Test
    public void in_fifteen_car_when_average_strategy() {
        parkBoy.setStrategy(StrategyFactory.getStrategy("average"));
        for(int i = 0; i < 15; i ++) {
            parkBoy.in(new Car(String.valueOf(i)));
            switch(i) {
                case 0:
                    Assert.assertEquals(parkBoy.getParkSize(0), 4);
                    Assert.assertEquals(parkBoy.getParkSize(1), 10);
                    break;
                case 1:
                    Assert.assertEquals(parkBoy.getParkSize(0), 4);
                    Assert.assertEquals(parkBoy.getParkSize(1), 9);
                    break;
                case 2:
                    Assert.assertEquals(parkBoy.getParkSize(0), 3);
                    Assert.assertEquals(parkBoy.getParkSize(1), 9);
                    break;
                case 3:
                    Assert.assertEquals(parkBoy.getParkSize(0), 3);
                    Assert.assertEquals(parkBoy.getParkSize(1), 8);
                    break;
                case 4:
                    Assert.assertEquals(parkBoy.getParkSize(0), 2);
                    Assert.assertEquals(parkBoy.getParkSize(1), 8);
                    break;
                case 5:
                    Assert.assertEquals(parkBoy.getParkSize(0), 2);
                    Assert.assertEquals(parkBoy.getParkSize(1), 7);
                    break;
                case 6:
                    Assert.assertEquals(parkBoy.getParkSize(0), 1);
                    Assert.assertEquals(parkBoy.getParkSize(1), 7);
                    break;
                case 7:
                    Assert.assertEquals(parkBoy.getParkSize(0), 1);
                    Assert.assertEquals(parkBoy.getParkSize(1), 6);
                    break;
                case 8:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 6);
                    break;
                case 9:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 5);
                    break;
                case 10:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 4);
                    break;
                case 11:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 3);
                    break;
                case 12:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 2);
                    break;
                case 13:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 1);
                    break;
                case 14:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 0);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 使用平均策略停15辆车，测试每停一辆车时停车BOY的空车位情况
     */
    @Test
    public void test_empty_num_when_in_fifteen_car_and_average_strategy() {
        parkBoy.setStrategy(StrategyFactory.getStrategy("average"));
        for(int i = 0; i < 15; i ++) {
            parkBoy.in(new Car(String.valueOf(i)));
            Assert.assertEquals(parkBoy.getEmptyNum(), 15 - i - 1);
        }
    }

    /**
     * 使用空置率策略停15辆车，测试每停一辆车时每个停车场的空车位情况
     */
    @Test
    public void in_fifteen_car_when_rate_strategy() {
        parkBoy.setStrategy(StrategyFactory.getStrategy("vacancyRate"));
        for(int i = 0; i < 15; i ++) {
            parkBoy.in(new Car(String.valueOf(i)));
            switch(i) {
                case 0:
                    Assert.assertEquals(parkBoy.getParkSize(0), 4);
                    Assert.assertEquals(parkBoy.getParkSize(1), 10);
                    break;
                case 1:
                    Assert.assertEquals(parkBoy.getParkSize(0), 4);
                    Assert.assertEquals(parkBoy.getParkSize(1), 9);
                    break;
                case 2:
                    Assert.assertEquals(parkBoy.getParkSize(0), 4);
                    Assert.assertEquals(parkBoy.getParkSize(1), 8);
                    break;
                case 3:
                    Assert.assertEquals(parkBoy.getParkSize(0), 3);
                    Assert.assertEquals(parkBoy.getParkSize(1), 8);
                    break;
                case 4:
                    Assert.assertEquals(parkBoy.getParkSize(0), 3);
                    Assert.assertEquals(parkBoy.getParkSize(1), 7);
                    break;
                case 5:
                    Assert.assertEquals(parkBoy.getParkSize(0), 3);
                    Assert.assertEquals(parkBoy.getParkSize(1), 6);
                    break;
                case 6:
                    Assert.assertEquals(parkBoy.getParkSize(0), 2);
                    Assert.assertEquals(parkBoy.getParkSize(1), 6);
                    break;
                case 7:
                    Assert.assertEquals(parkBoy.getParkSize(0), 2);
                    Assert.assertEquals(parkBoy.getParkSize(1), 5);
                    break;
                case 8:
                    Assert.assertEquals(parkBoy.getParkSize(0), 2);
                    Assert.assertEquals(parkBoy.getParkSize(1), 4);
                    break;
                case 9:
                    Assert.assertEquals(parkBoy.getParkSize(0), 1);
                    Assert.assertEquals(parkBoy.getParkSize(1), 4);
                    break;
                case 10:
                    Assert.assertEquals(parkBoy.getParkSize(0), 1);
                    Assert.assertEquals(parkBoy.getParkSize(1), 3);
                    break;
                case 11:
                    Assert.assertEquals(parkBoy.getParkSize(0), 1);
                    Assert.assertEquals(parkBoy.getParkSize(1), 2);
                    break;
                case 12:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 2);
                    break;
                case 13:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 1);
                    break;
                case 14:
                    Assert.assertEquals(parkBoy.getParkSize(0), 0);
                    Assert.assertEquals(parkBoy.getParkSize(1), 0);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 使用空置率策略停15辆车，测试每停一辆车时停车BOY的空车位情况
     */
    @Test
    public void test_empty_num_when_in_fifteen_car_and_rate_strategy() {
        parkBoy.setStrategy(StrategyFactory.getStrategy("vacancyRate"));
        for(int i = 0; i < 15; i ++) {
            parkBoy.in(new Car(String.valueOf(i)));
            Assert.assertEquals(parkBoy.getEmptyNum(), 15 - i - 1);
        }
    }
}
