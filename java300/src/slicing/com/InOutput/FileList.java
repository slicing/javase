package slicing.com.InOutput;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileList  extends JFrame {
	private static final long serialVersionUID = -2029566581047632579L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileList frame = new FileList();
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
	public FileList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel label = new JLabel("输入文件扩展名：");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel.add(label);

		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel.add(textField);
		textField.setColumns(12);

		JButton button = new JButton("选择文件夹");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_button_actionPerformed(e);
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel.add(button);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		header.setPreferredSize(new Dimension(header.getWidth(), 25));// 修改表头的高度
		table.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		table.setRowHeight(25);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new String[] { "文件名", "文件大小", "修改时间" });
		scrollPane.setViewportView(table);
	}

	private void do_button_actionPerformed(ActionEvent e) {
		 final String fileType = textField.getText();
		 if(fileType.isEmpty()){
		 	JOptionPane.showMessageDialog(this,"请输入文件类型！","",JOptionPane.WARNING_MESSAGE);
		 	return;
		 }
		 JFileChooser chooser = new JFileChooser();
		 chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		 chooser.setMultiSelectionEnabled(false);
		 int result = chooser.showOpenDialog(this);
		 if(result == JFileChooser.APPROVE_OPTION){
			 File[]listFiles = chooser.getSelectedFile().listFiles(new FileFilter(){

				 @Override
				 public boolean accept(File pathname) {
					 if(pathname.getName().endsWith(fileType)){
					 	return true;
					 }else{
					 	return false;
					 }
				 }
			 });
			 DefaultTableModel model = (DefaultTableModel)table.getModel();
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 for(File file:listFiles){
			 	String name = file.getName();
			 	long size = file.length();
			 	String modifyDate = format.format(new Date(file.lastModified()));
			 	model.addRow(new String[] {name,"" + size,modifyDate});
			 }
			 table.setModel(model);
		 }

	}
}
