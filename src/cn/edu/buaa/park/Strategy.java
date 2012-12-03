package cn.edu.buaa.park;

/**
 * 停车策略接口类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-12-2
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public interface Strategy {
    public Ticket in(Car car);

    public void setParkBoy(ParkBoy parkBoy);
}
