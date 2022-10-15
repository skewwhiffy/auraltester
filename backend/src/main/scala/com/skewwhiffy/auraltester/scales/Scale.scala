package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.AbsoluteNote

object Scale {
  lazy val major: AbsoluteNote => Scale = note => Scale()
}

class Scale {
  lazy val notes: List[AbsoluteNote] = ???
}
