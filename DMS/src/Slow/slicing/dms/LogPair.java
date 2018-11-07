package Slow.slicing.dms;

import java.io.Serializable;

public class LogPair implements Serializable {
	private Log login;
	private Log logout;

	public LogPair() {
	}

	public LogPair(Log login, Log logout) {
		this.login = login;
		this.logout = logout;
	}

	public LogPair(String str) {
		String[] data = str.split("\\|");
		login = new Log(data[0]);
		logout = new Log(data[1]);
	}

	@Override
	public String toString() {
		return "LogPair{" +
				"login=" + login +
				", logout=" + logout +
				'}';
	}

	public Log getLogin() {
		return login;
	}

	public void setLogin(Log login) {
		this.login = login;
	}

	public Log getLogout() {
		return logout;
	}

	public void setLogout(Log logout) {
		this.logout = logout;
	}
}
