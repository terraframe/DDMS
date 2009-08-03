package dss.vector.solutions.controlpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.ibm.icu.text.MessageFormat;

public class MdssControlPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public final int HEIGHT = 300;
	public final int WIDTH = 400; 
	
	private static final String TITLE = "title";

	private static final String START = "start";
	private static final String STOP = "stop";
	private static final String BACKUP = "backup";
	private static final String RESTORE = "restore";
	
	private static final String MOZAMBIQUE = "mozambique";
	private static final String MALAWI = "malawi";
	private static final String ZAMBIA = "zambia";

	private ResourceBundle bundle;
	
	private JButton startButton;
	private JButton stopButton;
	private JButton backupButton;
	private JButton restoreButton;

	private ButtonGroup group;
	private JRadioButton mozambiqueButton;
	private JRadioButton malawiButton;
	private JRadioButton zambiaButton;
	
	private JTextArea outputTextArea;
	
	final JFileChooser fc = new JFileChooser();

	public MdssControlPanel() {
		this(Locale.getDefault());
	}

	public MdssControlPanel(Locale locale) {
		bundle = ResourceBundle.getBundle("MdssControlPanel", locale);
	}
	
	public static void main(String[] args) {
		Locale locale = Locale.getDefault();
		if (args.length > 0) {
			String[] localeInfo = args[0].split("_");
			switch (localeInfo.length) {
				case 1:
					locale = new Locale(localeInfo[0]);
				case 2:
					locale = new Locale(localeInfo[0], localeInfo[1]);
				case 3:
					locale = new Locale(localeInfo[0], localeInfo[1], localeInfo[2]);
			}
		}
		MdssControlPanel mcp = new MdssControlPanel(locale);
		
		mcp.initialize();
	}

	private void initialize() {
		setSize(WIDTH, HEIGHT);

		setTitle(this.getText(TITLE));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);

		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BorderLayout());
		userPanel.add(this.createCountryPanel(), "North");
		userPanel.add(this.createActionsPanel(), "South");
		mainPanel.add(userPanel, "North");
		
		outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		mainPanel.add(new JScrollPane(outputTextArea), "Center");

		this.setButtons(false);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtons(true);
				runCommand(START, false);
			}
		});

		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setButtons(false);
				runCommand(STOP, false);
			}
		});

		backupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runCommand(BACKUP, true);
			}
		});

		restoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runCommand(RESTORE, true);
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
		mozambiqueButton = new JRadioButton(this.getText(MOZAMBIQUE));
		mozambiqueButton.setMnemonic(KeyEvent.VK_Z);
		mozambiqueButton.setActionCommand(MOZAMBIQUE);
		mozambiqueButton.setSelected(true);

		malawiButton = new JRadioButton(this.getText(MALAWI));
		malawiButton.setMnemonic(KeyEvent.VK_W);
		malawiButton.setActionCommand(MALAWI);

		zambiaButton = new JRadioButton(this.getText(ZAMBIA));
		zambiaButton.setMnemonic(KeyEvent.VK_Z);
		zambiaButton.setActionCommand(ZAMBIA);

		// Group the radio buttons.
		group = new ButtonGroup();
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

		startButton = new JButton(this.getText(START));
		stopButton = new JButton(this.getText(STOP));
		backupButton = new JButton(this.getText(BACKUP));
		restoreButton = new JButton(this.getText(RESTORE));

		actionsPanel.add(startButton);
		actionsPanel.add(stopButton);
		actionsPanel.add(backupButton);
		actionsPanel.add(restoreButton);

		return actionsPanel;
	}
	
	private void runCommand(String commandKey, boolean selectFile) {
		String[] parameters = new String[2];
		parameters[0] = group.getSelection().getActionCommand();
		
		if (selectFile) {
	        int returnVal = fc.showOpenDialog(this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            parameters[1] = fc.getSelectedFile().getAbsolutePath();
	        } else {
	        	return;
	        }
		}
		String command = MessageFormat.format(this.bundle.getString("command." + commandKey), parameters);
		
		outputTextArea.setText(null);
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(command);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;

            while((line=input.readLine()) != null) {
                outputTextArea.append(line + "\n");
            }

            int exitVal = pr.waitFor();
            outputTextArea.append("Exit code: "+exitVal);

        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }

	}
	
	private String getText(String key) {
		return this.bundle.getString("text." + key);
	}
}
