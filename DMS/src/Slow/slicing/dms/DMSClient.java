package Slow.slicing.dms;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.SocketHandler;
import java.util.stream.Stream;

public class DMSClient {
	private File logFile;
	private File textLogFile;
	private File positionFile;
	private int batch;
	private long interval;
	private Properties config;
	private File matchedFile;
	private File loginFile;
	private String host;
	private int port;
	private DMSClientFrame frame;
	public static final int LOG_LENGTH = 372;
	private static final short LOGIN = 7;
	private static final short LOGOUT = 8;
	public DMSClient(){
		frame = new DMSClientFrame();
		config = new Properties();
		try {
			FileInputStream in = new FileInputStream("client.properties");
			config.load(in);
		} catch (IOException e) {
			frame.showErrMsg(e);
		}
		logFile = new File(config.getProperty("log.file"));
		textLogFile = new File(config.getProperty("text.log.file"));
		positionFile = new File(config.getProperty("position.file"));
		batch = Integer.parseInt(config.getProperty("batch"));
		interval = Long.parseLong(config.getProperty("interval"));
		matchedFile = new File(config.getProperty("matched.log.file"));
		loginFile = new File(config.getProperty("login.log.file"));
		host = config.getProperty("server.ip");
		port = Integer.parseInt(config.getProperty("server.port"));
		logFile = new File("wtmpt");
		/*textLogFile = new File("log.txt");
		positionFile = new File("position.txt");
		batch = 10;
		interval = 5000;*/
	}
	public void action(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		while(true){
			readLogs();
			matchLogs();
			sendLogs();
			try{
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	private void matchLogs() {
		frame.showMatchLogsMsg("开始匹配计算");
		if(!textLogFile.exists()){
			frame.showMatchLogsMsg("没有文件：" + textLogFile);
			return;
		}
		if(matchedFile.exists()){
			frame.showMatchLogsMsg("还存在匹配的日志，没有发送");
			return;
		}
		Map<String,Log> logins = new HashMap<>();
		Map<String,Log> logouts = new HashMap<>();
		try {
			readLogs(loginFile,logins,logouts);
			readLogs(textLogFile,logins,logouts);
		}catch (IOException e){
			frame.showErrMsg(e);
			return;
		}
		List<LogPair> matched = new ArrayList<LogPair>();
		Set<String> KeySet = logouts.keySet();
		for(String key : KeySet){
			frame.showMatchLogsMsg("key: " + key);
			Log login = logins.remove(key);
			if(login != null){
				Log logout = logouts.get(key);
				frame.showMatchLogsMsg("fond: " + login + " , " + logout);
				matched.add(new LogPair(login,logout));
			}
		}
		File tempL = new File(System.nanoTime() + "L.txt");
		File tempM = new File(System.nanoTime() + "m.txt");
		try {
			save(matched,tempM);
			save(logins.values(),tempL);
			frame.showMatchLogsMsg("保存文件成功了");
			if(tempM.renameTo(matchedFile)){
				loginFile.delete();
				if(tempL.renameTo(loginFile)){
					textLogFile.delete();
					frame.showMatchLogsMsg("删除： " + textLogFile);
					frame.showMatchLogsMsg("结束匹配");
				}
			}
		}catch (Exception e){
			frame.showErrMsg(e);
			return;
		}
	}

	private void save(Collection<LogPair> col, File file) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(file);
		for(Object obj : col){
			out.println(obj);
		}
		out.close();
	}


	private void readLogs(File file, Map<String,Log> logins, Map<String,Log> logouts) throws IOException {
		if(!file.exists()){
			return;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(file))));
		String str;
		while((str = in.readLine()) != null){
			Log log = new Log(str);
			String key = log.getUser() + " , " + log.getPid() + " , " + log.getIp();
			if(log.getType() == LOGIN){
				logins.put(key,log);
			}else if(log.getType() == LOGOUT){
				logouts.put(key,log);
			}
		}
		in.close();
	}

