package dss.vector.solutions.ontology;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.runwaysdk.business.Business;
import com.runwaysdk.query.OIterator;
import com.runwaysdk.query.QueryFactory;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdBusiness;

import junit.framework.Assert;

public class AllpathsTest
{
  @Before
  public void setUp() throws Exception
  {
    
  }

  @After
  public void tearDown() throws Exception
  {
    
  }

  public void createTestData()
  {
    OntologyRelationship rel = OntologyRelationship.getByKey(OBO.IS_A);
    
    RootTerm root = RootTerm.getRootInstance();
    
    //                  Tree Root
    //                    /   \
    //                 Spacer  \
    //                  /       \
    //               delRoot     F
    //             /      |     /
    //            A       B    /
    //            |     / | \ /
    //            |    C  E  G
    //            |   /|   \ /
    //            | /  |    H
    //            D    |   /
    //            |    |   I
    //            J    |
    //              \  |
    //                K
    //                |
    //                L
    
    
    // Spacer
    Term spacer = new Term();
    spacer.setName("AllpathsTest Spacer");
    spacer.setTermId("AllpathsTest Spacer");
    spacer.apply();
    TermRelationship spacerRel = spacer.addParentTerm(root);
    spacerRel.setOntologyRelationship(rel);
    spacerRel.apply();
    
    // Delete Root
    Term delRoot = new Term();
    delRoot.setName("AllpathsTest Delete Root");
    delRoot.setTermId("AllpathsTest Delete Root");
    delRoot.apply();
    TermRelationship delRootRel = delRoot.addParentTerm(spacer);
    delRootRel.setOntologyRelationship(rel);
    delRootRel.apply();
    
    // A
    Term a = new Term();
    a.setName("AllpathsTest A");
    a.setTermId("AllpathsTest A");
    a.apply();
    TermRelationship aRel = a.addParentTerm(delRoot);
    aRel.setOntologyRelationship(rel);
    aRel.apply();
    
    // B
    Term b = new Term();
    b.setName("AllpathsTest B");
    b.setTermId("AllpathsTest B");
    b.apply();
    TermRelationship bRel = b.addParentTerm(delRoot);
    bRel.setOntologyRelationship(rel);
    bRel.apply();
    
    // C
    Term c = new Term();
    c.setName("AllpathsTest C");
    c.setTermId("AllpathsTest C");
    c.apply();
    TermRelationship cRel = c.addParentTerm(b);
    cRel.setOntologyRelationship(rel);
    cRel.apply();
    
    // D
    Term d = new Term();
    d.setName("AllpathsTest D");
    d.setTermId("AllpathsTest D");
    d.apply();
    TermRelationship dRel = d.addParentTerm(c);
    dRel.setOntologyRelationship(rel);
    dRel.apply();
    TermRelationship dRel2 = d.addParentTerm(a);
    dRel2.setOntologyRelationship(rel);
    dRel2.apply();
    
    // E
    Term e = new Term();
    e.setName("AllpathsTest E");
    e.setTermId("AllpathsTest E");
    e.apply();
    TermRelationship eRel = e.addParentTerm(b);
    eRel.setOntologyRelationship(rel);
    eRel.apply();
    
    // F
    Term f = new Term();
    f.setName("AllpathsTest F");
    f.setTermId("AllpathsTest F");
    f.apply();
    TermRelationship fRel = f.addParentTerm(RootTerm.getRootInstance());
    fRel.setOntologyRelationship(rel);
    fRel.apply();
    
    // G
    Term g = new Term();
    g.setName("AllpathsTest G");
    g.setTermId("AllpathsTest G");
    g.apply();
    TermRelationship gRel = g.addParentTerm(b);
    gRel.setOntologyRelationship(rel);
    gRel.apply();
    TermRelationship gRel2 = g.addParentTerm(f);
    gRel2.setOntologyRelationship(rel);
    gRel2.apply();
    
    // H
    Term h = new Term();
    h.setName("AllpathsTest H");
    h.setTermId("AllpathsTest H");
    h.apply();
    TermRelationship hRel = h.addParentTerm(g);
    hRel.setOntologyRelationship(rel);
    hRel.apply();
    TermRelationship hRel2 = h.addParentTerm(e);
    hRel2.setOntologyRelationship(rel);
    hRel2.apply();
    
    // I
    Term i = new Term();
    i.setName("AllpathsTest I");
    i.setTermId("AllpathsTest I");
    i.apply();
    TermRelationship iRel = i.addParentTerm(h);
    iRel.setOntologyRelationship(rel);
    iRel.apply();
    
    // J
    Term j = new Term();
    j.setName("AllpathsTest J");
    j.setTermId("AllpathsTest J");
    j.apply();
    TermRelationship jRel = j.addParentTerm(d);
    jRel.setOntologyRelationship(rel);
    jRel.apply();
    
    // K
    Term k = new Term();
    k.setName("AllpathsTest K");
    k.setTermId("AllpathsTest K");
    k.apply();
    TermRelationship kRel = k.addParentTerm(j);
    kRel.setOntologyRelationship(rel);
    kRel.apply();
    TermRelationship kRel2 = k.addParentTerm(c);
    kRel2.setOntologyRelationship(rel);
    kRel2.apply();
    
    // L
    Term l = new Term();
    l.setName("AllpathsTest L");
    l.setTermId("AllpathsTest L");
    l.apply();
    TermRelationship lRel = l.addParentTerm(k);
    lRel.setOntologyRelationship(rel);
    lRel.apply();
  }
  
