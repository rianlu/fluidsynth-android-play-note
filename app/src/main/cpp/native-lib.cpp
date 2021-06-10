#include <jni.h>
#include <string>
#include <fluidsynth.h>
#include <unistd.h>

fluid_synth_t *synth;
fluid_settings_t *settings;
fluid_audio_driver_t *adriver;

extern "C"
JNIEXPORT void JNICALL
Java_com_example_fluidsynthandroidhelloworld_MainActivity_loadSoundFont(JNIEnv *env, jobject thiz,
                                                                        jstring jSoundfontPath) {
    // Setup synthesizer
    settings = new_fluid_settings();
    fluid_settings_setstr(settings, "audio.driver", "opensles");
    fluid_settings_setint(settings, "audio.opensles.use-callback-mode", 1);
    fluid_settings_setint(settings, "audio.period-size", 64);

    synth = new_fluid_synth(settings);
    adriver = new_fluid_audio_driver(settings, synth);

    // Load sample soundfont
    const char* soundfontPath = env->GetStringUTFChars(jSoundfontPath, nullptr);
    fluid_synth_sfload(synth, soundfontPath, 1);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_fluidsynthandroidhelloworld_MainActivity_playNote(JNIEnv *env, jobject thiz,
                                                                   jint key) {
    fluid_synth_noteon(synth, 0, key, 127); // play middle C
}extern "C"
JNIEXPORT void JNICALL
Java_com_example_fluidsynthandroidhelloworld_MainActivity_release(JNIEnv *env, jobject thiz) {
    delete_fluid_audio_driver(adriver);
    delete_fluid_synth(synth);
    delete_fluid_settings(settings);
}