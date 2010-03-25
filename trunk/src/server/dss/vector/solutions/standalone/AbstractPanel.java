package dss.vector.solutions.standalone;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel implements ContainerIF
{

  /**
   * 
   */
  private static final long serialVersionUID = -3325736397662245965L;

  private ContainerIF       container;

  public AbstractPanel(ContainerIF container)
  {
    super();
    
    this.container = container;
  }

  public AbstractPanel(ContainerIF container, boolean isDoubleBuffered)
  {
    super(isDoubleBuffered);
    
    this.container = container;
  }

  public AbstractPanel(ContainerIF container, LayoutManager layout, boolean isDoubleBuffered)
  {
    super(layout, isDoubleBuffered);
    
    this.container = container;
  }

  public AbstractPanel(ContainerIF container, LayoutManager layout)
  {
    super(layout);
    
    this.container = container;
  }

  public abstract void unlock();

  public abstract void lock();

  public void unlockContainer()
  {
    this.container.unlock();
  }
  
  public void lockContainer()
  {
    this.container.lock();
  }
}
