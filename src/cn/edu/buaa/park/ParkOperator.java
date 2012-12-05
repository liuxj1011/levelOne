package cn.edu.buaa.park;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public abstract class ParkOperator {
	private static ParkOperator operator;
	
	public final static void setOperator(ParkOperator operator) {
		ParkOperator.operator = operator;
	}

	public final static ParkOperator getOperator() {
		return operator;
	}
	
	protected List<String> menuList = new ArrayList<String>();
	
	protected ParkOperator preOperator;
	
	/**
	 * 打印菜单
	 */
	public final void printMenu() {
		System.out.println("MENU");
		System.out.println("-----------------------------------------");
		if(menuList != null && menuList.size() > 0) {
			for(String menu : menuList) {
				System.out.println(menu);
			}
		} else {
			System.out.println("没有菜单。");
		}
		System.out.println("-----------------------------------------");
		System.out.println("[home]主界面 [back]上级菜单 [exit]退出         请输入：");
		execute(readInput());
	}

	private final void execute(String input) {
		if(input == null || "".equals(input)) {
			execute(readInput());
		}
		if ("home".equals(input.toLowerCase())) {
			ParkOperator.setOperator(ParkMain.getInstance());
		} else if ("back".equals(input.toLowerCase())) {
			if(this.preOperator != null) {
				ParkOperator.setOperator(this.preOperator);
			}
		} else if ("exit".equals(input.toLowerCase())) {
			System.exit(-1);
		} else {
			run(input);
		}
	}
	
	public abstract void run(String input);
	
	/**
	 * 获取用户输入
	 * @return	用户输入
	 */
	public final String readInput() {
		String input = "";
		try {
			input = new BufferedReader(new InputStreamReader(System.in)).readLine();
			while ("".equals(input)) {
				System.out.print("非法输入，请重新输入：");
				input = new BufferedReader(new InputStreamReader(System.in)).readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	/**
	 * 获取用户输入的数字
	 * @return	用户输入数字
	 */
	public final int readInputNum() {
		String input = readInput();
		while(!validateNum(input)) {
			System.out.print("非法输入，请重新输入：");
			input = readInput();
		}
        return Integer.parseInt(input);
    }
	
	private static boolean validateNum(String input) {
        return input != null && input.matches("\\d*");
    }
}