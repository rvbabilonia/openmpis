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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The Configuration class provides the method to read properties.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 */
public class Configuration {

    /**
     * The properties
     */
    private Properties properties = new Properties();

    /**
     * Constructor for Configuration.
     * 
     * @param   filename    the properties file
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public Configuration(String filename)
            throws FileNotFoundException, IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        properties.load(inputStream);
        inputStream.close();
    }

    /**
     * Gets the value of the property.
     * 
     * @param   property    the key of the property
     * @return              the value of the property
     */
    public String getProperty(String property) {
        return properties.getProperty(property);
    }

    /**
     * Gets all the properties.
     * 
     * @return              the properties
     */
    public Properties getProperties() {
        return properties;
    }
}