package com.example.githubissueviewer.server

import com.example.githubissueviewer.model.Issue
import retrofit2.Response

class ServerManager {
    companion object {
        private val SERVER = ServerDefine.retrofit.create(ServerInterface::class.java)

        suspend fun getIssueList(org: String, repo: String): Response<ArrayList<Issue>> {
            return SERVER.getIssueList(org, repo)
        }
    }
}