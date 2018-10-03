package slicing.com.InOutput;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ReplaceTool extends JFrame {
	private static final long serialVersionUID = -6579898848014795564L;
	private JPanel contentPane;
	private JTextField beforeTextField;
	private JTextField afterTextField;
	private File textFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReplaceTool frame = new ReplaceTool();
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
	public ReplaceTool() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 5, 5));

		JPanel contentPanel = new JPanel();
		contentPane.add(contentPanel);

		JLabel beforeLabel = new JLabel("替换前字符串：");
		contentPanel.add(beforeLabel);

		beforeTextField = new JTextField();
		contentPanel.add(beforeTextField);
		beforeTextField.setColumns(10);

		JButton chooseButton = new JButton("选择文件");
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_chooseButton_actionPerformed(e);
			}
		});
		contentPanel.add(chooseButton);

		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel);

		JButton replaceButton = new JButton("开始替换");
		replaceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					do_replaceButton_actionPerformed(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JLabel afterlabel = new JLabel("替换后字符串：");
		buttonPanel.add(afterlabel);

		afterTextField = new JTextField();
		buttonPanel.add(afterTextField);
		afterTextField.setColumns(10);
		buttonPanel.add(replaceButton);
	}

	private void do_replaceButton_actionPerformed(ActionEvent e) throws IOException {
		String before = beforeTextField.getText();
		if(before.isEmpty()){
			JOptionPane.showMessageDialog(this, "请输入替换前字符串", "警告信息", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String after = afterTextField.getText();
		if(after.isEmpty()){
			JOptionPane.showMessageDialog(this, "请输入替换后字符串", "警告信息", JOptionPane.WARNING_MESSAGE);
			return;
		}
		FileReader reader = null;
		FileWriter writer = null;
		StringBuilder sb = new StringBuilder();
		int flag = 0;
		char[] temp = new char[1024];
		try{
			reader = new FileReader(textFile);
			while ((flag = reader.read(temp)) != -1){
				sb.append(temp);
			}
			String content = sb.toString().replace(before,after);
			writer = new FileWriter(textFile);
			writer.write(content);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(writer != null){
				try {
					writer.close();
				}catch (IOException e1){
					e1.printStackTrace();
				}

			}
		}
		JOptionPane.showMessageDialog(this, "字符串替换成功！");
		return;
	}

	protected void do_chooseButton_actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("文本文件", "txt"));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			textFile = chooser.getSelectedFile();
		}
	}
}
