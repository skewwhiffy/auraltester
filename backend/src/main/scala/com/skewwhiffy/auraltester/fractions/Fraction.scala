package com.skewwhiffy.auraltester.fractions

import com.skewwhiffy.auraltester.fractions.Fraction.{hcf, lcm}

import scala.annotation.tailrec
import scala.util.chaining.scalaUtilChainingOps

object Fraction {
  @tailrec
  def apply(top: Int, bottom: Int): Fraction = bottom match {
    case 0 => throw new ArithmeticException("/ by zero")
    case it if it < 0 => Fraction(-top, -bottom)
    case _ => new Fraction(top, bottom)
  }

  def apply(whole: Int, top: Int, bottom: Int): Fraction =
    new Fraction(whole * bottom + top, bottom)

  def lcm(first: Int, second: Int): Int = {
    if (first <= 0 || second <= 0) {
      throw new ArithmeticException("Cannot find lcm for non-negative integer")
    }
    if (first < second) {
      return lcm(second, first)
    }
    Range(1, second + 1).find(it => (first * it) % second == 0) match {
      case None => throw new ArithmeticException("Rules of mathematics have changed.")
      case Some(it) => first * it
    }
  }

  def hcf(first: Int, second: Int): Int = first * second / lcm(first, second)
}

class Fraction(val top: Int, val bottom: Int) {
  // Can assume bottom > 0
  lazy val whole: Int = top / bottom
  lazy val topWithoutWhole: Int = top - (whole * bottom)
  lazy val simplified: Fraction = if (top == 0) Fraction(0, 1) else hcf(top, bottom) match {
    case 1 => this
    case it => Fraction(top / it, bottom / it)
  }

  def +(other: Fraction): Fraction = {
    val lcd = lcm(bottom, other.bottom)
    val newTop = top * (lcd / bottom) + other.top * (lcd / other.bottom)
    Fraction(newTop, lcd).simplified
  }

  def -(other: Fraction): Fraction = this + -other

  def *(other: Fraction): Fraction = Fraction(top * other.top, bottom * other.bottom).simplified

  def unary_- : Fraction = Fraction(-top, bottom)

  lazy val topHeavyString: String = if (bottom == 1) s"$top" else s"$top/$bottom"

  override lazy val toString: String = (if (whole == 0) "" else s"$whole ")
    .pipe(it => s"$it$topWithoutWhole/$bottom")

  override def equals(obj: Any): Boolean = obj match {
    case it: Int => this == Fraction (it, 1)
    case other: Fraction => simplified.top == other.simplified.top && simplified.bottom == other.simplified.bottom
    case _ => false
  }
}