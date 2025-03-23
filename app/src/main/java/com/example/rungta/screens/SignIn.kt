package com.example.rungta.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rungta.R

@Preview(showSystemUi = true)
@Composable
fun SignInScreen() {
//    styling dene ke liye ..agr hmko do colours kaa use krna hai simultaneously to hum brush ke object kaa use krte hai
    val brush = Brush.linearGradient(
        listOf(
            Color(0xFF238CDD),
            Color(0xFF255DCC)
        )
    )
    Image(
        painter = painterResource(id = R.drawable.login_blur),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(70.dp))
        Image(
            painter = painterResource(id = R.drawable.hii),
            contentDescription = null,
            modifier = Modifier
                .height(400.dp)
                .width(400.dp)
        )

        Text(
            text = "Rungta Project",
//    to use/modify some properties of the typography we have used th ecopy function
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Companion.ExtraBold),
            color = Color(0xFF101010)
        )
        Text(
            text = "Our 6th sem project an ai-powered chatting application, " +
                    "incorporating various AI-driven       features along with normal chatting features",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color(0xFF101010)
        )
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .background(brush, CircleShape)
                .fillMaxWidth(.7f)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = CircleShape
        ) {
            Text(
                text = "Continue with Google",
                modifier = Modifier.padding(end = 20.dp),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Image(
                painter = painterResource(id = R.drawable.goog_0ed88f7c), contentDescription = null,
                modifier = Modifier.scale(1.2f)
            )
        }
    }
}
