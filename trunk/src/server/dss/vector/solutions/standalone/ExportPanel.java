package dss.vector.solutions.standalone;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

import com.terraframe.mojo.dataaccess.transaction.TransactionPropertyChangeEvent;

import dss.vector.solutions.util.MDSSProperties;

public class ExportPanel extends JPanel implements ActionListener, PropertyChangeListener
{
  /**
   * 
   */
  private static final long   serialVersionUID = 6656584484212249758L;

  private static final String EXPORT_COMMAND   = "EXPORT";

  private JRadioButton        allRadio;

  private JRadioButton        rangeRadio;

  private JRadioButton        exportedRadio;

  private JPanel              sequencePanel;

  private JPanel              rangePanel;

  private JPanel              radioPanel;

  private ButtonGroup         group;

  private JLabel              startLabel;

  private JLabel              endLabel;

  private NumericTextField    startField;

  private NumericTextField    endField;

  private FileBrowser         browser;

  private JButton             exportButton;

  private JProgressBar        progressBar;

  private JPanel              buttonPanel;

  public ExportPanel()
  {
    // Create the content-pane-to-be.
    super();

    String saveLabel = MDSSProperties.getString("Save_Location");
    String exportLabel = MDSSProperties.getString("Export");

    this.setLayout(new BorderLayout());

    this.createSequencePanel();

    this.browser = new FileBrowser(true);
    this.browser.setBorder(BorderFactory.createTitledBorder(saveLabel));

    this.exportButton = new JButton(exportLabel);
    this.exportButton.setActionCommand(EXPORT_COMMAND);
    this.exportButton.addActionListener(this);

    this.progressBar = new JProgressBar();
    this.progressBar.setMinimum(0);
    this.progressBar.setMaximum(100);
    this.progressBar.setValue(0);
    this.progressBar.setVisible(true);

    this.buttonPanel = new JPanel(new BorderLayout());
    this.buttonPanel.add(progressBar, BorderLayout.CENTER);
    this.buttonPanel.add(exportButton, BorderLayout.EAST);

    this.add(sequencePanel, BorderLayout.NORTH);
    this.add(browser, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);

    this.setSize(StandaloneClient.DIMENSION);
  }

  private final void createSequencePanel()
  {
    String sequenceLabel = MDSSProperties.getString("Export_Sequences");

    this.createRadioPanel();
    this.createRangePanel();

    sequencePanel = new JPanel(new BorderLayout());
    sequencePanel.add(radioPanel, BorderLayout.NORTH);
    sequencePanel.add(rangePanel, BorderLayout.CENTER);
    sequencePanel.setBorder(BorderFactory.createTitledBorder(sequenceLabel));
  }

  private final void createRangePanel()
  {
    startLabel = new JLabel(MDSSProperties.getString("Start_Sequence"));
    endLabel = new JLabel(MDSSProperties.getString("End_Sequence"));
    startField = new NumericTextField(10);
    endField = new NumericTextField(10);

    rangePanel = new JPanel(new FlowLayout());
    rangePanel.add(startLabel);
    rangePanel.add(startField);
    rangePanel.add(endLabel);
    rangePanel.add(endField);

    rangePanel.setVisible(false);
  }

  private final void createRadioPanel()
  {
    allRadio = new JRadioButton(MDSSProperties.getString("All_Sequences"));
    allRadio.setMnemonic(KeyEvent.VK_A);
    allRadio.setActionCommand(ExportOption.ALL.name());
    allRadio.addActionListener(this);
    allRadio.setSelected(true);

    rangeRadio = new JRadioButton(MDSSProperties.getString("Range"));
    rangeRadio.setMnemonic(KeyEvent.VK_R);
    rangeRadio.setActionCommand(ExportOption.RANGE.name());
    rangeRadio.addActionListener(this);

    exportedRadio = new JRadioButton(MDSSProperties.getString("Not_exported"));
    exportedRadio.setMnemonic(KeyEvent.VK_N);
    exportedRadio.setActionCommand(ExportOption.NOT_IMPORTED.name());
    exportedRadio.addActionListener(this);

    // Group the radio buttons.
    group = new ButtonGroup();
    group.add(allRadio);
    group.add(rangeRadio);
    group.add(exportedRadio);

    radioPanel = new JPanel(new FlowLayout());
    radioPanel.add(allRadio);
    radioPanel.add(rangeRadio);
    radioPanel.add(exportedRadio);
  }

  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals(EXPORT_COMMAND))
    {
      String option = group.getSelection().getActionCommand();

      this.exportButton.setEnabled(false);
      this.progressBar.setValue(0);

      ExportManager manager = new ExportManager(this);
      manager.setOption(ExportOption.valueOf(option));
      manager.setLower(startField.getText());
      manager.setUpper(endField.getText());
      manager.setLocation(browser.getFile());
      manager.execute();
    }
    else
    {
      if (command.equals(ExportOption.RANGE.name()))
      {
        this.rangePanel.setVisible(true);
        this.rangePanel.repaint();
      }
      else
      {
        this.rangePanel.setVisible(false);
        this.rangePanel.repaint();
      }
    }
  }

  public void propertyChange(PropertyChangeEvent evt)
  {
    if (TransactionPropertyChangeEvent.PROGRESS == evt.getPropertyName())
    {
      int progress = (Integer) evt.getNewValue();

      System.out.println(progress);

      progressBar.setValue(progress);

      if (progress >= 100)
      {
        this.reset();
      }
    }
  }

  private void reset()
  {
    this.exportButton.setEnabled(true);
  }

  public void handleError(Throwable t)
  {
    JOptionPane.showMessageDialog(this, t.getLocalizedMessage());

    this.reset();
  }

}
