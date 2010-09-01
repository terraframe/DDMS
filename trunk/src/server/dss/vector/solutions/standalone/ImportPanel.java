package dss.vector.solutions.standalone;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.runwaysdk.ProblemException;
import com.runwaysdk.ProblemIF;
import com.runwaysdk.dataaccess.transaction.ITaskListener;

import dss.vector.solutions.util.MDSSProperties;

public class ImportPanel extends AbstractPanel implements ActionListener, ITaskListener
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

  private Locale              locale;

  public ImportPanel(ContainerIF container, Locale locale)
  {
    // Create the content-pane-to-be.
    super(container, new BorderLayout());

    this.locale = locale;
    this.statusLabel = new JLabel(MDSSProperties.getString("Ready_To_Import", locale));

    this.browser = new FileBrowser(false);
    this.browser.setBorder(BorderFactory.createTitledBorder(MDSSProperties.getString("Import_transaction_file", locale)));

    this.importButton = new JButton(MDSSProperties.getString("Import", locale));
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

  private void executeImport()
  {
    if (!StandaloneClient.isServerUp())
    {
      this.lockContainer();
      this.statusLabel.setText(MDSSProperties.getString("Import_in_progress", locale));
      this.progressBar.setValue(0);

      ImportManager manager = new ImportManager(this.browser.getFile(), this, locale);
      manager.execute();
    }
    else
    {
      this.updateServerStatus();
    }
  }

  public void complete()
  {
    this.unlockContainer();
    this.statusLabel.setText(MDSSProperties.getString("Import_Complete", locale));
  }

  private final void updateServerStatus()
  {
    boolean up = StandaloneClient.isServerUp();

    if (up)
    {
      this.statusLabel.setText(MDSSProperties.getString("Server_Down", locale));
      this.importButton.setText(MDSSProperties.getString("Refresh", locale));
      this.importButton.setActionCommand(REFRESH_COMMAND);
    }
    else
    {
      this.statusLabel.setText(MDSSProperties.getString("Ready_To_Import", locale));
      this.importButton.setText(MDSSProperties.getString("Import", locale));
      this.importButton.setActionCommand(IMPORT_COMMAND);
    }
  }

  public void handleError(Exception e)
  {
    String message = MDSSProperties.getString("Import_Problem", locale);
    e.printStackTrace();

    try
    {
      if (e instanceof ProblemException)
      {
        message += ":\n";
        List<ProblemIF> problems = ( (ProblemException) e ).getProblems();

        for (ProblemIF problem : problems)
        {
          message += problem.getLocalizedMessage() + "\n";
        }
      }
      else
      {
        message = e.getLocalizedMessage();
      }
    }
    catch (Throwable t)
    {
      // Use the default error message
    }

    JOptionPane.showMessageDialog(this, message);

    this.complete();
  }

  public void unlock()
  {
    this.importButton.setEnabled(true);
  }

  @Override
  public void lock()
  {
    this.importButton.setEnabled(false);
  }

  @Override
  public void done()
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void taskProgress(int percent)
  {
    progressBar.setValue(percent);
  }

  @Override
  public void taskStart(String name, int amount)
  {
    // TODO Auto-generated method stub

  }
}
