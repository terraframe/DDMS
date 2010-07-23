package dss.vector.solutions.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.runwaysdk.dataaccess.DuplicateGraphPathException;
import com.runwaysdk.dataaccess.cache.DataNotFoundException;
import com.runwaysdk.dataaccess.database.Database;
import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.StartSession;
import com.runwaysdk.system.metadata.MdClass;

import dss.vector.solutions.ontology.AllPaths;
import dss.vector.solutions.ontology.AllPathsQuery;
import dss.vector.solutions.ontology.InvalidOBOFormatException;
import dss.vector.solutions.ontology.MO;
import dss.vector.solutions.ontology.OBO;
import dss.vector.solutions.ontology.Ontology;
import dss.vector.solutions.ontology.OntologyQuery;
import dss.vector.solutions.ontology.OntologyRelationship;
import dss.vector.solutions.ontology.OntologyRelationshipQuery;
import dss.vector.solutions.ontology.RootTerm;
import dss.vector.solutions.ontology.Term;
import dss.vector.solutions.ontology.TermQuery;
import dss.vector.solutions.ontology.TermRelationship;
import dss.vector.solutions.ontology.TermRelationshipQuery;

public class OntologyImporter
{
  public static final int feedbackMod = 50;

  private boolean displayStatusToSysOut = false;

  private static final String OBO_ONTOLOGY_DEFAULT_NAMESPACE         = "default-namespace";
  private static final String OBO_SECTION_DELIMITER                  = "[";
  private static final String OBO_FIELD_DELIMITER                    = ":";
  private static final String OBO_TYPEDEF_DELIMITER                  = "[Typedef]";
  private static final String OBO_TERM_DELIMITER                     = "[Term]";

  private static final String OBO_FIELD_ID                           = "id";
  private static final String OBO_FIELD_NAME                         = "name";
  private static final String OBO_FIELD_NAMESPACE                    = "namespace";
  private static final String OBO_FIELD_DEF                          = "def";
  private static final String OBO_FIELD_COMMENT                      = "comment";

  // Typedef attributes
  private static final String OBO_FIELD_ALT_ID                       = "alt_id";
  private static final String OBO_FIELD_INVERSE_OF                   = "inverse_of";
  private static final String OBO_FIELD_INVERSE_OF_ON_INSTANCE_LEVEL = "inverse_of_on_instance_level";
  private static final String OBO_FIELD_BUILTIN                      = "builtin";
  private static final String OBO_FIELD_IS_REFLEXIVE                 = "is_reflexive";
  private static final String OBO_FIELD_IS_TRANSITIVE                = "is_transitive";
  private static final String OBO_FIELD_IS_OBSOLETE                  = "is_obsolete";
  private static final String OBO_FIELD_IS_ANTI_SYMMETRIC            = "is_anti_symmetric";
  
  private String ontologyTitle;
  private String fileName;
  private Ontology ontology = null;
  private Map<String, OntologyRelationshipInfo> ontologyRelationshipInfoMap;
  private Map<String, OntologyRelationship> ontologyRelationshipByNameMap;
  private List<TermRelationshipInfo> termRelationshipInfoList;

  private class OntologyRelationshipInfo
  {
    private OntologyRelationship ontologyRelationship;
    private String inverseOfId;
    private String inverseOfOnInstanceLevelId;

    private OntologyRelationshipInfo(OntologyRelationship ontologyRelationship,
        String inverseOfId, String inverseOfOnInstanceLevelId)
    {
      this.ontologyRelationship = ontologyRelationship;
      this.inverseOfId = inverseOfId;
      this.inverseOfOnInstanceLevelId = inverseOfOnInstanceLevelId;
    }

    private OntologyRelationship getOntologyRelationship()
    {
      return this.ontologyRelationship;
    }

    private String getInverseOfId()
    {
      return this.inverseOfId;
    }

    private String getInverseOfOnInstanceLevelId()
    {
      return this.inverseOfOnInstanceLevelId;
    }
  }

  private class TermRelationshipInfo
  {
    private String parentTermId;
    private String childTermId;
    private String relationshipName;

    private TermRelationshipInfo(String parentTermId, String childTermId, String relationshipName)
    {
      this.parentTermId = parentTermId;
      this.childTermId = childTermId;
      this.relationshipName = relationshipName;
    }

