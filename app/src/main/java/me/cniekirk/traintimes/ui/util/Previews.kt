package me.cniekirk.traintimes.ui.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4,
    name = "Normal Phone"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4_XL,
    name = "Large Phone"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_3A,
    name = "Small Phone"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.TABLET,
    name = "Tablet"
)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_4,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
annotation class DevicesPreview