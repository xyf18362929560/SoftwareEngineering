package nju.express.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.rmi.Naming;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.apache.log4j.Logger;

import nju.express.blservice.Collectionblservice;
import nju.express.blservice.Postblservice;
import nju.express.blservice.UserblService;
import nju.express.blservice.Vehicleblservice;
import nju.express.blservice.impl.CollectionblserviceImpl;
import nju.express.blservice.impl.PostblserviceImpl;
import nju.express.blservice.impl.UserblServiceImpl;
import nju.express.blservice.impl.VehicleblserviceImpl;
import nju.express.dataservice.CollectionDataService;
import nju.express.dataservice.DepartmentDataService;
import nju.express.dataservice.GoodsDataService;
import nju.express.dataservice.JobDataService;
import nju.express.dataservice.MyOrderDataService;
import nju.express.dataservice.PostDataService;
import nju.express.dataservice.ReceiverDataService;
import nju.express.dataservice.SenderDataService;
import nju.express.dataservice.UserDataService;
import nju.express.dataservice.VehicleDataService;
import nju.express.ui.business.BusinessPanel;
import nju.express.ui.courier.CourierPanel;
import nju.express.vo.Department;
import nju.express.vo.Job;
import nju.express.vo.User;

public class MainFrame extends JFrame {

	
	
	private static final long serialVersionUID = -5912660057088784794L;
	

	private static Logger logger = Logger.getLogger(MainFrame.class);
	private static final MainFrame MAINFRAME =new MainFrame();
	
	
	private JPanel contentPane;
	private JPanel currentPane;
	int mx, my, jfx, jfy;
	private JPanel controlpanel;
	private JPanel logpanel;
	
	UserblService userblService;
	Postblservice postblservice;
	Collectionblservice collectionblservice;
	Vehicleblservice vehicleblservice;

	UserDataService userDataService;
	PostDataService postDataService;
	SenderDataService senderDataService;
	ReceiverDataService receiverDataService;
	GoodsDataService goodsDataService;
	CollectionDataService collectionDataService;
	JobDataService jobDataService;
	DepartmentDataService departmentDataService;
	VehicleDataService vehicleDataService;
	MyOrderDataService orderDataService;
	
	LoginPanel loginPanel;
	CourierPanel courierPanel;
	BusinessPanel businessPanel;
	
	private User user;
	private Job job;
	private Department department;
	
	public static  MainFrame getInstance(){
		return MAINFRAME;
	}

	/**
	 * Create the frame.
	 */
	private MainFrame() {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
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
		contentPane.setLayout(new BorderLayout(0, 0));

		controlpanel = new JPanel(); // (1280,36)
		controlpanel.setBorder(null);
		contentPane.add(controlpanel, BorderLayout.NORTH);

		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		JButton btnMax = new JButton("Max");
		btnMax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (getExtendedState() == JFrame.MAXIMIZED_BOTH) {
					setExtendedState(JFrame.NORMAL);
				} else {

					setExtendedState(JFrame.MAXIMIZED_BOTH);
				}
			}
		});
		JButton btnMin = new JButton("Min");
		btnMin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		GroupLayout gl_controlpanel = new GroupLayout(controlpanel);
		gl_controlpanel.setHorizontalGroup(gl_controlpanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_controlpanel.createSequentialGroup().addContainerGap(1067, Short.MAX_VALUE)
						.addComponent(btnMin).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnMax)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExit)));
		gl_controlpanel
				.setVerticalGroup(gl_controlpanel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_controlpanel.createSequentialGroup()
								.addGroup(gl_controlpanel.createParallelGroup(Alignment.BASELINE).addComponent(btnExit)
										.addComponent(btnMax).addComponent(btnMin))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		controlpanel.setLayout(gl_controlpanel);

		try {
			userDataService = (UserDataService) Naming.lookup("rmi://127.0.0.1:6600/userDataService");
			postDataService = (PostDataService) Naming.lookup("rmi://127.0.0.1:6600/postDataService");
			senderDataService = (SenderDataService) Naming.lookup("rmi://127.0.0.1:6600/senderDataService");
			receiverDataService = (ReceiverDataService) Naming.lookup("rmi://127.0.0.1:6600/receiverDataService");
			goodsDataService = (GoodsDataService) Naming.lookup("rmi://127.0.0.1:6600/goodsDataService");
			collectionDataService = (CollectionDataService) Naming.lookup("rmi://127.0.0.1:6600/collectionDataService");
			jobDataService = (JobDataService) Naming.lookup("rmi://127.0.0.1:6600/jobDataService");
			departmentDataService = (DepartmentDataService) Naming.lookup("rmi://127.0.0.1:6600/departmentDataService");
			vehicleDataService= (VehicleDataService) Naming.lookup("rmi://127.0.0.1:6600/vehicleDataService");
			orderDataService=(MyOrderDataService) Naming.lookup("rmi://127.0.0.1:6600/orderDataService");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		userblService = new UserblServiceImpl(userDataService,jobDataService,departmentDataService);
		loginPanel = new LoginPanel(userblService);

		postblservice = new PostblserviceImpl(postDataService, senderDataService, receiverDataService, goodsDataService,
				userDataService, collectionDataService,orderDataService);
		collectionblservice = new CollectionblserviceImpl(collectionDataService, userDataService,departmentDataService);
		vehicleblservice=new VehicleblserviceImpl(vehicleDataService, departmentDataService);
		
		//TODO other blservice
		courierPanel = new CourierPanel(postblservice);

		// TODO 营业厅界面
		businessPanel = new BusinessPanel(vehicleblservice);

		// TODO 初始化currPane应该为loginPanel，为了测试改成其他的
		// currentPane=loginPanel;
		currentPane = businessPanel;
		contentPane.add(currentPane, BorderLayout.CENTER);

		logpanel = new JPanel();
		contentPane.add(logpanel, BorderLayout.SOUTH);

		JLabel label = new JLabel("\u65E5\u5FD7\u4FE1\u606F");
		GroupLayout gl_panel = new GroupLayout(logpanel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap().addComponent(label).addContainerGap(1222, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
						.addComponent(label).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		logpanel.setLayout(gl_panel);

		setVisible(true);

	}

	public void changeCurrentPane(JPanel jPanel) {

		this.remove(currentPane);
		// 添加需要显示的JPanel
		getContentPane().add(jPanel);
		// 设置当前的JPanel
		this.currentPane = jPanel;
		this.repaint();

		this.setVisible(true);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
