package com.example.kiaerror

import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@NetworkScope
@Component
abstract class ClientComponent {

    @NetworkScope
    @Provides
    fun provideNetworkScopeObject(): NetworkScopeObject {
        return NetworkScopeObject()
    }

}