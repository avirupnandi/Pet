package domain

case class Pet(name:String,
               typeOfPet:String)
object Pet {
  def apply(pet: String): Pet = {
    Pet(pet.split(",")(0), pet.split(",")(0))
  }
}