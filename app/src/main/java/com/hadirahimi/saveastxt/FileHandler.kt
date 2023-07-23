package com.hadirahimi.saveastxt

import android.content.Context
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader

class FileHandler(private val context:Context)
{
    private val fileName = "asmrKotlin.txt"
    //Function to read text from te file
    fun readText(): String{
        return try
        {
            val fileInputStream = context.openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text : String?
            while (bufferedReader.readLine().also { text = it } != null ){
                stringBuilder.append(text)
            }
            stringBuilder.toString()
        }catch (e:FileNotFoundException)
        {
            //Handle the case when the file does not exist
            ""
        }catch (e : Exception)
        {
            //Handle other possible exceptions
            e.printStackTrace()
            ""
        }
    }
    
    //function to write text to the file
    fun writeText(inputData : String)
    {
        try
        {
            val fileOutputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE)
            fileOutputStream.write(inputData.toByteArray())
            fileOutputStream.close() // close the stream after writing
        }catch (e:Exception)
        {
            //Handle IO Exception
            e.printStackTrace()
        }
    }
}










