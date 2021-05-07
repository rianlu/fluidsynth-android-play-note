# FluidSynth For Android

因为目前需求只需要使用`FluidSynth`播放对应`key`，所以对原来的库做了些修改：

- 移除一些无关的库

- libs 移到了 `jniLibs`下

- 新增三个方法，其实就是把原先提供的方法进行了拆分

  ```java
  public native void loadSoundFont(String soundfontPath);
  
  public native void playNote(int key);
  
  public native void release();
  ```



> 原作者博客地址：[Creating a Fluidsynth Hello World App for Android](https://medium.com/@hectorricardomendez/creating-a-fluidsynth-hello-world-app-for-android-5e112454a8eb)