    private String getParentTermId()
    {
      return this.parentTermId;
    }

    private String getChildTermId()
    {
      return this.childTermId;
    }

    private String getRelationshipName()
    {
      return this.relationshipName;
    }
  }


  /**
   * @param args
   */
  @StartSession
  public static void main(String[] args) throws Exception
  {
    OntologyImporter oi = null;
    String fileName = null;

    switch (args.length)
    {
        case 2:
            fileName = args[0];
            String ontologyTitle = args[1];
            oi = new OntologyImporter(fileName, ontologyTitle);
            oi.displayStatusToSysOut = true;
            oi.importOntology();
            break;
        case 1:
            fileName = args[0];
            oi = new OntologyImporter(fileName);
            oi.displayStatusToSysOut = true;
            oi.importOntology();
            break;
        default:
            String errMsg =
              "Incorrect args!  Takes two arguments, filename & ontology name (optional)\n"+
              "Specifying just the filename will only import the relationship definitions and will \n"+
              "term definitions";
            System.out.println(errMsg);
    }
  }

  private OntologyImporter()
  {
    super();
    this.ontologyRelationshipInfoMap = new HashMap<String, OntologyRelationshipInfo>();
    this.ontologyRelationshipByNameMap = new HashMap<String, OntologyRelationship>();

    this.initializeBuiltInRelationships();

    this.termRelationshipInfoList = new LinkedList<TermRelationshipInfo>();
  }

  public OntologyImporter(String fileName, String ontologyTitle)
  {
    this();
    this.fileName = fileName;
    this.ontologyTitle = ontologyTitle;
  }

  public OntologyImporter(String fileName)
  {
    this();
    this.fileName = fileName;
    this.ontologyTitle = null;
  }

  protected void setDisplayStatusToSysOut(boolean displayStatusToSysOut)
  {
    this.displayStatusToSysOut = displayStatusToSysOut;
  }

  /**
   * Some ontology relationships are built in, meaning they apply to
   * any ontology.
   */
  private void initializeBuiltInRelationships()
  {
    QueryFactory qf = new QueryFactory();

    OntologyRelationshipQuery orQ = new OntologyRelationshipQuery(qf);
    orQ.WHERE(orQ.getIsBuiltIn().EQ(true));

    for (OntologyRelationship ontologyRelationship : orQ.getIterator())
    {
      this.ontologyRelationshipByNameMap.put(ontologyRelationship.getName(), ontologyRelationship);
    }

  }

  @Transaction
  public void importOntology() throws Exception
  {
//    cleanup();

    if (this.ontologyTitle != null)
    {
      this.importOntologyDefinition();
    }

    this.importOntologyRelationshipDefinitions();

    if (this.ontologyTitle != null)
    {
      this.importTermDefinitions();
    }
  }

  private void cleanup()
  {
    int applyCount = 0;

    QueryFactory qf = new QueryFactory();

    if (this.displayStatusToSysOut)
    {
      System.out.println("\nDeleting: ["+MdClass.getMdClass(TermRelationship.CLASS).definesType()+"]");
    }
    TermRelationshipQuery trQ = new TermRelationshipQuery(qf);
    for (TermRelationship termRelationship : trQ.getIterator())
    {
      TermRelationship.get(termRelationship.getId()).delete();
      applyCount = cleanupFeedback(applyCount);
    }

    applyCount = 0;
    if (this.displayStatusToSysOut)
    {
      System.out.println("\nDeleting: ["+MdClass.getMdClass(Term.CLASS).definesType()+"]");
    }
    TermQuery tQ = new TermQuery(qf);
    for (Term term : tQ.getIterator())
    {
      Term.get(term.getId()).delete();
      applyCount = cleanupFeedback(applyCount);
    }

    applyCount = 0;
    if (this.displayStatusToSysOut)
    {
      System.out.println("\nDeleting: ["+MdClass.getMdClass(OntologyRelationship.CLASS).definesType()+"]");
    }
    OntologyRelationshipQuery orQ = new OntologyRelationshipQuery(qf);
    for (OntologyRelationship ontologyRelationship : orQ.getIterator())
    {
      OntologyRelationship.get(ontologyRelationship.getId()).delete();
      applyCount = cleanupFeedback(applyCount);
    }

    applyCount = 0;
    if (this.displayStatusToSysOut)
    {
      System.out.println("\nDeleting: ["+MdClass.getMdClass(Ontology.CLASS).definesType()+"]");
    }
    OntologyQuery ontologyQuery = new OntologyQuery(qf);
    for (Ontology ontology : ontologyQuery.getIterator())
    {
      ontology.delete();
      applyCount = cleanupFeedback(applyCount);
    }
  }


