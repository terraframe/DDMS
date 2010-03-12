package dss.vector.solutions.standalone;

import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class NumericTextField extends JTextField
{

  /**
   * 
   */
  private static final long serialVersionUID = 2893886153083039367L;
  
  
  public NumericTextField()
  {
    super();
  }

  public NumericTextField(Document arg0, String arg1, int arg2)
  {
    super(arg0, arg1, arg2);
  }

  public NumericTextField(int arg0)
  {
    super(arg0);
  }

  public NumericTextField(String arg0, int arg1)
  {
    super(arg0, arg1);
  }

  public NumericTextField(String arg0)
  {
    super(arg0);
  }

  @Override
  protected Document createDefaultModel()
  {
    return new NumericDocument();
  }

  private static class NumericDocument extends PlainDocument
  {
    /**
     * 
     */
    private static final long serialVersionUID = 1397344123747421062L;
    
    // The regular expression to match input against (zero or more digits)
    private final static Pattern DIGITS = Pattern.compile("\\d*");

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
    {
      // Only insert the text if it matches the regular expression
      if (str != null && DIGITS.matcher(str).matches())
      {
        super.insertString(offs, str, a);
      }
    }
  }
}