# Movie Hangman

Classic Movie Hangman Game built using Jetpack Compose. This demonstrates [Modern Android best practices](https://developer.android.com/topic/architecture#modern-app-architecture).

- Compose canvas drawing, Animations
- Kotlin Coroutines with Flow
- Hilt for DI
- Room for Data
- Modular MVVM Architecture with UDF (Inspired by [Unscramble](https://github.com/google-developer-training/basic-android-kotlin-compose-training-unscramble) and [Noty](https://github.com/PatilShreyas/NotyKT/tree/master/noty-android))

## Design and Architecture Details
Click on any key on the Button Layout sends event to ViewModel to handle. The event handlers in ViewModel take appropriate actions and update the state when required:
- A life is lost on wrong guess, minus score 
- Increment the score for guessing it right
- Add a life when a movie is guessed
- Keep playing until all lives lost
- Start a new game 
- Game (app) exhausts when no more movie is left to guess

The state changes are passed down to the UI for updates.

The architecture of the app follows recommended [App Architecture principles](https://developer.android.com/topic/architecture)

<img src="/results/Architecture.png" width="260">

The classic Hangman is drawn using Compose Animations based on lives left. 

## Illustrations

<img src="/results/screenshot_1.png" width="260">&emsp;<img src="/results/screenshot_2.png" width="260">&emsp;<img src="/results/screenshot_3.png" width="260">
### Animated Preview of the App
<img src="/results/hangman.gif" width="260">

# Contributions

Most welcome to file issues, PRs.

# License

```
Copyright 2023 Varsha Kulkarni
 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
 
    https://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


