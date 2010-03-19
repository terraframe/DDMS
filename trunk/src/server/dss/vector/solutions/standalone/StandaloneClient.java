package dss.vector.solutions.standalone;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.terraframe.mojo.constants.DeployProperties;

import dss.vector.solutions.util.MDSSProperties;

public class StandaloneClient extends JFrame implements ActionListener
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

  private JPanel              backupPanel;

  public StandaloneClient()
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

    String synchronizationLabel = MDSSProperties.getString("Synchronization");
    String optionLabel = MDSSProperties.getString("Synchronization_Options");
    String exportLabel = MDSSProperties.getString("Export_Transactions");
    String importLabel = MDSSProperties.getString("Import_Transactions");

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

    exportPanel = new ExportPanel();
    importPanel = new ImportPanel();
    backupPanel = new BackupPanel();

    contentPane = new JTabbedPane();
    contentPane.setSize(DIMENSION);
    contentPane.add(MDSSProperties.getString("Control_Panel"), backupPanel);
    contentPane.add(exportLabel, exportPanel);
    contentPane.add(importLabel, importPanel);
    contentPane.setVisible(true);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setSize(DIMENSION);
    this.setJMenuBar(menuBar);
    this.add(contentPane);
    this.setResizable(true);
    this.setVisible(true);
    this.setTitle(MDSSProperties.getString("MDSS"));
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

  public static void main(String args[])
  {
    new StandaloneClient();
  }

  public static final boolean isServerUp()
  {
    try
    {
      String url = DeployProperties.getApplicationURL();
      URL server = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) server.openConnection();
      connection.connect();

      connection.disconnect();
    }
    catch (Exception e)
    {
      return false;
    }

    return true;
  }

}