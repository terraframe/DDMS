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
package dss.vector.solutions.kaleidoscope.data.etl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.business.Transient;
import com.runwaysdk.dataaccess.MdAttributeConcreteDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.ProgrammingErrorException;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdAttribute;
import com.runwaysdk.system.metadata.MdWebAttribute;
import com.runwaysdk.util.IDGenerator;

import dss.vector.solutions.DefaultGeoEntity;
import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoEntitySelectionProblem;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoSynonymQuery;
import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.kaleidoscope.ontology.NonUniqueEntityResultException;
import dss.vector.solutions.util.QueryUtil;

public class TargetFieldGeoEntity extends TargetField implements TargetFieldGeoEntityIF, TargetFieldValidationIF, Reloadable
{
  public static class UniversalAttribute implements Reloadable
  {
    private String       attributeName;

    private String       label;

    private GeoHierarchy universal;

    public UniversalAttribute(String attributeName, String label, GeoHierarchy universal)
    {
      this.attributeName = attributeName;
      this.label = label;
      this.universal = universal;
    }

    public String getAttributeName()
    {
      return attributeName;
    }

    public void setAttributeName(String attributeName)
    {
      this.attributeName = attributeName;
    }

    public GeoHierarchy getUniversal()
    {
      return universal;
    }

    public void setUniversal(GeoHierarchy universal)
    {
      this.universal = universal;
    }

    public String getLabel()
    {
      return label;
    }
  }

  private ArrayList<UniversalAttribute> attributes;

  private String                        id;

  private GeoEntity                     root;

  private GeoHierarchy                  rootUniversal;

  private GeoEntity                         defaultGeo;

  public TargetFieldGeoEntity()
  {
    this.attributes = new ArrayList<UniversalAttribute>();
    this.id = IDGenerator.nextID();
    this.defaultGeo = DefaultGeoEntity.getDefaultGeoEntity().getGeoEntity();
  }

  public String getId()
  {
    return id;
  }

  public GeoEntity getRoot()
  {
    return root;
  }

  public void setRoot(GeoEntity root)
  {
    this.root = root;
    this.rootUniversal = GeoHierarchy.getGeoHierarchyFromType(root.getMdClass().definesType());
  }

  public void addUniversalAttribute(String attributeName, String label, GeoHierarchy universal)
  {
    this.attributes.add(new UniversalAttribute(attributeName, label, universal));
  }

  @Override
  public Map<String, String> getUniversalAttributes()
  {
    Map<String, String> map = new HashMap<String, String>();

    for (UniversalAttribute attribute : this.attributes)
    {
      map.put(attribute.getAttributeName(), attribute.getUniversal().getId());
    }

    return map;
  }

  @Override
  public FieldValue getValue(MdAttributeConcreteDAOIF mdAttribute, Transient source)
  {
    List<String> labels = new ArrayList<String>();

    for (UniversalAttribute attribute : attributes)
    {
      labels.add(source.getValue(attribute.getAttributeName()));
    }

    try
    {
      GeoEntity entity = this.getLocation(this.root, labels);

      return new FieldValue(entity.getId());
    }
    catch (ExclusionException e)
    {
      return e.getProblem();
    }
  }

