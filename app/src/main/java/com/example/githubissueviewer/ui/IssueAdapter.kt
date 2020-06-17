package com.example.githubissueviewer.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.githubissueviewer.R
import com.example.githubissueviewer.model.IssueModel
import kotlinx.android.synthetic.main.item_issue.view.*

class IssueAdapter(
    private val context: Context,
    private var items: List<IssueModel>,
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<IssueAdapter.ViewHolder>() {

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
        val item: IssueModel = items[position]

        holder.apply {
            issue.text = context.getString(R.string.issue, item.number, item.title)
            layout.setOnClickListener(onClickListener)
        }
    }

    fun setItems(issueList: ArrayList<IssueModel>) {
        items = issueList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var layout: ConstraintLayout = view.layout_parent
        var issue: TextView = view.text_issue
    }
}

