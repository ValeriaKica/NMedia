package ru.netology.nmedia.Activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityEditPostBinding

class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
       // setContentView(R.layout.activity_edit_post)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edit.requestFocus()
        binding.edit.setText(intent?.getStringExtra(Intent.EXTRA_TEXT))
        binding.ok.setOnClickListener {
            val text = binding.edit.text.toString()
            if (text.isBlank()) {
                setResult(RESULT_CANCELED)
            } else {
                intent.putExtra(Intent.EXTRA_TEXT, text)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
              //  supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
