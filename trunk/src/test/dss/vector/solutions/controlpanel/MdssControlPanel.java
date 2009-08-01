package dss.vector.solutions.controlpanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MdssControlPanel extends JFrame {
	private JButton startButton;
	private JButton stopButton;
	private JButton backupButton;
	private JButton restoreButton;

	private JRadioButton mozambiqueButton;
	private JRadioButton malawiButton;
	private JRadioButton zambiaButton;

	public MdssControlPanel() {
	}

	public static void main(String[] args) {

		MdssControlPanel mcp = new MdssControlPanel();
		mcp.initialize();
	}

	private void initialize() {
		setSize(400, 200);
		setTitle("MDSS Control Panel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);

		mainPanel.add(this.createCountryPanel(), "Center");
		mainPanel.add(this.createActionsPanel(), "South");

		this.setButtons(false);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtons(true);
			}
		});
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtons(false);
			}
		});

		this.setVisible(true);
	}

	private void setButtons(boolean started) {
		startButton.setEnabled(!started);
		stopButton.setEnabled(started);
		backupButton.setEnabled(!started);
		restoreButton.setEnabled(!started);

		mozambiqueButton.setEnabled(!started);
		malawiButton.setEnabled(!started);
		zambiaButton.setEnabled(!started);
	}

	private JPanel createCountryPanel() {
		JPanel countryPanel = new JPanel();
		countryPanel.setLayout(new GridLayout(0, 1));

		// Create the radio buttons.
		mozambiqueButton = new JRadioButton("Mozambique");
		// mozambiqueButton.setMnemonic(KeyEvent.VK_Z);
		// mozambiqueButton.setActionCommand("Mozambique");
		mozambiqueButton.setSelected(true);

		malawiButton = new JRadioButton("Malawi");
		// malawiButton.setMnemonic(KeyEvent.VK_W);
		// malawiButton.setActionCommand("Malawi");

		zambiaButton = new JRadioButton("Zambia");
		// zambiaButton.setMnemonic(KeyEvent.VK_Z);
		// zambiaButton.setActionCommand("Zambia");

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(mozambiqueButton);
		group.add(malawiButton);
		group.add(zambiaButton);

		countryPanel.add(mozambiqueButton);
		countryPanel.add(malawiButton);
		countryPanel.add(zambiaButton);

		return countryPanel;
	}

	private JPanel createActionsPanel() {
		JPanel actionsPanel = new JPanel();
		actionsPanel.setLayout(new FlowLayout());

		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		backupButton = new JButton("Backup");
		restoreButton = new JButton("Restore");

		actionsPanel.add(startButton);
		actionsPanel.add(stopButton);
		actionsPanel.add(backupButton);
		actionsPanel.add(restoreButton);

		return actionsPanel;
	}
}
