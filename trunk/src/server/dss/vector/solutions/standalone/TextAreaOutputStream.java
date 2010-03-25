package dss.vector.solutions.standalone;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class TextAreaOutputStream extends OutputStream
{
  private JTextArea textArea;

  public TextAreaOutputStream(JTextArea textArea)
  {
    super();
    this.textArea = textArea;
  }
  
  @Override
  public void write(byte[] b) throws IOException
  {
    textArea.append(new String(b));
  }

  @Override
  public void write(int b) throws IOException
  {    
    textArea.append(new String(new byte[]{(byte)b}));
  }

}
