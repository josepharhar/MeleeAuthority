#include <iostream>

int main(int argc, char** argv) {
  if (argc < 2) {
    std::cerr << "Usage: scanner melee.iso" << std::endl;
  }

  int image_fd = OpenImage(argv[1]);
  if (image_fd < 0) {
    std::cerr << "Failed to open file \"" << argv[1] << "\"" << std::endl;
    return 1;
  }

  ReadSectors(image_fd);
  
  return 0;
}
