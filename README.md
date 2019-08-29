
# Firefox Lite Backend API Integration Test

Firefox Lite Backend API test to make sure backend service are good. 


## Getting Started

Please clone the project and set up Gradle environment.

```
git clone git@github.com:mozilla-tw/FFLiteApiAutomationTest.git
```

## System Requirement
1. The system can switch among [mock,stage,prod] environment to access endpoint.
2. The system can include or exclude tests by multiple tags
3. The system can constructing request
4. The system can extract, verify response 
5. The system can generate test report 

## System design overview
<img width="463" alt="Screen Shot 2019-08-28 at 5 20 08 PM" src="https://user-images.githubusercontent.com/44990878/63843274-b4714b80-c9b8-11e9-9a9d-99af43243204.png">

## Running the tests

We can shift among [mock,stage,prod] by sending env parameter as input.

```
./gradlew clean -Penv=mock runApiTest
```

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Pei Yun Liu** - *Initial work* - [Daisy Liu](https://github.com/Daisy-pliu)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.


## Acknowledgments

* Service engineering team

