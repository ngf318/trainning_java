package com.nebula.mylibrarykotlin

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*

class ActivityTestOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_one)

        initView()
    }

    @SuppressLint("NewApi")
    private fun initView() {
        findViewById<TextView>(R.id.text_one).setOnClickListener {
            Toast.makeText(this, "hello", Toast.LENGTH_LONG).show()
        }

        var textOne = findViewById<TextView>(R.id.text_one)
        textOne.text = "wtttt"
        textOne?.let { textView -> textView.text = "aaaa" }
        with(textOne) {
            text = "Whats up"
        }
        var imageView = findViewById<ImageView>(R.id.image_view)
        imageView.setBackgroundResource(R.drawable.ic_new_post)
        imageView.background = getDrawable(R.drawable.ic_new_post)
        imageView.setImageResource(R.drawable.ic_new_post)
    }

    override fun onResume() {
        super.onResume()
        var x = 1
        var str : String? = null
        str.isNullOrEmpty()
        val strs = str ?: return
    }

    override fun onDestroy() {
        super.onDestroy()
        clearx()
    }

    private fun clearx() {
        var a = "abc"
        var b = 2
        var c = true

        var d = a.takeIf { true }
        var e = a.also { a = "xxx" }
        var f = a.let { a = "ccc" }
        var g = with(a) {
            a = "ddd"
        }
        println(d)
        println(e)
        println(a)
        println(f)
        println(g)
    }

    open fun showExchangeDialog(context: Context, diamonds: Long) {
        val dialog = Dialog(context)
        val view: View = LayoutInflater.from(context).inflate(R.layout.activity_test_one, null)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(view)
        val lp = dialog.window!!.attributes
        dialog.window!!.attributes = lp
        dialog.window!!.setBackgroundDrawableResource(R.drawable.ic_new_post)
        dialog.show()
        view.findViewById<View>(R.id.text_one).setOnClickListener { v: View? -> dialog.dismiss() }
        val diamond_balance = view.findViewById<TextView>(R.id.text_one)
        diamond_balance.text = String.format(Locale.US, context.getString(R.string.appbar_scrolling_view_behavior), diamonds)
        val edit_tv = view.findViewById<EditText>(R.id.text_one)
        val energy_count = view.findViewById<TextView>(R.id.text_one)
        val ok = view.findViewById<TextView>(R.id.text_one)
        edit_tv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val length = s.length
                if (length > 0) {
                    try {
                        val d = java.lang.Long.valueOf(s.toString())
                        if (d > diamonds) {
                            ok.background = ContextCompat.getDrawable(context, R.drawable.ic_new_post)
                            ok.setOnClickListener(null)
                        } else {
                            ok.setOnClickListener {
                                dialog.dismiss()
                            }
                            ok.background = ContextCompat.getDrawable(context, R.drawable.ic_new_post)
                        }
                    } catch (e: NumberFormatException) {
                        e.printStackTrace()
                    }
                } else {
                    ok.background = ContextCompat.getDrawable(context, R.drawable.ic_new_post)
                    ok.setOnClickListener(null)
                    energy_count.text = 0.toString()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}