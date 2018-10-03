package slicing.com.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PhoneBook extends JFrame {
	private static final long serialVersionUID = -3041722856251346473L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhoneBook frame = new PhoneBook();
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
	public PhoneBook() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				do_this_windowActivated(null);
			}
		});
		setTitle("\u6211\u7684\u7535\u8BDD\u7C3F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
	}
	protected void do_this_windowActivated(ActionEvent event){
		Map<String,String> directory = new HashMap<String, String>();
		directory.put("阿一", "33265****");
		directory.put("阿二", "66150****");
		directory.put("阿三", "20427****");
		directory.put("阿四", "98823****");
		directory.put("阿五", "91364****");
		directory.put("阿六", "89259****");
		directory.put("阿七", "12441****");
		directory.put("阿八", "79920****");
		directory.put("阿九", "22721****");
		directory.put("阿十", "89383****");
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setColumnIdentifiers(new Object[]{"姓名","手机号"});
		Set<String> names = directory.keySet();
		for(Iterator<String> it = names.iterator();it.hasNext();){
			String name = it.next();// 获得键
			model.addRow(new Object[] { name, directory.get(name) });
		}
		table.setModel(model);
	}


}
