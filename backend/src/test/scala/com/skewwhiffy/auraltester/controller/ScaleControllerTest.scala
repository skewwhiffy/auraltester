package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.scales.ScaleTypeFactory
import com.skewwhiffy.auraltester.services.ScaleService
import com.skewwhiffy.auraltester.testutils.MockInstantiation
import org.mockito.{InjectMocks, Mock}
import org.scalatest.funsuite.AnyFunSuite

class ScaleControllerTest extends AnyFunSuite with MockInstantiation {
  @Mock
  private val internalNotationFactory: InternalNotationFactory = null
  @Mock
  private val scaleService: ScaleService = null
  @Mock
  private val scaleTypeFactory: ScaleTypeFactory = null
  @InjectMocks
  private val scaleController: ScaleController = null

}