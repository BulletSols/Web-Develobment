package com.megaStore.abstractDao.abstractResultSetExtractor;

import com.megaStore.abstractDao.ColoumnCheck;
import org.springframework.jdbc.core.ResultSetExtractor;

public abstract class AbstractResultSetExtractor<T> extends ColoumnCheck implements ResultSetExtractor<T> {
}
