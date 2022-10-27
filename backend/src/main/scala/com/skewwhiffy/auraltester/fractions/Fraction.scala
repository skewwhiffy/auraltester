package com.skewwhiffy.auraltester.fractions

import scala.util.chaining.*

object Fraction:
  def apply(top: BigInt, bottom: BigInt): Fraction = new Fraction(top, bottom)
  def apply(whole: BigInt, top: BigInt, bottom: BigInt): Fraction =
    new Fraction(whole * bottom + top, bottom)

class Fraction(private val top: BigInt, private val bottom: BigInt):
  lazy val whole: BigInt = top / bottom
  lazy val topWithoutWhole: BigInt = top - (whole * bottom)
  override lazy val toString: String = (if whole == 0 then "" else s"$whole ")
    .pipe(it => s"$it$topWithoutWhole/$bottom")
