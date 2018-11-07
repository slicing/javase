package Slow.slicing.dms;

import sun.nio.ch.Net;

import javax.swing.*;
import javax.swing.text.Document;
import javax.xml.bind.Element;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Policy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.logging.LogRecord;

public class DMSServer {
	private Properties config;
	private int port;
	private int poolSize;
	private int queueSize;
	private File serverLogFile;
	private ExecutorService threadPool;
	private BlockingQueue<String> queue;
	private ServerSocket ss;
	private DMSServerFrame frame;
	private Listener listener;
	private LogFileWriter fileWriter;

	public  DMSServer(){
		frame = new DMSServerFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		config = new Properties();
		try {
			config.load(new FileInputStream("server.properties"));
		} catch (Exception e) {
			e.printStackTrace();
			frame.showErrMsg(e);
		}
		port = Integer.parseInt(config.getProperty("server.port"));
		poolSize = Integer.parseInt(config.getProperty("pool.size"));
		queueSize = Integer.parseInt(config.getProperty("queue.size"));
		serverLogFile = new File(config.getProperty("server.log.file"));
		threadPool = Executors.newFixedThreadPool(poolSize);
		queue = new LinkedBlockingDeque<String>(queueSize);
	}

	private class Listener extends Thread{
		@Override
		public void run() {

			while (true){
				try {
					frame.showNetMsg("等待客户连接");
					Socket s = ss.accept();
					frame.showNetMsg("客户端连接成功");
					String ip = s.getInetAddress().toString();
					frame.showNetMsg("客户端IP："  + ip);
					frame.showNetMsg("创建客户端服务线程");
					LogReceiver receiver = new LogReceiver(s);
					frame.showNetMsg("执行服务线程");
					threadPool.execute(receiver);

				} catch (IOException e) {
					frame.showErrMsg(e);
					e.printStackTrace();
				}
			}
		}
	}

	private class LogFileWriter extends Thread{
		@Override
		public void run() {
			while (true){
				try {
					frame.showSaveMsg("等待数据。。。");
					String log = queue.take();
					frame.showSaveMsg("等到数据" + log);
					PrintWriter out = new PrintWriter(new FileWriter(serverLogFile,true));
					frame.showSaveMsg("打开文件" + serverLogFile);
					do{
						frame.showSaveMsg("保存日志" + log);
						out.println(log);
						log = queue.poll();
					}while (log != null);
					frame.showSaveMsg("关闭文件");
					out.close();
				} catch (Exception e) {
					frame.showErrMsg(e);
				}
			}
		}
	}

	private class LogReceiver implements Runnable {
		Socket socket;
		public LogReceiver(Socket s) {
			this.socket = s;
		}
		@Override
		public void run() {
			try {
				frame.showClientMag("从in接收客户端提交的xml");
				InputStream in = socket.getInputStream();
				Document request = NetService.receive(in);
				frame.showClientMag(request.asXML());
				frame.showClientMag("xml存储到List");
				List<String> list = new ArrayList<String>();
				String xpath = "/request/logs/log";
				List<Element> elements = request.selectNodes(xpath);
				for(Element e : elements){
					String log = e.getTextTrim();
					list.add(log);
				}
				frame.showClientMag("将每个log插入到缓冲队列中");
				String code = "200";
				String msg = "OK";
				for(String log : list){
					boolean added = queue.offer(log,5,TimeUnit.SECONDS);
					if(!added){
						frame.showClientMag("队列满，插入失败！撤回插入数据");
						queue.removeAll(list);
						code = "500";
						msg = "队列满插入失败";
						break;
					}
				}
				frame.showClientMag("反馈消息");
				Document response = DocumentHelper.creteDocument();
				Element root = response.addElement("response");
				root.addElement("code").addText(code);
				root.addElement("message").addText(msg);
				frame.showClientMag(response.asXML());
				OutputStream out = socket.getOutputStream();
				frame.showClientMag("发送xml");
				NetService .send(response,out);
				socket.close();
			} catch (Exception e) {
				frame.showErrMsg(e);
			}
		}
	}

	public static void main(String[] args) {
		DMSServer server = new DMSServer();
		server.action();
	}

	private void action() {
		frame.setVisible(true);
		try {
			frame.showNetMsg("绑定端口：" + port);
			ss = new ServerSocket(port);
			listener = new Listener();
			listener.start();
			frame.showNetMsg("创建并且开始网络端口监听线程");
			fileWriter = new LogFileWriter();
			fileWriter.start();
			frame.showNetMsg("启动文件写出线程");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
