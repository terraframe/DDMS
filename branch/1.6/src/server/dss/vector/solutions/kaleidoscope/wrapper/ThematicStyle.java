package dss.vector.solutions.kaleidoscope.wrapper;

import org.json.JSONArray;
import org.json.JSONException;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;

import dss.vector.solutions.kaleidoscope.dashboard.AllAggregationType;

public interface ThematicStyle extends Style
{
  public static final String VAL              = "val";

  public static final String VALMAX           = "valMax";

  public static final String COLOR            = "color";

  public static final String ISRANGECATEGORY  = "isRangeCat";

  public static final String ISOTHERCAT       = "otherCat";

  public static final String RANGEALLMIN      = "rangeAllMin";

  public static final String RANGEALLMAX      = "rangeAllMax";

  public static final String CATEGORYVALUE    = "catVal";

  public static final String CATEGORYCOLOR    = "catColor";

  public static final String CATEGORYTITLE    = "catTitle";

  public static final String CATEGORYMAXVALUE = "catMaxVal";

  public Integer getBubbleMinSize();

  public void setBubbleMinSize(Integer size);

  public Integer getBubbleMaxSize();

  public void setBubbleMaxSize(Integer size);

  public Integer getBubbleSize();

  public String getBubbleStroke();

  public Integer getBubbleStrokeWidth();

  public Double getBubbleStrokeOpacity();

  public String getBubbleWellKnownName();

  public void setBubbleSize(Integer size);

  public void setBubbleStroke(String stroke);

  public void setBubbleStrokeWidth(Integer width);

  public void setBubbleWellKnownName(String pointWellKnownName);

  public void setBubbleFill(String fill);

  public String getBubbleFill();

  public void setBubbleOpacity(Double opacity);

  public Double getBubbleOpacity();

  public void setBubbleRotation(Integer rotation);

  public Integer getBubbleRotation();

  public Boolean getBubbleContinuousSize();

  public Integer getNumBubbleSizeCategories();

  public String getGradientPolygonMinFill();

  public void setGradientPolygonMinFill(String fill);

  public String getGradientPolygonMaxFill();

  public void setGradientPolygonMaxFill(String fill);

  public Double getGradientPolygonFillOpacity();

  public String getGradientPolygonStroke();

  public Integer getGradientPolygonStrokeWidth();

  public Double getGradientPolygonStrokeOpacity();

  public Integer getNumGradientPolygonCategories();

  public Integer getGradientPointSize();

  public String getGradientPointMinFill();

  public String getGradientPointMaxFill();

  public Double getGradientPointFillOpacity();

  public String getGradientPointStroke();

  public Integer getGradientPointStrokeWidth();

  public Double getGradientPointStrokeOpacity();

  public Integer getNumGradientPointCategories();

  public Integer getCategoryPointSize();

  public String getGradientPointWellKnownName();

  public String getCategoryPointWellKnownName();

  public String getCategoryPolygonStyles();

  public Double getCategoryPolygonFillOpacity();

  public String getCategoryPolygonStroke();

  public Integer getCategoryPolygonStrokeWidth();

  public Double getCategoryPolygonStrokeOpacity();

  public MdAttributeDAOIF getSecondaryAttributeDAO();

  public AllAggregationType getSecondaryAttributeAggregationMethod();

  public JSONArray getSecondaryAttributeCategoriesAsJSON() throws JSONException;
}
