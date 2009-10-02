package dss.vector.solutions.controlpanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MdssControlPanel extends JFrame {
	private static final long serialVersionUID = 1L;

	public final int HEIGHT = 300;
	public final int WIDTH = 400;

	private static final String URL = "url.server";
	private static final String TITLE = "title";

	private static final String START = "start";
	private static final String STOP = "stop";
	private static final String BACKUP = "backup";
	private static final String RESTORE = "restore";
	
	private static final String COUNTRY = "country.";

	private ResourceBundle bundle;
	private Map<String, String> countries = new HashMap<String, String>();

	private JButton startButton;
	private JButton stopButton;
	private JButton backupButton;
	private JButton restoreButton;

	private ButtonGroup group = new ButtonGroup();

	private JTextArea outputTextArea;

	final JFileChooser fc = new JFileChooser();

	public MdssControlPanel() {
		this(Locale.getDefault());
	}

	public MdssControlPanel(Locale locale) {
		bundle = ResourceBundle.getBundle("MdssControlPanel", locale);
		
		Enumeration<String> e = bundle.getKeys();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			if (key.startsWith(COUNTRY)) {
				String countryName = key.substring(COUNTRY.length()).toLowerCase();
				String countryText = bundle.getString(key);
				countries.put(countryName, countryText);
			}
		}
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

		String status = this.checkMdssStatus();
		if (status != null) {
			Enumeration<AbstractButton> e = group.getElements();
			while (e.hasMoreElements()) {
				AbstractButton button = e.nextElement();
				if (button.getActionCommand().equals(status.toLowerCase())) {
					button.setSelected(true);
				}
			}
			setButtons(true);
		} else {
			setButtons(false);
		}

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});

		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});

		backupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backup();
			}
		});

		restoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restore();
			}
		});

		this.setVisible(true);
	}

	private String checkMdssStatus() {
		String status = null;
		URL mdss;
		try {
			mdss = new URL(this.bundle.getString(URL) + "/status.jsp");
	        URLConnection mdssConnection = mdss.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(mdssConnection.getInputStream()));
	        status=in.readLine();
	        in.close();
		} catch (MalformedURLException e) {
			// This shouldn't happen
			e.printStackTrace();
		} catch (IOException e) {
			// Do nothing--if we can't get there, then MDSS isn't running
		}
		return status;
	}

	private void setButtons() {
		this.setButtons(this.checkMdssStatus() != null );
	}

	private void setButtons(boolean started) {
		startButton.setEnabled(!started);
		stopButton.setEnabled(started);
		backupButton.setEnabled(!started);
		restoreButton.setEnabled(!started);

		Enumeration<AbstractButton> e = group.getElements();
		while (e.hasMoreElements()) {
			AbstractButton button = e.nextElement();
			button.setEnabled(!started);
		}
	}

	private void disableButtons() {
		startButton.setEnabled(false);
		stopButton.setEnabled(false);
		backupButton.setEnabled(false);
		restoreButton.setEnabled(false);
	}

	private JPanel createCountryPanel() {
		JPanel countryPanel = new JPanel();
		countryPanel.setLayout(new GridLayout(0, 1));

		boolean isFirst = true;
		for (String country: countries.keySet()) {
			JRadioButton countryButton = new JRadioButton(countries.get(country));
			//countryButton.setMnemonic(KeyEvent.VK_Z);
			countryButton.setActionCommand(country);
			if (isFirst) {
				countryButton.setSelected(true);
				isFirst = false;
			}
			group.add(countryButton);
			countryPanel.add(countryButton);
		}

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

	private File chooseFile(boolean chooseDirectoryOnly) {
		if (chooseDirectoryOnly) {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		} else {
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        } else {
        	return null;
        }
	}

	private void runCommand(String commandKey, String path, String file) {
		disableButtons();

		Object[] parameters = new Object[3];
		parameters[0] = group.getSelection().getActionCommand();
		parameters[1] = path;
		parameters[2] = file;

		String command = MessageFormat.format(this.bundle.getString("command." + commandKey), parameters);

		outputTextArea.setText(null);
        try {
            Runtime rt = Runtime.getRuntime();
            final Process pr = rt.exec(command);

			Thread outputThread = new Thread() {
				public void run() {
		            outputTextArea.setText(null);
		            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

		            String line=null;

		            try {
						while((line=input.readLine()) != null) {
						    outputTextArea.append(line + "\n");
						    outputTextArea.setCaretPosition(outputTextArea.getText().length());
						}
			            int exitVal = pr.waitFor();
			            outputTextArea.append("Exit code: "+exitVal);
					} catch (IOException e) {
						outputTextArea.append(e.toString());
					} catch (InterruptedException e) {
						// Do nothing);
					}

			        setButtons();
				}
			};
			outputThread.start();
        } catch(Exception e) {
            outputTextArea.append(e.toString());
        }
	}

	private String getText(String key) {
		return this.bundle.getString("text." + key);
	}

	private void start() {
		setButtons(true);
		runCommand(START, null, null);
		setButtons();
	}

	private void stop() {
		setButtons(false);
		runCommand(STOP, null, null);
		setButtons();
	}

	private void backup() {
		File file = chooseFile(true);
		if (file != null) {
			runCommand(BACKUP, file.getAbsolutePath(), group.getSelection().getActionCommand());
			/*
			outputTextArea.setText(null);

			final PipedOutputStream out = new PipedOutputStream();
			PrintStream ps = new PrintStream(out);

			final Backup backup = new Backup(ps, group.getSelection().getActionCommand(), group.getSelection().getActionCommand(), true, true);
			Thread backupThread = new Thread() {
				public void run() {
					backup.backup();
				}
			};
			backupThread.start();


			Thread outputThread = new Thread() {
				public void run() {
					try {
						BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(out)));
						String line = null;
						while((line=in.readLine()) != null) {
							outputTextArea.append(line + "\n");
						}
						outputTextArea.append("Done!");
					} catch (IOException e) {
						// Do nothing -- streams close themselves
					}
					outputTextArea.append("Done!");
				}
			};
			outputThread.start();
			*/
		}
	}

	private void restore() {
		File file = chooseFile(false);
		if (file != null) {
			runCommand(RESTORE, file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-(file.getName().length()+1)), file.getName());
		}
	}

}
