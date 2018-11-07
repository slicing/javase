package Slow.slicing.dms;

import sun.nio.cs.ext.MS874;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class DMSServerFrame extends JFrame {
	private JPanel root;
	private JTextArea netLogArea;
	private JTextArea clientLogArea;
	private JTextArea saveLogArea;
	private JTextArea errLogArea;
	public DMSServerFrame(){
		setTitle("DMS Sever");
		setSize(800,600);
		setLocationRelativeTo(null);
		root = new JPanel(new GridLayout(2,2));
		add(root);
		netLogArea = add("网络连接日志");
		clientLogArea = add("客户服务日志");
		saveLogArea = add("保存日志");
		errLogArea = add("异常消息");
	}

	private JTextArea add(String title) {
		JTextArea area = new JTextArea();
		JScrollPane pane = new JScrollPane(area);
		root.add(pane);
		pane.setBorder(new TitledBorder(title));
		area.setEditable(false);
		return area;
	}

	public static void main(String[] args) {
		DMSServerFrame frame = new DMSServerFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void showNetMsg(String msg){
		this.netLogArea.append(msg + "\n");
	}

	public void showClientMag(String msg){
		this.clientLogArea.append(msg + "\n");
	}
	public void showSaveMsg(String msg){
		this.saveLogArea.append(msg + "\n");
	}
	public void showErrMsg(Exception e){
		StringWriter buf = new StringWriter();
		PrintWriter out = new PrintWriter(buf);
		e.printStackTrace(out);
		out.close();
		String msg = buf.toString();
		this.errLogArea.append(msg+"\n");
	}

}
