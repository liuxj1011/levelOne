package cn.edu.buaa.park;

/**
 * 票据类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-11-18
 * Time: 上午8:01
 * To change this template use File | Settings | File Templates.
 */
public class Ticket {
	// 票据编码
	private String code;
	
	/**
	 * 构造器
	 */
	public Ticket(String code) {
		this.code = code;
	}

	/**
	 * 构造器
	 */
	public Ticket() {
	}

	/**
	 * 获取票据编号
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
		Ticket other = (Ticket) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}