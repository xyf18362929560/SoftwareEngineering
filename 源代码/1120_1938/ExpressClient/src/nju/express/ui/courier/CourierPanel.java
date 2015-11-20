package nju.express.ui.courier;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import nju.express.blservice.Postblservice;
import nju.express.po.User;

public class CourierPanel extends JPanel {
	
	private static final long serialVersionUID = 3083436208220174728L;

	/**
	 * Create the panel.
	 */
	/**
	 * 目前因为只写了寄件单界面，只传入了postblservice，后面需要加入其他的blservice
	 * 
	 * @param postblservice
	 */
	public CourierPanel(Postblservice postblservice) {
		this.setSize(1280, 674);
		
		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 18));
		add(tabbedPane, BorderLayout.CENTER);
		
		PostPanel postPanel = new PostPanel(postblservice);
		tabbedPane.addTab("寄件单", null, postPanel, "新建或查看寄件单");
		//TODO 加入业务逻辑服务
		RecipientPanel recipientPanel = new RecipientPanel();
		tabbedPane.addTab("收件单", null, recipientPanel, "填写收件单");

	}

}
