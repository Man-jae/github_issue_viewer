package com.example.githubissueviewer.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubissueviewer.R
import com.example.githubissueviewer.data.Issue
import kotlinx.android.synthetic.main.item_issue.view.*

class IssueAdapter(
    private val context: Context,
    private var items: List<Issue?>,
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<IssueAdapter.ViewHolder>() {
    companion object {
        private const val LOGO_URL = "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(R.layout.item_issue, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.apply {
            viewLogo.visibility = View.INVISIBLE
            textIssue.visibility = View.VISIBLE

            item?.let {
                textIssue.tag = item
                textIssue.text = context.getString(R.string.issue, item.number, item.title)
                textIssue.setOnClickListener(onClickListener)
            } ?: also {
                Glide.with(context)
                    .load(LOGO_URL)
                    .into(viewLogo)

                viewLogo.tag = item
                viewLogo.visibility = View.VISIBLE
                textIssue.visibility = View.INVISIBLE
                viewLogo.setOnClickListener(onClickListener)
            }
        }
    }

    fun setItems(issueList: ArrayList<Issue?>) {
        items = issueList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textIssue: TextView = view.text_issue
        var viewLogo: ImageView = view.view_logo
    }
}

