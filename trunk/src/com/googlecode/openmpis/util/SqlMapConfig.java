/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.googlecode.openmpis.util;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * The SqlMapConfig class is a convenience class that instantiates a long-lived,
 * thread safe service object called SqlMapClient.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class SqlMapConfig {

    /**
     * The SQL map
     */
    private static final SqlMapClient sqlMap;
    
    /**
     * Creates the SQL map.
     */
    static {
        try {
            String resource = "com/googlecode/openmpis/persistence/ibatis/sqlmapconfig.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing SqlMapConfig class. Cause: " + e);
        }
    }

    /**
     * Returns the SQL map.
     * 
     * @return      the SQL map
     */
    public static SqlMapClient getSqlMapInstance() {
        return sqlMap;
    }
}