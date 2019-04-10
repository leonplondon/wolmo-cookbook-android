package ar.com.wolox.android.cookbook.tests

import android.view.View
import androidx.annotation.StringRes
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import java.lang.reflect.ParameterizedType

@RunWith(AndroidJUnit4::class)
open class WolmoActivityTest<T : WolmoActivity>() {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<T>(getViewClazz())

    @Before
    fun beforeWolmoActivity() {
        activityRule.activity.supportFragmentManager.beginTransaction()
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewClazz(): Class<T> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
    }

    protected fun hasErrorText(@StringRes errorRes: Int): Matcher<View> =
            ViewMatchers.hasErrorText(activityRule.activity.getString(errorRes))
}