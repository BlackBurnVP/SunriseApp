package com.vitalii.sunriseapp

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun getSunset(view: View){


    }

    inner class MyAsyncTask:AsyncTask<String,String,String>(){
        override fun doInBackground(vararg params: String?): String {

            try {
                val url= URL(params[0])
                val urlConnect=url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout=7000

                var inString = convertStreamToString(urlConnect.inputStream)
                publishProgress(inString)
            }catch (ex:Exception){}
            return ""
        }

        /**
         * Reading JSON data
         */
        override fun onProgressUpdate(vararg values: String?) {
            try {
                val json = JSONObject(values[0])
                val query = json.getJSONObject("query") //repeat for each tag in JSON response
                val result = query.getJSONObject("result") //get next block in JSON response
            }catch (ex:Exception){}
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }
    }

    /**
     * Converts response in String
     * @param inputStream input Stream of url Connection
     */
    fun convertStreamToString(inputStream:InputStream):String{
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line:String?
        var allString:String = ""

        try {
            do{
                line = bufferReader.readLine()
                if(line!=null){
                    allString+=line
                }
            } while (line!=null)
        }catch (ex:Exception){}

        return allString
    }
}
