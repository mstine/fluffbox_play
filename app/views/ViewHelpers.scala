package views

object ViewHelpers {

  def pages(total: Long, max: Int = 8): Int =
    if (total % max == 0) (total.toInt / max) - 1 else (total.toInt / max)

  def currentPage(offset: Int, max: Int = 8): Int = offset / max

  def previousOffset(offset: Int, max: Int = 8): Int = (currentPage(offset, max) - 1) * max

  def nextOffset(offset: Int, max: Int = 8): Int = (currentPage(offset, max) + 1) * max

}
