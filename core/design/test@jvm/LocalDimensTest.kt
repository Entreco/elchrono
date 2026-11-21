package nl.entreco.elchrono.core.design

import androidx.compose.runtime.AbstractApplier
import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.unit.dp
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDimensTest {
    @Test
    fun defaultsAndNestedOverridesAreScopedToTheirComposition() {
        val seen = mutableListOf<Dimens>()
        val outer = Dimens(spaceXS = 6.dp)
        val inner = outer.copy(spaceM = 10.dp)
        val recomposer = Recomposer(EmptyCoroutineContext)
        val composition = Composition(TestApplier(), recomposer)
        try {
            composition.setContent {
                seen.add(LocalDimens.current)
                CompositionLocalProvider(LocalDimens provides outer) {
                    seen.add(LocalDimens.current)
                    ElChronoTheme {
                        seen.add(LocalDimens.current)
                        CompositionLocalProvider(LocalDimens provides inner) {
                            seen.add(LocalDimens.current)
                        }
                    }
                    seen.add(LocalDimens.current)
                }
                seen.add(LocalDimens.current)
            }
            assertEquals(listOf(Dimens(), outer, outer, inner, outer, Dimens()), seen)
        } finally {
            composition.dispose()
            recomposer.cancel()
        }
    }

    private class TestApplier : AbstractApplier<Unit>(Unit) {
        override fun insertTopDown(index: Int, instance: Unit) = Unit
        override fun insertBottomUp(index: Int, instance: Unit) = Unit
        override fun remove(index: Int, count: Int) = Unit
        override fun move(from: Int, to: Int, count: Int) = Unit
        override fun onClear() = Unit
    }
}
