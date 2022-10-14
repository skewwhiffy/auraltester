package com.skewwhiffy.auraltester.helper;

public class StreamHelper {
  @SuppressWarnings("unused")
  public static <T> T noParallel(T first, T second) {
    throw new UnsupportedOperationException("No parallel");
  }
}
