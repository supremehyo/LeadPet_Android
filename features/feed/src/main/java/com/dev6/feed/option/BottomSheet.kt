package com.dev6.feed.option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.dev6.core.enums.PostOptionType
import com.dev6.feed.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomSheet(private val callback: (PostOptionType) -> Unit, type: Int) :
    BottomSheetDialogFragment() {

    var type = type
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(this.type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (type == R.layout.fragment_bottom_sheet) {
            view.findViewById<RadioGroup>(R.id.radioGroup1)
                .setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.recentRadio -> optionSelection(callback, PostOptionType.RECENT)
                        R.id.dateRadio -> optionSelection(callback, PostOptionType.DATE)
                        R.id.totalRadio -> optionSelection(callback, PostOptionType.TOTAL)
                    }
                }
        } else {
            view.findViewById<RadioGroup>(R.id.radioGroup2)
                .setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.feedRadio -> optionSelection(callback, PostOptionType.FEED)
                        R.id.thingRadio -> optionSelection(callback, PostOptionType.THING)
                    }
                }
        }
    }

    private fun optionSelection(callback: (PostOptionType) -> Unit, type: PostOptionType) {
        callback(type)
        this.dismiss()
    }
}
