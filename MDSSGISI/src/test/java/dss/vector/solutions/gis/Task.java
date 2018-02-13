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
package dss.vector.solutions.gis;

public class Task
{
  private String taskName;

  private int    total;

  private int    work;

  public Task(String taskName, int total)
  {
    this.taskName = taskName;
    this.total = total;
    this.work = 0;
  }

  public String getTaskName()
  {
    return taskName;
  }

  public int getTotal()
  {
    return total;
  }

  public int getWork()
  {
    return work;
  }

  public void addWork(int work)
  {
    this.work += work;
  }

}
