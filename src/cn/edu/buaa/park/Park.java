package cn.edu.buaa.park;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-11
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class Park {
    private List<Car> carList = new ArrayList<Car>();
    private int limitNum;
    public Park(int num) {
          this.limitNum = num;
    }

    public int getNum() {
        return this.limitNum - this.carList.size();
    }

    public void in(Car car) {
        if(this.carList.size() >= this.limitNum) {
            throw new ParkException("停车场已满，不能停车了。");
        }
        this.carList.add(car);
    }

    public void out(Car car) {
        if(this.carList.size() <= 0) {
            throw new ParkException("停车场没有停任何车，不能取车。");
        }
        if(this.carList.contains(car)) {
            this.carList.remove(car);
        } else {
            throw new ParkException("你搞错了，你的车没停在这。");
        }
    }
}
