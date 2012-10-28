package cn.edu.buaa.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-10-28
 * Time: 下午3:44
 * To change this template use File | Settings | File Templates.
 */
public class Stack {
    private int limitSize = 0;
    private List<String> list = new ArrayList<String>();

    public Stack(int i) {
        if(i < 0) {
            throw new StackException("不能构造小于0的堆栈！");
        }
        this.limitSize = i;
    }

    public Stack() {
    }

    public int size() {
        return this.list.size();
    }

    public void push(String obj) {
        if(this.limitSize > 0 && this.list.size() >= this.limitSize) {
            throw new StackException("堆栈已满，不能入栈了！");
        }
        this.list.add(obj);
    }

    public String pop() {
        if(this.list.size() <= 0) {
            throw new StackException("堆栈已经空了，不能出栈了！");
        }
        String result = this.list.get(this.list.size() - 1);
        this.list.remove(this.list.size() - 1);
        return result;
    }

    public void clear() {
        this.list.clear();
    }
}