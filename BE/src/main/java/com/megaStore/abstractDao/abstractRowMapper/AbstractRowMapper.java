package com.megaStore.abstractDao.abstractRowMapper;

import com.megaStore.abstractDao.ColoumnCheck;
import org.springframework.jdbc.core.RowMapper;

public abstract class AbstractRowMapper<T> extends ColoumnCheck implements RowMapper<T> {
}
