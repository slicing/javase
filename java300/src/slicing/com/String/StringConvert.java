package slicing.com.String;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
public class StringConvert extends JFrame {
	private static final long serialVersionUID = 4556387791998133270L;
	private JTextField inputTextField;
	private JTextField outputTextField;

	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StringConvert frame = new StringConvert();
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
	public StringConvert() {
		setTitle("\u5B57\u7B26\u4E32\u5927\u5C0F\u5199\u8F6C\u6362");
		setBounds(100, 100, 450, 214);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		inputTextField = new JTextField();
		inputTextField.setBounds(23, 21, 383, 31);
		getContentPane().add(inputTextField);
		inputTextField.setColumns(10);

		JButton button = new JButton("\u8F6C\u6362");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_button_actionPerformed(null);
			}
		});

		// lambda表达式
//		button.addActionListener((e) -> {
//			do_button_actionPerformed(null);
//		});


		button.setBounds(23, 77, 93, 23);
		getContentPane().add(button);

		JRadioButton radioButton = new JRadioButton("大写");
		radioButton.setActionCommand("大写");
		radioButton.setSelected(true);
		radioButton.setBounds(169, 77, 76, 23);
		buttonGroup.add(radioButton);
		getContentPane().add(radioButton);

		JRadioButton radioButton_1 = new JRadioButton("小写");
		radioButton_1.setBounds(280, 77, 121, 23);
		radioButton_1.setActionCommand("小写");
		buttonGroup.add(radioButton_1);
		getContentPane().add(radioButton_1);

		outputTextField = new JTextField();
		outputTextField.setEditable(false);
		outputTextField.setColumns(10);
		outputTextField.setBounds(23, 122, 383, 31);
		getContentPane().add(outputTextField);
		@SuppressWarnings("unused")
		String strBook = "MingRiBook".toLowerCase();
	}
	protected void do_button_actionPerformed(Annotation arg0){
		String command = buttonGroup.getSelection().getActionCommand();
		boolean upper = command.equals("大写");
		String text = inputTextField.getText();
		if(upper){
			outputTextField.setText(text.toUpperCase());
		}else {
			outputTextField.setText(text.toLowerCase());
		}
	}

}
