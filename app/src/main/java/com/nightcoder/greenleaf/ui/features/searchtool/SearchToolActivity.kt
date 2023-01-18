package com.nightcoder.greenleaf.ui.features.searchtool

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.nightcoder.greenleaf.R
import com.nightcoder.greenleaf.adapter.VehicleFeatureGridAdapter
import com.nightcoder.greenleaf.databinding.ActivitySearchToolBinding
import com.nightcoder.greenleaf.domain.state.UiState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchToolActivity : AppCompatActivity() {

    private var _binding: ActivitySearchToolBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchToolViewModel by viewModels()

    private var adapter = VehicleFeatureGridAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivitySearchToolBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            _binding = it
        }

        initView()
        initObserver()
    }

    private fun initObserver() {

        viewModel.state.observe(this) { state ->
            binding.resultView.isVisible = state is UiState.Content
            binding.progressbar.isVisible = state is UiState.Loading
            binding.errorView.isVisible = state is UiState.Error

            if (state is UiState.Content) {
                adapter.setItems(state.data)
            }
        }

    }

    private fun initView() {

        binding.recyclerView.adapter = adapter

        binding.backBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.searchBtn.setOnClickListener {
            val queryText = binding.searchField.text
            if (queryText.isNullOrEmpty()) {
                Toast.makeText(this, R.string.reg_no_required, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            hideKeyBoard()
            viewModel.queryDetails(queryText.toString())
        }

    }

    private fun hideKeyBoard() {
        try {
            val view: View? = this.currentFocus
            if (view != null) {
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (ignored: Exception) {
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}