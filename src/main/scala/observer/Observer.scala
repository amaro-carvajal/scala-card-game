package observer

trait Observer[T]:
  def update(sub: Subject[T], response: T): Unit