#include <iostream>
#include <string>

template<typename T>
T rcatenate(T v) {
    return v;
}

template<typename T, typename... Args>
T rcatenate(T head, Args... tail) {
    return rcatenate(tail...) + " " + head;
}

int main(int argc, char const *argv[])
{
    std::string s1 = "biz", s2 = "fox", s3 = "wiz";
    std::string cc = rcatenate(s1, s2, s3);
    std::cout << "reverse concatenated = " << cc << std::endl;
    return 0;
}

