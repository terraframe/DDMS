package dss.vector.solutions.standalone;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileBrowser extends JPanel implements ActionListener
{

  /**
   * 
   */
  private static final long serialVersionUID = -2753969495907583322L;

  private JTextField        field;

  private JButton           button;

  private boolean           save;

  public FileBrowser(boolean save)
  {
    super(new FlowLayout());

    this.field = new JTextField(20);
    this.button = new JButton("Browse");
    this.button.addActionListener(this);
    this.save = save;

    this.add(field);
    this.add(button);
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
    JFileChooser fc = new JFileChooser();
    
    if(this.save)
    {
      fc.showSaveDialog(this);      
    }
    else
    {
      fc.showOpenDialog(this);
    }
    
    File file = fc.getSelectedFile();

    if (file != null)
    {
      field.setText(file.getAbsolutePath());
    }
  }

  public File getFile()
  {
    String path = field.getText();

    if (path != null && path.length() > 0)
    {
      return new File(path);
    }

    return null;
  }
}
