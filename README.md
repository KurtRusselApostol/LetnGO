### For new interns, please read this first and ask questions later.
# About LetnGo
Letn'Go is an Android booking and reservation app similar to Airbnb and Booking.com. The app aims to:
* Service clients for easy bookings to their preferred location.
* Give hosts a platform for easy and convinient booking creation and management.
* Have hassle-free transactions between hosts and clients.

This app offers:
* Platform for the bookers to search for desired location to stay.
* Easy setup for hosts to add booking offerings.
* Recommended places to stay for first-time and long-time bookers.

# About the Repository
This application is still in development stage with lots of issues and bugs although there are lots of improvements and milestones achieved. 
This app is actively being worked on by interns of Rental Listing App team of [Melham Construction Corporation (MCC)](https://dev-melhamconstruction.pantheonsite.io/). <br />
<br /> **Only interns and employees of MCC may able to contribute to this repository.**

### Overview
This project is being worked on **Android Studio** using **Java** (back-end, middle-end), **XML** (front-end, UI/UX)
and **Firebase Realtime Database** (back-end). New interns, if you have little-to-no experience to one or more of
those, you can view the following resources:
* [Java Documentation](https://docs.oracle.com/en/java/)
* [Official Firebase Guide](https://firebase.google.com/docs/guides)
* [XML Language Tutorial by W3Schools](https://www.w3schools.com/xml/)
* [Google's Documentation for Android Studio](https://developer.android.com/guide)

### Prerequisites
If you are a new intern and already joined as a contributor, you will need to install the following:
* [Android Studio](https://developer.android.com/studio)
* [Java](https://www.oracle.com/java/technologies/downloads/)
* [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
* [OpenJDK version 18](https://jdk.java.net/18/) (optional - might be required if generating SHA-1 key) <br />
* [Github Desktop](https://desktop.github.com/) (optional)

You will also need to join the Discord server of Front-End and Back-End teams, Facebook group chat for MCC Rental Listing Team and our Trello workspace. You will need to ask your team leaders for links.<br /> <br />
For Firebase database access, ask the team leader or back-end leader for Firebase account. <br /> <br />
Working with the repository in the Android Studio requires repository cloning. To do that:
* Launch your Android Studio.
* Click 'Get from VCS'.
* Login your Github account.
* Select the LetnGo repository.
* Select which folder in your directory you will clone.
* Click clone and wait for Android Studio to install additional components. Make sure that Gradle components are properly installed.

For testing and debugging the app, you may use the Android Studio emulator (although it requires additional installs). Alternatively, you can use a third-party emulators like LDPlayer 9 or Bluestacks or an Android Smartphone (should be Android 10 or later) and enable USB debugging (requires enabling Developer option).

### Contribution Guide
Here are some guidelines when developing the app:
* Front-end and back-end team leaders and members need to work together. It is recommended to use Discord voice chat for collaboration.
* Tasks should be properly distributed by team leaders according to the development timeline. Use Trello to keep on track.
* Bugs and issues discovered in the app should be listed and addressed to each members and leaders.
* **IMPORTANT!!!** When working on a certain XML or Java file, tell your members exactly the file you are working on. Pushing your code in a same file that your other member worked on will cause merging issues and will reject the push. It is suggested to use Pull Request and review the codes together or alternatively, talk to your fellow member that is working with the same file you are working on to review and compare codes before pushing and merging.
* Caution when updating your repository in Android Studio: Before updating, view committed changes first. Updating the project may lose your work. If a member pushed a file you are working on, talk to him/her about merging the file.
* If you don't want to apply your changes while you have not committed yet, simply shelf the changes.

Note that changes can be easily tracked by looking at commits. It is logged and whatever you pushed in the repository can be reviewed by the project leader and officers-in-charge (OIC). <br />

The repository in Java side is divided into packages (folders). Previously, all Java files are grouped in one package and it made the development more confusing. Now, it has a structure.
* com.example.letngo - the main package.
* login.system - where login and sign-up-related classes are.
* nav - where the main 5 fragments are. They are group by each fragments which are:
  * nav.account
  * nav.categories
  * nav.explore
  * nav.wishlist
  * nav.notifications

#### Note on Firebase Database
It is good to assign one data analyst to maintain and apply security rules for the database. <br />

As you can observe in the database, the rules are inadequate and public. For data analyst, you can apply security rules in the database once the database is finalized and ready to be deployed. Do this before the app launch. <br />

The database used is Realtime Database. It is structured using JSON and it is unstructured and older compared to Firestore. Firestore Database, on the other hand, is way more structured database that includes the support of arrays and more. If you feel that you need to switch from Realtime Database to Firestore Database, you need to discuss it to developers as it requires major overhaul in coding side of things, especially in Java classes that uses Firebase. A [lengthy detail here](https://firebase.google.com/docs/firestore/rtdb-vs-firestore) explains the differences of two databases that you might want to read. [A helpful video by Chris Esplin](https://www.youtube.com/watch?v=TmXct7seeBY) might also help! Do take note that changing from Realtime Database to Firestore Database **can be lengthy** and **slow down the development process** as you will restructure the database and codebase. <br />

Our current database is somewhat unstructured. There are some old data that remains in there. Fortunately, there are two parents (which name 'Hostings' and 'User_Acct') that were provided as a basis for the structure of our database. You can check it out and maybe improve on it.

### For Code Commeting and Documentarians
It is a good practice to comment your code in order for the future programmers who will be working in this project understand the flow and functionality of the codebases. Refer to the *Java documentation* for code commenting. <br /> <br />
As per instruction of the Officer-In-Charge (OIC), for progress reports, front-end and back-end leaders, post those in MCC Discord Thread 'Updates' in 'rental-listing-website' channel and in Trello LetnGo Main Board. <br /> <br />
It is recommended to assign a documentarian to manage and track the project progress, document changes and additions and modify this README file. If you want to add something in this README file, you are free to do so as long as:
* It would help future programmers to get the gist about this project.
* Informs future project leaders about the project.
* It is presentable to the higher-ups.
* Keep the repository viewers engaged about the project progress (since this is public).

Project progress is visible in Trello and Attendance Tracker. <br />
For questions, do not hestitate to ask the project leader, front-end leader, back-end leader, OIC, other contributors, and former interns. <br />
<br />
Good luck!!!
