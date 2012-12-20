package cn.edu.buaa.park;

import java.util.HashMap;
import java.util.Map;

/**
 * 停车场类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-11
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class Park {
    // 用来存放票据和车辆的对应关系
    private Map<Ticket, Car> carList = new HashMap<Ticket, Car>();
    // 车位数
    private int totalNum;
    // 停车场编号
    private String code;
    // 是否被管理
    private boolean managed = false;
    
    /**
     * 停车场构造器
     * @param code 停车场编号
     * @param num 车位数
     */
    public Park(String code, int num) {
    	this.code = code;
        this.totalNum = num;
    }

    /**
     * 停车场构造器
     * @param num 车位数
     */
    public Park(int num) {
          this.totalNum = num;
    }

    /**
     * 将一辆车停到停车场
     * @param car   车
     * @return  停车票据
     */
    public Ticket in(Car car) {
        if(isFull()) {
            throw new ParkException("停车场已满，不能停车了。");
        }
        Ticket ticket = new Ticket(car.getCode());
        this.carList.put(ticket, car);
        return ticket;
    }

    /**
     * 将一辆车从停车场取出
     * @param ticket    票据
     * @return  车
     */
    public Car out(Ticket ticket) {
        if(contain(ticket)) {
            return this.carList.remove(ticket);
        }
        throw new ParkException("没有你要取的车。");
    }

    /**
     * 停车场是否已满/停车场是否还有空停车位
     * @return  true-已满 false-未满
     */
    public boolean isFull() {
        return this.carList.size() >= this.totalNum;
    }
    
    /**
     * 根据停车票据判断是否停在这
     * @param ticket	票据ID
     * @return	true-是 false-否
     */
    public boolean contain(Ticket ticket) {
    	return this.carList.containsKey(ticket);
    }
    
    /**
     * 获取空车位数量
     * @return      空车位数量
     */
    public int getEmptyNum() {
        return this.totalNum - this.carList.size();
    }

    /**
     * 获取车位数
     * @return  车位数
     */
    public int getTotalNum() {
        return totalNum;
    }

	/**
	 * 获取停车场编号
	 * @return	停车场编号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置停车场是否被管理
	 * @param managed	true是 false否
	 */
	public void setManaged(boolean managed) {
		this.managed = managed;
	}

	/**
	 * 判断停车场是否被管理了
	 * @return	true是 false否
	 */
	public boolean isManaged() {
		return managed;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Park other = (Park) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}