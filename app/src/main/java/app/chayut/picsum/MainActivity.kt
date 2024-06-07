package app.chayut.picsum

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import app.chayut.picsum.databinding.ActivityMainBinding
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter = PicRecyclerViewAdapter()

    private val viewMode by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.recyclerMain.apply {
            this.adapter = this@MainActivity.adapter
        }

        viewMode.picList.observe(this) {
            adapter.setItems(it)
        }

        viewMode.getPicsumData(Volley.newRequestQueue(this))
    }
}