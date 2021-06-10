# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
cmake_minimum_required(VERSION 3.4.1)

# Create a variable fluidsynth_DIR to specify where the fluidsynth library is located.
set(fluidsynth_DIR ${CMAKE_CURRENT_SOURCE_DIR}/fluidsynth)
set(ANDROID_API_DIR ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})

# Our code (native-lib.cpp) will be calling fluidsynth functions, so we add the fluidsynth binaries as dependencies of our code.

add_library(libc++_shared SHARED IMPORTED)
set_target_properties(libc++_shared PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libc++_shared.so)

add_library(libFLAC SHARED IMPORTED)
set_target_properties(libFLAC PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libFLAC.so)

add_library(libfluidsynth SHARED IMPORTED)
set_target_properties(libfluidsynth PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libfluidsynth.so)

add_library(libglib-2.0 SHARED IMPORTED)
set_target_properties(libglib-2.0 PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libglib-2.0.so)

add_library(libgthread-2.0 SHARED IMPORTED)
set_target_properties(libgthread-2.0 PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libgthread-2.0.so)

add_library(libiconv SHARED IMPORTED)
set_target_properties(libiconv PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libiconv.so)

add_library(libintl SHARED IMPORTED)
set_target_properties(libintl PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libintl.so)

add_library(liboboe SHARED IMPORTED)
set_target_properties(liboboe PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/liboboe.so)

add_library(libogg SHARED IMPORTED)
set_target_properties(libogg PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libogg.so)

add_library(libsndfile SHARED IMPORTED)
set_target_properties(libsndfile PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libsndfile.so)

add_library(libvorbis SHARED IMPORTED)
set_target_properties(libvorbis PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libvorbis.so)

add_library(libvorbisenc SHARED IMPORTED)
set_target_properties(libvorbisenc PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/libvorbisenc.so)

add_library(preloadable_libiconv SHARED IMPORTED)
set_target_properties(preloadable_libiconv PROPERTIES IMPORTED_LOCATION ${ANDROID_API_DIR}/preloadable_libiconv.so)

# Library that will be called directly from JAVA
add_library(native-lib SHARED native-lib.cpp)

# Specifies the directory where the C or C++ source coude will look the #include <yourlibrary.h> header files
target_include_directories(native-lib PRIVATE ${fluidsynth_DIR}/include)

# Link everything alltogether. Notice that native-lib should be the first element in the list.
target_link_libraries(
        native-lib
        libfluidsynth
)
