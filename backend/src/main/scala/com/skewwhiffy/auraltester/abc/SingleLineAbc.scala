package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.AbsoluteNote

class SingleLineAbc(
  private val displayName: String,
  private val clef: Clef,
  private val bars: List[AbsoluteNote]
)
