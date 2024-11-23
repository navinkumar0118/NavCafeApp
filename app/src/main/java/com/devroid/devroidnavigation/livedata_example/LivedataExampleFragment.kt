package com.devroid.devroidnavigation.livedata_example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devroid.devroidnavigation.R
import com.devroid.devroidnavigation.cofee_ordering.CoffeeOrderHomeViewModel
import com.devroid.devroidnavigation.databinding.FragmentCoffeeOrderHomeBinding
import com.devroid.devroidnavigation.databinding.FragmentLivedataExampleBinding


class LivedataExampleFragment : Fragment() {

    private lateinit var _binding: FragmentLivedataExampleBinding
    private val binding get() = _binding


    //view model declaration
    lateinit var viewModel: LiveDataExampleViewModel


    var temp = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLivedataExampleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model initialisation
        viewModel = ViewModelProvider(this)[LiveDataExampleViewModel::class.java]

        // Create the observer which updates the UI.
        val scoreListener = Observer<String> { latestValue ->
            _binding.scoreTextView.text =  latestValue
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.scoreLiveData.observe(viewLifecycleOwner, scoreListener)



        _binding.btn.setOnClickListener {
            temp+=5
            viewModel.updateLivedata(temp.toString())
        }

    }

}