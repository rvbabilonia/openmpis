/*
 * This file is part of OpenMPIS.
 *
 * Copyright (c) 2019 VincenzoLabs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.vincenzolabs.openmpis.representation;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The data transfer object for street address.
 *
 * @author Rey Vincent Babilonia
 */
public class StreetAddressJson {

    private String streetNumber;

    private String suburb;

    private String city;

    private String province;

    private String country;

    /**
     * Returns the street number.
     *
     * @return the street number
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets the street number.
     *
     * @param streetNumber the street number
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * Returns the suburb.
     *
     * @return the suburb
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * Sets the suburb.
     *
     * @param suburb the suburb
     */
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    /**
     * Returns the city or town.
     *
     * @return the city or town
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city or town.
     *
     * @param city the city or town
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the province or state.
     *
     * @return the province or state
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the province or state.
     *
     * @param province the province or state
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Returns the country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StreetAddressJson that = (StreetAddressJson) o;

        return new EqualsBuilder()
            .append(streetNumber, that.streetNumber)
            .append(suburb, that.suburb)
            .append(city, that.city)
            .append(province, that.province)
            .append(country, that.country)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(streetNumber)
            .append(suburb)
            .append(city)
            .append(province)
            .append(country)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("streetNumber", streetNumber)
            .append("suburb", suburb)
            .append("city", city)
            .append("province", province)
            .append("country", country)
            .toString();
    }
}
