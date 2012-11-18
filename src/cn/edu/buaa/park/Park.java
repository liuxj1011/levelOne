package cn.edu.buaa.park;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-11
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class Park {
    private Map<Ticket, Car> carList = new HashMap<Ticket, Car>();
    private int limitNum;
    public Park(int num) {
          this.limitNum = num;
    }

    public int getNum() {
        return this.limitNum - this.carList.size();
    }

    public Ticket in(Car car) {
        if(this.carList.size() >= this.limitNum) {
            throw new ParkException("停车场已满，不能停车了。");
        }
        Ticket ticket = new Ticket();
        this.carList.put(ticket, car);
        return ticket;
    }

    public Car out(Ticket ticket) {
        if(this.carList.containsKey(ticket)) {
            return this.carList.remove(ticket);
        }
        throw new ParkException("没有你要取的车。");
    }
}
