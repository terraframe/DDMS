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
package dss.vector.solutions.query;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.runwaysdk.constants.ClientRequestIF;
import com.runwaysdk.generation.loader.Reloadable;
import com.runwaysdk.system.metadata.MdAttributeConcreteDTO;
import com.runwaysdk.system.metadata.MdAttributeDTO;
import com.runwaysdk.system.metadata.MdBusinessDTO;
import com.runwaysdk.system.metadata.MdClassDTO;
import com.runwaysdk.system.metadata.MdRelationshipDTO;
import com.runwaysdk.system.metadata.MdWebAttributeDTO;
import com.runwaysdk.system.metadata.MdWebDateDTO;
import com.runwaysdk.system.metadata.MdWebFieldDTO;
import com.runwaysdk.system.metadata.MdWebFormDTO;
import com.runwaysdk.system.metadata.MdWebGeoDTO;
import com.runwaysdk.system.metadata.MdWebMultipleTermDTO;
import com.runwaysdk.system.metadata.MdWebPrimitiveDTO;
import com.runwaysdk.system.metadata.MdWebSingleTermGridDTO;
import com.runwaysdk.system.metadata.MdWebTextDTO;

import dss.vector.solutions.entomology.MosquitoCollection;
import dss.vector.solutions.entomology.MosquitoCollectionDTO;
import dss.vector.solutions.general.EpiDateDTO;
import dss.vector.solutions.generator.MdFormUtilDTO;
import dss.vector.solutions.geo.GeoFieldDTO;
import dss.vector.solutions.geo.generated.CollectionSiteDTO;
import dss.vector.solutions.geo.generated.SentinelSiteDTO;
import dss.vector.solutions.ontology.NestedTermsWarningDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.util.Halp;
import dss.vector.solutions.util.LocalizationFacade;
import dss.vector.solutions.util.QueryUtil;

public class FormQueryBuilder implements Reloadable
{
  private ClientRequestIF       request;

  private List<SelectableGroup> groups;

  private SerializableGroup     dateGroup;

  private SerializableGroup     geoGroup;

  private CompositeGeoField     geoField;

  private List<String>          typesToLoad;

  private String                mainType;

  private JSONObject            queryList;

  private String                queryClass;

  public FormQueryBuilder(ClientRequestIF request, String queryClass)
  {
    this.request = request;
    this.queryClass = queryClass;
    this.groups = new LinkedList<SelectableGroup>();
    this.dateGroup = new SerializableGroup();
    this.geoGroup = new SerializableGroup();
    this.geoField = new CompositeGeoField();

    this.typesToLoad = new LinkedList<String>();
    this.typesToLoad.addAll(Arrays.asList(new String[] { NestedTermsWarningDTO.CLASS, EpiDateDTO.CLASS, SavedSearchDTO.CLASS, SavedSearchViewDTO.CLASS, QueryController.CLASS, QueryBuilderDTO.CLASS }));
  }

  public void setQuerySpecifics(String type, QueryConstants.QueryType queryType) throws JSONException
  {
    this.loadQuerySpecifics(QueryConstants.namespaceQuery(type, queryType));
  }

  public void setQuerySpecifics(String type, String typeName) throws JSONException
  {
    this.loadQuerySpecifics(QueryConstants.namespaceQuery(type, typeName));
  }

  private void loadQuerySpecifics(String namespacedType) throws JSONException
  {
    SavedSearchViewQueryDTO query = SavedSearchDTO.getSearchesForType(request, namespacedType);
    JSONArray queries = new JSONArray();
    for (SavedSearchViewDTO view : query.getResultSet())
    {
      JSONObject idAndName = new JSONObject();
      idAndName.put("id", view.getSavedQueryId());
      idAndName.put("name", view.getQueryName());

      queries.put(idAndName);
    }

    JSONObject queryList = new JSONObject();
    queryList.put("queries", queries);
    queryList.put("namespacedType", namespacedType);

    this.queryList = queryList;
  }
  
