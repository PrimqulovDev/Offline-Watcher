package app.test.offlinewatcher.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import app.test.offlinewatcher.R
import app.test.offlinewatcher.base.BaseActivity
import app.test.offlinewatcher.utils.extensions.gone
import app.test.offlinewatcher.utils.extensions.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {

    val sharedViewModel: SharedViewModel by viewModels()

    override fun initialize(savedInstanceState: Bundle?) {
        navigateIfNeed(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateIfNeed(intent)
    }

    fun navigateIfNeed(intent: Intent?) {

    }

    var isLoading: Boolean
        get() = pbMain.isVisible
        set(value) {
            if (value) pbMain.visible() else pbMain.gone()
        }
}