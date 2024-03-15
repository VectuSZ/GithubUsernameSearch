package com.githubrepositoryapp.githubrepository.model;

import java.util.List;
import java.util.Map;

public class RepositoryResponse {
    private List<Map<String, String>> repositories;

    public List<Map<String, String>> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Map<String, String>> repositories) {
        this.repositories = repositories;
    }
}
