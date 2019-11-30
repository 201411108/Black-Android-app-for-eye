package com.example.black

import android.widget.SeekBar
import android.widget.TextView

//TODO("3개에 대해서 Seekbar 만들기")

//class PeriodSeekBarListener : SeekBar.OnSeekBarChangeListener{
//
//    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//        seekBar?.progress = (progress/10)*10
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