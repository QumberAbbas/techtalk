package com.coders.springtest.base

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner.StrictStubs

@RunWith(StrictStubs::class)
abstract class BaseTest {
    @Rule @JvmField
    val rule = MockitoJUnit.rule()
    @Rule @JvmField
    public val overrideSchedulersRule = RxSchedulerOverrideRule()
}