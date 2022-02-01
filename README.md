CriptoAssets

Application that displays scrollable list of assets using the response from CoinBase API

Code
Architecture
    The whole data flow is using Coroutines Flow as carrier, fragment to repository.
    With presentation pattern set as MVVM, view models are responsible for holding information in memory to survive different screen behaviours.
    There is 3 modules separating responsibilities, using concepts of Clean Architecture. Basically, :data holds every information about the API integration and do wherever is necessary to return the desired object to the application.
    :domain is a pure Kotlin library that only has control about the models and interactors between :data and :app. And the :app take care about everything else, from dependency injections to UI.
    Repository pattern is been use on :data to implement specific integrations in each class and delivering data to Room DB, while Flow Coroutines updates the view of changes on query results.
    
App

![Screenshot_20220130-203459](https://user-images.githubusercontent.com/7894673/151793070-368766bb-7a5f-4bb4-9468-5300185661e4.png)
![Screenshot_20220131-090322](https://user-images.githubusercontent.com/7894673/151793074-69058754-019d-4895-bff7-7513f34ab35f.png)
![Screenshot_20220131-090337](https://user-images.githubusercontent.com/7894673/151793079-ea3e0d9d-759e-4c73-84ab-a70d61ad5f75.png)
![Screenshot_20220131-090358](https://user-images.githubusercontent.com/7894673/151793086-a355fe7a-e6ed-4afe-ba3b-213e602f49e9.png)


Frameworks|Tools
    Flow Coroutines
    ViewModel
    DataBinding
    ConstraintLayout
    Room
    Retrofit
