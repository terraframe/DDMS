package dss.vector.solutions.standalone;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.terraframe.mojo.constants.DeployProperties;
import com.terraframe.mojo.dataaccess.transaction.TransactionImportManager;
import com.terraframe.mojo.session.StartSession;

import dss.vector.solutions.util.MDSSProperties;

public class ImportPanel extends JPanel implements ActionListener, PropertyChangeListener
{
  /**
   * 
   */
  private static final long   serialVersionUID = -4533704205773557158L;

  private static final String IMPORT_COMMAND   = "IMPORT";

  private static final String REFRESH_COMMAND  = "REFRESH";

  private FileBrowser         browser;

  private JButton             importButton;

  private JProgressBar        progressBar;

  private JPanel              buttonPanel;

  private JLabel              statusLabel;

  private JPanel              statusPanel;

  public ImportPanel()
  {
    // Create the content-pane-to-be.
    super(new BorderLayout());

    this.statusLabel = new JLabel(MDSSProperties.getString("Ready_To_Import"));

    this.browser = new FileBrowser(false);
    this.browser.setBorder(BorderFactory.createTitledBorder(MDSSProperties.getString("Import_transaction_file")));

    this.importButton = new JButton(MDSSProperties.getString("Import"));
    this.importButton.setActionCommand(IMPORT_COMMAND);
    this.importButton.addActionListener(this);

    this.progressBar = new JProgressBar();
    this.progressBar.setValue(0);
    this.progressBar.setVisible(true);

    this.buttonPanel = new JPanel(new BorderLayout());
    this.buttonPanel.add(progressBar, BorderLayout.CENTER);
    this.buttonPanel.add(importButton, BorderLayout.EAST);

    this.statusPanel = new JPanel(new BorderLayout());
    this.statusPanel.add(statusLabel, BorderLayout.NORTH);
    this.statusPanel.add(buttonPanel, BorderLayout.CENTER);

    this.add(browser, BorderLayout.CENTER);
    this.add(statusPanel, BorderLayout.SOUTH);

    this.setSize(StandaloneClient.DIMENSION);

    this.updateServerStatus();
  }

  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals(IMPORT_COMMAND))
    {
      this.executeImport();
    }
    else if (command.equals(REFRESH_COMMAND))
    {
      this.updateServerStatus();
    }
  }

  public void propertyChange(PropertyChangeEvent evt)
  {
    if ("progress" == evt.getPropertyName())
    {
      int progress = (Integer) evt.getNewValue();

      progressBar.setValue(progress);

      if (progress >= 100)
      {
        this.complete();
      }
    }
  }

  private void executeImport()
  {
    if (!this.isServerUp())
    {
      this.importButton.setEnabled(false);
      this.statusLabel.setText(MDSSProperties.getString("Import_in_progress"));

      this.importFile();
    }
    else
    {
      this.updateServerStatus();
    }
  }

  @StartSession
  private void importFile()
  {
    File file = browser.getFile();
    
    try
    {
      
      if (file == null || !file.exists() || file.isDirectory())
      {
        throw new RuntimeException("Provide a file to import.");
      }

       TransactionImportManager manager = new
       TransactionImportManager(file.getAbsolutePath());
//       manager.addPropertyChangeListener(this);
       manager.importTransactions();

//      ImportManager manager = new ImportManager();
//      manager.addPropertyChangeListener(this);
//      manager.execute();
    }
    catch (Exception e)
    {
      this.errorHandler(e);
      this.complete();
    }
  }
  
  private void errorHandler(Exception e)
  {
    String message = MDSSProperties.getString("Import_Problem");

    try
    {
      message = e.getLocalizedMessage();
    }
    catch (Throwable t)
    {
      t.printStackTrace();
      // Use the default error message
    }

    JOptionPane.showMessageDialog(this, message);
  }

  private void complete()
  {
    this.importButton.setEnabled(true);
    this.progressBar.setValue(0);
    this.statusLabel.setText(MDSSProperties.getString("Import_Complete"));
  }

  private final void updateServerStatus()
  {
    boolean up = this.isServerUp();

    if (up)
    {
      this.statusLabel.setText(MDSSProperties.getString("Server_Down"));
      this.importButton.setText(MDSSProperties.getString("Refresh"));
      this.importButton.setActionCommand(REFRESH_COMMAND);
    }
    else
    {
      this.statusLabel.setText(MDSSProperties.getString("Ready_To_Import"));
      this.importButton.setText(MDSSProperties.getString("Import"));
      this.importButton.setActionCommand(IMPORT_COMMAND);
    }
  }

  private final boolean isServerUp()
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
