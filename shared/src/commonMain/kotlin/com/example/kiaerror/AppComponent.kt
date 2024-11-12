package com.example.kiaerror

import me.tatarka.inject.annotations.Component
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent

/**
 * This was a fix I did for 0.0.5 where each platform had this interface just inherit from the actual generated
 * `AppComponentMerged`. It was working but when updated to 0.1.0 this technique breaks as we the compiler
 * checks if we actually inheriting against `AppComponentMerged`
 */
expect interface AppComponentMerged2

/**
 * This is internal because in my real project it is in my common shared library. I don't want my
 * Android, JVM, or WASM modules that depend on this common library to be able to create this component.
 * Would prefer this to remain internal but not the end of the world if it doesn't
 */
@AppScope
@Component
@MergeComponent(AppScope::class)
internal abstract class AppComponent(
    @Component val clientComponent: ClientComponent,
): AppComponentMerged2 {
    abstract val networkScopeObject: NetworkScopeObject
}