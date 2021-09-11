package br.com.wolking.moneyinvestmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.wolking.moneyinvestmvvm.databinding.FragmentHomeBinding
import br.com.wolking.moneyinvestmvvm.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val vm: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = vm

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.toastShow.observe(viewLifecycleOwner, {
            Toast.makeText(
                requireContext(),
                getString(R.string.value_calculated),
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}