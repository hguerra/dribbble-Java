package br.com.geoambiente.domain;

import br.com.geoambiente.util.ZonedDateTimeDeserializer;
import br.com.geoambiente.util.ZonedDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty("application_id")
    private Long pkey;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    @JsonProperty("added_at")
    @Column
    private ZonedDateTime addedAt;

    @PrePersist
    protected void onCreate() {
        this.addedAt = ZonedDateTime.now(ZoneOffset.UTC);
    }
}
