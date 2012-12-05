package cn.edu.buaa.park;

/**
 * 汽车类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-11
 * Time: 下午3:24
 * To change this template use File | Settings | File Templates.
 */
public class Car {
	// 票据编码
	private String code;
	
	/**
	 * 构造器
	 */
	public Car(String code) {
		this.code = code;
	}

	/**
	 * 构造器
	 */
	public Car() {
	}

	/**
	 * 获取车编号
	 */
	public String getCode() {
		return code;
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
		Car other = (Car) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}