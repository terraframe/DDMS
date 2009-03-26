package dss.vector.solutions.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ibm.icu.text.SimpleDateFormat;
import com.terraframe.mojo.business.ViewDTO;
import com.terraframe.mojo.constants.ClientRequestIF;
import com.terraframe.mojo.transport.metadata.AttributeBooleanMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeCharacterMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeDateMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeEnumerationMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeIntegerMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeMdDTO;
import com.terraframe.mojo.transport.metadata.AttributeReferenceMdDTO;

import dss.vector.solutions.entomology.assay.AssayTestResultDTO;
import dss.vector.solutions.mo.AbstractTermDTO;
import dss.vector.solutions.mo.SpecieDTO;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.mail.Authenticator;  
import javax.mail.PasswordAuthentication;  


import java.util.Properties;

public class Halp implements com.terraframe.mojo.generation.loader.Reloadable {

	public final static String CLASS = "dss.vector.solutions.util.Halp";

	public static String join(List<String> s, String delimiter) {
		StringBuilder builder = new StringBuilder();
		Iterator<String> iter = s.iterator();

		while (iter.hasNext()) {
			builder.append(iter.next());
			if (iter.hasNext()) {
				builder.append(delimiter);
			}
		}
		return builder.toString();
	}

	public static String getDropDownMap(AbstractTermDTO[] terms) throws JSONException {
		JSONObject map = new JSONObject();
		for (AbstractTermDTO term : terms) {
			map.put(term.getDisplayLabel(), term.getId());
		}
		return map.toString();
	}

	public static String getDropDownMap2(List<SpecieDTO> terms) throws JSONException {
		JSONObject map = new JSONObject();
		for (SpecieDTO term : terms) {
			map.put(term.getDisplayLabel(), term.getId());
		}
		return map.toString();
	}

	public static String getDisplayLabels(AbstractTermDTO[] terms, String name) throws JSONException {
		JSONArray ids = new JSONArray();
		JSONArray labels = new JSONArray();
		for (AbstractTermDTO term : terms) {
			ids.put(term.getId());
			labels.put(term.getDisplayLabel());
		}
		return name + "Ids = " + ids.toString() + "; \n " + name + "Labels = " + labels.toString() + ";";
	}

	public static String getDataMap(ViewDTO[] rows, String[] attribs, ViewDTO view) throws JSONException {
		JSONArray map = new JSONArray();
		ArrayList<String> ordered_attribs = new ArrayList<String>(Arrays.asList(attribs));
		for (String a : view.getAccessorNames()) {
			if (!ordered_attribs.contains(a)) {
				ordered_attribs.add(a.substring(0, 1).toUpperCase() + a.substring(1));
			}
		}
		System.out.println("attribs length =  " + ordered_attribs.size());
		for (ViewDTO row : rows) {
			JSONObject element = new JSONObject();
			Class<?> c = row.getClass();

			for (String attrib : ordered_attribs) {
				try {
					// System.out.println("Setting "+attrib);
					String value = (String) c.getMethod("get" + attrib).invoke(row).toString();
					// System.out.println("Setting "+attrib+" to "+value);

					if (attrib.contains("Date")) {
						SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
						element.put(attrib, df.format(new Date(value)));
					} else {
						// FIXME: this is a hack for enums
						String clean_value = value.replaceAll("\\[", "").replaceAll("\\]", "");
						element.put(attrib, clean_value);
					}

				} catch (IllegalAccessException x) {
					System.out.println(x + " " + x.getCause());
				} catch (IllegalArgumentException x) {
					System.out.println(x + " " + x.getCause());
				} catch (InvocationTargetException x) {
					System.out.println(x + " " + x.getCause());
				} catch (NoSuchMethodException x) {
					System.out.println("No such method get" + attrib);
				} catch (NullPointerException x) {
					System.out.println("Null Pointer Exception get" + attrib);
				}

			}
			map.put(element);
		}
		return map.toString().replaceAll(",", ",\n");
	}

