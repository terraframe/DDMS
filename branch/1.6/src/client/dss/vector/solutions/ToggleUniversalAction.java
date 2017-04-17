package dss.vector.solutions;

import com.runwaysdk.generation.loader.Reloadable;

public class ToggleUniversalAction implements ConditionalAction, Reloadable
{
  private String   positiveElement;

  private String   negativeElement;

  private String[] universals;

  public ToggleUniversalAction(String positiveElement, String negativeElement, String... universals)
  {
    this.positiveElement = positiveElement;
    this.negativeElement = negativeElement;
    this.universals = universals;
  }

  public String getJavascript()
  {
    StringBuffer buffer = new StringBuffer();

    buffer.append("    if(document.getElementById('" + positiveElement + "').checked === true){\n");
    
    for (String universal : universals)
    {
      buffer.append("      selectSearch.addExtraUniversal('" + universal + "');\n");
    }
    
    buffer.append("    }\n");

    buffer.append("    YAHOO.util.Event.on('" + positiveElement + "', 'click', function(e) {\n");

    for (String universal : universals)
    {
      buffer.append("      selectSearch.addExtraUniversal('" + universal + "');\n");
    }

    buffer.append("      geoSearch.clearCache();\n");
    buffer.append("    });\n");

    buffer.append("\n");
    buffer.append("    YAHOO.util.Event.on('" + negativeElement + "', 'click', function(e) {\n");
    buffer.append("      selectSearch.clearExtraUniversals();\n");
    buffer.append("      geoSearch.clearCache();\n");
    buffer.append("    });\n");

    return buffer.toString();
  }

}
