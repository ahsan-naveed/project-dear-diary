#include <assert.h>

// weakly typed enum (pollutes the surrounded scope)
enum TrafficLight {
    red,
    yellow,
    green
};
enum CarColor {
    black,
    gray,
    silver,
    white,
    // green // can't have the color green in two enums
};

// strongly typed enum class
enum class Pet {
    dog,
    cat,
    bird
};
enum class Mammal {
    dog,
    cougar,
    coyote,
    bear
};

int main(int argc, char const *argv[])
{
    TrafficLight color = TrafficLight::red;
    CarColor camry = CarColor::silver;

    // Notice we don't use :: operator the reason is, 
    // the surround scope has been polluted
    int colorNumber = red;
    int myCarsColor = black;
    
    Pet pet = Pet::dog;
    Mammal mammal = Mammal::dog;

    // these are all errors as they should be
    // Pet otherPet = Mammal::bear;
    // int PetNum = pet;
    // assert(pet == mammal);
    // assert (Pet::dog == Mammal::dog);

    return 0;
}
