# FluidSynth For Android

因为目前需求只需要使用`FluidSynth`播放对应`key`，所以对原来的库做了些修改：

- 基于`main`分支

- 移除一些无关的库

- libs 移到了 `jniLibs`下

- 新增三个方法，其实就是把原先提供的方法进行了拆分

  ```java
  public native void loadSoundFont(String soundfontPath);
  
  public native void playNote(int key);
  
  public native void release();
  ```



存在问题：在 `Android 7.0` 以下会闪退，错误日志如下：

```
Process: com.example.fluidsynthandroidhelloworld, PID: 13027
    java.lang.UnsatisfiedLinkError: dlopen failed: cannot locate symbol "in6addr_any" referenced by "/data/app/com.example.fluidsynthandroidhelloworld-1/lib/x86/libfluidsynth.so"...
        at java.lang.Runtime.loadLibrary(Runtime.java:372)
        at java.lang.System.loadLibrary(System.java:1076)
```



另一分支 `opensles` 中使用另一个项目[fluidsynth-android-opensles](https://github.com/degill/fluidsynth-android-opensles)编译出的 `so` 库替换，可以解决闪退的问题，但是同样在`Android 7.0` 以下不能播放，并且在初始化的时候会有一声异响。。。



> 原作者博客地址：[Creating a Fluidsynth Hello World App for Android](https://medium.com/@hectorricardomendez/creating-a-fluidsynth-hello-world-app-for-android-5e112454a8eb)

> opensles 作者地址：[[fluidsynth-android-opensles](https://github.com/degill/fluidsynth-android-opensles)](https://github.com/degill/fluidsynth-android-opensles)

