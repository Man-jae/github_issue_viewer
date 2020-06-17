package com.example.githubissueviewer.server

import com.example.githubissueviewer.model.Issue
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerInterface {
    @GET("{org}/{repo}/issues")
    suspend fun getIssueList(
        @Path("org") organization: String,
        @Path("repo") repository: String
    ): Response<ArrayList<Issue>>
}