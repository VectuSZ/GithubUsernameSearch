package com.githubrepositoryapp.githubrepository.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GithubService {
    @Autowired
    private RestTemplate restTemplate;

    public List<Map<String, String>> getUserRepositories(String username) throws JSONException {
        String url = STR."https://api.github.com/users/\{username}/repos";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();

        JSONArray repositories = new JSONArray(responseBody);
        List<Map<String, String>> repositoryList = new ArrayList<>();

        for (int i = 0; i < repositories.length(); i++) {
            JSONObject repository = repositories.getJSONObject(i);
            String repoName = repository.getString("name");
            String ownerLogin = repository.getJSONObject("owner").getString("login");

            String branchesUrl = UriComponentsBuilder.fromUriString(repository.getString("branches_url").replace("{/branch}", ""))
                    .scheme("https")
                    .host("api.github.com")
                    .build()
                    .toUriString();

            ResponseEntity<String> branchesResponse = restTemplate.getForEntity(branchesUrl, String.class);
            String branchesResponseBody = branchesResponse.getBody();
            JSONArray branchesArray = new JSONArray(branchesResponseBody);

            List<Map<String, String>> branchList = new ArrayList<>();
            for (int j = 0; j < branchesArray.length(); j++) {
                JSONObject branch = branchesArray.getJSONObject(j);
                String branchName = branch.getString("name");
                String lastCommitSha = branch.getJSONObject("commit").getString("sha");

                Map<String, String> branchInfo = new HashMap<>();
                branchInfo.put("name", branchName);
                branchInfo.put("last_commit_sha", lastCommitSha);
                branchList.add(branchInfo);
            }

            JSONArray branchJsonArray = new JSONArray(branchList);
            String branchListJsonString = branchJsonArray.toString();

            Map<String, String> repositoryInfo = new HashMap<>();
            repositoryInfo.put("repository_name", repoName);
            repositoryInfo.put("owner_login", ownerLogin);
            repositoryInfo.put("branches", branchList.toString());

            repositoryList.add(repositoryInfo);
        }

        return repositoryList;
    }
}