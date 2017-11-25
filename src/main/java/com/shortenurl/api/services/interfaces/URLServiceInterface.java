package com.shortenurl.api.services.interfaces;

import com.shortenurl.api.domains.URL;

import java.math.BigDecimal;

/**
 * URLServiceInterface interface.
 * 
 * @author devetude
 */
public interface URLServiceInterface {
	URL save(URL url);

	URL findURL(BigDecimal idx);

	BigDecimal decode(String encodedIdx);
}