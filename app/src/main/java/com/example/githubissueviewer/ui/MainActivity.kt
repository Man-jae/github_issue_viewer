package com.example.githubissueviewer.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubissueviewer.PopUpDialog
import com.example.githubissueviewer.R
import com.example.githubissueviewer.data.AppDatabase
import com.example.githubissueviewer.data.Issue
import com.example.githubissueviewer.server.ServerManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    companion object {
        private const val URL = "https://thingsflow.com/ko/home"
    }

    private var issueAdapter: IssueAdapter? = null
    private var issueList: ArrayList<Issue?> = arrayListOf()
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getInstance(this)
        initIssueList()
        getIssueToRoom()
        text_repo_title.setOnClickListener(onClickListener)
    }

    private fun initIssueList() {
        issueAdapter = IssueAdapter(this, issueList, onClickListener)
        recycler_issue.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = issueAdapter
        }
    }

    private fun checkNetwork(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    private fun getIssueToRoom() {
        issueList.clear()
        CoroutineScope(Dispatchers.IO).launch {
            val repository = db.repoDao().read()
            val issues = db.issueDao().readAll()
            withContext(Dispatchers.Main) {
                repository?.let { repo ->
                    issues?.forEachIndexed { index, issue ->
                        if (index == 4) issueList.add(null)
                        issueList.add(issue)
                    }
                    issueAdapter?.setItems(issueList)
                    text_repo_title.text = getString(R.string.title, repo.org, repo.repo)
                } ?: also {
                    text_repo_title.text = getString(R.string.repo_empty)
                }
            }
        }
    }

    private fun setIssueList(org: String, repo: String) {
        issueList.clear()
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = ServerManager.getIssueList(org, repo)
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        body.forEachIndexed { index, issue ->
                            if (index == 4) issueList.add(null)
                            issueList.add(issue)
                        }
                        issueAdapter?.setItems(issueList)
                    }
                }
            } catch (e: Exception) {
                Log.i("LOG", e.toString())
            }
        }

    }

    private val onClickListener: View.OnClickListener = View.OnClickListener {
        when (it.id) {
            R.id.text_repo_title -> {
                PopUpDialog.input(
                    context = this,
                    listener = object : PopUpDialog.ReturnValueListener {
                        override fun onReturnValue(org: String, repo: String) {
                            setIssueList(org, repo)
                        }
                    }
                )
            }
            R.id.text_issue -> {
                (it.tag as Issue).let { issue ->
                    Intent(this, DetailActivity::class.java).apply {
                        putExtra("EXTRA_ISSUE", issue)
                        startActivity(this)
                    }
                }
            }

            R.id.view_logo -> {
                Intent(Intent.ACTION_VIEW, Uri.parse(URL)).apply {
                    startActivity(this)
                }
            }
        }
    }
}
