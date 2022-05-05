package com.example.chuck.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import com.example.chuck.R
import com.example.chuck.adapters.ViewPagerAdapter
import com.example.chuck.databinding.ActivityMainBinding
import com.example.chuck.events.Events
import com.example.chuck.events.GlobalBus.bus
import com.example.chuck.fragments.RandomJokeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.greenrobot.eventbus.Subscribe
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


val tabsArray = arrayOf(
    "Categories",
    "Random Joke",
    "Favs",
    "Joke Search")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom) }

    private var clicked = false
    private var filename = "records.txt"
    private var smsText = ""

    override fun onStart() {
        super.onStart()
        bus?.register(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        var frag: Fragment? = null
        return when (item.itemId) {
            R.id.menu_day -> {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
                true
            }
            R.id.menu_night -> {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
                delegate.localNightMode
                true
            }
//            R.id.favs -> {
//                val fragmentDemo : Fragment = FavouritesFragment()
//                Log.d("Fragment class: ", fragmentDemo.javaClass.name)
//                val fragmentManager : FragmentManager = this.supportFragmentManager
//                val transaction : FragmentTransaction = fragmentManager.beginTransaction()
//                transaction.replace(R.id.fragmentLayout, fragmentDemo)//nie robi replace tylko add, moze container?
//                transaction.addToBackStack(null)
//                // Commit the transaction
//                transaction.commit()
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        val toolbar = binding.toolbar
        setContentView(view)
        setSupportActionBar(toolbar)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val fab = binding.fab
        val save = binding.saveBtn
        val send = binding.sendBtn

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val allFragments = supportFragmentManager.fragments
                for (fragment in allFragments){
                    if(fragment is RandomJokeFragment){
                        fragment.refreshDataFunction()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()

        fab.setOnClickListener{
            onFabButtonClicked()
        }

        save.setOnClickListener {
//            Toast.makeText(this, "Save button clicked", Toast.LENGTH_SHORT).show()
            saveMessage()
        }

        send.setOnClickListener {
//            Toast.makeText(this, "Send button clicked", Toast.LENGTH_SHORT).show()
            sendSMS("+48663851713",smsText)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    @Suppress("SameParameterValue", "DEPRECATION")
    private fun sendSMS(phoneNumber: String, message: String) {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),1)
        val sentPI: PendingIntent = PendingIntent.getBroadcast(this, 0, Intent("SMS_SENT"), 0)
        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, sentPI, null)
    }

    private fun saveMessage(){
        val file = File(filesDir, this.filename)
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            fos.write(this.smsText.toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
            //Toast.makeText(this, "Failed: " + e.message, Toast.LENGTH_LONG).show()
        } finally {
            if (fos != null) {
                try { //Toast.makeText(this,"Write to $filename successfully!",Toast.LENGTH_LONG).show()
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                //Toast.makeText(this, "Failed to write!", Toast.LENGTH_LONG).show()
            }
        } //readInternalDemo()
    }

//    private fun readInternalDemo() {
//        val file = File(filesDir, this.filename)
//        if (!file.exists()) {
//            Toast.makeText(this, "Failed: file does not exist", Toast.LENGTH_LONG).show()
//            return
//        }
//        var fis: FileInputStream? = null
//        var textContent = ""
//        try {
//            fis = FileInputStream(file)
//            val br = BufferedReader(InputStreamReader(fis))
//            textContent = br.readLine()
//        } catch (e: java.lang.Exception) {
//            Toast.makeText(this, "Failed: " + e.message, Toast.LENGTH_LONG).show()
//        } finally {
//            if (fis != null) {
//                Toast.makeText(this, "Read Successfully: $textContent", Toast.LENGTH_LONG).show()
//                try {
//                    fis.close()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                    Toast.makeText(this, "Failed to read!", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    }

    private fun onFabButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            binding.saveBtn.visibility = View.VISIBLE
            binding.sendBtn.visibility = View.VISIBLE
        } else {
            binding.saveBtn.visibility = View.INVISIBLE
            binding.sendBtn.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if(!clicked){
            binding.saveBtn.startAnimation(fromBottom)
            binding.sendBtn.startAnimation(fromBottom)
            binding.fab.startAnimation(rotateOpen)
        } else {
            binding.saveBtn.startAnimation(toBottom)
            binding.sendBtn.startAnimation(toBottom)
            binding.fab.startAnimation(rotateClose)
        }
    }

    //działa, jak wrzucić to w sendSMS
    @Subscribe
    fun getMessage(fragmentActivityMessage: Events.FragmentToActivityMessage) {
//        Toast.makeText(applicationContext,getString(R.string.message_main_activity) + " " + fragmentActivityMessage.message,Toast.LENGTH_SHORT).show()
        smsText = fragmentActivityMessage.message
    }

    override fun onStop() {
        super.onStop()
        bus?.unregister(this)
    }
}