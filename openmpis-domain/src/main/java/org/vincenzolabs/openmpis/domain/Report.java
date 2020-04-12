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

import java.time.OffsetDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The domain model object for investigation report.
 *
 * @author Rey Vincent Babilonia
 */
public final class Report {

    private final String uuid;

    private final OffsetDateTime creationDate;

    private final String investigatorUuid;

    private final String report;

    private final String ipAddress;

    /**
     * Private constructor.
     *
     * @param builder the {@link Builder}
     */
    private Report(Builder builder) {
        this.uuid = builder.uuid;
        this.creationDate = builder.creationDate;
        this.investigatorUuid = builder.investigatorUuid;
        this.report = builder.report;
        this.ipAddress = builder.ipAddress;
    }

    /**
     * Returns the UUID.
     *
     * @return the UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Returns the {@link OffsetDateTime} the {@link Report} was filed.
     *
     * @return the {@link OffsetDateTime} the {@link Report} was filed
     */
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Returns the investigator UUID.
     *
     * @return the investigator UUID
     */
    public String getInvestigatorUuid() {
        return investigatorUuid;
    }

    /**
     * Returns the report.
     *
     * @return the report
     */
    public String getReport() {
        return report;
    }

    /**
     * Returns the client IP address.
     *
     * @return the client IP address
     */
    public String getIpAddress() {
        return ipAddress;
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

        Report report1 = (Report) o;

        return new EqualsBuilder()
            .append(uuid, report1.uuid)
            .append(creationDate, report1.creationDate)
            .append(investigatorUuid, report1.investigatorUuid)
            .append(report, report1.report)
            .append(ipAddress, report1.ipAddress)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(uuid)
            .append(creationDate)
            .append(investigatorUuid)
            .append(report)
            .append(ipAddress)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("uuid", uuid)
            .append("creationDate", creationDate)
            .append("investigatorUuid", investigatorUuid)
            .append("report", report)
            .append("ipAddress", ipAddress)
            .toString();
    }

    /**
     * The builder.
     */
    public static class Builder {
        
        private String uuid;

        private OffsetDateTime creationDate;

        private String investigatorUuid;

        private String report;

        private String ipAddress;

        /**
         * Private constructor.
         */
        private Builder() {
            // prevent instantiation
        }

        /**
         * Sets the UUID.
         *
         * @param uuid the UUID
         * @return the {@link Builder}
         */
        public Builder withUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        /**
         * Sets the {@link OffsetDateTime} this {@link Report} was filed.
         *
         * @param creationDate the {@link OffsetDateTime} the {@link Report} was filed
         * @return the {@link Builder}
         */
        public Builder withCreationDate(OffsetDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        /**
         * Sets the investigator UUID.
         *
         * @param investigatorUuid the investigator UUID
         * @return the {@link Builder}
         */
        public Builder withInvestigatorUuid(String investigatorUuid) {
            this.investigatorUuid = investigatorUuid;
            return this;
        }

        /**
         * Sets the report.
         *
         * @param report the report
         * @return the {@link Builder}
         */
        public Builder withReport(String report) {
            this.report = report;
            return this;
        }

        /**
         * Sets the client IP address of the person filing a tip.
         *
         * @param ipAddress the client IP address
         * @return the {@link Builder}
         */
        public Builder withIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        /**
         * Builds a {@link Report}.
         *
         * @return the {@link Report}
         */
        public Report build() {
            return new Report(this);
        }
    }
}
