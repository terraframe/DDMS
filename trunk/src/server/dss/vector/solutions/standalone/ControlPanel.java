package dss.vector.solutions.standalone;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.runwaysdk.constants.DeployProperties;

import dss.vector.solutions.util.MDSSProperties;

public class ControlPanel extends AbstractPanel
{
  /**
   * 
   */
  private static final long   serialVersionUID      = -6658235304142152484L;

  private static final String SESSION_TIME_PROPERTY = "sessionTime";

  private static final String PROPERTIES_FILENAME   = "common.properties";

  private static final String START                 = "start";

  private static final String STOP                  = "stop";

  private static final String BACKUP                = "backup";

  private static final String RESTORE               = "restore";

  private static final String TIMEOUT               = "timeout";

  private static final String COUNTRY               = "country.";

  private static final String DEFAULT_TIMEOUT       = "86400";

  private ResourceBundle      bundle;

  private Map<String, String> countries             = new HashMap<String, String>();

  private JButton             startButton;

  private JButton             stopButton;

  private JButton             backupButton;

  private JButton             restoreButton;

  private JTextField          timeoutField;

  private ButtonGroup         group                 = new ButtonGroup();

  private JTextArea           outputTextArea;

  final JFileChooser          fc                    = new JFileChooser();

  public ControlPanel(ContainerIF container)
  {
    this(container, Locale.getDefault());
  }

