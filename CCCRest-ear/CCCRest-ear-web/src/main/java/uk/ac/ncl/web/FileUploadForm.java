/*
 * 
 */
package uk.ac.ncl.web;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 * The Class FileUploadForm.
 *
 */
public class FileUploadForm {

	public FileUploadForm() {
	}
	

	private byte[] data;

	/**
	 * Gets the data.
	 * @return  the data
	 *
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * @param data  the new data
	 *
	 */
	@FormParam("file")
	public void setData(byte[] data) {
		this.data = data;
	}

	
}