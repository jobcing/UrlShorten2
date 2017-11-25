package com.shortenurl.api.domains;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * URL class.
 * 
 * @author devetude
 */
@Entity
public class URL {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private BigDecimal idx;

	@Column(name = "originalURL", columnDefinition = "TEXT")
	private String originalURL;

	public URL() {
	}

	public URL(String originalURL) {
		this.originalURL = originalURL;
	}

	public URL(BigDecimal idx, String originalURL) {
		this.idx = idx;
		this.originalURL = originalURL;
	}

	public BigDecimal getIdx() {
		return idx;
	}

	public void setIdx(BigDecimal idx) {
		this.idx = idx;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}
}