package com.kamath.roshni.corecodebase.api.syncmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamath.roshni.corecodebase.api.models.Movie;

import java.util.List;

/**
 * Created by mac on 12/11/16.
 */

public class MovieResponse {

    @JsonProperty("page")
    private int page;
    @JsonProperty("results")
    private List<Movie> results;
    @JsonProperty("total_results")
    private int totalResults;
    @JsonProperty("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
