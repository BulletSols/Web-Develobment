package com.megaStore.abstractDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColoumnCheck {

    protected static boolean hasColoumn(ResultSet resultSet, String coloumnName) throws SQLException {
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            if (resultSet.getMetaData().getColumnLabel(i).equalsIgnoreCase(coloumnName))
                return true;
        }

        return false;
    }
}