  private int cleanupFeedback(int applyCount)
  {
    if (this.displayStatusToSysOut)
    {
      if (applyCount % feedbackMod == 0)
      {
        System.out.println();
      }
      System.out.print(".");
      applyCount++;
    }

    return applyCount;
  }

  /**
   * Creates a new ontology with the given ontology title and namespace or
   * uses an existing ontology with the given ontology title and namespace
   * if one exists.
   *
   * @precondition this.ontologyTitle != null && !this.ontologyTitle.equals("")
   *
   * @throws Exception
   */
  private void importOntologyDefinition() throws Exception
  {
    String defaultNamespace = "";

    try
    {
      BufferedReader br = new BufferedReader(new FileReader(this.fileName));
      String line;
      while ((line = br.readLine()) != null)
      {
        // We are finished processing the ontology section
        if (line.startsWith(OBO_SECTION_DELIMITER))
        {
          break;
        }
        else if (line.startsWith(OBO_ONTOLOGY_DEFAULT_NAMESPACE))
        {
          defaultNamespace = this.extractFieldValue(OBO_ONTOLOGY_DEFAULT_NAMESPACE, line);
        }
      }
      br.close();
    }
    catch (FileNotFoundException e)
    {
      throw e;
    }
    catch (IOException e)
    {
      throw e;
    }

    String ontologyKey = Ontology.buildKey(defaultNamespace, this.ontologyTitle);

    try
    {

      this.ontology = Ontology.getByKey(ontologyKey);

      if (this.displayStatusToSysOut)
      {
        System.out.println("Modifying Ontology: " + this.ontology.getTitle());
      }
    }
    // Ontology does not yet exist
    catch (DataNotFoundException e)
    {
      this.ontology = new Ontology();
      this.ontology.setTitle(this.ontologyTitle);
      this.ontology.setDefaultNamespace(defaultNamespace);
      this.ontology.apply();

      if (this.displayStatusToSysOut)
      {
        System.out.println("Created Ontology: " + this.ontology.getTitle());
      }

    }
  }

  private void importOntologyRelationshipDefinitions() throws Exception
  {
    if (this.displayStatusToSysOut)
    {
      System.out.println("Importing Ontology Relationshipo Definitions");
    }

    try
    {
      BufferedReader br = new BufferedReader(new FileReader(this.fileName));
      String line;
      while ((line = br.readLine()) != null)
      {
        if (line.startsWith(OBO_TYPEDEF_DELIMITER))
        {
          boolean encounteredNewTypeDef = this.importTypeDef(br, line);

          while (encounteredNewTypeDef)
          {
            encounteredNewTypeDef = this.importTypeDef(br, line);
          }
        }
      }
      br.close();
    }
    catch (FileNotFoundException e)
    {
      throw e;
    }
    catch (IOException e)
    {
      throw e;
    }

    // set the inverse references and relationship to the ontology
    for (OntologyRelationshipInfo ontologyRelationshipInfo : this.ontologyRelationshipInfoMap.values())
    {
      OntologyRelationship ontologyRelationship = ontologyRelationshipInfo.getOntologyRelationship();
      String inverseOfId = ontologyRelationshipInfo.getInverseOfId();
      String inverseOfOnInstanceLevelId = ontologyRelationshipInfo.getInverseOfOnInstanceLevelId();

      if (!inverseOfId.equals(""))
      {
        try
        {
          OntologyRelationship inverseOfOntologyRelationship = OntologyRelationship.getByKey(inverseOfId);
          ontologyRelationship.setInverseOf(inverseOfOntologyRelationship);
        }
        catch (DataNotFoundException e)
        {
          String devMessage =
            "Unable to locate "+OBO_TYPEDEF_DELIMITER+" with id ["+inverseOfId+"] for ["+OBO_FIELD_INVERSE_OF+
            "] on "+ontologyRelationship+" with id ["+ontologyRelationship.getRelationshipId()+"]";
          this.throwInvalidOBOFormatException(devMessage);
        }
      }

      if (!inverseOfOnInstanceLevelId.equals(""))
      {
        try
        {
          OntologyRelationship inverseOfOnInstanceLevelOR = OntologyRelationship.getByKey(inverseOfOnInstanceLevelId);
          ontologyRelationship.setInverseOf(inverseOfOnInstanceLevelOR);
        }
        catch (DataNotFoundException e)
        {
          String devMessage =
            "Unable to locate "+OBO_TYPEDEF_DELIMITER+" with id ["+inverseOfOnInstanceLevelId+"] for ["+OBO_FIELD_INVERSE_OF_ON_INSTANCE_LEVEL+
            "] on "+ontologyRelationship+" with id ["+ontologyRelationship.getRelationshipId()+"]";
          this.throwInvalidOBOFormatException(devMessage);
        }
      }

      ontologyRelationship.apply();

      if (this.displayStatusToSysOut)
      {
        if (ontologyRelationship.isNew())
        {
          System.out.println("Creating Ontology Relationship: "+ontologyRelationship.getRelationshipId());
        }
        else
        {
          System.out.println("Modifying Ontology Relationship: "+ontologyRelationship.getRelationshipId());
        }
      }
    }

  }

