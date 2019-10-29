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
package dss.vector.solutions.form.business;

import com.runwaysdk.dataaccess.MdWebAttributeDAOIF;
import com.runwaysdk.dataaccess.MdWebFormDAOIF;
import com.runwaysdk.dataaccess.metadata.MdWebFormDAO;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.LeftJoinEq;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.SelectableChar;
import com.runwaysdk.query.SelectablePrimitive;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.generator.FormIdAlreadyExistException;
import dss.vector.solutions.query.QueryBuilder;

public class FormBedNet extends FormBedNetBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = -1917753186;

  public static final String FORM_TYPE        = "dss.vector.solutions.form.FormBedNet";

  public static final String NET_ID           = "netId";

  public FormBedNet()
  {
    super();
  }

  @Override
  protected String buildKey()
  {
    if (this.getOid() != null && this.getOid().length() > 0 && this.getSurvey() != null)
    {
      return this.getSurvey().getKey() + " " + this.getOid();
    }

    return super.buildKey();
  }

  public static FormBedNet getByBedNetId(String suveryId, String netId)
  {
    FormBedNetQuery query = new FormBedNetQuery(new QueryFactory());
    query.WHERE(AND.get(query.getSurvey().getOid().EQ(suveryId), query.getOid().EQ(netId)));

    OIterator<? extends FormBedNet> it = query.getIterator();

    try
    {
      if (it.hasNext())
      {
        return it.next();
      }
    }
    finally
    {
      it.close();
    }

    return null;
  }

  public static ValueQuery getNetIds(String value)
  {
    QueryFactory f = new QueryFactory();

    ValueQuery valueQuery = new ValueQuery(f);
    FormBedNetQuery q = new FormBedNetQuery(valueQuery);

    SelectableChar orderBy = q.getOid(FormBedNet.OID);
    SelectablePrimitive[] selectables = new SelectablePrimitive[] { orderBy };

    Condition[] conditions = new Condition[] {};
    LeftJoinEq[] joins = new LeftJoinEq[] {};

    if (value != null && value.length() > 0)
    {
      String[] tokens = value.split(" ");
      SelectablePrimitive[] searchables = new SelectablePrimitive[] { orderBy };

      QueryBuilder.textLookup(valueQuery, f, tokens, searchables, selectables, conditions, joins);
    }
    else
    {
      QueryBuilder.distinctOrderedLookup(valueQuery, f, orderBy, selectables, conditions, joins);
    }

    valueQuery.restrictRows(20, 1);

    return valueQuery;
  }

  public static void validateNetId(String value)
  {
    FormBedNetQuery query = new FormBedNetQuery(new QueryFactory());

    query.WHERE(query.getOid().EQ(value));

    if (query.getCount() > 0)
    {
      MdWebFormDAOIF mdForm = (MdWebFormDAOIF) MdWebFormDAO.getMdTypeDAO(FormBedNet.FORM_TYPE);
      MdWebAttributeDAOIF mdField = (MdWebAttributeDAOIF) mdForm.getMdField(NET_ID);

      String msg = "A form household already exists with the oid [" + value + "]";

      FormIdAlreadyExistException e = new FormIdAlreadyExistException(msg);
      e.setTypeDisplayLabel(mdForm.getDisplayLabel(Session.getCurrentLocale()));
      e.setAttributeDisplayLabel(mdField.getDisplayLabel(Session.getCurrentLocale()));
      e.setValue(value);
      e.apply();

      throw e;
    }
  }

}
