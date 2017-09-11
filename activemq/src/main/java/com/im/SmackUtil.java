package com.im;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class SmackUtil {
	private static Connection connection;
	private ConnectionConfiguration config;
	private final static String server = "115.159.40.184";
	static String returnmessage ="";

	private final void fail(Object o) {
		if (o != null) {
			System.out.println(o);
		}
	}

	private final static void fail(Object o, Object... args) {
		if (o != null && args != null && args.length > 0) {
			String s = o.toString();
			for (int i = 0; i < args.length; i++) {
				String item = args[i] == null ? "" : args[i].toString();
				if (s.contains("{" + i + "}")) {
					s = s.replace("{" + i + "}", item);
				} else {
					s += " " + item;
				}
			}
			System.out.println(s);
		}
	}

	/**
	 * 初始Smack对openfire服务器链接的基本配置
	 */
	public boolean init(String username, String password) {
		try {
			/**
			 * 5222是openfire服务器默认的通信端口，你可以登录http://192.168.8.32:9090/
			 * 到管理员控制台查看客户端到服务器端口
			 */
			config = new ConnectionConfiguration(server, 5222);

			/** 是否启用压缩 */
			config.setCompressionEnabled(true);
			/** 是否启用安全验证 */
			config.setSASLAuthenticationEnabled(true);
			/** 是否启用调试 */
			config.setDebuggerEnabled(false);
			config.setSelfSignedCertificateEnabled(false);
			config.setVerifyChainEnabled(false);
			// config.setReconnectionAllowed(true);
			// config.setRosterLoadedAtLogin(true);

			/** 创建connection链接 */
			connection = new XMPPConnection(config);
			/** 建立连接 */
			connection.connect();
			connection.login(username, password);
			if (connection.getUser() != null) {
				return true;
			}
			return false;
		} catch (XMPPException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		SmackUtil sm = new SmackUtil();
		sm.init("test", "test");
//		sm.getAllUser();
		// sm.testChatManager("", "");

	}

	/***
	 * 聊天
	 * 
	 * @param context
	 * @param user
	 */
	public static void testChatManager(String context, String user) {
		/** 获取当前登陆用户的聊天管理器 */
		ChatManager chatManager = connection.getChatManager();
		/** 为指定用户创建一个chat，MyMessageListeners用于监听对方发过来的消息 */
		Chat chat = chatManager.createChat(user, new MessageListener() {

			@Override
			public void processMessage(Chat chat, Message message) {
				fail("body: ", message.getBody());
				 String str = message.getBody();
				 returnmessage=str;
				 System.out.println(str+"--------");
			}
		});
		try {
			/** 发送消息 */
			chat.sendMessage(context);

			/** 用message对象发送消息 */
			Message message = new Message();
			message.setProperty("color", "red");
			chat.sendMessage(message);
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUser() {
		Roster roster = connection.getRoster();
		List<RosterEntry> entriesList = new ArrayList<RosterEntry>();
		Collection<RosterEntry> rosterEntry = roster.getEntries();
		Iterator<RosterEntry> i = rosterEntry.iterator();
		while (i.hasNext())
			entriesList.add(i.next());
		List<User> userList = new ArrayList<User>();
		for (RosterEntry rosterEntry1 : entriesList) {
			User user = new User();
			user.setOpenfireId(rosterEntry1.getUser());
			user.setUsername(rosterEntry1.getName());
			if (rosterEntry1.getStatus() == null) {
				// 在线
				user.setStaute(1);
			} else {
				user.setStaute(0);
			}
			userList.add(user);
		}
		return userList;

	}
}
