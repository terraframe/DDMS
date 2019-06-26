package com.runwaysdk.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileIO
{
  private static final int BUFFER = 2048;

  /**
   * Reads the given file into a String
   * 
   * @param fileName
   *          Name of the file to read
   * @return String containing the file's contents
   */
  public static String readString(String fileName) throws IOException
  {
    return readString(new File(fileName));
  }

  /**
   * Reads the given file into a String
   * 
   * @param file
   *          File to read
   * @return String containing the file's contents
   */
  public static String readString(File file) throws IOException
  {
    // We read bytes directly and force a utf8 decoding
    return new String(readBytes(file), "utf8");
  }

  public static List<String> readLines(File file) throws IOException
  {
    BufferedReader reader = null;
    try
    {
      List<String> lines = new LinkedList<String>();
      reader = new BufferedReader(new FileReader(file));
      while (reader.ready())
        lines.add(reader.readLine());
      return lines;
    }
    finally
    {
      if (reader != null)
        reader.close();
    }
  }

  /**
   * Reads the given file into a byte[]
   * 
   * @param fileName
   *          Name of the file to read
   * @return byte[] containing the file's contents
   */
  public static byte[] readBytes(String fileName) throws IOException
  {
    return readBytes(new File(fileName));
  }

  /**
   * Reads the given file into a byte[]
   * 
   * @param file
   *          File to read
   * @return byte[] containing the file's contents
   */
  public static byte[] readBytes(File file) throws IOException
  {
    BufferedInputStream input = null;
    try
    {
      input = new BufferedInputStream(new FileInputStream(file));
      byte[] bytes = new byte[(int) file.length()];

      input.read(bytes);
      input.close();
      return bytes;
    }
    catch (IOException e)
    {
      throw e;
    }
    finally
    {
      if (input != null)
      {
        input.close();
      }
    }
  }

  /**
   * Writes a String to a file. Note that this does not change the content of
   * the String. For example, newlines will be written exactly as they are
   * found, not adapted to the current operating system.
   * 
   * @param fileName
   *          Name of the file to read
   * @param data
   *          Content to write into the file
   */
  public static void write(String fileName, String data) throws IOException
  {
    write(new File(fileName), data, false);
  }

  public static void write(String fileName, String data, boolean append) throws IOException
  {
    write(new File(fileName), data, append);
  }

  /**
   * Writes a String to a file. Note that this does not change the content of
   * the String. For example, newlines will be written exactly as they are
   * found, not adapted to the current operating system.
   * 
   * @param file
   *          The file to write to
   * @param data
   *          Content to write into the file
   */
  public static void write(File file, String data) throws IOException
  {
    write(file, data, false);
  }

  public static void write(File file, String data, boolean append) throws IOException
  {
    if (data == null || data.trim().equals(""))
      return;

    makeDirectories(file);
    BufferedWriter writer = null;
    try
    {
      writer = new BufferedWriter(new FileWriter(file, append));
      writer.write(data);
      writer.flush();
      writer.close();
      return;
    }
    catch (IOException e)
    {
      throw e;
    }
    finally
    {
      if (writer != null)
      {
        writer.close();
      }
    }
  }

  /**
   * Writes a byte[] to a file.
   * 
   * @param fileName
   *          Name of the file to read
   * @param data
   *          Content to write into the file
   */
  public static void write(String fileName, byte[] data) throws IOException
  {
    write(fileName, data, null);
  }

  /**
   * Writes a byte[] to a file.
   * 
   * @param fileName
   *          Name of the file to read
   * @param data
   *          Content to write into the file
   * @param logPrintStream
   */
  public static void write(String fileName, byte[] data, PrintStream logPrintStream) throws IOException
  {
    write(new File(fileName), data);
  }

  /**
   * Writes a byte[] to a file. If the byte array is null or has a length of 0,
   * then nothing is written to the file system.
   * 
   * @param file
   *          The file to write to
   * @param data
   *          Content to write into the file
   */
  public static void write(File file, byte[] data) throws IOException
  {
    write(file, data, null);
  }

  /**
   * Writes a byte[] to a file. If the byte array is null or has a length of 0,
   * then nothing is written to the file system.
   * 
   * @param file
   *          The file to write to
   * @param data
   *          Content to write into the file
   * @param logPrintStream
   */
  public static void write(File file, byte[] data, PrintStream logPrintStream) throws IOException
  {
    if (data == null || data.length == 0)
    {
      return;
    }

    makeDirectories(file);
    BufferedOutputStream output = null;
    try
    {
      output = new BufferedOutputStream(new FileOutputStream(file));
      output.write(data);
      output.flush();
      output.close();
      return;
    }
    catch (IOException e)
    {
      throw e;
    }
    finally
    {
      if (output != null)
      {
        output.close();
      }
    }
  }

  /**
   * Creates the directories for a File if they don't exist.
   * 
   * @param file
   */
  private static void makeDirectories(File file)
  {
    if (file.isDirectory())
      file.mkdirs();
    else
    {
      File parentFile = file.getParentFile();

      // If the file has no parent, then no directories need to be made
      if (parentFile == null)
        return;

      parentFile.mkdirs();
    }
  }

  /**
   * Terrible hack method to try and fix buggy file deletion.
   * 
   * @param deleteMe
   *          File to be deleted
   */
  public static void deleteFile(File deleteMe) throws IOException
  {
    // Bail out if the file doesn't exist
    if (!deleteMe.exists())
      return;

    // We're willing to try this whole cycle up to 3 times
    for (int i = 0; i < 3; i++)
    {
      // First off we try a regular delete
      if (!deleteMe.delete())
      {
        // Regular delete failed. Garbage Collect and try again
        System.gc();
        if (!deleteMe.delete())
        {
          // Still not deleted - sleep for a bit and try again
          try
          {
            Thread.sleep(100);
          }
          catch (InterruptedException e)
          {
          }

          // Return statments to cover successful deletes.
          if (deleteMe.delete())
            return;
        }
        else
          return;
      }
      else
        return;
    }

    // Still no joy, even after several tries. Just give up.
    String error = "Unable to delete file [" + deleteMe.getAbsolutePath() + "]";
    throw new IOException(error);
  }

  /**
   * Recursively copies the directory and all sub-directories into the
   * destination.
   * 
   * @param source
   *          Original directory
   * @param destination
   *          New directory (will be created)
   * 
   * @return true if the directory was copied successfully
   */
  public static boolean copyFolder(File source, File destination)
  {
    return copyFolder(source, destination, (PrintStream) null);
  }

  /**
   * Recursively copies the directory and all sub-directories into the
   * destination.
   * 
   * @param source
   *          Original directory
   * @param destination
   *          New directory (will be created)
   * @param logPrintStream
   * 
   * @return true if the directory was copied successfully
   */
  public static boolean copyFolder(File source, File destination, PrintStream logPrintStream)
  {
    for (String string : source.list())
    {
      File newSource = new File(source, string);
      File newDest = new File(destination, string);
      if (newSource.isDirectory())
      {
        copyFolder(newSource, newDest, logPrintStream);
      }
      else if (newSource.isFile())
      {
        try
        {
          copyFile(newSource, newDest, logPrintStream);
        }
        catch (IOException e)
        {
          return false;
        }
      }
      else
      {
        // Not a file or a folder... weird.
        return false;
      }
    }
    return true;
  }

  /**
   * Recursively copies a file or directory into the destination file or
   * directory. If the source is a directory then all sub-directories into the
   * are also copied.
   * 
   * @param source
   *          Original file or directory
   * @param destination
   *          New file or directory (will be created)
   * 
   */
  public static void copy(File source, File destination) throws IOException
  {
    if (source.isDirectory())
    {
      copyFolder(source, destination);
    }
    else
    {
      copyFile(source, destination);
    }
  }

  /**
   * Recursively copies the directory and all sub-directories into the
   * destination.
   * 
   * @param source
   *          Original directory
   * @param destination
   *          New directory (will be created)
   * @param filenameFilter
   * 
   * @return true if the directory was copied successfully
   */
  public static boolean copyFolder(File source, File destination, FilenameFilter filenameFilter)
  {
    return copyFolder(source, destination, filenameFilter, null);
  }

  /**
   * Recursively copies the directory and all sub-directories into the
   * destination.
   * 
   * @param source
   *          Original directory
   * @param destination
   *          New directory (will be created)
   * @param filenameFilter
   * @param logPrintStream
   * 
   * @return true if the directory was copied successfully
   */
  public static boolean copyFolder(File source, File destination, FilenameFilter filenameFilter, PrintStream logPrintStream)
  {
    for (String string : source.list(filenameFilter))
    {
      File newSource = new File(source, string);
      File newDest = new File(destination, string);
      if (newSource.isDirectory())
      {
        copyFolder(newSource, newDest, filenameFilter, logPrintStream);
      }
      else if (newSource.isFile())
      {
        try
        {
          copyFile(newSource, newDest, logPrintStream);
        }
        catch (IOException e)
        {
          return false;
        }
      }
      else
      {
        // Not a file or a folder... weird.
        return false;
      }
    }
    return true;
  }

  /**
   * Gets a full-depth list of all the folders that reside under the given root.
   * If the root is a file the folder it resides in is used as the root.
   * 
   * @param root
   * @return
   */
  public static List<File> getFolderTree(File root)
  {
    // If the file passed in isn't a directory, det the directory it's in
    LinkedList<File> files = new LinkedList<File>();
    if (!root.isDirectory())
      root = root.getParentFile();

    // Don't include svn directories
    if (root.getAbsolutePath().endsWith(".svn"))
      return files;

    // Add the root, then recursively add all the children
    files.add(root);
    for (File child : root.listFiles())
      if (child.isDirectory())
        files.addAll(getFolderTree(child));

    return files;
  }

  /**
   * Searches the classpath for the given filename, and returns the directory it
   * was found in.
   * 
   * @param fileName
   * @return
   * @throws URISyntaxException
   */
  public static File getDirectory(String fileName) throws URISyntaxException
  {
    URL url = Object.class.getResource(fileName);
    File base = new File(url.toURI());
    return base.getParentFile();
  }

  /**
   * Gets a list of all the files in the given folder, and all subfolders. If
   * the root is a file then a list containing just that file is returned.
   * 
   * @param root
   * @return
   */
  public static List<File> listFilesRecursively(File root)
  {
    FileFilter fileFilter = new FileFilter()
    {
      public boolean accept(File pathname)
      {
        return true;
      }
    };

    return listFilesRecursively(root, fileFilter);
  }

  /**
   * Gets a list of all the files in the given folder, and all subfolders. If
   * the root is a file then a list containing just that file is returned.
   * 
   * @param root
   * @param fileFilter
   * @return
   */
  public static List<File> listFilesRecursively(File root, FileFilter fileFilter)
  {
    LinkedList<File> list = new LinkedList<File>();

    // Recursion termination: if the given root is a file, return just it.
    if (!root.isDirectory())
    {
      list.add(root);
      return list;
    }

    // Exclude .svn directories
    if (root.getName().equals(".svn"))
    {
      return list;
    }

    // The root is a folder - get all of its files
    for (File child : root.listFiles(fileFilter))
      list.addAll(listFilesRecursively(child, fileFilter));
    return list;
  }

  /**
   * Copies a file
   * 
   * @param source
   *          Original file
   * @param dest
   *          New copy of the original
   * @throws IOException
   */
  public static void copyFile(File source, File dest) throws IOException
  {
    copyFile(source, dest, null);
  }

  /**
   * Copies a file
   * 
   * @param source
   *          Original file
   * @param dest
   *          New copy of the original
   * @param logPrintStream
   * @throws IOException
   */
  public static void copyFile(File source, File dest, PrintStream logPrintStream) throws IOException
  {
    byte[] bytes = readBytes(source);
    write(dest, bytes, logPrintStream);
  }

  /**
   * Recursivley deletes all the files, sub-directories, and sub-files of a
   * given directory
   * 
   * @param directory
   *          The directory
   */
  public static void deleteDirectory(File directory) throws IOException
  {
    if (directory.isDirectory())
    {
      File[] files = directory.listFiles();

      if (files != null)
      {
        for (File file : files)
        {
          deleteDirectory(file);
        }
      }
    }

    FileIO.deleteFile(directory);
  }

  public static void deleteFolderContent(File directory, FileFilter filter) throws IOException
  {
    if (directory.isDirectory())
    {
      File[] files = directory.listFiles(filter);

      if (files != null)
      {
        for (File file : files)
        {
          deleteDirectory(file);
        }
      }
    }
  }

  public static void write(ZipInputStream iStream, File destination) throws IOException
  {
    try
    {
      ZipEntry entry;

      while ( ( entry = iStream.getNextEntry() ) != null)
      {
        String path = destination.getAbsolutePath() + File.separator + entry.getName();

        if (entry.isDirectory())
        {
          File directory = new File(path);
          directory.mkdir();
        }
        else
        {
          int size;
          byte[] buffer = new byte[BUFFER];
          BufferedOutputStream oStream = new BufferedOutputStream(new FileOutputStream(path));

          try
          {
            while ( ( size = iStream.read(buffer, 0, buffer.length) ) != -1)
            {
              oStream.write(buffer, 0, size);
            }

            oStream.flush();
          }
          finally
          {
            oStream.close();
          }
        }
      }
    }
    finally
    {
      iStream.close();
    }
  }

}
