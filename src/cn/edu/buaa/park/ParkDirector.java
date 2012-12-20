package cn.edu.buaa.park;

import java.util.List;

public class ParkDirector extends ParkOperator{
	public ParkDirector() {
		this.menuList.add("0:显示所有员工信息；");
		this.menuList.add("1:创建停车场经理；");
		this.menuList.add("2:为停车场经理指定管理的停车场；");
		this.menuList.add("3:为停车场经理指定管理的停车BOY；");
		this.menuList.add("4:创建停车BOY；");
		this.menuList.add("5:为停车BOY指定管理的停车场；");
		this.menuList.add("6:创建停车场；");
		this.menuList.add("7:查看所有停车场信息；");
	}

	public void run(String input) {
		if("0".equals(input)) {
			printEmployee();
		} else if("1".equals(input)) {
			createManager();
		} else if("2".equals(input)) {
			managerHandlePark();
		} else if("3".equals(input)) {
			managerHandleBoy();
		} else if("4".equals(input)) {
			createBoy();
		} else if("5".equals(input)) {
			boyHandlePark();
		} else if("6".equals(input)) {
			createPark();
		} else if("7".equals(input)) {
			printParkInfo();
		} else {
			System.out.println("错误的输入！");
		}
	}
	
	/**
	 * 创建经理
	 */
	private void createManager() {
		String input = "";
		while("".equals(input)) {
			System.out.print("请输入经理的编号：");
			input = super.readInput();
		}
		ParkManager manager = new ParkManager(input);
		if(ParkMain.getInstance().getManagerList().contains(manager)) {
			System.out.println("已经存在编号[" + manager.getCode() + "]的停车场经理，不能重复创建！");
			return;
		}
		ParkMain.getInstance().addManager(manager);
		System.out.println("创建停车场经理成功，经理的编号是：" + input);
	}
	
	/**
	 * 创建停车场
	 */
	private void createPark() {
		String input = "";
		while("".equals(input)) {
			System.out.print("请输入停车场的编号：");
			input = super.readInput();
		}
		System.out.print("请输入停车场的车位数：");
		int num = super.readInputNum();
		Park park = new Park(input, num);
		if(ParkMain.getInstance().getParkList().contains(park)) {
			System.out.println("已经存在编号[" + park.getCode() + "]的停车场，不能重复创建！");
			return;
		}
		ParkMain.getInstance().addPark(park);
		System.out.println("创建停车场成功，停车场的编号是：" + input);
	}
	
	/**
	 * 创建停车BOY
	 */
	private void createBoy() {
		String input = "";
		while("".equals(input)) {
			System.out.print("请输入停车BOY的编号：");
			input = super.readInput();
		}
		System.out.println("-----------------------------------------");
		System.out.println("0.默认策略：优先停放在前面的停车场；");
		System.out.println("1.平均策略：优先停放在停车数量较少的停车场；");
		System.out.println("2.空置率策略：优先停放在空置率较高的停车场；");
		System.out.println("-----------------------------------------");
		int num = -1;
		String[] strategys = new String[]{"", "average", "vacancyRate"};
		while(num < 0 || num > 2) {
			System.out.print("请输入停车策略序号：");
			num = super.readInputNum();
		}
		ParkBoy boy = new ParkBoy(input);
		boy.setStrategy(StrategyFactory.getStrategy(strategys[num]));
		if(ParkMain.getInstance().getBoyList().contains(boy)) {
			System.out.println("已经存在编号[" + boy.getCode() + "]的停车BOY，不能重复创建！");
			return;
		}
		ParkMain.getInstance().addBoy(boy);
		System.out.println("创建停车BOY成功，停车BOY的编号是：" + input);
	}
	
	/**
	 * 为停车场经理指定管理的停车场
	 */
	private void managerHandlePark() {
		if(ParkMain.getInstance().getManagerList().size() == 0) {
			System.out.println("目前没有创建任何经理，请先创建经理！");
			return;
		}
		ParkManager manager = ParkMain.getInstance().selectManager();
		if (!manager.parkIsNull()) {
			System.out.println("停车场经理[" + manager.getCode() + "]已经指定了管理的停车场！");
			return;
		}
		if(ParkMain.getInstance().getParkList().size() == 0) {
			System.out.println("目前没有创建任何停车场，请先创建停车场！");
			return;
		}
		Park park = ParkMain.getInstance().selectPark();
		if(park.isManaged()) {
			System.out.println("停车场[" + park.getCode() + "]已经指定了其管理者，请指定其他停车场！");
			return;
		}
		manager.handlePark(park);
		System.out.println("为停车场经理[" + manager.getCode() + "]指定管理的停车场[" + park.getCode() + "]成功！");
	}
	
