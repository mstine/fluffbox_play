package models

import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current

case class Speaker(name: String, title: String, imageUrl: String, bio: String, id: Pk[Long] = NotAssigned)

object Speaker {

  val speaker = {
    get[Pk[Long]]("id") ~
      get[String]("name") ~
      get[String]("title") ~
      get[String]("imageUrl") ~
      get[String]("bio") map {
      case id ~ name ~ title ~ imageUrl ~ bio => Speaker(name, title, imageUrl, bio, id)
    }
  }

  def all(offset: Int): List[Speaker] = DB.withConnection {
    implicit c =>
      SQL("select * from speaker limit 8 offset {offset}")
        .on('offset -> offset)
        .as(speaker *)
  }

  def getBy(id: Long): Speaker = DB.withConnection {
    implicit c =>
      SQL("select * from speaker where id = {id}")
        .on('id -> id)
        .as(speaker.single)
  }

  def count(): Long = DB.withConnection {
    implicit c =>
      SQL("select count(*) from speaker").as(scalar[Long].single)
  }

  def create(speaker: Speaker) {
    DB.withConnection {
      implicit c =>
        SQL("insert into speaker (name, title, imageUrl, bio) values ({name}, {title}, {imageUrl}, {bio})").on(
          'name -> speaker.name,
          'title -> speaker.title,
          'imageUrl -> speaker.imageUrl,
          'bio -> speaker.bio
        ).executeUpdate()
    }
  }

}
