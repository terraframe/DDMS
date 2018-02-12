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
package dss.vector.solutions.irs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.CommonGenerationUtil;
import com.runwaysdk.generation.loader.Reloadable;

import dss.vector.solutions.GridBuilder;
import dss.vector.solutions.general.EpiDateDTO;
import dss.vector.solutions.general.MalariaSeasonDTO;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.yui.ColumnSetup;
import dss.vector.solutions.util.yui.DataGrid;
import dss.vector.solutions.util.yui.ViewDataGrid;

public class GeoTargetGridBuilder extends GridBuilder implements Reloadable
{
  private ClientRequestIF    request;

  private GeoTargetViewDTO[] data;

  private MalariaSeasonDTO   season;

  private HttpServletRequest req;

  public GeoTargetGridBuilder(ClientRequestIF request, GeoTargetViewDTO[] data, MalariaSeasonDTO season, HttpServletRequest req)
  {
    this.request = request;
    this.data = data;
    this.season = season;
    this.req = req;
  }

  public DataGrid build()
  {
    GeoTargetViewDTO view = new GeoTargetViewDTO(request);

    EpiDateDTO[] weeks = season.getEpiWeeks();

    String[] keys = this.getKeys(weeks);
    Map<String, ColumnSetup> map = this.getColumns(weeks);

    GridBuilder.setEditable(map, GeoTargetViewDTO.ENTITYLABEL, false);

    return new ViewDataGrid(view, map, keys, data);
  }

  private Map<String, ColumnSetup> getColumns(EpiDateDTO[] weeks)
  {
    Map<String, ColumnSetup> map = new HashMap<String, ColumnSetup>();

    map.put(CommonGenerationUtil.upperFirstCharacter(GeoTargetViewDTO.TARGETID), new ColumnSetup(true, false));
    map.put(CommonGenerationUtil.upperFirstCharacter(GeoTargetViewDTO.GEOENTITY), new ColumnSetup(true, false));
    map.put(CommonGenerationUtil.upperFirstCharacter(GeoTargetViewDTO.ENTITYLABEL), new ColumnSetup(false, false));
    map.put(CommonGenerationUtil.upperFirstCharacter(GeoTargetViewDTO.SEASON), new ColumnSetup(true, false));

    for (EpiDateDTO week : weeks)
    {
      String startDate = Halp.getFormatedDate(req, week.getStartDate());
      String endDate = Halp.getFormatedDate(req, week.getEndDate());
      Integer numberOfWeeks = week.getNumberOfEpiWeeks();

      int index = ( week.getPeriod() % numberOfWeeks );
      String key = CommonGenerationUtil.upperFirstCharacter(GeoTargetViewDTO.TARGET + index);
      String label = new Integer(index + 1).toString();

      ColumnSetup setup = new ColumnSetup(false, true);
      setup.setSum(true);
      setup.setTitle(startDate + " -> " + endDate);
      setup.setLabel(label);
      setup.setWidth(20);
      setup.setIndicateRequired(false);

      map.put(key, setup);
    }

    return map;
  }

  private String[] getKeys(EpiDateDTO[] weeks)
  {
    List<String> keys = new LinkedList<String>();

    keys.add(GeoTargetViewDTO.TARGETID);
    keys.add(GeoTargetViewDTO.GEOENTITY);
    keys.add(GeoTargetViewDTO.ENTITYLABEL);
    keys.add(GeoTargetViewDTO.SEASON);

    for (EpiDateDTO week : weeks)
    {
      Integer numberOfWeeks = week.getNumberOfEpiWeeks();
      int index = ( week.getPeriod() % numberOfWeeks );
      String key = CommonGenerationUtil.upperFirstCharacter(GeoTargetViewDTO.TARGET + index);

      keys.add(key);
    }

    String[] array = keys.toArray(new String[keys.size()]);

    upperFirstCharacter(array);

    return array;
  }

}