	/**
	 * 为停车场经理指定管理的停车BOY
	 */
	private void managerHandleBoy() {
		if(ParkMain.getInstance().getManagerList().size() == 0) {
			System.out.println("目前没有创建任何经理，请先创建经理！");
			return;
		}
		if(ParkMain.getInstance().getBoyList().size() == 0) {
			System.out.println("目前没有创建任何停车BOY，请先创建停车BOY！");
			return;
		}
		ParkManager manager = ParkMain.getInstance().selectManager();
		ParkBoy boy = ParkMain.getInstance().selectBoy();
		if(boy.isManaged()) {
			System.out.println("停车BOY[" + boy.getCode() + "]已经指定了其管理者，请指定其他停车BOY！");
			return;
		}
		manager.handleBoy(boy);
		System.out.println("为停车场经理[" + manager.getCode() + "]指定管理的停车BOY[" + boy.getCode() + "]成功！");
	}
	
	/**
	 * 为停车BOY指定管理的停车场
	 */
	private void boyHandlePark() {
		if(ParkMain.getInstance().getBoyList().size() == 0) {
			System.out.println("目前没有创建任何停车BOY，请先创建停车BOY！");
			return;
		}
		if(ParkMain.getInstance().getParkList().size() == 0) {
			System.out.println("目前没有创建任何停车场，请先创建停车场！");
			return;
		}
		ParkBoy boy = ParkMain.getInstance().selectBoy();
		Park park = ParkMain.getInstance().selectPark();
		if(park.isManaged()) {
			System.out.println("停车场[" + park.getCode() + "]已经指定了其管理者，请指定其他停车场！");
			return;
		}
		boy.handlePark(park);
		System.out.println("为停车BOY[" + boy.getCode() + "]指定管理的停车场[" + park.getCode() + "]成功！");
	}
	
	/**
	 * 打印停车场信息
	 */
	private void printParkInfo() {
		if(ParkMain.getInstance().getParkList().size() == 0) {
			System.out.println("目前没有创建任何停车场，请先创建停车场！");
			return;
		}
		int emptyNum = 0;
		int totalNum = 0;
		System.out.println("-----------------------------------------");
		for(int i = 0; i < ParkMain.getInstance().getParkList().size(); i ++) {
			Park park = ParkMain.getInstance().getParkList().get(i);
			System.out.println("   停车场编号：" + park.getCode());
			System.out.println("      车位数：" + park.getTotalNum());
			System.out.println("      空位数：" + park.getEmptyNum());
			emptyNum += park.getEmptyNum();
			totalNum += park.getTotalNum();
		}
		System.out.println("-----------------------------------------");
		System.out.println("   共计车位数：" + totalNum);
		System.out.println("   共计空位数：" + emptyNum);
	}
	
	/**
	 * 打印员工信息
	 */
	private void printEmployee() {
		if(ParkMain.getInstance().getManagerList().size() == 0 && ParkMain.getInstance().getBoyList().size() == 0) {
			System.out.println("目前没有创建任何员工！");
			return;
		}
		for(int i = 0; i < ParkMain.getInstance().getManagerList().size(); i ++) {
			ParkManager manager = ParkMain.getInstance().getManagerList().get(i);
			List<ParkBoy> boyList = manager.getParkBoyList();
			String boyCodes = "";
			if(boyList != null && boyList.size() > 0) {
				for(ParkBoy boy : boyList) {
					boyCodes += boy.getCode() + "  ";
				}
			} else {
				boyCodes = "暂时没有管理的停车BOY";
			}
			
			System.out.println("   经理编号：" + manager.getCode());
			System.out.println("      管理的停车BOY编号：" + boyCodes);
		}
		for(int i = 0; i < ParkMain.getInstance().getBoyList().size(); i ++) {
			ParkBoy boy = ParkMain.getInstance().getBoyList().get(i);
			Strategy strategy = boy.getStrategy();
			String strategyName = "";
			if(strategy instanceof DefaultStrategy) {
				strategyName = "默认策略";
			} else if(strategy instanceof AverageStrategy) {
				strategyName = "平均策略";
			} else if(strategy instanceof VacancyRateStrategy) {
				strategyName = "空置率策略";
			}
			System.out.println("   停车BOY编号：" + boy.getCode() + "  停车策略：" + strategyName);
		}
		System.out.println("-----------------------------------------");
	}
}