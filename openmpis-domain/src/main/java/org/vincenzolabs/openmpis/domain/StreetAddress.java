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
package org.vincenzolabs.openmpis.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The domain model object for street address.
 *
 * @author Rey Vincent Babilonia
 */
public final class StreetAddress {

    private final String streetNumber;

    private final String suburb;

    private final String city;

    private final String province;

    private final String country;

    /**
     * Private constructor.
     *
     * @param builder the {@link Builder}
     */
    private StreetAddress(Builder builder) {
        this.streetNumber = builder.streetNumber;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.province = builder.province;
        this.country = builder.country;
    }

    /**
     * Returns the street number.
     *
     * @return the street number
     */
    public String getStreetNumber() {
        return streetNumber;
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
     * Returns the city or town.
     *
     * @return the city or town
     */
    public String getCity() {
        return city;
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
     * Returns the country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Returns a {@link Builder}.
     *
     * @return the {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StreetAddress that = (StreetAddress) o;

        return new EqualsBuilder()
            .append(streetNumber, that.streetNumber)
            .append(suburb, that.suburb)
            .append(city, that.city)
            .append(province, that.province)
            .append(country, that.country)
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
            .append("city", city)
            .append("province", province)
            .append("country", country)
            .toString();
    }

    /**
     * The builder.
     */
    public static class Builder {

        private String streetNumber;

        private String suburb;

        private String city;

        private String province;

        private String country;

        /**
         * Private constructor.
         */
        private Builder() {
            // prevent instantiation
        }

        /**
         * Sets the streetNumber.
         *
         * @param streetNumber the streetNumber
         * @return the {@link Builder}
         */
        public Builder withStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        /**
         * Sets the suburb.
         *
         * @param suburb the suburb
         * @return the {@link Builder}
         */
        public Builder withSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        /**
         * Sets the city or town.
         *
         * @param city the city or town
         * @return the {@link Builder}
         */
        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        /**
         * Sets the province or state.
         *
         * @param province the province or state
         * @return the {@link Builder}
         */
        public Builder withProvince(String province) {
            this.province = province;
            return this;
        }

        /**
         * Sets the country.
         *
         * @param country the country
         * @return the {@link Builder}
         */
        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        /**
         * Builds a {@link StreetAddress}.
         *
         * @return the {@link StreetAddress}
         */
        public StreetAddress build() {
            return new StreetAddress(this);
        }
    }
}
