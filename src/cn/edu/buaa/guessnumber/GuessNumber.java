package cn.edu.buaa.guessnumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-10-22
 * Time: 下午1:43
 * To change this template use File | Settings | File Templates.
 */
public class GuessNumber {
    private byte[] input;
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

    private byte[] stringToBytes(String num) {
        return num.getBytes();
    }

    private static String readInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while (true) {
            try {
                input = br.readLine();
                if(!"quit".equals(input.toLowerCase()) && !input.matches("(?!\\d*(\\d)\\d*\\1\\d*)\\d{4}")) {
                    System.out.println("你没有按照要求输入哦，重新输入吧：");
                } else {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return input;
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
        System.out.println("请输入4位数让别人来猜一猜，不能重复：");
        String input = readInput();
        System.out.println("要猜的数字是：" + input + "。嘘！不要告诉别人哦！");
        GuessNumber guessNumber = new GuessNumber(input);
        System.out.println("让我们来猜一猜是哪4个数字呢？");
        System.out.println("游戏过程中可以输入<quit>来退出游戏。");
        for(int i = 0; i < 6; i ++) {
            System.out.println("请输入第" + (i + 1) + "次要猜测的数字：");
            String num = readInput();
            if(num.toLowerCase().equals("quit")) {
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
