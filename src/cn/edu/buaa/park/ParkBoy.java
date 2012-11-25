package cn.edu.buaa.park;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-25
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoy {
    private List<Park> parkList = new ArrayList<Park>();

    public void handlePark(Park park) {
        this.parkList.add(park);
    }

    public Ticket in(Car car) {
        for(Park park : this.parkList) {
            if (!park.isFull()) {
                return park.in(car);
            }
        }
        throw new ParkException("没有空的停车位！");
    }

    public int getParkSize(int no) {
        if(no >= this.parkList.size()) {
            throw new ParkException("找不到你需要的停车场！");
        }
        return this.parkList.get(no).getNum();
    }

    public Car out(Ticket ticket) {
        Car car = null;
        for(Park park : this.parkList) {
            try{
                if((car = park.out(ticket)) != null) {
                    return car;
                }
            } catch (ParkException e) {
            }
            if(car != null) {
                break;
            }
        }
        throw new ParkException("没有您要取的车！");
    }
}
