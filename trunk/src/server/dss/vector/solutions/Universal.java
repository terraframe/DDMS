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
package dss.vector.solutions;

import java.util.ArrayList;
import java.util.List;

import com.runwaysdk.constants.MdBusinessInfo;
import com.runwaysdk.dataaccess.io.dataDefinition.XMLTags;

import dss.vector.solutions.geo.GeoHierarchy;

public class Universal {
	public static Universal EARTH = new Universal("Earth");

	public static final int MULTIPOLYGON = 0;
	public static final int POINT = 1;
	public static final int MULTILINESTRING = 2;
	public static final int ERROR = -1;

	public static String getSystemType(String description) {
		return MDSSInfo.GENERATED_GEO_PACKAGE + "." + GeoHierarchy.getSystemName(description);
	}

	private static String geometryXML = "<" + XMLTags.MULTIPOLYGON_TAG + " " + XMLTags.NAME_ATTRIBUTE + "=\"multiPolygon\" " + XMLTags.DISPLAY_LABEL_ATTRIBUTE + "=\"Multi Polygon\" " + XMLTags.DESCRIPTION_ATTRIBUTE + "=\"Multi Polygon\" " + XMLTags.REMOVE_ATTRIBUTE + "=\"false\" " + XMLTags.REQUIRED_ATTRIBUTE + "=\"false\" " + XMLTags.SRID_ATTRIBUTE + "=\"4326\" " + XMLTags.DIMENSION_ATTRIBUTE + "=\"2\" />";

	private String description;
	private String type;
	private String typeName;
	private Universal allowedIn = null;
	private boolean political = false;
	private boolean sprayTarget = false;
	private boolean populationAllowed = false;
	private boolean urban = false;

	private int geometry = ERROR;
	private String moRoot;

	private Universal(String description) {
		super();
		this.description = description;
		this.type = getSystemType(description);
		this.typeName = GeoHierarchy.getSystemName(description);
	}

	public Universal(String description, boolean political, boolean sprayTarget, boolean urban, boolean populationAllowed, String moRoot) {
		this(description);
		this.political = political;
		this.sprayTarget = sprayTarget;
		this.populationAllowed = populationAllowed;
		this.urban = urban;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPolitical() {
		return political;
	}

	public void setPolitical(boolean political) {
		this.political = political;
	}

	public boolean isUrban() {
		return urban;
	}

	public void setUrban(boolean urban) {
		this.urban = urban;
	}

	public boolean isSprayTarget() {
		return sprayTarget;
	}

	public void setSprayTarget(boolean sprayTarget) {
		this.sprayTarget = sprayTarget;
	}

	public boolean isPopulationAllowed() {
		return populationAllowed;
	}

	public void setPopulationAllowed(boolean populationAllowed) {
		this.populationAllowed = populationAllowed;
	}

	public Universal getAllowedIn() {
		return this.allowedIn;
	}

	public void setAllowedIn(Universal allowedIn) {
		this.allowedIn = allowedIn;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getType());
		sb.append(" (");
		sb.append(this.getDescription());
		sb.append(")");
		sb.append("/");
		if (this.getAllowedIn() != null) {
			sb.append(this.getAllowedIn().getType());
		}
		return sb.toString();
	}

