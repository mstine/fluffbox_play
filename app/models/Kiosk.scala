package models

import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.Play.current

case class Kiosk(store: String, inside: Boolean, address: String, city: String, state: String, zipCode: String, id: Pk[Long] = NotAssigned)

object Kiosk {

  implicit val kioskFormat = (
    (__ \ "store").format[String] and
      (__ \ "inside").format[Boolean] and
      (__ \ "address").format[String] and
      (__ \ "city").format[String] and
      (__ \ "state").format[String] and
      (__ \ "zipCode").format[String] and
      (__ \ "id").format[Long]
    )((store, inside, address, city, state, zipCode, id) => Kiosk(store, inside, address, city, state, zipCode, Id(id)),
      (k: Kiosk) => (k.store, k.inside, k.address, k.city, k.state, k.zipCode, k.id.get))

  val kiosk = {
    get[Pk[Long]]("id") ~
      get[String]("store") ~
      get[String]("address") ~
      get[String]("city") ~
      get[String]("state") ~
      get[String]("zipCode") ~
      get[Boolean]("inside") map {
      case id ~ store ~ address ~ city ~ state ~ zipCode ~ inside => Kiosk(store, inside, address, city, state, zipCode, id)
    }
  }

  def all(): List[Kiosk] = DB.withConnection {
    implicit c =>
      SQL("select * from kiosk").as(kiosk *)
  }

  def count(): Long = DB.withConnection {
    implicit c =>
      SQL("select count(*) from kiosk").as(scalar[Long].single)
  }

  def create(kiosk: Kiosk) = DB.withConnection {
    implicit c =>
      SQL("insert into kiosk (store, address, city, state, zipCode, inside) values ({store}, {address}, {city}, {state}, {zipCode}, {inside})").on(
        'store -> kiosk.store,
        'address -> kiosk.address,
        'city -> kiosk.city,
        'state -> kiosk.state,
        'zipCode -> kiosk.zipCode,
        'inside -> kiosk.inside
      ).executeUpdate()
  }

}
