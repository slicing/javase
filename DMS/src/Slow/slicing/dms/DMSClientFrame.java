package Slow.slicing.dms;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class DMSClientFrame extends JFrame {
	private JPanel root;
	private JTextArea readLogArea;
	private JTextArea matchLogArea;
	private JTextArea sendLogArea;
	private JTextArea errLogArea;
	public DMSClientFrame(){
		setTitle("DMS Client");
		setSize(800,600);
		setLocationRelativeTo(null);
		root = new JPanel(new GridLayout(2,2));
		add(root);
		readLogArea = add("读取日志");
		matchLogArea = add("匹配日志");
		sendLogArea = add("发送日志");
		errLogArea = add("异常消息");
	}


	public void showReadLogMsg(String msg){
		this.readLogArea.append(msg + "\n");
	}
	private JTextArea add(String title) {
		JTextArea area = new JTextArea();
		JScrollPane pane = new JScrollPane(area);
		root.add(pane);
		pane.setBorder(new TitledBorder(title));
		area.setEditable(false);
		return area;
	}
	public void showMatchLogsMsg(String msg){
		this.matchLogArea.append(msg + "\n");
	}

	public void showSendLodsMsg(String msg){
		this.sendLogArea.append(msg+"\n");
	}

	public void showErrMsg(Exception e){
		StringWriter buf = new StringWriter();
		PrintWriter out = new PrintWriter(buf);
		e.printStackTrace(out);
		out.close();
		String msg = buf.toString();
		this.errLogArea.append(msg+"\n");
	}

	public static void main(String[] args) {
		DMSClientFrame frame = new DMSClientFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
