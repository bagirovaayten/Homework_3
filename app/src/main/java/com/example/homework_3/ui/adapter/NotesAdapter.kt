package com.example.homework_3.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_3.R
import com.example.homework_3.data.Note
import kotlinx.android.synthetic.main.fragment_main.view.title
import kotlinx.android.synthetic.main.main_item_layout.view.*

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notes: MutableList<Note> = mutableListOf()
    var onItemClick: ((id: Int) -> Unit)? = null
    var onDeleteItemClick: ((note: Note) -> Unit)? = null

    fun setData(data: List<Note>) {
        this.notes.clear()
        this.notes.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.main_item_layout,
            parent,
            false
        )
        return NotesViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            itemView.title.text = note.title
            itemView.subtitle.text = note.body
            itemView.deleteIcon.setOnClickListener {
                onDeleteItemClick?.invoke(note)
                notifyItemRangeRemoved(notes.indexOf(note), notes.size - 1)
            }
            itemView.itemLayout.setOnClickListener {
                note.id.let { item -> onItemClick?.invoke(item?:0) }
            }
        }
    }
}