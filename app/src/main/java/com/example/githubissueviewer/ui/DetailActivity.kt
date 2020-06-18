package com.example.githubissueviewer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubissueviewer.R
import com.example.githubissueviewer.data.AppDatabase
import com.example.githubissueviewer.data.Issue
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private var issue: Issue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        init()
    }

    private fun init() {
        val db = AppDatabase.getInstance(this)
        val issueNumber = intent.getIntExtra("EXTRA_ISSUE", 0)

        CoroutineScope(Dispatchers.IO).launch {
            issue = db.issueDao().read(issueNumber)
            withContext(Dispatchers.Main) {
                setUi()
            }
        }
    }

    private fun setUi() {
        supportActionBar?.title = "#${issue?.number}"

        text_user.text = issue?.user?.userName

        Glide.with(this)
            .load(issue?.user?.userImage)
            .apply(RequestOptions.circleCropTransform())
            .into(view_user)

        text_issue_body.text = issue?.body
    }
}
