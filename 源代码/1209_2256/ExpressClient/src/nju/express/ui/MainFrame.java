package nju.express.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.rmi.Naming;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.apache.log4j.Logger;

import nju.express.blservice.Accountblservice;
import nju.express.blservice.BItransportblservice;
import nju.express.blservice.Constantblservice;
import nju.express.blservice.Deliveryblservice;
import nju.express.blservice.Departmentblservice;
import nju.express.blservice.Driverblservice;
import nju.express.blservice.IItransportblservice;
import nju.express.blservice.Paymentblservice;
import nju.express.blservice.Postblservice;
import nju.express.blservice.Recipientblservice;
import nju.express.blservice.Stockblservice;
import nju.express.blservice.UserblService;
import nju.express.blservice.Vehicleblservice;
import nju.express.blservice.impl.AccountblserviceImpl;
import nju.express.blservice.impl.BItransportblserviceImpl;
import nju.express.blservice.impl.ConstantblserviceImpl;
import nju.express.blservice.impl.DeliveryblserviceImpl;
import nju.express.blservice.impl.DepartmentblserviceImpl;
import nju.express.blservice.impl.DriverblserviceImpl;
import nju.express.blservice.impl.IItransportblserviceImpl;
import nju.express.blservice.impl.PaymentblserviceImpl;
import nju.express.blservice.impl.PostblserviceImpl;
import nju.express.blservice.impl.RecipientblserviceImpl;
import nju.express.blservice.impl.StockblserviceImpl;
import nju.express.blservice.impl.UserblServiceImpl;
import nju.express.blservice.impl.VehicleblserviceImpl;
import nju.express.dataservice.AccountDataService;
import nju.express.dataservice.BItransportDataService;
import nju.express.dataservice.DeliveryDataService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.DriverDataService;
import nju.express.dataservice.FareDataService;
import nju.express.dataservice.GoodsDataService;
import nju.express.dataservice.IItransportDataService;
import nju.express.dataservice.JobDataService;
import nju.express.dataservice.PaymentDataService;
import nju.express.dataservice.PostDataService;
import nju.express.dataservice.ReceiverDataService;
import nju.express.dataservice.RecipientDataService;
import nju.express.dataservice.SenderDataService;
import nju.express.dataservice.StockDataService;
import nju.express.dataservice.UserDataService;
import nju.express.dataservice.VehicleDataService;
import nju.express.po.User;
import nju.express.ui.business.BusinessPanel;
import nju.express.ui.center.CenterPanel;
import nju.express.ui.courier.CourierPanel;
import nju.express.ui.finance.FinancePanel;
import nju.express.ui.manager.ManagerPanel;
import nju.express.util.LoggerUtil;

public class MainFrame extends JFrame {
	static Logger logger = LoggerUtil.getLogger();
	private static final long serialVersionUID = -5912660057088784794L;

	private static final MainFrame MAINFRAME = new MainFrame();

	private JPanel contentPane;
	private JPanel currentPane;
	private JPanel beforePane;
	int mx, my, jfx, jfy;
	private JPanel controlpanel;
	private JPanel logpanel;

	UserblService userblService;
	Postblservice postblservice;
	Vehicleblservice vehicleblservice;
	Driverblservice driverblservice;
	BItransportblservice bItransportblservice;
	Stockblservice stockblservice;
	IItransportblservice iItransportblservice;
	Deliveryblservice deliveryblservice;
	Recipientblservice recipientblservice;
	Paymentblservice paymentblservice;
	Accountblservice accountblservice;
	Departmentblservice departmentblservice;
	Constantblservice constantblservice;

	UserDataService userDataService;
	PostDataService postDataService;
	SenderDataService senderDataService;
	ReceiverDataService receiverDataService;
	GoodsDataService goodsDataService;
	JobDataService jobDataService;
	DepartmentDataService departmentDataService;
	VehicleDataService vehicleDataService;
	DriverDataService driverDataService;
	BItransportDataService bItransportDataService;
	StockDataService stockDataService;
	IItransportDataService iItransportDataService;
	DeliveryDataService deliveryDataService;
	RecipientDataService recipientDataService;
	PaymentDataService paymentDataService;
	AccountDataService accountDataService;
	FareDataService fareDataService;

