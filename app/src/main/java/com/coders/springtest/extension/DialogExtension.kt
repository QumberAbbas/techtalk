package com.coders.springtest.extension

import android.widget.Toast
import com.coders.springtest.R
import com.example.flatdialoglibrary.dialog.FlatDialog

fun FlatDialog.showSubscriptionDialog() {
    setTitle(context.getString(R.string.premium_user))
    setIcon(R.drawable.ic_premium)
    setSubtitle(context.getString(R.string.premium_user_note))
    setFirstButtonText(context.getString(R.string.subscribe))
    setSecondButtonText(context.getString(R.string.cancel))
    setCancelable(true)
    withFirstButtonListner {
        Toast.makeText(
            context,
            R.string.todo,
            Toast.LENGTH_SHORT
        ).show()
    }
    withSecondButtonListner {
        dismiss()
    }
        .show()
}