package ru.bupyc9.beatbox

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BitBoxFragment: Fragment() {
    companion object {
        @JvmStatic private val COUNT_COLUMNS = 3

        @JvmStatic fun newInstance(): BitBoxFragment = BitBoxFragment()
    }

    private lateinit var mBeatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        mBeatBox = BeatBox(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_beat_box, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.fragment_beat_box_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(activity, COUNT_COLUMNS)
        val adapter = SoundAdapter(mBeatBox.sounds)
        adapter.setOnClickListener { _, sound -> mBeatBox.play(sound) }
        recyclerView.adapter = adapter

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        mBeatBox.release()
    }
}