  private boolean importTypeDef(BufferedReader br, String line) throws Exception
  {
    OntologyRelationship ontologyRelationship = null;
    String relationshipId = "";
    String inverseOfId = "";
    String inverseOfOnInstanceLevelId = "";

    boolean encounteredNewTypeDef = false;
    try
    {
      while ((line = br.readLine()) != null)
      {
        if (line.startsWith(OBO_TYPEDEF_DELIMITER))
        {
          encounteredNewTypeDef = true;
          break;
        }
        // Assumes this field is parsed first!!!!
        else if (line.startsWith(OBO_FIELD_ID+OBO_FIELD_DELIMITER))
        {
          relationshipId = this.extractFieldValue(OBO_FIELD_ID, line);
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setRelationshipId(this.extractFieldValue(OBO_FIELD_ID, line));
        }
        else if (line.startsWith(OBO_FIELD_ALT_ID+OBO_FIELD_DELIMITER))
        {
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setAltId(this.extractFieldValue(OBO_FIELD_ALT_ID, line));
        }
        else if (line.startsWith(OBO_FIELD_NAMESPACE+OBO_FIELD_DELIMITER))
        {
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setNamespace(this.extractFieldValue(OBO_FIELD_NAMESPACE, line));
        }
        else if (line.startsWith(OBO_FIELD_NAME+OBO_FIELD_DELIMITER))
        {
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setName(this.extractFieldValue(OBO_FIELD_NAME, line));
        }
        else if (line.startsWith(OBO_FIELD_DEF+OBO_FIELD_DELIMITER))
        {
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setDef(this.extractFieldValue(OBO_FIELD_DEF, line));
        }
        else if (line.startsWith(OBO_FIELD_COMMENT+OBO_FIELD_DELIMITER))
        {
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setDef(this.extractFieldValue(OBO_FIELD_COMMENT, line));
        }
        else if (line.startsWith(OBO_FIELD_INVERSE_OF_ON_INSTANCE_LEVEL+OBO_FIELD_DELIMITER))
        {
          inverseOfOnInstanceLevelId = this.extractReferenceFieldValue(OBO_FIELD_INVERSE_OF_ON_INSTANCE_LEVEL, line);
        }
        else if (line.startsWith(OBO_FIELD_INVERSE_OF+OBO_FIELD_DELIMITER))
        {
          inverseOfId = this.extractReferenceFieldValue(OBO_FIELD_INVERSE_OF, line);
        }
        else if (line.startsWith(OBO_FIELD_BUILTIN+OBO_FIELD_DELIMITER))
        {
          String builtInString = this.extractFieldValue(OBO_FIELD_BUILTIN, line);
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setIsBuiltIn(Boolean.parseBoolean(builtInString));
        }
        else if (line.startsWith(OBO_FIELD_IS_REFLEXIVE+OBO_FIELD_DELIMITER))
        {
          String isReflexiveString = this.extractFieldValue(OBO_FIELD_IS_REFLEXIVE, line);
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setIsReflexive(Boolean.parseBoolean(isReflexiveString));
        }
        else if (line.startsWith(OBO_FIELD_IS_TRANSITIVE+OBO_FIELD_DELIMITER))
        {
          String isTransitiveString = this.extractFieldValue(OBO_FIELD_IS_TRANSITIVE, line);
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setIsTransitive(Boolean.parseBoolean(isTransitiveString));
        }
        else if (line.startsWith(OBO_FIELD_IS_OBSOLETE+OBO_FIELD_DELIMITER))
        {
          String isObsoleteString = this.extractFieldValue(OBO_FIELD_IS_OBSOLETE, line);
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setIsObsolete(Boolean.parseBoolean(isObsoleteString));
        }
        else if (line.startsWith(OBO_FIELD_IS_ANTI_SYMMETRIC+OBO_FIELD_DELIMITER))
        {
          String isAntiSymmetricString = this.extractFieldValue(OBO_FIELD_IS_ANTI_SYMMETRIC, line);
          ontologyRelationship = initOntologyRelationship(ontologyRelationship, relationshipId);
          ontologyRelationship.setIsAntiSymmetric(Boolean.parseBoolean(isAntiSymmetricString));
        }
      }
    }
    catch (FileNotFoundException e)
    {
      throw e;
    }
    catch (IOException e)
    {
      throw e;
    }

    ontologyRelationship = initOntologyRelationship(ontologyRelationship,  relationshipId);

    ontologyRelationship.apply();

    if (this.ontology != null)
    {
      // create save point
      Savepoint savepoint = Database.setSavepoint();

      try
      {
        ontologyRelationship.addOntology(this.ontology).apply();
      }
      catch (DuplicateGraphPathException e)
      {
        // a relationship between this typedef and the ontology already exists
        Database.rollbackSavepoint(savepoint);
      }
      finally
      {
        Database.releaseSavepoint(savepoint);
      }
    }

    OntologyRelationshipInfo ontologyRelationshipInfo =
      new OntologyRelationshipInfo(ontologyRelationship, inverseOfId, inverseOfOnInstanceLevelId);

    this.ontologyRelationshipInfoMap.put(ontologyRelationship.getRelationshipId(), ontologyRelationshipInfo);
    this.ontologyRelationshipByNameMap.put(ontologyRelationship.getName(), ontologyRelationship);

    return encounteredNewTypeDef;

  }

