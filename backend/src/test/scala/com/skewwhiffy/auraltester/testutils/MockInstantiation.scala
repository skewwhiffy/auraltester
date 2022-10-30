package com.skewwhiffy.auraltester.testutils

import org.mockito.{InjectMocks, Mock}
import org.mockito.Mockito.mock
import org.scalatest.{Outcome, TestSuite}

trait MockInstantiation extends TestSuite {
  override def withFixture(test: NoArgTest): Outcome = {
    val mockFields = getClass
      .getDeclaredFields
      .filter(it => it.getDeclaredAnnotations.exists(it => it.isInstanceOf[Mock]))
    val mocks = mockFields.map(field => {
      field.setAccessible(true)
      val mockInstance = mock(field.getType)
      field.set(this, mockInstance)
      mockInstance
    })
    val injectFields = getClass
      .getDeclaredFields
      .filter(it => it.getDeclaredAnnotations.exists(it => it.isInstanceOf[InjectMocks]))
    injectFields.foreach(field => {
      field.setAccessible(true)
      val constructors = field.getType.getConstructors
      if (constructors.size != 1) {
        throw new IllegalArgumentException("Multiple constructors")
      }
      val constructor = constructors(0)
      val args: Array[Any] = constructor.getParameterTypes.map(argType => {
        mocks.find(it => argType.isAssignableFrom(it.getClass)) match {
          case None => null
          case Some(it) => it
        }
      })
      field.set(this, constructor.newInstance(args:_*))
    })

    test()
  }
}
