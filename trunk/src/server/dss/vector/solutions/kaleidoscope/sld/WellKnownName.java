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
package dss.vector.solutions.kaleidoscope.sld;

import com.runwaysdk.generation.loader.Reloadable;

/**
 * Represents a Well Known Name for SLD and provides a set of pre-defined
 * symbols or allows for construction of a custom value.
 */
public class WellKnownName implements Symbol, Reloadable
{
  /**
   * Standard symbols.
   * 
   */
  public enum STANDARD implements Symbol, Reloadable {
    CIRCLE, SQUARE, TRIANGLE, START, CROSS, X;

    @Override
    public String getSymbol()
    {
      return this.name().toLowerCase();
    }
    
    /**
     * Returns the enum as a WellKnownName object.
     * 
     * @return
     */
    public WellKnownName getWellKnownName()
    {
      return new WellKnownName(this.getSymbol());
    }
  }

  public static final String SHAPE_PREFIX = "shape://";

  /**
   * Shape symbols.
   */
  public enum SHAPE implements Symbol, Reloadable {
    VERTLINE, HORLINE, SLASH, BACKSLASH, DOT, PLUS, TIMES, OARROW, CARROW;

    @Override
    public String getSymbol()
    {
      return SHAPE_PREFIX + this.name().toLowerCase();
    }
    
    /**
     * Returns the enum as a WellKnownName object.
     * 
     * @return
     */
    public WellKnownName getWellKnownName()
    {
      return new WellKnownName(this.getSymbol());
    }
  }

  /**
   * The symbol recognized by SLD.
   */
  private String symbol;

  public WellKnownName(String symbol)
  {
    this.symbol = symbol;
  }
  
  @Override
  public String getSymbol()
  {
    return this.symbol;
  }

  @Override
  public String toString()
  {
    return this.getSymbol();
  }
}
