#include <iostream>
#include <vector>

enum class BattleCondition {
  red,
  yellow,
  green
};

void print_it(const int &n) {
  std::cout << "-" << n;
}

int main() {
  std::vector<int> nums{3, 4, 2, 8, 15, 267};

  std::for_each(nums.begin(), nums.end(), std::bind(&print_it, std::placeholders::_1));
  std::cout << std::endl;
  
  std::for_each(nums.begin(), nums.end(), [](const int &n) { std::cout << "-" << n; });
  std::cout << std::endl;

  auto myLambda = [](const int &n) { std::cout << "*" << n; };
  std::for_each(nums.begin(), nums.end(), myLambda);
  std::cout << std::endl;

  // lambda is converting a switch statement to  switch expression
  auto currentLight = BattleCondition::green;
  // the ampersand here, allows us to do a reference scoping,
  // we can get the reference to all the values that are in the
  // surrounding scope, and so we can get accessto current light
  // and then we're getting its value and we're using it to set
  // our shield level with,
  const auto shieldLevel = [&]() {
    switch (currentLight) {
      case BattleCondition::green:
        return 50;
      case BattleCondition::yellow:
        return 700;
      case BattleCondition::red:
      default:
        return 1000;
    }
  }();
  std::cout << "Current Shield Level = " << shieldLevel << std::endl;

  return 0;
}

// Notes:
// Lambdas are first class objects in C++. You can assign them to variables,
// you can send them to functions, you can create them on the fly.