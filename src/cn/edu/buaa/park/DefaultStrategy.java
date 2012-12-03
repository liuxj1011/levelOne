package cn.edu.buaa.park;

import java.util.List;

/**
 * 默认策略，先将第一个停车场停满再停第二个停车场
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-12-2
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public class DefaultStrategy extends AbstractStrategy implements Strategy {
    @Override
    public Ticket in(Car car, List<Park> parkList) {
        for(Park park : parkList) {
            if (!park.isFull()) {
                return park.in(car);
            }
        }
        throw new ParkException("没有空的停车位！");
    }
}
