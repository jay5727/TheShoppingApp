package com.jay.theshoppingapp.core.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, device = TABLET)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, device = TABLET)
annotation class TabletPreview
