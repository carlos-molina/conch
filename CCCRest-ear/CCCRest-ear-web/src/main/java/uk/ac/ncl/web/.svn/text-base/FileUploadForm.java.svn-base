/*
 * 
 */
package uk.ac.ncl.web;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 * The Class FileUploadForm.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 5 $$, $$Date: 2012-09-05 23:33:00 +0100 (Wed, 05 Sep 2012) $$
 */
public class FileUploadForm {

	public FileUploadForm() {
	}
	
	/**
	 * @uml.property  name="data"
	 */
	private byte[] data;

	/**
	 * Gets the data.
	 * @return  the data
	 * @uml.property  name="data"
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * @param data  the new data
	 * @uml.property  name="data"
	 */
	@FormParam("file")
	public void setData(byte[] data) {
		this.data = data;
	}

	
}