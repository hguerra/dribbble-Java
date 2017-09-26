package br.com.geoambiente.domain.impl;

import br.com.geoambiente.domain.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Image extends AbstractEntity {
    @Column
    private String teaser;

    @Column
    private String normal;

    @Column
    private String hidpi;
}
