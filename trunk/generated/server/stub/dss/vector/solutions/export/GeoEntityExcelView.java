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

import com.runwaysdk.business.BusinessFacade;
import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.io.ExcelImporter.ImportContext;
import com.runwaysdk.dataaccess.metadata.MdTypeDAO;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.AND;
import com.runwaysdk.query.Condition;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.OR;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Session;
import com.runwaysdk.system.metadata.MdBusiness;
import com.runwaysdk.system.metadata.MdBusinessQuery;

import dss.vector.solutions.AmbigiousGeoEntityException;
import dss.vector.solutions.ExcelImportManager;
import dss.vector.solutions.MDSSInfo;
import dss.vector.solutions.UnknownGeoEntityException;
import dss.vector.solutions.UnknownTermProblem;
import dss.vector.solutions.geo.AllPaths;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.GeoTypeMismatchException;
import dss.vector.solutions.geo.LocatedIn;
import dss.vector.solutions.geo.UnknownGeoParentException;
import dss.vector.solutions.geo.generated.GeoEntity;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;

public class GeoEntityExcelView extends GeoEntityExcelViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long  serialVersionUID = 1247004716597L;

  public static final String LARGE_GEO_DATA   = "+++";

  public static final String NOT_EXPORTED     = "###";

  private String             parentGeoEntityId;

  public GeoEntityExcelView()
  {
    super();
  }

  @Override
  public void apply()
  {
    GeoEntity entity;
    String newGeoId = this.getGeoId();
    String pid = inferParentId();
    boolean newLocatedIn;

    /*
     * If an entity is being imported under itself, NEVER delete its located in
     * values or apply it under a new parent.
     */
    boolean selfImport = false;

    String entityType = buildQualifiedType(this.getGeoType());
    if (entityType == null)
    {
      throw new DataNotFoundException("Invalid value: " + this.getGeoType(), MdTypeDAO.getMdTypeDAO(GeoHierarchy.CLASS));
    }

    GeoEntityQuery q = new GeoEntityQuery(new QueryFactory());
    q.WHERE(q.getGeoId().EQ(newGeoId));

    OIterator<? extends GeoEntity> it = q.getIterator();
    try
    {
      if (it.hasNext())
      {
        entity = it.next();

        // Validate that the entity and the universal type match
        if (!entity.getType().equals(entityType))
        {
          GeoTypeMismatchException e = new GeoTypeMismatchException();
          e.setGeoId(newGeoId);
          e.setGeoType(this.getGeoType());
          e.apply();

          throw e;
        }

        entity.lock();

        selfImport = entity.getId().equals(pid);
        if (!selfImport)
        {
          // Per Miguel's instructions, importing removes all prior located_in
          // relationships, semantically "moving" the entity
          for (LocatedIn l : entity.getAllLocatedInGeoEntityRel())
          {
            // Don't bother to delete the node we want to end up under
            if (!l.getParentId().equals(pid))
            {
              entity.deleteRelationship(l.getParentId());
            }
          }
        }
      }
      else
      {
        entity = (GeoEntity) BusinessFacade.newBusiness(entityType);
      }
    }
    finally
    {
      it.close();
    }

    if (selfImport)
    {
      // an entity is being imported into itself, so just update the entity and
      // do nothing
      // relative to located_in relationships.
      newLocatedIn = false;
    }
    else
    {
      dss.vector.solutions.geo.AllPathsQuery query = new dss.vector.solutions.geo.AllPathsQuery(new QueryFactory());
      query.WHERE(query.getChildGeoEntity().getGeoId().EQ(newGeoId));
      query.WHERE(query.getParentGeoEntity().getId().EQ(pid));
      OIterator<? extends AllPaths> iterator = query.getIterator();
      if (iterator.hasNext())
      {
        newLocatedIn = false;
      }
      else
      {
        newLocatedIn = true;
      }
    }

    entity.getEntityLabel().setValue(this.getEntityLabel());
    entity.setActivated(this.getActivated() != null && this.getActivated());
    entity.setGeoId(newGeoId);
    entity.setTerm(this.validateGeoSubtypeByDisplayLabel(entity, this.getSubType(), GeoEntity.getTermMd()));

    String importData = this.getGeometryWKT();
    if (!importData.equals(LARGE_GEO_DATA) && !importData.equals(NOT_EXPORTED))
    {
      entity.setGeoData(importData);
    }

    if (newLocatedIn)
    {
      entity.applyWithParent(pid, false, null); // we don't want to call this because it may rebuild the allpaths table, which we've already done.
      
//      entity.addLocatedInGeoEntity(GeoEntity.get(pid)).apply();
//      GeoEntity.copyTermFast(pid, entity.getId());
    }
    else
    {
      entity.apply();
    }
  }

  private String buildQualifiedType(String input)
  {
    int lastDot = input.lastIndexOf('.');

    String geoPackage = null;
    String geoType = null;
    if (lastDot > 0)
    {
      geoPackage = input.substring(0, lastDot);
      geoType = input.substring(lastDot + 1);
    }
    else
    {
      geoPackage = MDSSInfo.GENERATED_GEO_PACKAGE;
      geoType = input;
    }

    MdBusinessQuery query = new MdBusinessQuery(new QueryFactory());
    query.WHERE(query.getPackageName().EQ(geoPackage));
    query.AND(OR.get(query.getTypeName().EQi(geoType), query.getDisplayLabel().localize().EQi(geoType)));
    OIterator<? extends MdBusiness> iterator = query.getIterator();
    try
    {
      if (iterator.hasNext())
      {
        return iterator.next().definesType();
      }
      return null;
    }
    finally
    {
      iterator.close();
    }
  }

  @Override
  public String getGeoId()
  {
    return super.getGeoId(); // .split("\\.")[0];
  }

  public static void setupImportListener(ImportContext context, String[] params, ExcelImportManager importer)
  {
    context.addListener(new GeoParentListener(params[0]));
  }

  public static List<String> customAttributeOrder()
  {
    LinkedList<String> list = new LinkedList<String>();
    list.add(ENTITYLABEL);
    list.add(GEOID);
    list.add(GEOTYPE);
    list.add(SUBTYPE);
    list.add(PARENTNAME);
    list.add(PARENTTYPE);
    list.add(ACTIVATED);
    list.add(GEOMETRYWKT);
    return list;
  }

  public String inferParentId()
  {
    String pName = this.getParentName();
    String pType = this.getParentType();

    if (pName == null || pName.length() == 0)
    {
      return parentGeoEntityId;
    }

    GeoEntityQuery query = new GeoEntityQuery(new QueryFactory());
    Condition condition = OR.get(query.getEntityLabel().localize().EQ(pName), query.getGeoId().EQ(pName));

    if (pType != null && pType.length() > 0)
    {
      String geoType = buildQualifiedType(pType);

      condition = AND.get(condition, query.getType().EQ(geoType));
    }

    query.WHERE(condition);

    OIterator<? extends GeoEntity> iterator = query.getIterator();

    try
    {
      if (!iterator.hasNext())
      {
        if (pType != null && pType.length() > 0)
        {
          UnknownGeoParentException exception = new UnknownGeoParentException();
          exception.setGeoId(pName);
          exception.setGeoType(pType);
          exception.apply();

          throw exception;
        }
        else
        {
          UnknownGeoEntityException exception = new UnknownGeoEntityException();
          exception.setEntityName(pName);
          exception.apply();

          throw exception;
        }
      }

      GeoEntity parent = iterator.next();

      if (iterator.hasNext())
      {
        String msg = "Geo Entity name [" + pName + "] is ambiguous (It has more than one possible solution)";
        AmbigiousGeoEntityException ex = new AmbigiousGeoEntityException(msg);
        ex.setEntityName(pName);

        throw ex;
      }

      return parent.getId();
    }
    finally
    {
      iterator.close();
    }
  }

  public String getParentGeoEntityId()
  {
    return parentGeoEntityId;
  }

  public void setParentGeoEntityId(String parentGeoEntityId)
  {
    this.parentGeoEntityId = parentGeoEntityId;
  }

  @Transaction
  public Term validateGeoSubtypeByDisplayLabel(GeoEntity entity, String displayLabel, MdAttributeDAOIF mdAttribute)
  {
    // No value means they didn't specify one. Don't throw a problem; just
    // return null
    if (displayLabel.trim().length() == 0)
    {
      return null;
    }

    QueryFactory factory = new QueryFactory();

    String type = entity.getType();
    GeoHierarchy universal = GeoHierarchy.getGeoHierarchyFromType(type);
    Term universalMoRoot = universal.getTerm();

    AllPathsQuery apq = new AllPathsQuery(factory);
    apq.WHERE(apq.getParentTerm().EQ(universalMoRoot));

    TermQuery tq = new TermQuery(factory);
    tq.WHERE(tq.getName().EQi(displayLabel));
    tq.WHERE(tq.getId().EQ(apq.getChildTerm().getId()));

    OIterator<? extends Term> iterator = tq.getIterator();

    try
    {
      if (iterator.hasNext())
      {
        return iterator.next();
      }

      String attributeLabel = mdAttribute.getDisplayLabel(Session.getCurrentLocale());
      String msg = "Unknown " + attributeLabel + " with the given name [" + displayLabel + "]";

      UnknownTermProblem e = new UnknownTermProblem(msg);
      e.setTermName(displayLabel);
      e.setAttributeLabel(attributeLabel);
      e.throwIt();

      // We expect to return nothing, as we're throwing a problem, but include
      // this to satisfy the compile time requirement
      return null;
    }
    finally
    {
      iterator.close();
    }
  }
}
