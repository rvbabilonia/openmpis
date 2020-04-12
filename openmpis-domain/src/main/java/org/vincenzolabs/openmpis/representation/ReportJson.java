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

import java.time.OffsetDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The data transfer object for investigation report.
 *
 * @author Rey Vincent Babilonia
 */
public class ReportJson {

    private String uuid;

    private OffsetDateTime creationDate;

    private String investigatorUuid;

    private String report;

    private String ipAddress;

    /**
     * Returns the UUID.
     *
     * @return the UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the UUID.
     *
     * @param uuid the UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Returns the {@link OffsetDateTime} the {@link ReportJson} was filed.
     *
     * @return the {@link OffsetDateTime} the {@link ReportJson} was filed
     */
    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the {@link OffsetDateTime} the {@link ReportJson} was filed.
     *
     * @param creationDate the {@link OffsetDateTime} the {@link ReportJson} was filed
     */
    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
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
     * Sets the investigator UUID.
     *
     * @param investigatorUuid the investigator UUID
     */
    public void setInvestigatorUuid(String investigatorUuid) {
        this.investigatorUuid = investigatorUuid;
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
     * Sets the report.
     *
     * @param report the report
     */
    public void setReport(String report) {
        this.report = report;
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
     * Sets the IP address.
     *
     * @param ipAddress the IP address
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReportJson reportJson1 = (ReportJson) o;

        return new EqualsBuilder()
            .append(uuid, reportJson1.uuid)
            .append(creationDate, reportJson1.creationDate)
            .append(investigatorUuid, reportJson1.investigatorUuid)
            .append(report, reportJson1.report)
            .append(ipAddress, reportJson1.ipAddress)
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
}
