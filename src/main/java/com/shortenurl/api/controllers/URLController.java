package com.shortenurl.api.controllers;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortenurl.api.controllers.forms.DoShortForm;
import com.shortenurl.api.controllers.responses.DefaultResponse;
import com.shortenurl.api.controllers.responses.data.DoShortResponseData;
import com.shortenurl.api.domains.URL;
import com.shortenurl.api.services.URLService;
import com.shortenurl.api.util.exceptions.BadRequestException;
import com.shortenurl.api.util.resources.Strings;
import com.shortenurl.api.util.url.URLUtil;

import io.swagger.annotations.Api;

/**
 * RestController class.
 * 
 * @author devetude, iljun
 */
@RestController
@Api
public class URLController {
	private static final String TAG = URLController.class.getSimpleName();

	@Autowired
	private URLService urlService;

	@Autowired
	private HttpServletResponse httpServletResponse;

	@PostMapping("/doShort")
	public @ResponseBody ResponseEntity<DefaultResponse> doShort(@RequestBody DoShortForm doShortForm) {
		String originalURL = doShortForm.getOriginalURL();

		if (!URLUtil.validateURL(originalURL)) {
			throw new BadRequestException(Strings.IS_NOT_VALID_URL);
		}

		BigDecimal idx = urlService.save(new URL(originalURL)).getIdx();
		String shortenURL = URLUtil.encode(idx);
		DoShortResponseData doShortResponseData = new DoShortResponseData(shortenURL);
		DefaultResponse defaultResponse = new DefaultResponse(doShortResponseData);

		return new ResponseEntity<DefaultResponse>(defaultResponse, HttpStatus.OK);
	}

	@GetMapping("/{encodedIdx}")
	public void redirect(@PathVariable String encodedIdx) throws IOException {
		BigDecimal idx = urlService.decode(encodedIdx);
		URL url = urlService.findURL(idx);

		httpServletResponse.sendRedirect(url.getOriginalURL());
	}
}