#include <iostream>
#include <ifstream>
#include <stdio.h>

template<typename T>
T SwitchEndianness() {

}

int main(int argc, char** argv) {
  std::ifstream melee_file("melee.iso", ios::binary);
  std::streampos begin = melee_file.tellg();
  melee_file.seekg(0, ios::end);
  std::streampos end = melee_file.tellg();
}
