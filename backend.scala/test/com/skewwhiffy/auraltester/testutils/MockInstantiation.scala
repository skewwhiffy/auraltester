package com.skewwhiffy.auraltester.testutils

import org.mockito.{InjectMocks, Mock, MockitoSugar}
import org.mockito.Mockito
import org.scalatest.{Outcome, TestSuite}
import play.api.mvc.ControllerComponents
import play.api.test.Helpers.stubControllerComponents

trait MockInstantiation extends TestSuite with MockitoSugar {
  override def withFixture(test: NoArgTest): Outcome = {
    val mockFields = getClass
      .getDeclaredFields
      .filter(it => it.getDeclaredAnnotations.exists(it => it.isInstanceOf[Mock]))
    val mocks = mockFields.map(field => {
      field.setAccessible(true)
      val mockInstance = Mockito.mock(field.getType)
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
          case None => if (argType == classOf[ControllerComponents]) stubControllerComponents() else null
          case Some(it) => it
        }
      })
      field.set(this, constructor.newInstance(args:_*))
    })

    test()
  }
}
