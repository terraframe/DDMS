package dss.vector.solutions;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.terraframe.mojo.dataaccess.transaction.Transaction;
import com.terraframe.mojo.session.StartSession;

import dss.vector.solutions.geo.generated.Earth;
import dss.vector.solutions.ontology.IsA;
import dss.vector.solutions.ontology.MO;
import dss.vector.solutions.ontology.OntologyDefinition;
import dss.vector.solutions.ontology.Term;

public class OntologyImporter {
	private static final String OBO_FIELD_DELIMITER = "\\s*:\\s*";
	private static final String OBO_SECTION_DELIMITER = "[";
	private static final String OBO_SECTION_FILE = "File";
	private static final String OBO_SECTION_TERM = "Term";
	private static final String OBO_VALUE_TRUE = "TRUE";
	private static final String OBO_FIELD_DEF = "def";
	private static final String OBO_FIELD_ID = "id";
	private static final String OBO_FIELD_NAME = "name";
	private static final String OBO_FIELD_IS_OBSOLETE = "is_obsolete";
	private static final String OBO_FIELD_NAMESPACE = "default-namespace";
	private static final String OBO_FIELD_COMMENT = "comment";
	private static final String OBO_FIELD_DESCRIPTION = "def";
	private static final String OBO_RELATIONSHIP_DELIMITER = "\\s*!\\s*";
	private static final String OBO_RELATIONSHIP_IS_A = "is_a";

	private String filename;
	private Set<String> allTermKeys = new HashSet<String>();
	private OntologyDefinition ontology; 
	private Map<String, List<String>> relationships = new HashMap<String, List<String>>();
	private Map<String, String> termIds = new HashMap<String, String>();

	/**
	 * @param args
	 */
	@StartSession
	public static void main(String[] args) {
		System.out.println("Start");
		String filename = "/home/chris/TerraFrame/MO/MO.obo";
		if (args.length > 0) {
			filename = args[0];
		}

		OntologyImporter oi = new OntologyImporter(filename);
		oi.importOntology();
		System.out.println("End");
	}

	private OntologyImporter() {
		super();
	}
	
	public OntologyImporter(String filename) {
		this();
		this.filename = filename;
	}


	@Transaction
	public void importOntology() {
		this.importOntology(filename);
	}

	@Transaction
	private void importOntology(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String section = OBO_SECTION_FILE;
			Map<String, List<String>> sectionData = new HashMap<String, List<String>>();
			while ((line = br.readLine()) != null) {
				if (line.startsWith(OBO_SECTION_DELIMITER)) {
					this.processSection(section, sectionData);
					section = line.substring(1,line.length()-1);
					sectionData = new HashMap<String, List<String>>();
				} else {
					String[] tokens = line.split(OBO_FIELD_DELIMITER,2);
					if (tokens.length == 2) {
						this.put(sectionData, tokens[0], tokens[1]);
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.processRelationships();
		System.out.println(allTermKeys);
	}

	private void processSection(String section, Map<String, List<String>> sectionData) {
		if (OBO_SECTION_TERM.equals(section)) {
			this.processTerm(sectionData);
		} else if (OBO_SECTION_FILE.equals(section)) {
			this.processFile(sectionData);
		}
	}
	
	@Transaction
	private void processFile(Map<String, List<String>> sectionData) {
		System.out.println("File");
		  
		String namespace = this.getString(sectionData,OBO_FIELD_NAMESPACE);
		ontology = new OntologyDefinition();
		ontology.setOntologyName("MO");
		ontology.setNamespace(namespace);
		ontology.apply();
	}


	private void processTerm(Map<String, List<String>> sectionData) {
		String obsolete = this.getString(sectionData,OBO_FIELD_IS_OBSOLETE);
		if (obsolete != null && OBO_VALUE_TRUE.equals(obsolete.toUpperCase())) {
			System.out.print("OBSOLETE ");
		}
		System.out.print(this.getString(sectionData,OBO_FIELD_NAME));
		System.out.print(" (" + this.getString(sectionData,OBO_FIELD_ID) + ")");
		
		if (this.getString(sectionData,"is_a") != null) {
			System.out.print (" extends " + this.getString(sectionData,"is_a"));
		}
		System.out.print(" = " + this.getString(sectionData,OBO_FIELD_DEF));
		System.out.println();
		
		this.allTermKeys.addAll(sectionData.keySet());
		
		if (obsolete == null || !OBO_VALUE_TRUE.equals(obsolete.toUpperCase())) {
			MO moTerm = new MO();
			moTerm.setOntology(ontology);
			moTerm.setTermId(this.getString(sectionData,OBO_FIELD_ID));
			moTerm.setTermName(this.getString(sectionData,OBO_FIELD_NAME));
			if (this.getString(sectionData, OBO_FIELD_COMMENT) != null) {
				moTerm.setTermComment(this.getString(sectionData,OBO_FIELD_COMMENT));
			}
			if (this.getString(sectionData, OBO_FIELD_DESCRIPTION) != null) {
				moTerm.setDescription(this.getString(sectionData,OBO_FIELD_DESCRIPTION));
			}
			moTerm.apply();
			termIds.put(moTerm.getTermId(), moTerm.getId());
			
			List<String> isA = this.get(sectionData, OBO_RELATIONSHIP_IS_A); 
			if (isA != null) {
				for (String parent: isA) {
					String parentId = parent;
					String[] tokens = parent.split(OBO_RELATIONSHIP_DELIMITER,2);
					if (tokens.length == 2) {
						parentId = tokens[0];
					}
					this.put(relationships, parentId, moTerm.getId());
				}
			}
		}
	}
	
	private void processRelationships() {
		for (String parent: relationships.keySet()) {
			List<String> children = relationships.get(parent);
			for (String child: children) {
				IsA relationship = new IsA(termIds.get(parent), child);
				relationship.apply();
			}
		}
	}
	
	private void put(Map<String, List<String>> multimap, String key, String value) {
		if (multimap.containsKey(key)) {
			multimap.get(key).add(value);
		} else {
			List<String> l = new ArrayList<String>();
			l.add(value);
			multimap.put(key, l);
		}
	}

	private List<String> get(Map<String, List<String>> multimap, String key) {
		return multimap.get(key);
		
	}
	
	private String getString(Map<String, List<String>> multimap, String key) {
		String s = null;
		List<String> l = this.get(multimap, key);
		if (l != null) {
			StringBuilder sb = new StringBuilder();
			for (String value: l) {
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append(value);
			}
			s = sb.toString();
		} 
		return s; 
	}
}
