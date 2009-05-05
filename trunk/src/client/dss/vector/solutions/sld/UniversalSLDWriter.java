package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.UniversalLayerDTO;

public class UniversalSLDWriter extends SLDWriter implements Reloadable
{

  protected UniversalSLDWriter(UniversalLayerDTO layer)
  {
    super(layer);
  }

  protected UniversalLayerDTO getLayer()
  {
    return (UniversalLayerDTO) super.getLayer();
  }
}
