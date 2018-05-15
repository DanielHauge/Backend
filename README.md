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
- Some architecture design and such.

### Maintainability and Testability
- Low coupling and high cohersion with interfaces and polymorphism, high quality code and low technical dept.
- Is the code maintainable and testable?

### Testing
- References to tests and testcases.

### Continious Integration / Delivery / Deployment 
- How we did Continious integration and pipeline with tests and deployment and so on.
