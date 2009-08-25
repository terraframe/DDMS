package dss.vector.solutions;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import com.terraframe.mojo.constants.MdBusinessInfo;
import com.terraframe.mojo.dataaccess.io.dataDefinition.XMLTags;

public class Universal {
	public static Universal EARTH = new Universal("Earth");

	public static final int MULTIPOLYGON = 0;
	public static final int POINT = 1;
	public static final int MULTILINESTRING = 2;
	public static final int ERROR = -1;

	public static String getSystemName(String description)
	{
		String systemName = description;
		String name = description
			.replace("/", " Or ")
			.replace("&", " And ");
		String[] parts = name.split("[^a-zA-Z0-9]");
		StringBuffer sb = new StringBuffer();
		if (parts.length==1 && description.equals(description.toUpperCase())) {
			// It's an acronym, so use it as is.
			systemName = description;
		} else {
			// Create a camelcase representation of the description
			for (String part: parts) {
				String arabicPart = convertRomanToArabic(part);
				if (arabicPart.equals(part)) {
					sb.append(part.substring(0,1).toUpperCase());
					sb.append(part.substring(1).toLowerCase());
				} else {
					sb.append(arabicPart);
				}
			}
			systemName = sb.toString();
		}
		return systemName;
	}

    public static String getSystemType(String description)
    {
      return "dss.vector.solutions.geo.generated."+getSystemName(description);
	}

	private static String convertRomanToArabic(String part) {
		if (part.endsWith("IV")) {
			return part.substring(0,part.length()-2) + "4";
		}
		if (part.endsWith("V")) {
			return part.substring(0,part.length()-1) + "5";
		}
		if (part.endsWith("III")) {
			return part.substring(0,part.length()-3) + "3";
		}
		if (part.endsWith("II")) {
			return part.substring(0,part.length()-2) + "2";
		}
		if (part.endsWith("I")) {
			return part.substring(0,part.length()-1) + "1";
		}
		return part;
	}

	private final String[] geometryXML = {
	        "<"+XMLTags.MULTIPOLYGON_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"multiPolygon\" "+XMLTags.DISPLAY_LABEL_ATTRIBUTE+"=\"Multi Polygon\" "+XMLTags.DESCRIPTION_TAG+"=\"Multi Polygon\" "+XMLTags.REMOVE_TAG+"=\"false\" "+XMLTags.REQUIRED_TAG+"=\"false\" "+XMLTags.SRID_ATTRIBUTE+"=\"4326\" "+XMLTags.DIMENSION_ATTRIBUTE+"=\"2\" />",
	        "<"+XMLTags.POINT_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"point\" "+XMLTags.DISPLAY_LABEL_ATTRIBUTE+"=\"Point\" "+XMLTags.DESCRIPTION_TAG+"=\"Point\" "+XMLTags.REMOVE_TAG+"=\"false\" "+XMLTags.REQUIRED_TAG+"=\"false\" "+XMLTags.SRID_ATTRIBUTE+"=\"4326\" "+XMLTags.DIMENSION_ATTRIBUTE+"=\"2\" />",
	        "<"+XMLTags.MULTILINESTRING_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"multiLineString\" "+XMLTags.DISPLAY_LABEL_ATTRIBUTE+"=\"Multi LineString\" "+XMLTags.DESCRIPTION_TAG+"=\"Multi LineString\" "+XMLTags.REMOVE_TAG+"=\"false\" "+XMLTags.REQUIRED_TAG+"=\"false\" "+XMLTags.SRID_ATTRIBUTE+"=\"4326\" "+XMLTags.DIMENSION_ATTRIBUTE+"=\"2\" />"
//	        "<"+XMLTags.LINESTRING_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"lineString\" "+XMLTags.DISPLAY_LABEL_ATTRIBUTE+"=\"LineString\" "+XMLTags.DESCRIPTION_TAG+"=\"LineString\" "+XMLTags.REMOVE_TAG+"=\"false\" "+XMLTags.REQUIRED_TAG+"=\"false\" "+XMLTags.SRID_ATTRIBUTE+"=\"4326\" "+XMLTags.DIMENSION_ATTRIBUTE+"=\"2\" />"
	};

