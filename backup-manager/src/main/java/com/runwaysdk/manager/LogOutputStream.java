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
package com.runwaysdk.manager;

import java.io.OutputStream;

public class LogOutputStream extends OutputStream
{
  /** The internal memory for the written bytes. */
  private String mem;

  public LogOutputStream ()
  {
    mem = "";
  }

  /**
   * Writes a byte to the output stream. This method flushes automatically at the end of a line.
   */
  public void write (int b) {
      byte[] bytes = new byte[1];
      bytes[0] = (byte) (b & 0xff);
      mem = mem + new String(bytes);

      if (mem.endsWith ("\n")) {
          mem = mem.substring (0, mem.length () - 1);
          flush ();
      }
  }

  /**
   * Flushes the output stream.
   */
  public void flush () {
      Logger.error(mem);
      mem = "";
  }
}