	public String getMdBusinessTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<" + XMLTags.MD_BUSINESS_TAG + "\n");
		sb.append("   " + XMLTags.NAME_ATTRIBUTE + "=\"" + this.getType() + "\"\n");
		sb.append("   " + XMLTags.DISPLAY_LABEL_ATTRIBUTE + "=\"" + this.getDescription() + "\"\n");
		sb.append("   " + XMLTags.DESCRIPTION_ATTRIBUTE + "=\"" + this.getDescription() + "\"\n");
		sb.append("   " + XMLTags.REMOVE_ATTRIBUTE + "=\"true\"\n");
		// TODO - Remove the next line once GeoEntities have no is_a
		sb.append("   " + XMLTags.EXTENDS_ATTRIBUTE + "=\"" + "dss.vector.solutions.geo.generated.GeoEntity" + "\">\n");
		// TODO - Remove the next three lines once the geotype and associated
		// attribute are removed!
		// sb.append("   <"+XMLTags.ATTRIBUTES_TAG+">\n");
		// sb.append("      " + geometryXML + "\n");
		// sb.append("   </"+XMLTags.ATTRIBUTES_TAG+">\n");
		sb.append("</" + XMLTags.MD_BUSINESS_TAG + ">\n");
		return sb.toString();
	}

	public String getReadPermissionsTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<" + XMLTags.MD_BUSINESS_PERMISSION_TAG + " " + XMLTags.TYPE_ATTRIBUTE + "=\"" + this.getType() + "\">\n");
		sb.append("   <" + XMLTags.OPERATION_TAG + " " + XMLTags.NAME_ATTRIBUTE + "=\"" + XMLTags.READ + "\" />\n");
		sb.append("   <" + XMLTags.OPERATION_TAG + " " + XMLTags.NAME_ATTRIBUTE + "=\"" + XMLTags.READ_ALL_ATTRIBUTES + "\" />\n");
		sb.append("</" + XMLTags.MD_BUSINESS_PERMISSION_TAG + ">\n");
		return sb.toString();
	}

	public String getUpdatePermissionsTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<" + XMLTags.MD_BUSINESS_PERMISSION_TAG + " type=\"" + this.getType() + "\">\n");
		sb.append("   <" + XMLTags.OPERATION_TAG + " " + XMLTags.NAME_ATTRIBUTE + "=\"" + XMLTags.CREATE + "\" />\n");
		sb.append("   <" + XMLTags.OPERATION_TAG + " " + XMLTags.NAME_ATTRIBUTE + "=\"" + XMLTags.WRITE + "\" />\n");
		sb.append("   <" + XMLTags.OPERATION_TAG + " " + XMLTags.NAME_ATTRIBUTE + "=\"" + XMLTags.WRITE_ALL_ATTRIBUTES + "\" />\n");
		sb.append("   <" + XMLTags.OPERATION_TAG + " " + XMLTags.NAME_ATTRIBUTE + "=\"" + XMLTags.DELETE + "\" />\n");
		sb.append("</" + XMLTags.MD_BUSINESS_PERMISSION_TAG + ">\n");
		return sb.toString();
	}

	public String getRelationshipTag(String parentType, String parentTypeName) {
		StringBuffer sb = new StringBuffer();
		sb.append("<" + XMLTags.RELATIONSHIP_TAG + "\n");
		sb.append("   " + XMLTags.TYPE_ATTRIBUTE + "=\"dss.vector.solutions.geo.AllowedIn\"\n");
		sb.append("   " + XMLTags.KEY_ATTRIBUTE + "=\"" + this.getTypeName() + "In" + parentTypeName + "\"\n");
		sb.append("   " + XMLTags.CHILD_KEY_TAG + "=\"" + this.getType() + "\"\n");
		sb.append("   " + XMLTags.PARENT_KEY_TAG + "=\"" + parentType + "\">\n");
		sb.append("</" + XMLTags.RELATIONSHIP_TAG + ">\n");
		return sb.toString();
	}

	public String getMetadataTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("   <object\n");
		sb.append("      " + XMLTags.KEY_ATTRIBUTE + "=\"" + this.getType() + "\"\n");
		sb.append("      " + XMLTags.TYPE_ATTRIBUTE + "=\"" + MdBusinessInfo.CLASS + "\" />\n");
		return sb.toString();
	}

	public String getGeoHierarchyTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<" + XMLTags.OBJECT_TAG + "\n");
		sb.append("   " + XMLTags.TYPE_ATTRIBUTE + "=\"dss.vector.solutions.geo.GeoHierarchy\"\n");
		sb.append("   " + XMLTags.KEY_ATTRIBUTE + "=\"" + this.getType() + "\">\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"political\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"" + (this.isPolitical() ? "true" : "false") + "\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"sprayTargetAllowed\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"" + (this.isSprayTarget() ? "true" : "false") + "\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"urban\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"" + (this.isUrban() ? "true" : "false") + "\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"populationAllowed\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"" + (this.isPopulationAllowed() ? "true" : "false") + "\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_REFERENCE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"geoEntityClass\"\n");
		sb.append("      " + XMLTags.KEY_ATTRIBUTE + "=\"" + this.getType() + "\"/>\n");
		sb.append("</" + XMLTags.OBJECT_TAG + ">\n");
		return sb.toString();
	}

	public String getCountryGeoIdTag(String geoid) {
		StringBuffer sb = new StringBuffer();
		sb.append("<" + XMLTags.OBJECT_TAG + "\n");
		sb.append("   " + XMLTags.TYPE_ATTRIBUTE + "=\"dss.vector.solutions.Property\"\n");
		sb.append("   " + XMLTags.KEY_ATTRIBUTE + "=\"countryGeoId\">\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"propertyName\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"countryGeoId\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"displayLabel\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"Country Geo ID\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"propertyPackage\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"" + PropertyInfo.INSTALL_PACKAGE + "\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"propertyValue\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"" + geoid + "\"/>\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"propertyType\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"STRING\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"propertyValidator\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"^[0-9A-Za-z]{1,16}$\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"validValues\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"12323,ABCD,23AB,ZZZ456\" />\n");
		sb.append("   <" + XMLTags.ATTRIBUTE_TAG + "\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_NAME_ATTRIBUTE + "=\"description\"\n");
		sb.append("      " + XMLTags.ENTITY_ATTRIBUTE_VALUE_ATTRIBUTE + "=\"The GeoID of the Country in which this server is installed\"/>\n");
		sb.append("</" + XMLTags.OBJECT_TAG + ">\n");
		return sb.toString();
	}

	public List<Universal> getAllowedInAncestors() {
		ArrayList<Universal> ancestors = new ArrayList<Universal>();
		Universal currentAncestor = this.getAllowedIn();
		while (currentAncestor != null) {
			ancestors.add(currentAncestor);
			currentAncestor = currentAncestor.getAllowedIn();
		}
		return ancestors;
	}
}