  /**
   * Initializes the given <code>OntologyRelationship</code> object
   *
   * @precondition relationshipId != null
   *
   * @param ontologyRelationship
   * @param relationshipId
   * @return
   */
  private OntologyRelationship initOntologyRelationship(OntologyRelationship ontologyRelationship, String relationshipId)
  {
    if(relationshipId.equals(""))
    {
      String devMessage = "Missing ID for "+OBO_TYPEDEF_DELIMITER;
      this.throwInvalidOBOFormatException(devMessage);
    }

    if (ontologyRelationship != null)
    {
      return ontologyRelationship;
    }
    else
    {
      try
      {
        return OntologyRelationship.getByKey(relationshipId);
      }
      catch (DataNotFoundException e)
      {
        OntologyRelationship newOntologyRelationship = new OntologyRelationship();
        newOntologyRelationship.setRelationshipId(relationshipId);

        return newOntologyRelationship;
      }
    }
  }

  private void importTermDefinitions() throws Exception
  {
    int applyCount = 0;

    if (this.displayStatusToSysOut)
    {
      System.out.println("Creating Terms:");
    }

    try
    {
      BufferedReader br = new BufferedReader(new FileReader(this.fileName));
      String line;
      while ((line = br.readLine()) != null)
      {
        if (line.startsWith(OBO_TERM_DELIMITER))
        {
          boolean encounteredNewTerm = this.importTerm(br, line);

          if (this.displayStatusToSysOut)
          {
            if (applyCount % feedbackMod == 0)
            {
              System.out.println();
            }
            System.out.print(".");
            applyCount++;
          }

          while (encounteredNewTerm)
          {
            encounteredNewTerm = this.importTerm(br, line);

            if (this.displayStatusToSysOut)
            {
              if (applyCount % feedbackMod == 0)
              {
                System.out.println();
              }

              System.out.print(".");
              applyCount++;
            }
          }
        }
        else if (line.startsWith(OBO_TYPEDEF_DELIMITER))
        {
          break;
        }
      }
      br.close();
    }
    catch (FileNotFoundException e)
    {
      throw e;
    }
    catch (IOException e)
    {
      throw e;
    }

    System.out.println("\nCreating term relationships: ");
    applyCount = 0;


    for (TermRelationshipInfo termRelationshipInfo : this.termRelationshipInfoList)
    {
      Term term = null;
      try {
    	  term = Term.getByKey(termRelationshipInfo.getParentTermId());
      }
      catch (DataNotFoundException dnfe) {
    	  // The parent is not found.  This is bad...
    	  throw dnfe;
      }
      
      Term referencedTerm = null;
      try {
    	  referencedTerm = Term.getByKey(termRelationshipInfo.getChildTermId());
      } catch (DataNotFoundException dnfe) {
    	  // The child is not found.  This is generally because the relationship was added,
    	  // but the child was not (because it was obsolete), so we can ignore it.
    	  // Do nothing here, just leave referencedTerm null
      }
      
      if (term != null && referencedTerm != null) {
	      TermRelationship termRelationship = new TermRelationship(term, referencedTerm);
	      termRelationship.setOntologyRelationship(this.ontologyRelationshipByNameMap.get(termRelationshipInfo.getRelationshipName()));
	
	      if (this.displayStatusToSysOut)
	      {
	        if (applyCount % feedbackMod == 0)
	        {
	          System.out.println();
	        }
	        System.out.print(".");
	        applyCount++;
	      }
	
	      // create save point
	      Savepoint savepoint = Database.setSavepoint();
	      try
	      {
	        termRelationship.applyWithoutCreatingAllPaths();
	      }
	      catch (DuplicateGraphPathException e)
	      {
	        // a relationship between this typedef and the parent and the child already exists
	        Database.rollbackSavepoint(savepoint);
	      }
	      finally
	      {
	        Database.releaseSavepoint(savepoint);
	      }
      }
    }

    attachRoots();
  }
  