	LoginPanel loginPanel;
	QueryOrderPanel queryOrderPanel;

	CourierPanel courierPanel;
	BusinessPanel businessPanel;
	CenterPanel centerPanel;
	FinancePanel financePanel;
	ManagerPanel managerPanel;

	// TODO 通过login获得的user等消息，可用于界面的默认以及欢迎
	private User user;

	private static JLabel loglabel;
	private JButton button_changeUser;
	private JButton button_log;
	private JSeparator separator;
	private JButton button_queryOrder;

	public static MainFrame getInstance() {
		return MAINFRAME;
	}

	/**
	 * Create the frame.
	 */
	private MainFrame() {

		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {

			e1.printStackTrace();
		}

		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = 1280;
		int height = 720;
		setSize(width, height);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		this.setLocation(w, h);
		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(jfx + e.getXOnScreen() - mx, jfy + e.getYOnScreen() - my);
			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mx = e.getXOnScreen();
				my = e.getYOnScreen();
				jfx = getX();
				jfy = getY();
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(null);

		controlpanel = new JPanel();
		controlpanel.setBounds(0, 0, 1280, 36);
		controlpanel.setBorder(null);
		contentPane.add(controlpanel);

		JButton btnExit = new JButton("退出");
		btnExit.setBounds(1208, 0, 72, 30);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		JButton btnMin = new JButton("返回");
		btnMin.setBounds(1137, 0, 71, 30);
		btnMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				beforePane();
			}
		});
		controlpanel.setLayout(null);
		controlpanel.add(btnMin);
		controlpanel.add(btnExit);

		button_changeUser = new JButton("\u5207\u6362\u8D26\u53F7");
		button_changeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(loginPanel);
			}
		});
		button_changeUser.setBounds(6, 0, 90, 30);
		controlpanel.add(button_changeUser);

		button_log = new JButton("\u67E5\u770B\u65E5\u5FD7");
		button_log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 显示log文件夹
				try {
					String[] cmd = new String[5];
					cmd[0] = "cmd";
					cmd[1] = "/c";
					cmd[2] = "start";
					cmd[3] = " ";
					cmd[4] = "e://logs";
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					logger.error("打开日志文件夹失败");
					showWarn("打开日志文件夹失败");
					e1.printStackTrace();
				}
			}
		});
		button_log.setBounds(108, 0, 90, 30);
		controlpanel.add(button_log);

		button_queryOrder = new JButton("\u67E5\u8BE2\u7269\u6D41\u4FE1\u606F");
		button_queryOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeCurrentPane(queryOrderPanel);
			}
		});
		button_queryOrder.setBounds(212, 0, 109, 30);
		controlpanel.add(button_queryOrder);

		getDataService();
		getblservice();

		loginPanel = new LoginPanel(userblService);
		queryOrderPanel = new QueryOrderPanel(postblservice);

		currentPane = loginPanel;
		currentPane.setBounds(0, 36, 1280, 664);
		contentPane.add(currentPane);

		logpanel = new JPanel();
		logpanel.setBounds(0, 700, 1280, 20);
		contentPane.add(logpanel);
		logpanel.setLayout(new BorderLayout(0, 0));

		loglabel = new JLabel("  \u65E5\u5FD7\u4FE1\u606F");
		loglabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		logpanel.add(loglabel);

		separator = new JSeparator();
		logpanel.add(separator, BorderLayout.NORTH);
		setVisible(true);

	}

	private void getDataService() {
		try {
			userDataService = (UserDataService) Naming.lookup("rmi://127.0.0.1:6600/userDataService");
			postDataService = (PostDataService) Naming.lookup("rmi://127.0.0.1:6600/postDataService");
			senderDataService = (SenderDataService) Naming.lookup("rmi://127.0.0.1:6600/senderDataService");
			receiverDataService = (ReceiverDataService) Naming.lookup("rmi://127.0.0.1:6600/receiverDataService");
			goodsDataService = (GoodsDataService) Naming.lookup("rmi://127.0.0.1:6600/goodsDataService");
			jobDataService = (JobDataService) Naming.lookup("rmi://127.0.0.1:6600/jobDataService");
			departmentDataService = (DepartmentDataService) Naming.lookup("rmi://127.0.0.1:6600/departmentDataService");
			vehicleDataService = (VehicleDataService) Naming.lookup("rmi://127.0.0.1:6600/vehicleDataService");
			driverDataService = (DriverDataService) Naming.lookup("rmi://127.0.0.1:6600/driverDataService");
			bItransportDataService = (BItransportDataService) Naming
					.lookup("rmi://127.0.0.1:6600/bItransportDataService");
			stockDataService = (StockDataService) Naming.lookup("rmi://127.0.0.1:6600/stockDataService");
			iItransportDataService = (IItransportDataService) Naming
					.lookup("rmi://127.0.0.1:6600/iItransportDataService");
			deliveryDataService = (DeliveryDataService) Naming.lookup("rmi://127.0.0.1:6600/deliveryDataService");
			recipientDataService = (RecipientDataService) Naming.lookup("rmi://127.0.0.1:6600/recipientDataService");
			paymentDataService = (PaymentDataService) Naming.lookup("rmi://127.0.0.1:6600/paymentDataService");
			accountDataService = (AccountDataService) Naming.lookup("rmi://127.0.0.1:6600/accountDataService");
			fareDataService = (FareDataService) Naming.lookup("rmi://127.0.0.1:6600/fareDataService");
		} catch (Exception e1) {
			MainFrame.getLoglabel().setText("连接服务器失败");
			logger.error("连接服务器失败");
			e1.printStackTrace();
		}
	}

	private void getblservice() {
		userblService = new UserblServiceImpl(userDataService, jobDataService, departmentDataService);
		postblservice = new PostblserviceImpl(postDataService, senderDataService, receiverDataService, goodsDataService,
				userDataService, jobDataService, departmentDataService, vehicleDataService, driverDataService,
				stockDataService, bItransportDataService, iItransportDataService, deliveryDataService,
				recipientDataService);
		vehicleblservice = new VehicleblserviceImpl(vehicleDataService, departmentDataService);
		driverblservice = new DriverblserviceImpl(driverDataService, departmentDataService);
		bItransportblservice = new BItransportblserviceImpl(bItransportDataService, departmentDataService,
				vehicleDataService, driverDataService);
		stockblservice = new StockblserviceImpl(stockDataService, departmentDataService);
		iItransportblservice = new IItransportblserviceImpl(iItransportDataService, departmentDataService);
		deliveryblservice = new DeliveryblserviceImpl(deliveryDataService, userDataService, jobDataService,
				departmentDataService);
		recipientblservice = new RecipientblserviceImpl(recipientDataService);
		paymentblservice = new PaymentblserviceImpl(paymentDataService, userDataService);
		accountblservice = new AccountblserviceImpl(accountDataService);
		departmentblservice=new DepartmentblserviceImpl(departmentDataService);
		constantblservice =new ConstantblserviceImpl(departmentDataService, jobDataService, fareDataService);
	}

	public void initPanel() {

		// TODO 快递员界面
		courierPanel = new CourierPanel(postblservice, recipientblservice);

		// TODO 营业厅界面
		businessPanel = new BusinessPanel(vehicleblservice, driverblservice, bItransportblservice, postblservice,
				deliveryblservice);

		centerPanel = new CenterPanel(postblservice, stockblservice, iItransportblservice, bItransportblservice);
		financePanel = new FinancePanel(paymentblservice, postblservice, accountblservice);
		managerPanel = new ManagerPanel(userblService,departmentblservice,constantblservice);
	}

	public void changeCurrentPane(JPanel jPanel) {
		this.beforePane = currentPane;
		this.remove(currentPane);
		getContentPane().add(jPanel);
		// 设置当前的JPanel
		this.currentPane = jPanel;
		currentPane.setBounds(0, 36, 1280, 664);
		this.repaint();

		this.setVisible(true);
	}

	public void beforePane() {
		if (null != beforePane) {

			changeCurrentPane(beforePane);
		}
	}

	public static JLabel getLoglabel() {
		return loglabel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// 显示警告
	protected int showWarn(String message) {
		return JOptionPane.showConfirmDialog(this, message, "警告", JOptionPane.OK_CANCEL_OPTION);
	}

}
