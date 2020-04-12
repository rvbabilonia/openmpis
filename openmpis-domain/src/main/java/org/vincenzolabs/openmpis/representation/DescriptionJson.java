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
import org.vincenzolabs.openmpis.enumeration.EyeColor;
import org.vincenzolabs.openmpis.enumeration.HairColor;
import org.vincenzolabs.openmpis.enumeration.Race;
import org.vincenzolabs.openmpis.enumeration.Religion;
import org.vincenzolabs.openmpis.enumeration.Sex;

/**
 * The data transfer object for description.
 *
 * @author Rey Vincent Babilonia
 */
public class DescriptionJson {

    private Sex sex;

    private Double height;

    private Double mass;

    private Religion religion;

    private Race race;

    private EyeColor eyeColor;

    private HairColor hairColor;

    private String medicalCondition;

    private String distinguishingFeatures;

    private String personalEffects;

    private String remarks;

    /**
     * Returns the {@link Sex}.
     *
     * @return the {@link Sex}
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Sets the {@link Sex}.
     *
     * @param sex the {@link Sex}
     */
    public void setSex(Sex sex) {
        this.sex = sex;

    }

    /**
     * Returns the height in centimeters.
     *
     * @return the height in centimeters
     */
    public Double getHeight() {
        return height;
    }

    /**
     * Sets the height in centimeters.
     *
     * @param height the height in centimeters
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * Returns the mass in kilograms.
     *
     * @return the mass in kilograms
     */
    public Double getMass() {
        return mass;
    }

    /**
     * Sets the mass in kilograms.
     *
     * @param mass the mass in kilograms
     */
    public void setMass(Double mass) {
        this.mass = mass;
    }

    /**
     * Returns the {@link Religion}.
     *
     * @return the {@link Religion}
     */
    public Religion getReligion() {
        return religion;
    }

    /**
     * Sets the {@link Religion}.
     *
     * @param religion the {@link Religion}
     */
    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    /**
     * Returns the {@link Race}.
     *
     * @return the {@link Race}
     */
    public Race getRace() {
        return race;
    }

    /**
     * Sets the {@link Race}.
     *
     * @param race the {@link Race}
     */
    public void setRace(Race race) {
        this.race = race;
    }

    /**
     * Returns the {@link EyeColor}.
     *
     * @return the {@link EyeColor}
     */
    public EyeColor getEyeColor() {
        return eyeColor;
    }

    /**
     * Sets the {@link EyeColor}.
     *
     * @param eyeColor the {@link EyeColor}
     */
    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    /**
     * Returns the {@link HairColor}.
     *
     * @return the {@link HairColor}
     */
    public HairColor getHairColor() {
        return hairColor;
    }

    /**
     * Sets the {@link HairColor}.
     *
     * @param hairColor the {@link HairColor}
     */
    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    /**
     * Returns the medical condition.
     *
     * @return the medical condition
     */
    public String getMedicalCondition() {
        return medicalCondition;
    }

    /**
     * Sets the medical condition.
     *
     * @param medicalCondition the medical condition
     */
    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    /**
     * Returns the distinguishing features.
     *
     * @return the distinguishing features
     */
    public String getDistinguishingFeatures() {
        return distinguishingFeatures;
    }

    /**
     * Sets the distinguishing features.
     *
     * @param distinguishingFeatures the distinguishing features
     */
    public void setDistinguishingFeatures(String distinguishingFeatures) {
        this.distinguishingFeatures = distinguishingFeatures;
    }

    /**
     * Returns the personal effects.
     *
     * @return the personal effects
     */
    public String getPersonalEffects() {
        return personalEffects;
    }

    /**
     * Sets the personal effects.
     *
     * @param personalEffects the personal effects
     */
    public void setPersonalEffects(String personalEffects) {
        this.personalEffects = personalEffects;
    }

    /**
     * Returns the remarks.
     *
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the remarks.
     *
     * @param remarks the remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DescriptionJson that = (DescriptionJson) o;

        return new EqualsBuilder()
            .append(sex, that.sex)
            .append(height, that.height)
            .append(mass, that.mass)
            .append(religion, that.religion)
            .append(race, that.race)
            .append(eyeColor, that.eyeColor)
            .append(hairColor, that.hairColor)
            .append(medicalCondition, that.medicalCondition)
            .append(distinguishingFeatures, that.distinguishingFeatures)
            .append(personalEffects, that.personalEffects)
            .append(remarks, that.remarks)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(sex)
            .append(height)
            .append(mass)
            .append(religion)
            .append(race)
            .append(eyeColor)
            .append(hairColor)
            .append(medicalCondition)
            .append(distinguishingFeatures)
            .append(personalEffects)
            .append(remarks)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("sex", sex)
            .append("height", height)
            .append("mass", mass)
            .append("religion", religion)
            .append("race", race)
            .append("eyeColor", eyeColor)
            .append("hairColor", hairColor)
            .append("medicalCondition", medicalCondition)
            .append("distinguishingFeatures", distinguishingFeatures)
            .append("personalEffects", personalEffects)
            .append("remarks", remarks)
            .toString();
    }
}
