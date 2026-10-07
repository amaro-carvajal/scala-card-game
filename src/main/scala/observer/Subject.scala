package observer

trait Subject[T]:
  def attach(observer: Observer[T]): Unit

  def detach(observer: Observer[T]): Unit

  def notifyObservers(notification: T): Unit
