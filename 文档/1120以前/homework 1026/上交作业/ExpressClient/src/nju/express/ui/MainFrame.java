package nju.express.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.rmi.Naming;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.apache.log4j.Logger;

import nju.express.blservice.UserblService;
import nju.express.blservice.impl.UserblServiceImpl;
import nju.express.dataservice.UserDataService;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(MainFrame.class);
	private JPanel contentPane;
	private JPanel currentPane;
	int mx, my, jfx, jfy;
	private JPanel controlpanel;

	LoginPanel loginPanel;
	UserDataService userDataService;
	UserblService userblService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
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
			userDataService = (UserDataService) Naming.lookup("rmi://127.0.0.1:6600/test1");
		} catch (Exception e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		}
		userblService = new UserblServiceImpl(userDataService);
		loginPanel = new LoginPanel(userblService);
		currentPane=loginPanel;
		contentPane.add(currentPane, BorderLayout.CENTER);
		setVisible(true);
	}
	public void changeCurrentPane(JPanel jPanel){
		
				this.remove(currentPane);
				//添加需要显示的JPanel
				this.add(jPanel);
				//设置当前的JPanel
				this.currentPane = jPanel;
				this.repaint();
				this.setVisible(true);
	}

}
