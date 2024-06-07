package app.chayut.picsum

import androidx.recyclerview.widget.RecyclerView
import app.chayut.picsum.databinding.ItemSectionHeaderBinding

class HeaderItemViewHolder(private val binding: ItemSectionHeaderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BaseRecyclerViewItem.SessionHeaderItem) {
        binding.tvSessionName.text = item.title
    }

}