package org.changsol.api.utils.jpas.restriction;

import javax.persistence.criteria.JoinType;

/**
 * JPA Restriction Join Record Class
 */
record ChangSolJpaJoin(String columnName, JoinType joinType) {

}
