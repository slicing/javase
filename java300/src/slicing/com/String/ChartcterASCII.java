package slicing.com.String;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ChartcterASCII extends JFrame {
	private static final long serialVersionUID = 5503258471694848265L;
	private JPanel contentPane;
	private JTextField charInputField;
	private JTextField codeOutputField;
	private JFormattedTextField codeInputField;
	private JTextField charOutputField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChartcterASCII frame = new ChartcterASCII();
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
	public ChartcterASCII() {
		setTitle("\u5B57\u7B26\u4E0E\u7F16\u7801\u8F6C\u6362");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null,
				"\u5B57\u7B26\u4E0EUnicode\u7801\u8F6C\u6362",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59,
				59)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 3, 5, 20));

		charInputField = new JTextField();
		panel.add(charInputField);
		charInputField.setColumns(10);

		JButton codeButton = new JButton("\u8F6C\u6362\u4E3AUnicode\u7801");
		codeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_codeButton_actionPerformed(e);
			}
		});
		panel.add(codeButton);

		codeOutputField = new JTextField();
		codeOutputField.setEditable(false);
		panel.add(codeOutputField);
		codeOutputField.setColumns(10);

		codeInputField = new JFormattedTextField(NumberFormat
				.getIntegerInstance());
		panel.add(codeInputField);
		codeInputField.setColumns(10);

		JButton charButton = new JButton("\u8F6C\u6362\u4E3A\u5B57\u7B26");
		charButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_charButton_actionPerformed(e);
			}
		});
		panel.add(charButton);

		charOutputField = new JTextField();
		charOutputField.setEditable(false);
		panel.add(charOutputField);
		charOutputField.setColumns(10);
	}
	protected void do_codeButton_actionPerformed(ActionEvent e){
		String text = charInputField.getText();
		char[] charArray = text.toCharArray();
		StringBuilder builder = new StringBuilder();
		for(char c:charArray){
			builder.append((int)c + " ");
		}
		codeOutputField.setText(builder.toString());
	}
	protected void do_charButton_actionPerformed(ActionEvent e){
		Number value = (Number) codeInputField.getValue();
		long code = value.longValue();
		charOutputField.setText(((char) code) + " ");
	}
}
