package slicing.com.String;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckIPAddress extends JFrame {
	private static final long serialVersionUID = -2671129992210934895L;
	private JPanel contentPane;
	private JTextField ipField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckIPAddress frame = new CheckIPAddress();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CheckIPAddress() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 128);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblip = new JLabel("\u8BF7\u8F93\u5165IP\u5730\u5740\uFF1A");
		lblip.setBounds(12, 14, 92, 15);
		contentPane.add(lblip);

		ipField = new JTextField();
		ipField.setBounds(100, 10, 141, 25);
		contentPane.add(ipField);

		JButton button = new JButton("\u9A8C\u8BC1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_button_actionPerformed(null);
			}
		});
		button.setBounds(66, 57, 93, 23);
		contentPane.add(button);
	}
	protected void do_button_actionPerformed(ActionEvent e){
		String text = ipField.getText();
		String info = matches(text);
		JOptionPane.showMessageDialog(null,info);

	}

	private String matches(String text) {
		if(text != null&&!text.isEmpty()){
			String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." +
					"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
					"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
					"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
			String regex1 = "^1[3,8,7,4]\\d{9}$";
			String regex2 = "^\\d{3}-?\\d{8}|\\d{4}-?\\d{8}$";
			if(text.matches(regex)){
				return text + "\n是一个合法ip；";
			}else{
				return text + "\n是一个非法ip；";
			}
		}
		return "请输入要验证的ip地址！";
	}
}
