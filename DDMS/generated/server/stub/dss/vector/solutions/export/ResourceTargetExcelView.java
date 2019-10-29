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
package dss.vector.solutions.export;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;

import dss.vector.solutions.general.MalariaSeason;
import dss.vector.solutions.general.MalariaSeasonQuery;
import dss.vector.solutions.irs.ResourceTarget;
import dss.vector.solutions.irs.ResourceTargetQuery;
import dss.vector.solutions.irs.ResourceTargetView;
import dss.vector.solutions.irs.SprayTeam;
import dss.vector.solutions.irs.SprayTeamQuery;
import dss.vector.solutions.irs.Targeter;
import dss.vector.solutions.irs.TeamMember;
import dss.vector.solutions.irs.TeamMemberQuery;

public class ResourceTargetExcelView extends ResourceTargetExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -282499924;

  public ResourceTargetExcelView()
  {
    super();
  }

  @Override
  public void apply()
  {
    Targeter targeter = this.getTargeter();
    MalariaSeason season = this.getMalariaSeason();

    ResourceTargetView view = this.getView(targeter, season);
    view.setTarget_0(this.getTarget_0());
    view.setTarget_1(this.getTarget_1());
    view.setTarget_2(this.getTarget_2());
    view.setTarget_3(this.getTarget_3());
    view.setTarget_4(this.getTarget_4());
    view.setTarget_5(this.getTarget_5());
    view.setTarget_6(this.getTarget_6());
    view.setTarget_7(this.getTarget_7());
    view.setTarget_8(this.getTarget_8());
    view.setTarget_9(this.getTarget_9());
    view.setTarget_10(this.getTarget_10());
    view.setTarget_11(this.getTarget_11());
    view.setTarget_12(this.getTarget_12());
    view.setTarget_13(this.getTarget_13());
    view.setTarget_14(this.getTarget_14());
    view.setTarget_15(this.getTarget_15());
    view.setTarget_16(this.getTarget_16());
    view.setTarget_17(this.getTarget_17());
    view.setTarget_18(this.getTarget_18());
    view.setTarget_19(this.getTarget_19());
    view.setTarget_20(this.getTarget_20());
    view.setTarget_21(this.getTarget_21());
    view.setTarget_22(this.getTarget_22());
    view.setTarget_23(this.getTarget_23());
    view.setTarget_24(this.getTarget_24());
    view.setTarget_25(this.getTarget_25());
    view.setTarget_26(this.getTarget_26());
    view.setTarget_27(this.getTarget_27());
    view.setTarget_28(this.getTarget_28());
    view.setTarget_29(this.getTarget_29());
    view.setTarget_30(this.getTarget_30());
    view.setTarget_31(this.getTarget_31());
    view.setTarget_32(this.getTarget_32());
    view.setTarget_33(this.getTarget_33());
    view.setTarget_34(this.getTarget_34());
    view.setTarget_35(this.getTarget_35());
    view.setTarget_36(this.getTarget_36());
    view.setTarget_37(this.getTarget_37());
    view.setTarget_38(this.getTarget_38());
    view.setTarget_39(this.getTarget_39());
    view.setTarget_40(this.getTarget_40());
    view.setTarget_41(this.getTarget_41());
    view.setTarget_42(this.getTarget_42());
    view.setTarget_43(this.getTarget_43());
    view.setTarget_44(this.getTarget_44());
    view.setTarget_45(this.getTarget_45());
    view.setTarget_46(this.getTarget_46());
    view.setTarget_47(this.getTarget_47());
    view.setTarget_48(this.getTarget_48());
    view.setTarget_49(this.getTarget_49());
    view.setTarget_50(this.getTarget_50());
    view.setTarget_51(this.getTarget_51());
    view.setTarget_52(this.getTarget_52());

    view.apply();
  }

  private Targeter getTargeter()
  {
    Targeter team = this.getSprayTeam();

    if (team != null)
    {
      return team;
    }

    Targeter member = this.getTeamMember();

    if (member != null)
    {
      return member;
    }

    throw new DataNotFoundException("Unable to find a team member of spray team with the id [" + this.getTargeterId() + "]", MdTypeDAO.getMdTypeDAO(Targeter.CLASS));
  }

  public Targeter getTeamMember()
  {
    TeamMemberQuery query = new TeamMemberQuery(new QueryFactory());
    query.WHERE(query.getMemberId().EQ(this.getTargeterId()));
    OIterator<? extends TeamMember> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  public Targeter getSprayTeam()
  {
    SprayTeamQuery query = new SprayTeamQuery(new QueryFactory());
    query.WHERE(query.getTeamId().EQ(this.getTargeterId()));
    OIterator<? extends SprayTeam> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  public ResourceTargetView getView(Targeter targeter, MalariaSeason season)
  {
    ResourceTargetQuery query = new ResourceTargetQuery(new QueryFactory());
    query.WHERE(query.getTargeter().EQ(targeter));
    query.AND(query.getSeason().EQ(season));

    OIterator<? extends ResourceTarget> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        ResourceTarget target = iterator.next();

        return target.getView();
      }
      else
      {
        ResourceTargetView view = new ResourceTargetView();
        view.setTargeter(targeter);
        view.setSeason(season);
        return view;
      }
    }
    finally
    {
      iterator.close();
    }
  }

  public MalariaSeason getMalariaSeason()
  {
    MalariaSeasonQuery query = new MalariaSeasonQuery(new QueryFactory());
    query.WHERE(query.getSeasonLabel().localize().EQ(this.getSeasonName()));

    OIterator<? extends MalariaSeason> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }
      else
      {
        throw new DataNotFoundException("Transmission season not found", MdTypeDAO.getMdTypeDAO(MalariaSeason.CLASS));
      }
    }
    finally
    {
      iterator.close();
    }
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(SEASONNAME);
    list.add(TARGETERID);
    list.add(TARGET_0);
    list.add(TARGET_1);
    list.add(TARGET_2);
    list.add(TARGET_3);
    list.add(TARGET_4);
    list.add(TARGET_5);
    list.add(TARGET_6);
    list.add(TARGET_7);
    list.add(TARGET_8);
    list.add(TARGET_9);
    list.add(TARGET_10);
    list.add(TARGET_11);
    list.add(TARGET_12);
    list.add(TARGET_13);
    list.add(TARGET_14);
    list.add(TARGET_15);
    list.add(TARGET_16);
    list.add(TARGET_17);
    list.add(TARGET_18);
    list.add(TARGET_19);
    list.add(TARGET_20);
    list.add(TARGET_21);
    list.add(TARGET_22);
    list.add(TARGET_23);
    list.add(TARGET_24);
    list.add(TARGET_25);
    list.add(TARGET_26);
    list.add(TARGET_27);
    list.add(TARGET_28);
    list.add(TARGET_29);
    list.add(TARGET_30);
    list.add(TARGET_31);
    list.add(TARGET_32);
    list.add(TARGET_33);
    list.add(TARGET_34);
    list.add(TARGET_35);
    list.add(TARGET_36);
    list.add(TARGET_37);
    list.add(TARGET_38);
    list.add(TARGET_39);
    list.add(TARGET_40);
    list.add(TARGET_41);
    list.add(TARGET_42);
    list.add(TARGET_43);
    list.add(TARGET_44);
    list.add(TARGET_45);
    list.add(TARGET_46);
    list.add(TARGET_47);
    list.add(TARGET_48);
    list.add(TARGET_49);
    list.add(TARGET_50);
    list.add(TARGET_51);
    list.add(TARGET_52);
    return list;
  }
}
