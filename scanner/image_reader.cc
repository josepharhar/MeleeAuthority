#include <unistd.h>

#define FST_OFFSET 0x424

int OpenImage(const char* filename) {
  return open(filename, O_RDONLY);
}

int ReadSectors(int fd) {
  char buffer[4];
  int return_code;

  return_code = pread(fd, buffer, 4, FST_OFFSET);
  if (return_code < 0) {
    return return_code;
  }
}
