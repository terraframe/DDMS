/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions.query;

import com.runwaysdk.dataaccess.transaction.Transaction;

public class CycleJobView extends CycleJobViewBase implements com.runwaysdk.generation.loader.Reloadable
{
  private static final long serialVersionUID = -702672080;

  public CycleJobView()
  {
    super();
  }

  @Override
  @Transaction
  public void apply()
  {
    if (this.getCreateJob())
    {
      CycleJob concrete = this.getConcrete();

      if (concrete.isAppliedToDB())
      {
        concrete.lock();
      }

      concrete.populate(this);
      concrete.apply();

      this.populate(concrete);
    }
    else
    {
      this.delete();

      /*
       * Clear all of the values
       */
      this.setJobName(null);
      this.setLayerId(null);
      this.setImageWidth(CycleJob.DEFAULT_WIDTH);
      this.setImageHeight(CycleJob.DEFAULT_HEIGHT);
      this.setConcreteId(null);
    }
  }

  @Override
  public void delete()
  {
    if (this.getConcreteId() != null && this.getConcreteId().length() > 0)
    {
      CycleJob concrete = this.getConcrete();
      concrete.delete();
    }
  }

  /**
   * Populates the contents of the view from the supplied job
   * 
   * @param concrete
   */
  public void populate(CycleJob job)
  {
    this.setCreateJob(false);
    this.setJobName(job.getJobName());
    this.setSavedMap(job.getSavedMap());
    this.setLayerId(job.getLayerId());
    this.setImageWidth(job.getImageWidth());
    this.setImageHeight(job.getImageHeight());

    if (job.isAppliedToDB())
    {
      this.setCreateJob(true);
      this.setConcreteId(job.getId());
    }
  }

  public CycleJob getConcrete()
  {
    if (this.getConcreteId() != null && this.getConcreteId().length() > 0)
    {
      return CycleJob.get(this.getConcreteId());
    }

    return new CycleJob();
  }

}
