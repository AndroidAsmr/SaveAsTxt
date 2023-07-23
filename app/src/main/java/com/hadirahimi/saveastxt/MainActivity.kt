package com.hadirahimi.saveastxt

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hadirahimi.saveastxt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityMainBinding
    private lateinit var fileHandler : FileHandler
    
    companion object{
        //request code for external storage permission
        private const val PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1
    }
    
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //init file handler
        fileHandler = FileHandler(this@MainActivity)
        checkWriteExternalStoragePermission()
        with(binding)
        {
            btnSave.setOnClickListener {
                if (etYourText.text.toString().isNotEmpty())
                {
                    fileHandler.writeText(etYourText.text.toString())
                    Toast.makeText(this@MainActivity , R.string.data_saved , Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@MainActivity , R.string.please_enter_text , Toast.LENGTH_SHORT).show()
                }
            }
            btnViewSavedText.setOnClickListener {
                val data = fileHandler.readText()
                if (data.isEmpty())
                {
                    Toast.makeText(this@MainActivity , R.string.no_data , Toast.LENGTH_SHORT).show()
                }else
                {
                    Toast.makeText(this@MainActivity , "Saved Data : $data" , Toast.LENGTH_SHORT).show()
                }
            }
        }
        
    }
    
    private fun checkWriteExternalStoragePermission()
    {
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE)
            }else
            {
                //permission already granted, you can proceed with file writing
                Toast.makeText(this , R.string.permission_already_granted , Toast.LENGTH_SHORT).show()
            }
    }
    
    override fun onRequestPermissionsResult(
        requestCode : Int ,
        permissions : Array<out String> ,
        grantResults : IntArray
    )
    {
        super.onRequestPermissionsResult(requestCode , permissions , grantResults)
        if (requestCode == PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE)
        {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED){
                //permission denied, handle the situation accordingly
                finish()
                Toast.makeText(this , R.string.permission_denied , Toast.LENGTH_SHORT).show()
            }
        }
    }
    
}












