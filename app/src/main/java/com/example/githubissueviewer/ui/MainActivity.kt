package com.example.githubissueviewer.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubissueviewer.R
import com.example.githubissueviewer.model.IssueModel
import com.example.githubissueviewer.server.ServerManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var issueAdapter: IssueAdapter? = null
    private var issueList: ArrayList<IssueModel?> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initIssueList()
        setIssueList()
    }

    private fun initIssueList() {
        issueAdapter = IssueAdapter(this, issueList, onClickListener)
        recycler_issue.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = issueAdapter
        }
    }

    private fun setIssueList() {
        CoroutineScope(Dispatchers.Main).launch {
            val response = ServerManager.getIssueList("google", "dagger")
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    body.forEachIndexed { index, issue ->
                        if (index == 4) issueList.add(null)
                        issueList.add(issue)
                    }
                    issueAdapter?.setItems(issueList)
                }
            } else {
                // TODO : Error
            }
        }
    }

    private val onClickListener: View.OnClickListener = View.OnClickListener {
        when (it.id) {
            R.id.text_issue -> {
                // TODO : 화면 이동
            }

            R.id.view_logo -> {
                // TODO : 화면 이동
            }
        }
    }
}
