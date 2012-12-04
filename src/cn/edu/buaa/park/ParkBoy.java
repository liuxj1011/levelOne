package cn.edu.buaa.park;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车BOY类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-25
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
public class ParkBoy {
    // 管理的停车场列表
    private List<Park> parkList = new ArrayList<Park>();
    // 停车策略
    private Strategy strategy;

    /**
     * 停车BOY构造器
     * @param strategy  停车策略
     */
    public ParkBoy(Strategy strategy) {
        setStrategy(strategy);
    }

    /**
     * 停车BOY构造器
     */
    public ParkBoy() {
        setStrategy(new DefaultStrategy());
    }

    /**
     * 设置停车BOY的停车策略
     * @param strategy  停车策略
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 管理一个停车场
     * @param park  停车场
     */
    public void handlePark(Park park) {
        this.parkList.add(park);
    }

    /**
     * 停车
     * @param car   车
     * @return  停车票据
     */
    public Ticket in(Car car) {
        return strategy.in(car, getParkList());
    }

    /**
     * 取车
     * @param ticket    停车票据
     * @return  车
     */
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

	/**
	 * 判断所管理的停车场是否已满
	 * @return true-已满 false-未满
	 */
	public boolean isFull() {
		for(Park park : this.parkList) {
			if(!park.isFull()) {
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
    	for(Park park : this.parkList) {
			if(park.contain(ticket)) {
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
		List<Park> list = getParkList();
		int num = 0;
		for(Park park : list) {
			num += park.getEmptyNum();
		}
		return num;
	}
	
	/**
	 * 获取所有停车场的车位总数
	 * @return	车位总数
	 */
	public int getTotalNum() {
		List<Park> list = getParkList();
		int num = 0;
		for(Park park : list) {
			num += park.getTotalNum();
		}
		return num;
	}
	
	/**
     * 获取指定停车场的空车位（该方法为测试专用，其他地方不要使用）
     * @param no    停车场序号
     * @return  停车场空车位
     */
    public int getParkSize(int no) {
        if(no >= this.parkList.size()) {
            throw new ParkException("找不到你需要的停车场！");
        }
        return this.parkList.get(no).getEmptyNum();
    }
	
	/**
     * 获取停车场列表
     * @return  停车场列表
     */
    private List<Park> getParkList() {
    	List<Park> list = new ArrayList<Park>();
    	list.addAll(this.parkList);
        return list;
    }
}
