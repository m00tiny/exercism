object CollatzCalculator {
  fun computeStepCount(start: Int): Int {
  var count = 0
  var run = start
  while (run != 1) {
	  if (run % 2 == 0) {
			count += 1
			run /= 2
		} else {
			count += 1
			run *= 3
			run += 1
      }
	  }
	return count
  }
}
