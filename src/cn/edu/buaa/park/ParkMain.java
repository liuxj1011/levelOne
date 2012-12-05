package cn.edu.buaa.park;

import java.util.ArrayList;
import java.util.List;

/**
 * 停车场主函数
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-12-04
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class ParkMain extends ParkOperator{
	private List<ParkManager> managerList = new ArrayList<ParkManager>();
	private List<ParkBoy> boyList = new ArrayList<ParkBoy>();
	private List<Park> parkList = new ArrayList<Park>();
	
	private ParkMain(){
		this.menuList.add("0:作为停车场主管进入；");
		this.menuList.add("1:作为停车场经理进入；");
		this.menuList.add("2:作为停车BOY进入；");
	}
	@Override
	public void run(String input) {
		ParkOperator operator = null;
		if("0".equals(input)) {
			operator = new ParkDirector();
		} else if("1".equals(input)) {
			operator = selectManager();
		} else if("2".equals(input)) {
			operator = selectBoy();
		} else {
			System.out.println("错误的输入！");
			return;
		}
		if(operator == null) {
			return;
		}
		operator.preOperator = this;
		ParkOperator.setOperator(operator);
	}
	
	/**
	 * 获取停车场经理列表
	 */
	public List<ParkManager> getManagerList() {
		return managerList;
	}

	/**
	 * 增加一个停车场经理
	 */
	public void addManager(ParkManager manager) {
		this.managerList.add(manager);
	}

	/**
	 * 获取停车BOY列表
	 */
	public List<ParkBoy> getBoyList() {
		return boyList;
	}

	/**
	 * 增加停车BOY
	 */
	public void addBoy(ParkBoy boy) {
		this.boyList.add(boy);
	}

	/**
	 * 获取停车场列表
	 */
	public List<Park> getParkList() {
		return parkList;
	}

	/**
	 * 增加停车场
	 */
	public void addPark(Park park) {
		this.parkList.add(park);
	}
	
	/**
	 * 选取一个停车场经理
	 */
	public ParkManager selectManager() {
		if(getManagerList().size() > 0) {
			System.out.println("-----------------------------------------");
			for(int i = 0; i < getManagerList().size(); i ++) {
				ParkManager manager = getManagerList().get(i);
				System.out.println(i + "." + manager.getCode());
			}
			System.out.println("-----------------------------------------");
			System.out.println("请选择一个经理：");
			int managerNum = super.readInputNum();
			while(managerNum >= getManagerList().size()) {
				System.out.println("你输入的经理不存在，请重新选择：");
				managerNum = super.readInputNum();
			}
			return getManagerList().get(managerNum);
		} else {
			System.out.println("当前没有创建任何经理！");
		}
		return null;
	}
	
	/**
	 * 选取一个停车场
	 */
	public Park selectPark() {
		if(getParkList().size() > 0) {
			System.out.println("-----------------------------------------");
			for(int i = 0; i < getParkList().size(); i ++) {
				Park park = getParkList().get(i);
				System.out.println(i + "." + park.getCode());
			}
			System.out.println("-----------------------------------------");
			System.out.println("请选择一个停车场：");
			int parkNum = super.readInputNum();
			while(parkNum >= getParkList().size()) {
				System.out.println("你输入的停车场不存在，请重新选择：");
				parkNum = super.readInputNum();
			}
			return getParkList().get(parkNum);
		} else {
			System.out.println("当前没有创建任何停车场！");
		}
		return null;
	}
	
	/**
	 * 选取一个停车BOY
	 */
	public ParkBoy selectBoy() {
		if(getBoyList().size() > 0) {
			System.out.println("-----------------------------------------");
			for(int i = 0; i < getBoyList().size(); i ++) {
				ParkBoy boy = getBoyList().get(i);
				System.out.println(i + "." + boy.getCode());
			}
			System.out.println("-----------------------------------------");
			System.out.println("请选择一个停车BOY：");
			int boyNum = super.readInputNum();
			while(boyNum >= getBoyList().size()) {
				System.out.println("你输入的停车BOY不存在，请重新选择：");
				boyNum = super.readInputNum();
			}
			return getBoyList().get(boyNum);
		} else {
			System.out.println("当前没有创建任何停车BOY！");
		}
		return null;
	}


	private static ParkMain main;
	
	public static ParkMain getInstance() {
		if(main == null) {
			main = new ParkMain();
		}
		return main;
	}
	
	public static void welcome() {
		System.out.println("=========================================");
		System.out.println("欢迎使用【停车场系统】");
		System.out.println("北京航空航天大学软件学院——一级工程实践考试");
		System.out.println("");
		System.out.println("刘小军(GS1221532)");
		System.out.println("齐  琦(GS1221323)");
		System.out.println("=========================================");
	}
	
	public static void main(String[] args) {
		welcome();
		ParkMain main = ParkMain.getInstance();
		ParkOperator.setOperator(main);
		while(true) {
			ParkOperator.getOperator().printMenu();
		}
	}
}