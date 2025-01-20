# Estate Listing - Android Application

![Firebase App Distribution - Staging](https://github.com/thiennguyen0196/estate-listing/actions/workflows/deploy_production_firebase.yml/badge.svg)
![Firebase App Distribution - Production](https://github.com/thiennguyen0196/estate-listing/actions/workflows/deploy_staging_firebase.yml/badge.svg)
![CI Review pull request](https://github.com/thiennguyen0196/estate-listing/actions/workflows/review_pull_request.yml/badge.svg)

Estate Listing is a simple Android application which allows users to view the list of real estate and bookmark it.

Estate Listing app is distributed on Firebase App Distribution. Please contact me to be added into the tester invitation link.

## **1. Architecture**
Using Clean Architecture and MVVM, the application provides a clean way to maintain and easy to scale up as well as writing unit test. It contains 3 layers:
- Data
- Domain
- Presentation (app)

The overview architect look like this:

![image](https://user-images.githubusercontent.com/21035435/69536839-9f4c8e80-0fa0-11ea-85ee-d7823e5a46b0.png)

## **2. Third-party libraries and technique**
Below is the list of third-party libraries and technique that I used in the project:

- **AndroidX**
- **Jetpack Compose**
- **Coroutines**
- **Retrofit**
- **OkHttp**
- **Moshi**
- **Dagger Hilt**
- **Room**
- **Coil**
- **Timber**
- **Chucker**
- **Junit**
- **Mockk**
- **Turbine**
- **Firebase**

## **3. Screenshot**

https://github.com/user-attachments/assets/cb14c2c4-014a-46bf-a9a3-cbad9296ae9a

## **4. Build the project on local**
- Please contact me to get the `google-services.json` and place it in the `app` folder.
- After cloning the repo, open Android Studio and update your BASE_API_URL in `env.properties` file.
- Run build.

## **5. Get the app APK file from Firebase App Distribution**
- Please contact me to be invited to the tester link and download the apk file.
- Download and install it on your device.

Thank you and have a nice day!
