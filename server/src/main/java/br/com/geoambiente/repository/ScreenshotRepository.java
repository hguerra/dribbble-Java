package br.com.geoambiente.repository;

import br.com.geoambiente.domain.impl.Screenshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface ScreenshotRepository extends JpaRepository<Screenshot, Long> {
    @Query("select s from Screenshot s order by s.addedAt desc")
    List<Screenshot> findAllOrderByAddedAtDesc();

    List<Screenshot> findByAddedAtGreaterThan(ZonedDateTime date);
}