	private String description;
	private String type;
    private String typeName;
	private Universal parent = null;
	private List<Universal> children = new ArrayList<Universal>();
	private Universal allowedIn = null;
	private boolean political = false;
	private boolean sprayTarget = false;
	private int geometry = ERROR;

	public Universal(String description) {
		super();
		this.description = description;
		this.type = getSystemType(description);
		this.typeName = getSystemName(description);
	}

	public Universal(String description, String geometryName, boolean political, boolean sprayTarget) {
		this(description);
		this.political = political;
		this.sprayTarget = sprayTarget;
		this.setGeometry(geometryName);
	}

    public String getTypeName()
    {
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

	public boolean isSprayTarget() {
		return sprayTarget;
	}

	public void setSprayTarget(boolean sprayTarget) {
		this.sprayTarget = sprayTarget;
	}

	public int getGeometry() {
		int geometry = this.geometry;
		Universal currentAncestor = this.getParent();
		while (currentAncestor != null) {
			// If any ancestor returns a geometry, we cannot
			if (currentAncestor.geometry != ERROR) {
				return ERROR;
			}
			currentAncestor = currentAncestor.getParent();
		}

		return geometry;
	}

	public void setGeometry(int geometry) {
		if (this.parent != null) {
			throw new InvalidParameterException();
		}
		this.geometry = geometry;
	}

	public void setGeometry(String geometryName) {
		int geometry = ERROR;
		String upperName = geometryName.trim().toUpperCase();
		if (upperName.equals("POINT")) {
			geometry = POINT;
		} else if (upperName.equals("MULTIPOLYGON")) {
			geometry = MULTIPOLYGON;
		} else if (upperName.equals("MULTILINESTRING")) {
			geometry = MULTILINESTRING;
		}
		this.setGeometry(geometry);
	}

	public Universal getParent() {
		return parent;
	}

	public void setParent(Universal parent) {
		if (this.geometry != ERROR) {
			throw new InvalidParameterException();
		}
		// Remove me from my current parent's children
		if (this.parent != null) {
			this.parent.getChildren().remove(this);
		}

		if (parent != null) {
			this.parent = parent;

			// Add me to my new parent's children
			parent.getChildren().add(this);
		}
	}

	public List<Universal> getChildren() {
		return children;
	}

	private void setChildren(List<Universal> children) {
		this.children = children;
	}

	public Universal getAllowedIn() {
		// My allowedIn is always my ultimate ia_a parent's allowedIn
		// (regardless of whether I have an allowedIn myself)
		Universal currentAncestor = this;
		while (currentAncestor.getParent() != null) {
			currentAncestor = currentAncestor.getParent();
		}
		return currentAncestor.allowedIn;
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
		if (this.getParent() != null) {
			sb.append(this.getParent().getType());
		}
		sb.append("/");
		if (this.getAllowedIn() != null) {
			sb.append(this.getAllowedIn().getType());
		}
		return sb.toString();
	}

	public String getMdBusinessTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<"+XMLTags.MD_BUSINESS_TAG+"\n");
		sb.append("   "+XMLTags.NAME_TAG+"=\""+ this.getType() + "\"\n");
		sb.append("   "+XMLTags.DISPLAY_LABEL_ATTRIBUTE+"=\"" + this.getDescription() + "\"\n");
		sb.append("   "+XMLTags.DESCRIPTION_TAG+"=\"" + this.getDescription() + "\"\n");
		sb.append("   "+XMLTags.REMOVE_TAG+"=\"true\"\n");
        sb.append("   "+XMLTags.EXTENDS_TAG+"=\"" + (this.getParent()==null ? "dss.vector.solutions.geo.generated.GeoEntity" : this.getParent().getType()) + "\">\n");
        if (this.getGeometry() >= 0 && this.getGeometry() < geometryXML.length) {
        	sb.append("   <"+XMLTags.ATTRIBUTES_TAG+">\n");
        	sb.append("      " + geometryXML[this.getGeometry()] + "\n");
        	sb.append("   </"+XMLTags.ATTRIBUTES_TAG+">\n");
        }
		sb.append("</"+XMLTags.MD_BUSINESS_TAG+">\n");
		return sb.toString();
	}

