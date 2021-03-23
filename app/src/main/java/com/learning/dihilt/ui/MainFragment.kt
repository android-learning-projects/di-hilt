package com.learning.dihilt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.learning.dihilt.R
import com.learning.dihilt.databinding.ActivityMainBinding
import com.learning.dihilt.databinding.FragmentMainBinding
import com.learning.dihilt.utils.Resource
import com.learning.dihilt.utils.hide
import com.learning.dihilt.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment
@Inject
constructor() : Fragment() {
    val TAG = "MainFragment"
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: DataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*   viewModel.getName().observe(viewLifecycleOwner, {
               when (it.status) {
                   Resource.Status.LOADING -> {
                       Log.d(TAG, "loading...")
                       binding.progressBar.visibility = View.VISIBLE
                   }
                   Resource.Status.SUCCESS -> {
                       Log.d(TAG, "success: ${it.data}")
                       binding.progressBar.visibility = View.GONE
                   }
                   Resource.Status.ERROR -> {
                       Log.d(TAG, "error: ")
                       binding.progressBar.visibility = View.GONE
                   }

               }
           })*/

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogsEvent)
        viewModel.setStateEventName(MainStateEvent.GetBlogsEvent)


    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d(TAG, "loading...")
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "success: ${it.data}")
                    binding.progressBar.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "error: ${it.message}")
                    binding.progressBar.visibility = View.GONE
                }

            }
        })

        viewModel.dataStateName.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d(TAG, "loading...")
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    Log.d(TAG, "success: ${it.data}")
                    binding.progressBar.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    Log.d(TAG, "error: ${it.message}")
                    binding.progressBar.visibility = View.GONE
                }

            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}