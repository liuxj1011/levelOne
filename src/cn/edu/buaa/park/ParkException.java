package cn.edu.buaa.park;

/**
 * 停车场异常类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-11
 * Time: 下午3:26
 * To change this template use File | Settings | File Templates.
 */
public class ParkException extends RuntimeException {
	private static final long serialVersionUID = -2323948745820049860L;

	public ParkException(String msg) {
        super(msg);
    }
}