	public String getReadPermissionsTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<"+XMLTags.MD_BUSINESS_PERMISSION_TAG+" "+XMLTags.TYPE_ATTRIBUTE+"=\"" + this.getType() + "\">\n");
        sb.append("   <"+XMLTags.OPERATION_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"READ\" />\n");
		sb.append("   <"+XMLTags.OPERATION_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\""+XMLTags.READ_ALL+"\" />\n");
		sb.append("</"+XMLTags.MD_BUSINESS_PERMISSION_TAG+">\n");
		return sb.toString();
	}

	public String getUpdatePermissionsTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<"+XMLTags.MD_BUSINESS_PERMISSION_TAG+" type=\"" + this.getType() + "\">\n");
        sb.append("   <"+XMLTags.OPERATION_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"CREATE\" />\n");
		sb.append("   <"+XMLTags.OPERATION_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"WRITE\" />\n");
		sb.append("   <"+XMLTags.OPERATION_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\""+XMLTags.WRITE_ALL+"\" />\n");
		sb.append("   <"+XMLTags.OPERATION_TAG+" "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"DELETE\" />\n");
		sb.append("</"+XMLTags.MD_BUSINESS_PERMISSION_TAG+">\n");
		return sb.toString();
	}

	public String getRelationshipTag(String parentType, String parentTypeName) {
		StringBuffer sb = new StringBuffer();
		sb.append("<"+XMLTags.RELATIONSHIP_TAG+"\n");
		sb.append("   "+XMLTags.TYPE_ATTRIBUTE+"=\"dss.vector.solutions.geo.AllowedIn\"\n");
        sb.append("   "+XMLTags.KEY_ATTRIBUTE+"=\"" + this.getTypeName() + "In" +parentTypeName+ "\"\n");
		sb.append("   "+XMLTags.CHILD_KEY_TAG+"=\"" + this.getType() + "\"\n");
		sb.append("   "+XMLTags.PARENT_KEY_TAG+"=\"" + parentType + "\">\n");
		sb.append("</"+XMLTags.RELATIONSHIP_TAG+">\n");
		return sb.toString();
	}

	public String getMetadataTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("   <object\n");
    	sb.append("      "+XMLTags.KEY_ATTRIBUTE+"=\"" + this.getType() + "\"\n");
//    	sb.append("      id=\"" + this.getName() + "Md\"\n");
    	sb.append("      "+XMLTags.TYPE_ATTRIBUTE+"=\""+MdBusinessInfo.CLASS+"\" />\n");
		return sb.toString();
	}

	public String getGeoHierarchyTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<"+XMLTags.OBJECT_TAG+"\n");
//		sb.append("   id=\"" + this.getName() + "H\"\n");
		sb.append("   "+XMLTags.TYPE_ATTRIBUTE+"=\"dss.vector.solutions.geo.GeoHierarchy\"\n");
		sb.append("   "+XMLTags.KEY_ATTRIBUTE+"=\"" + this.getType() + "\">\n");
		sb.append("   <"+XMLTags.VALUE_ATTRIBUTE_TAG+"\n");
		sb.append("      "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"political\"\n");
		sb.append("      "+XMLTags.VALUE_ATTRIBUTE+"=\"" + (this.isPolitical() ? "true" : "false") + "\" />\n");
		sb.append("   <"+XMLTags.VALUE_ATTRIBUTE_TAG+"\n");
		sb.append("      "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"sprayTargetAllowed\"\n");
		sb.append("      "+XMLTags.VALUE_ATTRIBUTE+"=\"" + (this.isSprayTarget() ? "true" : "false") + "\" />\n");
		sb.append("   <"+XMLTags.REFERENCE_ATTRIBUTE_TAG+"\n");
		sb.append("      "+XMLTags.ATTRIBUTE_ATTRIBUTE+"=\"geoEntityClass\"\n");
        sb.append("      "+XMLTags.KEY_ATTRIBUTE+"=\"" + this.getType() + "\"/>\n");
		sb.append("</"+XMLTags.OBJECT_TAG+">\n");
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

	public List<Universal> getIsADescendants() {
		ArrayList<Universal> descendants = new ArrayList<Universal>();
		for (Universal currentChild: children) {
			descendants.add(currentChild);
			descendants.addAll(currentChild.getIsADescendants());
		}
		return descendants;
	}

}
