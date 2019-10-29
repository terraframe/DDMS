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
package dss.vector.solutions.kaleidoscope.dashboard.query;

import java.util.List;

import com.runwaysdk.dataaccess.MdAttributeDAOIF;
import com.runwaysdk.dataaccess.MdAttributeReferenceDAOIF;
import com.runwaysdk.dataaccess.MdBusinessDAOIF;
import com.runwaysdk.dataaccess.metadata.MdAttributeDAO;
import com.runwaysdk.dataaccess.metadata.MdAttributeReferenceDAO;
import com.runwaysdk.dataaccess.metadata.MdBusinessDAO;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.query.Attribute;
import com.runwaysdk.query.GeneratedComponentQuery;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.query.Selectable;
import com.runwaysdk.query.SelectableSingle;
import com.runwaysdk.query.ValueQuery;
import com.runwaysdk.session.Session;

import dss.vector.solutions.geo.AllPathsQuery;
import dss.vector.solutions.geo.GeoHierarchy;
import dss.vector.solutions.geo.generated.GeoEntityQuery;
import dss.vector.solutions.kaleidoscope.dashboard.AllAggregationType;
import dss.vector.solutions.kaleidoscope.dashboard.DashboardStyle;
import dss.vector.solutions.kaleidoscope.dashboard.condition.DashboardCondition;
import dss.vector.solutions.kaleidoscope.dashboard.condition.LocationCondition;
import dss.vector.solutions.kaleidoscope.dashboard.layer.DashboardThematicLayer;
import dss.vector.solutions.kaleidoscope.geo.GeoNode;
import dss.vector.solutions.util.QueryUtil;

public class GeoEntityThematicQueryBuilder extends ThematicQueryBuilder implements Reloadable
{
  private GeoEntityQuery geoEntityQuery;

  private GeoHierarchy   universal;

  private GeoNode        geoNode;

  public GeoEntityThematicQueryBuilder(QueryFactory factory, DashboardThematicLayer layer, GeoHierarchy universal)
  {
    super(factory, layer);

    this.universal = universal;
    this.geoNode = layer.getGeoNode();
  }

  public GeoEntityThematicQueryBuilder(QueryFactory factory, MdAttributeDAOIF thematicMdAttribute, DashboardStyle style, AllAggregationType aggregation, List<DashboardCondition> conditions, GeoHierarchy universal, GeoNode geoNode)
  {
    super(factory, thematicMdAttribute, style, aggregation, conditions);

    this.universal = universal;
    this.geoNode = geoNode;
  }

  @Override
  protected void initialize(ValueQuery vQuery)
  {
    MdBusinessDAOIF mdBusiness = MdBusinessDAO.get(this.universal.getGeoEntityClassId());

    this.geoEntityQuery = (GeoEntityQuery) QueryUtil.getQuery(mdBusiness, vQuery);
  }

  @Override
  protected SelectableSingle getLabelSelectable(GeneratedComponentQuery query)
  {
    MdAttributeDAOIF mdAttribute = MdAttributeDAO.get(this.geoNode.getDisplayLabelAttribute().getId());

    SelectableSingle label = this.geoEntityQuery.getEntityLabel().localize(mdAttribute.definesAttribute());
    label.setColumnAlias(ThematicQueryBuilder.LABEL_ALIAS);
    label.setUserDefinedAlias(ThematicQueryBuilder.LABEL_ALIAS);
    label.setUserDefinedDisplayLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));

    return label;
  }

  @Override
  protected Selectable getIdentifierSelectable(GeneratedComponentQuery query)
  {
    MdAttributeDAOIF mdAttribute = MdAttributeDAO.get(this.geoNode.getIdentifierAttribute().getId());

    // geo id (for uniqueness)
    Selectable geoId = this.geoEntityQuery.getGeoId(mdAttribute.definesAttribute());
    geoId.setUserDefinedAlias(ThematicQueryBuilder.LOCATION_ALIAS);
    geoId.setColumnAlias(ThematicQueryBuilder.LOCATION_ALIAS);
    geoId.setUserDefinedDisplayLabel(mdAttribute.getDisplayLabel(Session.getCurrentLocale()));

    return geoId;
  }

  private Selectable getGeoEntityAttribute(GeneratedComponentQuery componentQuery)
  {
    MdAttributeReferenceDAOIF geoRef = MdAttributeReferenceDAO.get(this.geoNode.getGeoEntityAttributeId());

    // Join the entity's GeoEntity reference with the all paths table
    Selectable geoAttr = componentQuery.get(geoRef.definesAttribute());

    return geoAttr;
  }

  @Override
  protected void addLocationCriteria(ValueQuery vQuery, GeneratedComponentQuery componentQuery)
  {
    Attribute geoAttr = (Attribute) this.getGeoEntityAttribute(componentQuery);

    // the entity's GeoEntity should match the all path's child
    AllPathsQuery geAllPathsQ = new AllPathsQuery(vQuery);
    vQuery.WHERE(geoAttr.LEFT_JOIN_EQ(geAllPathsQ.getChildGeoEntity()));

    // the displayed GeoEntity should match the all path's parent
    vQuery.AND(geAllPathsQ.getParentGeoEntity().EQ(this.geoEntityQuery));
  }

  @Override
  protected void addLocationCondition(ValueQuery vQuery, GeneratedComponentQuery componentQuery, LocationCondition condition)
  {
    condition.restrictQuery(vQuery, this.getGeoEntityAttribute(componentQuery));
  }
}
