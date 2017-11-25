package com.shortenurl.api.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortenurl.api.domains.URL;
import com.shortenurl.api.repositories.URLRepository;
import com.shortenurl.api.services.interfaces.URLServiceInterface;
import com.shortenurl.api.util.exceptions.BadRequestException;
import com.shortenurl.api.util.resources.Strings;
import com.shortenurl.api.util.url.URLUtil;

/**
 * URLService class.
 * 
 * @author devetude
 */
@Service
public class URLService implements URLServiceInterface {
	private static final String TAG = URLService.class.getSimpleName();

	@Autowired
	private URLRepository urlRepository;

	@Override
	public URL save(URL url) {
		return urlRepository.save(url);
	}

	@Override
	public URL findURL(BigDecimal idx) {
		URL url = urlRepository.findOne(idx);

		if (url == null) {
			throw new BadRequestException(Strings.NOT_FIND_ORIGINAL_URL);
		}

		return url;
	}

	@Override
	public BigDecimal decode(String encodedIdx) {
		if (!URLUtil.validateEncodedIdx(encodedIdx)) {
			throw new BadRequestException(Strings.IS_NOT_VALID_ENCODED_IDX);
		}

		return URLUtil.decode(encodedIdx);
	}
}