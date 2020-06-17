package com.example.githubissueviewer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubissueviewer.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.apply {
            title = "#${intent.getIntExtra("EXTRA_ISSUE", 0)}"
        }
    }
}
