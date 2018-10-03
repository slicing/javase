package slicing.com.secondObject;

import javafx.print.Printer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.Timer;

public class AlarmClock {
	private String delay;
	private boolean flag;

	public AlarmClock(String delay, boolean flag) {
		this.delay = delay;
		this.flag = flag;
	}
	public void start() {
		class Printer implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat fromat = new SimpleDateFormat("k:m:s");
				String result = fromat.format(new Date());
				System.out.println("当前时间是：" + result);
				if (flag) {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		}
		//new Timer(delay,flag).start();
	}
}
