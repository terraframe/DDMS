package dss.vector.solutions.sld;

import com.terraframe.mojo.generation.loader.Reloadable;

import dss.vector.solutions.query.ThematicLayerDTO;

public class ThematicSLDWriter extends SLDWriter implements Reloadable
{

  protected ThematicSLDWriter(ThematicLayerDTO layer)
  {
    super(layer);
  }

  protected ThematicLayerDTO getLayer()
  {
    return (ThematicLayerDTO) super.getLayer();
  }
}
