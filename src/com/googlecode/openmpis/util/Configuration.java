/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.googlecode.openmpis.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * The Configuration class provides the methods to read properties.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class Configuration {

    /**
     * The properties
     */
    private Properties properties = new Properties();

    /**
     * Creates a new configuration file reader.
     * 
     * @param filename      the properties file
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
     * Gets the value of the specified property.
     * 
     * @param property      the key of the property
     * @return              the value of the property
     */
    public String getProperty(String property) {
        return properties.getProperty(property);
    }

    /**
     * Gets all the properties from the configuration file.
     * 
     * @return              the properties from the configuration file
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Returns a String representation of this object.
     * 
     * @return              a string representation of this object
     */
    @Override
    public String toString() {
        String content = "";
        Enumeration keys = properties.keys();
        Enumeration values = properties.elements();
        while (keys.hasMoreElements()) {
            content += "\n" + keys.nextElement().toString() + "=" + values.nextElement().toString();
        }
        return content;
    }
}