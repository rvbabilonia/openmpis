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

import org.vincenzolabs.openmpis.enumeration.EyeColor;
import org.vincenzolabs.openmpis.enumeration.HairColor;
import org.vincenzolabs.openmpis.enumeration.Race;
import org.vincenzolabs.openmpis.enumeration.Religion;
import org.vincenzolabs.openmpis.enumeration.Sex;

/**
 * The domain model object for description.
 *
 * @author Rey Vincent Babilonia
 */
public class Description {

    private final Sex sex;

    private final Double height;

    private final Double mass;

    private final Religion religion;

    private final Race race;

    private final EyeColor eyeColor;

    private final HairColor hairColor;

    private final String medicalCondition;

    private final String distinguishingFeatures;

    private final String personalEffects;

    private final String remarks;

    /**
     * Private constructor.
     *
     * @param builder the {@link Builder}
     */
    private Description(Builder builder) {
        this.sex = builder.sex;
        this.height = builder.height;
        this.mass = builder.mass;
        this.religion = builder.religion;
        this.race = builder.race;
        this.eyeColor = builder.eyeColor;
        this.hairColor = builder.hairColor;
        this.medicalCondition = builder.medicalCondition;
        this.distinguishingFeatures = builder.distinguishingFeatures;
        this.personalEffects = builder.personalEffects;
        this.remarks = builder.remarks;
    }

    /**
     * Returns the {@link Sex}.
     *
     * @return the {@link Sex}
     */
    public Sex getSex() {
        return sex;
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
     * Returns the weight in kilograms.
     *
     * @return the weight in kilograms
     */
    public Double getMass() {
        return mass;
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
     * Returns the {@link Race}.
     *
     * @return the {@link Race}
     */
    public Race getRace() {
        return race;
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
     * Returns the {@link HairColor}.
     *
     * @return the {@link HairColor}
     */
    public HairColor getHairColor() {
        return hairColor;
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
     * Returns the distinguishing features.
     *
     * @return the distinguishing features
     */
    public String getDistinguishingFeatures() {
        return distinguishingFeatures;
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
     * Returns the remarks.
     *
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Returns the {@link Builder}.
     *
     * @return the {@link Builder}
     */
    public Builder builder() {
        return new Builder();
    }

    /**
     * The builder.
     */
    public static class Builder {

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
         * Private constructor.
         */
        private Builder() {
            // prevent instantiation
        }

        /**
         * Sets the {@link Sex}.
         *
         * @param sex the {@link Sex}
         * @return the {@link Builder}
         */
        public Builder withSex(Sex sex) {
            this.sex = sex;
            return this;
        }

        /**
         * Sets the height in centimeters.
         *
         * @param height the height in centimeters
         * @return the {@link Builder}
         */
        public Builder withHeight(Double height) {
            this.height = height;
            return this;
        }

        /**
         * Sets the mass in kilograms.
         *
         * @param mass the mass in kilograms
         * @return the {@link Builder}
         */
        public Builder withMass(Double mass) {
            this.mass = mass;
            return this;
        }

        /**
         * Sets the {@link Religion}.
         *
         * @param religion the {@link Religion}
         * @return the {@link Builder}
         */
        public Builder withReligion(Religion religion) {
            this.religion = religion;
            return this;
        }

        /**
         * Sets the {@link Race}.
         *
         * @param race the {@link Race}
         * @return the {@link Builder}
         */
        public Builder withRace(Race race) {
            this.race = race;
            return this;
        }

        /**
         * Sets the {@link EyeColor}.
         *
         * @param eyeColor the {@link EyeColor}
         * @return the {@link Builder}
         */
        public Builder withEyeColor(EyeColor eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        /**
         * Sets the {@link HairColor}.
         *
         * @param hairColor the {@link HairColor}
         * @return the {@link Builder}
         */
        public Builder withHairColor(HairColor hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        /**
         * Sets the medical condition.
         *
         * @param medicalCondition the medical condition
         * @return the {@link Builder}
         */
        public Builder withMedicalCondition(String medicalCondition) {
            this.medicalCondition = medicalCondition;
            return this;
        }

        /**
         * Sets the distinguishing features.
         *
         * @param distinguishingFeatures the distinguishing features
         * @return the {@link Builder}
         */
        public Builder withDistinguishingFeatures(String distinguishingFeatures) {
            this.distinguishingFeatures = distinguishingFeatures;
            return this;
        }

        /**
         * Sets the personal effects.
         *
         * @param personalEffects the personal effects
         * @return the {@link Builder}
         */
        public Builder withPersonalEffects(String personalEffects) {
            this.personalEffects = personalEffects;
            return this;
        }

        /**
         * Sets the remarks.
         *
         * @param remarks the remarks
         * @return the {@link Builder}
         */
        public Builder withRemarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        /**
         * Builds an {@link Description}.
         *
         * @return the {@link Description}
         */
        public Description build() {
            return new Description(this);
        }
    }
}
