package br.com.geoambiente.domain.impl;

import br.com.geoambiente.domain.AbstractEntity;
import br.com.geoambiente.util.ZonedDateTimeSerializer;
import br.com.geoambiente.util.ZonedDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class Screenshot extends AbstractEntity {

    @JsonProperty("id")
    @NotNull(message = "Argument 'dribbbleId' is mandatory.")
    @Column(nullable = false, unique = false)
    private Long dribbbleId;

    @Column
    private String title;

    @JsonProperty("user")
    @OneToOne(cascade = CascadeType.ALL)
    private Author author;

    @OneToOne(cascade = CascadeType.ALL)
    private Image images;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    @JsonProperty("created_at")
    @Column
    private ZonedDateTime createdAt;

    @JsonProperty("views_count")
    @Column
    private Integer viewsCount;

    @JsonProperty("likes_count")
    @Column
    private Integer likesCount;

    @JsonProperty("comments_count")
    @Column
    private Integer commentsCount;

    @JsonProperty("buckets_count")
    @Column
    private Integer bucketsCount;

    @Override
    public String toString() {
        try {
            return new ObjectMapper()
                    .writer()
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return super.toString();
        }
    }
}
