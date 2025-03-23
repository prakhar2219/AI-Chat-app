package com.example.rungta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.rungta.GoogleSignIn.GoogleAuth
import com.example.rungta.screens.SignInScreen
import com.example.rungta.ui.theme.RungtaTheme
import kotlinx.coroutines.launch
import com.google.android.gms.auth.api.identity.Identity


class MainActivity : ComponentActivity() {
    private val viewModel: ChatViewModel by viewModels()
    private val googleAuthUiClient by lazy {
        GoogleAuth(
            context = applicationContext,
            viewModel = viewModel,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RungtaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = {
                            result ->
if (result.resultCode == RESULT_OK){
    lifecycleScope.launch {
    val signInResult=googleAuthUiClient.signInWithIntent(
        intent = result.data?:return@launch
    )}
}
                        })

                            SignInScreen (onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender=googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender?:return@launch
                                        ).build()
                                    )
                                }
                            })
                        }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        RungtaTheme {
            Greeting("Android")
        }
    }