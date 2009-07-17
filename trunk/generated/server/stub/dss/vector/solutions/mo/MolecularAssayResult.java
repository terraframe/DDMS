package dss.vector.solutions.mo;

import java.util.List;

import com.terraframe.mojo.query.QueryFactory;

import dss.vector.solutions.entomology.AbstractMosquitoCollection;

public class MolecularAssayResult extends MolecularAssayResultBase implements
    com.terraframe.mojo.generation.loader.Reloadable
{
  private static final long serialVersionUID = 1235599936777L;

  public MolecularAssayResult()
  {
    super();
  }

  public static MolecularAssayResult[] getAll()
  {
    MolecularAssayResultQuery query = new MolecularAssayResultQuery(new QueryFactory());
    List<MolecularAssayResult> list = AbstractTerm.getAll(query, MolecularAssayResult.class);

    return list.toArray(new MolecularAssayResult[list.size()]);
  }

  public static MolecularAssayResult[] getAllActive()
  {
    MolecularAssayResultQuery query = new MolecularAssayResultQuery(new QueryFactory());
    List<MolecularAssayResult> list = AbstractTerm.getAllActive(query, MolecularAssayResult.class);

    return list.toArray(new MolecularAssayResult[list.size()]);
  }
  
  public static MolecularAssayResult validateByDisplayLabel(String displayLabel)
  {
    return (MolecularAssayResult)AbstractTerm.validateByDisplayLabel(displayLabel);
  }
}
