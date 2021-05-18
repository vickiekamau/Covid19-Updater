package com.example.jokam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jokam.databinding.ActivitySignupBinding
import com.example.jokam.support.InputValidator
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.HashMap

class Signup : AppCompatActivity() {
    //late initialization
    private lateinit var activitySignupBinding: ActivitySignupBinding
    private lateinit var mCallbackManager: CallbackManager
    private lateinit var mFirebaseAuth: FirebaseAuth
    private lateinit var tokenTracker: AccessTokenTracker
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = ""
    private var TAG: String = "FacebookAuthentication"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        activitySignupBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(activitySignupBinding.root)


        mFirebaseAuth = FirebaseAuth.getInstance()
        FacebookSdk.sdkInitialize(this.applicationContext)

        //when facebook button clicked a callback is registered which gives success or failure result
        mCallbackManager = CallbackManager.Factory.create()
        activitySignupBinding.loginButton.setReadPermissions("public_profile", "email")
        activitySignupBinding.loginButton.registerCallback(mCallbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d(TAG, "Facebook token: " + loginResult.accessToken.token)
                    handleFacebookToken(loginResult.accessToken)
                   // startActivity(Intent(applicationContext, MainActivity::class.java))
                }

                override fun onCancel() {
                    Log.d(TAG, "Facebook onCancel.")

                }

                override fun onError(error: FacebookException) {
                    Log.d(TAG, "Facebook onError.")

                }
            })
        activitySignupBinding.signIn.setOnClickListener(View.OnClickListener { startActivity(Intent(this,MainActivity::class.java)) })
        activitySignupBinding.signUp.setOnClickListener(View.OnClickListener { view ->  inputValidation() })

        authStateListener = FirebaseAuth.AuthStateListener {
            fun onAuthStateChanged(firebaseAuth: FirebaseAuth){
                var user: FirebaseUser = firebaseAuth.currentUser
                 if(user!=null){
                    updateUI(user)
                }else{
                    updateUI(null)
                }
            }
        }


       /* tokenTracker = new AccessTokenTracker(){
             fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken, currentAccessToken: AccessToken){
                if (currentAccessToken == null) {
                    mFirebaseAuth.signOut()
                }
            }
        }*/


    }

    private fun inputValidation() {
        val validator = InputValidator()
        if (validator.validateEmailOptional(
                activitySignupBinding.emailLayout,
                activitySignupBinding.emailInput
            ) &&
            validator.validateRequired(
                activitySignupBinding.userNameLayout,
                activitySignupBinding.userNameInput
            )
            && validator.validateConfirmPassword(
                activitySignupBinding.passwordLayout,
                activitySignupBinding.passwordInput,
                activitySignupBinding.confirmPasswordInput
            ) && validator.validatePassword(
                activitySignupBinding.passwordLayout,
                activitySignupBinding.passwordInput
            )
        ) {
            signUp(
                Objects.requireNonNull(activitySignupBinding.userNameInput.text).toString(),
                Objects.requireNonNull(activitySignupBinding.emailInput.text).toString(),
                Objects.requireNonNull(activitySignupBinding.passwordInput.text).toString()
            );
        }
    }

    private fun signUp(username: String, email: String, password: String) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                   // val user = mFirebaseAuth.currentUser
                   // updateUI(user)
                    firebaseUserId = mFirebaseAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("users").child(firebaseUserId)

                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserId
                    userHashMap["username"] = username
                    userHashMap["email"] =  email
                    userHashMap["password"] = password

                    refUsers.updateChildren(userHashMap).addOnCompleteListener{ task ->
                        if(task.isSuccessful){
                            startActivity(Intent(this,MainActivity::class.java))
                        }
                    }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }


    private fun handleFacebookToken(accessToken: AccessToken) {
        Log.d(TAG, "Facebook token: " + accessToken.token)
        var credential: AuthCredential = FacebookAuthProvider.getCredential(accessToken.token)
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
               if (task.isSuccessful){
                   Log.d(TAG, "signIn with Credentials:success")
                   var user = mFirebaseAuth.currentUser
                   updateUI(user)
               } else {
                   Log.d(TAG, "signIn with Credentials:Failure", task.exception)
                   Toast.makeText(baseContext,"Authentication Failed", Toast.LENGTH_SHORT).show()
                   updateUI(null)
               }
            
        }
    }

    private fun updateUI(user: FirebaseUser?) {
               if(user != null){
                   activitySignupBinding.userNameInput.setText(user.displayName)
                   activitySignupBinding.emailInput.setText(user.email)
               }else {
                   activitySignupBinding.userNameInput.setText("")
                   activitySignupBinding.emailInput.setText("")
               }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
     }

    override fun onStart() {
        super.onStart()
        mFirebaseAuth.addAuthStateListener(authStateListener)

        //check if user is signed in (non-null) and update UI accordingly
        val currentUser = mFirebaseAuth.currentUser
        if(currentUser != null){

        }
     }

    override fun onStop() {
        super.onStop()

        if(authStateListener!= null){
            mFirebaseAuth.removeAuthStateListener(authStateListener)
        }
    }
}