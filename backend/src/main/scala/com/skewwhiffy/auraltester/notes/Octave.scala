package com.skewwhiffy.auraltester.notes

import scala.annotation.targetName

object Octave:
  lazy val default: Octave = Octave(0)

class Octave(val offsetFromDefault: Int):
  lazy val getAbc: Note => String = note => offsetFromDefault match
    case 0 => note.abc
    case it if it > 0 => s"${note.abc.toLowerCase}${"'".repeat(it - 1)}"
    case it if it < 0 => s"${note.abc}${",".repeat(-it)}"

  lazy val up: Octave = Octave(offsetFromDefault + 1)

  lazy val down: Octave = Octave(offsetFromDefault - 1)

  @targetName("higherThan")
  def >(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault

  @targetName("lowerThan")
  def <(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault

  @targetName("higherThanOrEqualTo")
  def >=(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault || this == other

  @targetName("lowerThanOrEqualTo")
  def <=(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault || this == other

  override def equals(obj: Any): Boolean = obj match
    case it: Octave => it.offsetFromDefault == offsetFromDefault
    case _ => false
