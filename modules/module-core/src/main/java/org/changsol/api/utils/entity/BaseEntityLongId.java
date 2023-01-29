package org.changsol.api.utils.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass //자식에게 매핑정보만 제공
public class BaseEntityLongId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
}