  public void addForm(MdWebFormDTO form, String groupName)
  {
    MdClassDTO formMdClass = form.getFormMdClass();
    String classType = formMdClass.getPackageName() + "." + formMdClass.getTypeName();

    if (this.mainType == null)
    {
      this.mainType = classType;
    }

    this.typesToLoad.add(classType);

    MdWebFieldDTO[] fields = MdFormUtilDTO.getAllFields(request, form);

    Set<String> readableAttributeNames = Halp.getReadableAttributeNames(classType, request);
    Map<MdWebFieldDTO, MdAttributeConcreteDTO> readableFieldMap = this.getReadableFieldMap(fields, readableAttributeNames);
    List<MdWebFieldDTO> readableFields = new LinkedList<MdWebFieldDTO>(readableFieldMap.keySet());

//    Collections.sort(readableFields, new FieldComparator());

    SelectableGroup group = new SelectableGroup();
    group.setClassType(classType);
    group.setLabel(form.getDisplayLabel().getValue());
    group.setGroup(groupName);

    this.groups.add(group);
    
    // If the form has an attribute named 'collectionId' then we join on MosquitoCollection
    boolean hasCollectionId = false;

    for (MdWebFieldDTO field : readableFields)
    {
      String fieldName = field.getFieldName();
      String fieldLabel = field.getDisplayLabel().getValue();
      
      if (fieldName.equals("collectionId"))
      {
        hasCollectionId = true;
      }

      MdAttributeConcreteDTO mdAttribute = readableFieldMap.get(field);
      String attributeName = mdAttribute.getAttributeName();

      if (field instanceof MdWebMultipleTermDTO)
      {
        TermDTO[] terms = TermDTO.getAllTermsForField(request, classType, attributeName);
        MdRelationshipDTO mdRelationship = MdFormUtilDTO.getMdRelationship(request, field);

        String relType = mdRelationship.getPackageName() + "." + mdRelationship.getTypeName();
        this.typesToLoad.add(relType);

        TermOptionFactory factory = new TermOptionFactory(QueryUtil.DUMMY_RELATIONSHIP_VALUE_ONE, relType);
        factory.setAttributeNamePrepend(attributeName);
        factory.setLabel(fieldLabel + " - ");

        SelectableGroup selectableGroup = new SelectableGroup();
        selectableGroup.setLabel(fieldLabel);
        selectableGroup.setClassType(relType);

        for (TermDTO term : terms)
        {
          selectableGroup.addOption(factory.createOption(term));
        }

        this.groups.add(selectableGroup);
      }
      else if (field instanceof MdWebSingleTermGridDTO)
      {
        TermDTO[] terms = TermDTO.getAllTermsForField(request, classType, attributeName);

        MdRelationshipDTO mdRelationship = MdFormUtilDTO.getMdRelationship(request, field);

        String relType = mdRelationship.getPackageName() + "." + mdRelationship.getTypeName();
        this.typesToLoad.add(relType);

        MdWebPrimitiveDTO[] compositeFields = MdFormUtilDTO.getCompositeFields(request, field.getId());

        for (MdWebPrimitiveDTO compositeField : compositeFields)
        {
          String compositeAttributeName = compositeField.getFieldName();
          String attributeLabel = compositeField.getDisplayLabel().getValue();

          TermOptionFactory factory = new TermOptionFactory(compositeField, compositeAttributeName, relType);
          
          // due to size restrictions only grab the first few letters of each part
          String prependField = fieldName.length() > 6 ? fieldName.substring(0, 6) : fieldName;
          String prependComposite = compositeAttributeName.length() > 6 ? compositeAttributeName.substring(0, 6) : compositeAttributeName;
          
          factory.setAttributeNamePrepend(prependField + "_" + prependComposite);
          factory.setLabel(attributeLabel + " - ");

          SelectableGroup selectableGroup = new SelectableGroup();
          selectableGroup.setLabel(fieldLabel + " - " + attributeLabel);
          selectableGroup.setClassType(relType);

          for (TermDTO term : terms)
          {
            selectableGroup.addOption(factory.createOption(term));
          }

          this.groups.add(selectableGroup);
        }
      }
      else if (! ( field instanceof MdWebTextDTO ))
      {
        if (field instanceof MdWebGeoDTO)
        {
          this.geoField.addField(GeoFieldDTO.getGeoField(request, classType, attributeName));

          this.geoGroup.addOption(new GeoOption(classType, attributeName, fieldLabel));
        }
        else if (field instanceof MdWebDateDTO)
        {
          this.dateGroup.addOption(new DateOption(classType, attributeName));
        }

        new SelectableOptionFactory(group, form.getFormName()).create(mdAttribute);
      }
    }
    
    // NOTE : I admit this code here was a mistake, however I'm going to leave it this way because changing it is too much work (for very little benefit). If this needs to be done
    //        again for a different form, the solution should have been:
    //   1. Create another table which defines additional joins. The table would map this MdWebForm to an additional MdWebForm (which just contains references to MosquitoCollection).
    //   2. In QueryController.queryType the logic is simple: check if there are additional joins in that table, then invoke 'addForm' for each additional web form.
    //   3. When cloning, if the form being cloned contains entries in this 'additional joins' table, then create entries for those additional joins for the clone as well.
    if (hasCollectionId)
    {
      addType((MdBusinessDTO) MdFormUtilDTO.getMdBusinessByType(request, MosquitoCollectionDTO.CLASS), groupName, 0);
      this.dateGroup.addOption(new DateOption(MosquitoCollectionDTO.CLASS, MosquitoCollectionDTO.COLLECTIONDATE));
      
      this.geoGroup.addOption(new GeoOption(MosquitoCollectionDTO.CLASS, MosquitoCollectionDTO.GEOENTITY, LocalizationFacade.getFromBundles("Geo_Entity")));
      
      GeoFieldDTO hackityHackHack = new GeoFieldDTO(this.request);
      hackityHackHack.setIsPoliticalHierarchy(true);
      hackityHackHack.setIsPopulationHierarchy(false);
      hackityHackHack.setIsSprayHierarchy(false);
      hackityHackHack.setIsUnderSystemRoot(false);
      hackityHackHack.setIsUrbanHierarchy(false);
      hackityHackHack.addExtraUniversal(CollectionSiteDTO.CLASS);
      hackityHackHack.addExtraUniversal(SentinelSiteDTO.CLASS);
      this.geoField.addField(hackityHackHack);
    }
  }

