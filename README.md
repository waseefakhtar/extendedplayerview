# extendedplayerview
 
![demo](demo.gif)

`extendedplayerview` is an easy to customize player view for Android extending from [PlayerView](https://exoplayer.dev/doc/reference/com/google/android/exoplayer2/ui/PlayerView.html) to make the migration super easy!

## Installation

#### Gradle
```groovy
implementation "com.waseefakhtar.extendedplayerview:extendedplayerview:1.0.0"
```

Or,

#### Manual
1. Download the project and copy ExtendedPlayerView directory to your project
2. Add the following line to your app's gradle:

```groovy
implementation project(':extendedplayerview')
```

## Usage

XML Changes:
```diff
- <com.google.android.exoplayer2.ui.PlayerView
+ <com.waseefakhtar.extendedplayerview.ExtendedPlayerView
  android:layout_width="match_parent"
  android:layout_height="wrap_content" />
```

Kotlin Changes:

```diff
- private var playerView: PlayerView
+ private var playerView: ExtendedPlayerView
```

## Customization

`extendedplayerview` is great for apps that prefer customizing player view properties. In order to make use of the easy to use customization, ExtendedPlayerView's properties can either be changed programmatically, or it can pick values from the new XML attributes.

```kotlin
playerView.cornerRadius = 84f
playerView.mute = true
playerView.controllerVisibility = false
```

Or,

```diff
<com.waseefakhtar.extendedplayerview.ExtendedPlayerView
        android:id="@+id/player_view"
        android:layout_width="match_parent"
        android:layout_height="256dp"
        android:layout_margin="16dp"
+       app:playerCornerRadius="20dp"
+       app:controllerVisibility="false"
+       app:mutePlayer="false"
        />
```

## License

```
Copyright 2020 Waseef Akhtar.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
