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
package dss.vector.solutions.entomology.assay;

import java.util.LinkedList;
import java.util.List;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OrderBy.SortOrder;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.general.Insecticide;
import dss.vector.solutions.general.KnockDownTimeProperty;
import dss.vector.solutions.general.KnockDownTimePropertyQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.util.QueryUtil;

public class KnockDownAssay extends KnockDownAssayBase implements com.runwaysdk.generation.loader.Reloadable, UniqueAssay
{
  private static final long serialVersionUID = 1237230639050L;

  public KnockDownAssay()
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
    else if(this.getUniqueAssayId() != null)
    {
      return this.getUniqueAssayId();
    }
    else if (this.getCollection() != null && this.getInsecticide() != null)
    {
      return "(" + this.getCollection().getCollectionId() + ", " + this.getInsecticide().toString() + ")";
    }

    return super.toString();
  }

  @Override
  public void apply()
  {
    UniqueAssayUtil.setUniqueAssayId(this);
    
    super.apply();

    if (this.isResistant() && this.getInsecticide() != null && this.getCollection() != null)
    {
      Term activeIngredient = this.getInsecticide().getActiveIngredient();
      String label = activeIngredient.getTermDisplayLabel().getValue(Session.getCurrentLocale());
      String collectionId = this.getCollection().getCollectionId();

      ResistantCollection info = new ResistantCollection();
      info.setActiveIngredient(label);
      info.setCollectionId(collectionId);
      info.throwIt();
    }
  }

  @Transaction
  public void applyAll(KnockDownIntervalView[] intervals)
  {
    this.apply();

    for (KnockDownIntervalView interval : intervals)
    {
      interval.setAssay(this);
      interval.apply();
    }
  }

  @Override
  @Transaction
  public void delete()
  {
    KnockDownIntervalQuery query = new KnockDownIntervalQuery(new QueryFactory());
    query.WHERE(query.getAssay().EQ(this));
    query.ORDER_BY(query.getIntervalTime(), SortOrder.ASC);

    OIterator<? extends KnockDownInterval> iterator = query.getIterator();

    try
    {
      List<? extends KnockDownInterval> intervals = iterator.getAll();

      for (KnockDownInterval interval : intervals)
      {
        interval.delete();
      }
    }
    finally
    {
      iterator.close();
    }
    
    super.delete();
  }

  @SuppressWarnings("unchecked")
  @Override
  public KnockDownIntervalView[] getIntervals()
  {
    KnockDownIntervalViewQuery query = new KnockDownIntervalViewQuery(new QueryFactory());
    query.WHERE(query.getAssay().EQ(this));
    query.ORDER_BY(query.getIntervalTime(), SortOrder.ASC);

    OIterator<? extends KnockDownIntervalView> iterator = query.getIterator();

    try
    {
      List<KnockDownIntervalView> list = (List<KnockDownIntervalView>) iterator.getAll();

      if (list.size() == 0 && this.isNew())
      {
        /*
         * Return the default intervals
         */
        list = new LinkedList<KnockDownIntervalView>();
        list.add(KnockDownIntervalView.build(this, 10));
        list.add(KnockDownIntervalView.build(this, 20));
        list.add(KnockDownIntervalView.build(this, 30));
        list.add(KnockDownIntervalView.build(this, 40));
        list.add(KnockDownIntervalView.build(this, 50));
        list.add(KnockDownIntervalView.build(this, 60));
      }

      return list.toArray(new KnockDownIntervalView[list.size()]);
    }
    finally
    {
      iterator.close();
    }
  }

  protected boolean isResistant()
  {
    Insecticide _insecticide = this.getInsecticide();
    Double _kd50 = this.getKd50();
    Double _kd95 = this.getKd95();

    if (_insecticide != null && _kd50 != null && _kd95 != null)
    {
      KnockDownTimePropertyQuery query = new KnockDownTimePropertyQuery(new QueryFactory());

      query.WHERE(query.getInsecticide().EQ(_insecticide));
      OIterator<? extends KnockDownTimeProperty> iterator = query.getIterator();

      try
      {
        if (iterator.hasNext())
        {
          KnockDownTimeProperty property = iterator.next();
          Integer lowerCutoff = property.getLowerTime();
          Integer upperCutoff = property.getUpperTime();

          return ( ( lowerCutoff <= _kd50 ) && ( upperCutoff <= _kd95 ) );
        }
      }
      finally
      {
        iterator.close();
      }
    }

    return false;
  }

  public static String getResistanceSQL(String[] labels)
  {

    String resistance_result = "resistance_result";

    String susceptibleLabel = labels[0];
    String potentialyResistantLabel = labels[1];
    String resistantLabel = labels[2];

    String assayTable = MdBusiness.getMdBusiness(KnockDownAssay.CLASS).getTableName();
    String collectionAssayTable = MdBusiness.getMdBusiness(CollectionAssay.CLASS).getTableName();
    String knockDownTimeTable = MdBusiness.getMdBusiness(KnockDownTimeProperty.CLASS).getTableName();

    String kd50 = QueryUtil.getColumnName(KnockDownAssay.getKd50Md());
    String kd95 = QueryUtil.getColumnName(KnockDownAssay.getKd95Md());

    String insectidce = QueryUtil.getColumnName(KnockDownAssay.getInsecticideMd());
    String lowerTime = QueryUtil.getColumnName(KnockDownTimeProperty.getLowerTimeMd());
    String upperTime = QueryUtil.getColumnName(KnockDownTimeProperty.getUpperTimeMd());

    String select = "SELECT assay.id AS id,";

    select += "(CASE WHEN (assay." + kd50 + " >= " + lowerTime + " AND assay." + kd95 + " >= " + upperTime + ") THEN '" + resistantLabel + "' ";
    select += "WHEN (assay." + kd50 + " >= " + lowerTime + " OR assay." + kd95 + " >= " + upperTime + ")   THEN '" + potentialyResistantLabel + "' ";
    select += "WHEN (assay." + kd50 + " < " + lowerTime + " OR assay." + kd95 + " < " + upperTime + ")   THEN '" + susceptibleLabel + "' ";
    select += "ELSE '' END) AS " + resistance_result + "";

    String from = "FROM " + collectionAssayTable + " AS collectionAssay, " + assayTable + " AS assay, " + knockDownTimeTable + " AS cutoff ";

    String where = "WHERE (collectionAssay." + insectidce + " = cutoff." + insectidce + " AND collectionAssay.id = assay.id) ";

    return select + "\n" + from + "\n" + where;
  }
}
