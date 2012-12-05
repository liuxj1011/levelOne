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
public class ParkManager extends ParkOperator{
	// 停车场
	private Park park;
	// 所管理的停车BOY列表
	private List<ParkBoy> parkBoyList = new ArrayList<ParkBoy>();
	// 经理编号
	private String code;

	/**
	 * 停车场经理构造器
	 * @param code
	 */
	public ParkManager(String code) {
		this.code = code;
		initMenu();
	}

	/**
	 * 默认构造器
	 */
	public ParkManager() {
		initMenu();
	}

	/**
	 * 管理停车场
	 * @param park	停车场
	 */
	public void handlePark(Park park) {
		park.setManaged(true);
		this.park = park;
	}

	/**
	 * 管理停车BOY
	 * @param boy	停车BOY
	 */
	public void handleBoy(ParkBoy boy) {
		boy.setManaged(true);
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
		if(!parkIsNull() && this.park.contain(ticket)) {
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
		if(!parkIsNull() && !park.isFull()) {
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
    	if(!parkIsNull() && park.contain(ticket)) {
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

	/**
	 * 获取经理编号
	 * @return	经理编号
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 判断所管理的停车场是否为空
	 * @return	true空 false非空
	 */
	public boolean parkIsNull() {
		return this.park == null;
	}
	
	/**
	 * 判断停车BOY是否为该经理所管理
	 * @return	true空 false非空
	 */
	public boolean containBoy(ParkBoy boy) {
		return this.parkBoyList.contains(boy);
	}
	
	/**
	 * 获取所管理的停车BOY列表
	 * @return	所管理的停车BOY列表
	 */
	public List<ParkBoy> getParkBoyList() {
		return parkBoyList;
	}
	
	/**
	 * 初始化菜单
	 */
	public void initMenu() {
		this.menuList.add("0.停车；");
		this.menuList.add("1.取车；");
		this.menuList.add("2.查看管理的所有停车场信息；");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkManager other = (ParkManager) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public void run(String input) {
		if("0".equals(input)) {
			inCar();
		} else if("1".equals(input)) {
			outCar();
		} else if("2".equals(input)) {
			printParkInfo();
		} else {
			System.out.println("错误的输入！");
			return;
		}
	}
	
	/**
	 * 停车
	 */
	private void inCar() {
		if(isFull()) {
			System.out.println("停车场已满，你还是找找其他停车场吧！");
			return;
		}
		System.out.println("请输入你停的车的车牌号：");
		String input = super.readInput();
		if(contain(new Ticket(input))) {
			System.out.println("你要停的车已经停在我们的停车场里了，你的是套牌车吧？你还不走... 110... 嘟—— 嘟——");
			return;
		}
		Ticket ticket = in(new Car(input));
		System.out.println("OK，你的车停好了，请记住你的取车票据号[" + ticket.getCode() + "]，凭此票据号取车。");
	}
	
	/**
	 * 取车
	 */
	private void outCar() {
		System.out.println("请输入你的取车票据号：");
		String input = super.readInput();
		if(!contain(new Ticket(input))) {
			System.out.println("我们这没有停你的车，你是不是记错了。");
			return;
		}
		Car car = out(new Ticket(input));
		System.out.println("OK，你的车交给你了，车牌是[" + car.getCode() + "]，欢迎下次光临。");
	}
	
	/**
	 * 打印停车场信息
	 */
	private void printParkInfo() {
		if(park == null && getParkBoyList().size() == 0) {
			System.out.println("目前没有管理任何停车场！");
			return;
		}
		int emptyNum = 0;
		int totalNum = 0;
		if(park != null) {
			System.out.println("   停车场编号：" + park.getCode());
			System.out.println("      车位数：" + park.getTotalNum());
			System.out.println("      空位数：" + park.getEmptyNum());
			emptyNum += park.getEmptyNum();
			totalNum += park.getTotalNum();
		}
		for(int i = 0; i < getParkBoyList().size(); i ++) {
			ParkBoy boy = getParkBoyList().get(i);
			System.out.println("   停车BOY编号：" + boy.getCode());
			boy.printParkInfo();
			emptyNum += boy.getEmptyNum();
			totalNum += boy.getTotalNum();
		}
		System.out.println("   共计车位数：" + totalNum);
		System.out.println("   共计空位数：" + emptyNum);
	}
}