  public void destroyTestData()
  {
    MdBusiness mdBusiness = MdBusiness.getMdBusiness(AllPaths.CLASS);
    mdBusiness.deleteAllTableRecords();
    
    TermQuery q = new TermQuery(new QueryFactory());
    OIterator<? extends Term> it = q.getIterator();
    while (it.hasNext())
    {
      Term t = it.next();
      if (t.getName().contains("AllpathsTest"))
      {
        t.delete(false);
      }
    }
    
    long pre = System.nanoTime();
    
    AllPaths.rebuildAllPaths();
    
    long post = System.nanoTime();
    long elapsed = (post - pre) / 1000000000;
    System.out.println("Rebuilding the allpaths table took: " + elapsed + " seconds");
  }
  
  public void validateAllpaths()
  {
    Stack<Term> s = new Stack<Term>();
    s.push(RootTerm.getRootInstance());
    
    while (!s.empty())
    {
      Term current = s.pop();
      
      OIterator<? extends Business> children = current.getChildren(TermRelationship.CLASS);
      try
      {
        while (children.hasNext())
        {
          Term child = (Term) children.next();
          
          if (child.getName().contains("AllpathsTest"))
          {
            s.push(child);
          }
        }
      }
      finally
      {
        children.close();
      }
      
      validateAllpaths(current);
    }
  }
  
  public void validateAllpaths(Term term)
  {
    enforceAPExist(term, term);
    Set<String> ancestors = validateParentAllpaths(term, term);
    assertNumChildEntries(term, ancestors.size() + 1);
  }
  
  public Set<String> validateParentAllpaths(Term child, Term parent)
  {
    if (parent.equals(RootTerm.getRootInstance()))
    {
      return new HashSet<String>();
    }
    
    Set<String> ancestors = new HashSet<String>();
    
    OIterator<? extends Business> parents = parent.getParents(TermRelationship.CLASS);
    try
    {
      while (parents.hasNext())
      {
        Term parentOfParent = (Term) parents.next();
        
        enforceAPExist(child, parentOfParent);
        
        ancestors.addAll(validateParentAllpaths(child, parentOfParent));
        
        ancestors.add(parentOfParent.getId());
      }
    }
    finally
    {
      parents.close();
    }
    
    return ancestors;
  }
  
  public void enforceAPExist(Term child, Term parent)
  {
    AllPathsQuery apq = new AllPathsQuery(new QueryFactory());
    apq.WHERE(apq.getChildTerm().EQ(child));
    apq.AND(apq.getParentTerm().EQ(parent));
    OIterator<? extends AllPaths> it = apq.getIterator();
    if (!it.hasNext())
    {
      throw new RuntimeException("Expected AllPaths entry between parent [" + parent.getName() + "] and child [" + child.getName() + "].");
    }
    it.next();
    if (it.hasNext())
    {
      throw new RuntimeException("Too many AllPaths entries defined between parent [" + parent.getName() + "] and child [" + child.getName() + "].");
    }
  }
  
  public void assertNumChildEntries(Term child, int num)
  {
    AllPathsQuery apq = new AllPathsQuery(new QueryFactory());
    apq.WHERE(apq.getChildTerm().EQ(child));
    Assert.assertEquals("Number of allpaths entries for child [" + child.getTermId() + "] is incorrect.", num, apq.getCount());
  }
  
  public void ensureNoTestDataExists()
  {
    AllPathsQuery q = new AllPathsQuery(new QueryFactory());
    OIterator<? extends AllPaths> it = q.getIterator();
    while (it.hasNext())
    {
      AllPaths ap = it.next();
      String childName = ap.getChildTerm().getName();
      
      if (childName.contains("AllpathsTest"))
      {
        throw new RuntimeException("Test AllPaths data exists between parent [" + ap.getParentTerm().getName() + "] and child [" + childName + "].");
      }
    }
  }
  
  @Test
  @Request
  public void testDelete() throws Exception
  {
    destroyTestData();
    
    try
    {
      createTestData();
      validateAllpaths();
      
      Term delRoot = Term.getByTermId("AllpathsTest Delete Root");
      
      System.out.println("Invoking deleteTerm");
      
      long pre = System.nanoTime();
      delRoot.deleteTerm();
      long post = System.nanoTime();
      long elapsed = (post - pre) / 1000000000;
      System.out.println("deleteTerm took: " + elapsed + " seconds");
      
      validateAllpaths();
      Term.getByTermId("AllpathsTest F").deleteTerm(); // F exists outside the delRoot
      Term.getByTermId("AllpathsTest Spacer").deleteTerm(); // Spacer exists outside the delRoot
      ensureNoTestDataExists();
    }
    finally
    {
      destroyTestData();
    }
  }
  
  @Test
  @Request
  public void testExportImport() throws Exception
  {
    destroyTestData();
    
    try
    {
      createTestData();
      
      OntologyExcelExporter.exportToFile(new File("OntologyExport2.xls"), Term.getByTermId("AllpathsTest Delete Root"));
      
      destroyTestData();
      
      OntologyExcelImporter.main(new String[]{"OntologyExport2.xls"});
      
      validateAllpaths();
    }
    finally
    {
      destroyTestData();
    }
  }
  
  @Test
  @Request
  public void testDeleteRelationship() throws Exception
  {
    destroyTestData();
    
    try
    {
      createTestData();
      validateAllpaths();
      
      Term a = Term.getByTermId("AllpathsTest A");
      Term b = Term.getByTermId("AllpathsTest B");
      Term delRoot = Term.getByTermId("AllpathsTest Delete Root");
      
      b.applyWithParent(a.getId(), false, delRoot.getId(), false);
      validateAllpaths();
      
      b.applyWithParent(delRoot.getId(), true, a.getId(), false);
      validateAllpaths();
      
      b.deleteRelationship(a.getId());
      validateAllpaths();
    }
    finally
    {
      destroyTestData();
    }
  }
}
