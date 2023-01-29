package org.changsol.api.apps.samples.domain;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.changsol.api.utils.entity.BaseEntityLongId;

/**
 * @author ChangSol
 * @version 0.0.1
 * @see SampleMaster
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SampleMaster extends BaseEntityLongId {
    private String masterName;
}
