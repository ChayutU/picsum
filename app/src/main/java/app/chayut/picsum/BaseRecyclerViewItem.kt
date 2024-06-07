package app.chayut.picsum

sealed class BaseRecyclerViewItem(open val type: Int) {
    data class SessionHeaderItem(
        var title: String? = null
    ) : BaseRecyclerViewItem(LiveRecyclerViewType.TYPE_HEADER)

    data class PicsumItem(
        val author: String? = null,
        val id: String? = null,
        val url: String? = null
    ) : BaseRecyclerViewItem(LiveRecyclerViewType.TYPE_PIC)
}