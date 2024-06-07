package app.chayut.picsum

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.chayut.picsum.model.PicItemDao
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson


class MainViewModel : ViewModel() {

    val picList: MutableLiveData<List<BaseRecyclerViewItem>> by lazy {
        MutableLiveData<List<BaseRecyclerViewItem>>()
    }

    fun getPicsumData(newRequestQueue: RequestQueue) {
        val list = ArrayList<BaseRecyclerViewItem>()

        list.add(BaseRecyclerViewItem.SessionHeaderItem("Sample picture"))

        val url = "https://picsum.photos/v2/list"
        val stringRequest =
            StringRequest(Request.Method.GET, url, {
                val daoList = Gson().fromJson(it, Array<PicItemDao>::class.java)
                daoList.map { dao ->
                    list.add(
                        BaseRecyclerViewItem.PicsumItem(
                            author = dao.author,
                            id = dao.id,
                            url = dao.url
                        )
                    )
                }
                picList.postValue(list)
            }, { })

        newRequestQueue.add(stringRequest)
    }
}
