package app.chayut.picsum

import androidx.recyclerview.widget.RecyclerView
import app.chayut.picsum.databinding.ItemPicBinding
import com.bumptech.glide.Glide

class PicItemViewHolder(private val binding: ItemPicBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BaseRecyclerViewItem.PicsumItem) {
        binding.tvAuthor.text = item.author
        Glide.with(binding.ivPicsum).load(item.url).into(binding.ivPicsum)
    }
}
