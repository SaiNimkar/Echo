package com.sai.echo.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.sai.echo.R


class SplashActivity : AppCompatActivity() {

    var permissionString = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.READ_PHONE_STATE, Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.RECORD_AUDIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (!hasPermissions(this@SplashActivity, *permissionString)) {
            /*Now wkhen the permissions were not granted, we want our application to ask for permissions. This is done with the help of
            * the lines of code written below*/
            ActivityCompat.requestPermissions(this@SplashActivity, permissionString, 131)/*We request the user for permissions by
                                                                                                            passing the array of permissions.
                                                                                                            The request code is the unique number,
                                                                                                            with which the Android OS will identify
                                                                                                            which request was fired. We used 131,
                                                                                                            you can use any other distinct number*/
        } else {
            /*Let's understand this in an easy way. The handler is used to handle the tasks i.e. it is used to delay the tasks which need to be
            * performed. Here we are delaying the opening of the next activity by 1000ms (1 sec) in order to make the welcome/splash screen visible
            * to the user and give our app a nice user experience(known as the UX)*/
            Handler().postDelayed({
                val startAct = Intent(this@SplashActivity, MainActivity::class.java)/*As we already know this is used to define the
                                                                                                  path of navigation from one activity to another*/
                startActivity(startAct) /*This statement is used to launch the new activity*/
                this.finish() /*Now, this statement is used to finish the current activity when the user moves on to the next activity.
                                This prevents the user to view the splash again by pressing back button, which won't be a good UX*/
            }, 1000)
        }
    }
    /*This is the method called when the user has completed the actions to be taken when the permissions were asked for. Now, its time to check the
    * result of the permissions request. This was eatlier not present till the android version 6.0.0(Marshmallow) or API 23*/



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            131 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED) {
                    Handler().postDelayed({
                        val startAct = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(startAct)
                        this.finish()
                    }, 1000)

                }else{
                    Toast.makeText( this@SplashActivity, "Please grant all permissions", Toast.LENGTH_SHORT)
                            .show()
                    this.finish()
                }
                return
            }

                else -> {
                Toast.makeText(this@SplashActivity, "Something went wrong", Toast.LENGTH_SHORT)
                        .show()
                this.finish()
            }
        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean {

        var hasAllPermissions = true

        for (permission in permissions) {
            val res = context.checkCallingOrSelfPermission(permission)
            if (res != PackageManager.PERMISSION_GRANTED) {
                hasAllPermissions = false
            }
        }

        return hasAllPermissions
    }
 }

