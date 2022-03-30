package com.example.chuck.model

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.chuck.databinding.DialogInfoBinding

class InfoDialog {

    private var dialog: AlertDialog? = null

    fun build(context: Context, title: String, description: String, cb: DialogCallback) {
        val binding: DialogInfoBinding = DialogInfoBinding.inflate(LayoutInflater.from(context))
        val dialogBuilder = AlertDialog.Builder(context)
            .setCancelable(false)
            .setView(binding.root)

        binding.continueButton.setOnClickListener {
            dialog?.dismiss()
            cb.onClose()
        }
        dialog = dialogBuilder.show().also {
            it.window?.setBackgroundDrawable((ColorDrawable(Color.TRANSPARENT)))
        }
    }
}