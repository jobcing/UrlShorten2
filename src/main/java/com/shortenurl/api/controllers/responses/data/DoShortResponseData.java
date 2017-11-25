package com.shortenurl.api.controllers.responses.data;

/**
 * DoshortResponseData class.
 * 
 * @author devetude, iljun
 */
public class DoShortResponseData {
	private static final String TAG = DoShortResponseData.class.getSimpleName();

	private String encodedIdx;

	public DoShortResponseData(String encodedIdx) {
		this.encodedIdx = encodedIdx;
	}

	public DoShortResponseData() {
	}

	public String getEncodedIdx() {
		return encodedIdx;
	}

	public void setEncodedIdx(String encodedIdx) {
		this.encodedIdx = encodedIdx;
	}
}