	private void readLogs() {
		frame.showReadLogMsg("开始读取日志。。。。");
		if(!logFile.exists()){
			System.out.println("没有输入日志文件 " + logFile);
			return;
		}
		if(textLogFile.exists()){
			frame.showReadLogMsg("输出文件已经存在，本次不读取了！");
			return;
		}
		int position;
		try {
			Scanner in = new Scanner(positionFile);
			position = in.nextInt();
			in.close();
			frame.showReadLogMsg("从文件读物位置：" + position);
		} catch (FileNotFoundException e) {
			frame.showReadLogMsg("从0读取");
			position = 0;
		}
		if(position * LOG_LENGTH == logFile.length()){
			frame.showReadLogMsg("没有新的log产生，本次读取结束");
			return;
		}
		RandomAccessFile in = null;
		PrintWriter out = null;
		try {
			in = new RandomAccessFile(logFile,"r");
			frame.showReadLogMsg("打开文件" + logFile);
			File temp = new File(System.nanoTime() + "L.txt");
			out = new PrintWriter(temp,"utf_8");
			frame.showReadLogMsg("打开临时文件" + temp);
			int i;
			for(i = 0;i<batch;i++){
				int n = i + position;
				String log = readLog(in,n);
				frame.showReadLogMsg("读取日志" + n + " : " + log);
				if(log == null){
					break;
				}
				out.println(log);
				frame.showReadLogMsg("保存临时文件");
			}
			position = position + i;
			out.close();
			if(temp.renameTo(textLogFile)){
				frame.showReadLogMsg("临时文件改名：" + textLogFile);
				PrintWriter pw = new PrintWriter(positionFile);
				pw.println(position);
				pw.close();
				frame.showReadLogMsg("保存下次读取位置：" + positionFile);
			}else{
				frame.showReadLogMsg("临时文件改名失败!");
			}
		} catch (IOException e) {
			e.printStackTrace();
			frame.showReadLogMsg("读取日志失败！"  + e);
			frame.showErrMsg(e);
		}finally {
			try{
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		frame.showReadLogMsg("结束读取！");
	}

	private String readLog(RandomAccessFile file, int n) throws IOException {
		long start = (long)LOG_LENGTH * n;
		file.seek(start + 0);
		byte[] buf = new byte[32];
		int c = file.read(buf);
		if(c == -1){
			return null;
		}
		String name = new String(buf,"iso8859-1").trim();
		file.seek(start + 68);
		int pid = file.readInt();
		file.seek(start + 72);
		short type = file.readShort();
		file.seek(start + 80);
		int time = file.readInt();
		file.seek(start + 114);
		buf = new byte[258];
		file.read(buf);
		String ip = new String(buf,"iso8859-1").trim();
		return name + " , " + pid + " , " + type + " , " + time + " , " + ip;
	}

	public void sendLogs(){
		if(!matchedFile.exists()){
			frame.showSendLodsMsg("没输入数据文件！" + matchedFile);
			return;
		}
		frame.showSendLodsMsg("读取" + matchedFile + "到XML!");
		Document doc = DocumentHelper.createDocument();
		Element[] root = doc.addElement("request");
		Element logs = root.addElement("logs");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(matchedFile),"utf-8"));
			String str;
			while ((str = in.readLine())!= null){
				logs.addElement("log").addText(str);
			}
		} catch (IOException e) {
			frame.showErrMsg(e);
			frame.showSendLodsMsg(e.getMessage());
		}finally {
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		frame.showSendLodsMsg("连接到服务器，发送数据");
		Socket s = null;
		try {
			s = new Socket(host,port);
			OutputStream out = s.getOutputStream();
			NetService.send(doc,out);
			InputStream netIn = s.getInputStream();
			Document response = NetService.receive(netIn);
			root = response.getRootElements();
			String code = root.elementTextTrim("code");
			String msg = root.elementTextTrim("message");
			frame.showSendLodsMsg(msg);
			if(code.equals("200")){
				matchedFile.delete();
			}
			frame.showSendLodsMsg("结束发送");
		} catch (Exception e) {
			frame.showErrMsg(e);
			frame.showSendLodsMsg(e.getMessage());
		}finally {
			try {
				if(s != null){
					s.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DMSClient client = new DMSClient();
		client.action();
	}
}
