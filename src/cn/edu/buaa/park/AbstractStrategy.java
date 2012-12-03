package cn.edu.buaa.park;

/**
 * 策略抽象类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-12-3
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractStrategy implements Strategy {
    protected ParkBoy parkBoy;

    @Override
    public void setParkBoy(ParkBoy parkBoy) {
        this.parkBoy = parkBoy;
    }

    @Override
    public abstract Ticket in(Car car);
}
