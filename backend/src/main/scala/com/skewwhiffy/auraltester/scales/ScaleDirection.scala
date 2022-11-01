package com.skewwhiffy.auraltester.scales

object ScaleDirection {
  case object ascending extends ScaleDirection("ascending")
  case object descending extends ScaleDirection("descending")
}

class ScaleDirection(val displayString: String)
