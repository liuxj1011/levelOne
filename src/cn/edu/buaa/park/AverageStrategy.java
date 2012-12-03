package cn.edu.buaa.park;

/**
 * 平均策略，每一个停车场各停一辆车
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-12-2
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class AverageStrategy extends AbstractStrategy implements Strategy {

    @Override
    public Ticket in(Car car) {
        int no = -1;
        int num = -1;
        for(int i = 0; i < parkBoy.getParkList().size(); i ++) {
            Park park = parkBoy.getParkList().get(i);
            if(park.isFull()) {
                continue;
            }
            if(num < 0) {
                num = park.getLimitNum() - park.getNum();
                no = i;
            }
            if((park.getLimitNum() - park.getNum()) < num) {
                num = park.getLimitNum() - park.getNum();
                no = i;
            }
        }
        if(no > -1) {
            return parkBoy.getParkList().get(no).in(car);
        }
        throw new ParkException("没有空的停车位！");
    }
}