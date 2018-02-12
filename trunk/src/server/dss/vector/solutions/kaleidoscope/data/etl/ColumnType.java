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
package dss.vector.solutions.kaleidoscope.data.etl;

import com.runwaysdk.generation.loader.Reloadable;

/**
 * These are the different kinds of cells we support. We keep track of the current one between the start and end.
 */
public enum ColumnType implements Reloadable {
  BOOLEAN, ERROR, FORMULA, INLINE_STRING, TEXT, NUMBER, UNDEFINED, DATE, LONG, DOUBLE, CATEGORY, LOCATION, IGNORE, LATITUDE, LONGITUDE, DOMAIN, FORMID
}
