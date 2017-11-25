package com.shortenurl.api.controllers.forms;

/**
 * DoShortForm class.
 * 
 * @author devetude, iljun
 */
public class DoShortForm {
	private static final String TAG = DoShortForm.class.getSimpleName();

	private String originalURL;

	public DoShortForm() {
	}

	public DoShortForm(String originalURL) {
		this.originalURL = originalURL;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	public static String getTag() {
		return TAG;
	}
}