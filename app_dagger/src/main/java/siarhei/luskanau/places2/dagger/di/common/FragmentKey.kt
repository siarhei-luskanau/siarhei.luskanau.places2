package siarhei.luskanau.places2.dagger.di.common

import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(value = RUNTIME)
@MapKey
internal annotation class FragmentKey(val value: KClass<out Fragment>)