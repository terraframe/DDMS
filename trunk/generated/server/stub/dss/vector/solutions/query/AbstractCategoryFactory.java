package dss.vector.solutions.query;

import java.awt.Color;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractCategoryFactory extends AbstractCategoryFactoryBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1851169193;
  
  public static final String STARTING_COLOR = "startingColor";
  public static final String ENDING_COLOR = "endingColor";
  public static final String COUNT = "count";
  public static final String PRECISION = "precision";
  
  public AbstractCategoryFactory()
  {
    super();
  }
  
  public abstract boolean isRequiredParameter(String param);
  public abstract boolean isSupportedParameter(String param);
  public abstract List<AbstractCategory> create(Layer layer, Map<String,String> parameters);

  public Set<String> allParameters() {
	  HashSet<String> allParameters = new HashSet<String>();
	  allParameters.add(STARTING_COLOR);
	  allParameters.add(ENDING_COLOR);
	  allParameters.add(COUNT);
	  allParameters.add(PRECISION);
	  return allParameters;
  }
  
  protected Color interpolateColor(int n, int total, Color startingColor, Color endingColor) {
	  return new Color(this.interpolate(n, total, startingColor.getRed(), endingColor.getRed()),
			           this.interpolate(n, total, startingColor.getGreen(), endingColor.getGreen()),
			           this.interpolate(n, total, startingColor.getBlue(), endingColor.getBlue()));
  }
  
  private int interpolate(int n, int total, int start, int end) {
	  return start + Math.round( ((float) (end - start)) * ((float) n / (float) total) );
  }
  
  protected Color decodeColor(String s) {
	  return Color.decode(s);
  }
  
  protected String encodeColor(Color c) {
	  return String.format( "#%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue() );
  }
}
