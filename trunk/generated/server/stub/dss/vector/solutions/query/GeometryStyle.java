package dss.vector.solutions.query;

import com.terraframe.mojo.dataaccess.ProgrammingErrorException;
import com.terraframe.mojo.system.gis.metadata.MdAttributeGeometry;
import com.terraframe.mojo.system.gis.metadata.MdAttributeLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiLineString;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributeMultiPolygon;
import com.terraframe.mojo.system.gis.metadata.MdAttributePoint;
import com.terraframe.mojo.system.gis.metadata.MdAttributePolygon;

import dss.vector.solutions.LineStyle;
import dss.vector.solutions.PointStyle;
import dss.vector.solutions.PolygonStyle;

public abstract class GeometryStyle extends GeometryStyleBase implements com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1240851035820L;

  public GeometryStyle()
  {
    super();
  }

  /**
   * Converts an MdAttributeGeometry to the propery GeometryStyle subclass.
   * This is done to match a point attribute to a point style, and so on. Note
   * that the GeometryStyle is created and applied in this method. This is done
   * to make it easier for calling processes to add the returned GeometryStyle
   * as a reference attribute with default styling.
   *
   * @param attrGeoMd
   * @return
   */
  public static GeometryStyle convertAttributeGeometryToStyle(MdAttributeGeometry attrGeoMd)
  {
    GeometryStyle geoStyle;
    if(attrGeoMd instanceof MdAttributePoint
        || attrGeoMd instanceof MdAttributeMultiPoint)
    {
      geoStyle = new PointStyle();
    }
    else if(attrGeoMd instanceof MdAttributeLineString
        || attrGeoMd instanceof MdAttributeMultiLineString)
    {
      geoStyle = new LineStyle();
    }
    else if(attrGeoMd instanceof MdAttributePolygon
        || attrGeoMd instanceof MdAttributeMultiPolygon)
    {
      geoStyle = new PolygonStyle();
    }
    else
    {
      String error = "The Geometry type ["+attrGeoMd.getType()+"] is not supported as a layer.";
      throw new ProgrammingErrorException(error);
    }

    geoStyle.apply();
    return geoStyle;
  }
}
