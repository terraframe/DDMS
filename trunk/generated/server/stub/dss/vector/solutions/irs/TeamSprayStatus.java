package dss.vector.solutions.irs;

import java.util.List;

import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.MdssLog;
import dss.vector.solutions.query.Layer;

public class TeamSprayStatus extends TeamSprayStatusBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -451404549;
  
  public TeamSprayStatus()
  {
    super();
  }  
  
  @Override
  public String toString()
  {
    if (this.isNew())
    {
      return "New: " + this.getClassDisplayLabel();
    }
    
    return this.getClassDisplayLabel();
  }
  
  @Override
  protected String buildKey()
  {
    if(this.getSpray() != null && this.getSprayTeam() != null)
    {
      return this.getSpray().getKey() + " - " + this.getSprayTeam().getKey();
    }
    
    return this.getId();
  }
  
  public TeamSprayStatusView unlockView()
  {
    this.unlock();

    return this.getView();
  }

  @Override
  public TeamSprayStatusView lockView()
  {
    this.lock();

    return this.getView();
  }

  public TeamSprayStatusView getView()
  {
    TeamSprayStatusView view = new TeamSprayStatusView();

    view.populateView(this);

    return view;
  }

  public void validateHouseholds(SprayMethod method)
  {
    Integer value = this.getHouseholds();

    if (value != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Household value is not applicable on a mop-up spray";
        HouseholdValueNotApplicableProblem p = new HouseholdValueNotApplicableProblem(msg);
        p.setNotification(this, HOUSEHOLDS);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validatePrevSprayedStructures(SprayMethod method)
  {
    Integer value = this.getPrevSprayedStructures();

    if (value != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Structure value is not applicable on a mop-up spray";
        PrevSprayedStructureValueNotApplicableProblem p = new PrevSprayedStructureValueNotApplicableProblem(msg);
        p.setNotification(this, PREVSPRAYEDSTRUCTURES);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validateStructures(SprayMethod method)
  {
    Integer value = this.getStructures();

    if (value != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Household value is not applicable on a mop-up spray";
        StructureValueNotApplicableProblem p = new StructureValueNotApplicableProblem(msg);
        p.setNotification(this, STRUCTURES);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validatePrevSprayedHouseholds(SprayMethod method)
  {
    Integer value = this.getPrevSprayedHouseholds();
    if (value != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        PrevSprayedHouseholdValueNotApplicableProblem p = new PrevSprayedHouseholdValueNotApplicableProblem(msg);
        p.setNotification(this, PREVSPRAYEDHOUSEHOLDS);
        p.apply();
        p.throwIt();
      }
    }
  }

  public void validateRooms(SprayMethod method)
  {
    if (this.getRooms() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Value is not applicable on a mop-up spray";
        RoomValueNotApplicableProblem p = new RoomValueNotApplicableProblem(msg);
        p.setNotification(this, ROOMS);
        p.apply();
        p.throwIt();
      }
    }
  }

  @Override
  public void validateSprayedRooms()
  {
    if (this.getRooms() != null && this.getSprayedRooms() != null && this.getRooms() < this.getSprayedRooms())
    {
      String msg = "The number of sprayed rooms cannot be greater than the number of rooms";

      SprayedSumProblem p = new SprayedSumProblem(msg);
      p.setNotification(this, SPRAYEDROOMS);
      p.setObjectLabel(this.getMdAttributeDAO(ROOMS).getDisplayLabel(Session.getCurrentLocale()));
      p.setSprayedObjectLabel(this.getMdAttributeDAO(SPRAYEDROOMS).getDisplayLabel(Session.getCurrentLocale()));
      p.setObjects(this.getRooms());
      p.setSprayedObjects(this.getSprayedRooms());
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateSprayedHouseholds()
  {
    if (this.getHouseholds() != null && this.getSprayedHouseholds() != null && this.getHouseholds() < this.getSprayedHouseholds())
    {
      String msg = "The number of sprayed households cannot be greater than the number of households";

      SprayedSumProblem p = new SprayedSumProblem(msg);
      p.setNotification(this, SPRAYEDHOUSEHOLDS);
      p.setObjectLabel(this.getMdAttributeDAO(HOUSEHOLDS).getDisplayLabel(Session.getCurrentLocale()));
      p.setSprayedObjectLabel(this.getMdAttributeDAO(SPRAYEDHOUSEHOLDS).getDisplayLabel(Session.getCurrentLocale()));
      p.setObjects(this.getHouseholds());
      p.setSprayedObjects(this.getSprayedHouseholds());
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void validateSprayedStructures()
  {
    if (this.getStructures() != null && this.getSprayedStructures() != null && this.getStructures() < this.getSprayedStructures())
    {
      String msg = "The number of sprayed structures cannot be greater than the number of structures";

      SprayedSumProblem p = new SprayedSumProblem(msg);
      p.setNotification(this, SPRAYEDSTRUCTURES);
      p.setObjectLabel(this.getMdAttributeDAO(STRUCTURES).getDisplayLabel(Session.getCurrentLocale()));
      p.setSprayedObjectLabel(this.getMdAttributeDAO(SPRAYEDSTRUCTURES).getDisplayLabel(Session.getCurrentLocale()));
      p.setObjects(this.getHouseholds());
      p.setSprayedObjects(this.getSprayedHouseholds());
      p.apply();

      p.throwIt();
    }
  }

  @Override
  public void apply()
  {
    SprayMethod method = this.getSprayMethod();

    // Validate MOP-UP
    validateHouseholds(method);
    validateStructures(method);
    validatePrevSprayedHouseholds(method);
    validatePrevSprayedStructures(method);
    validateRooms(method);
    validatePeople(method);
    validateBedNets(method);
    validateRoomsWithBedNets(method);
    validateLocked(method);
    validateRefused(method);
    validateOther(method);

    // Validate values
    validateSprayedHouseholds();
    validateSprayedStructures();
    validateSprayedRooms();

    super.apply();
  }

  private void validateOther(SprayMethod method)
  {
    if (this.getOther() != null)
    {
      if (method.equals(SprayMethod.MOP_UP))
      {
        String msg = "Other is not applicable on a mop-up spray";
        ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
        p.setNotification(this, OTHER);
        p.apply();
        p.throwIt();
      }
    }
  }

  private void validateRefused(SprayMethod method)
  {
    if (this.getRefused() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "Refused is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, REFUSED);
      p.apply();
      p.throwIt();
    }
  }

  private void validateLocked(SprayMethod method)
  {
    if (this.getLocked() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "Locked is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, LOCKED);
      p.apply();
      p.throwIt();
    }
  }

  private void validateRoomsWithBedNets(SprayMethod method)
  {
    if (this.getRoomsWithBedNets() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "Rooms with bed nets is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, ROOMSWITHBEDNETS);
      p.apply();
      p.throwIt();
    }
  }

  private void validateBedNets(SprayMethod method)
  {
    if (this.getBedNets() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "# ITNs is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, BEDNETS);
      p.apply();
      p.throwIt();
    }
  }

  protected void validatePeople(SprayMethod method)
  {
    if (this.getPeople() != null && method.equals(SprayMethod.MOP_UP))
    {
      String msg = "# People protected is not applicable on a mop-up spray";
      ValueNotApplicableProblem p = new ValueNotApplicableProblem(msg);
      p.setNotification(this, PEOPLE);
      p.apply();
      p.throwIt();
    }
  }

  protected SprayMethod getSprayMethod()
  {
    List<SprayMethod> method = this.getSpray().getSprayMethod();

    if (method.size() > 0)
    {
      return method.get(0);
    }

    return null;
  }
  
  public static void createTempTable(String tableName)
  {
    String sql = "DROP VIEW IF EXISTS " + tableName + ";\n";
    sql += "CREATE VIEW " + tableName + " AS ";
    sql += TeamSprayStatus.getTempTableSQL() + ";\n";
    Database.parseAndExecute(sql);
  }

  public static String getTempTableSQL()
  {
//    String select = "SELECT spraystatus.id,\n";
//
//    // insecticide stuff
//    // select += "active_ingredient_per_can_view.active_ingredient_per_can,\n";
//    select += "active_ingredient_per_can_view.active_ingredient_per_can /  areastandards.unitnozzleareacoverage AS standard_application_rate,\n";
//    select += "active_ingredient_per_can_view.active_ingredient_per_can AS active_ingredient_per_can,\n";
//    select += "(1000.0 * active_ingredient_per_can_view.active_ingredient_per_can) /  areastandards.unitnozzleareacoverage AS standard_application_rate_mg,\n";
//    select += "nozzle_defaultLocale AS nozzle_defaultLocale,\n";
//    select += "nozzle_ratio AS nozzle_ratio,\n";
//
//    // --application rate is:
//    // --(# can refills * amount of active ingredient per can refill) / (# units
//    // sprayed *average size of unit).\n";
//    select += "(CAST(refills AS float) * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedrooms / room_total)) / NULLIF(spraystatus.sprayedrooms * areastandards.room , 0) AS room_application_rate,\n";
//    select += "(CAST(refills AS float) * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedstructures/structure_total)) / NULLIF(spraystatus.sprayedstructures * areastandards.structurearea, 0) AS structure_application_rate,\n";
//    select += "(CAST(refills AS float) * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedhouseholds/ household_total)) / NULLIF(spraystatus.sprayedhouseholds * areastandards.household, 0) AS household_application_rate,\n";
//
//    select += "(CAST(refills AS float) * 1000.0 * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedrooms / room_total)) / NULLIF(spraystatus.sprayedrooms * areastandards.room , 0) AS room_application_rate_mg,\n";
//    select += "(CAST(refills AS float) * 1000.0 * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedstructures/structure_total)) / NULLIF(spraystatus.sprayedstructures * areastandards.structurearea, 0) AS structure_application_rate_mg,\n";
//    select += "(CAST(refills AS float) * 1000.0 * active_ingredient_per_can_view.active_ingredient_per_can * (spraystatus.sprayedhouseholds/ household_total)) / NULLIF(spraystatus.sprayedhouseholds * areastandards.household, 0) AS household_application_rate_mg,\n";
//    
//    // --application ratio is:\n";
//    // --"--(# can refills (30)* ave m_ per can refill (10)*nozzle ratio (6)) / (total units sprayed (11, 12 or 13)*ave m_ per unit (38, 37 or 36))\n";
//    select += "((CAST(refills AS float) * (spraystatus.sprayedrooms / room_total)) * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / NULLIF(spraystatus.sprayedrooms      * areastandards.room, 0) AS room_application_ratio,\n";
//    select += "((CAST(refills AS float) * (spraystatus.sprayedstructures/structure_total)) * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / NULLIF(spraystatus.sprayedstructures * areastandards.structurearea, 0) AS structure_application_ratio,\n";
//    select += "((CAST(refills AS float) * (spraystatus.sprayedhouseholds/ household_total)) * areastandards.unitnozzleareacoverage * active_ingredient_per_can_view.nozzle_ratio) / NULLIF(spraystatus.sprayedhouseholds * areastandards.household, 0) AS household_application_ratio,\n";
//    
//    
//
//    // --operational coverage is:\n";
//    // --(Total units sprayed / Total units available) *100 (to calculate
//    // percentage of units sprayed). \n";
//    select += "CAST(spraystatus.sprayedrooms      AS float) / NULLIF(spraystatus.rooms      ,0) * 100 AS room_operational_coverage,\n";
//    select += "CAST(spraystatus.sprayedstructures AS float) / NULLIF(spraystatus.structures ,0) * 100 AS structure_operational_coverage,\n";
//    select += "CAST(spraystatus.sprayedhouseholds AS float) / NULLIF(spraystatus.households ,0) * 100 AS household_operational_coverage,\n";
//
//    // unsprayed
//    select += "(spraystatus.rooms - spraystatus.sprayedrooms) AS room_unsprayed,\n";
//    select += "(spraystatus.structures - spraystatus.sprayedstructures) AS structure_unsprayed,\n";
//    select += "(spraystatus.households - spraystatus.sprayedhouseholds) AS household_unsprayed,\n";
//
//    String from = " FROM ";
//    // get the main tables
//    from += MdBusiness.getMdBusiness(AbstractSpray.CLASS).getTableName() + " AS abstractspray,\n";
//    from += MdBusiness.getMdBusiness(SprayStatus.CLASS).getTableName() + " AS spraystatus,\n";
//    from += MdBusiness.getMdBusiness(SprayData.CLASS).getTableName() + " AS spraydata,\n";
//    from += MdBusiness.getMdBusiness(AreaStandards.CLASS).getTableName() + " AS areastandards,\n";
//    from += MdBusiness.getMdBusiness(ActorSpray.CLASS).getTableName() + " AS actorspray,\n";
//
//    // get views
//    from += "(" + InsecticideBrand.getTempTableSQL() + ") AS active_ingredient_per_can_view,\n";
//    from += "(" + ActorSpray.getUnitTotalsSQL() + ") AS unit_totals_view,\n";
//
//    String where = "";
//    // join main tables
//    where += "AND spraydata.id = abstractspray.spraydata \n";
//    where += "AND spraystatus.spray = abstractspray.id \n";
//    where += "AND abstractspray.id = actorspray.id \n";
//    // join views
//    where += "AND spraydata.brand = active_ingredient_per_can_view.id \n";
//    where += "AND actorspray.id = unit_totals_view.id \n";
//
//    select = select.substring(0, select.length() - 2);
//    from = from.substring(0, from.length() - 2);
//    where = "WHERE " + where.substring(3, where.length() - 2);
//
//    return select + "\n" + from + "\n" + where;
    
    return "";
  }
  
  /**
   * Takes in an XML string and returns a ValueQuery representing the structured
   * query in the XML.
   * 
   * @param xml
   * @return
   */
  public static ValueQuery xmlToValueQuery(String xml, String config, Layer layer)
  {
//    JSONObject queryConfig;
//    try
//    {
//      queryConfig = new JSONObject(config);
//    }
//    catch (JSONException e1)
//    {
//      throw new ProgrammingErrorException(e1);
//    }
//    
    QueryFactory queryFactory = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(queryFactory);
//
//    // IMPORTANT: Required call for all query screens.
//    Map<String, GeneratedEntityQuery> queryMap = QueryUtil.joinQueryWithGeoEntities(queryFactory, valueQuery, xml, queryConfig, layer);
//
//    SprayStatusQuery sprayStatusQuery = (SprayStatusQuery) queryMap.get(SprayStatus.CLASS);
//
//    AbstractSprayQuery abstractSprayQuery = (AbstractSprayQuery) queryMap.get(AbstractSpray.CLASS);
//    
//    SprayDataQuery sprayDataQuery = (SprayDataQuery) queryMap.get(SprayData.CLASS);
//    
//    InsecticideBrandQuery insecticideQuery = (InsecticideBrandQuery) queryMap.get(InsecticideBrand.CLASS);
//
//    ActorSprayQuery actorSprayQuery = (ActorSprayQuery) queryMap.get(ActorSpray.CLASS);
//    
//    
//    if (abstractSprayQuery != null)
//    {
//      valueQuery.WHERE(sprayStatusQuery.getSpray().EQ(abstractSprayQuery));
//
//      if (sprayDataQuery != null)
//      {
//        valueQuery.WHERE(abstractSprayQuery.getSprayData().EQ(sprayDataQuery));
//        QueryUtil.joinTermAllpaths(valueQuery,SprayData.CLASS,sprayDataQuery); 
//        
//        if (insecticideQuery != null)
//        {
//          valueQuery.WHERE(sprayDataQuery.getBrand().EQ(insecticideQuery));
//          QueryUtil.joinTermAllpaths(valueQuery,InsecticideBrand.CLASS,insecticideQuery); 
//        }
//        
//      }
// 
//      if (actorSprayQuery != null)
//      {
//        valueQuery.WHERE(abstractSprayQuery.getId().EQ(actorSprayQuery.getId()));
//      }
//      
//    }
//
//
//    String viewName = Layer.GEO_VIEW_PREFIX + System.currentTimeMillis();
//    ResourceTarget.createDatabaseView(viewName);
//    
//
//    String coverageCalculationsView = "spray_data_view";
//    String sprayCaluclationsSQL = "(" + SprayStatus.getTempTableSQL() + ")";
//    valueQuery.FROM(sprayCaluclationsSQL, coverageCalculationsView);
//    valueQuery.WHERE(new InnerJoinEq("id", sprayStatusQuery.getMdClassIF().getTableName(), sprayStatusQuery.getTableAlias(), "id", sprayCaluclationsSQL, coverageCalculationsView));
//
//    String unionView = "all_levels_spray_view";
//    String unionSQL = "(" + AbstractSpray.getSubquerySql(viewName) + ")";
//    valueQuery.FROM(unionSQL, unionView);
//    valueQuery.WHERE(new InnerJoinEq("id", sprayStatusQuery.getMdClassIF().getTableName(), sprayStatusQuery.getTableAlias(), "id", unionSQL, unionView));
//    
//    
//    QueryUtil.setTermRestrictions(valueQuery, queryMap );    
//    
//    QueryUtil.setNumericRestrictions(valueQuery, queryConfig);
//    
//    valueQuery = QueryUtil.setQueryDates(xml, valueQuery, SprayData.SPRAYDATE);
//    valueQuery = QueryUtil.setQueryRatio(xml, valueQuery, "COUNT(*)");
    return valueQuery;
  }

}
