package com.fpadilha90.assetsinfo.ui.ui.assets

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fpadilha90.assetsinfo.databinding.AssetsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AssetsFragment : Fragment() {
    private val viewModel by viewModel<AssetsViewModel>()

    private var _binding: AssetsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = AssetsFragmentBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = this@AssetsFragment
        _binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.assetsRecyclerView.adapter = AssetsAdapter(emptyList()) { assetInfo ->
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(assetInfo.website)
            }.let {
                startActivity(it)
            }
        }

        viewModel.assets.observe(viewLifecycleOwner) {
            (binding.assetsRecyclerView.adapter as AssetsAdapter).update(it)
        }

        binding.searchView.setOnQueryTextListener(object: androidx.appcompat.widget.
        SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.search(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.search(newText)
                }
                return true
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}