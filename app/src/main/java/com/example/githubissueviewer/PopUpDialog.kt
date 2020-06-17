package com.example.githubissueviewer

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.dialog_input.view.*


class PopUpDialog {
    companion object {
        fun input(
            context: Context,
            cancelable: Boolean = true,
            listener: ReturnValueListener? = null
        ) {
            val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater.inflate(R.layout.dialog_input, null).let { v ->
                AlertDialog.Builder(context).apply {
                    setView(v)
                    setPositiveButton("확인", null)
                    setNegativeButton("취소", null)
                    setCancelable(cancelable)
                }.create().let { dialog ->
                    dialog.setOnShowListener {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                            listener?.apply {
                                onReturnValue(
                                    org = v.edit_org.text.toString(),
                                    repo = v.edit_repo.text.toString()
                                )
                                dialog.dismiss()
                            }
                        }
                    }
                    dialog.show()
                }
            }
        }
    }

    interface ReturnValueListener {
        fun onReturnValue(org: String, repo: String)
    }
}