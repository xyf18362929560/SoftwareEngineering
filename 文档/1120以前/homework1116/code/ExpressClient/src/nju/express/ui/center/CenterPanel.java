package nju.express.ui.center;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class CenterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3036256395652948733L;

	/**
	 * Create the panel.
	 */
	public CenterPanel() {
		this.setSize(1280, 674);

		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 18));
		add(tabbedPane, BorderLayout.CENTER);
		
		
	}

}
