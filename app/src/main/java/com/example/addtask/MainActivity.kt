package com.example.addtask

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemLongClickListener
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var items: ArrayList<String>? = null
    private var itemsAdapter: ArrayAdapter<String>? = null
    private lateinit var listView: ListView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        button = findViewById(R.id.button)
        button.setOnClickListener(View.OnClickListener { view -> addItem(view) })
        items = ArrayList()
        itemsAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items!!)
        listView.setAdapter(itemsAdapter)
        setUpListViewListener()
    }

    private fun setUpListViewListener() {
        listView!!.onItemLongClickListener = OnItemLongClickListener { adapterView, view, position, id ->
            val context = applicationContext
            Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show()
            items!!.removeAt(position)
            itemsAdapter!!.notifyDataSetChanged()
            true
        }
    }

    private fun addItem(view: View) {
        val input = findViewById<EditText>(R.id.editText)
        val itemText = input.text.toString()
        if (itemText != "") {
            itemsAdapter!!.add(itemText)
            input.setText("")
        } else {
            Toast.makeText(applicationContext, "Please enter text..", Toast.LENGTH_LONG).show()
        }
    }
}