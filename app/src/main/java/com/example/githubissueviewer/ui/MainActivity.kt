package com.example.githubissueviewer.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubissueviewer.R
import com.example.githubissueviewer.model.IssueModel
import com.example.githubissueviewer.server.ServerManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var issueAdapter: IssueAdapter? = null
    private var issueList: ArrayList<IssueModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initIssueList()
    }

    private fun initIssueList() {
        issueAdapter = IssueAdapter(this, issueList, onClickListener)
        recycler_issue.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = issueAdapter
        }
    }

    private val onClickListener: View.OnClickListener = View.OnClickListener {
        when (it.id) {
            R.id.layout_parent -> {
                // TODO : 화면 이동
            }
        }
    }
}
