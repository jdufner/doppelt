package de.jdufner.doppelt.utils;

public final class NumberUtil {

  private NumberUtil() {
  }

  public static int liefereZufallszahl(final int vonInklusive, final int bisExklusive) {
    return vonInklusive + (int) (Math.random() * bisExklusive);
  }

}
