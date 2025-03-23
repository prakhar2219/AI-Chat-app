package com.example.rungta.GoogleSignIn

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.rungta.ChatViewModel
import com.example.rungta.R
import com.example.rungta.SignInResult
import com.example.rungta.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuth(
    private val context: Context,
    private val oneTapClient: SignInClient,
    val viewModel: ChatViewModel
) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        return try {
            val result = oneTapClient.beginSignIn(buildSignInRequest()).await()
            result?.pendingIntent?.intentSender
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        val serverClientId = context.getString(R.string.default_web_client_id)
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(serverClientId)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        viewModel.resetState()
        return try {
            val cred = oneTapClient.getSignInCredentialFromIntent(intent)
            val googleIdToken = cred.googleIdToken
            val googleCred = GoogleAuthProvider.getCredential(googleIdToken, null)
            val user = auth.signInWithCredential(googleCred).await().user

            SignInResult(
                errorMessage = null,
                data = user?.let {
                    UserData(
                        email = it.email ?: "No email",
                        userId = it.uid,
                        username = it.displayName ?: "Anonymous",
                        ppUrl = it.photoUrl?.toString()?.removeSuffix("=s96-c") ?: ""
                    )
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                errorMessage = "Sign-in failed. Please try again.",
                data = null
            )
        }
    }
}
