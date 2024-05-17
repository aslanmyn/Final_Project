package com.aslan.okko.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.aslan.okko.databinding.FragmentDividedBinding
import com.aslan.okko.ui.activies.SelectionActivity


class DividedFragment : Fragment() {
    private lateinit var binding: FragmentDividedBinding
    private lateinit var activity: SelectionActivity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDividedBinding.inflate(layoutInflater, container, false)
        binding.filmImage.clipToOutline=true
        binding.tvshowImage.clipToOutline=true
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.cardMovie.setOnClickListener {
            val action = DividedFragmentDirections.actionDividedFragmentToMovieFragment()
            findNavController().navigate(action)
        }
        binding.cardTvShow.setOnClickListener {

            val action = DividedFragmentDirections.actionDividedFragmentToTvShowsFragment()
            findNavController().navigate(action)
        }
    }

}