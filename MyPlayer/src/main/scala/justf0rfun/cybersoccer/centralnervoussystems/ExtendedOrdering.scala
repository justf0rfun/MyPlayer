package justf0rfun.cybersoccer.centralnervoussystems
trait ExtendedOrdering[T] extends Ordering[T] {

	def max(iterable: Iterable[T]): Option[Seq[T]] = {
		if (iterable.isEmpty) {
			return None
		}
		val iterator = iterable.iterator
		var maxList = List(iterator.next())
		iterator.foreach(
			e => compare(maxList.head, e) match {
				case i if (i < 0) =>
				case i if (i == 0) => maxList = e :: maxList
				case i if (0 < i) => maxList = List(e)
			})
		if (maxList.isEmpty) {
			return None
		}
		return new Some(maxList)
	}

	def min(iterable: Iterable[T]): Option[Seq[T]] = {
		if (iterable.isEmpty) {
			return None
		}
		val iterator = iterable.iterator
		var minList = List(iterator.next())
		iterator.foreach(
			e => compare(minList.head, e) match {
				case i if (i < 0) => minList = List(e)
				case i if (i == 0) => minList = e :: minList
				case i if (0 < i) => 
			})
		if (minList.isEmpty) {
			return None
		}
		return new Some(minList)
	}

}