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
    /**
     * 停车
     * @param car   车
     * @return  停车票据
     */
    public Ticket in(Car car);

    /**
     * 设置该策略所属的停车BOY
     * @param parkBoy   停车BOY
     */
    public void setParkBoy(ParkBoy parkBoy);
}
