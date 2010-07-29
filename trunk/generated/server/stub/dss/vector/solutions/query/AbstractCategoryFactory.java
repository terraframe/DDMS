package dss.vector.solutions.query;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.transaction.AbortIfProblem;
import com.runwaysdk.generation.loader.LoaderDecorator;
import com.runwaysdk.query.F;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdUtil;
import com.runwaysdk.system.metadata.MdUtilQuery;
import com.runwaysdk.system.metadata.UtilInheritanceQuery;

import dss.vector.solutions.MdssLog;

public abstract class AbstractCategoryFactory extends AbstractCategoryFactoryBase implements com.runwaysdk.generation.loader.Reloadable {
	private static final long serialVersionUID = 1851169193;

	public AbstractCategoryFactory() {
		super();
	}

	/**
	 * Returns a JSON map structure with the key and value pairs being the
	 * AbstractCategoryFactory subclass type and a list of required attributes
	 * from CategoryGen.
	 * 
	 * @return
	 */
	public static String getAllRequiredAttributes() {
		JSONObject obj = new JSONObject();

		// Use an instance of each subclass as a template to extract required
		// attributes
		// via an abstract method.
		MdUtil mdUtil = MdUtil.getMdUtil(AbstractCategoryFactory.CLASS);
		for (MdClass subclass : mdUtil.getAllSubEntity().getAll()) {
			String type = subclass.definesType();

			try {
				AbstractCategoryFactory factory = (AbstractCategoryFactory) LoaderDecorator.load(type).newInstance();
				String[] required = factory.getRequiredAttributes();

				obj.put(type, new JSONArray(Arrays.asList(required)));
			} catch (Throwable e) {
				String error = "Could not get the required factory attributes of type [" + type + "]";
				throw new ProgrammingErrorException(error, e);
			}
		}

		return obj.toString();
	}

	/**
	 * Returns a ValueQuery whose ValueObjects represent the id and display
	 * label for the subclasses of {@link AbstractCategoryFactory}.
	 * 
	 * @return
	 */
	public static ValueQuery getSubclassInfo() {
		QueryFactory f = new QueryFactory();
		ValueQuery vq = new ValueQuery(f);
		MdUtilQuery utilQ = new MdUtilQuery(f);
		UtilInheritanceQuery inheritQ = new UtilInheritanceQuery(f);

		MdUtil parent = MdUtil.getMdUtil(AbstractCategoryFactory.CLASS);

		vq.SELECT(F.CONCAT(utilQ.getPackageName(), F.CONCAT(".", utilQ.getTypeName()), QueryConstants.CATEGORY_FACTORY_TYPE), utilQ.getDisplayLabel().localize(QueryConstants.CATEGORY_FACTORY_DISPLAY));
		vq.WHERE(inheritQ.parentId().EQ(parent.getId()));
		vq.WHERE(utilQ.superClass(inheritQ));

		vq.ORDER_BY_ASC(utilQ.getDisplayLabel().localize());

		return vq;
	}
  
  protected abstract String[] getRequiredAttributes();
  
  /**
   * Creates a List of AbstracteCategory objects after validating that all
   * required attributes exist.
   * 
   * @param layer
   * @param categoryGen
   * @return
   */
  public List<AbstractCategory> create(Layer layer, CategoryGen categoryGen)
  {
    for(String required : this.getRequiredAttributes())
    {
      categoryGen.validateAttribute(required);
    }
    
    Integer count = categoryGen.getCategoryCount();
    if(count != null && count <= 1)
    {
      String error = "The number of categories must be greater than one.";
      new CountOfOneProblem(error).throwIt();
    }
    
    return createInternal(layer, categoryGen);
  }
  
  @AbortIfProblem
  protected abstract List<AbstractCategory> createInternal(Layer layer, CategoryGen categoryGen);
  
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
  
	protected double getUlp(int precision) {
		return Math.pow(10.0d, precision);
	}

	protected double floor(double n, double ulp) {
		return Math.floor(n * ulp) / ulp;
	}

	protected double ceil(double n, double ulp) {
		if (n == this.floor(n, ulp)) {
			return this.floor(n, ulp);
		}
		return (Math.floor(n * ulp) + 1.0d) / ulp;
	}

	protected double round(double n, double ulp) {
		return (Math.floor(0.5d + (n * ulp)) / ulp);
	}
	
	protected AbstractCategory createExact(Layer layer, String value, CategoryGen cg, int n, int total) {
		NonRangeCategory cat = new NonRangeCategory();

		cat.setExactValueStr(value);
		cat.setStyles(cg.interpolateStyle(n, total));

		return cat;
	}	
	
	protected AbstractCategory createRange(Layer layer, String lowerBound, String upperBound, CategoryGen cg, int n, int total) {
		MdssLog.debug(lowerBound + "->" + upperBound);
		
		RangeCategory cat = new RangeCategory();

		cat.setLowerBoundStr(lowerBound);
		cat.setUpperBoundStr(upperBound);
		cat.setStyles(cg.interpolateStyle(n, total));

		return cat;
	}
}
