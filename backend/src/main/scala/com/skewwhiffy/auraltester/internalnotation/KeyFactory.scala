package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.scales.Key
import org.springframework.stereotype.Component

@Component
class KeyFactory {
  def getKey(rawKey: String): Key = ???
}