  public ControlPanel(ContainerIF container, Locale locale)
  {
    super(container, new BorderLayout());

    // MdssLog.debug(System.getProperty("user.dir"));
    bundle = ResourceBundle.getBundle("MdssControlPanel", locale);

    Enumeration<String> e = bundle.getKeys();
    while (e.hasMoreElements())
    {
      String key = e.nextElement();
      if (key.startsWith(COUNTRY))
      {
        String countryName = key.substring(COUNTRY.length()).toLowerCase();
        String countryText = bundle.getString(key);
        countries.put(countryName, countryText);
      }
    }

    this.setSize(StandaloneClient.DIMENSION);

    JPanel userPanel = new JPanel();
    userPanel.setLayout(new BorderLayout());
    userPanel.add(this.createCountryPanel(), "North");
    userPanel.add(this.createActionsPanel(), "South");
    userPanel.add(this.createOptionsPanel(), "West");
    this.add(userPanel, "North");

    outputTextArea = new JTextArea();
    outputTextArea.setEditable(false);
    this.add(new JScrollPane(outputTextArea), "Center");

    if (StandaloneClient.isServerUp())
    {
      Enumeration<AbstractButton> enu = group.getElements();

      while (enu.hasMoreElements())
      {
        String country = MDSSProperties.getString("Country");

        AbstractButton button = enu.nextElement();

        if (button.getActionCommand().equals(country))
        {
          button.setSelected(true);
        }
      }
      setButtons(true);
    }
    else
    {
      setButtons(false);
    }

    startButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        start();
      }
    });

    stopButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        stop();
      }
    });

    backupButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        backup();
      }
    });

    restoreButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        restore();
      }
    });

    this.setVisible(true);
  }

  void setButtons()
  {
    this.setButtons(StandaloneClient.isServerUp());
  }

  private void setButtons(boolean started)
  {
    startButton.setEnabled(!started);
    stopButton.setEnabled(started);
    backupButton.setEnabled(!started);
    restoreButton.setEnabled(!started);

    Enumeration<AbstractButton> e = group.getElements();
    while (e.hasMoreElements())
    {
      AbstractButton button = e.nextElement();
      button.setEnabled(!started);
    }
  }

  private void disableButtons()
  {
    startButton.setEnabled(false);
    stopButton.setEnabled(false);
    backupButton.setEnabled(false);
    restoreButton.setEnabled(false);
  }

  private JPanel createCountryPanel()
  {
    JPanel countryPanel = new JPanel();
    countryPanel.setLayout(new GridLayout(0, 1));

    boolean isFirst = true;
    for (String country : countries.keySet())
    {
      JRadioButton countryButton = new JRadioButton(countries.get(country));

      countryButton.setActionCommand(country);
      if (isFirst)
      {
        countryButton.setSelected(true);
        isFirst = false;
      }
      group.add(countryButton);
      countryPanel.add(countryButton);
    }

    return countryPanel;
  }

  private JPanel createActionsPanel()
  {
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

  private JPanel createOptionsPanel()
  {
    JPanel optionsPanel = new JPanel();
    optionsPanel.setLayout(new GridLayout(1, 2));

    optionsPanel.add(new JLabel(this.getText(TIMEOUT) + " "));

    timeoutField = new JTextField(this.getTimeout());

    optionsPanel.add(timeoutField);

    return optionsPanel;
  }

  private String getTimeout()
  {
    String timeout = DEFAULT_TIMEOUT;
    BufferedReader in = null;
    try
    {
      in = new BufferedReader(new FileReader(PROPERTIES_FILENAME));
      String line;
      while ( ( line = in.readLine() ) != null)
      {
        if (line.trim().startsWith(SESSION_TIME_PROPERTY))
        {
          int equalsPos = line.lastIndexOf('=');
          if (equalsPos > 0)
          {
            String sessionTime = line.substring(equalsPos + 1).trim();

            timeout = ( (Integer) Integer.parseInt(sessionTime) ).toString();
          }
          break;
        }
      }
    }
    catch (IOException e)
    {
      // Do nothing...use default
      // MdssLog.debug(e);
    }
    catch (NumberFormatException e)
    {
      // Do nothing...use default
      // MdssLog.debug(e);
    }
    finally
    {
      if (in != null)
      {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          // Do nothing
        }
      }
    }
    return timeout;
  }

  private boolean isTimeoutValid(String timeout)
  {
    try
    {
      Integer.parseInt(timeout);
    }
    catch (NumberFormatException e)
    {
      return false;
    }
    return true;
  }

  private boolean setTimeout(String timeout)
  {
    String directory = DeployProperties.getDeployBin();
    String propertiesPath = directory + "/" + PROPERTIES_FILENAME;

    return this.writeProperty(propertiesPath, SESSION_TIME_PROPERTY, timeout);
  }

  private boolean writeProperty(String propertyPath, String propertyName, String propertyValue)
  {
    boolean success = false;

    BufferedReader in = null;
    BufferedWriter out = null;

    File newprops = new File(propertyPath + ".new");
    File props = new File(propertyPath);

    try
    {
      in = new BufferedReader(new FileReader(props));
      out = new BufferedWriter(new FileWriter(newprops));
      String line;
      while ( ( line = in.readLine() ) != null)
      {
        if (line.trim().startsWith(propertyName))
        {
          out.write(propertyName + "=" + propertyValue);
        }
        else
        {
          out.write(line);
        }
        out.write("\n");
      }
      success = true;
    }
    catch (Exception e)
    {
      success = false;
    }
    finally
    {
      if (in != null)
      {
        try
        {
          in.close();
        }
        catch (IOException e)
        {
          success = false;
        }
      }
      if (out != null)
      {
        try
        {
          out.close();
        }
        catch (IOException e)
        {
          success = false;
        }
      }
    }

    if (success)
    {
      success = false;
      File oldprops = new File(propertyPath + ".old");

      if (props.renameTo(oldprops))
      {
        if (newprops.renameTo(props))
        {
          oldprops.delete();
          success = true;
        }
      }
    }

    return success;
  }

  private File chooseFile(boolean chooseDirectoryOnly)
  {
    if (chooseDirectoryOnly)
    {
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }
    else
    {
      fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    int returnVal = fc.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
      return fc.getSelectedFile();
    }
    else
    {
      return null;
    }
  }

  private void runCommand(String commandKey, String path, String file)
  {
    disableButtons();

    Object[] parameters = new Object[3];
    parameters[0] = group.getSelection().getActionCommand();
    parameters[1] = path;
    parameters[2] = file;

    String command = MessageFormat.format(this.bundle.getString("command." + commandKey), parameters);

    outputTextArea.setText(null);
    try
    {
      Runtime rt = Runtime.getRuntime();
      final Process pr = rt.exec(command);

      Thread outputThread = new Thread()
      {
        public void run()
        {
          outputTextArea.setText(null);
          BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

          String line = null;

          try
          {
            while ( ( line = input.readLine() ) != null)
            {
              outputTextArea.append(line + "\n");
              outputTextArea.setCaretPosition(outputTextArea.getText().length());
            }
            int exitVal = pr.waitFor();
            outputTextArea.append("Exit code: " + exitVal);
          }
          catch (IOException e)
          {
            outputTextArea.append(e.toString());
          }
          catch (InterruptedException e)
          {
            // Do nothing);
          }

          setButtons();
        }
      };
      outputThread.start();
    }
    catch (Exception e)
    {
      outputTextArea.append(e.toString());
    }
  }

  private String getText(String key)
  {
    return this.bundle.getString("text." + key);
  }

  private void start()
  {
    if (isTimeoutValid(timeoutField.getText()))
    {
      if (setTimeout(timeoutField.getText()))
      {
        setButtons(true);
        runCommand(START, null, null);
        setButtons();
      }
      else
      {
        JOptionPane.showMessageDialog(this, this.getText("error.properties"));
      }
    }
    else
    {
      JOptionPane.showMessageDialog(this, this.getText("error.timeout"));
    }
  }

  private void stop()
  {
    setButtons(false);
    runCommand(STOP, null, null);
    setButtons();
  }

  private void backup()
  {
    File file = chooseFile(true);
    if (file != null)
    {
      this.lockContainer();
      
      new BackupManager(this, file).execute();
    }
  }

  private void restore()
  {
    File file = chooseFile(false);
    if (file != null)
    {
      this.lockContainer();

      new RestoreManager(this, file).execute();
    }
  }

  public JTextArea getTextArea()
  {
    return outputTextArea;
  }

  @Override
  public void unlock()
  {
    this.setButtons();
  }
  
  @Override
  public void lock()
  {
    this.disableButtons();
  }
}
