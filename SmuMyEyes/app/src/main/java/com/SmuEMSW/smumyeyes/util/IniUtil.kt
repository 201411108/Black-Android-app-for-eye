/*
 * IniUtil.kt
 * @handongkim
 * ini 파일 관리를 위한 클래스
 * history
 * 20191217     handongkim      초안 작성
 */

package com.example.black.util

import android.content.Context
import org.ini4j.Ini
import org.ini4j.Wini
import java.io.File
import java.io.IOException

class IniUtil(context : Context) {

    // ini파일 저장 경로 : /data/data/com.example.black.util/files/info/loadIni.ini
    private var iniFileDir = context.filesDir.toString() + "/info"
    private var iniFilePath = iniFileDir + "/loadIni.ini"

    fun setup() {
        try {
            val user_ini_dir = File(iniFileDir)
            if(!user_ini_dir.exists()) {
                user_ini_dir.mkdir()
            }
            val user_ini = File(iniFilePath)
            if(!user_ini.exists()) {
                user_ini.createNewFile()
                init()
            }
        } catch(e : IOException) {

        }
    }

    fun init() {
        setIni("loadInfo", "color", "50")
        setIni("loadInfo", "period", "10")
        setIni("loadInfo", "sustain", "200")
    }

    fun setIni(sectionName : String, optionName : String, value : String) {
        try {
            val userIniFile = File(iniFilePath)
            val wini = Wini(userIniFile)
            wini.put(sectionName, optionName, value)
            wini.store()
        } catch (e : IOException) {

        }
    }

    fun getIni(section : String, key : String) : String? {
        try {
            val userIniFile = File(iniFilePath)
            val user_ini = Ini(userIniFile)
            return user_ini.get(section, key)
        } catch(e : IOException) {
            return null
        }
    }
}