CriptoAssets

Application that displays scrollable list of assets using the respose from CoinBase API

Code
Architecture
    The whole data flow is using Coroutines Flow as carrier, fragment to repository.
    With presentation pattern set as MVVM, view models are responsible for holding information in memory to survive different screen behaviours.
    There is 3 modules separating responsibilities, using concepts of Clean Architecture. Basically, :data holds every information about the API integration and do wherever is necessary to return the desired object to the application.
    :domain is a pure Kotlin library that only has control about the models and interactors between :data and :app. And the :app take care about everything else, from dependency injections to UI.
App

Frameworks
    Flow Coroutines
