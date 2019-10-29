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
package dss.vector.solutions.querybuilder.irs;

import java.util.Set;

import com.runwaysdk.dataaccess.MdEntityDAOIF;
import com.runwaysdk.dataaccess.metadata.MdEntityDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.EnumerationMaster;
import com.runwaysdk.system.metadata.MetadataDisplayLabel;

import dss.vector.solutions.irs.AreaStandards;
import dss.vector.solutions.irs.InsecticideBrand;
import dss.vector.solutions.irs.TargetUnit;
import dss.vector.solutions.querybuilder.IRSQB;
import dss.vector.solutions.querybuilder.IRSQB.View;
import dss.vector.solutions.util.QueryUtil;

public class InsecticideView extends AuxiliaryProvider implements Reloadable
{
  public static final String CLASS = "dss.vector.solutions.querybuilder.irs.InsecticideView";

  public InsecticideView(IRSQB irsQB)
  {
    super(irsQB);
  }
  
  @Override
  protected View getView()
  {
    return View.INSECTICIDE_VIEW;
  }

  @Override
  public void loadDependencies()
  {
    // FIXME optimize the columns
    Set<Alias> selectAliases = this.irsQB.getSelectAliases();
//    if(selectAliases.contains(Alias.SPRAYED_UNITS) || selectAliases.contains(Alias.UNITS_UNSPRAYED))
//    {
//    }

    // this view joins on all spray activity's brand and start/end date
    this.irsQB.addRequiredAlias(View.ALL_ACTUALS, Alias.BRAND, Alias.SPRAY_DATE, Alias.DISEASE);
  }

  public String setSprayUnit(Alias alias)
  {
    return setNULL(alias);
  }
  
  public String setAreaUnit(Alias alias)
  {
    return setNULL(alias);
  }
  
