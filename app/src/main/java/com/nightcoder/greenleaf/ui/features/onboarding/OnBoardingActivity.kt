package com.nightcoder.greenleaf.ui.features.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nightcoder.greenleaf.databinding.ActivityOnboardingBinding
import com.nightcoder.greenleaf.ui.features.searchtool.SearchToolActivity

class OnBoardingActivity : AppCompatActivity() {

    private var _binding: ActivityOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityOnboardingBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            _binding = it
        }
        initView()
    }

    private fun initView() {
        binding.nextBtn.setOnClickListener {
            startActivity(Intent(this, SearchToolActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}