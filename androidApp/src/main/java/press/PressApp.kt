package press

import android.app.Application
import android.os.Looper
import press.di.AppComponent
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import me.saket.press.shared.di.SharedComponent

abstract class PressApp : Application() {

  companion object {
    lateinit var component: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    component = buildDependencyGraph()
    SharedComponent.initialize(this)

    RxAndroidPlugins.setInitMainThreadSchedulerHandler {
      AndroidSchedulers.from(Looper.getMainLooper(), true)
    }

    // todo: use workmanager to schedule syncing in the background.
  }

  abstract fun buildDependencyGraph(): AppComponent
}