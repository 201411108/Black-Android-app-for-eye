package com.example.black

import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main_2.*

//TODO("3개에 대해서 Seekbar 만들기")

//class PeriodSeekBarListener1 : SeekBar.OnSeekBarChangeListener{
//
//    var seekBar = R.id.periodSeekBar as SeekBar
//    var seekBarText = R.id.periodSeekBarText as TextView
//
//    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//        this.seekBar.progress = (progress/10)*10
//        seekBarText.text = progress.toString()
//        R.id.sustainTimeSeekBar
//    }
//
//    override fun onStartTrackingTouch(seekBar: SeekBar?) {
//    }
//
//    override fun onStopTrackingTouch(seekBar: SeekBar?) {
//    }
//}
//
//class SustainTimeSeekBarListener : SeekBar.OnSeekBarChangeListener{
//
//    var seekBar = R.id.sustainTimeSeekBar as SeekBar
//    var seekBarText = R.id.sustainTimeSeekBarText as TextView
//
//    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//        this.seekBar.progress = (progress/10)*10
//        seekBarText.text = progress.toString()
//    }
//
//    override fun onStartTrackingTouch(seekBar: SeekBar?) {
//    }
//
//    override fun onStopTrackingTouch(seekBar: SeekBar?) {
//    }
//}
//
//class ColorSeekBarListener : SeekBar.OnSeekBarChangeListener{
//
//    var seekBar = R.id.colorSeekBar as SeekBar
//    var seekBarText = R.id.colorSeekBarText as TextView
//
//    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//        this.seekBar.progress = (progress/10)*10
//        seekBarText.text = progress.toString()
//    }
//
//    override fun onStartTrackingTouch(seekBar: SeekBar?) {
//    }
//
//    override fun onStopTrackingTouch(seekBar: SeekBar?) {
//    }
//}