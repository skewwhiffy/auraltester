package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.AbsoluteNote

trait NoteSequence {
  def notes: List[AbsoluteNote]
}