  private void attachRoots()
  {
    Ontology ontology = Ontology.getByKey(MO.KEY);
    OntologyRelationship ontologyRel = OntologyRelationship.getByKey(OBO.IS_A);
    
    String rootStr = MDSSProperties.getString("ROOT");
    RootTerm root = new RootTerm();
    root.setTermId(rootStr);
    root.setName(rootStr);
    root.getTermDisplayLabel().setValue(rootStr);
    root.setOntology(ontology);
    root.apply();
    
    QueryFactory f = new QueryFactory();
    TermQuery q = new TermQuery(f);
    TermRelationshipQuery r = new TermRelationshipQuery(f);
    
    q.WHERE(q.getId().SUBSELECT_NOT_IN(r.childId()));
    q.AND(q.getId().NE(root.getId()));
    
    OIterator<? extends Term> iter = q.getIterator();
    
    try
    {
      while(iter.hasNext())
      {
        Term oldRoot = iter.next();
      
        TermRelationship rel = root.addChildTerm(oldRoot);
        rel.setOntologyRelationship(ontologyRel);
        rel.applyWithoutCreatingAllPaths();
      }
    }
    finally
    {
      iter.close();
    }
  }

  private boolean importTerm(BufferedReader br, String line) throws Exception
  {
    Term term = null;
    String termId = "";

    boolean encounteredNewTerm = false;
    boolean isObsolete = false; // DEFAULT
    try
    {
      while ((line = br.readLine()) != null)
      {
        if (line.startsWith(OBO_TERM_DELIMITER))
        {
          encounteredNewTerm = true;
          break;
        }
        else if (line.startsWith(OBO_TYPEDEF_DELIMITER))
        {
          break;
        }
        // Assumes this field is parsed first!!!!
        else if (line.startsWith(OBO_FIELD_ID+OBO_FIELD_DELIMITER))
        {
          termId = this.extractFieldValue(OBO_FIELD_ID, line);
          term = initTerm(term, termId);
          term.setTermId(this.extractFieldValue(OBO_FIELD_ID, line));
        }
        else if (line.startsWith(OBO_FIELD_NAME+OBO_FIELD_DELIMITER))
        {
          term = initTerm(term, termId);
          term.setName(this.extractFieldValue(OBO_FIELD_NAME, line));
        }
        else if (line.startsWith(OBO_FIELD_NAMESPACE+OBO_FIELD_DELIMITER))
        {
          term = initTerm(term, termId);
          term.setNamespace(this.extractFieldValue(OBO_FIELD_NAMESPACE, line));
        }
        else if (line.startsWith(OBO_FIELD_DEF+OBO_FIELD_DELIMITER))
        {
          term = initTerm(term, termId);
          term.setDef(this.extractFieldValue(OBO_FIELD_DEF, line));
        }
        else if (line.startsWith(OBO_FIELD_COMMENT+OBO_FIELD_DELIMITER))
        {
          term = initTerm(term, termId);
          term.setComment(this.extractFieldValue(OBO_FIELD_COMMENT, line));
        }
        else if (line.startsWith(OBO_FIELD_IS_OBSOLETE+OBO_FIELD_DELIMITER))
        {
          String obsoleteString = this.extractFieldValue(OBO_FIELD_IS_OBSOLETE, line);
          term = initTerm(term, termId);
          
          isObsolete = Boolean.parseBoolean(obsoleteString);
//          term.setObsolete(Boolean.parseBoolean(obsoleteString));
        }
        else
        {
          // The next line might be a relationship
          processRelationship(line, term);
        }
      }
    }
    catch (FileNotFoundException e)
    {
      throw e;
    }
    catch (IOException e)
    {
      throw e;
    }

    if (!isObsolete)
    {
      term.setOntology(this.ontology);
      term.apply();
    }
    else // term.getObsolete() == true;
    {
      // Delete the term if it is not new and it is marked obsolete.
      if (!term.isNew())
      {
        // If there are more than one row in the all paths table,
        // then the delete will abort because that table needs to
        // be maintained
        QueryFactory qf = new QueryFactory();
        AllPathsQuery apQ = new AllPathsQuery(qf);
        apQ.WHERE(apQ.getParentTerm().EQ(term).
            AND(apQ.getChildTerm().EQ(term)));

        for (AllPaths allPaths : apQ.getIterator())
        {
          allPaths.delete();
        }

        term.delete();
      }
    }

    return encounteredNewTerm;
  }

