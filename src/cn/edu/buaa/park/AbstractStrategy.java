package cn.edu.buaa.park;

import java.util.List;

/**
 * 停车策略抽象类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-12-3
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractStrategy implements Strategy {
    public abstract Ticket in(Car car, List<Park> parkList);
}
