package org.sopt.holix.presentation.chatting.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.holix.R
import org.sopt.holix.core.designsystem.theme.HolixAndroidTheme
import org.sopt.holix.core.designsystem.theme.HolixTheme
import org.sopt.holix.core.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChattingTextField(
    chat : String,
    onClickSendButton : () -> Unit,
    onTextChanged : (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Row (
        modifier = modifier
            .fillMaxWidth()
            .background(HolixTheme.colors.white),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_add_circle),
            contentDescription = stringResource(R.string.chat_add_circle_content_description),
            modifier = modifier
                .padding(start = 16.dp, end = 8.dp),
            tint = Color.Unspecified
        )

        BasicTextField(
            value = chat,
            onValueChange = onTextChanged,
            modifier = modifier
                .weight(1f)
                .background(Color.Transparent),
            textStyle = HolixTheme.typography.body3R15,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            visualTransformation = VisualTransformation.None,
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = chat,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = true,
                    interactionSource = interactionSource,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.chat_text_field_placeholder),
                            style = HolixTheme.typography.body3R15,
                            color = HolixTheme.colors.gray04
                        )
                    },
                    visualTransformation = VisualTransformation.None
                )
            }
        )

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_send),
            contentDescription = stringResource(R.string.chat_send_content_description),
            modifier = modifier
                .padding(start = 13.dp, end = 16.dp)
                .noRippleClickable {
                    if (chat.isNotEmpty()) {
                        onClickSendButton()
                    }
                },
            tint = if (chat.isEmpty()) {
                Color.Unspecified
            } else {
                HolixTheme.colors.mainBlue
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChattingTextFieldPreview() {
    HolixAndroidTheme {
        ChattingTextField(
            chat = "",
            onTextChanged = {

            },
            onClickSendButton = {

            }
        )
    }
}