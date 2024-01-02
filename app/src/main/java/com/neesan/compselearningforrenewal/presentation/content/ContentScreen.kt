package com.neesan.compselearningforrenewal.presentation.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.TriStateCheckbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ContentScreen(contentId: Long) {
    Column {
        Text(text = "contentId is $contentId")
        // define dependent checkboxes states
        val (state, onStateChange) = remember { mutableStateOf(true) }
        val (state2, onStateChange2) = remember { mutableStateOf(true) }

        // TriStateCheckbox state reflects state of dependent checkboxes
        val parentState = remember(state, state2) {
            if (state && state2) ToggleableState.On
            else if (!state && !state2) ToggleableState.Off
            else ToggleableState.Indeterminate
        }
        // click on TriStateCheckbox can set state for dependent checkboxes
        val onParentClick = {
            val s = parentState != ToggleableState.On
            onStateChange(s)
            onStateChange2(s)
        }

        // The sample below composes just basic checkboxes which are not fully accessible on their
        // own. See the CheckboxWithTextSample as a way to ensure your checkboxes are fully
        // accessible.
        TriStateCheckbox(
            state = parentState,
            onClick = onParentClick,
        )
        Spacer(Modifier.size(25.dp))
        Column(Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) {
            Checkbox(state, onStateChange)
            Spacer(Modifier.size(25.dp))
            Checkbox(state2, onStateChange2)
        }

        // 参考: https://developers.google.com/maps/documentation/android-sdk/maps-compose?hl=ja
        val singapore = LatLng(1.35, 103.87)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = singapore),
                title = "Singapore",
                snippet = "Marker in Singapore"
            )
        }

//        var uiSettings by remember { mutableStateOf(MapUiSettings()) }
//        var properties by remember {
//            mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
//        }
//
//        Box(Modifier.fillMaxSize()) {
//            GoogleMap(
//                modifier = Modifier.matchParentSize(),
//                properties = properties,
//                uiSettings = uiSettings
//            )
//            Switch(
//                checked = uiSettings.zoomControlsEnabled,
//                onCheckedChange = {
//                    uiSettings = uiSettings.copy(zoomControlsEnabled = it)
//                }
//            )
//        }
    }
}

@Preview
@Composable
fun ContentScreenPreview() {
    ContentScreen(1)
}