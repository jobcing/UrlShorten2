package com.shortenurl.api.util.url;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.shortenurl.api.controllers.responses.DefaultResponse;
import com.shortenurl.api.util.exceptions.BadRequestException;
import com.shortenurl.api.util.resources.Strings;

/**
 * URLUtil class.
 *
 * @author devetude, iljun
 */
public class URLUtil {
	private static final String TAG = URLUtil.class.getSimpleName();

	private static final char[] TABLE = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };
	private static final BigDecimal LENGTH = new BigDecimal(TABLE.length);

	private static final int QUOTIENT = 0;
	private static final int REMAINDER = 1;

	private static final String URL_PATTERN = "^(https?:\\/\\/)?((([a-z\\d](([a-z\\d-]*[a-z\\d]))|([ㄱ-힣])*)\\.)+(([a-zㄱ-힣]{2,}))|((\\d{1,3}\\.){3}\\d{1,3}))(\\:\\d+)?(\\/[-a-z\\d%_.+]*)*(\\?[;&a-z\\d%_~.~+=-]*)?(\\#[-a-z\\d_]*)?$";
	private static final Pattern PATTERN = Pattern.compile(URL_PATTERN);

	private static final int MAX_URL_LENGTH = 2_048;

	public static boolean validateURL(String url) {
		if (url.length() > MAX_URL_LENGTH) {
			return false;
		}

		String lowerCaseURL = url.toLowerCase();

		if (lowerCaseURL.contains("<script>") || lowerCaseURL.contains("</script>")) {
			return false;
		}

		String domain = url;
		int firstQuesitionIdx = url.indexOf("?");

		if (firstQuesitionIdx != -1) {
			domain = url.substring(0, firstQuesitionIdx);
			int firstSharpIdx = url.indexOf("#");

			if (firstSharpIdx != -1) {
				domain = url.substring(0, firstSharpIdx);
			}
		}

		return PATTERN.matcher(domain).matches();
	}

	public static String encode(BigDecimal idx) {
		Stack<Character> stack = new Stack<>();
		int val = idx.intValue();

		if (val <= TABLE.length) {
			return String.valueOf(TABLE[val]);
		}

		BigDecimal quotient = idx;
		BigDecimal[] bigDecimals;

		do {
			bigDecimals = quotient.divideAndRemainder(LENGTH);
			stack.push(TABLE[bigDecimals[REMAINDER].intValue()]);
			quotient = bigDecimals[QUOTIENT];
		} while (bigDecimals[QUOTIENT].compareTo(BigDecimal.ZERO) > 0);

		StringBuilder builder = new StringBuilder();

		while (!stack.isEmpty()) {
			builder.append(stack.pop());
		}

		return builder.toString();
	}

	public static BigDecimal decode(String encodedIdx) {
		char[] chars = encodedIdx.toCharArray();
		BigDecimal idx = new BigDecimal(0);
		int j = 0;

		for (int i = chars.length - 1; i >= 0; i--) {
			char c = chars[i];

			if ('a' <= c && c <= 'z') {
				idx = idx.add(new BigDecimal(c - 87).multiply(LENGTH.pow(j++)));
			}

			else if ('A' <= c && c <= 'Z') {
				idx = idx.add(new BigDecimal(c - 29).multiply(LENGTH.pow(j++)));
			}

			else if ('0' <= c && c <= '9') {
				idx = idx.add(new BigDecimal(c - 48).multiply(LENGTH.pow(j++)));
			}
		}

		return idx;
	}

	public static boolean validateEncodedIdx(String encodedIdx) {
		for (char c : encodedIdx.toCharArray()) {
			if (!('a' <= c && c <= 'z') && !('A' <= c && c <= 'Z') && !('0' <= c && c <= '9')) {
				return false;
			}
		}

		return true;
	}
}