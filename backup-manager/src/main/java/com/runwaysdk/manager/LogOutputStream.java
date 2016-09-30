package com.runwaysdk.manager;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogOutputStream extends PrintStream
{
  /** The internal memory for the written bytes. */
  private String mem;

  public LogOutputStream ()
  {
    super(new ByteArrayOutputStream());
    
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