  /**
   * 
   * 
   * @param labels
   * @return
   */
  private GeoEntity getLocation(GeoEntity root, List<String> labels)
  {
    GeoEntity parent = root;
    List<JSONObject> context = new LinkedList<JSONObject>();

    for (int i = 0; i < attributes.size(); i++)
    {
      UniversalAttribute attribute = attributes.get(i);
      String label = labels.get(i);

      if (label != null && label.length() > 0)
      {
        GeoHierarchy universal = attribute.getUniversal();

        if (rootUniversal.getKey().equals(universal.getKey()))
        {
          parent = root;
        }
        else
        {
          GeoEntity entity = this.findGeoEntity(parent, universal, label);

          if (entity == null)
          {
            GeoEntitySelectionProblem p = new GeoEntitySelectionProblem("Unknown Geo Entity");
            p.setEntityLabel(label);
            p.apply();

            p.throwIt();

            throw new ExclusionException("Location not found in system.", new LocationProblem(label, context, parent, universal));
          }

          parent = entity;
        }
      }

      try
      {
        JSONObject object = new JSONObject();
        object.put("label", label);
        object.put("universal", parent.getMdClass().getDisplayLabel(Session.getCurrentLocale()));

        context.add(object);
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }

    return parent;
  }

  private GeoEntity findGeoEntity(GeoEntity parent, GeoHierarchy universal, String label)
  {
    QueryFactory factory = new QueryFactory();

    AllPathsQuery aptQuery = new AllPathsQuery(factory);
    aptQuery.WHERE(aptQuery.getParentGeoEntity().EQ(parent));

    GeoSynonymQuery synonymQuery = new GeoSynonymQuery(factory);
    synonymQuery.WHERE(synonymQuery.getEntityName().EQi(label));

    MdBusinessDAOIF mdBusinessDAOIF = MdBusinessDAO.get(universal.getGeoEntityClassId());
    GeoEntityQuery query = (GeoEntityQuery) QueryUtil.getQuery(mdBusinessDAOIF, factory);
    query.AND(query.getId().EQ(aptQuery.getChildGeoEntity().getId()));
    query.AND(OR.get(query.getEntityLabel().localize().EQi(label), query.getGeoId().EQ(label), query.synonyms(synonymQuery)));

    OIterator<? extends GeoEntity> iterator = query.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        GeoEntity entity = iterator.next();

        if (iterator.hasNext())
        {
          NonUniqueEntityResultException e = new NonUniqueEntityResultException();
          e.setLabel(label);
          e.setUniversal(universal.getDisplayLabel());
          e.setParent(parent.getEntityLabel().getValue());

          throw e;
        }

        return entity;
      }

      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public void persist(TargetBinding binding)
  {
    MdWebAttribute targetAttribute = MdWebAttribute.getByKey(this.getKey());

    TargetFieldGeoEntityBinding field = new TargetFieldGeoEntityBinding();
    field.setTarget(binding);
    field.setTargetAttribute(targetAttribute);
    field.setColumnLabel(this.getLabel());
    field.setGeoEntity(this.root);
    field.apply();

    for (UniversalAttribute attribute : this.attributes)
    {
      MdAttribute sourceAttribute = MdAttribute.getByKey(binding.getSourceView().definesType() + "." + attribute.getAttributeName());

      UniversalAttributeBinding uAttribute = new UniversalAttributeBinding();
      uAttribute.setField(field);
      uAttribute.setSourceAttribute(sourceAttribute);
      uAttribute.setUniversal(attribute.getUniversal());
      uAttribute.apply();
    }
  }

  @Override
  public JSONObject toJSON() throws JSONException
  {
    JSONObject fields = new JSONObject();

    for (UniversalAttribute attribute : this.attributes)
    {
      fields.put(attribute.getUniversal().getId(), attribute.getLabel());
    }

    UniversalAttribute attribute = this.attributes.get(this.attributes.size() - 1);

    JSONObject object = new JSONObject();
    object.put("label", this.getLabel());
    object.put("universal", attribute.getUniversal().getId());
    object.put("fields", fields);
    object.put("id", this.id);

    return object;
  }

  /*
   * (non-Javadoc)
   * 
   * @see dss.vector.solutions.kaleidoscope.data.etl.TargetFieldGeoEntityIF#
   * getLocationProblem(com.runwaysdk.business.Transient,
   * com.runwaysdk.system.gis.geo.Universal)
   */
  @SuppressWarnings("unchecked")
  @Override
  public ImportProblemIF validate(Transient source, Map<String, Object> parameters)
  {
    GeoEntity parent = this.root;

    Map<String, Set<String>> locationExclusions = (Map<String, Set<String>>) parameters.get("locationExclusions");

    List<JSONObject> context = new LinkedList<JSONObject>();

    for (UniversalAttribute attribute : attributes)
    {
      String label = source.getValue(attribute.getAttributeName());
      GeoHierarchy entityUniversal = attribute.getUniversal();

      if (label != null && label.length() > 0)
      {
        if (this.isExcluded(locationExclusions, defaultGeo, entityUniversal, parent, label))
        {
          return null;
        }

        if (parent.getType().equals(entityUniversal.getId()))
        {
          GeoEntity entity = this.findGeoEntity(defaultGeo, entityUniversal, label);

          if (entity == null)
          {
            return new LocationProblem(label, context, defaultGeo, entityUniversal);
          }
        }
        else
        {
          GeoEntity entity = this.findGeoEntity(parent, entityUniversal, label);

          if (entity == null)
          {
            return new LocationProblem(label, context, parent, entityUniversal);
          }
          else
          {
            parent = entity;
          }
        }
      }

      try
      {
        JSONObject object = new JSONObject();
        object.put("label", label);
        object.put("universal", parent.getMdClass().getDisplayLabel(Session.getCurrentLocale()));

        context.add(object);
      }
      catch (JSONException e)
      {
        throw new ProgrammingErrorException(e);
      }
    }

    return null;
  }

  private boolean isExcluded(Map<String, Set<String>> locationExclusions, GeoEntity defaultGeo, GeoHierarchy universal, GeoEntity parent, String label)
  {
    String key = universal.getQualifiedType() + "-" + parent.getId();

    if (universal.getId().equals(this.rootUniversal.getId()))
    {
      key = universal.getId() + "-" + defaultGeo.getId();
    }

    if (locationExclusions.containsKey(key))
    {
      Set<String> labels = locationExclusions.get(key);

      return labels.contains(label);
    }

    return false;
  }
}
