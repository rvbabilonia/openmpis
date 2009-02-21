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

/**
 * The Pagination class provides the methods to paginate listings.
 * 
 * @author  <a href="mailto:rvbabilonia@gmail.com">Rey Vincent Babilonia</a>
 * @version 1.0
 */
public class Pagination {

    /**
     * The offset or number of results to be ignored
     */
    private int skipResults = 0;
    /**
     * The maximum number of results to be returned per page
     */
    private int maxResults = 10;
    /**
     * The total number of results
     */
    private int totalResults = -1;

    /**
     * Gets the offset or number of ignored results.
     * 
     * @return              the offset or number of ignored results
     */
    public int getSkipResults() {
        return skipResults;
    }

    /**
     * Sets the offset or number of ignored results.
     * 
     * @param skipResults   the offset or number of ignored results
     */
    public void setSkipResults(int skipResults) {
        this.skipResults = skipResults;
    }

    /**
     * Gets the maximum number of results to be returned per page.
     * 
     * @return              the maximum number of results to be returned per page
     */
    public int getMaxResults() {
        return maxResults;
    }

    /**
     * Sets the maximum number of results to be returned per page.
     * 
     * @param maxResults    the maximum number of results to be returned per page
     */
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * Gets the total number of results.
     * 
     * @return              the total number of results
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * Sets the total number of results.
     * 
     * @param totalResults  the total number of results
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Checks if there are more pages.
     * 
     * @return              <code>true</code> if there are more pages; <code>false</code> otherwise
     */
    public boolean hasMorePages() {
        if (skipResults + maxResults > totalResults) {
            return false;
        }
        return true;
    }

    /**
     * Increments skipResults by maxResults to generate the next page.
     */
    public void nextPage() {
        skipResults += maxResults;
    }
    
    /**
     * Decrements skipResults to generate the next page.
     */
    public void previousPage() {
        skipResults -= maxResults;
    }
    
    /**
     * Sets skipResults to 0 to generate the first page.
     */
    public void firstPage() {
        skipResults = 0;
    }
    
    /**
     * Sets skipResults to generate the last page.
     */
    public void lastPage() {
        skipResults = (getTotalPages() - 1) * maxResults;
    }

    /**
     * Gets the current page number.
     *
     * @return              the current page number
     */
    public int getCurrentPage() {
        return (skipResults / maxResults) + 1;
    }

    /**
     * Gets the total number of pages.
     *
     * @return              the total number of pages
     */
    public int getTotalPages() {
        int remainder = totalResults % maxResults;
        int totalPages = (totalResults - remainder) / maxResults;
        if (remainder >= 0) {
            totalPages += 1;
        } else if (totalResults == 0) {
            totalPages = 1;
        }

        return totalPages;
    }
}