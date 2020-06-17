package com.example.githubissueviewer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubissueviewer.R
import com.example.githubissueviewer.data.Issue
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var issue: Issue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        init()
        setUi()
    }

    private fun init() {
        issue = intent.getParcelableExtra("EXTRA_ISSUE")
        supportActionBar?.title = "#${issue?.number}"
    }

    private fun setUi() {
        text_user.text = issue?.user?.userName

        Glide.with(this)
            .load(issue?.user?.userImage)
            .apply(RequestOptions.circleCropTransform())
            .into(view_user)

        text_issue_body.text = issue?.body
    }
}
