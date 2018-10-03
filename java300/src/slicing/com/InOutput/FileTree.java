package slicing.com.InOutput;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;

public class FileTree extends JFrame {
	private static final long serialVersionUID = -2055459510450224221L;
	private JPanel contentPane;
	private JTree tree;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileTree frame = new FileTree();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public FileTree() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		File[] disks = File.listRoots();// 获得所有可用的文件系统根
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();// 创建节点
		for (File disk : disks) {// 将File数组中的元素增加到节点上
			root.add(new DefaultMutableTreeNode(disk));
		}
		tree = new JTree(root);// 使用节点创建树控件
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				do_tree_valueChanged(e);
			}
		});
		scrollPane.setViewportView(tree);
	}

	protected void do_tree_valueChanged(TreeSelectionEvent e) {
		// 获得用户选择的节点
		DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		File selectFile = (File) selectNode.getUserObject();// 获得节点代表的File类型对象
		if (selectFile.isDirectory()) {// 如果File类型对象是文件夹
			File[] files = selectFile.listFiles(new FileFilter() {

				@Override
				public boolean accept(File pathname) {// 过滤掉隐藏类型文件
					if (pathname.isHidden()) {
						return false;
					} else {
						return true;
					}
				}
			});
			for (File file : files) {// 将符合条件的File类型对象增加到用户选择的节点中
				selectNode.add(new DefaultMutableTreeNode(file));
			}
		} else {
			return;
		}
	}
}
