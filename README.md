# Techtalk
===========================================================

Introduction
-------------

### Functionality
Show users the video / article post. Load data from locally saved json file and then save it to the database. User can view the articles/ videos. For paid posts user will have to purchase membership.

### Arhcitecture
The project is developed using the MVVM architecture with the RxJava flavour

### Testing
The project has unit test for the repository and view models

### Libraries
* [AndroidX][androidx]
* [Android Architecture Components][arch]
* [RxJava/RxAndroid][rxJava]
* [Android Data Binding][data-binding]
* [Dagger 2][dagger2] for dependency injection
* [Glide][glide] for image loading
* [espresso][espresso] for UI tests
* [mockito][mockito] for mocking in tests
* [Flat Dialog][flatdialog] for subscription alert dialog
* [Play Pause View][playpause] for video play/pause button
* [Room][room-lib] for database



[androidx]: https://developer.android.com/jetpack/androidx
[rxJava]: https://github.com/ReactiveX/RxAndroid
[arch]: https://developer.android.com/arch
[data-binding]: https://developer.android.com/topic/libraries/data-binding/index.html
[espresso]: https://google.github.io/android-testing-support-library/docs/espresso/
[dagger2]: https://google.github.io/dagger
[glide]: https://github.com/bumptech/glide
[mockito]: http://site.mockito.org
[playpause]: https://github.com/OHoussein/android-material-play-pause-view
[room-lib]: https://developer.android.com/training/data-storage/room
[flatdialog]: https://github.com/mejdi14/Flat-Dialog-Android
