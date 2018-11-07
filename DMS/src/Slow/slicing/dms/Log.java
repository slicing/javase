package Slow.slicing.dms;

import java.io.Serializable;

public class Log implements Serializable {
	private String user;
	private int pid;
	private short type;
	private int time;
	private String ip;
	public Log(){

	}

	public Log(String user, int pid, short type, int time, String ip) {
		this.user = user;
		this.pid = pid;
		this.type = type;
		this.time = time;
		this.ip = ip;
	}
	public Log(String str){
		String[] data = str.split(",");
		user = data[0];
		pid = Integer.parseInt(data[1]);
		type = Short.parseShort(data[2]);
		time = Integer.parseInt(data[3]);
		ip = data[4];
	}

	@Override
	public String toString() {
		return "Log{" +
				"user='" + user + '\'' +
				", pid=" + pid +
				", type=" + type +
				", time=" + time +
				", ip='" + ip + '\'' +
				'}';
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
