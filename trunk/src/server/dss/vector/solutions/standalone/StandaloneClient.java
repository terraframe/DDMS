package dss.vector.solutions.standalone;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.runwaysdk.ProblemException;
import com.runwaysdk.ProblemIF;
import com.runwaysdk.constants.DeployProperties;

import dss.vector.solutions.util.MDSSProperties;

public class StandaloneClient extends JFrame implements ActionListener, ContainerIF
{
  /**
   * 
   */
  private static final long   serialVersionUID = -9047490035159604763L;

  private static final String EXPORT_ACTION    = "EXPORT_ACTION";

  private static final String IMPORT_ACTION    = "IMPORT_ACTION";

  static final Dimension      DIMENSION        = new Dimension(500, 400);

  private JMenuBar            menuBar;

  private JMenu               menu;

  private JMenuItem           exportItem;

  private JMenuItem           importItem;

  private JTabbedPane         contentPane;

  private ExportPanel         exportPanel;

  private ImportPanel         importPanel;

  private ControlPanel        controlPanel;

  public StandaloneClient(Locale locale)
  {
    // Try to use the native the look and feel
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e)
    {
      // Do nothing: keep the default look and feel
    }

    // Create the menu bar.
    menuBar = new JMenuBar();

    String synchronizationLabel = MDSSProperties.getString("Synchronization", locale);
    String optionLabel = MDSSProperties.getString("Synchronization_Options", locale);
    String exportLabel = MDSSProperties.getString("Export_Transactions", locale);
    String importLabel = MDSSProperties.getString("Import_Transactions", locale);

    // Build the first menu.
    menu = new JMenu(synchronizationLabel);
    menu.setMnemonic(KeyEvent.VK_A);
    menu.getAccessibleContext().setAccessibleDescription(optionLabel);
    menuBar.add(menu);

    // a group of JMenuItems
    exportItem = new JMenuItem(exportLabel, KeyEvent.VK_E);
    exportItem.setActionCommand(EXPORT_ACTION);
    exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    exportItem.addActionListener(this);
    menu.add(exportItem);

    importItem = new JMenuItem(importLabel, KeyEvent.VK_I);
    importItem.setActionCommand(IMPORT_ACTION);
    importItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
    importItem.addActionListener(this);
    menu.add(importItem);

    exportPanel = new ExportPanel(this, locale);
    importPanel = new ImportPanel(this, locale);
    controlPanel = new ControlPanel(this, locale);

    contentPane = new JTabbedPane();
    contentPane.setSize(DIMENSION);
    contentPane.add(MDSSProperties.getString("Control_Panel", locale), controlPanel);
    contentPane.add(exportLabel, exportPanel);
    contentPane.add(importLabel, importPanel);
    contentPane.setVisible(true);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setSize(DIMENSION);
    this.setJMenuBar(menuBar);
    this.add(contentPane);
    this.setResizable(true);
    this.setVisible(true);
    this.setTitle(MDSSProperties.getString("MDSS", locale));
  }

  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals(EXPORT_ACTION))
    {
      this.showExportPanel();
    }
    else if (command.equals(IMPORT_ACTION))
    {
      this.showImportPanel();
    }
  }

  public void showExportPanel()
  {
    contentPane.setSelectedComponent(exportPanel);
  }

  public void showImportPanel()
  {
    contentPane.setSelectedComponent(importPanel);
  }

  public void lock()
  {
    importPanel.lock();
    exportPanel.lock();
    controlPanel.lock();
  }

  public void unlock()
  {
    importPanel.unlock();
    exportPanel.unlock();
    controlPanel.unlock();
  }

  public static final boolean isServerUp()
  {
    try
    {
      String url = DeployProperties.getApplicationURL() + "/status.jsp";
      URL server = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) server.openConnection();
      connection.connect();

      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      in.readLine();
      in.close();

      connection.disconnect();
    }
    catch (Exception e)
    {
      return false;
    }

    return true;
  }

  public static void main(String[] args)
  {
    Locale locale = Locale.getDefault();

    if (args.length > 0)
    {
      String[] localeInfo = args[0].split("_");
      switch (localeInfo.length)
      {
        case 1:
          locale = new Locale(localeInfo[0]);
        case 2:
          locale = new Locale(localeInfo[0], localeInfo[1]);
        case 3:
          locale = new Locale(localeInfo[0], localeInfo[1], localeInfo[2]);
      }
    }

    new StandaloneClient(locale);
  }

  public static void handleError(AbstractPanel component, Throwable t)
  {
    if (t instanceof ProblemException)
    {
      String message = "";
      List<ProblemIF> problems = ( (ProblemException) t ).getProblems();

      for (ProblemIF problem : problems)
      {
        message += problem.getLocalizedMessage() + "\n";
      }
    }
    else
    {
      JOptionPane.showMessageDialog(component, t.getLocalizedMessage());
    }

    component.unlockContainer();
  }
}