package com.githubrepositoryapp.githubrepository.controller;

import com.githubrepositoryapp.githubrepository.model.RepositoryResponse;
import com.githubrepositoryapp.githubrepository.service.GithubService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GithubController {
    @Autowired
    private GithubService githubService;

    @GetMapping("/repositories/{username}")
    public ResponseEntity<RepositoryResponse> getUserRepositories(
            @PathVariable String username,
            @RequestHeader("Accept") String acceptHeader
    ) throws JSONException {
        if (!acceptHeader.equals("application/json")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Map<String, String>> repositories = githubService.getUserRepositories(username);
        RepositoryResponse repositoryResponse = new RepositoryResponse();
        repositoryResponse.setRepositories(repositories);

        return ResponseEntity.ok().body(repositoryResponse);
    }
}