  /**
   * Initializes the given <code>Term</code> object
   *
   * @precondition termId != null
   *
   * @param term
   * @param termId
   * @return
   */
  private Term initTerm(Term term, String termId)
  {
    if(termId.equals(""))
    {
      String devMessage = "Missing ID for "+OBO_TYPEDEF_DELIMITER;
      this.throwInvalidOBOFormatException(devMessage);
    }

    if (term != null)
    {
      return term;
    }
    else
    {
      try
      {
        return Term.getByKey(termId);
      }
      catch (DataNotFoundException e)
      {
        Term newTerm = new Term();
        newTerm.setTermId(termId);

        return newTerm;
      }
    }
  }

  private boolean processRelationship(String line, Term term)
  {
    int delimIndex = line.indexOf(":");
    if (delimIndex <= -1)
    {
      return false;
    }

    int separatorIndex = line.indexOf("!");
    if (separatorIndex <= -1)
    {
      return false;
    }

    String relationshipName = line.substring(0, delimIndex).trim();
    String referencedTermId = line.substring(delimIndex+1, separatorIndex).trim();

    if (this.ontologyRelationshipByNameMap.containsKey(relationshipName))
    {
      TermRelationshipInfo termRelationshipInfo = new TermRelationshipInfo(referencedTermId, term.getTermId(), relationshipName);
      this.termRelationshipInfoList.add(termRelationshipInfo);
      
      return true;
    }

    return false;
  }

  private String extractFieldValue(String fieldDelimiter, String line)
  {
    return line.substring((fieldDelimiter+OBO_FIELD_DELIMITER).length(), line.length()).trim();
  }

  private String extractReferenceFieldValue(String fieldDelimiter, String line)
  {
    int endIndex;

    if (line.indexOf("!") <= -1)
    {
      endIndex = line.length();
    }
    else
    {
      endIndex = line.indexOf("!");
    }

    return line.substring((fieldDelimiter+":").length(), endIndex).trim();
  }

  private void throwInvalidOBOFormatException(String devMessage)
  {
    InvalidOBOFormatException invalidOBOFormatException = new InvalidOBOFormatException(devMessage);
    invalidOBOFormatException.setFileName(this.fileName);
    invalidOBOFormatException.apply();
    throw invalidOBOFormatException;
  }
}
