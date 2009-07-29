package dss.vector.solutions;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Universal {
	public static Universal EARTH = new Universal("Earth");
	
	public static final int MULTIPOLYGON = 0;
	public static final int POINT = 1;
	public static final int MULTILINESTRING = 2;
	public static final int ERROR = -1;
	
	public static String getSystemName(String description) {
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
	        "<multipolygon name=\"multiPolygon\" label=\"Multi Polygon\" description=\"Multi Polygon\" removable=\"false\" required=\"false\" srid=\"4326\" dimension=\"2\" />",
	        "<point name=\"point\" label=\"Point\" description=\"Point\" removable=\"false\" required=\"false\" srid=\"4326\" dimension=\"2\" />",
	        "<multilinestring name=\"multiLineString\" label=\"Multi LineString\" description=\"Multi LineString\" removable=\"false\" required=\"false\" srid=\"4326\" dimension=\"2\" />"
//	        "<linestring name=\"lineString\" label=\"LineString\" description=\"LineString\" removable=\"false\" required=\"false\" srid=\"4326\" dimension=\"2\" />"
	};
	
	private String description;
	private String name;
	private Universal parent = null;
	private List<Universal> children = new ArrayList<Universal>();
	private Universal allowedIn = null;
	private boolean political = false;
	private boolean sprayTarget = false;
	private int geometry = ERROR;

	public Universal(String description) {
		super();
		this.description = description;
		this.name = getSystemName(description);
	}
	
	public Universal(String description, String geometryName, boolean political, boolean sprayTarget) {
		this(description);
		this.political = political;
		this.sprayTarget = sprayTarget;
		this.setGeometry(geometryName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		sb.append(this.getName());
		sb.append(" (");
		sb.append(this.getDescription());
		sb.append(")");
		sb.append("/");
		if (this.getParent() != null) {
			sb.append(this.getParent().getName());
		}
		sb.append("/");
		if (this.getAllowedIn() != null) {
			sb.append(this.getAllowedIn().getName());
		}
		return sb.toString();
	}
	
	public String getMdBusinessTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<mdBusiness\n");
		sb.append("   name=\"dss.vector.solutions.geo.generated." + this.getName() + "\"\n");   		 
		sb.append("   label=\"" + this.getDescription() + "\"\n");
		sb.append("   description=\"" + this.getDescription() + "\"\n");
		sb.append("   removable=\"true\"\n");
        sb.append("   extends=\"dss.vector.solutions.geo.generated." + (this.getParent()==null ? "GeoEntity" : this.getParent().getName()) + "\">\n");
        if (this.getGeometry() >= 0 && this.getGeometry() < geometryXML.length) {
        	sb.append("   <attributes>\n");
        	sb.append("      " + geometryXML[this.getGeometry()] + "\n");
        	sb.append("   </attributes>\n");
        }
		sb.append("</mdBusiness>\n");
		return sb.toString();
	}
	
	public String getReadPermissionsTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<mdBusinessPermission type=\"dss.vector.solutions.geo.generated." + this.getName() + "\">\n");
        sb.append("   <operation name=\"READ\" />\n");
		sb.append("   <operation name=\"READ_ALL\" />\n");
		sb.append("</mdBusinessPermission>\n");
		return sb.toString();
	}
	
	public String getUpdatePermissionsTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<mdBusinessPermission type=\"dss.vector.solutions.geo.generated." + this.getName() + "\">\n");
        sb.append("   <operation name=\"CREATE\" />\n");
		sb.append("   <operation name=\"WRITE\" />\n");
		sb.append("   <operation name=\"WRITE_ALL\" />\n");
		sb.append("   <operation name=\"DELETE\" />\n");
		sb.append("</mdBusinessPermission>\n");
		return sb.toString();
	}
	
	public String getRelationshipTag(String parentName) {
		StringBuffer sb = new StringBuffer();
		sb.append("<relationship\n");
		sb.append("   type=\"dss.vector.solutions.geo.AllowedIn\"\n");
		sb.append("   childId=\"" + this.name + "H\"\n");
		sb.append("   parentId=\"" + parentName + "H\"\n");
		sb.append("   id=\"" + this.name + "In" + parentName + "\">\n");
		sb.append("</relationship>\n");
		return sb.toString();
	}
	
	public String getMetadataTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("   <object\n");
    	sb.append("      key=\"dss.vector.solutions.geo.generated." + this.getName() + "\"\n");
    	sb.append("      id=\"" + this.getName() + "Md\"\n");
    	sb.append("      type=\"com.terraframe.mojo.system.metadata.MdBusiness\" />\n");
		return sb.toString();
	}	
	
	public String getGeoHierarchyTag() {
		StringBuffer sb = new StringBuffer();
		sb.append("<object\n");
		sb.append("   id=\"" + this.getName() + "H\"\n");
		sb.append("   type=\"dss.vector.solutions.geo.GeoHierarchy\"\n");
		sb.append("   key=\"GeoHierarchy_" + this.getName() + "\">\n");
		sb.append("   <valueAttribute\n");
		sb.append("      name=\"political\"\n");
		sb.append("      value=\"" + (this.isPolitical() ? "true" : "false") + "\" />\n");
		sb.append("   <valueAttribute\n");
		sb.append("      name=\"sprayTargetAllowed\"\n");
		sb.append("      value=\"" + (this.isSprayTarget() ? "true" : "false") + "\" />\n");
		sb.append("   <referenceAttribute\n");
		sb.append("      name=\"geoEntityClass\"\n");
		sb.append("      id=\"" + this.getName() + "Md\" />\n");
		sb.append("</object>\n");
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
