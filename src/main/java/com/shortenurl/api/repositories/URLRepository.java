package com.shortenurl.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shortenurl.api.domains.*;

import java.math.BigDecimal;

/**
 * URLRepository class.
 * 
 * @author devetude
 */
@Repository
public interface URLRepository extends JpaRepository<URL, BigDecimal> {
}