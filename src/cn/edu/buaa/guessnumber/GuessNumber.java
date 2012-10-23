package cn.edu.buaa.guessnumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-10-22
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public class GuessNumber {
    private byte[] input;

    // 构造器
    public GuessNumber(String input) {
        this.input = stringToBytes(input);
    }

    public String validate(String num) {
        byte[] nums = stringToBytes(num);
        return validateA(nums) + "a" + validateB(nums) + "b";
    }

    /**
     * A规则验证
     * @param nums  数据字节对象数组
     * @return  返回数字符合且位置符合的数字个数
     */
    private int validateA(byte[] nums) {
        int a = 0;
        for(int i = 0; i < this.input.length; i ++) {
            if(this.input[i] == nums[i]) {
                a ++;
            }
        }
        return a;
    }

    /**
     * B规则验证
     * @param nums  数据字节对象数组
     * @return  返回数字符合但位置不符的数字个数
     */
    private int validateB(byte[] nums) {
        int b = 0;
        for(int i = 0; i < this.input.length; i ++) {
            for(int j = 0; j < nums.length; j ++) {
                if(i == j) {
                    continue;
                }
                if(this.input[i] == nums[j]) {
                    b ++;
                    break;
                }
            }
        }
        return b;
    }

    /**
     * 将一个字符串转换为byte数组
     * @param num   字符串
     * @return  byte数组
     */
    private byte[] stringToBytes(String num) {
        return num.getBytes();
    }

    /**
     * 读取用户输入的选择项
     * @return  选择项
     */
    private static String readChoice() {
        String input = "";
        while (true) {
            input = readInput();
            if(!"quit".equals(input.toLowerCase()) && !input.equals("1") && !input.equals("2")) {
                System.out.println("没有你的选项哦，请重新输入吧：");
            } else {
                break;
            }
        }
        return input;
    }

    /**
     * 读取用户输入的数字或者quit
     * @return  数字或者quit
     */
    private static String readInputNum() {
        String input = "";
        while (true) {
            input = readInput();
            if(!"quit".equals(input.toLowerCase()) && !validateNum(input)) {
                System.out.println("你没有按照要求输入哦，重新输入吧：");
            } else {
                break;
            }
        }
        return input;
    }

    private static boolean validateNum(String input) {
        return input != null && input.matches("(?!\\d*(\\d)\\d*\\1\\d*)\\d{4}");
    }

    /**
     * 读取用户输入的字符串
     * @return  输入的字符串
     */
    private static String readInput() {
        String input = "";
        try {
            input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    /**
     * 生成一个随机的不重复的4位数
     * @return  随机的不重复的4位数
     */
    private static String generateRandomNum() {
        String result = "";
        Random random = new Random();
        while (true) {
            String s = String.valueOf(random.nextInt(10));
            if(result.indexOf(s) < 0) {
                result += s;
            }
            if(result.length() >= 4) {
                break;
            }

        }
        return result;
    }

    /**
     * 猜数字游戏主程序
     * 1.首先由用户输入一个数字让别人来猜
     * 2.然后由游戏者输入一个数字和用户的数字进行比较
     * 3.如果猜错了，将比较结果告诉游戏者
     * 4.如果游戏者猜对了或者猜测次数超过6次游戏结束
     * 5.游戏过程中可以直接输入quit退出游戏
     * 6.游戏过程中要求输入的数字必须为4位不重复的数字
     */
    public static void main(String[] args) {
        System.out.println("Game Start...");
        System.out.println("游戏过程中可以输入<quit>来退出游戏。");
        System.out.println("请选择一种游戏方式：");
        System.out.println("1.由系统自动创建一个4位不重复的数字来进行猜数字游戏；");
        System.out.println("2.由你输入一个4位不重复的数字来进行猜数字游戏。");
        System.out.println("请选择（请输入1或2）：");
        String choice = readChoice();
        if(choice.toLowerCase().equals("quit")) {
            System.out.println("还没开始就退出，我好伤心哦。");
            System.exit(0);
        }
        String input = "";
        if(choice.equals("1")) {
            input = generateRandomNum();
        } else {
            System.out.println("请输入4位数让别人来猜一猜，不能重复：");
            input = readInputNum();
            if(input.toLowerCase().equals("quit")) {
                System.out.println("还没开始就退出，我好伤心哦。");
                System.exit(0);
            }
        }
        System.out.println("要猜的数字是：" + input + "。嘘！不要告诉别人哦！");
        GuessNumber guessNumber = new GuessNumber(input);
        System.out.println("让我们来猜一猜是哪4个数字呢？你一共有6次机会哦！");
        for(int i = 0; i < 6; i ++) {
            System.out.println("请输入第" + (i + 1) + "次要猜测的数字：");
            String num = readInputNum();
            if(num.toLowerCase().equals("quit")) {
                System.out.println("想不到你居然放弃了，鄙视你。");
                break;
            }
            String result =  guessNumber.validate(num);
            if(result.equals("4a0b")) {
                System.out.println("你赢了，太棒了！");
                break;
            } else if(i == 5) {
                System.out.println("你输了，再接再厉哦！");
            } else {
                System.out.println(num + "，你没猜对哦，给你点提示：" + result);
            }
        }
        System.out.println("Game Over...");
    }


}
