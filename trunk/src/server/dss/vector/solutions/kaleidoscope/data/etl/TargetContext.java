/**
 * Copyright (c) 2015 TerraFrame, Inc. All rights reserved.
 *
 * This file is part of Runway SDK(tm).
 *
 * Runway SDK(tm) is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Runway SDK(tm) is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with Runway SDK(tm). If not, see <http://www.gnu.org/licenses/>.
 */
package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.runwaysdk.dataaccess.BusinessDAO;
import com.runwaysdk.dataaccess.BusinessDAOIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.BusinessDAOQuery;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.system.metadata.MdClass;
import com.runwaysdk.system.metadata.MdWebForm;

import dss.vector.solutions.generator.MdFormUtil;

public class TargetContext implements TargetContextIF, Reloadable
{
  private Map<String, TargetDefinitionIF> definitions;

  private Map<String, Set<String>>        locationExclusions;

  private Map<String, Set<String>>        categoryExclusions;

  public TargetContext()
  {
    this.definitions = new HashMap<String, TargetDefinitionIF>();
  }

  public void addDefinition(TargetDefinitionIF definition)
  {
    this.definitions.put(definition.getSourceType(), definition);
  }

  public TargetDefinitionIF getDefinition(String sourceType)
  {
    return this.definitions.get(sourceType);
  }

  public Map<String, Set<String>> getLocationExclusions()
  {
    return locationExclusions;
  }

  public void setLocationExclusions(Map<String, Set<String>> locationExclusions)
  {
    this.locationExclusions = locationExclusions;
  }

  @Override
  public Map<String, Set<String>> getCategoryExclusions()
  {
    return this.categoryExclusions;
  }

  public void setCategoryExclusions(Map<String, Set<String>> categoryExclusions)
  {
    this.categoryExclusions = categoryExclusions;
  }

  @Override
  public String getType(String sourceType)
  {
    TargetDefinitionIF definition = this.getDefinition(sourceType);

    return definition.getTargetType();
  }

  @Override
  public BusinessDAO getOrCreateMutable(String sourceType, String oid)
  {
    TargetDefinitionIF definition = this.getDefinition(sourceType);

    if (definition != null)
    {
      String type = definition.getTargetType();
      MdWebForm mdForm = MdWebForm.getByKey(type);
      MdClass mdClass = mdForm.getFormMdClass();

      if (oid != null)
      {
        QueryFactory factory = new QueryFactory();
        BusinessDAOQuery query = factory.businessDAOQuery(mdClass.definesType());
        query.WHERE(query.aCharacter(MdFormUtil.OID).EQ(oid));

        OIterator<BusinessDAOIF> it = query.getIterator();

        try
        {
          if (it.hasNext())
          {
            return it.next().getBusinessDAO();
          }
        }
        finally
        {
          it.close();
        }

      }

      return BusinessDAO.newInstance(mdClass.definesType());
    }

    return null;
  }

  @Override
  public List<TargetFieldIF> getFields(String sourceType)
  {
    TargetDefinitionIF definition = this.getDefinition(sourceType);

    return definition.getFields();
  }

  @Override
  public List<TargetDefinitionIF> getDefinitions()
  {
    return new LinkedList<TargetDefinitionIF>(this.definitions.values());
  }

  public void persist()
  {
    List<TargetDefinitionIF> definitions = this.getDefinitions();

    for (TargetDefinitionIF definition : definitions)
    {
      definition.persist();
    }
  }

}
