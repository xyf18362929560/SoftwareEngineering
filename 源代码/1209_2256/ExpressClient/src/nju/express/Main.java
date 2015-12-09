package nju.express;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import nju.express.ui.MainFrame;
import nju.express.util.LoggerUtil;

public class Main {
	static Logger logger = LoggerUtil.getLogger();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = MainFrame.getInstance();
					frame.setVisible(true);
					logger.info("客户端启动中...");
					MainFrame.getLoglabel().setText(" 客户端启动成功");
				} catch (Exception e) {
					logger.error("客户端启动失败...");
					e.printStackTrace();
				}
			}
		});

	}

}
