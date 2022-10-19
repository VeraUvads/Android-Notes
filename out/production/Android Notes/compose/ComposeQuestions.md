#### 1) What is recomposition?

Recomposition is the process of calling your composable functions again when inputs change. When Compose recomposes
based on new inputs, it only calls the functions or lambdas that might have changed, and skips the rest. By skipping all
functions or lambdas that don't have changed parameters, Compose can recompose efficiently.

***
#### 2) remember, remember(key), rememberSaveable, remember { derivedStateOf() } difference

*remember* - allows you to remember state from previous recompose invocation

*remember(key)* - will be recomposed only when key will change

*rememberSavable* - similarly to *remember*, but the stored value will survive the activity or process recreation using
the saved instance state mechanism (for example it happens when the screen is rotated in the Android application).

*remember { derivedStateOf(data) }* - similarly to *remember(key)*, but will recompose only if the result inside lambda
changes

***
#### 3) What is SideEffect for?

A side-effect is a change to the state of the app that happens outside the scope of a composable function.
Composables should ideally be side-effect free.
Sometimes side-effects are necessary, for example, to trigger a one-off event such as showing a snackbar or navigate to another screen given a certain state condition. These actions should be called from a controlled environment that is aware of the lifecycle of the composable. 

***
#### 4) Effect types

*LaunchedEffect* - run suspend functions in the scope of a composable.
Use to call suspend functions safely from inside a composable.

*DisposableEffect* - effects that require cleanup.
For side effects that need to be cleaned up after the keys change or if the composable leaves the Composition.
Allow register and unregister observers when needed.

*SideEffect* - publish Compose state to non-compose code

***
#### 4) State types

[//]: # TODO(https://developer.android.com/jetpack/compose/side-effects#rememberupdatedstate)

*rememberUpdatedState* -  ?

*produceState* - 

*derivedStateOf* - 

*snapshotFlow* -  ?


