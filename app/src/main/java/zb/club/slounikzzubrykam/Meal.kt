package zb.club.slounikzzubrykam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import zb.club.slounikzzubrykam.databinding.FragmentMealBinding


class Meal : Fragment() {
     private lateinit var binding: FragmentMealBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealBinding.inflate(layoutInflater)
        return binding.root

    }

}