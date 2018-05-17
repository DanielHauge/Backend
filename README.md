# Back-end
This repository is a subsystem in a project for Software development (PBA) in Test and Database course.

This part is based on both database and test project which can be found here: [Test](https://github.com/datsoftlyngby/soft2018spring-test-teaching-material/blob/master/exercises/Final%20Assignment%202018.pdf) and [Database](https://github.com/datsoftlyngby/soft2018spring-databases-teaching-material/blob/master/assignments/Project%20Description.ipynb)

## Strategy and development
We have chosen to work as agile consultants in a 3 student team. We want to focus on high quality code which hopefully results in highly working software with low technical depth. We have chosen to develop in a mutated form of scrum using Waffle.io as a platform for this, in addition we have chosen to give the best attempt at test driven development / Acceptance test–driven development. By creating tests first and using test as a documentation/design activity aswell as using the tests for regression testing. We have formulated a test strategy which can be found [Here](https://github.com/soft2018spring-gruppe10/Report/blob/master/Test%20Strategy.md)

## Development proccess of the API
- More into waffle.io and how we did scrum -> Into pearprogramming and reviewing each others code. How we made tests first and then procceded to implement.

## API
### Functionality:
This api's functionality is to link the frontend with the database. This api will do the heavily lifting of business logic and database interactions. It will query database for information tranform the data into a valid form and reduce the amount of information needed for the frotnend. It will send information as json. 
[![https://gyazo.com/b66a557ce5bf48562d6b2f858d78f0e3](https://i.gyazo.com/b66a557ce5bf48562d6b2f858d78f0e3.png)](https://gyazo.com/b66a557ce5bf48562d6b2f858d78f0e3)

The Api is capable of getting the information which correspond with the end-user queries in the project descriptions.

### Overview
![](https://github.com/soft2018spring-gruppe10/Backend/blob/master/Documentation/Pictures/Classdiagram2.png)


### Code quality
We have designed the system with high quality in focus. We wanted to be able to maintain and test the code we build. We have taken advantage of interfaces throughout the system. Notably the DataAcessor and the DataObjects, these can be switched out with any implementation of these interfaces. This has resulted in a very decoupled system. To change how the objects are being serialized, we can change the code 1 place and it works automaticly for all dataobjects. If we want to change the format of one of the protocols, we only need change in one place. furthermore, we can change the Database freely because of the DataAcessor interface. It has also caused very low technical dept. This is the outcome of good design, which has surfaces with the use of a testing mindset.

To put it our maintability into perspective, we had a case where we wanted to have all data in all dataobjects which contained city cordinates to have different names. we wanted to change from latitude to lat and longitude to lng, because it was easy and more convinenient for the front-end library to plot in these objects with these names that way. All we had to do to change the whole systems behavior when it came to handling cities with cordinates data was the following commits:
- [change](https://github.com/soft2018spring-gruppe10/Backend/commit/6032c043d54f325a4a5b92bb9f04a594c6338243) /Apart from changing jdk
- [tests were using these values](https://github.com/soft2018spring-gruppe10/Backend/commit/ef825157a7f78af28d8afdd78e2de791a3ff3218)

### Testing
- References to tests and testcases.

### Continious Integration / Delivery / Deployment 
We have setup a jenkins server with the following architecture:

![](https://github.com/soft2018spring-gruppe10/Backend/blob/master/Documentation/Pictures/Deploymentdiagram1.png)

Because of time and money restrictions we do not practise ideal procedures. We are deploying staging to the build server, and are using test database as staging database. Idealy we would like to have a seperate enviroment for the whole staging enviroment, idealy allmost identical to the/a production enviroment. All the containers talk easily together in a custom docker bridge network shared on the droplet. This way, the dns is created and can be accesed by the docker containers name with the ingress network.

[![https://gyazo.com/b7c8cdd4c19532aa2dda24ef68a985ea](https://i.gyazo.com/b7c8cdd4c19532aa2dda24ef68a985ea.png)](https://gyazo.com/b7c8cdd4c19532aa2dda24ef68a985ea)

We have 4 simple jobs

- 1. Test and build api
- 2. Stage api
- 3. build and stage website
- 4. system test (ui-testing)

[![https://gyazo.com/160f0b378c7674364ac563e3dd5bcf54](https://i.gyazo.com/160f0b378c7674364ac563e3dd5bcf54.png)](https://gyazo.com/160f0b378c7674364ac563e3dd5bcf54)
