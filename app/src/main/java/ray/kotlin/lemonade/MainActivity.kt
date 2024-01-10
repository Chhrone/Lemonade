package ray.kotlin.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeApp()
        }
    }
}


@Composable
fun LemonadeCard() {
    var currentStep by remember {
        mutableStateOf(1)
    }

    val requiredClicks = when (currentStep) {
        2 -> (3..7).random()
        else -> 1
    }

    var clicks = 0

    val imageResource = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when (currentStep) {
        1 -> "Tap the lemon tree to select a lemon"
        2 -> "Keep tapping the lemon to squeeze it"
        3 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                clicks++
                if (clicks == requiredClicks) currentStep++
                if (currentStep > 4) currentStep = 1
            },
            modifier = Modifier
                .padding(20.dp)
                .size(250.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFc7f9cc)),
            shape = RoundedCornerShape(30)
        ) {
            Image(painterResource(id = imageResource), contentDescription = currentStep.toString())
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = textResource)
    }
}


@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeCard()
}