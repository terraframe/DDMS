package csu.mrc.ivcc.mdss.entomology.assay;

public interface AdultAssayIF extends AssayIF
{
  public csu.mrc.ivcc.mdss.entomology.assay.AdultAgeRange getAgeRange();
  
  public Integer getFed();
  
  public Integer getGravid();
  
  public java.util.List<csu.mrc.ivcc.mdss.entomology.AssaySex> getSex();  
}
