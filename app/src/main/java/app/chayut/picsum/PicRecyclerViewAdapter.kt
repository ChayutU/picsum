package app.chayut.picsum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.chayut.picsum.databinding.ItemPicBinding
import app.chayut.picsum.databinding.ItemSectionHeaderBinding

class PicRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list = listOf<BaseRecyclerViewItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            LiveRecyclerViewType.TYPE_HEADER -> HeaderItemViewHolder(
                ItemSectionHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )


            LiveRecyclerViewType.TYPE_PIC -> PicItemViewHolder(
                ItemPicBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw NullPointerException("View Type $viewType doesn't match with any existing order detail type")
        }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list.getOrNull(position)?.type ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        list.getOrNull(position)?.let { item ->
            when {
                holder is HeaderItemViewHolder && item is BaseRecyclerViewItem.SessionHeaderItem -> holder.bind(
                    item
                )

                holder is PicItemViewHolder && item is BaseRecyclerViewItem.PicsumItem -> holder.bind(
                    item
                )

                else -> {}
            }
        }
    }

    fun setItems(list: List<BaseRecyclerViewItem>) {
        val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = this@PicRecyclerViewAdapter.list.size

            override fun getNewListSize(): Int = list.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return this@PicRecyclerViewAdapter.list.getOrNull(oldItemPosition) == list.getOrNull(
                    newItemPosition
                )
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return this@PicRecyclerViewAdapter.list.getOrNull(oldItemPosition) == list.getOrNull(
                    newItemPosition
                )
            }

        })
        this.list = list
        result.dispatchUpdatesTo(this)
    }
}