  public void addType(MdBusinessDTO type, String groupName, Integer insertIndex)
  {
    String classType = type.getPackageName() + "." + type.getTypeName();

    if (this.mainType == null)
    {
      this.mainType = classType;
    }

    this.typesToLoad.add(classType);

    List<? extends MdAttributeConcreteDTO> attrs = this.filterReadable(type.getAllAttribute(), classType);

    SelectableGroup group = new SelectableGroup();
    group.setClassType(classType);
    group.setLabel(type.getDisplayLabel().getValue());
    group.setGroup(groupName);

    if (insertIndex == null)
    {
      this.groups.add(group);
    }
    else
    {
      this.groups.add(insertIndex, group);
    }

    for (MdAttributeConcreteDTO attr : attrs)
    {
      if (!attr.getAttributeName().equals(MosquitoCollection.DISEASE) && !attr.getAttributeName().equals(MosquitoCollection.KEYNAME))
      {
        new SelectableOptionFactory(group, groupName).create(attr);
      }
    }
  }
  
  private Map<MdWebFieldDTO, MdAttributeConcreteDTO> getReadableFieldMap(MdWebFieldDTO[] fields, Set<String> readableAttributeNames)
  {
    // use a linked hash map to ensure order is preserved. This is a little hacky. (maybe rework the data structures?)
    Map<MdWebFieldDTO, MdAttributeConcreteDTO> map = new LinkedHashMap<MdWebFieldDTO, MdAttributeConcreteDTO>();

    for (MdWebFieldDTO field : fields)
    {
      if (field instanceof MdWebAttributeDTO)
      {
        MdAttributeDTO mdAttribute = ( (MdWebAttributeDTO) field ).getDefiningMdAttribute();

        if (mdAttribute instanceof MdAttributeConcreteDTO)
        {
          MdAttributeConcreteDTO mdAttributeConcrete = (MdAttributeConcreteDTO) mdAttribute;

          if (readableAttributeNames.contains(mdAttributeConcrete.getAttributeName()))
          {
            map.put(field, mdAttributeConcrete);
          }
        }
      }
    }

    return map;
  }
  
  private List<MdAttributeConcreteDTO> filterReadable(List<? extends MdAttributeDTO> attrs, String classType)
  {
    Set<String> readableAttributeNames = Halp.getReadableAttributeNames(classType, request);
    
    // use a linked hash map to ensure order is preserved. This is a little hacky. (maybe rework the data structures?)
    List<MdAttributeConcreteDTO> list = new LinkedList<MdAttributeConcreteDTO>();

    for (MdAttributeDTO attr : attrs)
    {
      if (attr instanceof MdAttributeConcreteDTO)
      {
        MdAttributeConcreteDTO mdAttributeConcrete = (MdAttributeConcreteDTO) attr;

        if (readableAttributeNames.contains(mdAttributeConcrete.getAttributeName()))
        {
          list.add(mdAttributeConcrete);
        }
      }
    }

    return list;
  }

  public void populateRequest(HttpServletRequest req)
  {
    req.setAttribute("typesToLoad", typesToLoad);
    req.setAttribute("dateAttributes", this.dateGroup.serialize());
    req.setAttribute("geoAttributes", this.geoGroup.serialize());
    req.setAttribute("geoField", geoField);
    req.setAttribute("groups", groups);
    req.setAttribute("type", mainType);
    req.setAttribute("queryList", queryList.toString());
    req.setAttribute("queryClass", queryClass);
  }
}
