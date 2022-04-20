package persist
import domain.Pet

import java.sql.DriverManager
import java.sql.Connection


class Persistence {
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost/mysql"
  val username = "root"
  val password = "root"
  Class.forName(driver)

  def insert(pet:Pet) {
    try {
      val connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      statement.executeQuery(s"INSERT INTO DataTable Values (${pet.name},${pet.typeOfPet})")
      connection.close()
      } catch {
      case e => e.printStackTrace
    }

  }
  def delete(pet:String) {
    try {
      val connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      statement.executeQuery(s"DELETE FROM DataTable WHERE name = ${pet}")
      connection.close()
    } catch {
      case e => e.printStackTrace
    }

  }
  def get() {
    try {
      val connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement()
      val pets = statement.executeQuery(s"SELECT * FROM DataTable")
      connection.close()
      pets
    } catch {
      case e => e.printStackTrace
    }

  }
}