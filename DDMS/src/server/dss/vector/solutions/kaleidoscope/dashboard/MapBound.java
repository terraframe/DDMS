/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.kaleidoscope.dashboard;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.json.JSONException;
import org.json.JSONObject;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.generation.loader.Reloadable;

public class MapBound implements Reloadable
{
  private int    width;

  private int    height;

  private double bottom;

  private double top;

  private double right;

  private double left;

  private int    x;

  private int    y;

  public MapBound(int width, int height, JSONObject mapBoundsObj)
  {
    try
    {
      this.x = 0;
      this.y = 0;
      this.width = width;
      this.height = height;

      this.bottom = mapBoundsObj.getDouble("bottom");
      this.top = mapBoundsObj.getDouble("top");
      this.right = mapBoundsObj.getDouble("right");
      this.left = mapBoundsObj.getDouble("left");
    }
    catch (JSONException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }

  public MapBound(int width, int height, double left, double right, double bottom, double top)
  {
    super();

    this.x = 0;
    this.y = 0;

    this.width = width;
    this.height = height;
    this.left = left;
    this.bottom = bottom;
    this.right = right;
    this.top = top;
  }

  public int getWidth()
  {
    return width;
  }

  public int getHeight()
  {
    return height;
  }

  public int getX()
  {
    return x;
  }
  
  public void setX(int x)
  {
    this.x = x;
  }

  public int getY()
  {
    return y;
  }
  
  public void setY(int y)
  {
    this.y = y;
  }

  public String left()
  {
    return Double.toString(left);
  }

  public String right()
  {
    return Double.toString(right);
  }

  public String bottom()
  {
    return Double.toString(bottom);
  }

  public String top()
  {
    return Double.toString(top);
  }

  public double getTop()
  {
    return top;
  }

  public double getBottom()
  {
    return bottom;
  }

  public double getLeft()
  {
    return left;
  }

  public double getRight()
  {
    return right;
  }

  public MapBound scale(int layerWidth, int layerHeight)
  {
    try
    {
      // Offset the layerCanvas so that it is center
      int widthOffset = (int) ( ( width - layerWidth ) / 2 );
      int heightOffset = (int) ( ( height - layerHeight ) / 2 );

      CoordinateReferenceSystem crs = CRS.decode("EPSG:4326");
      ReferencedEnvelope envelope = new ReferencedEnvelope(left, right, bottom, top, crs);

      // Envelope envelope = new Envelope(left, right, bottom, top);

      double vWidth = Math.abs(right) - Math.abs(left);
      double vHeight = Math.abs(top) - Math.abs(bottom);

      double xScale = ( width / vWidth );
      double yScale = ( height / vHeight );

      double deltaX = widthOffset * Math.abs(1 / xScale);
      double deltaY = heightOffset * Math.abs(1 / yScale);

      envelope.expandBy(deltaX, deltaY);

      MapBound scaled = new MapBound(this.width, this.height, envelope.getMinX(), envelope.getMaxX(), envelope.getMinY(), envelope.getMaxY());

      this.width = layerWidth;
      this.height = layerHeight;
      this.x = widthOffset;
      this.y = heightOffset;

      return scaled;
    }
    catch (MismatchedDimensionException | FactoryException e)
    {
      throw new ProgrammingErrorException(e);
    }
  }
}
