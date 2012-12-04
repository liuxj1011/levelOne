package cn.edu.buaa.park;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 停车场经理类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-12-4
 * Time: 下午1:05
 * To change this template use File | Settings | File Templates.
 */
public class ParkManager {
	// 停车场
	private Park park;
	// 所管理的停车BOY列表
	private List<ParkBoy> parkBoyList = new ArrayList<ParkBoy>();

	/**
	 * 管理停车场
	 * @param park	停车场
	 */
	public void handlePark(Park park) {
		this.park = park;
	}

	/**
	 * 管理停车BOY
	 * @param boy	停车BOY
	 */
	public void handleBoy(ParkBoy boy) {
		this.parkBoyList.add(boy);
	}
	
	/**
     * 停车
     * @param car   车
     * @return  停车票据
     */
	public Ticket in(Car car) {
		if(isFull()) {
			throw new ParkException("停车场已满，不能停车了。");
		}
		int num = this.parkBoyList.size();
		if(this.park != null) {
			num += 1;
		}
		Random random = new Random();
		while(true) {
			int i = random.nextInt(num);
			if(i == 0 && !this.park.isFull()) {
				return park.in(car);
			} else if(i > 0) {
				ParkBoy boy = this.parkBoyList.get(i - 1);
				if(!boy.isFull()) {
					return boy.in(car);
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
	}
	
	/**
	 * 取车
	 * @param ticket	停车票据
	 * @return	车
	 */
	public Car out(Ticket ticket) {
		if(this.park != null && this.park.contain(ticket)) {
			return this.park.out(ticket);
		}
		for(ParkBoy boy : this.parkBoyList) {
			if(boy.contain(ticket)) {
				return boy.out(ticket);
			}
		}
		throw new ParkException("没有你要取的车。");
	}
	
	/**
	 * 判断停车经理所管理的停车场是否已满
	 * @return true-已满 false-未满
	 */
	public boolean isFull() {
		if(park != null && !park.isFull()) {
			return false;
		}
		for(ParkBoy boy : this.parkBoyList) {
			if(!boy.isFull()) {
				return false;
			}
		}
		return true;
	}
	
	/**
     * 根据停车票据判断是否停在这
     * @param ticket	票据ID
     * @return	true-是 false-否
     */
    public boolean contain(Ticket ticket) {
    	if(park != null && park.contain(ticket)) {
			return true;
		}
		for(ParkBoy boy : this.parkBoyList) {
			if(boy.contain(ticket)) {
				return true;
			}
		}
		return false;
    }
	
	/**
	 * 获取所有停车场的空车位数量
	 * @return	空车位数量
	 */
	public int getEmptyNum() {
		int num = 0;
		for(ParkBoy boy : this.parkBoyList) {
			num += boy.getEmptyNum();
		}
		if(this.park != null) {
			num += this.park.getEmptyNum();
		}
		return num;
	}
	
	/**
	 * 获取所有停车场的车位总数
	 * @return	车位总数
	 */
	public int getTotalNum() {
		int num = 0;
		for(ParkBoy boy : this.parkBoyList) {
			num += boy.getTotalNum();
		}
		if(this.park != null) {
			num += this.park.getTotalNum();
		}
		return num;
	}
}