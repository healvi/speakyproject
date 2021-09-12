package com.healvi.speaky.presentation.assessment.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healvi.speaky.databinding.GridListHorizontalBinding
import com.healvi.speaky.domain.model.assesment.AssessmentPack

class PraAssessmentAdapter
    (private val listTest: ArrayList<AssessmentPack>) :
    RecyclerView.Adapter<PraAssessmentAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: AssessmentPack)
    }

    inner class ListViewHolder(private val binding: GridListHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pack: AssessmentPack) {
            with(binding) {
                titlePack.text = pack.title
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(pack) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = GridListHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listTest[position])
    }

    override fun getItemCount(): Int = listTest.size
}