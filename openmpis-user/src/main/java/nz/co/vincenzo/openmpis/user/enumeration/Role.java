/*
 * This file is part of OpenMPIS, the Open Source Missing Persons Information System.
 * Copyright (C) 2008-2017  Rey Vincent Babilonia <rvbabilonia@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nz.co.vincenzo.openmpis.user.enumeration;

/**
 * The user roles.
 *
 * @author Rey Vincent Babilonia
 * @since 2.0.0
 */
public enum Role {

    /**
     * OpenMPIS system administrator.
     */
    ADMINISTRATOR,
    /**
     * Encoder or clerk.
     */
    ENCODER,
    /**
     * Investigator or case officer.
     */
    INVESTIGATOR
}
