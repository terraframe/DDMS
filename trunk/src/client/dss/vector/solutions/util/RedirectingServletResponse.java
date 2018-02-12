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
package dss.vector.solutions.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;



/**
 * @author Darrell Taylor
 *
 * This class is used to render a jsp to a string for inside out rendering.
 *
 */
public class RedirectingServletResponse extends HttpServletResponseWrapper {

    RedirectServletStream out;
    HttpServletResponse response;
    PrintWriter printWriter;
    OutputStreamWriter osw;

    public RedirectingServletResponse(HttpServletResponse response, OutputStream out) {
        super(response);
        this.response = response;
        this.out = new RedirectServletStream(out);
        try {
            this.osw = new OutputStreamWriter(out,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.printWriter = new PrintWriter(osw,true);
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#flushBuffer()
     */
    public void flushBuffer() throws IOException {
        printWriter.flush();
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getOutputStream()
     */
    public ServletOutputStream getOutputStream() throws IOException {
        return out;
    }

    /* (non-Javadoc)
     * @see javax.servlet.ServletResponse#getWriter()
     */
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }


    /* (non-Javadoc)
	 * @see javax.servlet.ServletResponseWrapper#getResponse()
	 */
	public ServletResponse getResponse() {
		return super.getResponse();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletResponseWrapper#setResponse(javax.servlet.ServletResponse)
	 */
	public void setResponse(ServletResponse arg0) {
		super.setResponse(arg0);
	}



	private static class RedirectServletStream extends ServletOutputStream {
	    OutputStream out;

        RedirectServletStream(OutputStream out) {
            this.out = out;
        }

        //public void write(char[] c, int off, int len)
        //{
         //   out.write(c,off,len);
       // }

        public void write(int param) throws java.io.IOException {
            out.write(param);
        }

        @Override
        public boolean isReady()
        {
          return true;
        }

        @Override
        public void setWriteListener(WriteListener arg0)
        {
          // ??
        }
    }

}