	public static String getDropdownSetup(ViewDTO view, String[] attribs, String extra_rows, ClientRequestIF clientRequest) throws JSONException {
		ArrayList<String> arr = new ArrayList<String>();
		int colnum = 0;
		Class<?> v = view.getClass();
		// List<String> v_attribs = view.getAttributeNames();
		ArrayList<String> ordered_attribs = new ArrayList<String>(Arrays.asList(attribs));
		for (String a : view.getAccessorNames()) {
			if (!ordered_attribs.contains(a) && a.length() >= 3) {
				ordered_attribs.add(a.substring(0, 1).toUpperCase() + a.substring(1));
			}
		}

		ArrayList<String> dropdownbuff = new ArrayList<String>();
		for (String attrib : ordered_attribs) {
			try {
				AttributeMdDTO md = (AttributeMdDTO) v.getMethod("get" + attrib + "Md").invoke(view);

				if (md instanceof AttributeReferenceMdDTO) {
					Class<?> mo_term = md.getJavaType();
					if (AbstractTermDTO.class.isAssignableFrom(mo_term)) {
						AbstractTermDTO[] terms = (AbstractTermDTO[]) mo_term.getMethod("getAll", new Class[] { ClientRequestIF.class }).invoke(null, clientRequest);
						dropdownbuff.add(getDisplayLabels(terms, attrib));
					}
				}

			} catch (Exception x) {
				System.out.println("Other exception on " + attrib + " " + x.getMessage());
			}
			colnum++;
		}
		if (extra_rows.length() > 0) {
			arr.add(extra_rows);
		}
		return (Halp.join(dropdownbuff, "\n"));
	}
	public static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows, boolean autoload)throws JSONException
	{
		return getColumnSetup(view,attribs,extra_rows,autoload,1);
	}
	
	public static String getColumnSetup(ViewDTO view, String[] attribs, String extra_rows, boolean autoload, int num_to_hide) throws JSONException {
		ArrayList<String> arr = new ArrayList<String>();
		int colnum = 0;
		Class<?> v = view.getClass();
		// List<String> v_attribs = view.getAttributeNames();
		ArrayList<String> ordered_attribs = new ArrayList<String>(Arrays.asList(attribs));
		for (String a : view.getAccessorNames()) {
			String upcased_attrib = a.substring(0, 1).toUpperCase() + a.substring(1);
			if (!ordered_attribs.contains(upcased_attrib) && a.length() >= 3 && autoload) {
				ordered_attribs.add(upcased_attrib);
			}
		}

		for (String attrib : ordered_attribs) {
			try {
				ArrayList<String> buff = new ArrayList<String>();

				buff.add("key:'" + attrib + "'");

				AttributeMdDTO md = (AttributeMdDTO) v.getMethod("get" + attrib + "Md").invoke(view);
				Class<?> mdClass = md.getClass();
				// buff.add("class:"+mdClass.toString());
				String label = (String) mdClass.getMethod("getDisplayLabel").invoke(md).toString();
				buff.add("label:'" + label + "'");
				if (colnum < num_to_hide) {
					buff.add("hidden:true");
				} else {
					if (!Arrays.asList(attribs).contains(attrib)) {
						buff.add("hidden:true");
					}
					String editor = "null";

					if (md instanceof AttributeIntegerMdDTO) {
						editor = "new YAHOO.widget.TextboxCellEditor({validator:YAHOO.widget.DataTable.validateNumber,disableBtns:true})";
					}
					if (md instanceof AttributeBooleanMdDTO) {
						editor = "new YAHOO.widget.CheckboxCellEditor({checkboxOptions:['true','false'],disableBtns:true})";
					}
					if (md instanceof AttributeCharacterMdDTO) {
						editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
					}
					if (md instanceof AttributeDateMdDTO) {
						buff.add("formatter:YAHOO.widget.DataTable.formatDate");
						// editor = "new YAHOO.widget.DateCellEditor({disableBtns:true})";
						 editor = "new YAHOO.widget.DateCellEditor({calendar:MojoCal.init(),disableBtns:true})";
						// editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
					}
					if (md instanceof AttributeEnumerationMdDTO) {
						editor = "new YAHOO.widget.RadioCellEditor({radioOptions:['";
						editor += Halp.join(((AttributeEnumerationMdDTO) md).getEnumNames(), "','");
						editor += "'],disableBtns:true})";
					}
					if (md instanceof AttributeReferenceMdDTO) {
						Class<?> refrenced_class = md.getJavaType();

						if (AssayTestResultDTO.class.isAssignableFrom(refrenced_class)) {
							editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
						}

						if (AbstractTermDTO.class.isAssignableFrom(refrenced_class)) {
							editor = "new YAHOO.widget.DropdownCellEditor({dropdownOptions:" + attrib + "Labels,disableBtns:true})";
							buff.add("save_as_id:true");
						} else {
							editor = "new YAHOO.widget.TextboxCellEditor({disableBtns:true})";
						}

					}
					buff.add("editor:" + editor);
				}
				arr.add("{" + Halp.join(buff, ",") + "}");
			} catch (IllegalAccessException x) {
				System.out.println("IllegalAccessException on " + attrib + " " + x.getMessage());
			} catch (IllegalArgumentException x) {
				System.out.println("IllegalArgumentException on " + attrib + " " + x.getMessage());
			} catch (InvocationTargetException x) {
				System.out.println("InvocationTargetException on " + attrib + " " + x.getMessage());
			} catch (NoSuchMethodException x) {
				System.out.println("No such method on " + attrib + x.getMessage());
			} catch (Exception x) {
				System.out.println("Other exception on " + attrib + " " + x.getMessage());
			}
			colnum++;
		}
		if (extra_rows.length() > 0) {
			arr.add(extra_rows);
		}
		return ("[" + Halp.join(arr, ",\n") + "]");
	}

	public static void sendErrorMail(Throwable exception, HttpServletRequest request) {
		String from = "MDSSS-no-reply@terraframe.com";
		String to = "dtaylor@terraframe.com";
		String subject = "MDSS has produced an uncaught exception";
		String text = "Requested url: ";
		text += request.getAttribute("javax.servlet.forward.request_uri") + "\n\n";
		text += "Error in class: ";
		text += exception.getClass().getName() + "\n\n";
		text += exception.getLocalizedMessage() + "\n\n";
		text += request.getQueryString() + "\n\n";
		final Writer result = new StringWriter();
	    final PrintWriter printWriter = new PrintWriter(result);
	    exception.printStackTrace(printWriter);
		text += result.toString() + "\n\n";
		
		//
		// A properties to store mail server smtp information such as the host
		// name and the port number. With this properties we create a Session
		// object from which we'll create the Message object.
		//
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "terraframe.com");
		properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", true);
		Session session = Session.getDefaultInstance(properties, null);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			// Send the message to the recipient.
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	class MyAuth extends Authenticator
	{
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("dtaylor", "Rc9hs8Z2");
		}
	}

}
