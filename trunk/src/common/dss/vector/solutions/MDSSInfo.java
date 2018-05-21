/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;

public interface MDSSInfo
{
  /**
   * Root package shared by all MDSS sub-packages and classes.
   */
  public static final String ROOT_PACKAGE                    = "dss.vector.solutions";

  /**
   * Package for generated GeoEntity types as defined by administrators.
   */
  public static final String GENERATED_GEO_PACKAGE           = ROOT_PACKAGE + ".geo.generated";

  public static final String GENERATED_TABLE_PACKAGE         = ROOT_PACKAGE + ".table";

  public static final String GENERATED_FORM_PACKAGE          = ROOT_PACKAGE + ".form";

  public static final String GENERATED_FORM_BUSINESS_PACKAGE = GENERATED_FORM_PACKAGE + ".business";

  public static final String GENERATED_FORM_TREE_PACKAGE     = GENERATED_FORM_PACKAGE + ".tree";

  public static final String TYPE_QB                         = ROOT_PACKAGE + ".querybuilder.TypeQB";

  public static final String FORM_SURVEY_QB                  = ROOT_PACKAGE + ".querybuilder.FormSurveyQB";

  public static final String ROOT_FORM_SURVEY_CLASS          = GENERATED_FORM_BUSINESS_PACKAGE + ".FormSurvey";

  public static final String LISTENER_METHOD                 = "setupExportListener";

}
