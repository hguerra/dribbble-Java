package br.com.geoambiente.domain.impl;

import br.com.geoambiente.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Author extends AbstractEntity {

    @JsonProperty("id")
    @NotNull(message = "Argument 'dribbbleId' is mandatory.")
    @Column(nullable = false, unique = false)
    private Long dribbbleId;

    @NotNull(message = "Argument 'name' is mandatory.")
    @Column(nullable = false, unique = false)
    private String name;


    @NotNull(message = "Argument 'username' is mandatory.")
    @Column(nullable = false, unique = false)
    private String username;
}