  @Override
  public String getSQL()
  {
    // MdEntityDAOIF insectNozzleMd =
    // MdEntityDAO.getMdEntityDAO(InsecticideNozzle.CLASS);
    // String insectNozzleTable = insectNozzleMd.getTableName();
    // String configDateCol = QueryUtil.getColumnName(insectNozzleMd,
    // InsecticideNozzle.CONFIGURATIONDATE);
    // String enabledCol = QueryUtil.getColumnName(insectNozzleMd,
    // InsecticideNozzle.ENABLED);

    MdEntityDAOIF enumMasterMD = MdEntityDAO.getMdEntityDAO(EnumerationMaster.CLASS);
    String enumMasterTable = enumMasterMD.getTableName();
    String enumNameCol = QueryUtil.getColumnName(enumMasterMD, EnumerationMaster.ENUMNAME);
    String disLabelCol = QueryUtil.getColumnName(enumMasterMD, EnumerationMaster.DISPLAYLABEL);

    MdEntityDAOIF disLabelMd = MdEntityDAO.getMdEntityDAO(MetadataDisplayLabel.CLASS);
    String disLabelTable = disLabelMd.getTableName();
    String defaultLocaleCol = QueryUtil.getColumnName(disLabelMd, MetadataDisplayLabel.DEFAULTLOCALE);

    MdEntityDAOIF insectBrandMd = MdEntityDAO.getMdEntityDAO(InsecticideBrand.CLASS);
    String insectBrandTable = insectBrandMd.getTableName();
    String unitsPerApplicationCol = QueryUtil.getColumnName(insectBrandMd,
        InsecticideBrand.UNITSPERAPPLICATION);
    String unitQuantifierCol = QueryUtil.getColumnName(insectBrandMd, InsecticideBrand.UNITQUANTIFIER);
    String concentrationQuantifierCol = QueryUtil.getColumnName(insectBrandMd,
        InsecticideBrand.CONCENTRATIONQUANTIFIER);
    String diseaseCol = QueryUtil.getColumnName(InsecticideBrand.getDiseaseMd());

    // MdEntityDAOIF nozzleMd = MdEntityDAO.getMdEntityDAO(Nozzle.CLASS);
    // String nozzleTable = nozzleMd.getTableName();
    // String ratioCol = QueryUtil.getColumnName(nozzleMd, Nozzle.RATIO);
    // String nozzleDisLabelCol = QueryUtil.getColumnName(nozzleMd,
    // Nozzle.DISPLAYLABEL);

    MdEntityDAOIF areaStandardsMd = MdEntityDAO.getMdEntityDAO(AreaStandards.CLASS);
    String areaStandardsTable = areaStandardsMd.getTableName();
    String structureAreaCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.STRUCTUREAREA);
    String roomCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.ROOM);
    String householdCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.HOUSEHOLD);
    String unitAreaNozzleCovCol = QueryUtil.getColumnName(areaStandardsMd,
        AreaStandards.UNITNOZZLEAREACOVERAGE);
    String targetUnitCol = QueryUtil.getColumnName(areaStandardsMd, AreaStandards.TARGETUNIT);

    // id (brand)
    String select = "SELECT " + insectBrandTable + ".id,\n";

    // start date
    select += "COALESCE(start_date,'1900-01-01'::date) start_date,\n";

    // disease
    select += insectBrandTable + "." + diseaseCol + " disease,\n";

    // end date
    select += "COALESCE(end_Date,'2100-01-01'::date) end_date, \n";

    /*
     * removed for #2826 // nozzle start select += "COALESCE((SELECT i." +
     * configDateCol + " FROM " + insectNozzleTable + " i WHERE " +
     * insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i." +
     * RelationshipDAOIF.PARENT_ID_COLUMN + " \n"; select += "AND " +
     * insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i." +
     * RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." +
     * configDateCol + " < i." + configDateCol + " ORDER BY i." + configDateCol
     * + " DESC LIMIT 1 ),'1900-01-01'::date) nozzleStart, \n";
     */

    /*
     * removed for #2826 // nozzle end select += "COALESCE((SELECT i." +
     * configDateCol + " FROM " + insectNozzleTable + " i WHERE " +
     * insectNozzleTable + "." + RelationshipDAOIF.PARENT_ID_COLUMN + " = i." +
     * RelationshipDAOIF.PARENT_ID_COLUMN + " \n"; select += "AND " +
     * insectNozzleTable + "." + RelationshipDAOIF.CHILD_ID_COLUMN + " = i." +
     * RelationshipDAOIF.CHILD_ID_COLUMN + "  AND " + insectNozzleTable + "." +
     * configDateCol + " > i." + configDateCol + "  ORDER BY i." + configDateCol
     * + " ASC LIMIT 1),'2100-01-01'::date) nozzleEnd, \n";
     */

    // removed unitsPerApplicationCol * ratio for ticket #2826
    select += "" + unitQuantifierCol + "*"// + unitsPerApplicationCol +
                                          // "*ratio*"
        + "(" + concentrationQuantifierCol + "/100.0) AS active_ingredient_per_can,\n";

    /*
     * removed for #2826 // nozzle ratio select += "" + nozzleTable + "." +
     * ratioCol + " AS nozzle_ratio,\n";
     */

    /*
     * removed for #2826 // nozzle default locale select += "" + nozzleTable +
     * "." + nozzleDisLabelCol + " AS nozzle_defaultLocale,\n";
     */

    /*
     * removed for #2826 // enabled select += "" + insectNozzleTable + "." +
     * enabledCol + ",\n";
     */

    // spray unit
    select += "" + enumNameCol + " "+Alias.SPRAY_UNIT+",\n";

    // target unit display label
    select += "(SELECT " + defaultLocaleCol + " FROM " + disLabelTable + " md WHERE " + enumMasterTable
        + "." + disLabelCol + " = md.id) targetUnit_displayLabel,\n";

    // unit area
    select += "(CASE WHEN " + enumNameCol + " = '" + TargetUnit.ROOM.name() + "' THEN " + roomCol
        + "  WHEN " + enumNameCol + " = '" + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol
        + " WHEN " + enumNameCol + " = '" + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol
        + " END ) AS " + Alias.UNIT_AREA + ",\n";

    // standard application rate
    select += "" + unitAreaNozzleCovCol + " " + unitAreaNozzleCovCol + ",\n";
    select += "((" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*("
        + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol
        + " )  AS standard_application_rate,\n";

    // standard application rate mg
    select += "(1000.0 * (" + unitQuantifierCol + "*" + unitsPerApplicationCol + "*("
        + concentrationQuantifierCol + "/100.0)) / " + unitAreaNozzleCovCol
        + " ) AS standard_application_rate_mg,\n";

    // units per can (removed ratio per #2826)
    select += /* "ratio * " + */unitAreaNozzleCovCol + "/(CASE WHEN " + enumNameCol + " = '"
        + TargetUnit.ROOM.name() + "' THEN " + roomCol + " WHEN " + enumNameCol + " = '"
        + TargetUnit.STRUCTURE.name() + "' THEN " + structureAreaCol + " WHEN " + enumNameCol + " = '"
        + TargetUnit.HOUSEHOLD.name() + "' THEN " + householdCol + " END ) AS units_per_can\n";

    String from = "FROM ";
    from += areaStandardsTable + " AS " + areaStandardsTable + ",\n";
    from += insectBrandTable + " AS " + insectBrandTable + ",\n";

    /*
     * removed for #2826 from += nozzleTable + " AS " + nozzleTable + ",\n";
     * from += insectNozzleTable + " AS " + insectNozzleTable + "\n,";
     */
    from += "" + enumMasterTable + " " + enumMasterTable + "\n";

    String where = "WHERE \n";

    /*
     * removed for #2826 where += "AND " + insectNozzleTable + "." +
     * RelationshipDAOIF.PARENT_ID_COLUMN + " = " + insectBrandTable + ".id \n";
     * where += "AND " + insectNozzleTable + "." +
     * RelationshipDAOIF.CHILD_ID_COLUMN + " = " + nozzleTable + ".id \n";
     */
    where += enumMasterTable + ".id = " + targetUnitCol + "_c \n";

    return select + "\n" + from + "\n" + where;
  }
  

}
