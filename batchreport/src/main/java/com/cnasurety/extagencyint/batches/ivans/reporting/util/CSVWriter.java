package com.cnasurety.extagencyint.batches.ivans.reporting.util;

import java.io.*;
import java.util.List;


public class CSVWriter implements Closeable, Flushable {

   public static final int INITIAL_STRING_SIZE = 1024;
   public static final char DEFAULT_ESCAPE_CHARACTER = '"';
   public static final String DEFAULT_LINE_END = "\n";
   public static final String RFC4180_LINE_END = "\r\n";

   protected final Writer writer;
   protected final char separator;
   protected final char escapechar;
   protected String lineEnd;
   protected volatile IOException exception;

   

  
   public CSVWriter(Writer writer, char separator, char escapechar, String lineEnd) {
      this.writer = writer;
      this.separator = separator;
      this.escapechar = escapechar;
      this.lineEnd = lineEnd;
   }

   public void writeAll(Iterable<String[]> allLines) {
	      StringBuilder sb = new StringBuilder(INITIAL_STRING_SIZE);
	      try {
	         for (String[] line : allLines) {
	            writeNext(line, true, sb);
	            sb.setLength(0);
	         }
	      } catch (IOException e){
	         exception = e;
	      }
	   }

   public void writeAll(List<String[]> allLines, boolean applyQuotesToAll) {
      writeAll((Iterable<String[]>)allLines, applyQuotesToAll);
   }
   

   public void writeAll(Iterable<String[]> allLines, boolean applyQuotesToAll) {
      StringBuilder sb = new StringBuilder(INITIAL_STRING_SIZE);
      try {
         for (String[] line : allLines) {
            writeNext(line, applyQuotesToAll, sb);
            sb.setLength(0);
         }
      } catch (IOException e){
         exception = e;
      }
   }
   

   protected void writeNext(String[] nextLine, boolean applyQuotesToAll, Appendable appendable) throws IOException {
      if (nextLine == null) {
         return;
      }

      for (int i = 0; i < nextLine.length; i++) {
         if (i != 0) {
            appendable.append(separator);
         }
         String nextElement = nextLine[i];
         if (nextElement == null) {
            continue;
         }
            appendable.append(nextElement); 
      }
      appendable.append(lineEnd);
      writer.write(appendable.toString());
   }


   
   @Override
   public void flush() throws IOException {
      writer.flush();
   }

   
   @Override
   public void close() throws IOException {
      flush();
      writer.close();
   }

   
   public boolean checkError() {

      if (writer instanceof PrintWriter) {
         PrintWriter pw = (PrintWriter) writer;
         return pw.checkError();
      }

      flushQuietly();  // checkError in the PrintWriter class flushes the buffer so we shall too.
      return exception != null;
   }


   
   public void flushQuietly() {
      try {
         flush();
      } catch (IOException e) {
         // catch exception and ignore.
      }
   }
}
