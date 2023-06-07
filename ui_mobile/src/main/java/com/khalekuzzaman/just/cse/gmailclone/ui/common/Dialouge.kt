package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialogue(
    onCrossButtonClick:()->Unit,
    modifier: Modifier = Modifier,

) {

    val googleText = buildAnnotatedString {
        append(AnnotatedString("G", spanStyle = SpanStyle(Color.Blue)))
        append(AnnotatedString("o", spanStyle = SpanStyle(Color.Red)))
        append(AnnotatedString("o", spanStyle = SpanStyle(Color.Yellow)))
        append(AnnotatedString("g", spanStyle = SpanStyle(Color.Blue)))
        append(AnnotatedString("l", spanStyle = SpanStyle(Color.Green)))
        append(AnnotatedString("e", spanStyle = SpanStyle(Color.Red)))
    }

        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
              //  shouldOpenDialog= false
            }
        ) {
            Card(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier =
                        Modifier,
                        onClick = onCrossButtonClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cross),
                            contentDescription = null
                        )
                    }
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        text = googleText,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Surface(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Put the content here",
                        )

                    }
                }
            }


        }

}
