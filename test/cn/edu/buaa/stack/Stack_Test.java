package cn.edu.buaa.stack;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-10-28
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */
public class Stack_Test {
    // 创建一个堆栈时堆栈大小为0
    @Test
    public void stack_size_is_0_when_create_stack () {
        Stack stack = new Stack();
        Assert.assertEquals(0, stack.size());
    }

    //  压栈一个a对象堆栈大小为1
    @Test
    public void stack_size_is_1_when_push_a_object () {
        Stack stack = new Stack();
        stack.push("a");
        Assert.assertEquals(1, stack.size());
    }

    // 压栈一个a，出栈获取的也是a
    @Test
    public void pop_stack_return_a_when_push_a_first () {
        Stack stack = new Stack();
        stack.push("a");
        Assert.assertEquals("a", stack.pop());
    }

    // 压栈abcd，出栈为dcba
    @Test
    public void pop_stack_return_dcba_when_push_abcd() {
        Stack stack = new Stack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        Assert.assertEquals("d", stack.pop());
        Assert.assertEquals("c", stack.pop());
        Assert.assertEquals("b", stack.pop());
        Assert.assertEquals("a", stack.pop());
    }

    // 当清空一个非空堆栈，堆栈大小为0
    @Test
    public void stack_size_is_0_when_stack_clear () {
        Stack stack = new Stack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.clear();
        Assert.assertEquals(0, stack.size());
    }

    // 当构造一个大小小于0的堆栈时抛出异常
    @Test(expected=StackException.class)
    public void construct_stack_throw_exception_when_size_less_than_0() {
        new Stack(-1);
    }

    // 当堆栈以满时再进行入栈操作时抛出异常
    @Test(expected=StackException.class)
    public void push_stack_throw_exception_when_stack_is_full() {
        Stack stack = new Stack(1);
        stack.push("a");
        stack.push("b");
    }

    // 当堆栈为空时再进行出栈操作时抛出异常
    @Test(expected=StackException.class)
    public void pop_stack_throw_exception_when_stack_is_empty() {
        Stack stack = new Stack();
        stack.pop();
    }
}
